import javax.swing.JTable;

class helperFunctions {
	//Converts a given variable into a JTable
	public static JTable convertToTable(JTable table, String variable) {
		JTable retTable;
		int curCol=0;
		for(curCol=0; curCol<table.getColumnCount() && table.getColumnName(curCol)!=variable; ++curCol);
		if(curCol!=table.getColumnCount()) {
		    retTable=new JTable(table.getRowCount(), 1);
			for(int curRow=0; curRow<table.getRowCount(); ++curRow)
				retTable.setValueAt(table.getValueAt(curRow,curCol), curRow, 0);
		}
		else {
			retTable=createTable(table.getRowCount(), variable);
		}
		return retTable;
	}
	//creates a JTable with initial value variable, cols=1, and the given number of rows
	public static JTable createTable(int rows, String variable) {
	    JTable retTable=new JTable(rows, 1);
		for(int curRow=0; curRow<rows; ++curRow)
			retTable.setValueAt(variable, curRow, 0);
		return retTable;
	}
}