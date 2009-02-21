import java.util.Vector;

class TestMain {
	public static void main(String args[]) {
		Vector<String> colNames=new Vector<String>();
		Vector<String> colTypes=new Vector<String>();
		colNames.add("A");
		colTypes.add(CompareOps.DOUBLE);
		Table testObj=new Table("Test Table", colNames, colTypes);
		testObj.setValueAt(0.0,0,0);
		testObj.setValueAt(1.0,1,0);
		testObj.setValueAt(2.0,2,0);
		testObj.setValueAt(3.0,3,0);
		testObj.setValueAt(1.0,4,0);
		for(int ctr=0; ctr<testObj.getRowCount(); ++ctr) {
			System.out.println("TESTOBJ" + testObj.getValueAt(ctr, 0));
		}
		Table retTable=WhereClass.where(testObj, "A > 1.1");
		for(int ctr=0; ctr<retTable.getRowCount(); ++ctr) {
			System.out.println("RETTABLE" + retTable.getValueAt(ctr, 0));
		}
	}
}