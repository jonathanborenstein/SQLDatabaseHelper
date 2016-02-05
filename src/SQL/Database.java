package SQL;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;




import javax.swing.JOptionPane;

public class Database {

	private DatabaseMetaData dbmd;
	private ResultSet tables;
	private ResultSet rs;
	private ResultSet columns;
	private String table;
	private String column;
	private PreparedStatement pst;
	private Connection con;


	public void createConnection(String url, String user, String password) throws SQLException
	{
		con = DriverManager.getConnection(url, user, password);
	}


	public void createTable(int columns) throws SQLException
	{
		String name = JOptionPane.showInputDialog("Enter a table name");
		String create = "";
		for (int i = 0; i < columns; i++){
			create = create + JOptionPane.showInputDialog("Enter a column name") + " " + 
					JOptionPane.showInputDialog("Enter a data type") + ", ";
		}
		int last = create.lastIndexOf(",");
		create = create.substring(0, last);
		create = "(" + create + ")";
		String sql = "CREATE TABLE IF NOT EXISTS " + name + " " + create;
		pst = con.prepareStatement(sql);
		pst.execute();
	}
	
	public void insert() throws SQLException, ParseException
	{
		String stm1 = "INSERT into " + table  + "(" + this.getColumnName(rs) + ") VALUES(" + this.returnMarks() + ")";
		pst = con.prepareStatement(stm1);
		this.getColumnTypeName(rs);
		pst.executeUpdate();
	}

	public ResultSet orderBy() throws SQLException
	{
		this.chooseTable();
		this.chooseColumn();
		Statement stmt = null;
		stmt = con.createStatement();
		String sql = "SELECT * FROM " + table + " ORDER BY " + column;
		rs = stmt.executeQuery(sql);
		return rs;
	}

	private String chooseTable() throws SQLException
	{
		String array1[] = new String[20];
		int i = 0;
		dbmd = con.getMetaData();
		tables = dbmd.getTables(null, null, "%", new String[] { "TABLE" });
		while (tables.next()) {
			String table = tables.getString("TABLE_NAME");
			array1[i] = table;
			i++;
		}
		table = (String) JOptionPane.showInputDialog(null,
				"Choose a table", "Input",
				JOptionPane.INFORMATION_MESSAGE, null,
				array1, array1[0]);
		return table;

	}

	private String chooseColumn() throws SQLException
	{
		this.getColumns();
		String array2[] = new String[columns.getMetaData().getColumnCount()];
		int i = 1;
		for (int j = 0; j < columns.getMetaData().getColumnCount(); j++)
		{
			array2[j] = columns.getMetaData().getColumnName(i);
			i++;

		}
		column = (String) JOptionPane.showInputDialog(null,
				"Choose a column to order by", "Input",
				JOptionPane.INFORMATION_MESSAGE, null,
				array2, array2[0]);

		return column;
	}

	private ResultSet getColumns() throws SQLException
	{
		Statement stmt = null;
		stmt = con.createStatement();
		String sql = "SELECT * FROM " + table;
		columns = stmt.executeQuery(sql);
		return columns;
	}

	private String returnMarks() throws SQLException
	{
		return this.getQuestionMarks(rs).toString()
				.replace("[", "")
				.replace("]", "")
				.trim(); 
	}

	private ArrayList<String> getQuestionMarks(ResultSet rs) throws SQLException
	{
		ArrayList<String> array3 = new ArrayList<String>();
		for(int i =0; i < rs.getMetaData().getColumnCount(); i++)
		{
			array3.add("?");
		}
		return array3;
	}

	private String getColumnName(ResultSet rs) throws SQLException
	{

		ArrayList<String> array2 = new ArrayList<String>();
		int j = 1;
		for(int i =0; i < rs.getMetaData().getColumnCount(); i++)
		{
			array2.add(rs.getMetaData().getColumnName(j));
			j++;
		}
		String format = array2.toString()
				.replace("[", "")
				.replace("]", "")
				.trim();  

		return format;
	}

	private void getColumnTypeName(ResultSet rs) throws SQLException, ParseException
	{
		int j = 1;
		String s = null;
		ArrayList<String> array4 = new ArrayList<String>();
		for(int i =0; i < rs.getMetaData().getColumnCount(); i++)
		{
			array4.add(rs.getMetaData().getColumnTypeName(j));
			System.out.println(array4.get(i));
			String type = array4.get(i);
			int a;
			double d;
			boolean b;

			switch (type) {
			case "int4": 
				s = JOptionPane.showInputDialog("Enter a number");
				a = Integer.parseInt(s);
				pst.setInt(j, a);
				break;

			case "varchar":
				s = JOptionPane.showInputDialog("Enter a string");
				pst.setString(j, s);
				break;

			case "bpchar":
				s = JOptionPane.showInputDialog("Enter a string");
				pst.setString(j, s);
				break;

			case "text":
				s = JOptionPane.showInputDialog("Enter a string");
				pst.setString(j, s);
				break;

			case "numeric": 
				s = JOptionPane.showInputDialog("Enter a number");
				a = Integer.parseInt(s);
				pst.setInt(j, a);
				break;

			case "bpnumeric": 
				s = JOptionPane.showInputDialog("Enter a number");
				a = Integer.parseInt(s);
				pst.setInt(j, a);
				break;

			case "float8": 
				s = JOptionPane.showInputDialog("Enter a double");
				d = Double.parseDouble(s);
				pst.setDouble(j, d);
				break;

			case "bool": 
				s = JOptionPane.showInputDialog("Enter true or false");
				b = Boolean.parseBoolean(s);
				pst.setBoolean(j, b);; 
				break;

			}
			j++;
		}
	}
}

