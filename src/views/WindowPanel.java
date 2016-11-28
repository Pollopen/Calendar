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

import object.User;

public class WindowPanel extends JPanel {
	
	private JPanel form, main, center;
	private JLabel loginLabel, emailLabel, passLabel, regLabel, regHere, passConfLabel, fnameLabel, snameLabel;
	private JTextField emailField, fnameField, snameField;
	private JPasswordField passField, passConfField;
	private JButton loginButton, registerPageButton, registerButton, loginPageButton, regButton;
	private ListenForButton lForButton;
	private GridBagConstraints gbc;
	private Window window;
	
	public WindowPanel(Window window){
		this.window=window;
		gbc = new GridBagConstraints();
		lForButton = new ListenForButton();
		center = new JPanel();
		center.setLayout(new GridLayout(1, 1));
		center.setPreferredSize(new Dimension(1400, 800));
		center.setBackground(new Color(255, 0, 0));
		//center.add(new Login(this));
		center.setVisible(true);

		add(center);
		getLoginPage();
	}
	public void getIndexPage() {
		//center.removeAll();
		//center.add(new Index(this));
		//center.updateUI();
	}

	public void getRegisterPage() {
		//center.removeAll();
		//center.add(new Register(this));
		//center.updateUI();
		center.removeAll();
		main = new JPanel();
		main.setPreferredSize(new Dimension(1400, 800));
		main.setLayout(new GridBagLayout());
		
		center.add(main);
		
		form = new JPanel();
		form.setLayout(new GridBagLayout());
		form.setPreferredSize(new Dimension(480,600));
		form.setBorder(BorderFactory.createLineBorder(Color.black));
		form.setVisible(true);
		
		main.add(form);

		
		
		
		// Stor text at top
		regHere = new JLabel("Registrera dig här!", JLabel.CENTER);
		regHere.setFont(new Font("Serif", Font.PLAIN, 25));
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 20, 0);
		
		form.add(regHere, gbc);
		
		// Email
		emailLabel = new JLabel("Email: ");
		emailLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		emailField = new JTextField();
		emailField.setPreferredSize(new Dimension(300, 30));
		
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(0, 0, 10, 0);

		form.add(emailLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.insets = new Insets(0, 0, 10, 0);
		
		form.add(emailField, gbc);
		
		// Password + password confirmation
		passLabel = new JLabel("Lösenord: ");
		passLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		passField = new JPasswordField();
		passField.setPreferredSize(new Dimension(300, 30));
		passConfLabel = new JLabel("Lösenord igen: ");
		passConfLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		passConfField = new JPasswordField();
		passConfField.setPreferredSize(new Dimension(300, 30));
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.insets = new Insets(0, 0, 10, 0);

		form.add(passLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.insets = new Insets(0, 0, 10, 0);
		
		form.add(passField, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.insets = new Insets(0, 0, 10, 0);
		
		form.add(passConfLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.insets = new Insets(0, 0, 10, 0);
		
		form.add(passConfField, gbc);
		
		// First name + last name
		fnameLabel = new JLabel("Förnamn: ");
		fnameLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		fnameField = new JTextField();
		fnameField.setPreferredSize(new Dimension(300, 30));
		snameLabel = new JLabel("Efternamn: ");
		snameLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		snameField = new JTextField();
		snameField.setPreferredSize(new Dimension(300, 30));
		
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.insets = new Insets(0, 0, 10, 0);

		form.add(fnameLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.insets = new Insets(0, 0, 10, 0);
		
		form.add(fnameField, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 9;
		gbc.insets = new Insets(0, 0, 10, 0);
		
		form.add(snameLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 10;
		gbc.insets = new Insets(0, 0, 10, 0);
		
		form.add(snameField, gbc);
		
		// Reg button
		regButton = new JButton("Registrera");

		gbc.gridx = 0;
		gbc.gridy = 11;
		gbc.insets = new Insets(0, 0, 40, 0);
		
		form.add(regButton, gbc);
		
		// Login label + button
		loginLabel = new JLabel("Har du redan ett konto?");
		
		gbc.gridx = 0;
		gbc.gridy = 12;
		gbc.insets =  new Insets(0, 0, 10, 0);
		
		form.add(loginLabel, gbc);
		
		loginPageButton = new JButton("Klicka här för att logga in");
		
		gbc.gridx = 0;
		gbc.gridy = 13;
		gbc.insets = new Insets(0, 0, 10, 0);
		
		form.add(loginPageButton, gbc);
		
		
		
		ListenForButton lForButton = new ListenForButton();

		// Tell Java that you want to be alerted when an event
		// occurs on the button

		regButton.addActionListener(lForButton);
		loginPageButton.addActionListener(lForButton);
		center.updateUI();
	}

	public void getLoginPage() {
		/*
		this.setJMenuBar(null);
		center.removeAll();
		center.add(new Login(this));
		center.updateUI();
		*/
		center.removeAll();
		main = new JPanel();
		main.setPreferredSize(new Dimension(1400, 800));
		main.setLayout(new GridBagLayout());

		center.add(main);

		form = new JPanel();
		form.setLayout(new GridBagLayout());
		form.setPreferredSize(new Dimension(480, 600));
		form.setVisible(true);
		form.setBorder(BorderFactory.createLineBorder(Color.black));

		gbc.gridx = 0;
		gbc.gridy = 0;

		main.add(form, gbc);

		// Login text
		loginLabel = new JLabel("Logga in här!", JLabel.CENTER);
		loginLabel.setFont(new Font("Serif", Font.PLAIN, 25));

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 10, 0);

		form.add(loginLabel, gbc);

		// Email
		emailLabel = new JLabel("Email: ");
		emailLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		emailField = new JTextField();
		emailField.setPreferredSize(new Dimension(300, 30));

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(0, 0, 10, 0);

		form.add(emailLabel, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.insets = new Insets(0, 0, 10, 0);

		form.add(emailField, gbc);

		// Password
		passLabel = new JLabel("Lösenord: ");
		passLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		passField = new JPasswordField();
		passField.setPreferredSize(new Dimension(300, 30));

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.insets = new Insets(0, 0, 10, 0);

		form.add(passLabel, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.insets = new Insets(0, 0, 10, 0);

		form.add(passField, gbc);

		// Login button
		loginButton = new JButton("Logga in!");

		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.insets = new Insets(0, 0, 10, 0);

		form.add(loginButton, gbc);

		// Registration
		regLabel = new JLabel("Inget konto? Registrera dig här!", JLabel.CENTER);
		regLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		registerPageButton = new JButton("Registrera dig!");

		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.insets = new Insets(0, 0, 10, 0);

		form.add(regLabel, gbc);

		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.insets = new Insets(0, 0, 10, 0);

		form.add(registerPageButton, gbc);

		loginButton.addActionListener(lForButton);
		registerPageButton.addActionListener(lForButton);
		center.updateUI();
	}
	private class ListenForButton implements ActionListener {

		// This method is called when an event occurs

		public void actionPerformed(ActionEvent e) {

			// Check if the source of the event was the button

			if (e.getSource() == loginButton) {
				String loginEmail = emailField.getText();
				char[] loginPassCandidate = passField.getPassword();
				if(Login.checkLogin(loginEmail,loginPassCandidate)){
					getIndexPage();
				}
			}
				
			if (e.getSource() == registerPageButton) {
				getRegisterPage();
			}
			if (e.getSource() == loginPageButton) {
				getLoginPage();
			}

		}

	}
}
