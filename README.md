# read-write-file-jdbc-oracle
This program reads data from a file (.txt) and insert them to Oracle database. Also, it retrieves data from Oracle database and writes data to a file(.txt)

steps for connection java to oracle with jdbc
1. Load the driver: 
Class.forName("oracle.jdbc.driver.OracleDriver");
Be sure to import all jar files for oracle driver

2. Establish a connection to oracle database: 
Coonection con = DriverManager.getConnection(String url, String user, String password)
url - a database url of the form jdbc:subprotocol:subname
user - the database user on whose behalf the connection is being made
password - the user's password

official doc: https://docs.oracle.com/javase/8/docs/api/java/sql/DriverManager.html#getConnection-java.lang.String-java.lang.String-java.lang.String-

3. create a statement:
Statement stmt = con.createStatement();

Official doc: https://docs.oracle.com/javase/7/docs/api/java/sql/Statement.html

4. Execute a Query:
String sqlQuery = "SELECT * FROM EMPLOYEES"
ResultSet rs = stmt.executeQuery(sqlQuery);

5. Translate the data from result set
 table of data representing a database result set, which is usually generated by executing a statement that queries the database.
A ResultSet object maintains a cursor pointing to its current row of data. 
Initially the cursor is positioned before the first row. 
The next method moves the cursor to the next row, and because it returns false when there are no more rows in the ResultSet object, it can be used in a while loop to iterate through the result set.

official doc: https://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html

and YOU ARE DONE :) 

  