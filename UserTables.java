public class UserTables {
	
	static void viewAllTableRelations() {
		// Loop through the database to print all of the tables
		for (int i=0; i<TableOps.db.size(); ++i){
			Table curTable = TableOps.db.get(i);
			// Show all columns on one row
			System.out.print(curTable.getName() + ":\t");
			for (int j=0; j<curTable.getColumnCount(); j++)
				System.out.print(curTable.getColName(j) + '\t');
			System.out.println();
		}			
	}
	
	static void viewTableRelationsAtIndex(int index) {
		Table curTable = TableOps.db.get(index);
		// Show all columns on one row
		for (int j=0; j<curTable.getColumnCount(); j++)
			System.out.print(curTable.getColName(j) + '\t');
		System.out.println();
	}
	
	static void viewTableRelationsWithName(String name) {
		// Loop through the database to print all of the tables
		for (int i=0; i<TableOps.db.size(); ++i) {
			Table curTable = TableOps.db.get(i);
			if (!name.equals(curTable.getName()))
				continue;
			
			// Show all columns on one row
			for (int j=0; j<curTable.getColumnCount(); j++)
				System.out.print(curTable.getColName(j) + '\t');
			System.out.println();
		}		
	}

    /**
     * Analagous to the Table's own toString method except
     * this prints to the console instead of a String
     * @param table
     */
	static void printEntireTable(Table table) {
		for (int i=0; i<table.getColumnCount(); i++)
			System.out.print(table.getColName(i) + "\t");
		System.out.println();
		
		for (int i=0; i<table.getRowCount(); i++) {
			for (int j=0; j<table.getColumnCount(); j++)
				System.out.print(table.getValueAt(i, j) + "\t");
			System.out.println();
		}
	}

    static void printAllTables() {
        for (int i=0; i<TableOps.db.size(); i++) {
            printEntireTable(TableOps.db.get(i));
            System.out.println();
        }
    }
}
