
import javax.swing.JTable;

class drewOps {

    String TRUE = "TRUE";
    String FALSE = "FALSE";

    // AND
    JTable and(JTable lTable, JTable rTable) {
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
    JTable or(JTable lTable, JTable rTable) {
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
    JTable not(JTable table) {
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
    JTable notEqual(JTable lTable, JTable rTable) {
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
    JTable equals(JTable lTable, JTable rTable) {
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

    JTable lessEqual(JTable lTable, JTable rTable) {
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

    JTable greaterEqual(JTable lTable, JTable rTable) {
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

    JTable greaterThan(JTable lTable, JTable rTable) {
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

    JTable lessThan(JTable lTable, JTable rTable) {
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