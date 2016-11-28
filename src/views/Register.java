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
	private JLabel regHere, emailLabel, passLabel, passConfLabel, fnameLabel, snameLabel, loginLabel;
	private JTextField emailField, fnameField, snameField;
	private JPasswordField passField, passConfField;
	private JavaDB db = new JavaDB();
	private JButton regButton, loginButton;
	private GridBagConstraints gbc;
	private int user_id;
	private Window window;

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
			
			if(e.getSource() == loginButton)
			{
				window.getLoginPage();
			}

		}

	}
}