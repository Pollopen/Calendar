package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import database.SQLManager;
import object.User;

public class Login extends JPanel {

	private GridBagConstraints gbc;
	private JButton loginButton, registerPageButton;
	private JPanel formPanel;
	private Component regLabel;
	private JPasswordField passField;
	private JTextField emailField;
	private JLabel emailLabel, loginLabel, passLabel;
	private ActionListener lForButton;
	private WindowPanel wp;
	private User user;
	private Window window;

	public Login(Window window, WindowPanel wp, User user) {
		// TODO Auto-generated constructor stub
		gbc = new GridBagConstraints();
		lForButton = new ListenForButton();
		window.setJMenuBar(null);
		user = null;
		this.wp=wp;
		this.user=user;
		this.window=window;
		SQLManager.setUser(user);
	
		setPreferredSize(new Dimension(1400, 800));
		setLayout(new GridBagLayout());

		formPanel = new JPanel();
		formPanel.setLayout(new GridBagLayout());
		formPanel.setPreferredSize(new Dimension(480, 600));
		formPanel.setVisible(true);
		formPanel.setBorder(BorderFactory.createLineBorder(Color.black));

		gbc.gridx = 0;
		gbc.gridy = 0;

		add(formPanel, gbc);

		// Login text
		loginLabel = new JLabel("Logga in här!", JLabel.CENTER);
		loginLabel.setFont(new Font("Serif", Font.PLAIN, 25));

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 10, 0);

		formPanel.add(loginLabel, gbc);

		// Email
		emailLabel = new JLabel("Email: ");
		emailLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		emailField = new JTextField();
		emailField.setPreferredSize(new Dimension(300, 30));

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(0, 0, 10, 0);

		formPanel.add(emailLabel, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.insets = new Insets(0, 0, 10, 0);

		formPanel.add(emailField, gbc);

		// Password
		passLabel = new JLabel("Lösenord: ");
		passLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		passField = new JPasswordField();
		passField.setPreferredSize(new Dimension(300, 30));

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.insets = new Insets(0, 0, 10, 0);

		formPanel.add(passLabel, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.insets = new Insets(0, 0, 10, 0);

		formPanel.add(passField, gbc);

		// Login button
		loginButton = new JButton("Logga in!");

		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.insets = new Insets(0, 0, 10, 0);

		formPanel.add(loginButton, gbc);

		// Registration
		regLabel = new JLabel("Inget konto? Registrera dig här!", JLabel.CENTER);
		regLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		registerPageButton = new JButton("Registrera dig!");

		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.insets = new Insets(0, 0, 10, 0);

		formPanel.add(regLabel, gbc);

		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.insets = new Insets(0, 0, 10, 0);

		formPanel.add(registerPageButton, gbc);

		loginButton.addActionListener(lForButton);
		registerPageButton.addActionListener(lForButton);
		gbc.insets = new Insets(0, 0, 0, 0);
	}
	private class ListenForButton implements ActionListener {

		// This method is called when an event occurs

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == loginButton) {
				String loginEmail = emailField.getText();
				char[] loginPassCandidate = passField.getPassword();
				if (SQLManager.checkLogin(loginEmail, loginPassCandidate, window, wp)) {
					wp.getIndexPage();
				}
			}
			if (e.getSource() == registerPageButton) {
				wp.getRegisterPage();
			}
		}
	}
}
