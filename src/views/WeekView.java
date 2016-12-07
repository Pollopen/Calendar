package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.StateMachine;

public class WeekView extends JPanel {

	private StateMachine SM;
	private JPanel weekDaysPanel, weekDatePanel;
	private JLabel weekDaysLabel, dayOfMonthLabel;
	private Calendar cal;
	private String date, tempFocusedDay, tempFocusedMonth, tempFocusedYear;
	private Date focusedDate;
	private DateFormat getFocusDate = new SimpleDateFormat("yyyy-MM-dd");
	private DateFormat getWeekDay = new SimpleDateFormat("u");
	private DateFormat getMonth = new SimpleDateFormat("M");
	private int weekDay, newDayDate, newMonthDate, newYearDate;

	public WeekView(StateMachine SM) {
		this.SM = SM;

		this.setLayout(new GridLayout(2, 7));
		this.setPreferredSize(new Dimension(1175, 725));
		this.setVisible(true);

		cal = Calendar.getInstance();
		date = SM.getFocusedDate();

		tempFocusedDay = date.substring(8, 10);
		newDayDate = Integer.parseInt(tempFocusedDay);
		System.out.println("Day: " + tempFocusedDay);

		tempFocusedMonth = date.substring(5, 7);
		newMonthDate = Integer.parseInt(tempFocusedMonth);
		System.out.println("Month: " + tempFocusedMonth);

		tempFocusedYear = date.substring(0, 4);
		newYearDate = Integer.parseInt(tempFocusedYear);
		System.out.println("Year: " + tempFocusedYear);

		String[] dayName = { "Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag" };

		for (int i = 0; i < 7; i++) {

			weekDaysPanel = new JPanel();
			weekDaysPanel.setPreferredSize(new Dimension(165, 700));
			weekDaysPanel.setVisible(true);
			weekDaysPanel.setBackground(new Color(255, 255, 255));
			add(weekDaysPanel);

			weekDaysLabel = new JLabel();
			weekDaysLabel.setText(dayName[i]);
			weekDaysLabel.setFont(new Font("Serif", Font.PLAIN, 20));
			weekDaysPanel.add(weekDaysLabel);

		}

		// cal.set(newYearDate = Integer.parseInt(tempFocusedYear), newMonthDate
		// = Integer.parseInt(tempFocusedMonth), newDayDate =
		// Integer.parseInt(tempFocusedDay));

		System.out.println(newYearDate + " " + cal.get(Calendar.YEAR));
		System.out.println(newMonthDate + " " + cal.get(Calendar.MONTH));
		System.out.println(newDayDate);

		System.out.println(cal.get(Calendar.DAY_OF_WEEK));

		cal.set(newYearDate, (newMonthDate - 1), newDayDate);

		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
				cal.get(Calendar.DAY_OF_MONTH) - (cal.get(Calendar.DAY_OF_WEEK) - 2));

		for (int i = cal.get(Calendar.DAY_OF_MONTH); i < cal.get(Calendar.DAY_OF_MONTH) + 7; i++) {

			dayOfMonthLabel = new JLabel();
			dayOfMonthLabel.setText("" + i);

			weekDatePanel = new JPanel();
			weekDatePanel.setPreferredSize(new Dimension(165, 25));
			weekDatePanel.setVisible(true);

			add(weekDatePanel);
			weekDatePanel.add(dayOfMonthLabel);

		}

	}

}
