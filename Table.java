
import java.util.Vector;

public class Table {

    Vector<Vector<Object>> database;
    Vector<String> colTypes;

    Table() {
        database = new Vector<Vector<Object>>();
    }
    Table(int numRows, int numColumns) {
        database = new Vector<Vector<Object>>();
    }

    Object getValueAt(int row, int column) {
        return database.get(row).get(column);
    }

    void setValueAt(Object value, int row, int column) {
        database.get(row).set(row, value);
    }

    int getRowCount() {
        return database.size();
    }

    int getColumnCount() {
        return database.get(0).size();
    }
}
