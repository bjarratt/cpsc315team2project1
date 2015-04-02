# Introduction #
Parser Style Design
# Details #
Query passes to select, update, or delete function (returns whatever select, update, delete return) <br />

**SELECT** calls **FROM** function to get table, selects data, passes to **WHERE** (returns whatever where returns)<br>
<b>UPDATE</b> calls <b>FROM</b> function to get table, passes to <b>WHERE</b>, modifies entries. (pointers?) <br>
<b>DELETE</b>  calls <b>FROM</b> function to get table, passes to <b>WHERE</b>, deletes entries. (pointers?) <br>

<b>WHERE</b> will parse and pass to functions of these 3 categories: table operators, binary operators, comparison operators <br>

<b>table</b>: IN, EXISTS, ALL, ANY, these will call a <b>SELECT</b> <br>
<b>binary</b>: AND, OR, NOT <br>
<b>comparison</b>: =, !=, >, <, >=, <= <br>

Additional detail:<br>
<b>SELECT</b> needs to preform: AS, COUNT, MIN, MAX<br>
<br>
Design Difficulties/Flaws:<br>
Preforming the table operators without a <b>SELECT</b> call per row.