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
	
	private static JavaDB db = new JavaDB();

	public static boolean register(Window window, String fname, String sname, String email, char[] pass1, char[] pass2){
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
					return false;
				}
			}

		} else {
			passwordMatch = false;
			return false;
		}

		if (passwordMatch) {
			hashed = BCrypt.hashpw(String.valueOf(pass1), BCrypt.gensalt());
			String SQL = "INSERT INTO user(email,password,fname,sname) VALUES('" + email + "','" + hashed
					+ "','" + fname + "','" + sname + "');";
			db.execute(SQL);

			SQL = "SELECT * FROM user WHERE email = '" + email + "'";
			Object[][] data = db.getData(SQL);
			int user_id = Integer.parseInt((String) data[0][0]);

			SQL = "INSERT INTO calendar(creator_id,name,description,notification) VALUES('" + user_id
					+ "','Min kalender','Det här är din standard kalender',1);";
			db.execute(SQL);

			JOptionPane.showMessageDialog(window, "Tack för att du registrerade dig, " + fname + " " + sname,
					"Registrering lyckades!", JOptionPane.INFORMATION_MESSAGE);
			return true;
		}
		return false;
	}
}