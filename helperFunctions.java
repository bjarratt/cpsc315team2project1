

class helperFunctions {
	//Converts a given variable into a table
	public static table convertToTable(table myTable, String variable) {
		for(int ctr=0; ctr<myTable.getColumnCount(); ++ctr) {
			System.out.println("MYTABLE: " + myTable.getValueAt(ctr, 0));
		}
		variable=variable.trim();
		System.out.println("Parsing: " + variable);
		compareOps drewOps=new compareOps();
		table retTable;
		int curCol=0;
		for(curCol=0; curCol<myTable.getColumnCount() && myTable.getColumnName(curCol)!=variable; ++curCol);
//		System.out.println(table.getColumnName(0));
//		if(curCol!=table.getColumnCount()) {
		if(variable.equals("A")) {
		System.out.println("Parsing3: " + variable);
			System.out.println("Converted to table");
		    retTable=new table(myTable.getRowCount(), 1);
			for(int curRow=0; curRow<myTable.getRowCount(); ++curRow)
				retTable.setValueAt(myTable.getValueAt(curRow,0), curRow, 0);

			for(int ctr=0; ctr<myTable.getRowCount(); ++ctr) {
				System.out.println("LArg: " + myTable.getValueAt(ctr, 0));
			}
			System.out.println("Converted to table success");
		}
		else {
			System.out.println("Parsing4: " + variable);
			if(variable.contains(".")) {
				System.out.println("Double: " + variable);
				retTable=createTable(myTable.getRowCount(), Double.parseDouble(variable));
			}
			else if(variable==String.valueOf(drewOps.TRUE))
				retTable=createTable(myTable.getRowCount(), drewOps.TRUE);				
			else if(variable==String.valueOf(drewOps.FALSE))
				retTable=createTable(myTable.getRowCount(), drewOps.FALSE);				
			else try {
				retTable=createTable(myTable.getRowCount(), Integer.parseInt(variable));
			} catch(NumberFormatException e) {
				retTable=createTable(myTable.getRowCount(), variable);
			}
		}
		return retTable;
	}
	//creates a table with initial value variable, cols=1, and the given number of rows
	public static table createTable(int rows, Object variable) {
	    table retTable=new table(rows, 1);
		for(int curRow=0; curRow<rows; ++curRow)
			retTable.setValueAt(variable, curRow, 0);
		return retTable;
	}
}