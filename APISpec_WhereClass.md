# Overview #

## **class WhereClass** ##

class **WhereClass**

The WhereClass takes on the functionality of the SQL clause WHERE, which is used to extract only those columns that fulfill a specified set of conditions.  It makes use of **Table**, **SubQueryOperators**, **CompareOps**, and **HelperFunctions**.

### Data Members ###

final String **ctrlString** == "####" - Identifies which portions of the query have already been run.  If the input ever contains this string, the program will fail _epically_.

Vector< Table > **subQueries** - A Vector of **Table** objects that contains the subqueries that have already been performed; this is what **ctrlString** points to.

Table **myTable** - Used in the argument **Table** constructor to set an initial value.

### Constructor Summary ###

private **WhereClass**(Table _argTable_) - Makes **subQueries** and the _argTable_ global for full use by other classes.

### Method Summary ###

public static Table **where**(Table _argTable_, String _commands_) - The actual SQL WHERE clause, part of the SELECT-FROM-WHERE statement.  Sets the conditions under which **select**() and **from**() find columns in a **Table** by parsing the _commands_ string and calling **SubQueryOperators** functions accordingly.  Returns a **Table** meeting the conditions defined in the _commands_.

private Table **runOperator**(String _command_) - Parses the _command_ and runs the specified operation.

private String **getSubQuery**(String _command_) - Parses the _command_ and gets the appropriate **SubQueryOperator** function to be run by **runOperator**().