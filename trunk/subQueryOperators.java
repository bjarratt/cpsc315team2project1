import javax.swing.JTable;

class subQueryOperators {
	public static JTable existsOp(JTable table, String query) {
		drewOps drewOps=new drewOps();
		System.out.println("EXISTS");
		JTable subQueryTable=tableOps.select(query);
		JTable retTable=helperFunctions.createTable(table.getRowCount(), drewOps.FALSE);
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
								retTable.setValueAt(drewOps.TRUE, tableRowCtr, 0);
							}
						}
					}
				}
			}
		}
		return retTable;
	}
	public static JTable anyOp(JTable table, String op, String query) {
		drewOps drewOps=new drewOps();
		//System.out.println("ANY");
		JTable subQueryTable=tableOps.select(query);
		double maxVal=Double.parseDouble((String)subQueryTable.getValueAt(0, 0));
		double nextVal=0;
		JTable retTable=helperFunctions.createTable(table.getRowCount(), drewOps.FALSE);
		if(op.contains(">")) {
			for(int ctr=0; ctr<subQueryTable.getRowCount(); ++ctr) {
				nextVal=Double.parseDouble((String)subQueryTable.getValueAt(ctr, 0));
				if(maxVal<nextVal) {
					maxVal=nextVal;
				}
			}
			subQueryTable=helperFunctions.createTable(table.getRowCount(), String.valueOf(maxVal));
			if(op.contains("="))
				retTable=drewOps.greaterEqual(table, subQueryTable);
			else
				retTable=drewOps.equals(table, subQueryTable);
		}
		else if(op.contains("<")) {
			for(int ctr=0; ctr<subQueryTable.getRowCount(); ++ctr) {
				nextVal=Double.parseDouble((String)subQueryTable.getValueAt(ctr, 0));
				if(maxVal>nextVal) {
					maxVal=nextVal;
				}
			}
			subQueryTable=helperFunctions.createTable(table.getRowCount(), String.valueOf(maxVal));
			if(op.contains("="))
				retTable=drewOps.lessEqual(table, subQueryTable);
			else
				retTable=drewOps.lessThan(table, subQueryTable);
		}
		else {
			for(int ctr=0; ctr<subQueryTable.getColumnCount(); ++ctr) {
				retTable=drewOps.or(retTable, drewOps.equals(table, helperFunctions.createTable(table.getRowCount(), (String) subQueryTable.getValueAt(ctr, 0))));
			}
		}
		return retTable;
	}
	public static JTable allOp(JTable table, String op, String query) {
		drewOps drewOps=new drewOps();
		//System.out.println("ALL");
		JTable subQueryTable=tableOps.select(query);
		double maxVal=Double.parseDouble((String)subQueryTable.getValueAt(0, 0));
		double nextVal=0;
		JTable retTable=helperFunctions.createTable(table.getRowCount(), drewOps.FALSE);
		if(op.contains(">")) {
			for(int ctr=0; ctr<subQueryTable.getRowCount(); ++ctr) {
				nextVal=Double.parseDouble((String)subQueryTable.getValueAt(ctr, 0));
				if(maxVal>nextVal) {
					maxVal=nextVal;
				}
			}
			subQueryTable=helperFunctions.createTable(table.getRowCount(), String.valueOf(maxVal));
			if(op.contains("="))
				retTable=drewOps.greaterEqual(table, subQueryTable);
			else
				retTable=drewOps.equals(table, subQueryTable);
		}
		else if(op.contains("<")) {
			for(int ctr=0; ctr<subQueryTable.getRowCount(); ++ctr) {
				nextVal=Double.parseDouble((String)subQueryTable.getValueAt(ctr, 0));
				if(maxVal<nextVal) {
					maxVal=nextVal;
				}
			}
			subQueryTable=helperFunctions.createTable(table.getRowCount(), String.valueOf(maxVal));
			if(op.contains("="))
				retTable=drewOps.lessEqual(table, subQueryTable);
			else
				retTable=drewOps.lessThan(table, subQueryTable);
		}
		else {
			for(int ctr=0; ctr<subQueryTable.getColumnCount(); ++ctr) {
				retTable=drewOps.or(retTable, drewOps.equals(table, helperFunctions.createTable(table.getRowCount(), (String) subQueryTable.getValueAt(ctr, 0))));
			}
		}
		return retTable;
	}
}
