import java.util.Vector;
import static java.lang.System.*;

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
		Boolean running = true;
		
		Interface window = new Interface(ap,af,at,gi);
		window.setVisible(true);
        if(!loadDb.load("restoreDb.txt") && !loadDb.load("SampleData.txt")) {
        	//Both db load attempts failed, create an empty database
   			System.err.println("Error Loading SampleData.txt, blank db created");
        	TableOps.createTable("FlightInfo (flight# INTEGER, plane# INTEGER, passenger# INTEGER, startCity STRING, stopCity STRING, mealInclude STRING, departure STRING)");
        	TableOps.createTable("PassengerInfo (name STRING, addr STRING, passenger# INTEGER, age INTEGER)");
   			TableOps.createTable("TicketInfo (flight# INTEGER, passenger# INTEGER, class STRING, seat STRING, price DOUBLE)");
        }

//        Table tickets = TableOps.select("* FROM FlightInfo");
//        UserTables.printEntireTable(tickets);
    	Table displayTable=TableOps.select("mealInclude,flight#, plane#, startCity, stopCity, departure" +
				   "FROM FlightInfo" +
	 			  "WHERE mealInclude=TRUE");
    	UserTables.printEntireTable(displayTable);
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