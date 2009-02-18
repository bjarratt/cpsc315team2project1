

class subQueryOperators {
	public static table existsOp(table myTable, String query) {
		compareOps drewOps=new compareOps();
		tableOps tableOps=new tableOps();
		System.out.println("EXISTS");
		table subQueryTable=tableOps.select(query);
		table retTable=helperFunctions.createTable(myTable.getRowCount(), drewOps.FALSE);
		int tableColCtr=0;
		int tableRowCtr=0;
		int existsColCtr=0;
		int existsRowCtr=0;
		for(existsColCtr=0; existsColCtr<subQueryTable.getColumnCount(); ++existsColCtr) {
			for(tableColCtr=0; tableColCtr<myTable.getColumnCount(); ++tableColCtr) {
				if(myTable.getColumnName(tableColCtr)==subQueryTable.getColumnName(existsColCtr)) {
					for(existsRowCtr=0; existsRowCtr<subQueryTable.getRowCount(); ++existsRowCtr) {
						for(tableRowCtr=0; tableRowCtr<retTable.getRowCount(); ++tableRowCtr) {
							if((String) myTable.getValueAt(tableRowCtr, tableColCtr)==(String) subQueryTable.getValueAt(existsRowCtr, existsColCtr)) {
								retTable.setValueAt(drewOps.TRUE, tableRowCtr, 0);
							}
						}
					}
				}
			}
		}
		return retTable;
	}
	public static table anyOp(table myTable, String op, String query) {
		compareOps drewOps=new compareOps();
		tableOps tableOps=new tableOps();
		//System.out.println("ANY");
		table subQueryTable=tableOps.select(query);
		double maxVal=Double.parseDouble((String)subQueryTable.getValueAt(0, 0));
		double nextVal=0;
		table retTable=helperFunctions.createTable(myTable.getRowCount(), drewOps.FALSE);
		if(op.contains(">")) {
			for(int ctr=0; ctr<subQueryTable.getRowCount(); ++ctr) {
				nextVal=Double.parseDouble((String)subQueryTable.getValueAt(ctr, 0));
				if(maxVal<nextVal) {
					maxVal=nextVal;
				}
			}
			subQueryTable=helperFunctions.createTable(myTable.getRowCount(), String.valueOf(maxVal));
			if(op.contains("="))
				retTable=drewOps.greaterEqual(myTable, subQueryTable);
			else
				retTable=drewOps.equals(myTable, subQueryTable);
		}
		else if(op.contains("<")) {
			for(int ctr=0; ctr<subQueryTable.getRowCount(); ++ctr) {
				nextVal=Double.parseDouble((String)subQueryTable.getValueAt(ctr, 0));
				if(maxVal>nextVal) {
					maxVal=nextVal;
				}
			}
			subQueryTable=helperFunctions.createTable(myTable.getRowCount(), String.valueOf(maxVal));
			if(op.contains("="))
				retTable=drewOps.lessEqual(myTable, subQueryTable);
			else
				retTable=drewOps.lessThan(myTable, subQueryTable);
		}
		else {
			for(int ctr=0; ctr<subQueryTable.getColumnCount(); ++ctr) {
				retTable=drewOps.or(retTable, drewOps.equals(myTable, helperFunctions.createTable(myTable.getRowCount(), (String) subQueryTable.getValueAt(ctr, 0))));
			}
		}
		return retTable;
	}
	public static table allOp(table myTable, String op, String query) {
		compareOps drewOps=new compareOps();
		tableOps tableOps=new tableOps();
		//System.out.println("ALL");
		table subQueryTable=tableOps.select(query);
		double maxVal=Double.parseDouble((String)subQueryTable.getValueAt(0, 0));
		double nextVal=0;
		table retTable=helperFunctions.createTable(myTable.getRowCount(), drewOps.FALSE);
		if(op.contains(">")) {
			for(int ctr=0; ctr<subQueryTable.getRowCount(); ++ctr) {
				nextVal=Double.parseDouble((String)subQueryTable.getValueAt(ctr, 0));
				if(maxVal>nextVal) {
					maxVal=nextVal;
				}
			}
			subQueryTable=helperFunctions.createTable(myTable.getRowCount(), String.valueOf(maxVal));
			if(op.contains("="))
				retTable=drewOps.greaterEqual(myTable, subQueryTable);
			else
				retTable=drewOps.equals(myTable, subQueryTable);
		}
		else if(op.contains("<")) {
			for(int ctr=0; ctr<subQueryTable.getRowCount(); ++ctr) {
				nextVal=Double.parseDouble((String)subQueryTable.getValueAt(ctr, 0));
				if(maxVal<nextVal) {
					maxVal=nextVal;
				}
			}
			subQueryTable=helperFunctions.createTable(myTable.getRowCount(), String.valueOf(maxVal));
			if(op.contains("="))
				retTable=drewOps.lessEqual(myTable, subQueryTable);
			else
				retTable=drewOps.lessThan(myTable, subQueryTable);
		}
		else {
			for(int ctr=0; ctr<subQueryTable.getColumnCount(); ++ctr) {
				retTable=drewOps.or(retTable, drewOps.equals(myTable, helperFunctions.createTable(myTable.getRowCount(), (String) subQueryTable.getValueAt(ctr, 0))));
			}
		}
		return retTable;
	}
}
