class compareOps {

    static final String TRUE    = "TRUE";
    static final String FALSE   = "FALSE";
    static final String STRING  = "STRING";
    static final String DOUBLE  = "DOUBLE";
    static final String INTEGER = "INTEGER";

    // AND
    static table and(table lTable, table rTable) {
        int colNums = lTable.getColumnCount();
        table retTable = new table(colNums, 1);
        for (int i = 0; i < colNums; i++) {
            String lval = (String) lTable.getValueAt(i, 0);
            String rval = (String) rTable.getValueAt(i, 0);
            if (lval.equals(TRUE) && rval.equals(TRUE)) {
                retTable.setValueAt(TRUE, i, 0);
            } else {
                retTable.setValueAt(FALSE, i, 0);
            }
        }

        return retTable;
    }

    // OR
    static table or(table lTable, table rTable) {
        int colNums = lTable.getColumnCount();
        table retTable = new table(colNums, 1);
        for (int i = 0; i < colNums; i++) {
            String lval = (String) lTable.getValueAt(i, 0);
            String rval = (String) rTable.getValueAt(i, 0);
            if (lval.equals(TRUE) || rval.equals(TRUE)) {
                retTable.setValueAt(TRUE, i, 0);
            } else {
                retTable.setValueAt(FALSE, i, 0);
            }
        }

        return retTable;
    }

    // NOT
    static table not(table table) {
        int colNums = table.getColumnCount();
        table retTable = new table(colNums, 1);
        for (int i = 0; i < colNums; i++) {
            String val = (String) table.getValueAt(i, 0);
            if (val.equals(TRUE))
                table.setValueAt(FALSE, i, 0);
            else
                table.setValueAt(TRUE, i, 0);
        }

        return retTable;
    }

    // != not equal
    static table notEqual(table lTable, table rTable) {
        int colNums = lTable.getColumnCount();
        table retTable = new table(colNums, 1);
        Object lval;
        Object rval;
        
        for (int i = 0; i < colNums; i++) {
        	// Check type and assign
            if (table.colTypes.get(0).equals(STRING)) {
            	lval = (String) lTable.getValueAt(i, 0);
            	rval = (String) rTable.getValueAt(i, 0);
            }
            else if (table.colTypes.get(0).equals(DOUBLE)) {
            	lval = (Double) lTable.getValueAt(i, 0);
            	rval = (Double) rTable.getValueAt(i, 0);
            }
            else if (table.colTypes.get(0).equals(INTEGER)) {
            	lval = (Integer) lTable.getValueAt(i, 0);
            	rval = (Integer) rTable.getValueAt(i, 0);
            }
            
            // 0 means they are same
            if (lval.compareTo(rval)!=0)
                retTable.setValueAt(TRUE, i, 0);
            else
                retTable.setValueAt(FALSE,i, 0);
         }


        return retTable;
    }

    // = equals
    static table equals(table lTable, table rTable) {
    	int colNums = lTable.getColumnCount();
        table retTable = new table(colNums, 1);
        Object lval;
        Object rval;
        
        for (int i = 0; i < colNums; i++) {
        	// Check type and assign
            if (table.colTypes.get(0).equals(STRING)) {
            	lval = (String) lTable.getValueAt(i, 0);
            	rval = (String) rTable.getValueAt(i, 0);
            }
            else if (table.colTypes.get(0).equals(DOUBLE)) {
            	lval = (Double) lTable.getValueAt(i, 0);
            	rval = (Double) rTable.getValueAt(i, 0);
            }
            else if (table.colTypes.get(0).equals(INTEGER)) {
            	lval = (Integer) lTable.getValueAt(i, 0);
            	rval = (Integer) rTable.getValueAt(i, 0);
            }

            // 0 means they are same
            if (lval.compareTo(rval)==0)
                retTable.setValueAt(TRUE, i, 0);
            else
                retTable.setValueAt(FALSE,i, 0);
         }


        return retTable;
    }

    // <= less than or equal to
    static table lessEqual(table lTable, table rTable) {
    	int colNums = lTable.getColumnCount();
        table retTable = new table(colNums, 1);
        Object lval;
        Object rval;
        
        for (int i = 0; i < colNums; i++) {
        	// Check type and assign
            if (table.colTypes.get(0).equals(STRING)) {
            	lval = (String) lTable.getValueAt(i, 0);
            	rval = (String) rTable.getValueAt(i, 0);
            }
            else if (table.colTypes.get(0).equals(DOUBLE)) {
            	lval = (Double) lTable.getValueAt(i, 0);
            	rval = (Double) rTable.getValueAt(i, 0);
            }
            else if (table.colTypes.get(0).equals(INTEGER)) {
            	lval = (Integer) lTable.getValueAt(i, 0);
            	rval = (Integer) rTable.getValueAt(i, 0);
            }
            
            // 0 means they are same
            // -1 means lval is less
            if (lval.compareTo(rval)<=0)
                retTable.setValueAt(TRUE, i, 0);
            else
                retTable.setValueAt(FALSE,i, 0);
         }


        return retTable;
    }

    // >= greater than or equal to
    static table greaterEqual(table lTable, table rTable) {
    	int colNums = lTable.getColumnCount();
        table retTable = new table(colNums, 1);
        Object lval;
        Object rval;
        
        for (int i = 0; i < colNums; i++) {
        	// Check type and assign
            if (table.colTypes.get(0).equals(STRING)) {
            	lval = (String) lTable.getValueAt(i, 0);
            	rval = (String) rTable.getValueAt(i, 0);
            }
            else if (table.colTypes.get(0).equals(DOUBLE)) {
            	lval = (Double) lTable.getValueAt(i, 0);
            	rval = (Double) rTable.getValueAt(i, 0);
            }
            else if (table.colTypes.get(0).equals(INTEGER)) {
            	lval = (Integer) lTable.getValueAt(i, 0);
            	rval = (Integer) rTable.getValueAt(i, 0);
            }
            
            // 0 means they are same
            // 1 means lval is greater
            if (lval.compareTo(rval)>=0)
                retTable.setValueAt(TRUE, i, 0);
            else
                retTable.setValueAt(FALSE,i, 0);
         }


        return retTable;
    }

    // > greater than
    static table greaterThan(table lTable, table rTable) {
    	int colNums = lTable.getColumnCount();
        table retTable = new table(colNums, 1);
        Object lval;
        Object rval;
        
        for (int i = 0; i < colNums; i++) {
        	// Check type and assign
            if (table.colTypes.get(0).equals(STRING)) {
            	lval = (String) lTable.getValueAt(i, 0);
            	rval = (String) rTable.getValueAt(i, 0);
            }
            else if (table.colTypes.get(0).equals(DOUBLE)) {
            	lval = (Double) lTable.getValueAt(i, 0);
            	rval = (Double) rTable.getValueAt(i, 0);
            }
            else if (table.colTypes.get(0).equals(INTEGER)) {
            	lval = (Integer) lTable.getValueAt(i, 0);
            	rval = (Integer) rTable.getValueAt(i, 0);
            }
            
            // 1 means lval is greater
            if (lval.compareTo(rval)==1)
                retTable.setValueAt(TRUE, i, 0);
            else
                retTable.setValueAt(FALSE,i, 0);
         }

    }

    // < less than
    static table lessThan(table lTable, table rTable) {
    	int colNums = lTable.getColumnCount();
        table retTable = new table(colNums, 1);
        Object lval;
        Object rval;
        
        for (int i = 0; i < colNums; i++) {
        	// Check type and assign
            if (table.colTypes.get(0).equals(STRING)) {
            	lval = (String) lTable.getValueAt(i, 0);
            	rval = (String) rTable.getValueAt(i, 0);
            }
            else if (table.colTypes.get(0).equals(DOUBLE)) {
            	lval = (Double) lTable.getValueAt(i, 0);
            	rval = (Double) rTable.getValueAt(i, 0);
            }
            else if (table.colTypes.get(0).equals(INTEGER)) {
            	lval = (Integer) lTable.getValueAt(i, 0);
            	rval = (Integer) rTable.getValueAt(i, 0);
            }
            
            // -1 means lval is less
            if (lval.compareTo(rval)==-1)
                retTable.setValueAt(TRUE, i, 0);
            else
                retTable.setValueAt(FALSE,i, 0);
         }

    }
}