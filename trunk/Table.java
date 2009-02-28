import java.util.Vector;

public class Table {

    private Vector<Vector<Object>> table;
    private Vector<String> colTypes;
    private Vector<String> colNames;
    private String tableName;

    //Blank should be used ONLY to evade table not initialized error
    Table() {
    }

    //Copy
    Table(Table oldTable) {
   		tableName=oldTable.tableName;
   		table = new Vector<Vector<Object>>();
       	if(oldTable.table!=null) {
    		for(int i=0; i<oldTable.table.size(); ++i) {
    			//System.out.println(i);
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
    	int rTableRow=-1;
    	int lTableCol;
    	boolean nJoin=false;
    	//Attempt a natural Join
    	for(lTableCol=0; lTableCol<lTable.getColumnCount(); ++lTableCol) {
    		if((rTableCol=rTableCpy.colWithName(lTable.getColName(lTableCol)))!=-1) {
    			if(!nJoin) {
    				nJoin=true;
    				//Add columns, we are joining on lTable.getColName(lTableCol)
    				for(int i=0; i<rTable.getColumnCount(); ++i) {
						if(!rTable.getColName(i).equals(lTable.getColName(lTableCol))) {
							colTypes.add(rTable.colTypes.get(i));
							colNames.add(rTable.colNames.get(i));
						}
    				}
    				for(int lTableRow=getRowCount()-1; lTableRow>=0; --lTableRow) {
    					Vector<Object> modifyRow=table.get(lTableRow);
    					//Loop through all matching rows
    					while((rTableRow=rTable.getRowIndex(getValueAt(lTableRow, lTableCol), rTableCol, rTableRow+1))!=-1) {
    						addRow(modifyRow);
    						//Append columns to matching row
    						for(int i=0; i<rTable.getColumnCount(); ++i) {
    							if(!rTable.getColName(i).equals(lTable.getColName(lTableCol)))
    								table.get(getRowCount()-1).add(rTable.getValueAt(rTableRow, i));
    						}
    					}
    					//remove the row, all matching values in rTable have been appended
    					removeRow(lTableRow);
    				}
    			}
    			else
    				//remove any rows that do not match, we are joining on multiple columns
    				for(int lTableRow=getRowCount()-1; lTableRow>=0; --lTableRow)
    					if((rTableRow=rTable.getRowIndex(getValueAt(lTableRow, lTableCol), rTableCol, rTableRow+1))==-1)
        					removeRow(lTableRow);
    				
    		}
    	}
    	if(!nJoin) {
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
    	for (int i=0; i< getColumnCount(); i++) {
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
    	//System.out.println(getColumnCount() + " " + index);
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

    int getRowIndex(Object o, int colIndex, int rowCtr) {
    	if(rowCtr<0)
    		rowCtr=0;
    	if(getColType(colIndex).equals(CompareOps.STRING)) {
    		for(;rowCtr<getRowCount() && !((String) table.get(rowCtr).get(colIndex)).equals(o);++rowCtr);
    		if(rowCtr==getRowCount())
    			return -1;
    		else
    			return rowCtr;
    	}
    	else if(getColType(colIndex).equals(CompareOps.DOUBLE)) {
    		for(; rowCtr<getRowCount() && !((Double) table.get(rowCtr).get(colIndex)).equals(o);++rowCtr);
    		if(rowCtr==getRowCount())
    			return -1;
    		else
    			return rowCtr;
    	}
    	else if(getColType(colIndex).equals(CompareOps.INTEGER)) {
    		for(; rowCtr<getRowCount() && !((Integer) table.get(rowCtr).get(colIndex)).equals(o);++rowCtr);
    		if(rowCtr==getRowCount())
    			return -1;
    		else
    			return rowCtr;
    	}
    	else if(getColType(colIndex).equals(CompareOps.BOOLEAN)) {
    		for(; rowCtr<getRowCount() && !((String) table.get(rowCtr).get(colIndex)).equals(o);++rowCtr);
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
    void addColumns(Table newCols) {
    	if(newCols.getRowCount()>getRowCount()) {
    		Vector<Object> nullRow=new Vector<Object>();
    		for(int i=0; i<getColumnCount(); ++i)
    			nullRow.add(i);
    		for(int i=getRowCount(); i<newCols.getRowCount(); ++i)
    			addRow(nullRow);
    	}
    	for(int colCtr=0; colCtr<newCols.getColumnCount(); ++colCtr) {
        	int rowCtr;
    		for(rowCtr=0; rowCtr<newCols.getRowCount(); ++rowCtr) {
    			table.get(rowCtr).add(newCols.getValueAt(rowCtr,colCtr));
    		}
    		for(; rowCtr<getRowCount(); ++rowCtr) {
    			table.get(rowCtr).add(null);
    		}
    		colNames.add(newCols.colNames.get(colCtr));
    		colTypes.add(newCols.colTypes.get(colCtr));
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