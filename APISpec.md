# Overview #

## **class Table** ##

public class **Table**

The Table class is used to display and edit regular two-dimensional tables of cells.  It is composed as a vector of Vector< Object > and includes private vectors for storage of column names and column types, as well as a string **tableName**.

### Data Members ###

private Vector< Vector< Object >> **table** - The **Table** object itself, a Vector of Vectors of type Object.

private Vector< String > **colTypes** - Stores the column types in the **Table**.

private Vector< String > **colNames** - Stores the column names in the **Table**.

private String **tableName** - The name of the **Table**.

### Constructor Summary ###

**Table**() - Constructs a default **Table** that is initialized with a default data model, a default column model, and a default selection model.

**Table**(Table _oldTable_) - The copy constructor for **Table** that creates an exact copy of _oldTable_.

**Table**(String _name_, Table _lTable_, Table _rTable_) - Analogous to the **SQL JOIN** operation, this constructor takes in a string (the table name) and two other tables and constructs a new table called _name_ that is composed of the joined _lTable_ and _rTable_.

**Table**(String _name_, int _rows_, int _columns_) - Constructs a **Table** called _name_ with _rows_ number of rows and _columns_ number of columns.

**Table**(int _rows_, int _columns_) - Constructs an unnamed **Table** with _rows_ number of rows and _columns_ number of columns.

**Table**(String _name_, Vector< String > _columnNames_, Vector< String > _columnTypes_) - Constructs a **Table** called _name_ with one vector of _columnNames_ and another of _columnTypes_.


### Method Summary ###

Object **getValueAt**(int _row_, int _column_) - Gets the row _row_ and the column _column_ from the **Table**.

void **setValueAt**(Object _value_, int _row_, int _column_) - Sets the value _value_ at the specified row _row_ and column _column_.

int **getRowCount**() - Returns the number of rows in the **Table**.

int **getColumnCount**() - Returns the number of columns in the **Table**.

int **colWithName**(String _name_) - Finds a column in the **Table** with a name matching _name_.

void **setColName**(int _index_, String _name_) - Sets the column name at _index_ of **Table** to _name_.

String **getColName**(int _index_) - Returns the column name at _index_ of **Table**.

void **addRow**(Vector< Object > _stuff_) - Adds a new row (a Vector of Objects called _stuff_) to the **Table**.

Vector< Object > **getRow**(int _index_) - Returns the row located at _index_ of **Table**.

void **removeRow**(int _index_) - Removes the row located at _index_ of **Table**.

int **getRowIndex**(Object _o_, int _colIndex_) - Finds the index of row _o_ in a column at _colIndex_ in **Table**.

void **removeColumn**(int _index_) - Removes a column at _index_ of **Table**.

public void **addColumns**(Table _newCols_) - Adds columns into the new **Table** _newCols_.

public Table **getColumn**(int _index_) - finds the column at _index_ of **Table** and returns a new **Table** consisting of a single column with a _columnType_ and _columnName_.

public void **setName**(String _newName_) - Sets the name of the **Table** to _newName_.

public String **getName**() - Returns the name of the **Table**.