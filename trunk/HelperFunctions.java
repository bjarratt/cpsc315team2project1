import java.util.Vector;

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
		for(curCol=0; curCol<myTable.getColumnCount() && !myTable.getColName(curCol).equals(variable); ++curCol);
		System.out.println("!!!!!" + myTable.getColName(curCol));
		if(curCol!=myTable.getColumnCount()) {
			Vector<String> colName=new Vector<String>();
			Vector<String> colType=new Vector<String>();
			colName.add(variable);
			colType.add(myTable.getColType(curCol));
			Vector<Object> addRow=new Vector<Object>();
			addRow.setSize(1);
			System.out.println("helperFuncs Parsing3: " + variable);
			System.out.println("helperFuncs Converted to table");
		    retTable=new Table(myTable.getName() + " variable", colName, colType);
			for(int curRow=0; curRow<myTable.getRowCount(); ++curRow) {
				addRow.setElementAt(myTable.getValueAt(curRow,0), 0);
				retTable.addRow(addRow);
			}
			System.out.println("helperFuncs Converted to table success");
		}
		else {
			try {
				String objectType;
				Object typedObject;
				System.out.println("helperFuncs Parsing4:\'" + variable + "\'");
				if(variable.contains("\"") || variable.contains("\'")) {
					//Strip Quotes
					if(variable.contains("\""))
						variable=variable.substring(variable.indexOf("\"")+1, variable.lastIndexOf("\""));
					else
						variable=variable.substring(variable.indexOf("\'")+1, variable.lastIndexOf("\'"));
					//Return as string
					objectType=CompareOps.STRING;
					typedObject=variable;
				}
				else if(variable.contains(".")) {
					System.out.println("Double: " + variable);
					typedObject=Double.parseDouble(variable);
					objectType=CompareOps.DOUBLE;
				}
				else if(variable.equals(String.valueOf(CompareOps.TRUE))) {
					typedObject=CompareOps.TRUE;
					objectType=CompareOps.BOOLEAN;
				}
				else if(variable.equals(String.valueOf(CompareOps.FALSE))) {
					typedObject=CompareOps.FALSE;
					objectType=CompareOps.BOOLEAN;
				}
				else {
					typedObject=Integer.parseInt(variable);
					objectType=CompareOps.INTEGER;
				}
				retTable=createTable(variable, objectType, myTable.getRowCount(),typedObject);
			} catch(NumberFormatException e) {
				System.err.println("ERROR, Unknown type?");
				//Give up, hope a string works!
				retTable=createTable(variable, CompareOps.STRING, myTable.getRowCount(),variable);
			}
		}
		return retTable;
	}
	//creates a table with initial value variable, cols=1, and the given number of rows
	public static Table createTable(String name, String type, int rows, Object variable) {
		Vector<String> colName=new Vector<String>();
		Vector<String> colType=new Vector<String>();
		Vector<Object> addRow=new Vector<Object>();
		addRow.setSize(1);
		colType.add(type);
		colName.add(name);
	    Table retTable=new Table(String.valueOf(variable), colName, colType);
		for(int curRow=0; curRow<rows; ++curRow) {
			addRow.setElementAt(variable, 0);
			retTable.addRow(addRow);
		}
		return retTable;
	}
}