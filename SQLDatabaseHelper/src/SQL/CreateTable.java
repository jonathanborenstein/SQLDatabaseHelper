package SQL;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class CreateTable extends JPanel {

	private JLabel name;
	private JLabel dataType;
	private JLabel column;
	private JLabel nullLabel;

	private JLabel dataType2;
	private JLabel column2;
	private JLabel nullLabel2;

	private JTextField nameFieldB;
	private JTextField dataTypeField;
	private JTextField dataTypeField2;
	private JTextField columnField;
	private JTextField columnField2;
	private JTextField nullField;
	private JTextField nullField2;

	private JButton createBtn;
	private JButton clearBtn;

	private FormListener formListener;
	private FormListener formListener2;
	private FormListener formListener3;

	private JButton addColumn;
	private JLabel name2;
	private JTextField nameFieldC;

	private JLabel name3;
	private JTextField nameFieldD;
	private JLabel pKeyLabel;
	private JTextField pKeyField;
	private JButton pKeyBtn;



	public CreateTable() {
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);
		setLayout(new FlowLayout());

		name = new JLabel("Table Name: ");
		name2 = new JLabel(" Table Name: ");
		name3 = new JLabel("    Table Name: ");
		dataType= new JLabel("Datatype: ");
		column = new JLabel("Column: ");
		nullLabel = new JLabel("Null or Not Null: "); 

		dataType2= new JLabel("Datatype: ");
		column2 = new JLabel("Column: ");
		nullLabel2 = new JLabel("Null or Not Null: "); 

		pKeyLabel = new JLabel("   Primary Key: ");
		pKeyField = new JTextField(15);
		pKeyBtn = new JButton("Add Primary Key");


		nameFieldB = new JTextField(15);
		nameFieldC = new JTextField(15);
		nameFieldD = new JTextField(15);
		dataTypeField = new JTextField(15);
		columnField = new JTextField(15);
		nullField = new JTextField(15);

		dataTypeField2 = new JTextField(15);
		columnField2 = new JTextField(15);
		nullField2 = new JTextField(15);


		createBtn = new JButton("Create Table");
		addColumn = new JButton("Add Column");
		clearBtn = new JButton("Clear Fields");


		createBtn.addActionListener(e -> {
			String name = nameFieldB.getText();
			String column = columnField.getText();
			String data = dataTypeField.getText();
			String nullF = nullField.getText();

			FormEvent ev = new FormEvent(this, name, column, data, nullF);

			if(formListener != null) {
				formListener.formEventOccurred(ev);
			}
		});

		clearBtn.addActionListener(e -> {
			nameFieldB.setText("");
			columnField.setText("");
			dataTypeField.setText("");
			nullField.setText("");
			nameFieldC.setText("");
			columnField2.setText("");
			dataTypeField2.setText("");
			nullField2.setText("");
			pKeyField.setText("");
			nameFieldD.setText("");

		});

		addColumn.addActionListener(e -> {
			String name = nameFieldC.getText();
			String column= columnField2.getText();
			String data = dataTypeField2.getText();
			String nullF = nullField2.getText();

			FormEvent ev = new FormEvent(this, name, column, data, nullF);

			if(formListener2 != null) {
				formListener2.formEventOccurred(ev);
			}
		});

		pKeyBtn.addActionListener(e -> {
				String name = nameFieldD.getText();
				String pKey = pKeyField.getText();

				FormEvent ev = new FormEvent(this, name, pKey);

				if(formListener3 != null) {
					formListener3.formEventOccurred(ev);
				}

		});


		Border innerBorder = BorderFactory.createTitledBorder("Create Table");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));


		add(name);
		add(nameFieldB);
		add(column);
		add(columnField);
		add(dataType);
		add(dataTypeField);
		add(nullLabel);
		add(nullField);
		add(createBtn);
		add(name2);
		add(nameFieldC);
		add(column2);
		add(columnField2);
		add(dataType2);
		add(dataTypeField2);
		add(nullLabel2);
		add(nullField2);
		add(addColumn);
		add(name3);
		add(nameFieldD);
		add(pKeyLabel);
		add(pKeyField);
		add(pKeyBtn);
		add(clearBtn);

	}

	public void setFormListener(FormListener listener) {
		this.formListener = listener;
	}

	public void setFormListener2(FormListener listener) {
		this.formListener2 = listener;
	}

	public void setFormListener3(FormListener listener) {
		this.formListener3 = listener;
	}

}