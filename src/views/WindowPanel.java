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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.StateMachine;
import object.Calendar;
import object.Event;
import object.User;
import views.calendar.CalChooseList;
import views.calendar.addedit.CalAddEdit;

public class WindowPanel extends JPanel {

	private JPanel overviewPanel1, overviewPanel2, notificationPanel, center, mainPanel, leftPanel, addEventButtonPanel,
			overviewPanel, CalendarChoicePanel, rightPanel, upperLeftPanel, upperRightPanel;
	private Calendar[] calArray;
	private JButton addEventButton;

	private ListenForButton lForButton;
	private GridBagConstraints gbc, gbcLeft;
	private Window window;
	private User user;
	private StateMachine SM;
	private CalChooseList calChooseList;
	private ViewChoice viewChoice;
	private MonthOverview monthOverview;
	private MonthYearPanel monthYearPanel;

	public WindowPanel(Window window) {
		this.window = window;
		gbc = new GridBagConstraints();
		gbcLeft = new GridBagConstraints();
		lForButton = new ListenForButton();
		center = new JPanel();
		center.setLayout(new GridLayout(1, 1));
		center.setPreferredSize(new Dimension(1400, 800));
		center.setBackground(new Color(255, 0, 0));
		// center.add(new Login(this));
		center.setVisible(true);
		add(center);
		SM = new StateMachine(user);
		viewChoice = null;
		/*
		 * TODO FIX ORGANISATION
		 */

		getLoginPage();
	}

	public void getIndexPage() {
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
		// upperLeftPanel.setBackground(new Color(255, 0, 0));

		gbc.gridx = 0;
		gbc.gridy = 0;

		mainPanel.add(upperLeftPanel, gbc);

		// Upper right panel
		upperRightPanel = new JPanel();
		upperRightPanel.setPreferredSize(new Dimension(1200, 50));
		upperRightPanel.setLayout(new GridBagLayout());
		upperRightPanel.setVisible(true);
		// upperRightPanel.setBackground(new Color(0, 255, 0));

		gbc.gridx = 1;
		gbc.gridy = 0;

		mainPanel.add(upperRightPanel, gbc);
		getViewChoice();

		// Left panel
		leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(200, 750));
		leftPanel.setLayout(new GridBagLayout());
		leftPanel.setVisible(true);
		// leftPanel.setBackground(new Color(0, 0, 255));

		gbc.gridx = 0;
		gbc.gridy = 1;

		mainPanel.add(leftPanel, gbc);

		notificationPanel = new JPanel();
		notificationPanel.setPreferredSize(new Dimension(200, 150));
		notificationPanel.setVisible(true);
		// notificationPanel.setBackground(new Color(255, 0, 255));

		gbc.gridx = 0;
		gbc.gridy = 0;

		leftPanel.add(notificationPanel, gbc);

		getNotificationPage();

		addEventButtonPanel = new JPanel();
		addEventButtonPanel.setPreferredSize(new Dimension(200, 60));
		addEventButtonPanel.setLayout(new GridBagLayout());
		addEventButtonPanel.setVisible(true);
		// addEventButtonPanel.setBackground(new Color(0, 255, 0));

		gbc.gridx = 0;
		gbc.gridy = 1;

		leftPanel.add(addEventButtonPanel, gbc);

		addEventButton = new JButton("Skapa Event");
		addEventButton.addActionListener(lForButton);
		addEventButton.setFont(new Font("Serif", Font.PLAIN, 25));
		gbc.gridx = 0;
		gbc.gridy = 0;
		addEventButtonPanel.add(addEventButton, gbc);

		overviewPanel = new JPanel();
		overviewPanel.setPreferredSize(new Dimension(200, 240));
		overviewPanel.setVisible(true);
		overviewPanel.setLayout(new GridBagLayout());
		// overviewPanel.setBackground(new Color(100, 100, 100));

		gbc.gridx = 0;
		gbc.gridy = 2;

		leftPanel.add(overviewPanel, gbc);

		overviewPanel1 = new JPanel();
		overviewPanel1.setPreferredSize(new Dimension(200, 40));
		overviewPanel1.setVisible(true);
		// overviewPanel.setBackground(new Color(100, 100, 100));

		gbc.gridx = 0;
		gbc.gridy = 0;

		overviewPanel.add(overviewPanel1, gbc);

		overviewPanel2 = new JPanel();
		overviewPanel2.setPreferredSize(new Dimension(200, 200));
		overviewPanel2.setVisible(true);
		// overviewPanel.setBackground(new Color(100, 100, 100));

		gbc.gridx = 0;
		gbc.gridy = 1;

		overviewPanel.add(overviewPanel2, gbc);

		CalendarChoicePanel = new JPanel();
		CalendarChoicePanel.setPreferredSize(new Dimension(200, 300));
		CalendarChoicePanel.setVisible(true);
		// CalendarChoicePanel.setBackground(new Color(255, 0, 255));

		gbc.gridx = 0;
		gbc.gridy = 3;

		leftPanel.add(CalendarChoicePanel, gbc);

		calChoiceList();

		getOverview();
		// Right panel
		rightPanel = new JPanel();
		rightPanel.setPreferredSize(new Dimension(1200, 750));
		rightPanel.setLayout(new GridBagLayout());
		rightPanel.setVisible(true);
		rightPanel.setBackground(new Color(150, 150, 150));

		gbc.gridx = 1;
		gbc.gridy = 1;

		mainPanel.add(rightPanel, gbc);

		getViewViewer();

		center.updateUI();
	}

	public void getOverview() {
		overviewPanel1.removeAll();
		overviewPanel2.removeAll();

		if (calChooseList != null) {
			remove(calChooseList);
		}
		DateFormat tempFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date tempDate = null;
		try {
			tempDate = tempFormat.parse(SM.getFocusedDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		monthYearPanel = new MonthYearPanel(SM, this);
		overviewPanel1.add(monthYearPanel);
		monthOverview = new MonthOverview(SM, user, tempDate, false, this);
		overviewPanel2.add(monthOverview);
		overviewPanel1.updateUI();
		overviewPanel2.updateUI();

	}

	public void getNotificationPage() {
		notificationPanel.removeAll();
		notificationPanel.add(new NotificationView());
		notificationPanel.updateUI();
	}

	public void getRegisterPage() {
		center.removeAll();
		center.add(new Register(window, this));
		center.updateUI();
	}

	public void getLoginPage() {
		center.removeAll();
		center.add(new Login(window, this, user));
		center.updateUI();
	}

	public void getAddEventPage() {

		rightPanel.removeAll();
		rightPanel.add(new AddEvent(user, null, this, SM));
		rightPanel.updateUI();

	}

	public void getDeleteAndEditEventPage(Event event) {

		rightPanel.removeAll();
		rightPanel.add(new EditEvent(this, user, event));
		rightPanel.updateUI();

	}

	public void getAddCalendarPage() {
		rightPanel.removeAll();
		gbc.gridx = 0;
		gbc.gridy = 0;
		rightPanel.add(new CalAddEdit(SM, this, user, window), gbc);
		rightPanel.updateUI();
	}

	public void calChoiceList() {
		CalendarChoicePanel.removeAll();
		if (calChooseList != null) {
			remove(calChooseList);
		}
		gbc.gridx = 0;
		gbc.gridy = 6;
		calChooseList = new CalChooseList(user, SM, this);
		CalendarChoicePanel.add(calChooseList, gbc);
		CalendarChoicePanel.updateUI();
	}

	public void getViewViewer() {
		rightPanel.removeAll();
		gbc.gridx = 0;
		gbc.gridy = 0;
		switch (SM.getActiveview()) {
		case 1:
			rightPanel.add(new DayView(SM, user, this), gbc);
			break;
		case 2:
			rightPanel.add(new WeekView(SM, user, this), gbc);
			break;
		case 3:
			rightPanel.add(new MonthView(SM, user, this), gbc);
			break;
		case 4:
			rightPanel.add(new YearView(SM, user, this), gbc);
			break;
		default:
			rightPanel.add(new MonthView(SM, user, this), gbc);
			break;
		}
		rightPanel.updateUI();
	}

	public void getViewChoice() {
		upperRightPanel.removeAll();
		if (viewChoice != null) {
			remove(viewChoice);
		}
		viewChoice = new ViewChoice(SM, this);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 0, 0);
		upperRightPanel.add(viewChoice, gbc);
		upperRightPanel.updateUI();
	}

	public void getSharedEventPage() {
		rightPanel.removeAll();
		rightPanel.add(new SharedEventView(this));
		rightPanel.updateUI();
	}

	public void getSharedCalPage() {
		rightPanel.removeAll();
		rightPanel.add(new SharedCalView(this));
		rightPanel.updateUI();
	}

	public void sendUser(User user) {

		this.user = user;
	}

	private class ListenForButton implements ActionListener {

		// This method is called when an event occurs

		public void actionPerformed(ActionEvent e) {
			// Check if the source of the event was the button
			if (e.getSource() == addEventButton) {
				getAddEventPage();
			}

		}

	}
}
