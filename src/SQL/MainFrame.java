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

	public MainFrame() throws SQLException {
		super("SQL Database Helper");

		setLayout(new BorderLayout());

		toolbar = new Toolbar();
		textPanel = new TextPanel();
		formPanel = new FormPanel();
		create = new CreateTable();


		toolbar.setFormListener(new FormListener(){
			public void formEventOccurred(FormEvent e) {
				db = new Database();
				final String url= e.getUrl();
				final String user = e.getUser();
				final String password = e.getPass();
				try {
					db.createConnection(url, user, password);
					formPanel.setModelA(db.chooseTable());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		create.setFormListener(new FormListener() {
			public void formEventOccurred(FormEvent e) {
				try {
					db.setName(e.getName());
					db.setCol(e.getColumn());
					db.setDataType(e.getData());
					db.setNull(e.getNullField());
					db.createTable();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		create.setFormListener2(new FormListener() {
			public void formEventOccurred(FormEvent e) {
				try {
					db.setName(e.getName());
					db.setCol(e.getColumn());
					db.setDataType(e.getData());
					db.setNull(e.getNullField());
					db.addColumn();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		create.setFormListener3(new FormListener() {
			public void formEventOccurred(FormEvent e) {
				try {
					db.setName(e.getName());
					db.setCol(e.getPKey());
					db.addPrimaryKey();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		formPanel.setFormListener(new FormListener(){
			public void formEventOccurred(FormEvent e) {
				db.setTable(e.getTableName());
				try {
					formPanel.setModelA(db.chooseColumn());
				} catch (SQLException e1) {
					e1.printStackTrace();
				};
			}
		});

		formPanel.setFormListener5(new FormListener(){
			public void formEventOccurred(FormEvent e) {
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
						long date;

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
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});

		formPanel.setFormListener3(new FormListener(){
			public void formEventOccurred(FormEvent e) {
				try {
					formPanel.setModelA(db.chooseTable());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		formPanel.setFormListener2(new FormListener(){
			public void formEventOccurred(FormEvent e) {
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
					e1.printStackTrace();
				}



			}
		});

		formPanel.setFormListener4(new FormListener(){
			public void formEventOccurred(FormEvent e) {
				textPanel.clearText();
			}
		});

		add(formPanel, BorderLayout.WEST);
		add(textPanel, BorderLayout.CENTER);
		add(toolbar, BorderLayout.NORTH);
		add(create, BorderLayout.EAST);

		setSize(1400, 900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}

