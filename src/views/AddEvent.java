package views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import controller.DateLabelFormatter;
import controller.StateMachine;
import database.SQLManager;
import object.Calendar;
import object.Event;
import object.User;

public class AddEvent extends JPanel {

	private JPanel main, centerEvent, top, centerLeft, centerRight;
	private JLabel nameLabel, locationLabel, startTimeLabel, endTimeLabel, eventInfoLabel, eventDescLabel;
	private JTextField nameField, locationField, userSearchField;
	private DefaultListModel listModel;
	private Calendar[] calArray, eventCalArray;
	private JList<Object> userList;
	private JButton eventCreate, userSearchButton;
	private JTextArea eventDescArea;
	private JCheckBox fullDayActivity, notifications;
	private JSpinner startTimeSpinner, endTimeSpinner;
	private JSpinner.DateEditor startTimeEditor, endTimeEditor;
	private JDatePanelImpl startDatePanel, endDatePanel;
	private JDatePickerImpl startDatePicker, endDatePicker;
	private UtilDateModel startModel, endModel;
	private Properties startProperties, endProperties;
	private GridBagConstraints gbc, gbcLeft;
	private JComboBox<String> calDropDown;
	private boolean checkFields;
	private String eventCreateCalArray;
	private GregorianCalendar startGregCal, endGregCal;
	private ArrayList checklist;
	private User user;
	private Event event;
	private StateMachine SM;
	private WindowPanel wp;
	private Border etchedBorder;

	public AddEvent(User user, Event event, WindowPanel wp, StateMachine SM) {

		this.user = user;
		this.event = event;
		this.wp = wp;
		this.SM = SM;

		etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);

		startGregCal = new GregorianCalendar();
		endGregCal = new GregorianCalendar();

		checklist = new ArrayList();

		gbc = new GridBagConstraints();
		gbcLeft = new GridBagConstraints();
		// Panel management

		main = new JPanel();
		main.setPreferredSize(new Dimension(1175, 725));
		main.setLayout(new GridBagLayout());
		// main.setBackground(new Color(0, 255, 255));
		main.setVisible(true);

		this.add(main);

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
			if (eventCalArray[i].getCreator_id() == user.getId()) {
				eventCreateCalArray = eventCalArray[i].getName();
				calDropDown.addItem(eventCreateCalArray);
			}
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

		eventInfoLabel = new JLabel();
		eventInfoLabel.setText("Bjud in anv�ndare!");
		eventInfoLabel.setFont(new Font("Serif", Font.PLAIN, 20));

		gbcLeft.gridx = 0;
		gbcLeft.gridy = 0;
		gbcLeft.insets = new Insets(0, 0, 10, 0);
		gbcLeft.anchor = GridBagConstraints.WEST;

		centerRight.add(eventInfoLabel, gbcLeft);

		userSearchField = new JTextField();
		userSearchField.setPreferredSize(new Dimension(300, 30));

		gbcLeft.gridx = 0;
		gbcLeft.gridy = 1;
		gbcLeft.insets = new Insets(0, 0, 10, 10);
		gbcLeft.anchor = GridBagConstraints.WEST;

		centerRight.add(userSearchField, gbcLeft);

		userSearchButton = new JButton("S�k!");
		userSearchButton.setPreferredSize(new Dimension(60, 29));

		gbcLeft.gridx = 1;
		gbcLeft.gridy = 1;
		gbcLeft.insets = new Insets(0, 0, 10, 0);
		gbcLeft.anchor = GridBagConstraints.WEST;

		centerRight.add(userSearchButton, gbcLeft);

		userList = new JList();
		listModel = new DefaultListModel();
		userList.setPreferredSize(new Dimension(300, 410));
		userList.setModel(listModel);
		userList.setBorder(etchedBorder);

		JScrollPane listScrollPane = new JScrollPane(userList);

		gbcLeft.gridx = 0;
		gbcLeft.gridy = 2;
		gbcLeft.insets = new Insets(0, 0, 10, 0);
		gbcLeft.anchor = GridBagConstraints.WEST;

		centerRight.add(userList, gbcLeft);

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

		startTimeLabel = new JLabel("Start p� event");
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

		notifications = new JCheckBox();
		notifications.setText("Notifikationer?");
		notifications.setSelected(true);
		notifications.setToolTipText("Vill du f� notifikationer fr�n det h�r eventet?");

		gbcLeft.gridx = 1;
		gbcLeft.gridy = 5;
		gbcLeft.insets = new Insets(0, -120, 10, 0);
		gbcLeft.anchor = GridBagConstraints.WEST;

		centerLeft.add(notifications, gbcLeft);

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

		endTimeLabel = new JLabel("Slut p� event");
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

		eventDescLabel = new JLabel("Beskriv eventet h�r!");
		eventDescLabel.setFont(new Font("Serif", Font.PLAIN, 20));

		gbc.gridx = 0;
		gbc.gridy = 9;
		gbc.insets = new Insets(0, 0, 10, 0);

		centerLeft.add(eventDescLabel, gbc);

		eventDescArea = new JTextArea();
		eventDescArea.setPreferredSize(new Dimension(300, 100));
		eventDescArea.setLineWrap(true);
		eventDescArea.setWrapStyleWord(true);
		eventDescArea.setBorder(etchedBorder);

		gbc.gridx = 0;
		gbc.gridy = 10;
		gbc.insets = new Insets(0, 0, 10, 0);

		centerLeft.add(eventDescArea, gbc);

		String tempDate = SM.getFocusedDate();

		String tempStartDateYear = tempDate.substring(0, 4);
		String tempStartDateMonth = tempDate.substring(5, 7);
		String tempStartDateDay = tempDate.substring(8, 10);

		startDatePicker.setPreferredSize(new Dimension(202, 30));
		startDatePicker.getModel().setYear(Integer.parseInt(tempStartDateYear));
		startDatePicker.getModel().setMonth(Integer.parseInt(tempStartDateMonth) - 1);
		startDatePicker.getModel().setDay(Integer.parseInt(tempStartDateDay));
		startDatePicker.getModel().setSelected(true);

		endDatePicker.setPreferredSize(new Dimension(202, 30));
		endDatePicker.getModel().setYear(Integer.parseInt(tempStartDateYear));
		endDatePicker.getModel().setMonth(Integer.parseInt(tempStartDateMonth) - 1);
		endDatePicker.getModel().setDay(Integer.parseInt(tempStartDateDay));
		endDatePicker.getModel().setSelected(true);

		startTimeSpinner.setValue(startGregCal.getTime());
		endTimeSpinner.setValue(endGregCal.getTime());

		ListenForButton lForButton = new ListenForButton();

		fullDayActivity.addActionListener(lForButton);
		eventCreate.addActionListener(lForButton);
		userSearchButton.addActionListener(lForButton);
		gbc.insets = new Insets(0, 0, 0, 0);
	}

	private class ListenForButton implements ActionListener {

		// This method is called when an event occurs

		public void actionPerformed(ActionEvent e) {
			// Check if the source of the event was the button

			if (e.getSource() == userSearchButton) {
				String inputSearch = userSearchField.getText();
				Object[][] inputResult = SQLManager.searchForUser(inputSearch);

				listModel.removeAllElements();

				for (int i = 0; i < inputResult.length; i++) {
					listModel.addElement(inputResult[i][1]);

					checklist.add(inputResult[i][0]);

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

				checkFields = true;

				String inputEventName = nameField.getText();
				if (inputEventName.length() < 1) {
					JOptionPane.showMessageDialog(null, "Eventets namn m�ste vara minst 2 bokst�ver l�ngt!");
					checkFields = false;
				}

				String inputEventLocation = locationField.getText();
				if (inputEventLocation.length() < 1) {
					JOptionPane.showMessageDialog(null, "Eventets plats m�ste vara minst tv� bokst�ver l�ngt!");
					checkFields = false;
				}

				String inputEventTextArea = eventDescArea.getText();
				int inputFullDayEvent;
				int inputEventStartDay = 0;
				int inputEventStartMonth = 0;
				int inputEventStartYear = 0;
				int inputEventEndDay = 0;
				int inputEventEndMonth = 0;
				int inputEventEndYear = 0;
				int inputCreateEventForCalendarId = user.getCalArray()[calDropDown.getSelectedIndex()].getCal_id();
				int inputNotifications;
				String inputEventStartTime = "";
				String inputEventEndTime = "";

				String formatStartDate = "";
				String formatEndDate = "";

				if (notifications.isSelected()) {
					inputNotifications = 1;
				} else {
					inputNotifications = 0;
				}

				if (fullDayActivity.isSelected()) {

					inputFullDayEvent = 1;

					inputEventStartYear = startDatePicker.getModel().getYear();
					inputEventStartMonth = startDatePicker.getModel().getMonth();
					inputEventStartDay = startDatePicker.getModel().getDay();

					inputEventEndYear = endDatePicker.getModel().getYear();
					inputEventEndMonth = endDatePicker.getModel().getMonth();
					inputEventEndDay = endDatePicker.getModel().getDay();

					int checkStart = inputEventStartYear + inputEventStartMonth + inputEventStartDay;
					int checkEnd = inputEventEndYear + inputEventEndMonth + inputEventEndDay;

					if (inputEventStartYear < inputEventEndYear) {
						checkFields = true;
					} else if (checkStart > checkEnd) {
						JOptionPane.showMessageDialog(null, "Eventet kan inte sluta innan det b�rjat!");
						checkFields = false;

					}

					formatStartDate = inputEventStartYear + "-" + (inputEventStartMonth + 1) + "-" + inputEventStartDay
							+ " 01:01:01";
					formatEndDate = inputEventEndYear + "-" + (inputEventEndMonth + 1) + "-" + inputEventEndDay
							+ " 01:01:01";
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

					int checkStart = inputEventStartYear + inputEventStartMonth + inputEventStartDay;
					int checkEnd = inputEventEndYear + inputEventEndMonth + inputEventEndDay;

					int checkStartTime = (Integer.parseInt(inputEventStartTime.substring(0, 2)) * 60)
							+ Integer.parseInt(inputEventStartTime.substring(3, 5));
					int checkEndTime = (Integer.parseInt(inputEventEndTime.substring(0, 2)) * 60)
							+ Integer.parseInt(inputEventEndTime.substring(3, 5));

					if (checkEnd < checkStart) {
						JOptionPane.showMessageDialog(null, "Eventet kan inte sluta innan det b�rjat!");
						checkFields = false;
					}

					if (checkEnd == checkStart) {
						if (checkEndTime <= checkStartTime) {
							JOptionPane.showMessageDialog(null, "Eventet slutar innan det b�rjar eller �r f�r kort!");
							checkFields = false;
						}
					}

					formatStartDate = inputEventStartYear + "-" + (inputEventStartMonth + 1) + "-" + inputEventStartDay
							+ " " + inputEventStartTime + ":01";
					formatEndDate = inputEventEndYear + "-" + (inputEventEndMonth + 1) + "-" + inputEventEndDay + " "
							+ inputEventEndTime + ":01";
				}

				if (checkFields == true) {
					SQLManager.addEvent(inputEventName, inputEventLocation, inputEventTextArea, inputFullDayEvent,
							inputCreateEventForCalendarId, formatStartDate, formatEndDate, inputNotifications);

					// Send invite request to people in the list

					if (listModel.size() > 0) {
						for (int i = 0; i < userList.getSelectedIndices().length; i++) {

							Object selectedUserId = checklist.get(userList.getSelectedIndices()[i]);
							int tempSelectedUserId = Integer.parseInt((String) selectedUserId);
							System.out.println(
									tempSelectedUserId + " ---------------------- Users id ------------------------ ");

							SQLManager.sendEventInvite(tempSelectedUserId);

							wp.getNotificationPage();

						}
					}

					user.reloadarrays();

					wp.getViewViewer();

					wp.getInviteView();

				}

			}

		}

	}
}
