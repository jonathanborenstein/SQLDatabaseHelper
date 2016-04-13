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

import javax.swing.DefaultListModel;

public class Database {

	private DatabaseMetaData dbmd;
	private ResultSet tables;
	private ResultSet rs;
	private ResultSet columns;
	private String table;
	private String column;
	private String column1;
	private String nullField;
	private String dataType;
	private PreparedStatement pst;
	private Connection con;
	private String name;


	public void createConnection(String url, String user, String password) throws SQLException
	{
		con = DriverManager.getConnection(url, user, password);
	}

	public Connection getCon()
	{
		return con;
	}

	public void createTable() throws SQLException
	{
		String sql = "CREATE TABLE IF NOT EXISTS " + this.name + "(" + this.column1 + " " + this.dataType + " " + 
				this.nullField + ")";
		pst = con.prepareStatement(sql);
		System.out.println(sql);
		pst.execute();
	}

	public void addColumn() throws SQLException
	{
		String sql = "ALTER TABLE " + this.name + " ADD " + this.column1 + " " + this.dataType + " " + this.nullField;
		pst = con.prepareStatement(sql);
		System.out.println(sql);
		pst.execute();
	}

	public void addPrimaryKey() throws SQLException
	{
		String sql = "ALTER TABLE " + this.name + " ADD PRIMARY KEY(" + this.column1 + ")";
		pst = con.prepareStatement(sql);
		System.out.println(sql);
		pst.execute();
	}

	public String insert() throws SQLException, ParseException
	{
		String stm1 = "INSERT into " + table  + "(" + this.getColumnName(rs) + ") VALUES(" + this.returnMarks() + ")";
		System.out.println(stm1);
		return stm1;
	}

	public ResultSet orderBy() throws SQLException
	{
		Statement stmt = null;
		stmt = con.createStatement();
		String sql = "SELECT * FROM " + table + " ORDER BY " + column;
		System.out.println(sql);
		rs = stmt.executeQuery(sql);
		return rs;
	}

	public void setTable(String table)
	{
		this.table = table;
	}

	public void setColumn(String column)
	{
		this.column = column;
	} 

	public DefaultListModel chooseTable() throws SQLException
	{
		int i = 0;
		int j = 0;
		dbmd = con.getMetaData();
		tables = dbmd.getTables(null, null, "%", new String[] { "TABLE" });

		while (tables.next()) {
			j++;
		}

		DefaultListModel array1 = new DefaultListModel();

		tables = dbmd.getTables(null, null, "%", new String[] { "TABLE" });
		while (tables.next()) {
			String table = tables.getString("TABLE_NAME");
			array1.addElement(table);
			i++;
		}
		return array1;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDatatype()
	{
		return dataType;
	}

	public void setDataType(String dataType)
	{
		this.dataType = dataType;
	}

	public String getCol()
	{
		return column1;
	}

	public void setCol(String col)
	{
		this.column1 = col;
	}

	public String getNull()
	{
		return nullField;
	}

	public void setNull(String nullField)
	{
		this.nullField = nullField;
	}


	public DefaultListModel chooseColumn() throws SQLException
	{
		this.getColumns();
		String array2[] = new String[columns.getMetaData().getColumnCount()];
		int i = 1;

		DefaultListModel array1 = new DefaultListModel();

		for (int j = 0; j < columns.getMetaData().getColumnCount(); j++)
		{
			array1.addElement(columns.getMetaData().getColumnName(i));
			i++;

		}
		return array1;
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

	public ArrayList<String> getColumnTypeName() throws SQLException, ParseException
	{
		int j = 1;
		ArrayList<String> array4 = new ArrayList<String>();
		for(int i =0; i < rs.getMetaData().getColumnCount(); i++)
		{
			array4.add(rs.getMetaData().getColumnTypeName(j));
			j++;
		}
		return array4;
	}
}