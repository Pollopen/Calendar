package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.StateMachine;

public class WeekView extends JPanel {

	private StateMachine SM;
	private JPanel weekDaysPanel, weekDaysLabelPanel;
	private JLabel weekDaysLabel, dayOfMonthLabel;
	private Calendar cal;
	private String date;
	private Date focusedDate;
	private DateFormat getFocusDate = new SimpleDateFormat("yyyy-MM-dd");
	private DateFormat getWeekDay = new SimpleDateFormat("u");
	private DateFormat getMonth = new SimpleDateFormat("M");
	private int weekDay;

	public WeekView(StateMachine SM) {
		this.SM = SM;

		this.setLayout(new GridLayout(2, 7));
		this.setPreferredSize(new Dimension(1175, 725));
		this.setVisible(true);

		cal = new GregorianCalendar();
		date = SM.getFocusedDate();

		String tempFocusedDate = date.substring(8, 10);
		System.out.println(tempFocusedDate);

		String[] dayName = { "Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag" };

		for (int i = 0; i < 7; i++) {

			weekDaysLabelPanel = new JPanel();
			weekDaysLabelPanel.setPreferredSize(new Dimension(165, 25));
			weekDaysLabelPanel.setVisible(true);
			weekDaysLabelPanel.setBackground(new Color(255, 255, 255));
			add(weekDaysLabelPanel);

			weekDaysLabel = new JLabel();
			weekDaysLabel.setText(dayName[i]);
			weekDaysLabel.setFont(new Font("Serif", Font.PLAIN, 20));
			weekDaysLabelPanel.add(weekDaysLabel);

		}

		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), Integer.parseInt(tempFocusedDate));

		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
				cal.get(Calendar.DAY_OF_MONTH) - (cal.get(Calendar.DAY_OF_WEEK) - 2));

		for (int i = cal.get(Calendar.DAY_OF_MONTH); i < cal.get(Calendar.DAY_OF_MONTH) + 7; i++) {

			weekDaysPanel = new JPanel();
			weekDaysPanel.setPreferredSize(new Dimension(165, 700));
			weekDaysPanel.setVisible(true);
			weekDaysPanel.setBackground(new Color(255, 255, 255));
			add(weekDaysPanel);

			dayOfMonthLabel = new JLabel();
			dayOfMonthLabel.setText("" + i);
			weekDaysPanel.add(dayOfMonthLabel);

		}

	}

}
