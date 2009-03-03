import java.util.Vector;

//Gabriel Copley
//Revised 2/20/09
//Revised by Drew 2/24/09 fixed convertToTable column select
//Modified 3/02/09 added comments
//Converts variables into tables

class HelperFunctions {
	//Converts a given variable into a table
	public static Table convertToTable(Table myTable, String variable) {
		variable=variable.trim();
		Table retTable;
		int curCol=myTable.colWithName(variable);
		//Attempt lookup of column
		if(curCol!=-1) {
			Vector<String> colName=new Vector<String>();
			Vector<String> colType=new Vector<String>();
			colName.add(variable);
			colType.add(myTable.getColType(curCol));
			Vector<Object> addRow=new Vector<Object>();
			addRow.add(null);
			//Create table
		    retTable=new Table(myTable.getName() + " variable", colName, colType);
			for(int curRow=0; curRow<myTable.getRowCount(); ++curRow) {
				addRow.setElementAt(myTable.getValueAt(curRow,curCol), 0);
				retTable.addRow(addRow);
			}
		}
		else {
			try {
				String objectType;
				Object typedObject;
				//Strings must be enclosed by quotes
				if(variable.contains("\"") || variable.contains("\'")) {
					if(variable.contains("\""))
						variable=variable.substring(variable.indexOf("\"")+1, variable.lastIndexOf("\""));
					else
						variable=variable.substring(variable.indexOf("\'")+1, variable.lastIndexOf("\'"));
					objectType=CompareOps.STRING;
					typedObject=variable;
				}
				//already tried string and attribute by this point, . should be enough
				else if(variable.contains(".")) {
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
				//Give up, hope a string works!
				retTable=createTable(variable, CompareOps.STRING, myTable.getRowCount(),(String) variable);
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