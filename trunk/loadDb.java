import java.io.BufferedReader;
import java.io.FileReader;

class loadDb {
	static void load(String fileName) {
		try {
			String nextLine;
			String currentTable;
			BufferedReader in;
			in = new BufferedReader(new FileReader(fileName));
	        while ((nextLine = in.readLine())!= null) {
	        	currentTable=nextLine.substring(0,nextLine.indexOf(' '));
	        	TableOps.createTable(nextLine);
	        	while((nextLine = in.readLine())!= null && !nextLine.equals("")) {
	        		TableOps.insertInto(currentTable + " VALUES (" + nextLine + ")");
	        	}
	        }
		} catch(Exception e) {
			System.err.println(e);
		};
	}
}
