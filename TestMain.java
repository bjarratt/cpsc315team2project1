class TestMain {
	public static void main(String args[]) {
		
		/* Pass in nulls for now, may be useful to pass in an instantiated class
		 * later on.
		 * 
		 * Be sure to use the Close button and not the windowing X or else...
		 */
		AddPassenger ap = new AddPassenger(null);
		AddFlight af 	= new AddFlight(null);
		AddTicket at	= new AddTicket(null);
		GetInfo gi		= new GetInfo(null);
		
		Interface window = new Interface(ap,af,at,gi);
		window.setVisible(true);

        loadDb.load("SampleData.txt");

        Table tickets = TableOps.select("* FROM TicketInfo");
        UserTables.printEntireTable(tickets);

		/*
		 * window.setVisible(true);
		 * 
		 * Vector<String> colNames=new Vector<String>(); Vector<String>
		 * colTypes=new Vector<String>(); Vector<Object> addRow=new
		 * Vector<Object>(); colNames.add("A"); colNames.add("I");
		 * colTypes.add(CompareOps.DOUBLE); colTypes.add(CompareOps.INTEGER);
		 * Table testObj=new Table("B", colNames, colTypes); addRow.add(0.0); //
		 * D addRow.add(10); // I testObj.addRow(addRow);
		 * addRow.setElementAt(1.0, 0); // D addRow.setElementAt(20, 1); // I
		 * testObj.addRow(addRow); addRow.setElementAt(2.0, 0);
		 * addRow.setElementAt(30, 1); testObj.addRow(addRow);
		 * addRow.setElementAt(3.0, 0); addRow.setElementAt(40, 1);
		 * testObj.addRow(addRow); addRow.setElementAt(1.0, 0);
		 * addRow.setElementAt(20, 1); testObj.addRow(addRow);
		 * TableOps.db.add(testObj); TableOps.createTable("A (A "+
		 * CompareOps.DOUBLE + ")"); TableOps.insertInto("A VALUES 1.0");
		 * TableOps.insertInto("A VALUES 2.0");
		 * TableOps.insertInto("A VALUES 3.0");
		 * TableOps.insertInto("A VALUES 1.0"); // for(int ctr=0;
		 * ctr<testObj.getRowCount(); ++ctr) { // System.out.println("TESTOBJ "
		 * + testObj.getValueAt(ctr, 0) +'\t'+ testObj.getValueAt(ctr, 1)); // }
		 * // Table retTable=WhereClass.where(testObj, "A > 1.1"); Table
		 * retTable=TableOps.select("A,I FROM A,B WHERE I > 2");
		 * out.println("\n\nThis is my final return table!\n");
		 * UserTables.printEntireTable(retTable);
		 * retTable=TableOps.select("A FROM A WHERE A > 2");
		 * out.println("\n\nThis is my final return table!\n");
		 * UserTables.printEntireTable(retTable);
		 */
	}
}