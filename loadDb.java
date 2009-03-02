import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

class loadDb {
	//Loads the database
	static boolean load(String fileName) {
		try {
			BufferedReader in= new BufferedReader(new FileReader(fileName));
			try {
				String nextLine;
				String currentTable;
				while ((nextLine = in.readLine())!= null) {
					currentTable=nextLine.substring(0,nextLine.indexOf(' '));
					TableOps.createTable(nextLine);
					while((nextLine = in.readLine())!= null && !nextLine.equals("")) {
						TableOps.insertInto(currentTable + " VALUES (" + nextLine + ")");
					}
				}
			} finally {
				in.close();
			}
		} catch(Exception e) {
			return false;
		}
		return true;
	}
	//Saves the database to the given file
	static boolean saveDatabase(String fileName) {
		try {
			BufferedWriter out= new BufferedWriter(new FileWriter(fileName));
			try {
				for(int tableCtr=0; tableCtr<TableOps.db.size(); ++tableCtr) {
					Table curTable=TableOps.db.get(tableCtr);
					out.write(curTable.getName() + " (" );
					if(curTable.getColumnCount()!=0)
						out.write(curTable.getColName(0) + " " + curTable.getColType(0));
					for(int colCtr=1; colCtr<curTable.getColumnCount(); ++colCtr) {
						out.write(", " + curTable.getColName(colCtr) + " " + curTable.getColType(colCtr));
					}
					out.write(")");
					out.newLine();
					for(int rowCtr=0; rowCtr<curTable.getRowCount(); ++rowCtr) {
						try {
							out.write(curTable.getValueAt(rowCtr,0).toString());
						} catch(NullPointerException e) {
							out.write("");
						}
						for(int colCtr=1; colCtr<curTable.getColumnCount(); ++colCtr) {
							try {
								out.write("," + curTable.getValueAt(rowCtr,colCtr).toString());
							} catch(NullPointerException e) {
								out.write(",");
							}
						}
						out.newLine();
					}
					out.newLine();
				}
			} finally {
				out.close();
			}
		} catch(Exception e) {
			return false;
		}
		return true;
	}
}