package views;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.mindrot.jbcrypt.BCrypt;

import database.JavaDB;
import object.User;

public class Login extends JPanel {

	private JLabel loginLabel, emailLabel, passLabel, regLabel;
	private JTextField emailField;
	private JPasswordField passField;
	private JButton loginButton, registerButton;
	private JavaDB db = new JavaDB("localhost", "root", "", "calendar");
	private String loginPassHashed;
	private Window window;
	private User user;

	public Login(Window window) {
		this.window = window;
		// setLayout(new GridLayout(6,1));
		// Login text
		loginLabel = new JLabel("Logga in här!", JLabel.CENTER);
		// Email
		emailLabel = new JLabel("Email: ");
		emailField = new JTextField(40);
		// Password
		passLabel = new JLabel("Lösenord: ");
		passField = new JPasswordField(40);
		// Login button
		loginButton = new JButton("Logga in!");
		// Registration
		regLabel = new JLabel("Inget konto? Registrera dig här!", JLabel.CENTER);
		registerButton = new JButton("Registrera dig!");

		ListenForButton lForButton = new ListenForButton();

		loginButton.addActionListener(lForButton);
		registerButton.addActionListener(lForButton);

		add(loginLabel);
		add(emailLabel);
		add(emailField);
		add(passLabel);
		add(passField);
		add(loginButton);
		add(regLabel);
		add(registerButton);

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
