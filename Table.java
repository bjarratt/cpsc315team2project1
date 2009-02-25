import java.util.Vector;

public class Table {

    private Vector<Vector<Object>> table;
    private Vector<String> colTypes;
    private Vector<String> colNames;
    private String tableName;

    //Blank... if used, we're doomed
    Table() {
    }

    //Copy
    Table(Table oldTable) {
   		tableName=oldTable.tableName;
   		table = new Vector<Vector<Object>>();
       	if(oldTable.table!=null) {
    		for(int i=0; i<oldTable.table.size(); ++i) {
    			System.out.println(i);
    			table.add(new Vector<Object>(oldTable.table.get(i)));
    		}
    		colTypes=new Vector<String>(oldTable.colTypes);
    		colNames=new Vector<String>(oldTable.colNames);
    	}
    	else {
    		colTypes=new Vector<String>();
    		colNames=new Vector<String>();
    	}
    		
    }
    //JOIN for two tables
    Table(String name, Table lTable, Table rTable) {
    	//Use copy constructor to get the lTable setup
    	this(lTable);
    	Table rTableCpy=new Table(rTable);
    	
    	tableName=name;
    	int rTableCol;
    	int rTableRow;
    	int lTableCtr;
    	boolean nJoin=false;
    	for(lTableCtr=0; lTableCtr<lTable.getColumnCount(); ++lTableCtr) {
    		if((rTableCol=rTableCpy.colNames.indexOf(colNames.get(lTableCtr)))!=-1) {
    			nJoin=true;
				Table tempTable=new Table();
    			for(int rowCtr=getRowCount()-1; rowCtr>=0; --rowCtr) {
    				if((rTableRow=getRowIndex(table.get(lTableCtr).get(rowCtr), rTableCol))!=-1)
    					tempTable.addRow(rTableCpy.table.get(rTableRow));
    				else
    					removeRow(rowCtr);
    			}
    			tempTable.removeColumn(rTableCol);
    			rTableCpy=new Table(tempTable);
    		}
    	}
    	if(nJoin) {
    		for(int ctr=0; ctr<rTableCpy.getColumnCount(); ++ctr) {
    			addColumns(rTableCpy.getColumn(ctr));
    		}
    	}
    	else if(!nJoin) {
//TODO: Implement Cartesian join
    	}
    }
    Table(int rows, int columns) {
        tableName = "Unnamed";
        table = new Vector<Vector<Object>>();
        for(int i=0; i<rows; ++i) {
                table.add(new Vector<Object>());
        }
        for(int i = 0; i< table.size(); ++i) {
            table.get(i).setSize(columns);
        }
        colTypes=new Vector<String>();
        colNames=new Vector<String>();
        for(int i=0; i<columns; ++i) {
        	colTypes.add("");
        	colNames.add("");
        }
    }

    // This constructor SHOULD be the one used
    Table(String name, Vector<String> columnNames, Vector<String> columnTypes) {
        tableName = name;
        colNames = new Vector<String>(columnNames);
        colTypes = new Vector<String>(columnTypes);

        table = new Vector<Vector<Object>>();
    }

    Object getValueAt(int row, int column) {
    	System.out.println("ColC" + getColumnCount() + "RowC" + getRowCount() + "r" + row + "c" + column);
        return table.get(row).get(column);
    }

    void setValueAt(Object value, int row, int column) {
        table.get(row).set(column, value);
    }

    int getRowCount() {
        return table.size();
    }

    int getColumnCount() {
        return colTypes.size();
    }

    int colWithName(String name) {
    	for (int i=0; i< colNames.size(); i++) {
    		if (colNames.get(i).equals(name))
    			return i;
    	}
    	// No column exists with this name. Throw an error value.
    	return -1;
    }
    
    void setColName(int index, String name) {
        colNames.set(index, name);
    }

    String getColName(int index) {
    	System.out.println(getColumnCount() + " " + index);
        return colNames.get(index);
    }

    String getColType(int index) {
        return colTypes.get(index);
    }

    void addRow(Vector<Object> stuff) {
        table.add(new Vector<Object>(stuff));
    }

    Vector<Object> getRow(int index) {
        return (table.get(index));
    }

    void removeRow(int index) {
        table.remove(index);
    }

    int getRowIndex(Object o, int colIndex) {
    	int rowCtr=0;
    	if(getColType(colIndex)==CompareOps.STRING) {
    		for(rowCtr=0; rowCtr<getRowCount() && ((String) table.get(rowCtr).get(colIndex)).equals(o););
    		if(rowCtr==getRowCount())
    			return -1;
    		else
    			return rowCtr;
    	}
    	else if(getColType(colIndex)==CompareOps.DOUBLE) {
    		for(rowCtr=0; rowCtr<getRowCount() && ((Double) table.get(rowCtr).get(colIndex)).equals(o););
    		if(rowCtr==getRowCount())
    			return -1;
    		else
    			return rowCtr;
    	}
    	else if(getColType(colIndex)==CompareOps.INTEGER) {
    		for(rowCtr=0; rowCtr<getRowCount() && ((Integer) table.get(rowCtr).get(colIndex)).equals(o););
    		if(rowCtr==getRowCount())
    			return -1;
    		else
    			return rowCtr;
    	}
    	else if(getColType(colIndex)==CompareOps.BOOLEAN) {
    		for(rowCtr=0; rowCtr<getRowCount() && ((String) table.get(rowCtr).get(colIndex)).equals(o););
    		if(rowCtr==getRowCount())
    			return -1;
    		else
    			return rowCtr;
    	}
    	else
    		return -1;
    }
    void removeColumn(int index) {
        for(int i=0; i<table.size(); i++) {
            table.get(i).remove(index);
        }
        colNames.remove(index);
        colTypes.remove(index);
    }
    public void addColumns(Table newCols) {
    	for(int colCtr=0; colCtr<newCols.getColumnCount(); ++colCtr) {
        	colTypes.add(newCols.getColType(colCtr));
        	colNames.add(newCols.getColName(colCtr));
           	int ctr=0;
           	if(newCols.getRowCount()<=table.size()) {
           		for(ctr=0; ctr<newCols.getRowCount(); ++ctr)
           			table.get(ctr).add(new Vector<Object>(newCols.table.get(ctr)));
           		for(; ctr<table.size(); ++ctr)
           			table.get(ctr).add(null);
           	}
           	else {
           		for(ctr=0; ctr<table.size(); ++ctr)
           			table.get(ctr).add(new Vector<Object>(newCols.table.get(ctr)));
           		for(ctr=0; ctr<newCols.getRowCount(); ++ctr) {
           			Vector<Object> newRow=new Vector<Object>();
           			for(int i=0; i<getColumnCount()-1; ++i)
           				newRow.add(null);
           			newRow.add(new Vector<Object>(newCols.table.get(ctr)));
           			addRow(newRow);
           		}
           	}
       	}
    }
    public Table getColumn(int index) {
    	if(index>=getColumnCount()) {
    		return new Table();
    	}
    	Vector<String> colType=new Vector<String>();
    	Vector<String> colName=new Vector<String>();
    	colType.add(getColType(index));
    	colName.add(getColName(index));
    	Table retTable=new Table(tableName, colType, colName);
      	int ctr=0;
  		for(ctr=0; ctr<getRowCount(); ++ctr)
   			retTable.addRow(new Vector<Object>(table.get(ctr)));
   		return retTable;
    }
    public void setName(String newName) {
        tableName = newName;
    }

    public String getName() {
        return tableName;
    }
}