import java.util.Stack;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

class whereClass {
	final String ctrlString="####";
	Vector<JTable> subQueries;
	JTable table;

	public static JTable where(JTable argTable, String commands) {
		drewOps drewOps=new drewOps();
		//System.out.println("Where Entry " + commands);
		whereClass whereObj=new whereClass(argTable);
		int selectIndex;
		int subQueryIndex;
		int oParenIndex;
		String[] args;
		String subQuery;
		//Handle any sub queries
		while((selectIndex=commands.indexOf("(SELECT"))!=-1) {
			if((subQueryIndex=commands.substring(0, selectIndex).indexOf("EXISTS"))!=-1) {
				subQuery=whereObj.getSubQuery(commands.substring(subQueryIndex+6));
				whereObj.subQueries.add(subQueryOperators.existsOp(whereObj.table, subQuery));
				commands=commands.substring(0,subQueryIndex) +
				whereObj.ctrlString + whereObj.subQueries.size() +
				commands.substring(subQueryIndex+6+subQuery.length(), commands.length());
			}
			else if((subQueryIndex=commands.substring(0, selectIndex).indexOf("IN"))!=-1) {
				subQuery=whereObj.getSubQuery(commands.substring(subQueryIndex+2));
				oParenIndex=commands.substring(0, subQueryIndex).lastIndexOf('(');
//				whereObj.subQueries.add(in(helperFunctions.convertToTable(whereObj.table, commands.substring(oParenIndex+1, subQueryIndex-1)), subQuery));
				commands=commands.substring(0,subQueryIndex) +
				whereObj.ctrlString + whereObj.subQueries.size() +
				commands.substring(subQueryIndex+2+subQuery.length(), commands.length());
			}
			else if((subQueryIndex=commands.substring(0, selectIndex).indexOf("ANY"))!=-1) {
				subQuery=whereObj.getSubQuery(commands.substring(subQueryIndex+3));
				oParenIndex=commands.substring(0, subQueryIndex).lastIndexOf('(');
				args=commands.substring(oParenIndex+1, subQueryIndex-1).split(" ");
				whereObj.subQueries.add(subQueryOperators.anyOp(helperFunctions.convertToTable(whereObj.table, args[0].trim()), args[1].trim(), subQuery));
				commands=commands.substring(0,oParenIndex) +
				whereObj.ctrlString + whereObj.subQueries.size() +
				commands.substring(subQueryIndex+3+subQuery.length(), commands.length());
			}
			else if((subQueryIndex=commands.substring(0, selectIndex).indexOf("ALL"))!=-1) {
				subQuery=whereObj.getSubQuery(commands.substring(subQueryIndex+3));
				oParenIndex=commands.substring(0, subQueryIndex).lastIndexOf('(');
				args=commands.substring(oParenIndex+1, subQueryIndex-1).split(" ");
				whereObj.subQueries.add(subQueryOperators.allOp(helperFunctions.convertToTable(whereObj.table, args[0].trim()), args[1].trim(), subQuery));
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
			while(openParens.size()!=0) {
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
		DefaultTableModel model =(DefaultTableModel)whereObj.table.getModel();
		JTable results=whereObj.runOperator(commands);
		for(int curRow=results.getRowCount()-1; curRow>0; --curRow) {
			if((String)results.getValueAt(curRow, 0)==drewOps.FALSE)
				model.removeRow(curRow);
		}
		return whereObj.table;
	}
	//Makes subQueries, table "global"
	private whereClass(JTable argTable) {
		subQueries=new Vector<JTable>();
		table=argTable;
	}
	//Runs the given command
	private JTable runOperator(String command) {
		drewOps drewOps=new drewOps();
		//System.out.println("Run " + command);
		String[] args;
		JTable lArg;
		JTable rArg;
		if(command.contains("AND")) {
			args=command.split(" AND ");
			if(args[0].startsWith(ctrlString)) {
				//System.out.println(args[0].indexOf("ctrlString")+ctrlString.length()+1);
				//System.out.println(Integer.valueOf((args[0].substring(args[0].indexOf("ctrlString")+ctrlString.length()+1))));
				lArg=subQueries.elementAt(Integer.valueOf((args[0].substring(args[0].indexOf("ctrlString")+ctrlString.length()+1)))-1);
			}
			else
				lArg=helperFunctions.convertToTable(table, args[0].trim());
			if(args[1].startsWith(ctrlString)) {
				rArg=subQueries.elementAt(Integer.valueOf((args[1].substring(args[1].indexOf("ctrlString")+ctrlString.length()+1))));
			}
			else
				rArg=helperFunctions.convertToTable(table, args[1].trim());
			for(int ctr=0; ctr<lArg.getRowCount(); ++ctr) {
				//System.out.println("AND" + lArg.getValueAt(ctr, 0));
			}
			return(drewOps.and(rArg, lArg));
		}
		else if(command.contains("OR")) {
			args=command.split(" OR ");
			if(args[0].startsWith(ctrlString)) {
				lArg=subQueries.elementAt(Integer.valueOf((args[0].substring(args[0].indexOf("ctrlString")+ctrlString.length()+1)))-1);
			}
			else
				lArg=helperFunctions.convertToTable(table, args[0].trim());
			if(args[1].startsWith(ctrlString)) {
				rArg=subQueries.elementAt(Integer.valueOf((args[1].substring(args[1].indexOf("ctrlString")+ctrlString.length()+1))));
			}
			else
				rArg=helperFunctions.convertToTable(table, args[1].trim());
			return drewOps.or(rArg, lArg);
		}
		else if(command.startsWith("NOT")) {
			args=command.split(" NOT ");
			if(args[1].startsWith(ctrlString)) {
				rArg=subQueries.elementAt(Integer.valueOf((args[1].substring(args[1].indexOf("ctrlString")+ctrlString.length()+1))));
			}
			else
				rArg=helperFunctions.convertToTable(table, args[1].trim());
			return drewOps.not(rArg);
		}
		else if(command.contains("!=")) {
			args=command.split(" != ");
			if(args[0].startsWith(ctrlString)) {
				lArg=subQueries.elementAt(Integer.valueOf((args[0].substring(args[0].indexOf("ctrlString")+ctrlString.length()+1)))-1);
			}
			else
				lArg=helperFunctions.convertToTable(table, args[0].trim());
			if(args[1].startsWith(ctrlString)) {
				rArg=subQueries.elementAt(Integer.valueOf((args[1].substring(args[1].indexOf("ctrlString")+ctrlString.length()+1))));
			}
			else
				rArg=helperFunctions.convertToTable(table, args[1].trim());
			return drewOps.notEqual(rArg, lArg);
		}
		else if(command.contains("<=")) {
			args=command.split(" <= ");
			if(args[0].startsWith(ctrlString)) {
				lArg=subQueries.elementAt(Integer.valueOf((args[0].substring(args[0].indexOf("ctrlString")+ctrlString.length()+1)))-1);
			}
			else
				lArg=helperFunctions.convertToTable(table, args[0].trim());
			if(args[1].startsWith(ctrlString)) {
				rArg=subQueries.elementAt(Integer.valueOf((args[1].substring(args[1].indexOf("ctrlString")+ctrlString.length()+1))));
			}
			else
				rArg=helperFunctions.convertToTable(table, args[1].trim());
			return drewOps.lessEqual(rArg, lArg);
		}
		else if(command.contains(">=")) {
			args=command.split(" >= ");
			if(args[0].startsWith(ctrlString)) {
				lArg=subQueries.elementAt(Integer.valueOf((args[0].substring(args[0].indexOf("ctrlString")+ctrlString.length()+1)))-1);
			}
			else
				lArg=helperFunctions.convertToTable(table, args[0].trim());
			if(args[1].startsWith(ctrlString)) {
				rArg=subQueries.elementAt(Integer.valueOf((args[1].substring(args[1].indexOf("ctrlString")+ctrlString.length()+1))));
			}
			else
				rArg=helperFunctions.convertToTable(table, args[1].trim());
			return drewOps.greaterEqual(rArg, lArg);
		}
		else if(command.contains(">")) {
			//System.out.println("Run2 " + command);
			args=command.split(" > ");
			if(args[0].startsWith(ctrlString)) {
				lArg=subQueries.elementAt(Integer.valueOf((args[0].substring(args[0].indexOf("ctrlString")+ctrlString.length()+1)))-1);
			}
			else
				lArg=helperFunctions.convertToTable(table, args[0].trim());
			if(args[1].startsWith(ctrlString)) {
				rArg=subQueries.elementAt(Integer.valueOf((args[1].substring(args[1].indexOf("ctrlString")+ctrlString.length()+1))));
			}
			else
				rArg=helperFunctions.convertToTable(table, args[1].trim());
			return drewOps.greaterThan(rArg, lArg);
		}
		else if(command.contains("<")) {
			args=command.split(" < ");
			if(args[0].startsWith(ctrlString)) {
				lArg=subQueries.elementAt(Integer.valueOf((args[0].substring(args[0].indexOf("ctrlString")+ctrlString.length()+1)))-1);
			}
			else
				lArg=helperFunctions.convertToTable(table, args[0].trim());
			if(args[1].startsWith(ctrlString)) {
				rArg=subQueries.elementAt(Integer.valueOf((args[1].substring(args[1].indexOf("ctrlString")+ctrlString.length()+1)))-1);
			}
			else
				rArg=helperFunctions.convertToTable(table, args[1].trim());
			return drewOps.lessThan(rArg, lArg);
		}
		else if(command.contains("=")) {
			args=command.split(" = ");
			if(args[0].startsWith(ctrlString)) {
				lArg=subQueries.elementAt(Integer.valueOf((args[0].substring(args[0].indexOf("ctrlString")+ctrlString.length()+1)))-1);
			}
			else
				lArg=helperFunctions.convertToTable(table, args[0].trim());
			if(args[1].startsWith(ctrlString)) {
				rArg=subQueries.elementAt(Integer.valueOf((args[1].substring(args[1].indexOf("ctrlString")+ctrlString.length()+1)))-1);
			}
			else
				rArg=helperFunctions.convertToTable(table, args[1].trim());
			return drewOps.equals(rArg, lArg);
		}
		//Should be unreachable
		return table;
	}
	//gets (...).....
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