package views;

import java.awt.Color;
import java.awt.Component;
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

public class Register extends JPanel {
	private GridBagConstraints gbc;
	private JPanel form;
	private JButton loginPageButton, regButton;
	private JLabel loginPageLabel, regEmailLabel, regHereLabel, regPassLabel, regPassConfLabel, regSNameLabel, regFNameLabel;
	private JTextField regFNameField, regEmailField, regSNameField;
	private JPasswordField regPassField, regPassConfField;
	private Window window;
	private WindowPanel wp;
	public Register(Window window, WindowPanel wp){
		this.wp=wp;
		this.window=window;
		gbc = new GridBagConstraints();
		setPreferredSize(new Dimension(1400, 800));
		setLayout(new GridBagLayout());

		form = new JPanel();
		form.setLayout(new GridBagLayout());
		form.setPreferredSize(new Dimension(480, 600));
		form.setBorder(BorderFactory.createLineBorder(Color.black));
		form.setVisible(true);
		gbc.gridx=0;
		gbc.gridy=0;
		add(form,gbc);

		// Stor text at top
		regHereLabel = new JLabel("Registrera dig här!", JLabel.CENTER);
		regHereLabel.setFont(new Font("Serif", Font.PLAIN, 25));

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 20, 0);

		form.add(regHereLabel, gbc);

		// Email
		regEmailLabel = new JLabel("Email: ");
		regEmailLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		regEmailField = new JTextField();
		regEmailField.setPreferredSize(new Dimension(300, 30));

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(0, 0, 10, 0);

		form.add(regEmailLabel, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.insets = new Insets(0, 0, 10, 0);

		form.add(regEmailField, gbc);

		// Password + password confirmation
		regPassLabel = new JLabel("Lösenord: ");
		regPassLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		regPassField = new JPasswordField();
		regPassField.setPreferredSize(new Dimension(300, 30));
		regPassConfLabel = new JLabel("Lösenord igen: ");
		regPassConfLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		regPassConfField = new JPasswordField();
		regPassConfField.setPreferredSize(new Dimension(300, 30));

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.insets = new Insets(0, 0, 10, 0);

		form.add(regPassLabel, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.insets = new Insets(0, 0, 10, 0);

		form.add(regPassField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.insets = new Insets(0, 0, 10, 0);

		form.add(regPassConfLabel, gbc);

		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.insets = new Insets(0, 0, 10, 0);

		form.add(regPassConfField, gbc);

		// First name + last name
		regFNameLabel = new JLabel("Förnamn: ");
		regFNameLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		regFNameField = new JTextField();
		regFNameField.setPreferredSize(new Dimension(300, 30));
		regSNameLabel = new JLabel("Efternamn: ");
		regSNameLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		regSNameField = new JTextField();
		regSNameField.setPreferredSize(new Dimension(300, 30));

		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.insets = new Insets(0, 0, 10, 0);

		form.add(regFNameLabel, gbc);

		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.insets = new Insets(0, 0, 10, 0);

		form.add(regFNameField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 9;
		gbc.insets = new Insets(0, 0, 10, 0);

		form.add(regSNameLabel, gbc);

		gbc.gridx = 0;
		gbc.gridy = 10;
		gbc.insets = new Insets(0, 0, 10, 0);

		form.add(regSNameField, gbc);

		// Reg button
		regButton = new JButton("Registrera");

		gbc.gridx = 0;
		gbc.gridy = 11;
		gbc.insets = new Insets(0, 0, 40, 0);

		form.add(regButton, gbc);

		// Login label + button
		loginPageLabel = new JLabel("Har du redan ett konto?");

		gbc.gridx = 0;
		gbc.gridy = 12;
		gbc.insets = new Insets(0, 0, 10, 0);

		form.add(loginPageLabel, gbc);

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

	}
	private class ListenForButton implements ActionListener {

		// This method is called when an event occurs

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == regButton) {
				String email = regEmailField.getText();
				String fname = regFNameField.getText();
				String sname = regSNameField.getText();
				char[] pass1 = regPassField.getPassword();
				char[] pass2 = regPassConfField.getPassword();

				if (SQLManager.register(window, fname, sname, email, pass1, pass2)) {
					wp.getLoginPage();
				}
			}
			if (e.getSource() == loginPageButton) {
				wp.getLoginPage();
			}
		}
	}
	
}
