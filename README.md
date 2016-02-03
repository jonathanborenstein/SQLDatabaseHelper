# SQLDatabaseHelper
SQL Database Helper

This is a program used to help modify and view tables in an SQL databse, although more features will be added shortly.

An example to choose a table, order it, and insert a new row into a databse would look like this (as seen in Example.java):
  
		Database db1 = new Database();
		
		final String url= "jdbc:postgresql://localhost/Tweets";
		final String user = "postgres";
		final String password = "postgres";
		
		db1.createConnection(url, user, password);
		
		//orderBy method will allow you too choose column, table, and will order by column chosen
		db1.orderBy();
		db1.insert();
		
		//Choose a new table
		db1.orderBy();
		//set for loop to insert multiple entries into the table
		for (int i =0; i < 3; i++){
		db1.insert();
		}
		
The only method you must call is orderBy in order to choose a table and column (to order by). The other methods are now private to the Database class.
