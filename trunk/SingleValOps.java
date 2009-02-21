class SingleValOps {
	static Double max(Table myTable) {
		double maxVal=0;
		if(myTable.getRowCount()>0) {
			if(myTable.colType(0)==CompareOps.DOUBLE) {
				maxVal=(Double) myTable.getValueAt(0,0);
				for(int i=1; i<myTable.getRowCount(); ++i) {
					if((Double) myTable.getValueAt(i,0)>maxVal)
						maxVal=(Double) myTable.getValueAt(i,0);
				}
			}
			else if(myTable.colType(0)==CompareOps.INTEGER) {
				maxVal=(Integer) myTable.getValueAt(0,0);
				for(int i=1; i<myTable.getRowCount(); ++i) {
					if((Integer) myTable.getValueAt(i,0)>maxVal)
						maxVal=(Integer) myTable.getValueAt(i,0);
				}
			}
		}
		return maxVal;
	}
	static Double min(Table myTable) {
		double minVal=0;
		if(myTable.getRowCount()>0) {
			if(myTable.colType(0)==CompareOps.DOUBLE) {
				minVal=(Double) myTable.getValueAt(0,0);
				for(int i=1; i<myTable.getRowCount(); ++i) {
					if((Double) myTable.getValueAt(i,0)<minVal)
						minVal=(Double) myTable.getValueAt(i,0);
				}
			}
			else if(myTable.colType(0)==CompareOps.INTEGER) {
				minVal=(Integer) myTable.getValueAt(0,0);
				for(int i=1; i<myTable.getRowCount(); ++i) {
					if((Integer) myTable.getValueAt(i,0)<minVal)
						minVal=(Integer) myTable.getValueAt(i,0);
				}
			}
		}
		return minVal;
	}
	static int count(Table myTable) {
		return(myTable.getRowCount());
	}
	static double sum(Table myTable) {
		double sumVal=0;
		if(myTable.colType(0)==CompareOps.DOUBLE) {
			sumVal=(Double) myTable.getValueAt(0,0);
			for(int i=1; i<myTable.getRowCount(); ++i) {
				sumVal+=(Double) myTable.getValueAt(i,0);
			}
		}
		else if(myTable.colType(0)==CompareOps.INTEGER) {
			sumVal=(Integer) myTable.getValueAt(0,0);
			for(int i=1; i<myTable.getRowCount(); ++i) {
				sumVal+=(Integer) myTable.getValueAt(i,0);
			}
		}
		return sumVal;
	}
}