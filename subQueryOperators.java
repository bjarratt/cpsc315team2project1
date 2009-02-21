//Gabriel Copley
//Last Revised 2/18/09
//Preforms operations that involve a subQuery
//USES table, tableOps

class SubQueryOperators {
	public static Table existsOp(Table myTable, String query) {
		TableOps tableOps=new TableOps();
		System.out.println("EXISTS");
		Table subQueryTable=tableOps.select(query);
		Table retTable=HelperFunctions.createTable(myTable.getRowCount(), CompareOps.FALSE);
		int tableColCtr=0;
		int tableRowCtr=0;
		int existsColCtr=0;
		int existsRowCtr=0;
		for(existsColCtr=0; existsColCtr<subQueryTable.getColumnCount(); ++existsColCtr) {
			for(tableColCtr=0; tableColCtr<myTable.getColumnCount(); ++tableColCtr) {
				if(myTable.colNames.get(tableColCtr)==subQueryTable.colNames.get(existsColCtr)) {
					for(existsRowCtr=0; existsRowCtr<subQueryTable.getRowCount(); ++existsRowCtr) {
						for(tableRowCtr=0; tableRowCtr<retTable.getRowCount(); ++tableRowCtr) {
							if((String) myTable.getValueAt(tableRowCtr, tableColCtr)==(String) subQueryTable.getValueAt(existsRowCtr, existsColCtr)) {
								retTable.setValueAt(CompareOps.TRUE, tableRowCtr, 0);
							}
						}
					}
				}
			}
		}
		return retTable;
	}
	public static Table anyOp(Table myTable, String op, String query) {
		TableOps tableOps=new TableOps();
		//System.out.println("ANY");
		Table subQueryTable=tableOps.select(query);
		double maxVal=Double.parseDouble((String)subQueryTable.getValueAt(0, 0));
		double nextVal=0;
		Table retTable=HelperFunctions.createTable(myTable.getRowCount(), CompareOps.FALSE);
		if(op.contains(">")) {
			for(int ctr=0; ctr<subQueryTable.getRowCount(); ++ctr) {
				nextVal=Double.parseDouble((String)subQueryTable.getValueAt(ctr, 0));
				if(maxVal<nextVal) {
					maxVal=nextVal;
				}
			}
			subQueryTable=HelperFunctions.createTable(myTable.getRowCount(), String.valueOf(maxVal));
			if(op.contains("="))
				retTable=CompareOps.greaterEqual(myTable, subQueryTable);
			else
				retTable=CompareOps.equals(myTable, subQueryTable);
		}
		else if(op.contains("<")) {
			for(int ctr=0; ctr<subQueryTable.getRowCount(); ++ctr) {
				nextVal=Double.parseDouble((String)subQueryTable.getValueAt(ctr, 0));
				if(maxVal>nextVal) {
					maxVal=nextVal;
				}
			}
			subQueryTable=HelperFunctions.createTable(myTable.getRowCount(), String.valueOf(maxVal));
			if(op.contains("="))
				retTable=CompareOps.lessEqual(myTable, subQueryTable);
			else
				retTable=CompareOps.lessThan(myTable, subQueryTable);
		}
		else {
			for(int ctr=0; ctr<subQueryTable.getColumnCount(); ++ctr) {
				retTable=CompareOps.or(retTable, CompareOps.equals(myTable, HelperFunctions.createTable(myTable.getRowCount(), (String) subQueryTable.getValueAt(ctr, 0))));
			}
		}
		return retTable;
	}
	public static Table allOp(Table myTable, String op, String query) {
		TableOps tableOps=new TableOps();
		//System.out.println("ALL");
		Table subQueryTable=tableOps.select(query);
		double maxVal=Double.parseDouble((String)subQueryTable.getValueAt(0, 0));
		double nextVal=0;
		Table retTable=HelperFunctions.createTable(myTable.getRowCount(), CompareOps.FALSE);
		if(op.contains(">")) {
			for(int ctr=0; ctr<subQueryTable.getRowCount(); ++ctr) {
				nextVal=Double.parseDouble((String)subQueryTable.getValueAt(ctr, 0));
				if(maxVal>nextVal) {
					maxVal=nextVal;
				}
			}
			subQueryTable=HelperFunctions.createTable(myTable.getRowCount(), String.valueOf(maxVal));
			if(op.contains("="))
				retTable=CompareOps.greaterEqual(myTable, subQueryTable);
			else
				retTable=CompareOps.equals(myTable, subQueryTable);
		}
		else if(op.contains("<")) {
			for(int ctr=0; ctr<subQueryTable.getRowCount(); ++ctr) {
				nextVal=Double.parseDouble((String)subQueryTable.getValueAt(ctr, 0));
				if(maxVal<nextVal) {
					maxVal=nextVal;
				}
			}
			subQueryTable=HelperFunctions.createTable(myTable.getRowCount(), String.valueOf(maxVal));
			if(op.contains("="))
				retTable=CompareOps.lessEqual(myTable, subQueryTable);
			else
				retTable=CompareOps.lessThan(myTable, subQueryTable);
		}
		else {
			for(int ctr=0; ctr<subQueryTable.getColumnCount(); ++ctr) {
				retTable=CompareOps.or(retTable, CompareOps.equals(myTable, HelperFunctions.createTable(myTable.getRowCount(), (String) subQueryTable.getValueAt(ctr, 0))));
			}
		}
		return retTable;
	}
}
