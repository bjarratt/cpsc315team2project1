# Introduction #

Post any questions that someone else might be able to address if a particular problem is giving you trouble. Probably best in

<u>Q:</u><br>
<u>A:</u>

format.<br>
<br>
<br>
<h1>Details</h1>

<u>Q: Gabriel</u>

running into an issue with programming the where function.  Could I<br>
send any given operator two single column JTables and expect a return<br>
of a single column JTable containing "true, false, true, false, true,<br>
true" etc. Example for '>': Query: "number>3"<br>
<br>
LColumn <br>
5 <br>
7 <br>
1 <br>
3 <br>
9 <br>
10 <br>

RColumn <br>
3 <br>
3 <br>
3 <br>
3 <br>
3 <br>
3 <br>

Return: <br>
true <br>
true <br>
false <br>
false <br>
true <br>
true <br>

<u>A: Drew</u>

As opposed to just <b>edit: rows</b> where the number is greater than 3?<br>
<blockquote><br>
Response: Gabriel<br>
Yes, because the response from a given operator will be passed on to the next operator in the sequence.  If one of the next operators happen to be "OR" then the false could become true.  (This also helps the NOT case since NOT merely has to invert true to false and vice-versa).  The response JTable can be either string or boolean, for now I will assume "string."<br></blockquote>

<u>Q: Drew</u>

Because we apparently can't set the names of the columns or extract the types in cells, do we want to do something like this?<br>
<br>
<pre><code>class myTable extends JTable {<br>
     Vector&lt;String&gt; colNames;<br>
     Vector&lt;String&gt; colTypes;<br>
     // maybe more nice things here?<br>
}<br>
</code></pre>


<u>Q: Drew</u>

We need a constructor for table. Does anyone know how to go about doing this? I'm not sure how it's done with a subclass.<br>
<br>
<u>A: Gabriel</u>
Add these two lines to table:<br>
<pre><code>    public table () {super();}<br>
    public table (int x, int y) {super(x,y);}<br>
</code></pre>
Alternately these allow the use of colTypes/colNames as well:<br>
<pre><code>    public table () {super();colTypes=new Vector&lt;String&gt;();colNames=new Vector&lt;String&gt;();}<br>
    public table (int x, int y) {super(x,y);colTypes=new Vector&lt;String&gt;();colNames=new Vector&lt;String&gt;();}<br>
</code></pre>

<u>Q: Gabriel</u>
Missing MANY functions on work assignments:<br>
<a href='http://www.1keydata.com/sql/sqlcreate.html'>http://www.1keydata.com/sql/sqlcreate.html</a>
<pre><code>CREATE TABLE "table_name"<br>
("column 1" "data_type_for_column_1",<br>
"column 2" "data_type_for_column_2",<br>
... )<br>
</code></pre>
<a href='http://www.1keydata.com/sql/sqlinsert.html'>http://www.1keydata.com/sql/sqlinsert.html</a>
<pre><code>INSERT INTO "table_name" ("column1", "column2", ...)<br>
VALUES ("value1", "value2", ...)<br>
</code></pre>
SUM, COUNT, MIN, MAX<br>
<br>
<u>Q: Gabriel</u>
Should the where() function modify it's input?  currently it modifies the table object it's passed so that "retTable where(argTable, query)" retTable==argTable.