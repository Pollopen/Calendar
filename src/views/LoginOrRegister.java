/*package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoginOrRegister extends JPanel {

	private boolean gls;
	private JLabel info;
	private JButton loginButton, registerButton;
	
	public LoginOrRegister(boolean getLoginStatus,boolean getRegisterStatus)
	{
		gls = getLoginStatus;
		info = new JLabel("Klicka en av knapparna för att gå vidare!", JLabel.CENTER);
		loginButton = new JButton("Logga in!");
		registerButton = new JButton("Registrera dig!");
		
		ListenForButton lForButton = new ListenForButton();
		
		loginButton.addActionListener(lForButton);
		registerButton.addActionListener(lForButton);
		
		
		add(info);
		add(loginButton);
		add(registerButton);
		
	}
	
	private class ListenForButton implements ActionListener{
		
	// This method is called when an event occurs
	
		public void actionPerformed(ActionEvent e){
		
		// Check if the source of the event was the button
		
			if(e.getSource() == loginButton){
				
				System.out.println("logged in");
				setVisible(false);
				gls = true;
				
			} 
			
			if(e.getSource() == registerButton){
				
				System.out.println("registrerad hej");
				setVisible(false);
				
			}
		}
	
	}
}
*/
