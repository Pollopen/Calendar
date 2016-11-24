package views;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JPanel {
	
	private JLabel  loginHere, emailHere, passHere, regHere;
	private JTextField emailReg;
	private JPasswordField passReg;
	private JButton loginButton, registerButton;
	private Window window;
	
	public Login(Window window)
	{
		this.window = window;
		//setLayout(new GridLayout(6,1));
		// Login text
		loginHere = new JLabel("Logga in här!", JLabel.CENTER);
		// Email 
		emailHere = new JLabel("Email: ");
		emailReg = new JTextField(40);
		// Password
		passHere = new JLabel("Lösenord: ");
		passReg = new JPasswordField(40);
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
		add(emailReg);
		add(passHere);
		add(passReg);
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
				
			}
			
			if(e.getSource() == registerButton)
			{
				window.getRegister();
			}
		
		}
	
	}
	

}
