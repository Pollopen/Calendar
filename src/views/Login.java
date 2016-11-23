package views;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JPanel {
	
	private JLabel  regHere, emailHere, passHere;
	private JTextField emailReg;
	private JPasswordField passReg;
	
	public Login()
	{
		// Login text
		regHere = new JLabel("Logga in här!", JLabel.CENTER);
		// Email 
		emailHere = new JLabel("Email: ");
		emailReg = new JTextField(40);
		// Password
		passHere = new JLabel("Lösenord: ");
		passReg = new JPasswordField(40);
		
		add(regHere);
		add(emailHere);
		add(emailReg);
		add(passHere);
		add(passReg);
		
	
		
	}
	

}
