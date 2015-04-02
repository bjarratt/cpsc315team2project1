# Overview #

## **class SubQueryOperators** ##

class **SubQueryOperators**

The SubQueryOperators class takes on the functionality of SQL subqueries EXISTS, ANY, and ALL.  It uses functions from the class **SingleValOps** to make boolean comparisons and determine if columns and/or rows exist in the **Table**.

### Data Members ###

None.

### Constructor Summary ###

None.

### Method Summary ###

public static Table **existsOp**(Table _myTable_, String _op_, String _query_) - Tests whether the inner query portion of _query_ returns any row based on the comparison made by _op_. If it does, then the outer query portion _query_ proceeds and a new **Table** that meets the _query_ conditions is returned.

public static Table **anyOp**(Table _myTable_, String _op_, String _query_) -  Works like the SQL ANY qualifier. The subquery generates a **Table** which consists of all columns in the specified **Table** that are greater, less than or equal to (depending on the type of _op_) another **Table**.

public static Table **allOp**(Table _myTable_, String _op_, String _query_) - Allows us to check if the condition from _query_ is realized for all the columns  of the **Table**, and returns a new **Table** satisfying these conditions.