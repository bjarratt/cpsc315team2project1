# Overview #

## **class CompareOps** ##

The **CompareOps** class contains all of the functions that are used to perform comparison operations on **Table** objects and the rows/columns contained in them.

### Data Members ###

static final String TRUE    = "TRUE";  - all of these data members are used to provide arguments needed for the comparison functions

static final String FALSE   = "FALSE";

static final String STRING  = "STRING";

static final String DOUBLE  = "DOUBLE";

static final String INTEGER = "INTEGER";

static final String BOOLEAN = "BOOLEAN";

### Constructor Summary ###

None.

### Method Summary ###

static Table **and**(Table _lTable_, Table _rTable_) - Performs the AND operation of the SELECT query by creating a union of _lTable_ and _rTable_.

static Table **or**(Table _lTable_, Table _rTable_) - Performs the OR operation of the SELECT query by creating an intersection of _lTable and_rTable_._

static Table **not**(Table _table_) - Performs the NOT operation of the SELECT query by setting key values in _table_ to FALSE if they are TRUE, and vice versa.

static Table **notEqual**(Table _lTable_, Table _rTable_) - Performs the != operation of the SELECT query by comparing _lTable_ and _rTable_ and setting values as necessary.

static Table **equals**(Table _lTable_, Table _rTable_) - Performs the = operation of the SELECT query by comparing _lTable_ and _rTable_ and setting values as necessary.

static Table **lessEqual**(Table _lTable_, Table _rTable_) - Performs the <= operation of the SELECT query by comparing _lTable_ and _rTable_ and setting values as necessary.

static Table **greaterEqual**(Table _lTable_, Table _rTable_) - Performs the >= operation of the SELECT query by comparing _lTable_ and _rTable_ and setting values as necessary.

static Table **greaterThan**(Table _lTable_, Table _rTable_) - Performs the > operation of the SELECT query by comparing _lTable_ and _rTable_ and setting values as necessary.

static Table **lessThan**(Table _lTable_, Table _rTable_) - Performs the < operation of the SELECT query by comparing _lTable_ and _rTable_ and setting values as necessary.