import java.util.*;

class TableOps {

    static Vector<Table> db = new Vector<Table>();
    static Vector<String> columnNames;
    static Vector<String> columnTypes;
    String columnName;
    
    
    public static ArrayList<Table> from(Vector<Table> database, String tableNames){
    	
    	String[] args = tableNames.split(" ");
    	ArrayList<Table> tables = new ArrayList<Table>();
        database = db;
        
        if(database.size() == 0){
                System.out.println("Database contains nothing.");
        }
        else if(database.size() > 0){
        	for(int i = 0; i < database.size(); i++){
        		if(args.equals(database.get(i).name())){
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
        
    	String[] args = query.split("FROM", 2);
    	String[] conditions = args[1].split("WHERE", 2);
    	
        Table selectedTable = new Table("Results", columnNames, columnTypes);
        ArrayList<Table> testTables = from(db, args[1]);
        
        //print out the list of tables
        for(int i = 0; i < testTables.size(); i++){
        	System.out.println("Table " + i + ":" + testTables.toString().charAt(i) + "\n");
        }
        
        /*
        //add specified columns first
        if(args[0].contains("*")){
        	//currently sets selectedTable equal to the first table returned by FROM
            selectedTable = from(db, args[1]).get(0);
        }
        else{

        }
		*/
        
        //call WHERE
        selectedTable = WhereClass.where(selectedTable, conditions[1]);    
          
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