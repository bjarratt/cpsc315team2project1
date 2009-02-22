import java.util.*;

class TableOps {

    static Vector<Table> db = new Vector<Table>();
    static Vector<String> columnNames;
    static Vector<String> columnTypes;
    String columnName;
    
    
    public static ArrayList<Table> from(Vector<Table> database, String tableNames){
    	
    	String[] args;
    	args = tableNames.split(" ");
    	ArrayList<Table> tables = new ArrayList<Table>();
        database = db;
        
        if(database.size() == 0){
                System.out.println("Database contains nothing.");
        }
        else if(database.size() > 0){
        	for(int i = 0; i < database.size(); i++){
        		if(tableNames.equals(database.get(i).name())){
        			tables.add(database.get(i));
        		}
        		else{
        			System.err.println("This table does not exist in the database.");
        		}
        	}
        }
        return tables;
    }

    public static Table select(String query){
        //this function will return a new table containing the columns
        //that meet the requirements of WHERE
        
    	String[] args;
    	args = query.split("FROM", 2);
    	String[] conditions = args[1].split("WHERE", 2);
    	
        Table selectedTable = new Table("Results", columnNames, columnTypes);
        
        //add specified columns first
        if(args[0].contains("*")){
            selectedTable = from(db, args[1]).get(0);
        }
        else{
            for(int i = 0; i < args[1].length(); i++){
                    selectedTable.addColumn(i);
                    selectedTable.setColName(i, args[1]);
                }
        }

        //remove rows that do not meet WHERE statement conditions
        for(int i = 0; i < selectedTable.getColumnCount(); i++){
            if(false /*WHERE conditions are not met */){
                
            }
        }
        return selectedTable;
    }
    
    public Table update(String column, String row){
        //This function modifies columns in the selected table that
        //meet the conditions of where()
        Table selectedTable = select(column);
        
        /*for(int i=0; i<selectedTable.size(); i++){
         *              if(selectedTable.getColumn meets where() conditions){
         *                              selectedTable.editCellAt(row, column);
         *              }
         *              else{
         *                      ignore false column
         *              }
         * }
        */
        
        return selectedTable;
    }
    
    public Table delete(String column, String row){
        
        //This function deletes columns in the selected table that
        //meet the conditions of where()
        Table selectedTable = select(column);
        
        /*for(int i=0; i<selectedTable.size(); i++){
         *              if(selectedTable.getColumn meets where() conditions){
         *                              selectedTable.removeColumn(column);
         *              }
         *              else{
         *                      ignore false column
         *              }
         * }
        */
        
        return selectedTable;
    }
}