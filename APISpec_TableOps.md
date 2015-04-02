# Overview #

## **class TableOps** ##

The **TableOps** class contains all of the functions that are used to perform operations on **Table** objects, as well as the functions analogous to SQL SELECT and FROM.

### Data Members ###

static ArrayList< Table > **db** - Functions as the database in which we will store and retrieve all of our **Table** objects.

### Constructor Summary ###

None.

### Method Summary ###

public static Table **from**(ArrayList< Table > _database_, String _tableNames_) - Works like the SQL FROM statement.  Returns one or more **Table** objects from the _database_.

public static Table **select**(String _query_) - Parses the _query_ into a table name portion, a database portion, and a conditions portion.  Selects a column (or columns) from a **Table** returned by **from**() from the database that matches the conditions portion of _query_, implemented in **where**().

public static String **selectAs**(String _query_, Table _table_) - Parses the _query_ to see if the statement AS exists; if it does, it renames the columns chosen by **select**() as the strings passed in the second part of _query_ and adds them to the **Table**.

public static void **update**(String _query_) - Modifies columns in the selected **Table** that are specified by _query_.

public static void **delete**(String _query_) - Deletes columns in the selected **Table** that are specified by _query_.

public static void **drop**(String _query_) - Removes **Table** objects from the database that are specified by _query_.

public static void **create**(String _query_) - Creates a new **Table** with the values and types specified by _query_ and adds it to the database.

public static Table **insert**(String _query_) - Finds the **Table** specified by _query_, checks the types of values to be inserted, and adds values into the rows specified by _query_.

public static Table **in**(Table _table_, String _query_) - Works like the SQL IN operator.  Checks for items in **Table** that match the values specified in _query_ and returns a **Table** containing those items if the do exist.