//Gabriel Copley
//Last Revised 2/20/09
//WHERE portion of a SELECT query
//USES table, SubQueryOperators, compareOps, and HelperFunctions

import java.util.Stack;
import java.util.Vector;

class WhereClass {
	final String ctrlString="####";
	Vector<Table> subQueries;
	Table myTable;

	//Makes subQueries, table "global"
	private WhereClass(Table argTable) {
		subQueries=new Vector<Table>();
		myTable=argTable;
	}

	//Returns all rows in argTable that returned true when the functions were performed.
	//Note: This is for small dbs only, since it is faster to run
	//  logic commands instead of retrieving data from the table,
	//  parsing by row would be faster then by column.
	public static Table where(Table argTable, String commands) {
		//Bail if argTable is empty
		if(argTable.getRowCount()==0)
			return argTable;
		WhereClass whereObj=new WhereClass(argTable);
		int selectIndex;
		int inIndex;
		int oParen;
		int cParen;
		String[] args;
		Stack<Integer> openParens;
		Table results;
		//Handle any sub queries, the single | is deliberate in the case of multiple subQueries
		while((selectIndex=commands.indexOf("(SELECT"))!=-1 | (inIndex=commands.indexOf("IN"))!=-1) {
			//Handle the IN function
			if(inIndex!=-1 && (selectIndex==-1 || inIndex<selectIndex)) {
				if((oParen=commands.substring(0,inIndex).lastIndexOf('('))!=-1) {
					cParen=getCParen(commands.substring(inIndex));
				}
				else {
					oParen=0;
					cParen=commands.length();
				}
				args=commands.substring(oParen,cParen).split("IN");
				whereObj.subQueries.add(TableOps.in(HelperFunctions.convertToTable(whereObj.myTable, args[0].trim()),args[1].trim()));
				//commands.substring(commands.length+1 gives arrayOutOfBounds instead of an empty string
				if(cParen==commands.length())
					--cParen;
				commands=commands.substring(0,oParen) +
				  whereObj.ctrlString + (whereObj.subQueries.size()-1) +
				  commands.substring(cParen+1, commands.length());
			}
			//Handle EXISTS, ANY, ALL
			else {
				if((oParen=commands.substring(0,selectIndex).lastIndexOf('('))!=-1) {
					cParen=getCParen(commands.substring(selectIndex));
				}
				else {
					oParen=0;
					cParen=commands.length();
				}
				if(commands.contains("EXISTS")) {
					args=commands.substring(oParen,cParen).split("EXISTS");
					whereObj.subQueries.add(SubQueryOperators.existsOp(HelperFunctions.convertToTable(whereObj.myTable, args[0].trim()),args[1].trim()));
				}
				else if(commands.contains("ANY")) {
					args=commands.substring(oParen,cParen).split("ANY");
					int opIndex;
					if((opIndex=args[0].indexOf("<"))!=-1 || (opIndex=args[0].indexOf(">"))!=-1 || (opIndex=args[0].indexOf("="))!=-1) {
						whereObj.subQueries.add(SubQueryOperators.anyOp(HelperFunctions.convertToTable(whereObj.myTable, args[0].substring(0, opIndex).trim()),args[0].substring(opIndex),args[1].trim()));
					}
					//??? Unknown operator
					else
						whereObj.subQueries.add(HelperFunctions.createTable(CompareOps.FALSE, CompareOps.BOOLEAN, whereObj.myTable.getRowCount(), CompareOps.FALSE));
				}
				else if(commands.contains("ALL")) {
					args=commands.substring(oParen,cParen).split("ALL");
					int opIndex;
					if((opIndex=args[0].indexOf("<"))!=-1 || (opIndex=args[0].indexOf(">"))!=-1 || (opIndex=args[0].indexOf("="))!=-1) {
						whereObj.subQueries.add(SubQueryOperators.allOp(HelperFunctions.convertToTable(whereObj.myTable, args[0].substring(0, opIndex).trim()),args[0].substring(opIndex),args[1].trim()));
					}
					//??? Unknown operator
					else
						whereObj.subQueries.add(HelperFunctions.createTable(CompareOps.FALSE, CompareOps.BOOLEAN, whereObj.myTable.getRowCount(), CompareOps.FALSE));
				}
				if(cParen==commands.length())
					--cParen;
				commands=commands.substring(0,oParen) +
				  whereObj.ctrlString + (whereObj.subQueries.size()-1) +
				  commands.substring(cParen+1, commands.length());
			}
		}
		//Handle everything in parenthesis
		if((oParen=commands.indexOf('('))!=-1) {
			openParens=new Stack<Integer>();
			openParens.push(oParen);
			while(openParens.size()!=0 || commands.indexOf('(')!=-1) {
				if(commands.indexOf('(', oParen+1)!=-1 && (commands.indexOf('(', oParen+1) < commands.indexOf(')', oParen+1))) {
					oParen=commands.indexOf('(', oParen);
					openParens.push(oParen);
				}
				else {
					oParen=openParens.pop();
					cParen=commands.indexOf(')', oParen+1);
					whereObj.subQueries.add(whereObj.runOperator(commands.substring(oParen+1, cParen)));
					commands=commands.substring(0,oParen) +
					  whereObj.ctrlString + (whereObj.subQueries.size()-1) +
					  commands.substring(cParen+1, commands.length());
				}
			}
		}
		//Handle the last operator
		results=whereObj.runOperator(commands);
		//Remove all the false rows
		for(int curRow=results.getRowCount()-1; curRow>=0; --curRow) {
			if(CompareOps.FALSE.equals((String)results.getValueAt(curRow, 0))) {
				whereObj.myTable.removeRow(curRow);
			}
		}
		return whereObj.myTable;
	}
	//Runs the given command
	private Table runOperator(String command) {
		String[] args;
		Table lArg;
		Table rArg;
		if(command.contains("AND")) {
			args=command.split("AND");
			args[0]=args[0].trim();
			args[1]=args[1].trim();
			if(args[0].startsWith(ctrlString))
				lArg=subQueries.elementAt(Integer.valueOf(args[0].substring(args[0].indexOf("ctrlString")+ctrlString.length()+1)));
			else
				lArg=HelperFunctions.convertToTable(myTable, args[0]);
			if(args[1].startsWith(ctrlString))
				rArg=subQueries.elementAt(Integer.valueOf(args[1].substring(args[1].indexOf("ctrlString")+ctrlString.length()+1)));
			else
				rArg=HelperFunctions.convertToTable(myTable, args[1]);
			return(CompareOps.and(lArg, rArg));
		}
		else if(command.contains("OR")) {
			args=command.split("OR");
			args[0]=args[0].trim();
			args[1]=args[1].trim();
			if(args[0].startsWith(ctrlString))
				lArg=subQueries.elementAt(Integer.valueOf(args[0].substring(args[0].indexOf("ctrlString")+ctrlString.length()+1)));
			else
				lArg=HelperFunctions.convertToTable(myTable, args[0]);
			if(args[1].startsWith(ctrlString))
				rArg=subQueries.elementAt(Integer.valueOf(args[1].substring(args[1].indexOf("ctrlString")+ctrlString.length()+1)));
			else
				rArg=HelperFunctions.convertToTable(myTable, args[1]);
			return CompareOps.or(lArg, rArg);
		}
		else if(command.contains("NOT")) {
			args=command.split("NOT");
			args[1]=args[1].trim();
			if(args[1].startsWith(ctrlString))
				rArg=subQueries.elementAt(Integer.valueOf(args[1].substring(args[1].indexOf("ctrlString")+ctrlString.length()+1)));
			else
				rArg=HelperFunctions.convertToTable(myTable, args[1]);
			return CompareOps.not(rArg);
		}
		else if(command.contains("!=")) {
			args=command.split("!=");
			args[0]=args[0].trim();
			args[1]=args[1].trim();
			if(args[0].startsWith(ctrlString))
				lArg=subQueries.elementAt(Integer.valueOf(args[0].substring(args[0].indexOf("ctrlString")+ctrlString.length()+1)));
			else
				lArg=HelperFunctions.convertToTable(myTable, args[0]);
			if(args[1].startsWith(ctrlString))
				rArg=subQueries.elementAt(Integer.valueOf(args[1].substring(args[1].indexOf("ctrlString")+ctrlString.length()+1)));
			else
				rArg=HelperFunctions.convertToTable(myTable, args[1]);
			return CompareOps.notEqual(lArg, rArg);
		}
		else if(command.contains("<=")) {
			args=command.split("<=");
			args[0]=args[0].trim();
			args[1]=args[1].trim();
			if(args[0].startsWith(ctrlString))
				lArg=subQueries.elementAt(Integer.valueOf(args[0].substring(args[0].indexOf("ctrlString")+ctrlString.length()+1)));
			else
				lArg=HelperFunctions.convertToTable(myTable, args[0]);
			if(args[1].startsWith(ctrlString))
				rArg=subQueries.elementAt(Integer.valueOf(args[1].substring(args[1].indexOf("ctrlString")+ctrlString.length()+1)));
			else
				rArg=HelperFunctions.convertToTable(myTable, args[1]);
			return CompareOps.lessEqual(lArg, rArg);
		}
		else if(command.contains(">=")) {
			args=command.split(">=");
			args[0]=args[0].trim();
			args[1]=args[1].trim();
			if(args[0].startsWith(ctrlString))
				lArg=subQueries.elementAt(Integer.valueOf(args[0].substring(args[0].indexOf("ctrlString")+ctrlString.length()+1)));
			else
				lArg=HelperFunctions.convertToTable(myTable, args[0]);
			if(args[1].startsWith(ctrlString))
				rArg=subQueries.elementAt(Integer.valueOf(args[1].substring(args[1].indexOf("ctrlString")+ctrlString.length()+1)));
			else
				rArg=HelperFunctions.convertToTable(myTable, args[1]);
			return CompareOps.greaterEqual(lArg, rArg);
		}
		else if(command.contains(">")) {
			args=command.split(">");
			args[0]=args[0].trim();
			args[1]=args[1].trim();
			if(args[0].startsWith(ctrlString))
				lArg=subQueries.elementAt(Integer.valueOf(args[0].substring(args[0].indexOf("ctrlString")+ctrlString.length()+1)));
			else
				lArg=HelperFunctions.convertToTable(myTable, args[0]);
			if(args[1].startsWith(ctrlString))
				rArg=subQueries.elementAt(Integer.valueOf(args[1].substring(args[1].indexOf("ctrlString")+ctrlString.length()+1)));
			else
				rArg=HelperFunctions.convertToTable(myTable, args[1]);
			return CompareOps.greaterThan(lArg, rArg);
		}
		else if(command.contains("<")) {
			args=command.split("<");
			args[0]=args[0].trim();
			args[1]=args[1].trim();
			if(args[0].startsWith(ctrlString))
				lArg=subQueries.elementAt(Integer.valueOf(args[0].substring(args[0].indexOf("ctrlString")+ctrlString.length()+1)));
			else
				lArg=HelperFunctions.convertToTable(myTable, args[0]);
			if(args[1].startsWith(ctrlString))
				rArg=subQueries.elementAt(Integer.valueOf(args[1].substring(args[1].indexOf("ctrlString")+ctrlString.length()+1)));
			else
				rArg=HelperFunctions.convertToTable(myTable, args[1]);
			return CompareOps.lessThan(lArg, rArg);
		}
		else if(command.contains("=")) {
			args=command.split("=");
			args[0]=args[0].trim();
			args[1]=args[1].trim();
			if(args[0].startsWith(ctrlString))
				lArg=subQueries.elementAt(Integer.valueOf(args[0].substring(args[0].indexOf("ctrlString")+ctrlString.length()+1)));
			else
				lArg=HelperFunctions.convertToTable(myTable, args[0]);
			if(args[1].startsWith(ctrlString))
				rArg=subQueries.elementAt(Integer.valueOf(args[1].substring(args[1].indexOf("ctrlString")+ctrlString.length()+1)));
			else
				rArg=HelperFunctions.convertToTable(myTable, args[1]);
			return CompareOps.equals(lArg, rArg);
		}
		//No operator found, attempt to return whatever the last operator returned
		else if(command.contains(ctrlString)) {
			return (subQueries.elementAt(Integer.valueOf(command.substring(command.indexOf("ctrlString")+ctrlString.length()+1))));
		}
		else if(command.trim().equals(CompareOps.TRUE))
			return HelperFunctions.createTable(CompareOps.TRUE, CompareOps.BOOLEAN, myTable.getRowCount(), CompareOps.TRUE);
		//Unknown Operator? return false
		
		return HelperFunctions.createTable(CompareOps.FALSE, CompareOps.BOOLEAN, myTable.getRowCount(), CompareOps.FALSE);
	}
	//Returns the closeParen assuming an openParen prior to this string
	private static int getCParen(String command) {
		int parenIndex=0;
		int parenDepth=1;
		
		while(parenDepth>0) {
			if(command.indexOf('(', parenIndex) != -1) {
				if(command.indexOf('(', parenIndex)<command.indexOf(')', parenIndex)) {
					++parenDepth;
					parenIndex=command.indexOf('(', parenIndex)+1;
				}
				else {
					--parenDepth;
					parenIndex=command.indexOf(')', parenIndex)+1;
				}
			}
			else {
				--parenDepth;
				parenIndex=command.indexOf(')', parenIndex)+1;
				//If no ')' found, return -1
				if(parenIndex==0)
					return (-1);
			}
		}
		return parenIndex;
	}
}