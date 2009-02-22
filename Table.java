import java.util.Vector;

public class Table {

    private Vector<Vector<Object>> table;
    private Vector<String> colTypes;
    private Vector<String> colNames;
    private Vector<Object> defaultValues;
    private String tableName;
    private int numRows = 0;
    private int numCols = 0;

    Table(String name) {
        tableName = name;
        table = new Vector<Vector<Object>>();
    }

    Table(String name, int rows, int columns) {
        tableName = name;
        numRows = rows;
        numCols = columns;
        table = new Vector<Vector<Object>>(rows);
        for(int i = 0; i< numRows; i++) {
            table.get(i).setSize(columns);
        }
    }

    Table(int rows, int columns) {
        tableName = "Unnamed";
        numRows = rows;
        numCols = columns;
        table = new Vector<Vector<Object>>();
        for(int i=0; i<rows; ++i) {
                table.add(new Vector<Object>());
        }
        for(int i = 0; i< numRows; ++i) {
            table.get(i).setSize(columns);
        }
    }

    // This constructor SHOULD be the one used
    Table(String name, Vector<String> columnNames, Vector<String> columnTypes) {
        tableName = name;
        numCols = columnNames.size();
        colNames = columnNames;
        colTypes = columnTypes;
        defaultValues = new Vector<Object>(numCols);

        table = new Vector<Vector<Object>>();
    }
    
    Table(String name, Vector<String> columnNames, Vector<String> columnTypes, Vector<Object> defaultVals) {
        tableName = name;
        numCols = columnNames.size();
        colNames = columnNames;
        colTypes = columnTypes;
        defaultValues = defaultVals;

        table = new Vector<Vector<Object>>();
    }

    Object getValueAt(int row, int column) {
        return table.get(row).get(column);
    }

    void setValueAt(Object value, int row, int column) {
        table.get(row).set(column, value);
    }

    int getRowCount() {
        return numRows;
    }

    int getColumnCount() {
        return numCols;
    }

    int colWithName(String name) {
    	for (int i=0; i< colNames.size(); i++) {
    		if (colNames.get(i).equals(name))
    			return i;
    	}
    	// No column exists with this name. Throw an error value.
    	return -1;
    }
    
    void setColName(int index, String name) {
        colNames.set(index, name);
    }

    String getColName(int index) {
        return colNames.get(index);
    }

    String colType(int index) {
        return colTypes.get(index);
    }

    void addRow(Vector<Object> stuff) {
        table.add(new Vector<Object>(stuff));
        numRows++;
    }

    // Use above if possible. This function adds
    void addRow() {
    	Vector<Object> toAdd = new Vector<Object>(numCols);
    	for (int i=0; i<numCols; ++i)
    		toAdd.add(defaultValues.get(i));
    	table.add(toAdd);
    }
    
    void removeRow(int index) {
        table.remove(index);
        numRows--;
    }

    void removeColumn(int index) {
        for(int i=0; i<table.size(); i++) {
            table.get(i).remove(index);
        }
        colNames.remove(index);
        colTypes.remove(index);
        numCols--;
    }

    void addColumn(int index) {
        for(int i=0; i<table.size(); i++) {
            table.get(i).add(index);
        }
        colNames.add(index, colNames.elementAt(index));
        colTypes.add(index, colNames.elementAt(index));
        numCols++;
    }

    void name(String newName) {
        tableName = newName;
    }

    String name() {
        return tableName;
    }
}