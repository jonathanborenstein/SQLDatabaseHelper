package SQL;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Toolbar extends JPanel implements ActionListener {

	private JButton connect;
	
	private JLabel url;
	private JLabel user;
	private JLabel password;
	
	private JTextField urlText;
	private JTextField userText;
	private JTextField passwordText;
	
	private StringListener textListener;
	private StringListener textListener1;
	
	private FormListener formListener;
	
	public Toolbar() {
		setBorder(BorderFactory.createEtchedBorder());
		
		connect = new JButton("Connect");
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		url = new JLabel("Url");
		user = new JLabel("Username");
		password = new JLabel("Password");
		
		urlText = new JTextField(25);
		userText = new JTextField(25);
		passwordText = new JTextField(25);
		
		add(url);
		add(urlText);
		add(user);
		add(userText);
		add(password);
		add(passwordText);
		
		add(connect);
		
		connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String url = urlText.getText();
				String user = userText.getText();
				String password = passwordText.getText();
			
				
				FormEvent ev = new FormEvent(this, url, user, password);
				
				if(formListener != null) {
					formListener.formEventOccurred(ev);
				}
			}
		});
		
	}
	
	public void setFormListener(FormListener listener) {
		this.formListener = listener;
	}
	
	
	public void setStringListener(StringListener listener) {
		this.textListener = listener;
	}
	
	public void setStringListener1(StringListener listener) {
		this.textListener1 = listener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
