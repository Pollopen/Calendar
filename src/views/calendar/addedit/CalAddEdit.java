package views.calendar.addedit;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.StateMachine;
import database.SQLManager;
import object.Calendar;
import object.User;
import views.WindowPanel;


public class CalAddEdit extends JPanel {
	private JPanel addCalMain, addCalCenter, addCalCenterLeft, addCalCenterRight, editCalendar1, editCalendar2, editCalendar3;
	private GridBagConstraints gbc;
	private Calendar[] calArray;
	private JTextField calAddNameField, calEditNameField, calendarNameField;
	private JTextArea calAddDescTextArea, calendarDescTextArea, calEditDescTextArea;
	private JLabel calAddNotLabel, calAddNameLabel, calAddLabel, calendarDescLabel, calendarNameLabel, calEditDescLabel, calEditNameLabel;
	private JButton calAddButton, calSaveButton, calRemoveButton;
	private User user;
	private CalEditList calEditList;
	private StateMachine SM;
	private WindowPanel wp;
	private ListenForButton lForButton;
	
	public CalAddEdit(StateMachine SM, WindowPanel wp, User user){
		this.user=user;
		this.SM=SM;
		this.wp=wp;
		lForButton = new ListenForButton();
		gbc = new GridBagConstraints();
		// Panel management

		addCalMain = new JPanel();
		addCalMain.setPreferredSize(new Dimension(1175, 725));
		addCalMain.setLayout(new GridBagLayout());
		// addCalMain.setBackground(new Color(0, 255, 255));
		addCalMain.setVisible(true);

		add(addCalMain);

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

		calendarList();

		addCalCenterRight = new JPanel();
		addCalCenterRight.setPreferredSize(new Dimension(475, 725));
		addCalCenterRight.setLayout(new GridBagLayout());
		addCalCenterRight.setBackground(new Color(0, 0, 255));
		addCalCenterRight.setVisible(true);

		gbc.gridx = 1;
		gbc.gridy = 0;

		addCalCenter.add(addCalCenterRight, gbc);

		calendarEdit(SM.getCalEditStatus());
	
	}
	public void calendarList() {
		addCalCenterLeft.removeAll();
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

		calList();
	}
	public void calList() {
		if (calEditList != null) {
			remove(calEditList);
		}
		gbc.gridx = 0;
		gbc.gridy = 6;
		calEditList = new CalEditList(calArray, SM, wp, this);
		addCalCenterLeft.add(calEditList, gbc);

	}
	public void calendarEdit(int calID) {
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

		if (calID == 0) {
			calRemoveButton.setEnabled(false);
		} else {
			calRemoveButton.setEnabled(true);
		}
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
		editCalendar2.add(calEditNameLabel, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		editCalendar2.add(calEditNameField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		editCalendar2.add(calEditDescLabel, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		editCalendar2.add(calEditDescTextArea, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;

		addCalCenterRight.add(editCalendar3, gbc);

		addCalCenterRight.updateUI();

	}
	private class ListenForButton implements ActionListener {

		// This method is called when an event occurs

		public void actionPerformed(ActionEvent e) {
			
			// Check if the source of the event was the button
			if (e.getSource() == calSaveButton) {
				String temp1 = calEditNameField.getText();
				String temp2 = calEditDescTextArea.getText();
				System.out.println("save kommand");
				System.out.println(temp1);
				System.out.println(temp2);
				if (SQLManager.editCalendar(calArray[SM.getCalEditStatus()].getCal_id(), temp1, temp2)) {
					addCalCenterLeft.removeAll();
					user.reloadarrays();
					wp.getAddCalendarPage();
					wp.calChoiceList();
				}

			}
			if (e.getSource() == calRemoveButton) {
				System.out.println("ta bort command");
				if(SQLManager.removeCalendar(calArray[SM.getCalEditStatus()].getCal_id())){
					user.reloadarrays();
					wp.getAddCalendarPage();
					wp.calChoiceList();
				}

			}
			if (e.getSource() == calAddButton) {
				String temp1 = calAddNameField.getText();
				String temp2 = calAddDescTextArea.getText();
				System.out.println("*");
				System.out.println(temp1);
				System.out.println(temp2);
				if (SQLManager.addCalendar(temp1, temp2)) {
					addCalCenterLeft.removeAll();
					user.reloadarrays();
					wp.getAddCalendarPage();
					wp.calChoiceList();
				}

			}

		}
	}
	
}
