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
        else {
    		for(int argCtr=0, dbCtr=0; argCtr<args.length; ++argCtr) {
    			//Find the table in the database
    			for(dbCtr = 0; dbCtr<database.size() && !args[argCtr].trim().equals(database.get(dbCtr).getName()); ++dbCtr);
    			if(dbCtr!=db.size()){
   					if(addedTable)
   						retTable=new Table(retTable.getName()+" JOINED " + database.get(dbCtr).getName(), retTable, database.get(dbCtr));
   					else {
   						addedTable=true;
   						retTable=database.get(dbCtr);
   					}
   				}
    			//Generate a blank table
   				else{
   					if(addedTable)
   						retTable=new Table(retTable.getName()+" JOINED " + args[argCtr], retTable, new Table(args[argCtr], new Vector<String>(), new Vector<String>()));
   					else {
   						addedTable=true;
   						retTable=new Table(args[argCtr], new Vector<String>(), new Vector<String>());
   					}
   				}
    		}
        }
        return retTable;
    }

    //this function will return a new table containing the columns
    //that meet the requirements of WHERE
    public static Table select(String query){
    	String[] args = query.split("FROM", 2);
    	//if no from, just return the table
    	if(args.length<2)
    		return HelperFunctions.convertToTable(new Table(1,1), args[0]);
    	String[] conditions = args[1].split("WHERE", 2);
        Table selectedTable= new Table(from(db, conditions[0].trim()));
        //if no where, skip calling the where function
        if(conditions.length>1)
        	selectedTable = WhereClass.where(selectedTable, conditions[1]);
       	args[0]=args[0].trim();
   		for(int minLoc=-1; (minLoc=args[0].indexOf("MIN(", minLoc+1))!=-1;)
   			selectedTable.addColumns(HelperFunctions.createTable(args[0].substring(minLoc, query.indexOf(")", minLoc)),CompareOps.DOUBLE, selectedTable.getRowCount(), SingleValOps.min(selectedTable, args[0].substring(minLoc+4, query.indexOf(")", minLoc)))));
   		for(int minLoc=-1; (minLoc=args[0].indexOf("MAX(", minLoc+1))!=-1;)
   			selectedTable.addColumns(HelperFunctions.createTable(args[0].substring(minLoc, query.indexOf(")", minLoc)),CompareOps.DOUBLE, selectedTable.getRowCount(), SingleValOps.max(selectedTable, args[0].substring(minLoc+4, query.indexOf(")", minLoc)))));
   		for(int minLoc=-1; (minLoc=args[0].indexOf("COUNT(", minLoc+1))!=-1;)
   			selectedTable.addColumns(HelperFunctions.createTable(args[0].substring(minLoc, query.indexOf(")", minLoc)),CompareOps.INTEGER, selectedTable.getRowCount(), SingleValOps.count(selectedTable, args[0].substring(minLoc+6, query.indexOf(")", minLoc)))));
   		for(int minLoc=-1; (minLoc=args[0].indexOf("SUM(", minLoc+1))!=-1;)
   			selectedTable.addColumns(HelperFunctions.createTable(args[0].substring(minLoc, query.indexOf(")", minLoc)),CompareOps.DOUBLE, selectedTable.getRowCount(), SingleValOps.sum(selectedTable, args[0].substring(minLoc+4, query.indexOf(")", minLoc)))));
        if(args[0].contains("AS"))
        	args[0]=selectAs(args[0],selectedTable);
        args[0]+=',';
        if(!args[0].contains("*")) {
        	for(int selectedTableCtr=selectedTable.getColumnCount()-1; selectedTableCtr>=0; --selectedTableCtr) {
        		if(!args[0].contains(selectedTable.getColName(selectedTableCtr)+','))
        			selectedTable.removeColumn(selectedTableCtr);
        	}
    		String[] colNames=args[0].split("[ \t\n]*,[ \t\n]*");
    		Vector<String> colName=new Vector<String>();
    		Vector<String> colType=new Vector<String>();
    		colName.add(null);
    		colType.add(null);
    		Table nullVal=new Table(null, colName, colType);
        	for(int colNameCtr=0; colNameCtr<colNames.length; ++colNameCtr) {
        		if(selectedTable.colWithName(colNames[colNameCtr])==-1) {
        			nullVal.setColName(0,colNames[colNameCtr]);
        			selectedTable.addColumns(nullVal);
        		}
        	}
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
    			if (oldNames[i].trim().equals(table.getColName(j).trim())) {
    				table.setColName(j, newNames[i].trim());
    				break;
    			}
    		}
    	}
    	
    	return args[1].trim();
    }
    
    public static void update(String query){
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
        return;
    }
    

    public static void delete(String query){
        
        //This function deletes columns in the selected table that
        //meet the conditions of where()
    	String[] args = query.split("FROM", 2);
    	String[] conditions = args[1].split("WHERE", 2);
    	String not =  "not(" + conditions[2] + ")";
        Table testTable=from(db, conditions[0]);
        WhereClass.where(testTable, not);
    }
    
    public static void drop(String query){
    	String[] args = query.split("FROM", 2);
    	//loop through and delete all columns in the table
    	for(int i=0; i<db.size(); ++i)
    		if(db.get(i).getName()==args[1]) 
    		 db.remove(i);
    	return;
    }

    public static void createTable(String query) {
    	String[] arg;
    	String[] args=query.split("\\(",2);
    	String tName=args[0].trim();
    	//System.out.println("\'" + tName + "\'");
    	args=args[1].split(",");
    	Vector<String> colNames=new Vector<String>();
    	Vector<String> colTypes=new Vector<String>();
   		for(int ctr=0; ctr<args.length; ++ctr) {
   			arg=args[ctr].trim().split(" ",2);
   			colNames.add(arg[0].trim());
   			if(arg[1].contains(")"))
   				arg[1]=arg[1].substring(0, arg[1].indexOf(")"));
   			colTypes.add(arg[1].trim());
   		}
       	db.add(new Table(tName, colNames, colTypes));
   	}
    public static void insertInto(String query) {
    	// Parse
    	String[] args = query.split("VALUES",2);
    	String tableName = args[0].trim();
    	String[] values = args[1].split(",");
    	//System.out.println(values.length);
    	// Find appropriate table
    	Table thisTable = from(db,tableName);
    	
    	// Check types and add into row
    	Vector<Object> newRow = new Vector<Object>();
		if(values.length>0 && values[0].contains("("))
			values[0]=values[0].substring(values[0].indexOf("(")+1);
    	for(int i=0; i<values.length; ++i) {
    		if(values[i].contains(")"))
    			values[i]=values[i].substring(0, values[i].lastIndexOf(")"));
    		if (thisTable.getColType(i).equals(CompareOps.STRING))
    			newRow.add(new String(values[i]));
    		else if (thisTable.getColType(i).equals(CompareOps.DOUBLE))
    			newRow.add(new Double(values[i]));
    		else if (thisTable.getColType(i).equals(CompareOps.INTEGER))
    			newRow.add(new Integer(values[i]));
    		else if (thisTable.getColType(i).equals(CompareOps.BOOLEAN))
    			newRow.add(new String(values[i]));
    	}
    	thisTable.addRow(newRow);
    }
    
    public static Table in(Table table, String query ) {
    	String keywords = query.split("\\(")[1].split("\\)")[0].trim();
    	String[] ids = keywords.split(",");
    	
    	Vector<String> colType = new Vector<String>();
    	Vector<String> colName = new Vector<String>();
    	colType.add(CompareOps.BOOLEAN);
    	colName.add("INnames");
    	Table retTable = new Table("IN table", colName, colType);
    	
    	// loop through table to see if the id's exist and assign TRUE or FALSE
    	for (int i=0; i<table.getRowCount(); i++) {
    		for (int j=0; j<ids.length; j++) {
    			Vector<Object> bool = new Vector<Object>();
    			if (table.getValueAt(i, 0).toString().equals(ids[j])) {
    				bool.add(CompareOps.TRUE);
    				retTable.addRow(bool);
    				break;
    			}if (j==ids.length-1) {
    				bool.add(CompareOps.FALSE);
    				retTable.addRow(bool);
    			}
    		}
    	}
    	
    	return retTable;
    }
}