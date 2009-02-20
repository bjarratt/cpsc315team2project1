class testMain {
	public static void main(String args[]) {
		table testObj=new table(5,1);
		testObj.setValueAt(0.0,0,0);
		testObj.setValueAt(1.0,1,0);
		testObj.setValueAt(2.0,2,0);
		testObj.setValueAt(3.0,3,0);
		testObj.setValueAt(1.0,4,0);
		table retTable=whereClass.where(testObj, "A > 1.1");
		for(int ctr=0; ctr<testObj.getRowCount(); ++ctr) {
			System.out.println("TESTOBJ" + testObj.getValueAt(ctr, 0));
		}
	}
}