//Gabriel Copley
//Last Revised 2/18/09
//Performs operations that involve a subQuery
//USES table, tableOps

class SubQueryOperators {
	//Returns true if subquery contains at least one row
	public static Table existsOp(Table myTable, String query) {
		Table subQueryTable=TableOps.select(query.substring(query.indexOf("SELECT")+6, query.lastIndexOf(')')));
		Table retTable;
		//Create false table
		if(subQueryTable.getRowCount()>0)
			retTable=HelperFunctions.createTable("EXISTS"+query,CompareOps.BOOLEAN, myTable.getRowCount(), CompareOps.TRUE);
		//Create true table
		else
			retTable=HelperFunctions.createTable("EXISTS"+query,CompareOps.BOOLEAN, myTable.getRowCount(), CompareOps.FALSE);
		return retTable;
	}
	//Returns true for any value such that myTable > min(subQuery) for the > operator (reversed for the < operator etc.) 
	public static Table anyOp(Table myTable, String op, String query) {
		Table subQueryTable=TableOps.select(query.substring(query.indexOf('(')+1, query.lastIndexOf(')')));
		Table retTable;
		double minMaxVal;
		if(op.contains(">")) {
			minMaxVal=SingleValOps.min(subQueryTable, subQueryTable.getColName(0));
			subQueryTable=HelperFunctions.createTable("ANY("+query+")",CompareOps.DOUBLE, myTable.getRowCount(), String.valueOf(minMaxVal));
			//use drew's ops to save code
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
			//equals comparison, match anything on the left with anything on the right for true
			retTable=HelperFunctions.createTable("ANY("+query+")",CompareOps.BOOLEAN, myTable.getRowCount(), CompareOps.FALSE);
			//Create a false table then or it with equals' return value for every single row in subQuery
			for(int ctr=0; ctr<subQueryTable.getRowCount(); ++ctr)
				retTable=CompareOps.or(retTable, CompareOps.equals(myTable, HelperFunctions.createTable("ANY("+query+")",CompareOps.STRING, myTable.getRowCount(), subQueryTable.getValueAt(ctr, 0).toString())));
		}
		return retTable;
	}
	//Returns true for any value such that myTable > max(subQuery) for the > operator (reversed for the < operator etc.) 
	public static Table allOp(Table myTable, String op, String query) {
		Table subQueryTable=TableOps.select(query.substring(query.indexOf('(')+1, query.lastIndexOf(')')));
		Table retTable;
		double minMaxVal;
		if(op.contains(">")) {
			minMaxVal=SingleValOps.max(subQueryTable, subQueryTable.getColName(0));
			subQueryTable=HelperFunctions.createTable("ALL("+query+")",CompareOps.DOUBLE, myTable.getRowCount(), String.valueOf(minMaxVal));
			//use drew's ops to save code
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
				//All values in the subQuery are the same if ctr==subQueryTable at this point
				if(ctr==subQueryTable.getRowCount())
					retTable=CompareOps.equals(myTable, HelperFunctions.createTable("ALL("+query+")",subQueryTable.getColType(0), myTable.getRowCount(), allVal));
			}
		}
		return retTable;
	}
}
