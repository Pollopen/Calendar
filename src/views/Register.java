package views;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import database.JavaDB;

public class Register extends JPanel {
	
	JLabel regHere, emailHere, passHere, passConfHere, fnameHere, snameHere;
	JTextField emailReg, fnameReg, snameReg;
	JPasswordField  passReg, passConfReg;
	JButton regButton;
	JavaDB db = new JavaDB("localhost","root","","calendar");
	
	public Register()
	{
		setLayout(new GridLayout(13,1));
		
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
		regButton = new JButton("Registrera dig!");
		
		ListenForButton lForButton = new ListenForButton();
		
		// Tell Java that you want to be alerted when an event
		// occurs on the button
		
		regButton.addActionListener(lForButton);
		
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
		add(regButton);
	}
	

	private class ListenForButton implements ActionListener{
	
	// This method is called when an event occurs
	
		public void actionPerformed(ActionEvent e){
		
		// Check if the source of the event was the button
		
			if(e.getSource() == regButton){
			
				String email = emailReg.getText();
				char[] pass1 = passReg.getPassword();
				char[] pass2 = passConfReg.getPassword();
				String fname = fnameReg.getText();
				String sname = snameReg.getText();
				int pass1Length = pass1.length;
				int pass2Length = pass1.length;
				boolean passwordMatch=true;
				String password="";
				if(pass1Length==pass2Length){
					for(int i=1; i<=pass1Length; i++){
						if(pass1[i-1]==pass2[i-1]){
						password += pass1[i-1];
						}else{
							passwordMatch=false;
						}
					}
					
				}else{
					passwordMatch=false;
				}
				
				if(passwordMatch){
					String SQL="insert into user(email,password,fname,sname) values('"+email+"','"+password+"','"+fname+"','"+sname+"');";
					db.execute(SQL);
				}
				
			}
		
		}
	
	}
}