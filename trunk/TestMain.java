/* Created by Brandon Jarrat, Andrew Reagan, and Gabriel Copley
 * CPSC 315 Team Project 1
 * Airline Managing application with an SQL-like database implementation
 */

class TestMain {
	public static void main(String args[]) {

		Interface window = new Interface();
		window.setVisible(true);

        loadDb.load("SampleData.txt");

	}
}