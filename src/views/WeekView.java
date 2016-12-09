package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.StateMachine;
import object.Event;
import object.User;

public class WeekView extends JPanel {

	private StateMachine SM;
	private JPanel weekDaysPanel, weekDatePanel;
	private JLabel weekDaysLabel, dayOfMonthLabel;
	private Calendar cal, eventCal;
	private String date, tempFocusedDay, tempFocusedMonth, tempFocusedYear, tempStartTimeString, tempEndTimeString,
			tempEventName;
	private Date focusedDate;
	private DateFormat getFocusDate = new SimpleDateFormat("yyyy/MM/dd");
	private DateFormat getWeekDay = new SimpleDateFormat("u");
	private DateFormat getMonth = new SimpleDateFormat("M");
	private int weekDay, newDayDate, newMonthDate, newYearDate;
	private User user;
	private Event[] eventArray;
	private DayPanel[] daypanels = new DayPanel[7];

	public WeekView(StateMachine SM, User user) {
		this.SM = SM;
		this.user = user;

		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(1175, 725));
		setVisible(true);

		eventArray = user.getEventArray();

		cal = Calendar.getInstance();
		date = SM.getFocusedDate();
		System.out.println("checkdate focused date: "+getCheckDate(date));
		tempFocusedDay = date.substring(8, 10);
		newDayDate = Integer.parseInt(tempFocusedDay);
		System.out.println("Day: " + tempFocusedDay);
		
		tempFocusedMonth = date.substring(5, 7);
		newMonthDate = Integer.parseInt(tempFocusedMonth);
		System.out.println("Month: " + tempFocusedMonth);

		tempFocusedYear = date.substring(0, 4);
		newYearDate = Integer.parseInt(tempFocusedYear);
		System.out.println("Year: " + tempFocusedYear);
		String tempString=date.substring(0, 10);
		try {
			focusedDate=getFocusDate.parse(tempString);
		} catch (ParseException e) {
			System.out.println("Date Conversion failed!"+tempString);
		}
		
		weekDay = Integer.parseInt(getWeekDay.format(focusedDate));
		System.out.println("veckodag: "+weekDay);
		JPanel north = new JPanel(new GridLayout(2, 1));
		JPanel days = new JPanel(new GridLayout(1, 7));
		north.setPreferredSize(new Dimension(2000, 100));

		JPanel dates = new JPanel(new GridLayout(1, 7));
		north.add(days);
		north.add(dates);
		add(BorderLayout.NORTH, north);

		JPanel center = new JPanel(new GridLayout(1, 7));
		add(BorderLayout.CENTER, center);

		String[] dayName = { "Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag" };

		for (int i = 0; i < 7; i++) {

			weekDaysPanel = new JPanel();
			weekDaysPanel.setPreferredSize(new Dimension(165, 50));
			weekDaysPanel.setVisible(true);
			weekDaysPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0)));
			weekDaysPanel.setBackground(new Color(255, 255, 255));
			days.add(weekDaysPanel);

			weekDaysLabel = new JLabel();
			weekDaysLabel.setText(dayName[i]);
			weekDaysLabel.setFont(new Font("Serif", Font.PLAIN, 20));
			weekDaysPanel.add(weekDaysLabel);

		}

		cal.set(newYearDate, (newMonthDate - 1), newDayDate);

		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
				cal.get(Calendar.DAY_OF_MONTH) - (cal.get(Calendar.DAY_OF_WEEK) - 2));

		
		
		//datum utskrivning 
		for (int i = 1; i <= 7; i++) { // TODO FIXA DENNA 
			dayOfMonthLabel = new JLabel();
			if(weekDay==i){
				dayOfMonthLabel.setText("idag "+Integer.toString(i));
			}else{
				dayOfMonthLabel.setText(Integer.toString(i));//datumet
			}
			weekDatePanel = new JPanel();
			weekDatePanel.setPreferredSize(new Dimension(165, 25));
			weekDatePanel.setVisible(true);
			dates.add(weekDatePanel);
			weekDatePanel.add(dayOfMonthLabel);
		}
		
		for (int i = 0; i < 7; i++) {
			daypanels[i] = new DayPanel();
			center.add(daypanels[i]);
		}
		
		for(int i = 0; i < eventArray.length; i++)
		{
			tempEventName = eventArray[i].getName();
			tempStartTimeString = eventArray[i].getStart_time().substring(0, 10);
			tempEndTimeString = eventArray[i].getEnd_time().substring(0, 10);
			System.out.println("checkdate start time: "+getCheckDate(tempStartTimeString));
			System.out.println("checkdate end time: "+getCheckDate(tempEndTimeString));
			
			String tempYear = tempStartTimeString.substring(0, 4);
			String tempMonth = tempStartTimeString.substring(5, 7);
			String tempDay = tempStartTimeString.substring(8, 10);

			int tempYearInt = Integer.parseInt(tempYear);
			int tempMonthInt = Integer.parseInt(tempMonth);
			int tempDayInt = Integer.parseInt(tempDay);

			cal.set(tempYearInt, (tempMonthInt - 1), tempDayInt);
			
			int day = cal.get(Calendar.DAY_OF_WEEK);
			
			day = getDayOfWeek(day);
			
			String test = date.substring(8, 10);
			
			System.out.println(day + " day");
			System.out.println(Integer.parseInt(test) + " Test");
			
		
			daypanels[day].addEvent(eventArray[i]);
		}
		
		for(DayPanel p : daypanels)
		{
			p.setupEvent();
		}

	}
	
	public String addZero(int value) {
		if (value <= 9)
			return "0" + value;
		else
			return "" + value;
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
	private int getDaysOfMonth(String firstDay){
		String monthNum=firstDay.substring(5, 7);
		switch (Integer.parseInt(monthNum)) {
			case 1:
				return 31;
			case 2:
				if(isLeapYear(firstDay)){
					return 29;
				}else{
					return 28;
				}
			case 3:
				return 31;
			case 4:
				return 30;
			case 5:
				return 31;
			case 6:
				return 30;
			case 7:
				return 31;
			case 8:
				return 31;
			case 9:
				return 30;
			case 10:
				return 31;
			case 11:
				return 30;
			case 12:
				return 31;
			default:
				return 31;
		}
	}
	public static boolean isLeapYear(String firstDay) {
		String year=firstDay.substring(0, 4);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(year));
		return cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365;
	}
	public String getCheckDate(String firstday){
		String checkdate;
		checkdate=firstday.substring(0, 4)+firstday.substring(5, 7)+firstday.substring(8, 10);
		return checkdate;
	}
	

}
