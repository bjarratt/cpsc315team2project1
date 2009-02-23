import java.util.Vector;

class TestMain {
	public static void main(String args[]) {
		Vector<String> colNames=new Vector<String>();
		Vector<String> colTypes=new Vector<String>();
		Vector<Object> addRow=new Vector<Object>();
		colNames.add("A");
		colTypes.add(CompareOps.DOUBLE);
		Table testObj=new Table("A", colNames, colTypes);
		addRow.add(0.0);
		testObj.addRow(addRow);
		addRow.setElementAt(1.0, 0);
		testObj.addRow(addRow);
		addRow.setElementAt(2.0, 0);
		testObj.addRow(addRow);
		addRow.setElementAt(3.0, 0);
		testObj.addRow(addRow);
		addRow.setElementAt(1.0, 0);
		testObj.addRow(addRow);
		TableOps.db.add(testObj);
		for(int ctr=0; ctr<testObj.getRowCount(); ++ctr) {
			System.out.println("TESTOBJ" + testObj.getValueAt(ctr, 0));
		}
//		Table retTable=WhereClass.where(testObj, "A > 1.1");
		Table retTable=TableOps.select("A FROM A WHERE A > 1.1");
		for(int ctr=0; ctr<retTable.getRowCount(); ++ctr) {
			System.out.println("RETTABLE" + retTable.getValueAt(ctr, 0));
		}
	}
}