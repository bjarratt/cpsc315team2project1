SQL Database Project...Assignment details and instructions:

http://www.csdl.tamu.edu/~shipman/courses/cpsc315/team-project-1.pdf

UPDATE 09 Feb 2009:
New work done, design refining and outline of basic functions.

SELECT - picks a table to work with
> AS - renames whatever it returns (acts on name just before it)
> COUNT - counts all rows in the specified table
> MIN - finds the minimum value in a column
> MAX - finds the maximum value in a column

FROM - returns a reference to a specific table in the database

WHERE - sets the conditions for the current table
> functions that call SELECT on a table:
> EXISTS - determines if an element is in the current table
> ALL - all elements that meet a certain condition (using the comparison operators)
> ANY - any elements " (basically the or version of ALL)

> functions comparing two tables:
> AND, OR, NOT (or union, intersection and negation)
> plus standard comparisons =, !=, >, <, >=, <= for single cells as opposed to whole rows

CREATE - creates a new table
UPDATE - modifies elements in a table that meet a certain condition
DELETE - deletes all rows that meet a certain condition
IN - finds specific values in a column that are manually listed

for the full database  (JTable)
vector of table objects

and for each table vector(0) = column 0 of the JTable

use java.util.sets function if we want to use DISTINCT to remove duplicate elements

create a new table each time a query is performed