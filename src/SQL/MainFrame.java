package SQL;

import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JFrame;

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

		formPanel.setFormListener2(new FormListener(){
			public void formEventOccurred(FormEvent e) {
				try {
					db.setColumn(e.getTableName());
					ResultSet order = db.orderBy();
					System.out.println(db.getType());
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

		formPanel.setFormListener3(new FormListener(){
			public void formEventOccurred(FormEvent e) {
				try {
					formPanel.setModelA(db.chooseTable());
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

		setSize(1400, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}

