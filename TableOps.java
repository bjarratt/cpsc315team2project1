import java.util.*;

class TableOps {

    static ArrayList<Table> db = new ArrayList<Table>();
    
    
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
        Table testTable= new Table(from(db, conditions[0]));
        if(args[0].contains("AS")) {
        	args[0] = selectAs(args[0],testTable);
        }
        Table selectedTable = WhereClass.where(testTable, conditions[1]);
       	args[0]=args[0].trim()+',';
       	for(int selectedTableCtr=selectedTable.getColumnCount()-1; selectedTableCtr>=0; --selectedTableCtr) {
       		if(!args[0].contains(selectedTable.getColName(selectedTableCtr)+','))
       			selectedTable.removeColumn(selectedTableCtr);
       	}
        return selectedTable;
    }
    
    public static String selectAs(String query, Table table) {
    	String[] args = query.split("AS",2);
    	String[] oldNames = args[0].split(",");
    	String[] newNames = args[1].split(",");
    	
    	// Rename columns
    	for (int i=0; i<oldNames.length; i++) {
    		for (int j=0; j<table.getColumnCount(); j++) {
    			if (oldNames[i].equals(table.getColName(j))) {
    				table.setColName(j, newNames[i]);
    				break;
    			}
    		}
    	}
    	
    	return args[0].trim();
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

    public static Table insert(String query) {
    	// Parse
    	String[] args = query.split("VALUES",2);
    	String tableName = args[0];
    	String[] values = args[1].split(",");
    	
    	// Find appropriate table
    	Table thisTable = new Table();
    	for(int i=0; i<db.size(); i++) {
    		if(db.get(i).getName().equals(tableName)) {
    			thisTable = new Table(db.get(i));
    			break;
    		}
    		if (i==db.size()-1) {
    			System.err.println("Table doesn't exist!");
    			return new Table();
    		}
    	}
    	
    	// Check types and add into row
    	Vector<Object> newRow = new Vector<Object>();
    	for(int i=0; i<values.length; i++) {
    		if (thisTable.getColType(i).equals(CompareOps.STRING))
    			newRow.add(new String(values[i]));
    		else if (thisTable.getColType(i).equals(CompareOps.DOUBLE))
    			newRow.add(new Double(values[i]));
    		else if (thisTable.getColType(i).equals(CompareOps.INTEGER))
    			newRow.add(new Integer(values[i]));
    	}
    	
    	thisTable.addRow(newRow);
    	return thisTable;
    }
}