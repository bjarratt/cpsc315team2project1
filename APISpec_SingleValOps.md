# Overview #

## **class SingleValOps** ##

The **SingleValOps** class contains all of the functions that are used to perform measurement operations on **Table** objects.

### Data Members ###

None.

### Constructor Summary ###

None.

### Method Summary ###

static Double **max**(Table _myTable_, String _colName_) - Performs the equivalent of the SQL MAX function, which returns the largest value of _colName_ from **Table** _myTable_.

static Double **min**(Table _myTable_, String _colName_) - Performs the equivalent of the SQL MIN function, which returns the smallest value of _colName_ from **Table** _myTable_.

static Double **count**(Table _myTable_, String _colName_) - Performs the equivalent of the SQL MAX function, which returns the number of rows in _myTable_ if it has a column called _colName_.

static Double **sum**(Table _myTable_, String _colName_) - Performs the equivalent of the SQL SUM function, which returns the total sum of a numeric column _colName_ from **Table** _myTable_.