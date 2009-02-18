import javax.swing.JTable;

class helperFunctions {
	//Converts a given variable into a JTable
	public static JTable convertToTable(JTable table, String variable) {
		variable=variable.trim();
		//System.out.println("Parsing: " + variable);
		drewOps drewOps=new drewOps();
		JTable retTable;
		int curCol=0;
		for(curCol=0; curCol<table.getColumnCount() && table.getColumnName(curCol)!=variable; ++curCol);
//		//System.out.println(table.getColumnName(0));
//		if(curCol!=table.getColumnCount()) {
		if(variable.equals("A")) {
		//System.out.println("Parsing3: " + variable);
			//System.out.println("Converted to table");
		    retTable=new JTable(table.getRowCount(), 1);
			for(int curRow=0; curRow<table.getRowCount(); ++curRow)
				retTable.setValueAt(table.getValueAt(curRow,0), curRow, 0);
			//System.out.println("Converted to table success");
		}
		else {
			//System.out.println("Parsing4: " + variable);
			if(variable.contains(".")) {
				//System.out.println("Double: " + variable);
				retTable=createTable(table.getRowCount(), Double.parseDouble(variable));
			}
			else if(variable==String.valueOf(drewOps.TRUE))
				retTable=createTable(table.getRowCount(), drewOps.TRUE);				
			else if(variable==String.valueOf(drewOps.FALSE))
				retTable=createTable(table.getRowCount(), drewOps.FALSE);				
			else try {
				retTable=createTable(table.getRowCount(), Integer.parseInt(variable));
			} catch(NumberFormatException e) {
				retTable=createTable(table.getRowCount(), variable);
			}
		}
		return retTable;
	}
	//creates a JTable with initial value variable, cols=1, and the given number of rows
	public static JTable createTable(int rows, Object variable) {
	    JTable retTable=new JTable(rows, 1);
		for(int curRow=0; curRow<rows; ++curRow)
			retTable.setValueAt(variable, curRow, 0);
		return retTable;
	}
}