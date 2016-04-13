package SQL;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Toolbar extends JPanel implements ActionListener {

	private JButton connect;

	private JLabel url;
	private JLabel user;
	private JLabel password;

	private JTextField urlText;
	private JTextField userText;

	private FormListener formListener;

	private JPasswordField passwordField;

	public Toolbar() {
		setBorder(BorderFactory.createEtchedBorder());

		connect = new JButton("Connect");

		setLayout(new FlowLayout(FlowLayout.LEFT));

		url = new JLabel("Url");
		user = new JLabel("Username");
		password = new JLabel("Password");

		urlText = new JTextField(25);
		userText = new JTextField(25);

		passwordField = new JPasswordField(25);


		add(url);
		add(urlText);
		add(user);
		add(userText);
		add(password);
		add(passwordField);
		add(connect);

		connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String url = urlText.getText();
				String user = userText.getText();
				char[] password = passwordField.getPassword();

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


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
