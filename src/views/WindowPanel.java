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
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import controller.DateLabelFormatter;
import database.SQLManager;
import object.User;

public class WindowPanel extends JPanel {

	private JPanel form, main, addCalTop, addCalMain, center, centerEvent, mainPanel, leftPanel, addEventButtonPanel,
			overviewPanel, CalendarChoicePanel, placeholderPanel, rightPanel, upperLeftPanel, upperRightPanel, top,
			centerLeft, centerRight, addCalCenterRight, addCalCenterLeft, addCalCenter;
	private JLabel loginLabel, emailLabel, passLabel, regLabel, regHere, passConfLabel, fnameLabel, snameLabel,
			calenderNameLabel, nameLabel, locationLabel, startTimeLabel, endTimeLabel, descriptionLabel,
			calendarNameLabel;
	private JTextField emailField, fnameField, snameField, nameField, locationField, calenderNameField;
	private JPasswordField passField, passConfField;
	private JButton loginButton, registerPageButton, registerButton, loginPageButton, regButton;
	private JTextArea descriptionArea;
	private JSpinner startTimeSpinner, endTimeSpinner;
	private JSpinner.DateEditor startTimeEditor, endTimeEditor;
	private JDatePanelImpl startDatePanel, endDatePanel;
	private JDatePickerImpl startDatePicker, endDatePicker;
	private UtilDateModel startModel, endModel;
	private Properties startProperties, endProperties;
	private ListenForButton lForButton;
	private GridBagConstraints gbc;
	private Window window;
	private User user;

	public WindowPanel(Window window) {
		this.window = window;
		gbc = new GridBagConstraints();
		lForButton = new ListenForButton();
		center = new JPanel();
		center.setLayout(new GridLayout(1, 1));
		center.setPreferredSize(new Dimension(1400, 800));
		center.setBackground(new Color(255, 0, 0));
		// center.add(new Login(this));
		center.setVisible(true);
		add(center);

		/*
		 * TODO move all building of panels here?
		 */

		getLoginPage();
	}

	public void getIndexPage() {
		// center.removeAll();
		// center.add(new Index(this));
		// center.updateUI();
		gbc = new GridBagConstraints();
		MenuList menu = new MenuList();
		window.setJMenuBar(menu.createMenuBar(window, this));
		center.removeAll();

		// Main panel
		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(1400, 800));
		mainPanel.setLayout(new GridBagLayout());
		mainPanel.setVisible(true);
		mainPanel.setBackground(new Color(0, 0, 255));
		center.add(mainPanel);

		// Upper left panel
		upperLeftPanel = new JPanel();
		upperLeftPanel.setPreferredSize(new Dimension(200, 50));
		upperLeftPanel.setLayout(new GridLayout(2, 2));
		upperLeftPanel.setVisible(true);
		upperLeftPanel.setBackground(new Color(255, 0, 0));

		gbc.gridx = 0;
		gbc.gridy = 0;

		mainPanel.add(upperLeftPanel, gbc);

		// Upper right panel
		upperRightPanel = new JPanel();
		upperRightPanel.setPreferredSize(new Dimension(1200, 50));
		upperRightPanel.setLayout(new GridLayout(2, 2));
		upperRightPanel.setVisible(true);
		upperRightPanel.setBackground(new Color(0, 255, 0));

		gbc.gridx = 1;
		gbc.gridy = 0;

		mainPanel.add(upperRightPanel, gbc);

		// Left panel
		leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(200, 750));
		leftPanel.setLayout(new GridBagLayout());
		leftPanel.setVisible(true);
		leftPanel.setBackground(new Color(0, 0, 255));

		gbc.gridx = 0;
		gbc.gridy = 1;

		mainPanel.add(leftPanel, gbc);

		addEventButtonPanel = new JPanel();
		addEventButtonPanel.setPreferredSize(new Dimension(200, 100));
		addEventButtonPanel.setVisible(true);
		addEventButtonPanel.setBackground(new Color(0, 0, 255));

		gbc.gridx = 0;
		gbc.gridy = 0;

		leftPanel.add(addEventButtonPanel, gbc);

		overviewPanel = new JPanel();
		overviewPanel.setPreferredSize(new Dimension(200, 200));
		overviewPanel.setVisible(true);
		overviewPanel.setBackground(new Color(100, 100, 100));

		gbc.gridx = 0;
		gbc.gridy = 1;

		leftPanel.add(overviewPanel, gbc);

		CalendarChoicePanel = new JPanel();
		CalendarChoicePanel.setPreferredSize(new Dimension(200, 200));
		CalendarChoicePanel.setVisible(true);
		CalendarChoicePanel.setBackground(new Color(255, 0, 255));

		gbc.gridx = 0;
		gbc.gridy = 2;

		leftPanel.add(CalendarChoicePanel, gbc);

		// Right panel
		rightPanel = new JPanel();
		rightPanel.setPreferredSize(new Dimension(1200, 750));
		rightPanel.setLayout(new GridBagLayout());
		rightPanel.setVisible(true);
		rightPanel.setBackground(new Color(150, 150, 150));

		gbc.gridx = 1;
		gbc.gridy = 1;

		mainPanel.add(rightPanel, gbc);

		getAddEventPage();

		center.updateUI();
		/*
		 * calendarPanel = new JPanel(); calendarPanel.setPreferredSize(new
		 * Dimension(1175, 725)); calendarPanel.setVisible(true);
		 * calendarPanel.setBackground(new Color(255, 0, 0));
		 * 
		 * gbc.gridx = 0; gbc.gridy = 0;
		 * 
		 * rightPanel.add(calendarPanel, gbc);
		 */

	}

	public void getRegisterPage() {
		// center.removeAll();
		// center.add(new Register(this));
		// center.updateUI();
		center.removeAll();
		main = new JPanel();
		main.setPreferredSize(new Dimension(1400, 800));
		main.setLayout(new GridBagLayout());

		center.add(main);

		form = new JPanel();
		form.setLayout(new GridBagLayout());
		form.setPreferredSize(new Dimension(480, 600));
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
		gbc.insets = new Insets(0, 0, 10, 0);

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
		 * this.setJMenuBar(null); center.removeAll(); center.add(new
		 * Login(this)); center.updateUI();
		 */
		user = null;
		SQLManager.setUser(user);
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

	public void getAddEventPage() {
		rightPanel.removeAll();
		
		

		gbc = new GridBagConstraints();
		// Panel management

		main = new JPanel();
		main.setPreferredSize(new Dimension(1175, 725));
		main.setLayout(new GridBagLayout());
		// main.setBackground(new Color(0, 255, 255));
		main.setVisible(true);

		rightPanel.add(main);

		top = new JPanel();
		top.setPreferredSize(new Dimension(1175, 20));
		top.setLayout(new GridBagLayout());
		// top.setBackground(new Color(255, 0, 0));
		top.setVisible(true);

		gbc.gridx = 0;
		gbc.gridy = 0;

		main.add(top, gbc);

		centerEvent = new JPanel();
		centerEvent.setPreferredSize(new Dimension(1175, 705));
		centerEvent.setLayout(new GridBagLayout());
		// center.setBackground(new Color(0, 255, 0));
		centerEvent.setVisible(true);

		gbc.gridx = 0;
		gbc.gridy = 1;

		main.add(centerEvent, gbc);

		centerLeft = new JPanel();
		centerLeft.setPreferredSize(new Dimension(700, 705));
		centerLeft.setLayout(new GridBagLayout());
		// centerLeft.setBackground(new Color(0, 255, 0));
		centerLeft.setVisible(true);

		gbc.gridx = 0;
		gbc.gridy = 0;

		centerEvent.add(centerLeft, gbc);

		centerRight = new JPanel();
		centerRight.setPreferredSize(new Dimension(475, 705));
		centerRight.setLayout(new GridBagLayout());
		// centerRight.setBackground(new Color(0, 0, 255));
		centerRight.setVisible(true);

		gbc.gridx = 1;
		gbc.gridy = 0;

		centerEvent.add(centerRight, gbc);

		// Everything on panels

		// Event label + event field
		nameLabel = new JLabel("Event namn");
		nameLabel.setFont(new Font("Serif", Font.PLAIN, 20));

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 10, 0);

		centerLeft.add(nameLabel, gbc);

		nameField = new JTextField();
		nameField.setPreferredSize(new Dimension(300, 30));

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(0, 0, 10, 0);

		centerLeft.add(nameField, gbc);

		// Event location label + location field

		locationLabel = new JLabel("Plats");
		locationLabel.setFont(new Font("Serif", Font.PLAIN, 20));

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.insets = new Insets(0, 0, 10, 0);

		centerLeft.add(locationLabel, gbc);

		locationField = new JTextField();
		locationField.setPreferredSize(new Dimension(300, 30));

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.insets = new Insets(0, 0, 10, 0);

		centerLeft.add(locationField, gbc);

		// Event start time + end time

		startTimeLabel = new JLabel("Start på event");
		startTimeLabel.setFont(new Font("Serif", Font.PLAIN, 20));

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.insets = new Insets(0, 0, 10, 0);

		centerLeft.add(startTimeLabel, gbc);

		startModel = new UtilDateModel();
		// model.setDate(20,04,2014);
		// Need this...
		startProperties = new Properties();
		startProperties.put("text.today", "Today");
		startProperties.put("text.month", "Month");
		startProperties.put("text.year", "Year");

		startDatePanel = new JDatePanelImpl(startModel, startProperties);
		startDatePicker = new JDatePickerImpl(startDatePanel, new DateLabelFormatter());
		//startDatePicker.setPreferredSize(new Dimension(300, 30));

		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.insets = new Insets(0, 0, 10, 0);
		gbc.anchor = GridBagConstraints.WEST;

		centerLeft.add(startDatePicker, gbc);

		startTimeSpinner = new JSpinner(new SpinnerDateModel());
		startTimeEditor = new JSpinner.DateEditor(startTimeSpinner, "HH:mm");
		startTimeSpinner.setEditor(startTimeEditor);
		startTimeEditor.setPreferredSize(new Dimension(50, 23));

		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.insets = new Insets(0, 10, 10, 0);
		gbc.anchor = GridBagConstraints.WEST;
		
		centerLeft.add(startTimeSpinner, gbc);
		
		endTimeLabel = new JLabel("Slut på event");
		endTimeLabel.setFont(new Font("Serif", Font.PLAIN, 20));

		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.insets = new Insets(0, 0, 10, 0);

		centerLeft.add(endTimeLabel, gbc);

		endModel = new UtilDateModel();
		// model.setDate(20,04,2014);
		// Need this...
		endProperties = new Properties();
		endProperties.put("text.today", "Today");
		endProperties.put("text.month", "Month");
		endProperties.put("text.year", "Year");

		endDatePanel = new JDatePanelImpl(endModel, endProperties);
		endDatePicker = new JDatePickerImpl(endDatePanel, new DateLabelFormatter());
		//endDatePicker.setPreferredSize(new Dimension(200, 30));

		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.insets = new Insets(0, 0, 10, 0);
		gbc.anchor = GridBagConstraints.WEST;
		
		centerLeft.add(endDatePicker, gbc);
		
		endTimeSpinner = new JSpinner(new SpinnerDateModel());
		endTimeEditor = new JSpinner.DateEditor(endTimeSpinner, "HH:mm");
		endTimeSpinner.setEditor(endTimeEditor);
		endTimeEditor.setPreferredSize(new Dimension(50, 23));

		gbc.gridx = 1;
		gbc.gridy = 7;
		gbc.insets = new Insets(0, 10, 10, 0);
		gbc.anchor = GridBagConstraints.WEST;
		
		centerLeft.add(endTimeSpinner, gbc);

		rightPanel.updateUI();
	}

	public void getAddCalendarPage() {
		rightPanel.removeAll();

		gbc = new GridBagConstraints();

		// Panel management

		addCalMain = new JPanel();
		addCalMain.setPreferredSize(new Dimension(1175, 725));
		addCalMain.setLayout(new GridBagLayout());
		// main.setBackground(new Color(0, 255, 255));
		addCalMain.setVisible(true);

		rightPanel.add(addCalMain);

		addCalTop = new JPanel();
		addCalTop.setPreferredSize(new Dimension(1175, 20));
		addCalTop.setLayout(new GridBagLayout());
		addCalTop.setBackground(new Color(255, 0, 0));
		addCalTop.setVisible(true);

		gbc.gridx = 0;
		gbc.gridy = 0;

		addCalMain.add(addCalTop, gbc);

		addCalCenter = new JPanel();
		addCalCenter.setPreferredSize(new Dimension(1175, 705));
		addCalCenter.setLayout(new GridBagLayout());
		// addCalCenter.setBackground(new Color(0, 255, 0));
		addCalCenter.setVisible(true);

		gbc.gridx = 0;
		gbc.gridy = 1;

		addCalMain.add(addCalCenter, gbc);

		addCalCenterLeft = new JPanel();
		addCalCenterLeft.setPreferredSize(new Dimension(700, 705));
		addCalCenterLeft.setLayout(new GridBagLayout());
		addCalCenterLeft.setBackground(new Color(0, 255, 0));
		addCalCenterLeft.setVisible(true);

		gbc.gridx = 0;
		gbc.gridy = 0;

		addCalCenter.add(addCalCenterLeft, gbc);

		calenderNameLabel = new JLabel("Event namn");
		calenderNameLabel.setFont(new Font("Serif", Font.PLAIN, 20));

		gbc.gridx = 0;
		gbc.gridy = 0;

		addCalCenterLeft.add(calenderNameLabel, gbc);

		calenderNameField = new JTextField();
		calenderNameField.setPreferredSize(new Dimension(300, 30));
		addCalCenterLeft.add(calenderNameField, gbc);

		addCalCenterRight = new JPanel();
		addCalCenterRight.setPreferredSize(new Dimension(475, 705));
		addCalCenterRight.setLayout(new GridBagLayout());
		addCalCenterRight.setBackground(new Color(0, 0, 255));
		addCalCenterRight.setVisible(true);

		gbc.gridx = 1;
		gbc.gridy = 0;

		addCalCenter.add(addCalCenterRight, gbc);

		rightPanel.updateUI();
	}

	private class ListenForButton implements ActionListener {

		// This method is called when an event occurs

		public void actionPerformed(ActionEvent e) {

			// Check if the source of the event was the button

			if (e.getSource() == loginButton) {
				String loginEmail = emailField.getText();
				char[] loginPassCandidate = passField.getPassword();
				if (SQLManager.checkLogin(loginEmail, loginPassCandidate, window)) {
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
