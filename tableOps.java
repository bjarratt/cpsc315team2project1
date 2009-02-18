import javax.swing.*;
import java.util.*;

public class tableOps {

    Vector<JTable> db = new Vector<JTable>();
    ArrayList<JTable> tables = new ArrayList<JTable>();
    
    public JTable from(Vector<JTable> database){

    	database = db;
    	
    	if(database.size() == 0){
    		System.out.println("Error.");
    	}
    	else if(database.size() == 1){
    		JTable table1 = database.elementAt(0);
    		tables.add(table1);
    	}
    	else if(database.size() == 2){
    		JTable table2 = database.elementAt(1);
    		tables.add(table2);
    	}
        return tables.get(0);
    }

    public JTable select(String column, String table){
    	//this function will return a new table containing the columns
    	//that meet the requirements of where()
    	
    	JTable selectedTable = new JTable();
    	
    	String columnName = "";
    	column = columnName;
    	
    	String tableName = selectedTable.getName();
    	table = tableName;

    	/*for(int i=0; i<tables.size(); i++){
    	 * 		if(tables.get(0).getColumn(i) meets where() conditions){
    	 * 				selectedTable.addColumn(tables.get(0).getColumn(i));
    	 * 		}
    	 * 		else {
    	 * 			ignore false column
    	 * 		}
    	 * }
    	 */
    	
    	return selectedTable;
    }
    
    public JTable update(String column, String row, String table){
    	//This function modifies columns in the selected table that
    	//meet the conditions of where()
    	JTable selectedTable = select(column, table);
    	
    	/*for(int i=0; i<selectedTable.size(); i++){
       	 * 		if(selectedTable.getColumn meets where() conditions){
       	 * 				selectedTable.editCellAt(row, column);
       	 * 		}
       	 * 		else{
       	 * 			ignore false column
       	 * 		}
       	 * }
    	*/
    	
    	return selectedTable;
    }
    
    public JTable delete(String column, String row, String table){
    	
    	//This function deletes columns in the selected table that
    	//meet the conditions of where()
    	JTable selectedTable = select(column, table);
    	
    	/*for(int i=0; i<selectedTable.size(); i++){
       	 * 		if(selectedTable.getColumn meets where() conditions){
       	 * 				selectedTable.removeColumn(column);
       	 * 		}
       	 * 		else{
       	 * 			ignore false column
       	 * 		}
       	 * }
    	*/
    	
    	return selectedTable;
    }
}
