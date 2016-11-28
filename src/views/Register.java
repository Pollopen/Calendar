package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.mindrot.jbcrypt.BCrypt;

import database.JavaDB;

public class Register extends JPanel {

	private JPanel main, form;
	private JLabel regHere, emailLabel, passLabel, passConfLabel, fnameLabel, snameLabel;
	private JTextField emailField, fnameField, snameField;
	private JPasswordField passField, passConfField;
	private JButton regButton;
	private JavaDB db = new JavaDB();
	private GridBagConstraints gbc;
	private int user_id;
	private Window window;

	public Register(Window window) {
		this.window = window;
		
		gbc = new GridBagConstraints();
		
		main = new JPanel();
		main.setPreferredSize(new Dimension(1400, 800));
		main.setLayout(new GridBagLayout());
		
		this.add(main);
		
		form = new JPanel();
		form.setLayout(new GridBagLayout());
		form.setPreferredSize(new Dimension(480,600));
		form.setBorder(BorderFactory.createLineBorder(Color.black));
		form.setVisible(true);
		
		main.add(form);

		
		
		
		// Stor text at top
		regHere = new JLabel("Registrera dig här!", JLabel.CENTER);
		regHere.setFont(new Font("Serif", Font.PLAIN, 25));
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 75, 0);
		
		form.add(regHere, gbc);
		
		// Email
		emailLabel = new JLabel("Email: ");
		emailLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		emailField = new JTextField();
		emailField.setPreferredSize(new Dimension(300, 30));
		
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(0, 0, 10, 0);

		form.add(emailLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.insets = new Insets(0, 0, 10, 0);
		
		form.add(emailField, gbc);
		
		// Password + password confirmation
		passLabel = new JLabel("Lösenord: ");
		passLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		passField = new JPasswordField();
		passField.setPreferredSize(new Dimension(300, 30));
		passConfLabel = new JLabel("Lösenord igen: ");
		passConfLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		passConfField = new JPasswordField();
		passConfField.setPreferredSize(new Dimension(300, 30));
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.insets = new Insets(0, 0, 10, 0);

		form.add(passLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.insets = new Insets(0, 0, 10, 0);
		
		form.add(passField, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.insets = new Insets(0, 0, 10, 0);
		
		form.add(passConfLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.insets = new Insets(0, 0, 10, 0);
		
		form.add(passConfField, gbc);
		
		// First name + last name
		fnameLabel = new JLabel("Förnamn: ");
		fnameLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		fnameField = new JTextField();
		fnameField.setPreferredSize(new Dimension(300, 30));
		snameLabel = new JLabel("Efternamn: ");
		snameLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		snameField = new JTextField();
		snameField.setPreferredSize(new Dimension(300, 30));
		
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.insets = new Insets(0, 0, 10, 0);

		form.add(fnameLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.insets = new Insets(0, 0, 10, 0);
		
		form.add(fnameField, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 9;
		gbc.insets = new Insets(0, 0, 10, 0);
		
		form.add(snameLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 10;
		gbc.insets = new Insets(0, 0, 10, 0);
		
		form.add(snameField, gbc);
		
		// Reg button
		regButton = new JButton("Registrera dig!");

		gbc.gridx = 0;
		gbc.gridy = 11;
		gbc.insets = new Insets(0, 0, 10, 0);
		
		form.add(regButton, gbc);
		
		
		
		ListenForButton lForButton = new ListenForButton();

		// Tell Java that you want to be alerted when an event
		// occurs on the button

		regButton.addActionListener(lForButton);

	}

	private class ListenForButton implements ActionListener {

		// This method is called when an event occurs

		public void actionPerformed(ActionEvent e) {

			// Check if the source of the event was the button

			if (e.getSource() == regButton) {

				String email = emailField.getText();
				char[] pass1 = passField.getPassword();
				char[] pass2 = passConfField.getPassword();
				String fname = fnameField.getText();
				String sname = snameField.getText();
				int pass1Length = pass1.length;
				int pass2Length = pass2.length;
				boolean passwordMatch = true;
				String hashed = "";
				// password=String.valueOf(pass1);
				// System.out.println(password);
				if (pass1Length == pass2Length) {
					for (int i = 1; i <= pass1Length; i++) {
						if (pass1[i - 1] == pass2[i - 1]) {
						} else {
							passwordMatch = false;
						}
					}

				} else {
					passwordMatch = false;
				}

				if (passwordMatch) {
					hashed = BCrypt.hashpw(String.valueOf(pass1), BCrypt.gensalt());
					String SQL = "INSERT INTO user(email,password,fname,sname) VALUES('" + email + "','" + hashed
							+ "','" + fname + "','" + sname + "');";

					db.execute(SQL);

					SQL = "SELECT * FROM user WHERE email = '" + email + "'";
					Object[][] data = db.getData(SQL);

					user_id = Integer.parseInt((String) data[0][0]);

					SQL = "INSERT INTO calendar(creator_id,name,description,notification) VALUES('" + user_id
							+ "','Min kalender','Det här är din standard kalender',1);";

					db.execute(SQL);

					JOptionPane.showMessageDialog(window, "Tack för att du registrerade dig, " + fname + " " + sname,
							"Registrering lyckades!", JOptionPane.INFORMATION_MESSAGE);

					window.getLoginPage();

				}

			}

		}

	}
}