# SQLDatabaseHelper
SQL Database Helper

One of the main benefits of this program is that you don't need to understand SQL syntax in order to connect to a Database and view it, add tables, update it, etc. The program also allows you to insert entries into tables without having to know how many columns or column names there are before hand.

The program has been updated and now features a GUI. The program is still a work in progress, although the same functionality will exist as I update it.

Right now all you have to do is clone/download the project, connect to a database, and you will see a Create Table panel on the right side, and an add column feature below it. On the left side you will see a list of tables which you can select and then display the table or insert entries into the table.

One thing that is needed is a JDBC driver specific to the Database you use in order to form a connection. Just add a driver to the build path. These can easily be found through a Google search if you don't have one already.

The program currently has these functions (although more will be added shortly):

Connect Button: Provides a connection to your database.
Select Table Button: Allows you to select a table from the database, which then can be viewed in the TextPanel via the Display Table button.

Insert Button: Once a table is selected, you can use this function to insert a new entry into a specific table in your database. You do not need to know the column names or datatypes or SQL syntax, as this is provided for you.

Create Table Panel provides an area to Create a Table, Add a Column to the table, and to Add A Primary Key.

Refresh Table Button: This will show you all the tables in your Database.

You must use the Select Table button (which will then display the columns) first in order to use Display Table or the Insert function.

More functions such as adding foreign keys, updating rows, or doing a manual SQL query will be added shortly.
