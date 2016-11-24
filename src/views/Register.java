package views;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.mindrot.jbcrypt.BCrypt;

import database.JavaDB;

public class Register extends JPanel {
	
	private JLabel regHere, emailLabel, passLabel, passConfLabel, fnameLabel, snameLabel;
	private JTextField emailField, fnameField, snameField;
	private JPasswordField  passField, passConfField;
	private JButton regButton;
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
		// Reg button
		regButton = new JButton("Registrera dig!");
		
		ListenForButton lForButton = new ListenForButton();
		
		// Tell Java that you want to be alerted when an event
		// occurs on the button
		
		regButton.addActionListener(lForButton);
		
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
					
					JOptionPane.showMessageDialog(window,
						    "Du är nu registrerad",
						    "Registrering lyckades!",
						    JOptionPane.PLAIN_MESSAGE);
					
					window.getLoginPage();
					
				}
				
			}
		
		}
	
	}
}