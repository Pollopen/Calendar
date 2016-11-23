package views;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Register extends JPanel {
	
	JLabel regHere, emailHere, passHere, passConfHere, fnameHere, snameHere;
	JTextField emailReg, fnameReg, snameReg;
	JPasswordField  passReg, passConfReg;
	JButton reg;
	
	public Register()
	{
		setLayout(new GridLayout(12,1));
		
		// Stor text at top
		regHere = new JLabel("Registrera dig här!", JLabel.CENTER);
		// Email
		emailHere = new JLabel("Email: ");
		emailReg = new JTextField(40);
		// Password + password confirmation
		passHere = new JLabel("Lösenord: ");
		passReg = new JPasswordField(40);
		passConfHere = new JLabel("Lösenord igen: ");
		passConfReg = new JPasswordField(40);
		// First name + last name
		fnameHere = new JLabel("Förnamn: ");
		fnameReg = new JTextField(40);
		snameHere = new JLabel("Efternamn: ");
		snameReg = new JTextField(40);
		// Reg button
		reg = new JButton("Registrera dig!");
		
		add(regHere);
		add(emailHere);
		add(emailReg);
		add(passHere);
		add(passReg);
		add(passConfHere);
		add(passConfReg);
		add(fnameHere);
		add(fnameReg);
		add(snameHere);
		add(snameReg);
		add(reg);
	}
	
}
