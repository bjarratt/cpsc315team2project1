class testMain {
	public static void main(String args[]) {
		table testObj=new table(5,1);
		testObj.setValueAt(0.0,0,0);
		testObj.setValueAt(1.0,0,0);
		testObj.setValueAt(2.0,0,0);
		testObj.setValueAt(3.0,0,0);
		testObj.setValueAt(1.0,0,0);
		whereClass.where(testObj, "A > 1.1");
	}
}