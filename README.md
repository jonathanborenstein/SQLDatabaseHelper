# SQLDatabaseHelper
SQL Database Helper

This is a program used to help modify and view tables in an SQL databse, although more features will be added shortly. At the 
moment you must call the orderBy method in order for the insert method to work, since the orderBy method returns the ResultSet that is used in subsequent methods.

An example to choose a table, order it, and insert a new row into a databse would look like this (as seen in Example.Java):
  
		Database db1 = new Database();
		
		final String url= "jdbc:postgresql://localhost/Tweets";
		final String user = "postgres";
		final String password = "postgres";
		
		db1.createConnection(url, user, password);
		
		db1.chooseTable();
		db1.chooseColumn(db1.getColumns());
		ResultSet order = db1.orderBy();
		db1.getColumnName(order);
		db1.getQuestionMarks(order);
		db1.insert();
		
At the moment you must create a ResultSet and call orderBy on the database, and then call the following methods in order for the insert method to work.
