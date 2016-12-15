package views.calendar.addedit;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import controller.StateMachine;
import database.SQLManager;
import object.Calendar;
import object.User;
import views.Window;
import views.WindowPanel;

public class CalAddEdit extends JPanel {
	private JPanel calAddPanel, addCalCenterLeft, addCalCenterRight, editCalendar1, editCal2Panel, editCalendar3,
			editCal2RPanel, editCal2LPanel;
	private GridBagConstraints gbc, gbcLeft;
	private Calendar[] calArray;
	private JTextField calAddNameField, calEditNameField, calendarNameField, userSearchField;
	private JTextArea calAddDescTextArea, calendarDescTextArea, calEditDescTextArea;
	private JLabel calManageLabel, calAddDescLabel, calAddNameLabel, calAddLabel, calendarDescLabel, calendarNameLabel,
			calEditDescLabel, calEditNameLabel, calInfoLabel;
	private JButton calAddButton, calSaveButton, calRemoveButton, userSearchButton;
	private JCheckBox calAddNotBox, calEditNotBox;
	private User user;
	private CalEditList calEditList;
	private StateMachine SM;
	private WindowPanel wp;
	private ListenForButton lForButton;
	private DefaultListModel listModel;
	private JList userList;
	private ArrayList checkList;
	private Border etchedBorder;
	private Window window;

	public CalAddEdit(StateMachine SM, WindowPanel wp, User user, Window window) {
		this.window=window;
		this.user = user;
		this.SM = SM;
		this.wp = wp;
		lForButton = new ListenForButton();
		gbc = new GridBagConstraints();
		// Panel management
		etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);

		setPreferredSize(new Dimension(1175, 725));
		setLayout(new GridBagLayout());
		// addCalMain.setBackground(new Color(0, 255, 255));
		setVisible(true);

		addCalCenterLeft = new JPanel();
		addCalCenterLeft.setPreferredSize(new Dimension(500, 725));
		addCalCenterLeft.setLayout(new GridBagLayout());
		// addCalCenterLeft.setBackground(new Color(0, 255, 0));
		addCalCenterLeft.setVisible(true);

		gbc.gridx = 0;
		gbc.gridy = 0;

		add(addCalCenterLeft, gbc);

		calendarAdd();

		addCalCenterRight = new JPanel();
		addCalCenterRight.setPreferredSize(new Dimension(675, 725));
		addCalCenterRight.setLayout(new GridBagLayout());
		addCalCenterRight.setBackground(new Color(0, 0, 255));
		addCalCenterRight.setVisible(true);

		gbc.gridx = 1;
		gbc.gridy = 0;

		checkList = new ArrayList();

		add(addCalCenterRight, gbc);

		calendarEdit(SM.getCalEditStatus());

	}

	public void calendarAdd() {
		addCalCenterLeft.removeAll();
		// *name**people in calender**notification*
		// add new
		calAddPanel = new JPanel();
		calAddPanel.setBorder(etchedBorder);
		calAddPanel.setLayout(new GridBagLayout());
		calAddPanel.setPreferredSize(new Dimension(375, 400));
		calArray = user.getCalArray();
		calAddLabel = new JLabel("Lägg till kalender:");
		calAddLabel.setFont(new Font("Serif", Font.PLAIN, 30));
		calAddNameLabel = new JLabel("Kalendernamn:");
		calAddNameLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		calAddNameField = new JTextField();
		calAddNameField.setPreferredSize(new Dimension(300, 30));
		calAddDescLabel = new JLabel("Beskrivning:");
		calAddDescLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		calAddDescTextArea = new JTextArea();
		calAddDescTextArea.setPreferredSize(new Dimension(300, 100));
		calAddDescTextArea.setRows(5);
		calAddDescTextArea.setColumns(27);
		calAddDescTextArea.setLineWrap(true);
		calAddDescTextArea.setWrapStyleWord(true);
		calAddDescTextArea.setBorder(etchedBorder);
		calAddNotBox = new JCheckBox("Notifikationer?");
		calAddNotBox.setOpaque(false);
		calAddNotBox.setSelected(true);
		calAddButton = new JButton("Lägg till kalender");
		calAddButton.addActionListener(lForButton);

		gbc.gridx = 0;
		gbc.gridy = 0;
		addCalCenterLeft.add(calAddPanel, gbc);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 25, 0);
		calAddPanel.add(calAddLabel, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(0, 0, 5, 0);
		calAddPanel.add(calAddNameLabel, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		calAddPanel.add(calAddNameField, gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		calAddPanel.add(calAddDescLabel, gbc);
		gbc.gridx = 0;
		gbc.gridy = 4;
		calAddPanel.add(calAddDescTextArea, gbc);
		gbc.gridx = 0;
		gbc.gridy = 5;
		calAddPanel.add(calAddNotBox, gbc);
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.insets = new Insets(25, 0, 0, 0);
		calAddPanel.add(calAddButton, gbc);
		gbc.insets = new Insets(0, 0, 0, 0);
	}

	public void calList() {
		if (calEditList != null) {
			remove(calEditList);
		}
		gbc.gridx = 0;
		gbc.gridy = 0;
		editCal2LPanel.add(calManageLabel, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		calEditList = new CalEditList(calArray, SM, wp, this,user);
		editCal2LPanel.add(calEditList, gbc);

	}

	public void calendarEdit(int calID) {
		addCalCenterRight.removeAll();
		
		editCalendar1 = new JPanel();
		editCalendar1.setPreferredSize(new Dimension(675, 75));
		editCalendar1.setLayout(new GridBagLayout());
		editCalendar1.setBorder(etchedBorder);
		// editCalendar1.setBackground(new Color(255, 0, 0));
		editCalendar1.setVisible(true);

		calEditNotBox = new JCheckBox("Notifikationer?");
		calEditNotBox.setOpaque(false);
		try {
			if (calArray[calID].getNotification() == 1) {
				calEditNotBox.setSelected(true);
			} else {
				calEditNotBox.setSelected(false);
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			calID--;
			SM.setCalEditStatus(calID);
			if (calArray[calID].getNotification() == 1) {
				calEditNotBox.setSelected(true);
			} else {
				calEditNotBox.setSelected(false);
			}
		}

		calSaveButton = new JButton("Spara");
		calSaveButton.setFont(new Font("Serif", Font.PLAIN, 20));

		calSaveButton.addActionListener(lForButton);

		calRemoveButton = new JButton("Ta bort");
		calRemoveButton.setFont(new Font("Serif", Font.PLAIN, 20));

		if (calID == 0) {
			calRemoveButton.setEnabled(false);
		} else {
			calRemoveButton.setEnabled(true);
		}
		calRemoveButton.addActionListener(lForButton);

		editCal2Panel = new JPanel();
		editCal2Panel.setPreferredSize(new Dimension(675, 350));
		editCal2Panel.setLayout(new GridBagLayout());
		// editCal2Panel.setBackground(new Color(100, 255, 100));
		editCal2Panel.setVisible(true);

		editCal2LPanel = new JPanel();
		editCal2LPanel.setPreferredSize(new Dimension(225, 350));
		editCal2LPanel.setLayout(new GridBagLayout());
		// editCal2LPanel.setBackground(new Color(255, 0, 0));
		editCal2LPanel.setVisible(true);
		editCal2RPanel = new JPanel();
		editCal2RPanel.setPreferredSize(new Dimension(450, 350));
		editCal2RPanel.setLayout(new GridBagLayout());
		// editCal2RPanel.setBackground(new Color(0, 255, 0));
		editCal2RPanel.setVisible(true);

		editCal2LPanel.setBorder(etchedBorder);
		
		editCalendar3 = new JPanel();
		editCalendar3.setPreferredSize(new Dimension(675, 300));
		editCalendar3.setLayout(new GridBagLayout());
		// editCalendar3.setBackground(new Color(0, 0, 255));
		editCalendar3.setVisible(true);
		
		gbcLeft = new GridBagConstraints();

		calInfoLabel = new JLabel();
		calInfoLabel.setText("Sök efter användare!");

		gbcLeft.gridx = 0;
		gbcLeft.gridy = 0;
		gbcLeft.insets = new Insets(0, 0, 10, 0);
		gbcLeft.anchor = GridBagConstraints.WEST;

		editCalendar3.add(calInfoLabel, gbcLeft);

		userSearchField = new JTextField();
		userSearchField.setPreferredSize(new Dimension(300, 30));

		gbcLeft.gridx = 0;
		gbcLeft.gridy = 1;
		gbcLeft.insets = new Insets(0, 0, 10, 10);
		gbcLeft.anchor = GridBagConstraints.WEST;

		editCalendar3.add(userSearchField, gbcLeft);

		userSearchButton = new JButton("Sök!");
		userSearchButton.setPreferredSize(new Dimension(60, 29));

		gbcLeft.gridx = 1;
		gbcLeft.gridy = 1;
		gbcLeft.insets = new Insets(0, 0, 10, 0);
		gbcLeft.anchor = GridBagConstraints.WEST;

		editCalendar3.add(userSearchButton, gbcLeft);

		userList = new JList();
		listModel = new DefaultListModel();
		userList.setModel(listModel);
		userList.setPreferredSize(new Dimension(300, 200));
		userList.setBorder(etchedBorder);

		JScrollPane listScrollPane = new JScrollPane(userList);

		gbcLeft.gridx = 0;
		gbcLeft.gridy = 2;
		gbcLeft.insets = new Insets(0, 0, 10, 0);
		gbcLeft.anchor = GridBagConstraints.WEST;

		editCalendar3.add(userList, gbcLeft);

		calEditNameLabel = new JLabel("kalendernamn");
		calEditNameLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		try {
			String test = calArray[calID].getName();
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			calID = 0;
			SM.setCalEditStatus(0);
		}
		calEditNameField = new JTextField(calArray[calID].getName());
		calEditNameField.setPreferredSize(new Dimension(300, 30));

		calEditDescLabel = new JLabel("kalenderbeskrivning");
		calEditDescLabel.setFont(new Font("Serif", Font.PLAIN, 20));

		calEditDescTextArea = new JTextArea(calArray[calID].getDescription());
		calEditDescTextArea.setPreferredSize(new Dimension(300, 100));
		calEditDescTextArea.setRows(5);
		calEditDescTextArea.setColumns(27);
		calEditDescTextArea.setLineWrap(true);
		calEditDescTextArea.setWrapStyleWord(true);
		calEditDescTextArea.setBorder(etchedBorder);

		calendarNameLabel = new JLabel("Kalendernamn");
		calendarNameLabel.setFont(new Font("Serif", Font.PLAIN, 20));

		calManageLabel = new JLabel("Hantera Kalender:");
		calManageLabel.setFont(new Font("Serif", Font.PLAIN, 20));

		calendarNameField = new JTextField(calArray[calID].getName());
		calendarNameField.setPreferredSize(new Dimension(350, 30));

		calendarDescLabel = new JLabel("Kalenderbeskrivning");
		calendarDescLabel.setFont(new Font("Serif", Font.PLAIN, 20));

		calendarDescTextArea = new JTextArea(calArray[calID].getDescription());
		calendarDescTextArea.setPreferredSize(new Dimension(350, 100));
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
		addCalCenterRight.add(editCal2Panel, gbc);

		gbc.gridx = 0;
		gbc.gridy = 0;
		editCal2Panel.add(editCal2LPanel);
		gbc.gridx = 1;
		gbc.gridy = 0;
		editCal2Panel.add(editCal2RPanel);

		gbc.gridx = 0;
		gbc.gridy = 0;
		editCal2RPanel.add(calEditNameLabel, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		editCal2RPanel.add(calEditNameField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		editCal2RPanel.add(calEditDescLabel, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		editCal2RPanel.add(calEditDescTextArea, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		editCal2RPanel.add(calEditNotBox, gbc);

		calList();

		gbc.gridx = 0;
		gbc.gridy = 2;

		addCalCenterRight.add(editCalendar3, gbc);

		userSearchButton.addActionListener(lForButton);

		addCalCenterRight.updateUI();

	}

	private class ListenForButton implements ActionListener {

		// This method is called when an event occurs

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == userSearchButton) {

				String inputEditSearch = userSearchField.getText();
				Object[][] inputEditResult = SQLManager.searchForUser(inputEditSearch);

				listModel.removeAllElements();

				for (int i = 0; i < inputEditResult.length; i++) {

					listModel.addElement(inputEditResult[i][1]);

					checkList.add(inputEditResult[i][0]);

				}

			}

			// Check if the source of the event was the button
			if (e.getSource() == calSaveButton) {
				String temp1 = calEditNameField.getText();
				String temp2 = calEditDescTextArea.getText();
				if (SQLManager.editCalendar(calArray[SM.getCalEditStatus()].getCal_id(), temp1, temp2,window)) {

					if (listModel.size() > 0) {
						for (int i = 0; i < userList.getSelectedIndices().length; i++) {

							Object selectedUserId = checkList.get(userList.getSelectedIndices()[i]);
							int tempSelectedUserId = Integer.parseInt((String) selectedUserId);

							SQLManager.sendCalInvite(tempSelectedUserId);

						}
					}

					addCalCenterLeft.removeAll();
					user.reloadarrays();
					wp.getAddCalendarPage();
					wp.calChoiceList();
				}

			}
			if (e.getSource() == calRemoveButton) {
				if (SQLManager.removeCalendar(calArray[SM.getCalEditStatus()].getCal_id())) {
					user.reloadarrays();
					wp.getAddCalendarPage();
					wp.calChoiceList();
				}

			}
			if (e.getSource() == calAddButton) {
				String temp1 = calAddNameField.getText();
				String temp2 = calAddDescTextArea.getText();
				if (SQLManager.addCalendar(window, temp1, temp2)) {
					addCalCenterLeft.removeAll();
					user.reloadarrays();
					wp.getAddCalendarPage();
					wp.calChoiceList();
				}

			}

		}
	}

}
