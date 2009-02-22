class SingleValOps {
	static Double max(Table myTable, String colName) {
		double maxVal=0;
		int colNum=myTable.colWithName(colName);
		//What to do if user gives an invalid colName? return 0 I guess.
		if(colNum!=-1) {
			if(myTable.getRowCount()>0) {
				if(myTable.colType(colNum)==CompareOps.DOUBLE) {
					maxVal=(Double) myTable.getValueAt(0,colNum);
					for(int i=1; i<myTable.getRowCount(); ++i) {
						if((Double) myTable.getValueAt(i,colNum)>maxVal)
							maxVal=(Double) myTable.getValueAt(i,colNum);
					}
				}
				else if(myTable.colType(colNum)==CompareOps.INTEGER) {
					maxVal=(Integer) myTable.getValueAt(0,colNum);
					for(int i=1; i<myTable.getRowCount(); ++i) {
						if((Integer) myTable.getValueAt(i,colNum)>maxVal)
							maxVal=(Integer) myTable.getValueAt(i,colNum);
					}
				}	
			}
		}
		return maxVal;
	}
	static Double min(Table myTable, String colName) {
		double minVal=0;
		int colNum=myTable.colWithName(colName);
		//What to do if user gives an invalid colName? return 0 I guess.
		if(colNum!=-1) {
			if(myTable.getRowCount()>0) {
				if(myTable.colType(colNum)==CompareOps.DOUBLE) {
					minVal=(Double) myTable.getValueAt(0,colNum);
					for(int i=1; i<myTable.getRowCount(); ++i) {
						if((Double) myTable.getValueAt(i,colNum)<minVal)
							minVal=(Double) myTable.getValueAt(i,colNum);
					}
				}
				else if(myTable.colType(colNum)==CompareOps.INTEGER) {
					minVal=(Integer) myTable.getValueAt(0,colNum);
					for(int i=1; i<myTable.getRowCount(); ++i) {
						if((Integer) myTable.getValueAt(i,colNum)<minVal)
							minVal=(Integer) myTable.getValueAt(i,colNum);
					}
				}	
			}
		}
		return minVal;
	}
	static int count(Table myTable, String colName) {
		//No nulls currently
		if(myTable.colWithName(colName)==-1)
			return 0;
		return(myTable.getRowCount());
	}
	static double sum(Table myTable, String colName) {
		double sumVal=0;
		int colNum=myTable.colWithName(colName);
		if(colNum!=-1) {
			if(myTable.colType(colNum)==CompareOps.DOUBLE) {
				sumVal=(Double) myTable.getValueAt(0,colNum);
				for(int i=1; i<myTable.getRowCount(); ++i) {
					sumVal+=(Double) myTable.getValueAt(i,colNum);
				}
			}
			else if(myTable.colType(colNum)==CompareOps.INTEGER) {
				sumVal=(Integer) myTable.getValueAt(0,colNum);
				for(int i=1; i<myTable.getRowCount(); ++i) {
					sumVal+=(Integer) myTable.getValueAt(i,colNum);
				}
			}
		}
		return sumVal;
	}
}