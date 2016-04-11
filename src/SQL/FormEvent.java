package SQL;

import java.util.EventObject;

public class FormEvent extends EventObject {
	
	private String name;
	private String column;
	private String data;
	private String nullField;
	private String tableName;
	
	private String url;
	private String password;
	private String user;
	
	private char[] pass;
	
	public FormEvent(Object source) {
		super(source);
	}
	
	public FormEvent(Object source, String tableName) {
		super(source);
		this.tableName = tableName;
	}
	
	public FormEvent(Object source, String url, String username, char[] pass) {
		super(source);
		this.url = url;
		this.user = username;
		this.pass = pass;
	}
	
	public FormEvent(Object source, String name, String column, String data, String nullField) {
		super(source);
		
		this.name = name;
		this.column = column;
		this.data = data;
		this.nullField = nullField;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPassword() {
		return password;
	}
	
	public String getPass() {
		String str = "";
		for (int i =0; i <pass.length; i++)
		{
			str = str + pass[i];
		}
		return str;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getNullField() {
		return nullField;
	}

	public void setNullField(String nullField) {
		this.nullField = nullField;
	}

	
}
