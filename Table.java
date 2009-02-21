
import java.util.Vector;

public class Table {

    private Vector<Vector<Object>> database;
    private Vector<String> colTypes;
    private Vector<String> colNames;
    private String tableName;
    private int numRows = 0;
    private int numCols = 0;

    Table(String name) {
        tableName = name;
        database = new Vector<Vector<Object>>();
    }

    Table(String name, int rows, int columns) {
        tableName = name;
        numRows = rows;
        numCols = columns;
        database = new Vector<Vector<Object>>(rows);
        for(int i = 0; i< numRows; i++) {
            database.get(i).setSize(columns);
        }
    }

    Table(int rows, int columns) {
        tableName = "Unnamed";
        numRows = rows;
        numCols = columns;
        database = new Vector<Vector<Object>>(rows);
        for(int i = 0; i< numRows; i++) {
            database.get(i).setSize(columns);
        }
    }

    // This constructor SHOULD be the one used
    Table(String name, Vector<String> columnNames, Vector<String> columnTypes) {
        tableName = name;
        numCols = columnNames.size();
        colNames = columnNames;
        colTypes = columnTypes;

        database = new Vector<Vector<Object>>();
        database.get(0).setSize(numCols);
    }

    Object getValueAt(int row, int column) {
        return database.get(row).get(column);
    }

    void setValueAt(Object value, int row, int column) {
        database.get(row).set(row, value);
    }

    int getRowCount() {
        return numRows;
    }

    int getColumnCount() {
        return numCols;
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
        database.add(stuff);
        numRows++;
    }

    void removeRow(int index) {
        database.remove(index);
        numRows--;
    }

    void removeColumn(int index) {
        for(int i=0; i<database.size(); i++) {
            database.get(i).remove(index);
        }
        numCols--;
    }

    void name(String newName) {
        tableName = newName;
    }

    String name() {
        return tableName;
    }
}
