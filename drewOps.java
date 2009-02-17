
import javax.swing.JTable;

class drewOps {

    String TRUE = "TRUE";
    String FALSE = "FALSE";

    // AND
    JTable andOperator(JTable lTable, JTable rTable) {
        int colNums = lTable.getColumnCount();
        JTable retTable = new JTable(colNums, 1);
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
    JTable orOperator(JTable lTable, JTable rTable) {
        int colNums = lTable.getColumnCount();
        JTable retTable = new JTable(colNums, 1);
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
    JTable notOperator(JTable table) {
        int colNums = table.getColumnCount();
        JTable retTable = new JTable(colNums, 1);
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
    JTable notEqOp(JTable lTable, JTable rTable) {
        int colNums = lTable.getColumnCount();
        JTable retTable = new JTable(colNums, 1);
        for (int i = 0; i < colNums; i++) {
            Double lval = (Double) lTable.getValueAt(i, 0);
            Double rval = (Double) lTable.getValueAt(i,0);
            if (lval!=rval)
                retTable.setValueAt(TRUE, i, 0);
            else
                retTable.setValueAt(FALSE,i, 0);
         }


        return retTable;
    }

    // = equals
    JTable eqOp(JTable lTable, JTable rTable) {
        int colNums = lTable.getColumnCount();
        JTable retTable = new JTable(colNums, 1);
        for (int i=0; i<colNums; i++) {
            Double lval = (Double) lTable.getValueAt(i, 0);
            Double rval = (Double) rTable.getValueAt(i, 0);
            if (lval == rval)
                retTable.setValueAt(TRUE, i, 0);
            else
                retTable.setValueAt(FALSE, i, 0);
        }

        return retTable;
    }

    JTable lessEqOp(JTable lTable, JTable rTable) {
        int colNums = lTable.getColumnCount();
        JTable retTable = new JTable(colNums, 1);
        for (int i=0; i<colNums; i++) {
            Double lval = (Double) lTable.getValueAt(i, 0);
            Double rval = (Double) rTable.getValueAt(i, 0);
            if (lval <= rval)
                retTable.setValueAt(TRUE, i, 0);
            else
                retTable.setValueAt(FALSE, i, 0);
        }

        return retTable;
    }

    JTable greaterEqOp(JTable lTable, JTable rTable) {
        int colNums = lTable.getColumnCount();
        JTable retTable = new JTable(colNums, 1);
        for (int i=0; i<colNums; i++) {
            Double lval = (Double) lTable.getValueAt(i, 0);
            Double rval = (Double) rTable.getValueAt(i, 0);
            if (lval >= rval)
                retTable.setValueAt(TRUE, i, 0);
            else
                retTable.setValueAt(FALSE, i, 0);
        }

        return retTable;
    }

    JTable greaterThanOp(JTable lTable, JTable rTable) {
        int colNums = lTable.getColumnCount();
        JTable retTable = new JTable(colNums, 1);
        for (int i=0; i<colNums; i++) {
            Double lval = (Double) lTable.getValueAt(i, 0);
            Double rval = (Double) rTable.getValueAt(i, 0);
            if (lval > rval)
                retTable.setValueAt(TRUE, i, 0);
            else
                retTable.setValueAt(FALSE, i, 0);
        }

        return retTable;
    }

    JTable lessThanOp(JTable lTable, JTable rTable) {
        int colNums = lTable.getColumnCount();
        JTable retTable = new JTable(colNums, 1);
        for (int i=0; i<colNums; i++) {
            Double lval = (Double) lTable.getValueAt(i, 0);
            Double rval = (Double) rTable.getValueAt(i, 0);
            if (lval < rval)
                retTable.setValueAt(TRUE, i, 0);
            else
                retTable.setValueAt(FALSE, i, 0);
        }

        return retTable;
    }
}