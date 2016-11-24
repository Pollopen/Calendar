




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

public class Login extends JPanel {
	
	private JLabel  loginHere, emailHere, passHere, regHere;
	private JTextField emailField;
	private JPasswordField passField;
	private JButton loginButton, registerButton;
	private JavaDB db = new JavaDB("localhost","root","","calendar");
	private String loginPassHashed;
	public Login()
	{
		//setLayout(new GridLayout(6,1));
		// Login text
		loginHere = new JLabel("Logga in här!", JLabel.CENTER);
		// Email 
		emailHere = new JLabel("Email: ");
		emailField = new JTextField(40);
		// Password
		passHere = new JLabel("Lösenord: ");
		passField = new JPasswordField(40);
		// Login button
		loginButton = new JButton("Logga in!");
		// Registration
		regHere = new JLabel("Inget konto? Registrera dig här!", JLabel.CENTER);
		registerButton = new JButton("Registrera dig!");
		
		
		ListenForButton lForButton = new ListenForButton();
		
		loginButton.addActionListener(lForButton);
		registerButton.addActionListener(lForButton);
		
		add(loginHere);
		add(emailHere);
		add(emailField);
		add(passHere);
		add(passField);
		add(loginButton);
		add(regHere);
		add(registerButton);
		
	}
	
	private class ListenForButton implements ActionListener{
		
	// This method is called when an event occurs
	
		public void actionPerformed(ActionEvent e){
		
		// Check if the source of the event was the button
			
			if(e.getSource() == loginButton)
			{
				String loginEmail = emailField.getText();
				Object[][]data = db.getData("SELECT * FROM user WHERE email = "+loginEmail+"");
				loginPassHashed=(String) data[0][2];	
				
				char[] loginPassCandidate = passField.getPassword();
				if (BCrypt.checkpw(String.valueOf(loginPassCandidate), loginPassHashed))
					System.out.println("It matches");
				else
					System.out.println("It does not match");
			}
			
			if(e.getSource() == registerButton)
			{
				
			}
		
		}
	
	}
	

}
