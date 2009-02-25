class CompareOps {

    static final String TRUE    = "TRUE";
    static final String FALSE   = "FALSE";
    static final String STRING  = "STRING";
    static final String DOUBLE  = "DOUBLE";
    static final String INTEGER = "INTEGER";
    static final String BOOLEAN = "BOOLEAN";

    // AND
    static Table and(Table lTable, Table rTable) {
        int numRows = lTable.getRowCount();
        Table retTable = new Table(numRows, 1);
        for (int i = 0; i < numRows; i++) {
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
    static Table or(Table lTable, Table rTable) {
        int numRows = lTable.getRowCount();
        Table retTable = new Table(numRows, 1);
        for (int i = 0; i < numRows; i++) {
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
    static Table not(Table Table) {
        int numRows = Table.getRowCount();
        Table retTable = new Table(numRows, 1);
        for (int i = 0; i < numRows; i++) {
            String val = (String) Table.getValueAt(i, 0);
            if (val.equals(TRUE))
                retTable.setValueAt(FALSE, i, 0);
            else
                retTable.setValueAt(TRUE, i, 0);
        }
        return retTable;
    }

    // != not equal
    static Table notEqual(Table lTable, Table rTable) {
        int numRows = lTable.getRowCount();
        Table retTable = new Table(numRows, 1);

        for (int i = 0; i < numRows; i++) {
        	// Check type and assign
            if (lTable.getColType(0).equals(STRING)) {
            	String lval = (String) lTable.getValueAt(i, 0);
            	String rval = (String) rTable.getValueAt(i, 0);
                if (lval.compareTo(rval)!=0)
                    retTable.setValueAt(TRUE, i, 0);
                else
                    retTable.setValueAt(FALSE,i, 0);
            }
            else if (lTable.getColType(0).equals(DOUBLE)) {
            	Double lval = (Double) lTable.getValueAt(i, 0);
            	Double rval = (Double) rTable.getValueAt(i, 0);
                if (lval.compareTo(rval)!=0)
                    retTable.setValueAt(TRUE, i, 0);
                else
                    retTable.setValueAt(FALSE,i, 0);
            }
            else if (lTable.getColType(0).equals(INTEGER)) {
            	Integer lval = (Integer) lTable.getValueAt(i, 0);
            	Integer rval = (Integer) rTable.getValueAt(i, 0);
                if (lval.compareTo(rval)!=0)
                    retTable.setValueAt(TRUE, i, 0);
                else
                    retTable.setValueAt(FALSE,i, 0);
            }
            else
                return retTable;
         }

        return retTable;
    }

    // = equals
    static Table equals(Table lTable, Table rTable) {
    	int numRows = lTable.getRowCount();
        Table retTable = new Table(numRows, 1);

        for (int i = 0; i < numRows; i++) {
        	// Check type and assign
            if (lTable.getColType(0).equals(STRING)) {
            	String lval = (String) lTable.getValueAt(i, 0);
            	String rval = (String) rTable.getValueAt(i, 0);
                // 0 means they are same
                if (lval.compareTo(rval)==0)
                    retTable.setValueAt(TRUE, i, 0);
                else
                    retTable.setValueAt(FALSE,i, 0);
            }
            else if (lTable.getColType(0).equals(DOUBLE)) {
                Double lval = (Double) lTable.getValueAt(i, 0);
            	Double rval = (Double) rTable.getValueAt(i, 0);
                // 0 means they are same
                if (lval.compareTo(rval)==0)
                    retTable.setValueAt(TRUE, i, 0);
                else
                    retTable.setValueAt(FALSE,i, 0);
            }
            else if (lTable.getColType(0).equals(INTEGER)) {
            	Integer lval = (Integer) lTable.getValueAt(i, 0);
            	Integer rval = (Integer) rTable.getValueAt(i, 0);
                // 0 means they are same
                if (lval.compareTo(rval)==0)
                    retTable.setValueAt(TRUE, i, 0);
                else
                    retTable.setValueAt(FALSE,i, 0);
            }
         }

        return retTable;
    }

    // <= less than or equal to
    static Table lessEqual(Table lTable, Table rTable) {
    	int numRows = lTable.getRowCount();
        Table retTable = new Table(numRows, 1);

        for (int i = 0; i < numRows; i++) {
        	// Check type and assign
            if (lTable.getColType(0).equals(STRING)) {
            	String lval = (String) lTable.getValueAt(i, 0);
            	String rval = (String) rTable.getValueAt(i, 0);
                // 0 means they are same
                if (lval.compareTo(rval)<=0)
                    retTable.setValueAt(TRUE, i, 0);
                else
                    retTable.setValueAt(FALSE,i, 0);
            }
            else if (lTable.getColType(0).equals(DOUBLE)) {
                Double lval = (Double) lTable.getValueAt(i, 0);
            	Double rval = (Double) rTable.getValueAt(i, 0);
                // 0 means they are same
                if (lval.compareTo(rval)<=0)
                    retTable.setValueAt(TRUE, i, 0);
                else
                    retTable.setValueAt(FALSE,i, 0);
            }
            else if (lTable.getColType(0).equals(INTEGER)) {
            	Integer lval = (Integer) lTable.getValueAt(i, 0);
            	Integer rval = (Integer) rTable.getValueAt(i, 0);
                // 0 means they are same
                if (lval.compareTo(rval)<=0)
                    retTable.setValueAt(TRUE, i, 0);
                else
                    retTable.setValueAt(FALSE,i, 0);
            }
         }

        return retTable;
    }

    // >= greater than or equal to
    static Table greaterEqual(Table lTable, Table rTable) {
    	int numRows = lTable.getRowCount();
        Table retTable = new Table(numRows, 1);

        for (int i = 0; i < numRows; i++) {
        	// Check type and assign
            if (lTable.getColType(0).equals(STRING)) {
            	String lval = (String) lTable.getValueAt(i, 0);
            	String rval = (String) rTable.getValueAt(i, 0);
                // 0 means they are same
                // -1 means less
                if (lval.compareTo(rval)>=0)
                    retTable.setValueAt(TRUE, i, 0);
                else
                    retTable.setValueAt(FALSE,i, 0);
            }
            else if (lTable.getColType(0).equals(DOUBLE)) {
                Double lval = (Double) lTable.getValueAt(i, 0);
            	Double rval = (Double) rTable.getValueAt(i, 0);
                // 0 means they are same
                // -1 means less
                if (lval.compareTo(rval)>=0)
                    retTable.setValueAt(TRUE, i, 0);
                else
                    retTable.setValueAt(FALSE,i, 0);
            }
            else if (lTable.getColType(0).equals(INTEGER)) {
            	Integer lval = (Integer) lTable.getValueAt(i, 0);
            	Integer rval = (Integer) rTable.getValueAt(i, 0);
                // 0 means they are same
                // -1 means less
                if (lval.compareTo(rval)>=0)
                    retTable.setValueAt(TRUE, i, 0);
                else
                    retTable.setValueAt(FALSE,i, 0);
            }
         }

        return retTable;
    }

    // > greater than
    static Table greaterThan(Table lTable, Table rTable) {
    	int numRows = lTable.getRowCount();
        Table retTable = new Table(numRows, 1);

        for (int i = 0; i < numRows; i++) {
        	// Check type and assign
            if (lTable.getColType(0).equals(STRING)) {
            	String lval = (String) lTable.getValueAt(i, 0);
            	String rval = (String) rTable.getValueAt(i, 0);
                // 1 means greater than
                if (lval.compareTo(rval)==1)
                    retTable.setValueAt(TRUE, i, 0);
                else
                    retTable.setValueAt(FALSE,i, 0);
            }
            else if (lTable.getColType(0).equals(DOUBLE)) {
                Double lval = (Double) lTable.getValueAt(i, 0);
            	Double rval = (Double) rTable.getValueAt(i, 0);
                // 1 means greater than
                if (lval.compareTo(rval)==1)
                    retTable.setValueAt(TRUE, i, 0);
                else
                    retTable.setValueAt(FALSE,i, 0);
            }
            else if (lTable.getColType(0).equals(INTEGER)) {
            	Integer lval = (Integer) lTable.getValueAt(i, 0);
            	Integer rval = (Integer) rTable.getValueAt(i, 0);
                // 1 means greater than
                if (lval.compareTo(rval)==1)
                    retTable.setValueAt(TRUE, i, 0);
                else
                    retTable.setValueAt(FALSE,i, 0);
            }
         }

        return retTable;
    }

    // < less than
    static Table lessThan(Table lTable, Table rTable) {
    	int numRows = lTable.getRowCount();
        Table retTable = new Table(numRows, 1);

        for (int i = 0; i < numRows; i++) {
        	// Check type and assign
            if (lTable.getColType(0).equals(STRING)) {
            	String lval = (String) lTable.getValueAt(i, 0);
            	String rval = (String) rTable.getValueAt(i, 0);
                // -1 means less than
                if (lval.compareTo(rval)==-1)
                    retTable.setValueAt(TRUE, i, 0);
                else
                    retTable.setValueAt(FALSE,i, 0);
            }
            else if (lTable.getColType(0).equals(DOUBLE)) {
                Double lval = (Double) lTable.getValueAt(i, 0);
            	Double rval = (Double) rTable.getValueAt(i, 0);
                // -1 means less than
                if (lval.compareTo(rval)==-1)
                    retTable.setValueAt(TRUE, i, 0);
                else
                    retTable.setValueAt(FALSE,i, 0);
            }
            else if (lTable.getColType(0).equals(INTEGER)) {
            	Integer lval = (Integer) lTable.getValueAt(i, 0);
            	Integer rval = (Integer) rTable.getValueAt(i, 0);
                // -1 means less than
                if (lval.compareTo(rval)==-1)
                    retTable.setValueAt(TRUE, i, 0);
                else
                    retTable.setValueAt(FALSE,i, 0);
            }
         }

        return retTable;
    }
}