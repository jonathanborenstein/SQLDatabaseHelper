package SQL;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Example {

	public static void main(String[] args) throws SQLException {

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
		
		db1.chooseTable();
		db1.chooseColumn(db1.getColumns());
		order = db1.orderBy();
		db1.getColumnName(order);
		db1.getQuestionMarks(order);
		db1.insert();
		
		int i = 1;
		while (order.next()){
			String id  = order.getString(order.getMetaData().getColumnName(i));
			String first = order.getString(order.getMetaData().getColumnName(i+1));
			System.out.print(System.lineSeparator() + "ID: " + id + System.lineSeparator() + "First: " + first + 
					System.lineSeparator());

		}

	}
}





