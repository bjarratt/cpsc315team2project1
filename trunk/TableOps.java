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
                System.err.println("Database contains nothing.");
        }
        else if(database.size() > 0){
    		for(int argCtr=0, dbCtr=0; argCtr<args.length; ++argCtr) {
    			//Find the table in the database
    			for(dbCtr = 0; dbCtr<database.size() && !args[argCtr].trim().equals(database.get(dbCtr).getName()); ++dbCtr);
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
       	args[0]=args[0].trim()+',';
       	for(int selectedTableCtr=selectedTable.getColumnCount()-1; selectedTableCtr>=0; --selectedTableCtr) {
       		if(!args[0].contains(selectedTable.getColName(selectedTableCtr)+','))
       			selectedTable.removeColumn(selectedTableCtr);
       	}
        return selectedTable;
    }
    
    public static Table update(String query){
        //This function modifies columns in the selected table that
        //meet the conditions of where()
    	String[] args = query.split("SET", 2);
    	String[] conditions = args[1].split("WHERE", 2);
        Table testTable=from(db, conditions[0]);
        Table modTable = WhereClass.where(new Table(testTable), conditions[1]);
       	args[0]+=',';
       	for(int modTableCtr=modTable.getColumnCount(); modTableCtr>=0; --modTableCtr) {
       		if(!args[0].contains(modTable.getColName(modTableCtr)+','))
       			modTable.removeColumn(modTableCtr);
       	}
        return modTable;
    }
    
    public static Table delete(String query){
        
        //This function deletes columns in the selected table that
        //meet the conditions of where()
    	String[] args = query.split("FROM", 2);
    	String[] conditions = args[1].split("WHERE", 2);
        Table testTable=from(db, conditions[0]);
        Table modTable = WhereClass.where(new Table(testTable), conditions[1]);
       	args[0]+=',';
       	for(int modTableCtr=modTable.getColumnCount(); modTableCtr>=0; --modTableCtr) {
       		if(!args[0].contains(modTable.getColName(modTableCtr)+','))
       			modTable.removeColumn(modTableCtr);
       	}
        return modTable;
    }
    
    public static Table drop(String query){
    	String[] args = query.split("FROM", 2);
    	Table deletedTable=from(db, args[0]);
    	//loop through and delete all columns in the table
    	
    	return deletedTable;
    }
}