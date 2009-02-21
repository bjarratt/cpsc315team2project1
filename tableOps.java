import java.util.*;

public class tableOps {

    static Vector<Table> db = new Vector<Table>();
    static ArrayList<Table> tables = new ArrayList<Table>();
    static Vector<String> columnNames;
    static Vector<String> columnTypes;
    String columnName;
    
    
    public static Table from(Vector<Table> database){

    	database = db;
    	
    	if(database.size() == 0){
    		System.out.println("Error.");
    	}
    	else if(database.size() == 1){
    		Table table1 = database.elementAt(0);
    		tables.add(table1);
    	}
    	else if(database.size() == 2){
    		Table table2 = database.elementAt(1);
    		tables.add(table2);
    	}
        return tables.get(0);
    }

    public static Table select(String...colNames){
    	//this function will return a new table containing the columns
    	//that meet the requirements of where()
    	
    	Table selectedTable = new Table("Results", columnNames, columnTypes);
    	
    	if(colNames.toString() == "*"){
    		selectedTable = from(db);
    	}
    	else{
    		//add columns identified by colNames to selectedTable
    	}
    	
    	return selectedTable;
    }
    
    public Table update(String column, String row){
    	//This function modifies columns in the selected table that
    	//meet the conditions of where()
    	Table selectedTable = select(column);
    	
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
    
    public Table delete(String column, String row){
    	
    	//This function deletes columns in the selected table that
    	//meet the conditions of where()
    	Table selectedTable = select(column);
    	
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
