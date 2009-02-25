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
		if(argTable.getRowCount()==0)
			return argTable;
//		for(int ctr=0; ctr<argTable.getRowCount(); ++ctr) {
//			System.out.println("argTable 14: " + argTable.getValueAt(ctr, 0));
//		}
		//System.out.println("Where Entry " + commands);
		WhereClass whereObj=new WhereClass(argTable);
		int selectIndex;
		int subQueryIndex;
		int oParenIndex;
		String[] args;
		String subQuery;
		//Handle any sub queries
		while((selectIndex=commands.indexOf("(SELECT"))!=-1) {
			if((subQueryIndex=commands.substring(0, selectIndex).indexOf("EXISTS"))!=-1) {
				subQuery=whereObj.getSubQuery(commands.substring(subQueryIndex+6));
				whereObj.subQueries.add(SubQueryOperators.existsOp(whereObj.myTable, subQuery));
				commands=commands.substring(0,subQueryIndex) +
				whereObj.ctrlString + whereObj.subQueries.size() +
				commands.substring(subQueryIndex+6+subQuery.length(), commands.length());
			}
			else if((subQueryIndex=commands.substring(0, selectIndex).indexOf("IN"))!=-1) {
				subQuery=whereObj.getSubQuery(commands.substring(subQueryIndex+2));
				oParenIndex=commands.substring(0, subQueryIndex).lastIndexOf('(');
				args=commands.substring(oParenIndex+1, subQueryIndex-1).split(" ");
				whereObj.subQueries.add(TableOps.in(HelperFunctions.convertToTable(whereObj.myTable, args[0].trim()), subQuery));
				commands=commands.substring(0,oParenIndex) +
				whereObj.ctrlString + whereObj.subQueries.size() +
				commands.substring(subQueryIndex+2+subQuery.length(), commands.length());
			}
			else if((subQueryIndex=commands.substring(0, selectIndex).indexOf("ANY"))!=-1) {
				subQuery=whereObj.getSubQuery(commands.substring(subQueryIndex+3));
				oParenIndex=commands.substring(0, subQueryIndex).lastIndexOf('(');
				args=commands.substring(oParenIndex+1, subQueryIndex-1).split(" ");
				whereObj.subQueries.add(SubQueryOperators.anyOp(HelperFunctions.convertToTable(whereObj.myTable, args[0].trim()), args[1].trim(), subQuery));
				commands=commands.substring(0,oParenIndex) +
				whereObj.ctrlString + whereObj.subQueries.size() +
				commands.substring(subQueryIndex+3+subQuery.length(), commands.length());
			}
			else if((subQueryIndex=commands.substring(0, selectIndex).indexOf("ALL"))!=-1) {
				subQuery=whereObj.getSubQuery(commands.substring(subQueryIndex+3));
				oParenIndex=commands.substring(0, subQueryIndex).lastIndexOf('(');
				args=commands.substring(oParenIndex+1, subQueryIndex-1).split(" ");
				whereObj.subQueries.add(SubQueryOperators.allOp(HelperFunctions.convertToTable(whereObj.myTable, args[0].trim()), args[1].trim(), subQuery));
				commands=commands.substring(0,oParenIndex) +
				whereObj.ctrlString + whereObj.subQueries.size() +
				commands.substring(subQueryIndex+3+subQuery.length(), commands.length());
			}
		}
		//Handle everything else
		Stack<Integer> openParens=new Stack<Integer>();
		int oParen;
		int cParen;
		//System.out.println("Where Entry2 " + commands);
		if((oParen=commands.indexOf('('))!=-1) {
			//System.out.println("oParens" + commands);
			openParens.push(oParen);
			while(openParens.size()!=0 || commands.indexOf('(')!=-1) {
				//System.out.println("oParens2" + commands);
				if(commands.indexOf('(', oParen+1)!=-1 && (commands.indexOf('(', oParen+1) < commands.indexOf(')', oParen+1))) {
					//System.out.println("oParens3" + "Result: " + commands.indexOf('(', oParen+1) + commands);
					oParen=commands.indexOf('(', oParen);
					openParens.push(oParen);
				}
				else {
					oParen=openParens.pop();
					cParen=commands.indexOf(')', oParen+1);
					//System.out.println("oParens4" + commands.substring(oParen+1, cParen));
					whereObj.subQueries.add(whereObj.runOperator(commands.substring(oParen+1, cParen)));
					commands=commands.substring(0,oParen) +
					  whereObj.ctrlString + whereObj.subQueries.size() +
					  commands.substring(cParen+1, commands.length());
				}
			}
		}
		//System.out.println("Rest " + commands);
		Table results=whereObj.runOperator(commands);
//		for(int ctr=0; ctr<results.getRowCount(); ++ctr) {
//			System.out.println("TRUE/FALSE " + results.getValueAt(ctr, 0));
//		}

		for(int curRow=results.getRowCount()-1; curRow>=0; --curRow) {
			if((String)results.getValueAt(curRow, 0)==CompareOps.FALSE) {
				//System.out.println("Removing Row");
				whereObj.myTable.removeRow(curRow);
			}
		}
		return whereObj.myTable;
	}
	//Runs the given command
	private Table runOperator(String command) {
		//System.out.println("Run " + command);
		String[] args;
		Table lArg;
		Table rArg;
		if(command.contains("AND")) {
			args=command.split("AND");
			args[0]=args[0].trim();
			args[1]=args[1].trim();
			if(args[0].startsWith(ctrlString)) {
				//System.out.println(args[0].indexOf("ctrlString")+ctrlString.length()+1);
				//System.out.println(Integer.valueOf((args[0].substring(args[0].indexOf("ctrlString")+ctrlString.length()+1))));
				lArg=subQueries.elementAt(Integer.valueOf((args[0].substring(args[0].indexOf("ctrlString")+ctrlString.length()+1)))-1);
			}
			else
				lArg=HelperFunctions.convertToTable(myTable, args[0]);
			if(args[1].startsWith(ctrlString)) {
				rArg=subQueries.elementAt(Integer.valueOf((args[1].substring(args[1].indexOf("ctrlString")+ctrlString.length()+1)))-1);
			}
			else
				rArg=HelperFunctions.convertToTable(myTable, args[1]);
//			for(int ctr=0; ctr<lArg.getRowCount(); ++ctr) {
//				System.out.println("AND" + lArg.getValueAt(ctr, 0));
//			}
			return(CompareOps.and(lArg, rArg));
		}
		else if(command.contains("OR")) {
			args=command.split("OR");
			args[0]=args[0].trim();
			args[1]=args[1].trim();
			if(args[0].startsWith(ctrlString)) {
				lArg=subQueries.elementAt(Integer.valueOf((args[0].substring(args[0].indexOf("ctrlString")+ctrlString.length()+1)))-1);
			}
			else
				lArg=HelperFunctions.convertToTable(myTable, args[0]);
			if(args[1].startsWith(ctrlString)) {
				rArg=subQueries.elementAt(Integer.valueOf((args[1].substring(args[1].indexOf("ctrlString")+ctrlString.length()+1)))-1);
			}
			else
				rArg=HelperFunctions.convertToTable(myTable, args[1]);
			return CompareOps.or(lArg, rArg);
		}
		else if(command.contains("NOT")) {
			//System.out.println(command.contains(ctrlString));
			args=command.split("NOT");
			args[1]=args[1].trim();
			//System.out.println("NOT " + args[1].indexOf(ctrlString) + "ctrl"+ctrlString.length()+1);
			//System.out.println(Integer.valueOf((args[1].substring(args[1].indexOf("ctrlString")+ctrlString.length()+1))));
			if(args[1].startsWith(ctrlString)) {
				rArg=subQueries.elementAt(Integer.valueOf((args[1].substring(args[1].indexOf("ctrlString")+ctrlString.length()+1)))-1);
			}
			else
				rArg=HelperFunctions.convertToTable(myTable, args[1]);
//			for(int ctr=0; ctr<rArg.getRowCount(); ++ctr) {
//				System.out.println("NOT 166, whereClass: " + rArg.getValueAt(ctr, 0));
//			}
			return CompareOps.not(rArg);
		}
		else if(command.contains("!=")) {
			args=command.split("!=");
			args[0]=args[0].trim();
			args[1]=args[1].trim();
			if(args[0].startsWith(ctrlString)) {
				lArg=subQueries.elementAt(Integer.valueOf((args[0].substring(args[0].indexOf("ctrlString")+ctrlString.length()+1)))-1);
			}
			else
				lArg=HelperFunctions.convertToTable(myTable, args[0]);
			if(args[1].startsWith(ctrlString)) {
				rArg=subQueries.elementAt(Integer.valueOf((args[1].substring(args[1].indexOf("ctrlString")+ctrlString.length()+1)))-1);
			}
			else
				rArg=HelperFunctions.convertToTable(myTable, args[1]);
			return CompareOps.notEqual(lArg, rArg);
		}
		else if(command.contains("<=")) {
			args=command.split("<=");
			args[0]=args[0].trim();
			args[1]=args[1].trim();
			if(args[0].startsWith(ctrlString)) {
				lArg=subQueries.elementAt(Integer.valueOf((args[0].substring(args[0].indexOf("ctrlString")+ctrlString.length()+1)))-1);
			}
			else
				lArg=HelperFunctions.convertToTable(myTable, args[0]);
			if(args[1].startsWith(ctrlString)) {
				rArg=subQueries.elementAt(Integer.valueOf((args[1].substring(args[1].indexOf("ctrlString")+ctrlString.length()+1)))-1);
			}
			else
				rArg=HelperFunctions.convertToTable(myTable, args[1]);
			return CompareOps.lessEqual(lArg, rArg);
		}
		else if(command.contains(">=")) {
			args=command.split(">=");
			args[0]=args[0].trim();
			args[1]=args[1].trim();
			if(args[0].startsWith(ctrlString)) {
				lArg=subQueries.elementAt(Integer.valueOf((args[0].substring(args[0].indexOf("ctrlString")+ctrlString.length()+1)))-1);
			}
			else
				lArg=HelperFunctions.convertToTable(myTable, args[0]);
			if(args[1].startsWith(ctrlString)) {
				rArg=subQueries.elementAt(Integer.valueOf((args[1].substring(args[1].indexOf("ctrlString")+ctrlString.length()+1)))-1);
			}
			else
				rArg=HelperFunctions.convertToTable(myTable, args[1]);
			return CompareOps.greaterEqual(lArg, rArg);
		}
		else if(command.contains(">")) {
			args=command.split(">");
			args[0]=args[0].trim();
			args[1]=args[1].trim();
			if(args[0].startsWith(ctrlString)) {
				lArg=subQueries.elementAt(Integer.valueOf((args[0].substring(args[0].indexOf("ctrlString")+ctrlString.length()+1)))-1);
			}
			else {
				lArg=HelperFunctions.convertToTable(myTable, args[0]);
				//System.out.println("LARG CONVERT");
			}
			if(args[1].startsWith(ctrlString)) {
				rArg=subQueries.elementAt(Integer.valueOf((args[1].substring(args[1].indexOf("ctrlString")+ctrlString.length()+1)))-1);
			}
			else
				rArg=HelperFunctions.convertToTable(myTable, args[1]);
//			for(int ctr=0; ctr<lArg.getRowCount(); ++ctr) {
				//System.out.println("whereClass > LArg: " + lArg.getValueAt(ctr, 0));
//			}
//			for(int ctr=0; ctr<rArg.getRowCount(); ++ctr) {
//				System.out.println("whereClass > RArg: " + rArg.getValueAt(ctr, 0));
//			}
			return CompareOps.greaterThan(lArg, rArg);
		}
		else if(command.contains("<")) {
			args=command.split("<");
			args[0]=args[0].trim();
			args[1]=args[1].trim();
			if(args[0].startsWith(ctrlString)) {
				lArg=subQueries.elementAt(Integer.valueOf((args[0].substring(args[0].indexOf("ctrlString")+ctrlString.length()+1)))-1);
			}
			else
				lArg=HelperFunctions.convertToTable(myTable, args[0]);
			if(args[1].startsWith(ctrlString)) {
				rArg=subQueries.elementAt(Integer.valueOf((args[1].substring(args[1].indexOf("ctrlString")+ctrlString.length()+1)))-1);
			}
			else
				rArg=HelperFunctions.convertToTable(myTable, args[1]);
			return CompareOps.lessThan(lArg, rArg);
		}
		else if(command.contains("=")) {
			args=command.split("=");
			args[0]=args[0].trim();
			args[1]=args[1].trim();
			if(args[0].startsWith(ctrlString)) {
				lArg=subQueries.elementAt(Integer.valueOf((args[0].substring(args[0].indexOf("ctrlString")+ctrlString.length()+1)))-1);
			}
			else
				lArg=HelperFunctions.convertToTable(myTable, args[0]);
			if(args[1].startsWith(ctrlString)) {
				rArg=subQueries.elementAt(Integer.valueOf((args[1].substring(args[1].indexOf("ctrlString")+ctrlString.length()+1)))-1);
			}
			else
				rArg=HelperFunctions.convertToTable(myTable, args[1]);
			return CompareOps.equals(lArg, rArg);
		}
		//Should be unreachable
		return myTable;
	}
	//gets ...(...)...
	//Returns (...)
	private String getSubQuery(String command) {
		int oParen=command.indexOf('(');
		int parenIndex=oParen+1;
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
			}
		}
		return command.substring(oParen, parenIndex);
	}
}