//Gabriel Copley
//Revised 2/20/09
//Converts variables into tables

class HelperFunctions {
	//Converts a given variable into a table
	public static Table convertToTable(Table myTable, String variable) {
		for(int ctr=0; ctr<myTable.getColumnCount(); ++ctr) {
			System.out.println("MYTABLE: " + myTable.getValueAt(ctr, 0));
		}
		variable=variable.trim();
		System.out.println("helperFuncs Parsing: " + variable);
		Table retTable;
		int curCol=0;
		//Attempt lookup of column
		for(curCol=0; curCol<myTable.colNames.size() && !myTable.colNames.get(curCol).equals(variable); ++curCol);
		if(curCol!=myTable.colNames.size()) {
			System.out.println("helperFuncs Parsing3: " + variable);
			System.out.println("helperFuncs Converted to table");
		    retTable=new Table(myTable.getRowCount(), 1);
			for(int curRow=0; curRow<myTable.getRowCount(); ++curRow)
				retTable.setValueAt(myTable.getValueAt(curRow,0), curRow, 0);
			retTable.colTypes.add(myTable.colTypes.get(curCol));
			System.out.println("helperFuncs Converted to table success");
		}
		else {
			try {
			System.out.println("helperFuncs Parsing4: " + variable);
			if(variable.contains("\"") || variable.contains("\'")) {
				//Strip Quotes
				if(variable.contains("\""))
					variable=variable.substring(variable.indexOf("\"")+1, variable.lastIndexOf("\""));
				else
					variable=variable.substring(variable.indexOf("\'")+1, variable.lastIndexOf("\'"));
				//Return as string
				retTable=createTable(myTable.getRowCount(), variable);
				retTable.colTypes.add(CompareOps.STRING);
			}
			else if(variable.contains(".")) {
				System.out.println("Double: " + variable);
				retTable=createTable(myTable.getRowCount(), Double.parseDouble(variable));
				retTable.colTypes.add(CompareOps.DOUBLE);
			}
			else if(variable==String.valueOf(CompareOps.TRUE)) {
				retTable=createTable(myTable.getRowCount(), CompareOps.TRUE);
				retTable.colTypes.add("Boolean");
			}
			else if(variable==String.valueOf(CompareOps.FALSE)) {
				retTable=createTable(myTable.getRowCount(), CompareOps.FALSE);
				retTable.colTypes.add("Boolean");
			}
			else {
				retTable=createTable(myTable.getRowCount(), Integer.parseInt(variable));
				retTable.colTypes.add(CompareOps.INTEGER);
			}
			} catch(NumberFormatException e) {
				System.err.println("ERROR, Unknown type?");
				//Give up, hope a string works!
				retTable=createTable(myTable.getRowCount(), variable);
				retTable.colTypes.add("String");
			}
		}
		return retTable;
	}
	//creates a table with initial value variable, cols=1, and the given number of rows
	public static Table createTable(int rows, Object variable) {
	    Table retTable=new Table(rows, 1);
		for(int curRow=0; curRow<rows; ++curRow)
			retTable.setValueAt(variable, curRow, 0);
		return retTable;
	}
}