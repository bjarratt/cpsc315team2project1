
import javax.swing.JTable;

class compareOps {

    static final String TRUE = "TRUE";
    static final String FALSE = "FALSE";

    // AND
    static JTable and(JTable lTable, JTable rTable) {
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
    static JTable or(JTable lTable, JTable rTable) {
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
    static JTable not(JTable table) {
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
    static JTable notEqual(JTable lTable, JTable rTable) {
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
    static JTable equals(JTable lTable, JTable rTable) {
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

    static JTable lessEqual(JTable lTable, JTable rTable) {
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

    static JTable greaterEqual(JTable lTable, JTable rTable) {
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

    static JTable greaterThan(JTable lTable, JTable rTable) {
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

    static JTable lessThan(JTable lTable, JTable rTable) {
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