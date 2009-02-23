import java.util.*;

class TableOps {

    static ArrayList<Table> db = new ArrayList<Table>();
    static Vector<String> columnNames;
    static Vector<String> columnTypes;
    String columnName;
    
    
    public static Table from(ArrayList<Table> database, String tableNames){
    	String[] args = tableNames.split(",");
    	Table retTable=new Table();
        boolean addedTable=false;
        if(database.size() == 0){
                System.out.println("Database contains nothing.");
        }
        else if(database.size() > 0){
    		for(int argCtr=0, dbCtr=0; argCtr<args.length; ++argCtr) {
    			//Find the table in the database
    			for(dbCtr = 0; dbCtr<database.size() && args[argCtr].equals(database.get(dbCtr).getName()); ++dbCtr);
   				if(dbCtr!=db.size()){
   					if(addedTable)
   						retTable=new Table(retTable.getName()+" JOINED " + database.get(dbCtr).getName(), retTable, database.get(dbCtr));
   					else
   						retTable=new Table(database.get(dbCtr));
   				}
   				else{
   					System.err.println("This table does not exist in the database.");
   				}
    		}
        }
        return retTable;
    }

    //this function will return a new table containing the columns
    //that meet the requirements of WHERE
    public static Table select(String query){
    	String[] args = query.split("FROM", 2);
    	String[] conditions = args[1].split("WHERE", 2);
        Table testTable=from(db, conditions[0]);
        Table selectedTable = WhereClass.where(new Table(testTable), conditions[1]);
       	args[0]+=',';
       	for(int selectedTableCtr=selectedTable.getColumnCount(); selectedTableCtr>=0; --selectedTableCtr) {
       		if(!args[0].contains(selectedTable.getColName(selectedTableCtr)+','))
       			selectedTable.removeColumn(selectedTableCtr);
       	}
        return selectedTable;
    }
    
    public void update(String column, String row){
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
        
    }
    
    public void delete(String column, String row){
        
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
        
    }
}