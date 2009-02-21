//Gabriel Copley
//Last Revised 2/18/09
//Preforms operations that involve a subQuery
//USES table, tableOps

class SubQueryOperators {
	public static Table existsOp(Table myTable, String query) {
		System.out.println("EXISTS");
		Table subQueryTable=TableOps.select(query);
		Table retTable=HelperFunctions.createTable(CompareOps.BOOLEAN, myTable.getRowCount(), CompareOps.FALSE);
		int tableColCtr=0;
		int tableRowCtr=0;
		int existsColCtr=0;
		int existsRowCtr=0;
		for(existsColCtr=0; existsColCtr<subQueryTable.getColumnCount(); ++existsColCtr) {
			for(tableColCtr=0; tableColCtr<myTable.getColumnCount(); ++tableColCtr) {
				if(myTable.getColName(tableColCtr)==subQueryTable.getColName(existsColCtr)) {
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
		//System.out.println("ANY");
		Table subQueryTable=TableOps.select(query);
		Table retTable;
		double minMaxVal;
		if(op.contains(">")) {
			minMaxVal=SingleValOps.min(myTable);
			subQueryTable=HelperFunctions.createTable(CompareOps.DOUBLE, myTable.getRowCount(), String.valueOf(minMaxVal));
			if(op.contains("="))
				retTable=CompareOps.greaterEqual(myTable, subQueryTable);
			else
				retTable=CompareOps.equals(myTable, subQueryTable);
		}
		else if(op.contains("<")) {
			minMaxVal=SingleValOps.max(myTable);
			subQueryTable=HelperFunctions.createTable(CompareOps.DOUBLE, myTable.getRowCount(), String.valueOf(minMaxVal));
			if(op.contains("="))
				retTable=CompareOps.lessEqual(myTable, subQueryTable);
			else
				retTable=CompareOps.lessThan(myTable, subQueryTable);
		}
		else {
			retTable=HelperFunctions.createTable(CompareOps.BOOLEAN, myTable.getRowCount(), CompareOps.FALSE);
			for(int ctr=0; ctr<subQueryTable.getRowCount(); ++ctr) {
				retTable=CompareOps.or(retTable, CompareOps.equals(myTable, HelperFunctions.createTable(CompareOps.STRING, myTable.getRowCount(), (String) subQueryTable.getValueAt(ctr, 0))));
			}
		}
		return retTable;
	}
	public static Table allOp(Table myTable, String op, String query) {
		//System.out.println("ALL");
		Table subQueryTable=TableOps.select(query);
		Table retTable;
		double minMaxVal;
		if(op.contains(">")) {
			minMaxVal=SingleValOps.max(myTable);
			subQueryTable=HelperFunctions.createTable(CompareOps.DOUBLE, myTable.getRowCount(), String.valueOf(minMaxVal));
			if(op.contains("="))
				retTable=CompareOps.greaterEqual(myTable, subQueryTable);
			else
				retTable=CompareOps.equals(myTable, subQueryTable);
		}
		else if(op.contains("<")) {
			minMaxVal=SingleValOps.min(myTable);
			subQueryTable=HelperFunctions.createTable(CompareOps.DOUBLE, myTable.getRowCount(), String.valueOf(minMaxVal));
			if(op.contains("="))
				retTable=CompareOps.lessEqual(myTable, subQueryTable);
			else
				retTable=CompareOps.lessThan(myTable, subQueryTable);
		}
		else {
			retTable=HelperFunctions.createTable(CompareOps.BOOLEAN, myTable.getRowCount(), CompareOps.FALSE);
			if(subQueryTable.getRowCount()!=0) {
				int ctr;
				Object allVal=subQueryTable.getValueAt(0, 0);
				//everything will be false if == with two different variables
				for(ctr=0; allVal==subQueryTable.getValueAt(ctr, 0) && ctr<subQueryTable.getRowCount(); ++ctr);
				if(ctr==subQueryTable.getRowCount())
					retTable=CompareOps.equals(myTable, HelperFunctions.createTable(subQueryTable.colType(0), myTable.getRowCount(), allVal));
			}
		}
		return retTable;
	}
}
