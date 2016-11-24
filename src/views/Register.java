package views;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import org.mindrot.jbcrypt.BCrypt;

import database.JavaDB;

public class Register extends JPanel {
	
	private JLabel regHere, emailLabel, passLabel, passConfLabel, fnameLabel, snameLabel, loginEmailLabel, loginPassLabel;
	private JTextField emailField, fnameField, snameField, loginEmailField;
	private JPasswordField  passField, passConfField, loginPassField;
	private JButton regButton, loginButton;
	private JavaDB db = new JavaDB("localhost","root","","calendar");
	private Window window;
	
	public Register(Window window)
	{
		this.window = window;
		
		setLayout(new GridLayout(20,1));
		// Stor text at top
		regHere = new JLabel("Registrera dig här!", JLabel.CENTER);
		// Email
		emailLabel = new JLabel("Email: ");
		emailField = new JTextField(40);
		// Password + password confirmation
		passLabel = new JLabel("Lösenord: ");
		passField = new JPasswordField(40);
		passConfLabel = new JLabel("Lösenord igen: ");
		passConfField = new JPasswordField(40);
		// First name + last name
		fnameLabel = new JLabel("Förnamn: ");
		fnameField = new JTextField(40);
		snameLabel = new JLabel("Efternamn: ");
		snameField = new JTextField(40);
		
		loginEmailLabel = new JLabel("Email: ");
		loginEmailField = new JTextField(40);
		loginPassLabel = new JLabel("Lösenord: ");
		loginPassField = new JPasswordField(40);
		
		// Reg button
		regButton = new JButton("Registrera dig!");
		loginButton = new JButton("Logga in");
		
		ListenForButton lForButton = new ListenForButton();
		
		// Tell Java that you want to be alerted when an event
		// occurs on the button
		
		regButton.addActionListener(lForButton);
		loginButton.addActionListener(lForButton);
		
		add(regHere);
		add(emailLabel);
		add(emailField);
		add(passLabel);
		add(passField);
		add(passConfLabel);
		add(passConfField);
		add(fnameLabel);
		add(fnameField);
		add(snameLabel);
		add(snameField);
		add(regButton);
		add(loginEmailLabel);
		add(loginEmailField);
		add(loginPassLabel);
		add(loginPassField);
		add(loginButton);
	}
	

	private class ListenForButton implements ActionListener{
	
	// This method is called when an event occurs
	
		public void actionPerformed(ActionEvent e){
		
		// Check if the source of the event was the button
		
			if(e.getSource() == regButton){
			
				String email = emailField.getText();
				char[] pass1 = passField.getPassword();
				char[] pass2 = passConfField.getPassword();
				String fname = fnameField.getText();
				String sname = snameField.getText();
				int pass1Length = pass1.length;
				int pass2Length = pass2.length;
				boolean passwordMatch=true;
				String hashed="";
				//password=String.valueOf(pass1);
				//System.out.println(password);
				if(pass1Length==pass2Length){
					for(int i=1; i<=pass1Length; i++){
						if(pass1[i-1]==pass2[i-1]){
							
							//password += pass1[i-1];
						}else{
							passwordMatch=false;
						}
					}
					
					
				}else{
					passwordMatch=false;
				}
				
				if(passwordMatch){
					hashed = BCrypt.hashpw(String.valueOf(pass1), BCrypt.gensalt());
					String SQL="INSERT INTO user(email,password,fname,sname) VALUES('"+email+"','"+hashed+"','"+fname+"','"+sname+"');";
					db.execute(SQL);
				}
				
			}
			if(e.getSource() == loginButton){
				String loginEmail = loginEmailField.getText();
				Object[][]data = db.getData("SELECT * FROM user WHERE email = "+loginEmail+"");
				String loginPassHashed=(String) data[0][2];
				System.out.println(data[0][2]);
				char[] loginPassCandidate = loginPassField.getPassword();
				if (BCrypt.checkpw(String.valueOf(loginPassCandidate), loginPassHashed))
					System.out.println("It matches");
				else
					System.out.println("It does not match");
			}
		
		}
	
	}
}