package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import object.User;

public class Login extends JPanel {

	private static JavaDB db = new JavaDB();
	private static String loginPassHashed;
	private static Window window;
	private static User user;

	public static boolean checkLogin(String emailfield,char[] passfield){
		
		Object[][] data = db.getData("select * from user where email = '" + emailfield + "'");
		int accfound = 0;
		try {
			loginPassHashed = (String) data[0][2];
			accfound=1;
		} catch (ArrayIndexOutOfBoundsException e1) {
			JOptionPane.showMessageDialog(window, "Vi hittar inget konto kopplat till den email-adressen, var god försök igen!",
					"Inloggning misslyckades!", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		
		if(accfound==1){
			char[] loginPassCandidate = passfield;
			if (BCrypt.checkpw(String.valueOf(loginPassCandidate), loginPassHashed)) {
				System.out.println("It matches");
				user = new User(Integer.parseInt((String) data[0][0]), (String) data[0][1], (String) data[0][3],
					(String) data[0][4], (String) data[0][5]);
				// user = new user (int,string,string,string);
				user.getAll();
				user.reloadarrays();
				return true;
			} else {
				//System.out.println("It does not match");
				JOptionPane.showMessageDialog(window, "Du har angivit fel lösenord för det hör kontot, var god försök igen!",
					"Inloggning misslyckades!", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
		}
		return false;
	}

}
