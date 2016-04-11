package SQL;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;


public class FormPanel extends JPanel {
	
	private FormListener formListener1;
	private FormListener formListener;
	private FormListener formListener2;
	private FormListener formListener3;
	
	
	private JList tableList;
	private JButton insertBtn;
	private JButton insert2Btn;
	private JButton refreshBtn;
	private JList combo;
	
	
	public FormPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 300;
		setPreferredSize(dim);
		
	
		tableList = new JList();
		combo = new JList();
		
		tableList.setPreferredSize(new Dimension(100, 400));
		tableList.setBorder(BorderFactory.createEtchedBorder());
		tableList.setSelectedIndex(1);
		
		combo.setPreferredSize(new Dimension(100, 400));
		combo.setBorder(BorderFactory.createEtchedBorder());
		combo.setSelectedIndex(1);
		
		
		insertBtn = new JButton("Select Table");
		insert2Btn = new JButton("Display Table");
		refreshBtn = new JButton("Refresh");
		
		
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tableName = (String) tableList.getSelectedValue();
			
				
				FormEvent ev = new FormEvent(this, tableName);
				
				if(formListener != null) {
					formListener.formEventOccurred(ev);
				}
			}
		});
		
		insert2Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tableName = (String) tableList.getSelectedValue();
			

				FormEvent ev = new FormEvent(this, tableName);
				
				if(formListener2 != null) {
					formListener2.formEventOccurred(ev);
				}
			}
		});
		
		refreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tableName = (String) combo.getSelectedValue();
			

				FormEvent ev = new FormEvent(this, tableName);
				
				if(formListener3 != null) {
					formListener3.formEventOccurred(ev);
				}
			}
		});
		
		Border innerBorder = BorderFactory.createTitledBorder("Insert Into Table");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		setLayout(new FlowLayout());
		add(new JScrollPane(tableList));
		add(insertBtn);
		add(insert2Btn);
		add(refreshBtn);
		//add(combo);
		
		

	}
	
	public void setModelA(DefaultListModel model)
	{
		tableList.setModel(model);
	}
	
	public void setModelB(DefaultListModel model)
	{
		combo.setModel(model);
	}
	
	public void setFormListener(FormListener listener) {
		this.formListener = listener;
	}

	public void setFormListener1(FormListener listener) {
		this.formListener1 = listener;
		
	}
	public void setFormListener2(FormListener listener) {
		this.formListener2 = listener;
		
	}

	public void setFormListener3(FormListener listener) {
		this.formListener3 = listener;
		
	}
	
	
}