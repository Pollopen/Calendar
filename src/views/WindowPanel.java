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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
import controller.StateMachine;
import database.SQLManager;
import object.Calendar;
import object.User;

public class WindowPanel extends JPanel {


	private JPanel editCalendar1, editCalendar2, editCalendar3, form, main, addCalMain, center, centerEvent, mainPanel, leftPanel, addEventButtonPanel,
			overviewPanel, CalendarChoicePanel, placeholderPanel, rightPanel, upperLeftPanel, upperRightPanel, top,
			centerLeft, centerRight, addCalCenterRight, addCalCenterRight1, addCalCenterRight2, addCalCenterRight3,
			addCalCenterLeft, addCalCenter;
	private JLabel calAddLabel, calAddNotLabel, calAddNameLabel, loginLabel, emailLabel, passLabel, regLabel, regHere,
			passConfLabel, fnameLabel, snameLabel, calendarNameLabel, nameLabel, locationLabel, calListLabel,
			startTimeLabel, endTimeLabel, eventDescLabel, descriptionLabel, calendarDescLabel, regEmailLabel,
			regPassLabel, regPassConfLabel, regFNameLabel, regSNameLabel, loginPageLabel;
	private JTextField calAddNameField, calAddNotField, emailField, fnameField, snameField, nameField, locationField,
			calendarNameField, calendarDescField, regEmailField, regPassField, regPassConfField, regSNameField,
			regFNameField;
	private Calendar[] calArray, eventCalArray;
	private JTextArea calendarDescTextArea, calAddDescTextArea;
	private JPasswordField passField, passConfField;
	private JButton calAddButton, loginButton, registerPageButton, registerButton, loginPageButton, regButton,
			calSaveButton, calRemoveButton, eventCreate;
	private JTextArea eventDescArea;
	private JCheckBox fullDayActivity;
	private JSpinner startTimeSpinner, endTimeSpinner;
	private JSpinner.DateEditor startTimeEditor, endTimeEditor;
	private JDatePanelImpl startDatePanel, endDatePanel;
	private JDatePickerImpl startDatePicker, endDatePicker;
	private UtilDateModel startModel, endModel;
	private Properties startProperties, endProperties;
	private ListenForButton lForButton;
	private GridBagConstraints gbc, gbcLeft;
	private JComboBox<String> calDropDown;
	private String eventCreateCalArray;
	private WindowPanel windowpanel;
	private Window window;
	private User user;
	private StateMachine SM;

	public WindowPanel(Window window) {
		windowpanel = this;
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
		SM = new StateMachine(user);

		/*
		 * TODO FIX ORGANISATION
		 */

		getLoginPage();
	}

	public void getIndexPage() {
		// center.removeAll();
		// center.add(new Index(this));
		// center.updateUI();
		gbc = new GridBagConstraints();
		MenuList menu = new MenuList();
		window.setJMenuBar(menu.createMenuBar(window, this, user));
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
		gbcLeft = new GridBagConstraints();
		// Panel management

		main = new JPanel();
		main.setPreferredSize(new Dimension(1175, 725));
		main.setLayout(new GridBagLayout());
		// main.setBackground(new Color(0, 255, 255));
		main.setVisible(true);

		rightPanel.add(main);

		top = new JPanel();
		top.setPreferredSize(new Dimension(1175, 50));
		top.setLayout(new GridBagLayout());
		// top.setBackground(new Color(255, 0, 0));
		top.setVisible(true);

		gbc.gridx = 0;
		gbc.gridy = 0;

		main.add(top, gbc);

		eventCalArray = user.getCalArray();
		calDropDown = new JComboBox<String>();

		for (int i = 0; i < eventCalArray.length; i++) {
			eventCreateCalArray = eventCalArray[i].getName() + " " + eventCalArray[i].getCal_id();
			calDropDown.addItem(eventCreateCalArray);
		}

		gbcLeft.gridx = 0;
		gbcLeft.gridy = 0;
		gbcLeft.insets = new Insets(0, -385, 0, 10);
		gbcLeft.anchor = GridBagConstraints.WEST;

		top.add(calDropDown, gbcLeft);

		centerEvent = new JPanel();
		centerEvent.setPreferredSize(new Dimension(1175, 655));
		centerEvent.setLayout(new GridBagLayout());
		// center.setBackground(new Color(0, 255, 0));
		centerEvent.setVisible(true);

		gbc.gridx = 0;
		gbc.gridy = 1;

		main.add(centerEvent, gbc);
		
		eventCreate = new JButton("Skapa event");
		// eventCreate.setPreferredSize(new Dimension(70, 20));

		gbcLeft.gridx = 1;
		gbcLeft.gridy = 0;
		gbcLeft.insets = new Insets(0, -190, 0, 0);
		gbcLeft.anchor = GridBagConstraints.WEST;

		top.add(eventCreate, gbcLeft);
		
		
		centerLeft = new JPanel();
		centerLeft.setPreferredSize(new Dimension(700, 655));
		centerLeft.setLayout(new GridBagLayout());
		// centerLeft.setBackground(new Color(0, 255, 0));
		centerLeft.setVisible(true);

		gbc.gridx = 0;
		gbc.gridy = 0;

		centerEvent.add(centerLeft, gbc);

		centerRight = new JPanel();
		centerRight.setPreferredSize(new Dimension(475, 655));
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

		fullDayActivity = new JCheckBox();
		fullDayActivity.setText("Heldags aktivitet?");

		gbcLeft.gridx = 0;
		gbcLeft.gridy = 5;
		gbcLeft.insets = new Insets(0, 0, 10, 0);
		gbcLeft.anchor = GridBagConstraints.WEST;

		centerLeft.add(fullDayActivity, gbcLeft);

		startModel = new UtilDateModel();
		// model.setDate(20,04,2014);
		// Need this...
		startProperties = new Properties();
		startProperties.put("text.today", "Today");
		startProperties.put("text.month", "Month");
		startProperties.put("text.year", "Year");

		startDatePanel = new JDatePanelImpl(startModel, startProperties);
		startDatePicker = new JDatePickerImpl(startDatePanel, new DateLabelFormatter());
		startDatePicker.setTextEditable(false);
		// startDatePicker.setPreferredSize(new Dimension(300, 30));

		gbcLeft.gridx = 0;
		gbcLeft.gridy = 6;
		gbcLeft.insets = new Insets(0, 0, 10, 0);
		gbcLeft.anchor = GridBagConstraints.WEST;

		centerLeft.add(startDatePicker, gbcLeft);

		startTimeSpinner = new JSpinner(new SpinnerDateModel());
		startTimeEditor = new JSpinner.DateEditor(startTimeSpinner, "HH:mm");
		startTimeSpinner.setEditor(startTimeEditor);
		startTimeEditor.setPreferredSize(new Dimension(50, 23));

		gbcLeft.gridx = 0;
		gbcLeft.gridy = 6;
		gbcLeft.insets = new Insets(0, 225, 10, 0);
		gbcLeft.anchor = GridBagConstraints.WEST;

		centerLeft.add(startTimeSpinner, gbcLeft);

		endTimeLabel = new JLabel("Slut på event");
		endTimeLabel.setFont(new Font("Serif", Font.PLAIN, 20));

		gbc.gridx = 0;
		gbc.gridy = 7;
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
		endDatePicker.setTextEditable(false);
		// endDatePicker.setPreferredSize(new Dimension(200, 30));

		gbcLeft.gridx = 0;
		gbcLeft.gridy = 8;
		gbcLeft.insets = new Insets(0, 0, 10, 0);
		gbcLeft.anchor = GridBagConstraints.WEST;

		centerLeft.add(endDatePicker, gbcLeft);

		endTimeSpinner = new JSpinner(new SpinnerDateModel());
		endTimeEditor = new JSpinner.DateEditor(endTimeSpinner, "HH:mm");
		endTimeSpinner.setEditor(endTimeEditor);
		endTimeEditor.setPreferredSize(new Dimension(50, 23));

		gbcLeft.gridx = 0;
		gbcLeft.gridy = 8;
		gbcLeft.insets = new Insets(0, 225, 10, 0);
		gbcLeft.anchor = GridBagConstraints.WEST;

		centerLeft.add(endTimeSpinner, gbcLeft);

		eventDescLabel = new JLabel("Beskriv eventet här!");
		eventDescLabel.setFont(new Font("Serif", Font.PLAIN, 20));

		gbc.gridx = 0;
		gbc.gridy = 9;
		gbc.insets = new Insets(0, 0, 10, 0);

		centerLeft.add(eventDescLabel, gbc);

		eventDescArea = new JTextArea();
		eventDescArea.setPreferredSize(new Dimension(300, 100));
		eventDescArea.setLineWrap(true);
		eventDescArea.setWrapStyleWord(true);

		gbc.gridx = 0;
		gbc.gridy = 10;
		gbc.insets = new Insets(0, 0, 10, 0);

		centerLeft.add(eventDescArea, gbc);

		rightPanel.updateUI();

		fullDayActivity.addActionListener(lForButton);
		eventCreate.addActionListener(lForButton);
	}

	public void getAddCalendarPage() {
		rightPanel.removeAll();

		gbc = new GridBagConstraints();

		// Panel management

		addCalMain = new JPanel();
		addCalMain.setPreferredSize(new Dimension(1175, 725));
		addCalMain.setLayout(new GridBagLayout());
		// addCalMain.setBackground(new Color(0, 255, 255));
		addCalMain.setVisible(true);

		rightPanel.add(addCalMain);

		addCalCenter = new JPanel();
		addCalCenter.setPreferredSize(new Dimension(1175, 725));
		addCalCenter.setLayout(new GridBagLayout());
		// addCalCenter.setBackground(new Color(0, 255, 0));
		addCalCenter.setVisible(true);

		gbc.gridx = 0;
		gbc.gridy = 1;

		addCalMain.add(addCalCenter, gbc);

		addCalCenterLeft = new JPanel();
		addCalCenterLeft.setPreferredSize(new Dimension(700, 725));
		addCalCenterLeft.setLayout(new GridBagLayout());
		addCalCenterLeft.setBackground(new Color(0, 255, 0));
		addCalCenterLeft.setVisible(true);

		gbc.gridx = 0;
		gbc.gridy = 0;

		addCalCenter.add(addCalCenterLeft, gbc);

		calenderList();

		addCalCenterRight = new JPanel();
		addCalCenterRight.setPreferredSize(new Dimension(475, 725));
		addCalCenterRight.setLayout(new GridBagLayout());
		addCalCenterRight.setBackground(new Color(0, 0, 255));
		addCalCenterRight.setVisible(true);

		gbc.gridx = 1;
		gbc.gridy = 0;
		
		addCalCenter.add(addCalCenterRight, gbc);
		calenderEdit(3);
		SM.setCalEditStatus(3);
		
		rightPanel.updateUI();
	}
	private void calenderEdit(int calID){
		addCalCenterRight.removeAll();
		
		editCalendar1 = new JPanel();
		editCalendar1.setPreferredSize(new Dimension(475, 75));
		editCalendar1.setLayout(new GridBagLayout());
		editCalendar1.setBackground(new Color(255, 0, 0));
		editCalendar1.setVisible(true);

		calSaveButton = new JButton("Spara");

		calSaveButton = new JButton("Spara");

		calSaveButton.addActionListener(lForButton);

		calRemoveButton = new JButton("Ta bort");
		
		calRemoveButton.addActionListener(lForButton);
		
		editCalendar2 = new JPanel();
		editCalendar2.setPreferredSize(new Dimension(475, 350));
		editCalendar2.setLayout(new GridBagLayout());
		editCalendar2.setBackground(new Color(100, 255, 100));
		editCalendar2.setVisible(true);
		
		editCalendar3 = new JPanel();
		editCalendar3.setPreferredSize(new Dimension(475, 300));
		editCalendar3.setLayout(new GridBagLayout());
		editCalendar3.setBackground(new Color(0, 0, 255));
		editCalendar3.setVisible(true);

		calendarNameLabel = new JLabel("kalendernamn");
		calendarNameLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		
		calendarNameField = new JTextField(calArray[calID].getName());
		calendarNameField.setPreferredSize(new Dimension(300, 30));

		calendarDescLabel = new JLabel("kalenderbeskrivning");
		calendarDescLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		
		calendarDescTextArea = new JTextArea(calArray[calID].getDescription());
		calendarDescTextArea.setPreferredSize(new Dimension(300, 100));
		calendarDescTextArea.setRows(5);
		calendarDescTextArea.setColumns(27);
		calendarDescTextArea.setLineWrap(true);
		calendarDescTextArea.setWrapStyleWord(true);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		addCalCenterRight.add(editCalendar1, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		editCalendar1.add(calSaveButton, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		editCalendar1.add(calRemoveButton, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		addCalCenterRight.add(editCalendar2, gbc);

		gbc.gridx = 0;
		gbc.gridy = 0;
		editCalendar2.add(calendarNameLabel, gbc);


		gbc.gridx = 0;
		gbc.gridy = 1;
		editCalendar2.add(calendarNameField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		editCalendar2.add(calendarDescLabel, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		editCalendar2.add(calendarDescTextArea, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;

		addCalCenterRight.add(editCalendar3, gbc);
		
		addCalCenterRight.updateUI();

		rightPanel.updateUI();
	}

	private void calenderList() {
		// *name**people in calender**notification*
		// add new
		calArray = user.getCalArray();
		calAddLabel = new JLabel("Lägg till kalender:");
		calAddNameLabel = new JLabel("Kalendernamn");
		calAddNameField = new JTextField();
		calAddNameField.setPreferredSize(new Dimension(300, 30));
		calAddNotLabel = new JLabel("Beskrivning:");
		calAddDescTextArea = new JTextArea();
		calAddDescTextArea.setPreferredSize(new Dimension(300, 100));
		calAddDescTextArea.setRows(5);
		calAddDescTextArea.setColumns(27);
		calAddDescTextArea.setLineWrap(true);
		calAddDescTextArea.setWrapStyleWord(true);
		calAddButton = new JButton("Lägg till kalender");
		calAddButton.addActionListener(lForButton);
		gbc.gridx = 0;
		gbc.gridy = 0;
		addCalCenterLeft.add(calAddLabel, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		addCalCenterLeft.add(calAddNameLabel, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		addCalCenterLeft.add(calAddNameField, gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		addCalCenterLeft.add(calAddNotLabel, gbc);
		gbc.gridx = 0;
		gbc.gridy = 4;
		addCalCenterLeft.add(calAddDescTextArea, gbc);
		gbc.gridx = 0;
		gbc.gridy = 5;
		addCalCenterLeft.add(calAddButton, gbc);

		for (int i = 0; i < calArray.length; i++) {
			calListLabel = new JLabel(calArray[i].getName() + " " + calArray[i].getNotification());
			gbc.gridx = 0;
			gbc.gridy = i + 6;

			addCalCenterLeft.add(calListLabel, gbc);
		}

	}

	public void sendUser(User user) {
		this.user = user;
	}

	private class ListenForButton implements ActionListener {

		// This method is called when an event occurs

		public void actionPerformed(ActionEvent e) {

			// Check if the source of the event was the button

			if (e.getSource() == loginButton) {
				String loginEmail = emailField.getText();
				char[] loginPassCandidate = passField.getPassword();
				if (SQLManager.checkLogin(loginEmail, loginPassCandidate, window, windowpanel)) {
					getIndexPage();
				}
			}
			if (e.getSource() == registerButton) {

				// if(SQLManager.register(window, String fname, String sname,
				// String email, char[] pass1, char[] pass2)){
				// getLoginPage();
				// }
			}

			if (e.getSource() == registerPageButton) {
				getRegisterPage();
			}
			if (e.getSource() == loginPageButton) {
				getLoginPage();
			}
			if (e.getSource() == calSaveButton) {
				
			}
			if (e.getSource() == calRemoveButton) {
				
				SQLManager.removeCalender(calArray[SM.getCalEditStatus()].getCal_id());
				user.reloadarrays();
				getAddCalendarPage();
			}
			if (e.getSource() == calAddButton) {
				String temp1 = calAddNameField.getText();
				String temp2 = calAddDescTextArea.getText();
				if (SQLManager.addCalender(temp1, temp2)) {
					addCalCenterLeft.removeAll();
					user.reloadarrays();
					getAddCalendarPage();
				}

			}

			if (e.getSource() == fullDayActivity) {

				if (fullDayActivity.isSelected()) {
					// centerLeft.remove(startTimeSpinner);
					// centerLeft.remove(endTimeSpinner);
					startDatePicker.setPreferredSize(new Dimension(300, 30));
					endDatePicker.setPreferredSize(new Dimension(300, 30));
					centerLeft.updateUI();
					startTimeSpinner.setVisible(false);
					endTimeSpinner.setVisible(false);
					centerLeft.updateUI();
				} else {

					startDatePicker.setPreferredSize(new Dimension(202, 30));
					endDatePicker.setPreferredSize(new Dimension(202, 30));

					startTimeSpinner.setVisible(true);
					endTimeSpinner.setVisible(true);

					centerLeft.updateUI();

				}
			}

			if (e.getSource() == eventCreate) {
				String inputEventName = nameField.getText();
				String inputEventLocation = locationField.getText();
				String inputEventTextArea = eventDescArea.getText();
				int inputFullDayEvent;
				int inputEventStartDay = 0;
				int inputEventStartMonth = 0;
				int inputEventStartYear = 0;
				int inputEventEndDay = 0;
				int inputEventEndMonth = 0;
				int inputEventEndYear = 0;
				int inputCreateEventForCalendarId = user.getCalArray()[calDropDown.getSelectedIndex()].getCal_id();
				String inputEventStartTime = "";
				String inputEventEndTime = "";

				String formatStartDate = "";
				String formatEndDate = "";

				if (fullDayActivity.isSelected()) {

					inputFullDayEvent = 1;

					inputEventStartYear = startDatePicker.getModel().getYear();
					inputEventStartMonth = startDatePicker.getModel().getMonth();
					inputEventStartDay = startDatePicker.getModel().getDay();

					inputEventEndYear = endDatePicker.getModel().getYear();
					inputEventEndMonth = endDatePicker.getModel().getMonth();
					inputEventEndDay = endDatePicker.getModel().getDay();

					formatStartDate = inputEventStartYear + "-" + inputEventStartMonth + "-" + inputEventStartDay
							+ " 01:01:01";
					formatEndDate = inputEventEndYear + "-" + inputEventEndMonth + "-" + inputEventEndDay + " 01:01:01";
				} else {

					inputFullDayEvent = 0;

					inputEventStartYear = startDatePicker.getModel().getYear();
					inputEventStartMonth = startDatePicker.getModel().getMonth();
					inputEventStartDay = startDatePicker.getModel().getDay();

					inputEventEndYear = endDatePicker.getModel().getYear();
					inputEventEndMonth = endDatePicker.getModel().getMonth();
					inputEventEndDay = endDatePicker.getModel().getDay();

					inputEventStartTime = startTimeEditor.getFormat().format(startTimeSpinner.getValue());
					inputEventEndTime = endTimeEditor.getFormat().format(endTimeSpinner.getValue());

					formatStartDate = inputEventStartYear + "-" + inputEventStartMonth + "-" + inputEventStartDay + " "
							+ inputEventStartTime + ":01";
					formatEndDate = inputEventEndYear + "-" + inputEventEndMonth + "-" + inputEventEndDay + " "
							+ inputEventEndTime + ":01";
				}

				// System.out.println(inputEventStartDay);
				// System.out.println(inputEventStartMonth);
				// System.out.println(inputEventStartYear);

//				System.out.println("Start date: " + formatStartDate);
//				System.out.println("End date: " + formatEndDate);
//
//				System.out.println(inputCreateEventForCalendarId);

				SQLManager.addEvent(inputEventName, inputEventLocation, inputEventTextArea, inputFullDayEvent,
						inputCreateEventForCalendarId, formatStartDate, formatEndDate);
				
				getAddEventPage();

			}

		}

	}
}
