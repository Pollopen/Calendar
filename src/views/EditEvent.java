package views;

import java.awt.Color;
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

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import controller.DateLabelFormatter;
import database.SQLManager;
import object.Event;
import object.User;

public class EditEvent extends JPanel {

	private JPanel editEventMain, editEventTop, editEventCenter, editEventLeft, editEventRight;

	private JLabel editEventNameLabel, editEventLocationLabel, editEventStartLabel, editEventEndLabel,
			editEventDescLabel, editEventInfoLabel;
	private JTextField editEventNameField, editEventLocationField, userEditSearchField;
	private DefaultListModel editListModel;
	private JList<Object> userEditList;
	private Event[] editEventArray;
	private JButton editEventButton, deleteEventButton, userEditSearchButton;
	private JTextArea editEventDescArea;
	private JCheckBox editFullDayActivity;
	private JSpinner editStartTimeSpinner, editEndTimeSpinner;
	private JSpinner.DateEditor editStartTimeEditor, editEndTimeEditor;
	private JDatePanelImpl editStartDatePanel, editEndDatePanel;
	private JDatePickerImpl editStartDatePicker, editEndDatePicker;
	private UtilDateModel editStartModel, editEndModel;
	private Properties editStartProperties, editEndProperties;
	private GridBagConstraints gbc, gbcLeft;
	private JComboBox<String> editEventDropDown;
	private String editEventObjectArray;
	private GregorianCalendar editStartGregCal, editEndGregCal;
	private WindowPanel wp;
	private User user;
	private ArrayList checkEditList;

	public EditEvent(WindowPanel wp, User user) {
		
		this.wp = wp;
		this.user = user;

		checkEditList = new ArrayList();
		
		gbc = new GridBagConstraints();
		gbcLeft = new GridBagConstraints();
		
		editEventMain = new JPanel();
		editEventMain.setPreferredSize(new Dimension(1175, 725));
		editEventMain.setLayout(new GridBagLayout());
		editEventMain.setVisible(true);

		this.add(editEventMain);

		editEventTop = new JPanel();
		editEventTop.setPreferredSize(new Dimension(1175, 50));
		editEventTop.setLayout(new GridBagLayout());
		editEventTop.setBackground(new Color(255, 0, 0));
		editEventTop.setVisible(true);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 0, 0);

		editEventMain.add(editEventTop, gbc);

		editEventCenter = new JPanel();
		editEventCenter.setPreferredSize(new Dimension(1175, 675));
		editEventCenter.setLayout(new GridBagLayout());
		// center.setBackground(new Color(0, 255, 0));
		editEventCenter.setVisible(true);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(0, 0, 0, 0);

		editEventMain.add(editEventCenter, gbc);

		editEventLeft = new JPanel();
		editEventLeft.setPreferredSize(new Dimension(700, 675));
		editEventLeft.setLayout(new GridBagLayout());
		// editEventLeft.setBackground(new Color(0, 255, 0));
		editEventLeft.setVisible(true);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 0, 0);

		editEventCenter.add(editEventLeft, gbc);

		editEventRight = new JPanel();
		editEventRight.setPreferredSize(new Dimension(475, 675));
		editEventRight.setLayout(new GridBagLayout());
		// editEventRight.setBackground(new Color(0, 0, 255));
		editEventRight.setVisible(true);

		editEventInfoLabel = new JLabel();
		editEventInfoLabel.setText("Bjud in användare!");
		editEventInfoLabel.setFont(new Font("Serif", Font.PLAIN, 20));

		gbcLeft.gridx = 0;
		gbcLeft.gridy = 0;
		gbcLeft.insets = new Insets(0, 0, 10, 0);
		gbcLeft.anchor = GridBagConstraints.WEST;

		editEventRight.add(editEventInfoLabel, gbcLeft);

		userEditSearchField = new JTextField();
		userEditSearchField.setPreferredSize(new Dimension(300, 30));

		gbcLeft.gridx = 0;
		gbcLeft.gridy = 1;
		gbcLeft.insets = new Insets(0, 0, 10, 10);
		gbcLeft.anchor = GridBagConstraints.WEST;

		editEventRight.add(userEditSearchField, gbcLeft);

		userEditSearchButton = new JButton("Sök!");
		userEditSearchButton.setPreferredSize(new Dimension(60, 29));

		gbcLeft.gridx = 1;
		gbcLeft.gridy = 1;
		gbcLeft.insets = new Insets(0, 0, 10, 0);
		gbcLeft.anchor = GridBagConstraints.WEST;

		editEventRight.add(userEditSearchButton, gbcLeft);

		userEditList = new JList();
		editListModel = new DefaultListModel();
		userEditList.setModel(editListModel);
		userEditList.setPreferredSize(new Dimension(300, 410));

		JScrollPane listScrollPane = new JScrollPane(userEditList);

		gbcLeft.gridx = 0;
		gbcLeft.gridy = 2;
		gbcLeft.insets = new Insets(0, 0, 10, 0);
		gbcLeft.anchor = GridBagConstraints.WEST;

		editEventRight.add(userEditList, gbcLeft);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 0, 0);

		editEventCenter.add(editEventRight, gbc);

		editEventArray = user.getEventArray();
		editEventDropDown = new JComboBox<String>();

		for (int i = 0; i < editEventArray.length; i++) {
			editEventObjectArray = editEventArray[i].getName() + " " + editEventArray[i].getCal_id();
			editEventDropDown.addItem(editEventObjectArray);
		}

		gbcLeft.gridx = 0;
		gbcLeft.gridy = 0;
		gbcLeft.insets = new Insets(0, 0, 0, 10);
		gbcLeft.anchor = GridBagConstraints.WEST;

		editEventTop.add(editEventDropDown, gbcLeft);

		gbcLeft.gridx = 1;
		gbcLeft.gridy = 0;
		gbcLeft.insets = new Insets(0, 0, 0, 10);
		gbcLeft.anchor = GridBagConstraints.WEST;

		editEventButton = new JButton("Spara");

		editEventTop.add(editEventButton, gbcLeft);

		deleteEventButton = new JButton("Ta bort!");

		gbcLeft.gridx = 2;
		gbcLeft.gridy = 0;
		gbcLeft.insets = new Insets(0, 0, 0, 10);
		gbcLeft.anchor = GridBagConstraints.WEST;

		editEventTop.add(deleteEventButton, gbcLeft);

		// Event label + event field
		editEventNameLabel = new JLabel("Event namn");
		editEventNameLabel.setFont(new Font("Serif", Font.PLAIN, 20));

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 10, 0);

		editEventLeft.add(editEventNameLabel, gbc);

		editEventNameField = new JTextField();
		editEventNameField.setPreferredSize(new Dimension(300, 30));

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(0, 0, 10, 0);

		editEventLeft.add(editEventNameField, gbc);

		// Event location label + location field

		editEventLocationLabel = new JLabel("Plats");
		editEventLocationLabel.setFont(new Font("Serif", Font.PLAIN, 20));

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.insets = new Insets(0, 0, 10, 0);

		editEventLeft.add(editEventLocationLabel, gbc);

		editEventLocationField = new JTextField();
		editEventLocationField.setPreferredSize(new Dimension(300, 30));

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.insets = new Insets(0, 0, 10, 0);

		editEventLeft.add(editEventLocationField, gbc);

		// Event start time + end time

		editEventStartLabel = new JLabel("Start på event");
		editEventStartLabel.setFont(new Font("Serif", Font.PLAIN, 20));

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.insets = new Insets(0, 0, 10, 0);

		editEventLeft.add(editEventStartLabel, gbc);

		editFullDayActivity = new JCheckBox();
		editFullDayActivity.setText("Heldags aktivitet?");

		gbcLeft.gridx = 0;
		gbcLeft.gridy = 5;
		gbcLeft.insets = new Insets(0, 0, 10, 0);
		gbcLeft.anchor = GridBagConstraints.WEST;

		editEventLeft.add(editFullDayActivity, gbcLeft);

		editStartModel = new UtilDateModel();
		// model.setDate(20,04,2014);
		// Need this...
		editStartProperties = new Properties();
		editStartProperties.put("text.today", "Today");
		editStartProperties.put("text.month", "Month");
		editStartProperties.put("text.year", "Year");

		editStartDatePanel = new JDatePanelImpl(editStartModel, editStartProperties);
		editStartDatePicker = new JDatePickerImpl(editStartDatePanel, new DateLabelFormatter());
		editStartDatePicker.setTextEditable(false);
		// startDatePicker.setPreferredSize(new Dimension(300, 30))

		gbcLeft.gridx = 0;
		gbcLeft.gridy = 6;
		gbcLeft.insets = new Insets(0, 0, 10, 0);
		gbcLeft.anchor = GridBagConstraints.WEST;

		editEventLeft.add(editStartDatePicker, gbcLeft);

		editStartTimeSpinner = new JSpinner(new SpinnerDateModel());
		editStartTimeEditor = new JSpinner.DateEditor(editStartTimeSpinner, "HH:mm");
		editStartTimeSpinner.setEditor(editStartTimeEditor);
		editStartTimeEditor.setPreferredSize(new Dimension(50, 23));

		gbcLeft.gridx = 0;
		gbcLeft.gridy = 6;
		gbcLeft.insets = new Insets(0, 225, 10, 0);
		gbcLeft.anchor = GridBagConstraints.WEST;

		editEventLeft.add(editStartTimeSpinner, gbcLeft);

		editEventEndLabel = new JLabel("Slut på event");
		editEventEndLabel.setFont(new Font("Serif", Font.PLAIN, 20));

		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.insets = new Insets(0, 0, 10, 0);

		editEventLeft.add(editEventEndLabel, gbc);

		editEndModel = new UtilDateModel();
		// model.setDate(20,04,2014);
		// Need this...
		editEndProperties = new Properties();
		editEndProperties.put("text.today", "Today");
		editEndProperties.put("text.month", "Month");
		editEndProperties.put("text.year", "Year");

		editEndDatePanel = new JDatePanelImpl(editEndModel, editEndProperties);
		editEndDatePicker = new JDatePickerImpl(editEndDatePanel, new DateLabelFormatter());
		editEndDatePicker.setTextEditable(false);
		// endDatePicker.setPreferredSize(new Dimension(200, 30));

		gbcLeft.gridx = 0;
		gbcLeft.gridy = 8;
		gbcLeft.insets = new Insets(0, 0, 10, 0);
		gbcLeft.anchor = GridBagConstraints.WEST;

		editEventLeft.add(editEndDatePicker, gbcLeft);

		editEndTimeSpinner = new JSpinner(new SpinnerDateModel());
		editEndTimeEditor = new JSpinner.DateEditor(editEndTimeSpinner, "HH:mm");
		editEndTimeSpinner.setEditor(editEndTimeEditor);
		editEndTimeEditor.setPreferredSize(new Dimension(50, 23));

		gbcLeft.gridx = 0;
		gbcLeft.gridy = 8;
		gbcLeft.insets = new Insets(0, 225, 10, 0);
		gbcLeft.anchor = GridBagConstraints.WEST;

		editEventLeft.add(editEndTimeSpinner, gbcLeft);

		editEventDescLabel = new JLabel("Beskriv eventet här!");
		editEventDescLabel.setFont(new Font("Serif", Font.PLAIN, 20));

		gbc.gridx = 0;
		gbc.gridy = 9;
		gbc.insets = new Insets(0, 0, 10, 0);

		editEventLeft.add(editEventDescLabel, gbc);

		editEventDescArea = new JTextArea();
		editEventDescArea.setPreferredSize(new Dimension(300, 100));
		editEventDescArea.setLineWrap(true);
		editEventDescArea.setWrapStyleWord(true);

		gbc.gridx = 0;
		gbc.gridy = 10;
		gbc.insets = new Insets(0, 0, 10, 0);

		editEventLeft.add(editEventDescArea, gbc);

		ListenForButton lForButton = new ListenForButton();

		editFullDayActivity.addActionListener(lForButton);
		editEventDropDown.addActionListener(lForButton);
		editEventButton.addActionListener(lForButton);
		deleteEventButton.addActionListener(lForButton);
		userEditSearchButton.addActionListener(lForButton);
		gbc.insets = new Insets(0, 0, 0, 0);

	}
	
	public String addZero(int value) {
		if (value <= 9) {
			if (value == 0) {
				return "01";
			} else {
				return "0" + value;
			}

		} else {
			return "" + value;
		}

	}


	private class ListenForButton implements ActionListener {

		// This method is called when an event occurs

		public void actionPerformed(ActionEvent e) {
			// Check if the source of the event was the button
			if (e.getSource() == editFullDayActivity) {

				if (editFullDayActivity.isSelected()) {
					// centerLeft.remove(startTimeSpinner);
					// centerLeft.remove(endTimeSpinner);
					editStartDatePicker.setPreferredSize(new Dimension(300, 30));
					editEndDatePicker.setPreferredSize(new Dimension(300, 30));
					editStartTimeSpinner.setVisible(false);
					editEndTimeSpinner.setVisible(false);
					editEventLeft.updateUI();

				} else {

					editStartDatePicker.setPreferredSize(new Dimension(202, 30));
					editEndDatePicker.setPreferredSize(new Dimension(202, 30));
					editStartTimeSpinner.setVisible(true);
					editEndTimeSpinner.setVisible(true);
					editEventLeft.updateUI();
				}
			}

			if (e.getSource() == editEventDropDown) {
				String inputEditEventName = user.getEventArray()[editEventDropDown.getSelectedIndex()].getName();
				String inputEditEventLocation = user.getEventArray()[editEventDropDown.getSelectedIndex()].getLoc();
				String inputEditEventTextArea = user.getEventArray()[editEventDropDown.getSelectedIndex()]
						.getDescription();
				String inputEditEventStartDate = user.getEventArray()[editEventDropDown.getSelectedIndex()]
						.getStart_time();
				String inputEditEventEndDate = user.getEventArray()[editEventDropDown.getSelectedIndex()].getEnd_time();
				String inputEditEventStartTime = user.getEventArray()[editEventDropDown.getSelectedIndex()]
						.getStart_time();
				String inputEditEventEndTime = user.getEventArray()[editEventDropDown.getSelectedIndex()].getEnd_time();
				int inputEditEventFullDay = user.getEventArray()[editEventDropDown.getSelectedIndex()]
						.getEvent_full_day();
				boolean inputTempBool = false;

				String tempStartDateYear = inputEditEventStartDate.substring(0, 4);
				String tempStartDateMonth = inputEditEventStartDate.substring(5, 7);
				String tempStartDateDay = inputEditEventStartDate.substring(8, 10);

				String tempEndDateYear = inputEditEventEndDate.substring(0, 4);
				String tempEndDateMonth = inputEditEventEndDate.substring(5, 7);
				String tempEndDateDay = inputEditEventEndDate.substring(8, 10);

				String tempStartTimeHour = inputEditEventStartTime.substring(11, 13);
				String tempStartTimeMinute = inputEditEventStartTime.substring(14, 16);

				String tempEndTimeHour = inputEditEventEndTime.substring(11, 13);
				String tempEndTimeMinute = inputEditEventEndTime.substring(14, 16);

				editStartGregCal = new GregorianCalendar();
				editEndGregCal = new GregorianCalendar();

				editStartGregCal.set(java.util.Calendar.YEAR, Integer.parseInt(tempStartDateYear));
				editStartGregCal.set(java.util.Calendar.MONTH, Integer.parseInt(tempStartDateMonth));
				editStartGregCal.set(java.util.Calendar.DAY_OF_MONTH, Integer.parseInt(tempStartDateDay));

				editEndGregCal.set(java.util.Calendar.YEAR, Integer.parseInt(tempEndDateYear));
				editEndGregCal.set(java.util.Calendar.MONTH, Integer.parseInt(tempEndDateMonth));
				editEndGregCal.set(java.util.Calendar.DAY_OF_MONTH, Integer.parseInt(tempEndDateDay));

				editStartGregCal.set(java.util.Calendar.HOUR_OF_DAY, Integer.parseInt(tempStartTimeHour));
				editStartGregCal.set(java.util.Calendar.MINUTE, Integer.parseInt(tempStartTimeMinute));

				editEndGregCal.set(java.util.Calendar.HOUR_OF_DAY, Integer.parseInt(tempEndTimeHour));
				editEndGregCal.set(java.util.Calendar.MINUTE, Integer.parseInt(tempEndTimeMinute));

				if (inputEditEventFullDay == 0) {
					inputTempBool = false;
				} else {
					inputTempBool = true;
				}

				editEventNameField.setText(inputEditEventName);
				editEventLocationField.setText(inputEditEventLocation);
				editEventDescArea.setText(inputEditEventTextArea);
				editFullDayActivity.setSelected(inputTempBool);

				if (editFullDayActivity.isSelected()) {
					// centerLeft.remove(startTimeSpinner);
					// centerLeft.remove(endTimeSpinner);
					editStartDatePicker.setPreferredSize(new Dimension(300, 30));
					editStartDatePicker.getModel().setYear(Integer.parseInt(tempEndDateYear));
					editStartDatePicker.getModel().setMonth(Integer.parseInt(tempEndDateMonth));
					editStartDatePicker.getModel().setDay(Integer.parseInt(tempEndDateDay));
					editStartDatePicker.getModel().setSelected(true);

					editEndDatePicker.setPreferredSize(new Dimension(300, 30));
					editEndDatePicker.getModel().setYear(Integer.parseInt(tempEndDateYear));
					editEndDatePicker.getModel().setMonth(Integer.parseInt(tempEndDateMonth));
					editEndDatePicker.getModel().setDay(Integer.parseInt(tempEndDateDay));
					editEndDatePicker.getModel().setSelected(true);

					editStartTimeSpinner.setVisible(false);
					editStartTimeSpinner.setValue(editStartGregCal.getTime());

					editEndTimeSpinner.setVisible(false);
					editEndTimeSpinner.setValue(editEndGregCal.getTime());

					editEventLeft.updateUI();
				} else {

					editStartDatePicker.setPreferredSize(new Dimension(202, 30));
					editStartDatePicker.getModel().setYear(Integer.parseInt(tempEndDateYear));
					editStartDatePicker.getModel().setMonth(Integer.parseInt(tempEndDateMonth));
					editStartDatePicker.getModel().setDay(Integer.parseInt(tempEndDateDay));
					editStartDatePicker.getModel().setSelected(true);

					editEndDatePicker.setPreferredSize(new Dimension(202, 30));
					editEndDatePicker.getModel().setYear(Integer.parseInt(tempEndDateYear));
					editEndDatePicker.getModel().setMonth(Integer.parseInt(tempEndDateMonth));
					editEndDatePicker.getModel().setDay(Integer.parseInt(tempEndDateDay));
					editEndDatePicker.getModel().setSelected(true);

					editStartTimeSpinner.setVisible(true);
					editEndTimeSpinner.setVisible(true);

					editStartTimeSpinner.setValue(editStartGregCal.getTime());
					editEndTimeSpinner.setValue(editEndGregCal.getTime());

					editEventLeft.updateUI();

				}

			}

			if (e.getSource() == userEditSearchButton) {
				String inputEditSearch = userEditSearchField.getText();
				Object[][] inputEditResult = SQLManager.searchForUser(inputEditSearch);

				editListModel.removeAllElements();

				for (int i = 0; i < inputEditResult.length; i++) {

					editListModel.addElement(inputEditResult[i][1]);

					checkEditList.add(inputEditResult[i][0]);

					System.out.println(inputEditResult[i][0]);
				}
			}

			if (e.getSource() == editEventButton) {

				String inputEventName = editEventNameField.getText();
				String inputEventLocation = editEventLocationField.getText();
				String inputEventTextArea = editEventDescArea.getText();
				int inputFullDayEvent;
				int inputEventStartDay = 0;
				int inputEventStartMonth = 0;
				int inputEventStartYear = 0;
				int inputEventEndDay = 0;
				int inputEventEndMonth = 0;
				int inputEventEndYear = 0;
				int inputEventId = user.getEventArray()[editEventDropDown.getSelectedIndex()].getEvent_id();
				String inputEventStartTime = "";
				String inputEventEndTime = "";

				String formatStartDate = "";
				String formatEndDate = "";

				if (editFullDayActivity.isSelected()) {

					inputFullDayEvent = 1;

					inputEventStartYear = editStartDatePicker.getModel().getYear();
					inputEventStartMonth = editStartDatePicker.getModel().getMonth();
					inputEventStartDay = editStartDatePicker.getModel().getDay();

					inputEventEndYear = editEndDatePicker.getModel().getYear();
					inputEventEndMonth = editEndDatePicker.getModel().getMonth();
					inputEventEndDay = editEndDatePicker.getModel().getDay();

					formatStartDate = inputEventStartYear + "-" + addZero(inputEventStartMonth) + "-"
							+ inputEventStartDay + " 01:01:01";
					formatEndDate = inputEventEndYear + "-" + addZero(inputEventEndMonth) + "-" + inputEventEndDay
							+ " 01:01:01";
				} else {

					inputFullDayEvent = 0;

					inputEventStartYear = editStartDatePicker.getModel().getYear();
					inputEventStartMonth = editStartDatePicker.getModel().getMonth();
					inputEventStartDay = editStartDatePicker.getModel().getDay();

					inputEventEndYear = editEndDatePicker.getModel().getYear();
					inputEventEndMonth = editEndDatePicker.getModel().getMonth();
					inputEventEndDay = editEndDatePicker.getModel().getDay();

					inputEventStartTime = editStartTimeEditor.getFormat().format(editStartTimeSpinner.getValue());
					inputEventEndTime = editEndTimeEditor.getFormat().format(editEndTimeSpinner.getValue());

					formatStartDate = inputEventStartYear + "-" + addZero(inputEventStartMonth) + "-"
							+ inputEventStartDay + " " + inputEventStartTime + ":01";
					formatEndDate = inputEventEndYear + "-" + addZero(inputEventEndMonth) + "-" + inputEventEndDay + " "
							+ inputEventEndTime + ":01";
				}

				// System.out.println(inputEventStartDay);
				// System.out.println(inputEventStartMonth);
				// System.out.println(inputEventStartYear);

				// System.out.println("Start date: " + formatStartDate);
				// System.out.println("End date: " + formatEndDate);
				//
				// System.out.println(inputCreateEventForCalendarId);

				SQLManager.editEvent(inputEventName, inputEventLocation, inputEventTextArea, inputFullDayEvent,
						inputEventId, formatStartDate, formatEndDate);

				user.reloadarrays();

				if (editListModel.size() > 0) {
					for (int i = 0; i < userEditList.getSelectedIndices().length; i++) {

						Object selectedUserId = checkEditList.get(userEditList.getSelectedIndices()[i]);
						int tempSelectedUserId = Integer.parseInt((String) selectedUserId);
						// System.out.println(
						// tempSelectedUserId + " ---------------------- Users
						// id ------------------------ ");

						SQLManager.sendEventInvite(tempSelectedUserId);

					}
				}

				wp.getDeleteAndEditEventPage();

				wp.getNotificationPage();
			}

			if (e.getSource() == deleteEventButton) {

				int eventId = user.getEventArray()[editEventDropDown.getSelectedIndex()].getEvent_id();

				SQLManager.deleteEvent(eventId);

				user.reloadarrays();

				wp.getDeleteAndEditEventPage();

				wp.getNotificationPage();
			}

		}

	}

}
