package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.DateHandler;
import controller.StateMachine;
import object.Event;
import object.User;

public class WeekView extends JPanel {

	private StateMachine SM;
	private JPanel weekDaysPanel, weekDatePanel;
	private JLabel weekDaysLabel, dayOfMonthLabel;
	private String date;
	private Date focusedDate;
	private DateFormat getFocusDate = new SimpleDateFormat("yyyy/MM/dd");
	private DateFormat getWeekDay = new SimpleDateFormat("u");
	private int weekDay;
	private Event[] eventArray;
	private object.Calendar[] calArray;
	private DayPanel[] daypanels = new DayPanel[7];
	private WindowPanel wp;

	public WeekView(StateMachine SM, User user, WindowPanel wp) {
		this.SM = SM;
		this.wp = wp;
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(1175, 725));
		setVisible(true);
		eventArray = user.getEventArray();
		calArray = user.getCalArray();
		date = SM.getFocusedDate();
		date = DateHandler.convertToEasyDate(date);

		JPanel timeLine = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		JPanel centerPanel = new JPanel(new BorderLayout());

		add(BorderLayout.WEST, timeLine);

		gbc.gridx = 0;
		gbc.gridy = 0;
		int hour = 0;
		for (int i = 0; i < 26; i++) {
			if (i == 0) {
				JPanel empty = new JPanel();
				empty.setPreferredSize(new Dimension(50, 75));
				// empty.setBackground(new Color(255, 0, 0));
				timeLine.add(empty, gbc);
				gbc.gridy++;
			} else if (i == 1) {
				JPanel empty = new JPanel();
				empty.setPreferredSize(new Dimension(50, 50));
				// empty.setBackground(new Color(0, 255, 0));
				timeLine.add(empty, gbc);
				gbc.gridy++;
			} else {
				JPanel tempTime = new JPanel();
				if (i == 25) {
					tempTime.setBorder(BorderFactory.createMatteBorder(2, 0, 3, 0, Color.BLACK));
				} else {
					tempTime.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.BLACK));
				}
				tempTime.setPreferredSize(new Dimension(50, 25));
				String timeString = "";
				if (hour <= 9) {
					timeString += "0" + hour + ":00";
				} else {
					timeString += hour + ":00";
				}
				JLabel time = new JLabel(timeString);
				tempTime.add(time);
				hour++;

				timeLine.add(tempTime, gbc);
				gbc.gridy++;
			}
		}

		add(BorderLayout.CENTER, centerPanel);

		String tempString = DateHandler.convertFromEasyDate(date);
		try {
			focusedDate = getFocusDate.parse(tempString);
		} catch (ParseException e) {
			System.out.println("Date Conversion failed!" + tempString);
		}

		weekDay = Integer.parseInt(getWeekDay.format(focusedDate));
		System.out.println("veckodag: " + weekDay);
		String firstDay = DateHandler.addToDateString(date, -(weekDay - 1));
		JPanel north = new JPanel(new GridLayout(2, 1));
		JPanel days = new JPanel(new GridLayout(1, 7));
		north.setPreferredSize(new Dimension(2000, 75));

		JPanel dates = new JPanel(new GridLayout(1, 7));
		north.add(days);
		north.add(dates);
		centerPanel.add(BorderLayout.NORTH, north);

		JPanel center = new JPanel(new GridLayout(1, 7));
		centerPanel.add(BorderLayout.CENTER, center);

		String[] dayName = { "Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag" };

		for (int i = 0; i < 7; i++) {

			weekDaysPanel = new JPanel();
			weekDaysPanel.setPreferredSize(new Dimension(170, 50));
			weekDaysPanel.setVisible(true);
			weekDaysPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			weekDaysPanel.setBackground(new Color(200, 200, 200));
			days.add(weekDaysPanel);

			weekDaysLabel = new JLabel();
			weekDaysLabel.setText(dayName[i]);
			weekDaysLabel.setFont(new Font("Serif", Font.PLAIN, 20));
			weekDaysPanel.add(weekDaysLabel);

		}
		String dayOfWeek = firstDay;
		// datum utskrivning
		for (int i = 1; i <= 7; i++) {
			dayOfMonthLabel = new JLabel();
			dayOfMonthLabel.setText(dayOfWeek.substring(6, 8) + "/" + dayOfWeek.substring(4, 6));// datumet
			if (SM.getEasyDate().equals(dayOfWeek)) {
				dayOfMonthLabel.setForeground(Color.BLUE);
			}
			dayOfWeek = DateHandler.addToDateString(dayOfWeek, 1);
			weekDatePanel = new JPanel();
			weekDatePanel.setPreferredSize(new Dimension(170, 25));
			weekDatePanel.setVisible(true);
			weekDatePanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
			dates.add(weekDatePanel);
			weekDatePanel.add(dayOfMonthLabel);
		}
		dayOfWeek = firstDay;
		for (int i = 0; i < 7; i++) {

			daypanels[i] = new DayPanel(filterDayEvents(dayOfWeek, eventArray, calArray), dayOfWeek, i, true, SM, wp,
					user);

			center.add(daypanels[i]);
			dayOfWeek = DateHandler.addToDateString(dayOfWeek, 1);
		}
	}

	public String addZero(int value) {
		if (value <= 9)
			return "0" + value;
		else
			return "" + value;
	}

	private Event[] filterDayEvents(String firstDay, Event[] eventArray, object.Calendar[] calArray) {
		Event[] filteredList = null;
		Event[] ea = eventArray;
		object.Calendar[] ca = calArray;

		int matchNumber = 0;
		String weekDayChecker = firstDay;
		for (int i = 0; i < ea.length; i++) {
			String eventStartChecker = DateHandler.convertToEasyDate(ea[i].getStart_time());
			String eventEndChecker = DateHandler.convertToEasyDate(ea[i].getEnd_time());
			if (weekDayChecker.equals(eventStartChecker) || weekDayChecker.equals(eventEndChecker)
					|| checkIfInProgress(weekDayChecker, eventStartChecker, eventEndChecker)) {// is
																								// this
																								// event
																								// in
																								// this
																								// week
				int[] aca = SM.getActiveCalendars();
				for (int j = 0; j < aca.length; j++) {// If calendar is active
					if (ca[aca[j]].getCal_id() == ea[i].getCal_id()) {
						matchNumber++;
					}
				}
			}
		}

		filteredList = new Event[matchNumber];
		int k = 0;
		for (int i = 0; i < ea.length; i++) {
			String eventStartChecker = DateHandler.convertToEasyDate(ea[i].getStart_time());
			String eventEndChecker = DateHandler.convertToEasyDate(ea[i].getEnd_time());
			if (weekDayChecker.equals(eventStartChecker) || weekDayChecker.equals(eventEndChecker)
					|| checkIfInProgress(weekDayChecker, eventStartChecker, eventEndChecker)) {// is
																								// this
																								// event
																								// in
																								// this
																								// day
																								// of
																								// week
				int[] aca = SM.getActiveCalendars();
				for (int j = 0; j < aca.length; j++) {// If calendar is active
					if (ca[aca[j]].getCal_id() == ea[i].getCal_id()) {
						Event tempEvent = ea[i];
						tempEvent.setColorNum(j);
						filteredList[k] = tempEvent;
						k++;
					}
				}
			}
		}

		return filteredList;
	}

	private boolean checkIfInProgress(String checkDate, String checkEventDayStart, String checkEventDayEnd) {
		if (Integer.parseInt(checkDate) > Integer.parseInt(checkEventDayStart)
				&& Integer.parseInt(checkDate) < Integer.parseInt(checkEventDayEnd)) {
			return true;
		} else {
			return false;
		}

	}

	public static int getDayOfWeek(int day) {
		int[] from = { 1, 2, 3, 4, 5, 6, 7 };
		int[] to = { 6, 0, 1, 2, 3, 4, 5 };
		for (int i = 0; i < from.length; i++) {
			if (from[i] == day)
				return to[i];
		}
		return 0;
	}

	public static boolean isLeapYear(String firstDay) {
		String year = firstDay.substring(0, 4);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(year));
		return cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365;
	}

	public String getCheckDate(String firstday) {
		String checkdate;
		try {
			checkdate = firstday.substring(0, 4) + firstday.substring(5, 7) + firstday.substring(8, 10);
			return checkdate;
		} catch (java.lang.StringIndexOutOfBoundsException e) {
			return firstday;
		}

	}

}
