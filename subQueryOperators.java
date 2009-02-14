import javax.swing.JTable;

class subQueryOperators {
	public static JTable existsOp(JTable table, String query) {
		JTable subQueryTable=new JTable();//=select(query);
		JTable retTable=helperFunctions.createTable(table.getRowCount(), "false");
		int tableColCtr=0;
		int tableRowCtr=0;
		int existsColCtr=0;
		int existsRowCtr=0;
		for(existsColCtr=0; existsColCtr<subQueryTable.getColumnCount(); ++existsColCtr) {
			for(tableColCtr=0; tableColCtr<table.getColumnCount(); ++tableColCtr) {
				if(table.getColumnName(tableColCtr)==subQueryTable.getColumnName(existsColCtr)) {
					for(existsRowCtr=0; existsRowCtr<subQueryTable.getRowCount(); ++existsRowCtr) {
						for(tableRowCtr=0; tableRowCtr<retTable.getRowCount(); ++tableRowCtr) {
							if((String) table.getValueAt(tableRowCtr, tableColCtr)==(String) subQueryTable.getValueAt(existsRowCtr, existsColCtr)) {
								retTable.setValueAt("true", tableRowCtr, 0);
							}
						}
					}
				}
			}
		}
		return retTable;
	}
	public static JTable anyOp(JTable table, String op, String query) {
		JTable subQueryTable=new JTable();//=select(query);
		double maxVal=Double.parseDouble((String)subQueryTable.getValueAt(0, 0));
		double nextVal=0;
		JTable retTable=helperFunctions.createTable(table.getRowCount(), "false");
		if(op.contains(">")) {
			for(int ctr=0; ctr<subQueryTable.getRowCount(); ++ctr) {
				nextVal=Double.parseDouble((String)subQueryTable.getValueAt(ctr, 0));
				if(maxVal<nextVal) {
					maxVal=nextVal;
				}
			}
			subQueryTable=helperFunctions.createTable(table.getRowCount(), String.valueOf(maxVal));
//			if(op.contains("="))
//				retTable= >=Operator(table, subQueryTable);
//			else
//				retTable= >Operator(table, subQueryTable);
		}
		else if(op.contains("<")) {
			for(int ctr=0; ctr<subQueryTable.getRowCount(); ++ctr) {
				nextVal=Double.parseDouble((String)subQueryTable.getValueAt(ctr, 0));
				if(maxVal>nextVal) {
					maxVal=nextVal;
				}
			}
			subQueryTable=helperFunctions.createTable(table.getRowCount(), String.valueOf(maxVal));
//			if(op.contains("="))
//				retTable= <=Operator(table, subQueryTable);
//			else
//				retTable= <Operator(table, subQueryTable);
		}
		else {
			for(int ctr=0; ctr<subQueryTable.getColumnCount(); ++ctr) {
//				retTable=orOperator(retTable, =Operator(table, helperFunctions.createTable(table.getRowCount(), (String) subQueryTable.getValueAt(ctr, 0))));
			}
		}
		return retTable;
	}
	public static JTable allOp(JTable table, String op, String query) {
		JTable subQueryTable=new JTable();//=select(query);
		double maxVal=Double.parseDouble((String)subQueryTable.getValueAt(0, 0));
		double nextVal=0;
		JTable retTable=helperFunctions.createTable(table.getRowCount(), "false");
		if(op.contains(">")) {
			for(int ctr=0; ctr<subQueryTable.getRowCount(); ++ctr) {
				nextVal=Double.parseDouble((String)subQueryTable.getValueAt(ctr, 0));
				if(maxVal>nextVal) {
					maxVal=nextVal;
				}
			}
			subQueryTable=helperFunctions.createTable(table.getRowCount(), String.valueOf(maxVal));
//			if(op.contains("="))
//				retTable= >=Operator(table, subQueryTable);
//			else
//				retTable= >Operator(table, subQueryTable);
		}
		else if(op.contains("<")) {
			for(int ctr=0; ctr<subQueryTable.getRowCount(); ++ctr) {
				nextVal=Double.parseDouble((String)subQueryTable.getValueAt(ctr, 0));
				if(maxVal<nextVal) {
					maxVal=nextVal;
				}
			}
			subQueryTable=helperFunctions.createTable(table.getRowCount(), String.valueOf(maxVal));
//			if(op.contains("="))
//				retTable= <=Operator(table, subQueryTable);
//			else
//				retTable= <Operator(table, subQueryTable);
		}
		else {
			for(int ctr=0; ctr<subQueryTable.getColumnCount(); ++ctr) {
//				retTable=orOperator(retTable, =Operator(table, helperFunctions.createTable(table.getRowCount(), (String) subQueryTable.getValueAt(ctr, 0))));
			}
		}
		return retTable;
	}
}
