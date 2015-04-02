## Overview ##
### Input ###
where(JTable table, String query)<br>

The query portion is in bold for:<br>
<code>SELECT *</code><br>
<code>  FROM myTable</code><br>
<code> WHERE </code><b>a=5;</b>

<h3>Output</h3>
Modified version of the JTable containing only the rows specified by the query<br>
<br>
<h2>To Implement</h2>
Support for more than one JTable.  Array? Vector? Another argument?<br>
Operators, need names for the operators, currently commented out<br>
Qualified variable names: <b>table.a</b> is unsupported<br>

<h2>Known Bugs</h2>
none, class is completely <b>untested</b>.