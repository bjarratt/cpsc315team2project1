import javax.swing.*;
import java.util.*;

public class Table extends JTable {
     
	Vector<String> colTypes;
    Vector<String> colNames;
    Vector<Object> columns;
    Vector<Object> rows;
    
    
    public Object getRow(int rowindex){
		int i = rowindex;
		return rows.get(i);
	}
    
    public Object getColumn(int colIndex){
    	int i = colIndex;
    	return columns.get(i);
    }
    
    public Object addRow(int index, Object e){
    	int i = index;
    	rows.add(i, e);
    	return rows;
    }
    
    public Object addColumn(int index, Object e){
    	int i = index;
    	columns.add(i, e);
    	return columns;
    }
    
    public Object deleteRow(int index){
    	int i = index;
    	rows.remove(i);
    	return rows;
    }
    
    public Object deleteColumn(int index){
    	int i = index;
    	columns.remove(i);
    	return columns;
    }
}
