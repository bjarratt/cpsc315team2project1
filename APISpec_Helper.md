# Overview #

## **class HelperFunctions** ##

class **HelperFunctions**

The **HelperFunctions** class contains functions that convert a variable into a **Table** for use in the **WhereClass** and **SubQueryOperators** classes so that operations can be performed.

### Data Members ###

None.

### Constructor Summary ###

None.

### Method Summary ###

public static Table **convertToTable**(Table _myTable_, String _variable_) - Converts a given column name _variable_ into a new **Table** containing the values of that column.

public static Table **createTable**(String _type_, int _rows_, Object _variable_) - Creates a **Table** with initial value _variable_, cols=1, and the given number of rows _rows_.