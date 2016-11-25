package views;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.mindrot.jbcrypt.BCrypt;

import database.JavaDB;
import object.User;

public class Login extends JPanel {
	
	private JPanel main;
	private JLabel loginLabel, emailLabel, passLabel, regLabel;
	private JTextField emailField;
	private JPasswordField passField;
	private JButton loginButton, registerButton;
	private JavaDB db = new JavaDB("localhost", "root", "", "calendar");
	private String loginPassHashed;
	private GridBagConstraints gbc;
	private Window window;
	private User user;

	public Login(Window window) {
		user=null;
		this.window = window;
		
		main = new JPanel();
		main.setLayout(new GridBagLayout());
		main.setPreferredSize(new Dimension(1400,800));
		main.setVisible(true);
		this.add(main);
		
		gbc = new GridBagConstraints();
		
		// Login text
		loginLabel = new JLabel("Logga in här!", JLabel.CENTER);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		main.add(loginLabel, gbc);
		
		// Email
		emailLabel = new JLabel("Email: ");
		emailField = new JTextField(40);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		
		main.add(emailLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		
		main.add(emailField, gbc);
		
		// Password
		passLabel = new JLabel("Lösenord: ");
		passField = new JPasswordField(40);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		
		main.add(passLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		
		main.add(passField, gbc);
		
		// Login button
		loginButton = new JButton("Logga in!");
		
		gbc.gridx = 0;
		gbc.gridy = 5;
		
		main.add(loginButton, gbc);
		
		
		
		// Registration
		regLabel = new JLabel("Inget konto? Registrera dig här!", JLabel.CENTER);
		registerButton = new JButton("Registrera dig!");
		
		
		gbc.gridx = 0;
		gbc.gridy = 6;
		
		main.add(regLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 7;
		
		main.add(registerButton, gbc);

		ListenForButton lForButton = new ListenForButton();

		loginButton.addActionListener(lForButton);
		registerButton.addActionListener(lForButton);

		
		
		
		
	

	}

	private class ListenForButton implements ActionListener {

		// This method is called when an event occurs

		public void actionPerformed(ActionEvent e) {

			// Check if the source of the event was the button

			if (e.getSource() == loginButton) {
				String loginEmail = emailField.getText();
				Object[][] data = db.getData("select * from user where email = '" + loginEmail + "'");

				loginPassHashed = (String) data[0][2];

				char[] loginPassCandidate = passField.getPassword();
				if (BCrypt.checkpw(String.valueOf(loginPassCandidate), loginPassHashed)) {
					System.out.println("It matches");
					user = new User(Integer.parseInt((String) data[0][0]), (String) data[0][1], (String) data[0][3],
							(String) data[0][4], (String) data[0][5]);
					// user = new user (int,string,string,string);
					user.getAll();
					user.reloadarrays();
					window.getIndexPage();
				} else {
					System.out.println("It does not match");
				}
			}

			if (e.getSource() == registerButton) {
				window.getRegisterPage();
			}

		}

	}

}
