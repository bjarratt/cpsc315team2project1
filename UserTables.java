public class UserTables {
	
	void viewAllTables() {
		// Loop through the database to print all of the tables
		for (int i=0; i<TableOps.db.size(); ++i){
			Table curTable = TableOps.db.get(i);
			// Show all columns on one row
			for (int j=0; j<curTable.getColumnCount(); j++)
				System.out.print(curTable.getColName(j) + '\t');
			System.out.println();
		}			
	}
	
	void viewTableAtIndex(int index) {
		Table curTable = TableOps.db.get(index);
		// Show all columns on one row
		for (int j=0; j<curTable.getColumnCount(); j++)
			System.out.print(curTable.getColName(j) + '\t');
		System.out.println();
	}
	
	void viewTableWithName(String name) {
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
}
