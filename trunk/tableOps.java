import java.util.*;

public class tableOps {

    Vector<table> db = new Vector<table>();
    ArrayList<table> tables = new ArrayList<table>();
    
    public table from(Vector<table> database){

    	database = db;
    	
    	if(database.size() == 0){
    		System.out.println("Error.");
    	}
    	else if(database.size() == 1){
    		table table1 = database.elementAt(0);
    		tables.add(table1);
    	}
    	else if(database.size() == 2){
    		table table2 = database.elementAt(1);
    		tables.add(table2);
    	}
        return tables.get(0);
    }

    public static table select(String column){
    	//this function will return a new table containing the columns
    	//that meet the requirements of where()
    	
    	table selectedTable = new table();
    	
    	String columnName = "";
    	column = columnName;
    	
    	String tableName = selectedTable.getName();
    	

    	/*for(int i=0; i<tables.size(); i++){
    	 * 		if(tables.get(0).getColumn(i) meets where() conditions){
    	 * 				selectedTable.addColumn(tables.get(0).getColumn(i));
    	 * 		}
    	 * 		else {
    	 * 			ignore false column
    	 * 		}
    	 * }
    	 * 
    	 * if(where() conditions dictate){
    	 * 		selectedTable.add(selectedTable.selectAll());
    	 * }
    	 */
    	
    	return selectedTable;
    }
    
    public table selectAs(String column){
    	//this function will return a new table containing the columns
    	//that meet the requirements of where(), renamed as the user defines
    	
    	table selectedTable = new table();
    	
    	String columnName = "";
    	column = columnName;
    	
    	String tableName = selectedTable.getName();

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
    
    
    public table update(String column, String row){
    	//This function modifies columns in the selected table that
    	//meet the conditions of where()
    	table selectedTable = select(column);
    	
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
    
    public table delete(String column, String row){
    	
    	//This function deletes columns in the selected table that
    	//meet the conditions of where()
    	table selectedTable = select(column);
    	
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
