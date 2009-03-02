class TestMain {
        public static void main(String args[]) {
                Interface window = new Interface();
                window.setVisible(true);

	        if(!loadDb.load("restoreDb.txt") && !loadDb.load("SampleData.txt")) {
        		//Both db load attempts failed, create an empty database
   			System.err.println("Error Loading SampleData.txt, blank db created");
        		TableOps.createTable("FlightInfo (flight# INTEGER, plane# INTEGER, passenger# INTEGER, startCity STRING, stopCity STRING, mealInclude STRING, departure STRING)");
	        	TableOps.createTable("PassengerInfo (name STRING, addr STRING, passenger# INTEGER, age INTEGER)");
   			TableOps.createTable("TicketInfo (flight# INTEGER, passenger# INTEGER, class STRING, seat STRING, price DOUBLE)");
	        }
        }
}