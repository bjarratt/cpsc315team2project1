//Gabriel Copley
//Last Revised 2/18/09
//Performs operations that involve a subQuery
//USES table, tableOps

class SubQueryOperators {
	public static Table existsOp(Table myTable, String query) {
		Table subQueryTable=TableOps.select(query.substring(query.indexOf("SELECT")+6, query.lastIndexOf(')')));
		Table retTable;
		if(subQueryTable.getRowCount()>0)
			retTable=HelperFunctions.createTable("EXISTS"+query,CompareOps.BOOLEAN, myTable.getRowCount(), CompareOps.TRUE);
		else
			retTable=HelperFunctions.createTable("EXISTS"+query,CompareOps.BOOLEAN, myTable.getRowCount(), CompareOps.FALSE);
		return retTable;
	}
	public static Table anyOp(Table myTable, String op, String query) {
		Table subQueryTable=TableOps.select(query.substring(query.indexOf('(')+1, query.lastIndexOf(')')));
		Table retTable;
		double minMaxVal;
		if(op.contains(">")) {
			minMaxVal=SingleValOps.min(subQueryTable, subQueryTable.getColName(0));
			subQueryTable=HelperFunctions.createTable("ANY("+query+")",CompareOps.DOUBLE, myTable.getRowCount(), String.valueOf(minMaxVal));
			if(op.contains("="))
				retTable=CompareOps.greaterEqual(myTable, subQueryTable);
			else
				retTable=CompareOps.greaterThan(myTable, subQueryTable);
		}
		else if(op.contains("<")) {
			minMaxVal=SingleValOps.max(subQueryTable, subQueryTable.getColName(0));
			subQueryTable=HelperFunctions.createTable("ANY("+query+")",CompareOps.DOUBLE, myTable.getRowCount(), String.valueOf(minMaxVal));
			if(op.contains("="))
				retTable=CompareOps.lessEqual(myTable, subQueryTable);
			else
				retTable=CompareOps.lessThan(myTable, subQueryTable);
		}
		else {
			retTable=HelperFunctions.createTable("ANY("+query+")",CompareOps.BOOLEAN, myTable.getRowCount(), CompareOps.FALSE);
			for(int ctr=0; ctr<subQueryTable.getRowCount(); ++ctr) {
				HelperFunctions.createTable("ANY("+query+")",CompareOps.STRING, myTable.getRowCount(), subQueryTable.getValueAt(ctr, 0).toString());
				retTable=CompareOps.or(retTable, CompareOps.equals(myTable, HelperFunctions.createTable("ANY("+query+")",CompareOps.STRING, myTable.getRowCount(), subQueryTable.getValueAt(ctr, 0).toString())));
			}
		}
		return retTable;
	}
	public static Table allOp(Table myTable, String op, String query) {
		Table subQueryTable=TableOps.select(query.substring(query.indexOf('(')+1, query.lastIndexOf(')')));
		Table retTable;
		double minMaxVal;
		if(op.contains(">")) {
			minMaxVal=SingleValOps.max(subQueryTable, subQueryTable.getColName(0));
			subQueryTable=HelperFunctions.createTable("ALL("+query+")",CompareOps.DOUBLE, myTable.getRowCount(), String.valueOf(minMaxVal));
			if(op.contains("="))
				retTable=CompareOps.greaterEqual(myTable, subQueryTable);
			else
				retTable=CompareOps.greaterThan(myTable, subQueryTable);
		}
		else if(op.contains("<")) {
			minMaxVal=SingleValOps.min(subQueryTable, subQueryTable.getColName(0));
			subQueryTable=HelperFunctions.createTable("ALL("+query+")",CompareOps.DOUBLE, myTable.getRowCount(), String.valueOf(minMaxVal));
			if(op.contains("="))
				retTable=CompareOps.lessEqual(myTable, subQueryTable);
			else
				retTable=CompareOps.lessThan(myTable, subQueryTable);
		}
		else {
			retTable=HelperFunctions.createTable("ALL("+query+")",CompareOps.BOOLEAN, myTable.getRowCount(), CompareOps.FALSE);
			if(subQueryTable.getRowCount()!=0) {
				int ctr;
				Object allVal=subQueryTable.getValueAt(0, 0);
				//everything will be false if == with two different variables
				if(subQueryTable.getColType(0).equals(CompareOps.DOUBLE))
					for(ctr=0; ctr<subQueryTable.getRowCount() && ((Double) allVal).equals(subQueryTable.getValueAt(ctr, 0)); ++ctr);
				else if(subQueryTable.getColType(0).equals(CompareOps.INTEGER))
					for(ctr=0; ((Integer) allVal).equals(subQueryTable.getValueAt(ctr, 0)) && ctr<subQueryTable.getRowCount(); ++ctr);
				else if(subQueryTable.getColType(0).equals(CompareOps.BOOLEAN))
					for(ctr=0; ((String) allVal).equals(subQueryTable.getValueAt(ctr, 0)) && ctr<subQueryTable.getRowCount(); ++ctr);
				else
					for(ctr=0; ((String) allVal).equals(subQueryTable.getValueAt(ctr, 0)) && ctr<subQueryTable.getRowCount(); ++ctr);
				if(ctr==subQueryTable.getRowCount())
					retTable=CompareOps.equals(myTable, HelperFunctions.createTable("ALL("+query+")",subQueryTable.getColType(0), myTable.getRowCount(), allVal));
			}
		}
		return retTable;
	}
}
