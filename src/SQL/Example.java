package SQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;


public class Example {

	public static void main(String[] args) throws SQLException, ParseException {

		Database db1 = new Database();
		
		final String url= "jdbc:postgresql://localhost/Tweets";
		final String user = "postgres";
		final String password = "postgres";
		
		db1.createConnection(url, user, password);
		
		//orderBy method will allow you too choose column, table, and will order by column chosen
		db1.orderBy();
		db1.insert();
		
		//Choose a new table
		//Create a new ResultSet if you want to print the data as shown below
		ResultSet order = db1.orderBy();
		//set for loop to insert multiple entries into the table
		for (int i =0; i < 3; i++){
		db1.insert();
		}

		
		int i = 1;
		while (order.next()){
			String id  = order.getString(order.getMetaData().getColumnName(i));
			String first = order.getString(order.getMetaData().getColumnName(i+1));
			System.out.print(System.lineSeparator() + "ID: " + id + System.lineSeparator() + "First: " + first + 
					System.lineSeparator());

		}

	}
}





