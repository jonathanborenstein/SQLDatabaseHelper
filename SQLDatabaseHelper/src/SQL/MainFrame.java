package SQL;

import java.awt.BorderLayout;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MainFrame extends JFrame {

	private TextPanel textPanel;
	private Toolbar toolbar;
	private FormPanel formPanel;
	private CreateTable create;
	private Database db;

	public MainFrame() {
		super("SQL Database Helper");

		setLayout(new BorderLayout());

		toolbar = new Toolbar();
		textPanel = new TextPanel();
		formPanel = new FormPanel();
		create = new CreateTable();


		toolbar.setFormListener(e -> {
			db = new Database();
			final String url= e.getUrl();
			final String user = e.getUser();
			final String password = e.getPass();

			try {
				db.createConnection(url, user, password);
				formPanel.setModelA(db.chooseTable());
			} catch (SQLException e1) {
				System.out.println(e1.getMessage());
			}


		});

		create.setFormListener(e -> {
			db.setName(e.getName());
			db.setCol(e.getColumn());
			db.setDataType(e.getData());
			db.setNull(e.getNullField());
			try {
				db.createTable();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		create.setFormListener2(e -> {
			db.setName(e.getName());
			db.setCol(e.getColumn());
			db.setDataType(e.getData());
			db.setNull(e.getNullField());
			try {
				db.addColumn();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		});

		create.setFormListener3(e -> {
			try {
				db.setName(e.getName());
				db.setCol(e.getPKey());
				db.addPrimaryKey();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});

		formPanel.setFormListener(e -> {
			db.setTable(e.getTableName());
			try {
				formPanel.setModelA(db.chooseColumn());
			} catch (SQLException e1) {
				System.out.println("Please Refresh Table List, Select Table, and Then Display Table");
			};
		});

		formPanel.setFormListener5(e -> {
			try {
				db.setColumn(e.getTableName());
				ResultSet order = db.orderBy();
				ArrayList<String> array = db.getColumnTypeName();
				PreparedStatement pst;
				pst = db.getCon().prepareStatement(db.insert());
				String s;
				int j = 1;
				for (int i = 0; i < array.size(); i++)
				{
					System.out.println(array.get(i));
					String type = array.get(i);
					int a;
					double d;
					boolean b;

					switch (type) {
					case "INT": 
						s = JOptionPane.showInputDialog("Enter a " + type);
						a = Integer.parseInt(s);
						pst.setInt(j, a);
						break;

					case "FLOAT": 
						s = JOptionPane.showInputDialog("Enter a number");
						a = Integer.parseInt(s);
						pst.setFloat(j, a);
						break;

					case "DOUBLE": 
						s = JOptionPane.showInputDialog("Enter a double");
						d = Double.parseDouble(s);
						pst.setDouble(j, d);
						break;

					case "DATE": 
						s = JOptionPane.showInputDialog("Enter a date");
						pst.setDate(j, java.sql.Date.valueOf(s));
						break;

					case "TINYINT": 
						s = JOptionPane.showInputDialog("Enter true or false");
						b = Boolean.parseBoolean(s);
						pst.setBoolean(j, b);; 
						break;

					case "BIT": 
						s = JOptionPane.showInputDialog("Enter true or false");
						b = Boolean.parseBoolean(s);
						pst.setBoolean(j, b);; 
						break;

					case "VARCHAR":
						s = JOptionPane.showInputDialog("Enter a " + type);
						pst.setString(j, s);
						break;

					case "LONGVARCHAR":
						s = JOptionPane.showInputDialog("Enter a string");
						pst.setString(j, s);
						break;

					case "CHAR":
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
					}
					j++;			
				}
				pst.executeUpdate();
			} 
			catch (Exception e2){
				System.out.println("Not Allowed!");
			}
			finally{
				try {
					formPanel.setModelA(db.chooseTable());
				} catch (Exception e1) {
				}

			}
		});

		formPanel.setFormListener3(e -> { 
			try {
				formPanel.setModelA(db.chooseTable());
			} catch (Exception e1) {

			}
		});

		formPanel.setFormListener2(e -> {
			try {
				db.setColumn(e.getTableName());
				ResultSet order = db.orderBy();
				while (order.next()){
					String row = "";
					for (int i = 1; i <= order.getMetaData().getColumnCount(); i++) {
						row += order.getString(i) + ", ";     
					}
					textPanel.appendText(row + System.lineSeparator());
				}
			} catch (SQLException e1) {
				try {
					formPanel.setModelA(db.chooseTable());
				} catch (Exception e2) {
				}

			}

		});

		formPanel.setFormListener4(e ->
		textPanel.clearText());

		add(formPanel, BorderLayout.WEST);
		add(textPanel, BorderLayout.CENTER);
		add(toolbar, BorderLayout.NORTH);
		add(create, BorderLayout.EAST);

		setSize(1400, 900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}

