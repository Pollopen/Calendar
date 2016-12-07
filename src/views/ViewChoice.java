package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.StateMachine;

public class ViewChoice extends JPanel {
	private JButton dayButton, weekButton, monthButton, yearButton, prevWeekButton, nextWeekButton;
	private GridBagConstraints gbc;
	private GregorianCalendar gc;
	private ListenForButton lForButton;
	private SimpleDateFormat sdf;
	private StateMachine SM;
	private WindowPanel wp;
	private Calendar cal;
	private JSpinner dateChoice;
	private DateFormat getFocusDate = new SimpleDateFormat("yyyy/MM/dd");
	private Date focusedDate;
	private int week;
	private static int nextWeeksDate = 7;

	public ViewChoice(StateMachine SM, WindowPanel wp) {
		this.SM = SM;
		this.wp = wp;
		setOpaque(false);
		gbc = new GridBagConstraints();
		setLayout(new GridBagLayout());
		lForButton = new ListenForButton();
		dayButton = new JButton("Dag");
		dayButton.addActionListener(lForButton);
		weekButton = new JButton("Vecka");
		weekButton.addActionListener(lForButton);
		monthButton = new JButton("Månad");
		monthButton.addActionListener(lForButton);
		yearButton = new JButton("År");
		yearButton.addActionListener(lForButton);
		prevWeekButton = new JButton("<<");
		prevWeekButton.addActionListener(lForButton);
		nextWeekButton = new JButton(">>");
		nextWeekButton.addActionListener(lForButton);

		switch (SM.getActiveview()) {
		case 1:
			dayButton.setEnabled(false);
			break;
		case 2:
			weekButton.setEnabled(false);
			break;
		case 3:
			monthButton.setEnabled(false);
			break;
		case 4:
			yearButton.setEnabled(false);
			break;
		default:
			break;
		}

		gbc.gridx = 0;
		gbc.gridy = 0;
		add(dayButton, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(weekButton, gbc);
		gbc.gridx = 2;
		gbc.gridy = 0;
		add(monthButton, gbc);
		gbc.gridx = 3;
		gbc.gridy = 0;
		add(yearButton, gbc);

		if (SM.getActiveview() == 2) {
			gbc.gridx = 4;
			gbc.gridy = 0;
			add(prevWeekButton, gbc);
			gbc.gridx = 5;
			gbc.gridy = 0;
			add(nextWeekButton, gbc);
		}

		try {
			focusedDate = getFocusDate.parse(SM.getFocusedDate());
		} catch (ParseException e) {
			System.out.println("Date Conversion failed!");
		}

		dateChoice = new JSpinner(new SpinnerDateModel(focusedDate, null, null, Calendar.DAY_OF_MONTH));
		JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateChoice, "dd/MM/yy");
		dateChoice.setEditor(dateEditor);

		gbc.gridx = 4;
		gbc.gridy = 0;

		add(dateChoice);

		ListenForSpinner lForSpinner = new ListenForSpinner();

		dateChoice.addChangeListener(lForSpinner);

	}

	public String addZero(int value) {
		if (value <= 9)
			return "0" + value;
		else
			return "" + value;
	}

	public void getNextWeek() {

		cal = Calendar.getInstance();

		String todaysDate = SM.getFocusedDate();
		String focusedDay = todaysDate.substring(8, 10);
		String focusedMonth = todaysDate.substring(5, 7);
		String focusedYear = todaysDate.substring(0, 4);

		String addZeroToMonth = "0";
		String addZeroToDay = "0";
		String formatDate = "";
		String forMonthJan = "01";
		// int temp = Integer.parseInt(focusedMonth);
		//
		// if (temp == 12) {
		// temp -= 1;
		// }
		//
		// cal.set(Calendar.YEAR, Integer.parseInt(focusedYear));
		// cal.set(Calendar.MONTH, temp);

		System.out.println(focusedYear);
		System.out.println(focusedMonth);

		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int lastDayOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int lastWeekOfYear = cal.getActualMaximum(Calendar.WEEK_OF_YEAR);
		int weekOfYear = cal.get(Calendar.WEEK_OF_YEAR);

		nextWeeksDate += 7;

		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), nextWeeksDate);

		formatDate = cal.get(Calendar.YEAR) + "/" + addZero(cal.get(Calendar.MONTH) + 1) + "/"
				+ addZero(cal.get(Calendar.DAY_OF_MONTH));

		// if (lastDayOfMonth < nextWeeksDate) {
		// nextWeeksDate = nextWeeksDate - lastDayOfMonth;
		// month++;
		//
		// if (month > 11) {
		// month = 0;
		// year++;
		//
		// if (nextWeeksDate < 10) {
		// addZeroToDay = addZeroToDay + nextWeeksDate;
		//
		// formatDate = year + "/01/" + addZeroToDay;
		// } else {
		// formatDate = year + "/01/" + nextWeeksDate;
		// }
		//
		// } else if (nextWeeksDate < 10) {
		// addZeroToDay = addZeroToDay + nextWeeksDate;
		//
		// formatDate = year + "/0" + month + "/" + addZeroToDay;
		// } else {
		// formatDate = year + "/" + month + "/" + nextWeeksDate;
		// }
		// } else if (month < 10) {
		//
		// formatDate = year + "/0" + month + "/" + nextWeeksDate;
		//
		// if (nextWeeksDate < 10) {
		// addZeroToDay = addZeroToDay + nextWeeksDate;
		//
		// formatDate = year + "/0"+month+"/" + addZeroToDay;
		// } else {
		// formatDate = year + "/0"+month+"/" + nextWeeksDate;
		// }
		//
		// } else {
		// formatDate = year + "/" + month + "/" + nextWeeksDate;
		// }

		// if (lastDayOfMonth < nextWeeksDate)
		// {
		//
		// if (month == 11)
		// {
		// month = 0;
		// year++;
		// }
		// else
		// {
		// month++;
		// }
		//
		// weekOfYear++;
		//
		// if (weekOfYear > lastWeekOfYear)
		// {
		// weekOfYear = 0;
		// }
		//
		// nextWeeksDate = nextWeeksDate - lastDayOfMonth;
		// addZeroToDay = addZeroToDay+nextWeeksDate;
		// formatDate = year + "/" + month + "/0"+nextWeeksDate;
		//
		// }
		// else
		// {
		// formatDate = year + "/" + month + "/" + addZeroToDay;
		// }
		//
		//
		// if(month < 10 && month > 0)
		// {
		// addZeroToMonth = addZeroToMonth+month;
		//
		// formatDate = year + "/" + addZeroToMonth + "/" + addZeroToDay;
		//
		// } else {
		// formatDate = year + "/" + month + "/" + addZeroToDay;
		// }
		//
		//
		// if(month == 0)
		// {
		// formatDate = year + "/" + forMonthJan + "/" + addZeroToDay;
		// } else {
		//
		// formatDate = year + "/" + month + "/" + nextWeeksDate;
		//
		// }

		SM.setFocusedDate(formatDate);

		System.out.println("------------------------------------------------------");
		System.out.println("Sju dagar framåt: " + nextWeeksDate);
		System.out.println(SM.getFocusedDate());
		// System.out.println("Månad: " + month);
		// System.out.println("År: " + year);
		// System.out.println("Sista veckan på året: " + lastWeekOfYear);
		// System.out.println("Sista dagen i månaden: " + lastDayOfMonth);
		System.out.println("Formaterat datum " + formatDate);
		// System.out.println("addZeroToDay: " + addZeroToDay);
		// System.out.println("addZeroToMonth: " + addZeroToMonth);
		System.out.println("------------------------------------------------------");

	}

	private class ListenForButton implements ActionListener {

		// This method is called when an event occurs

		public void actionPerformed(ActionEvent e) {

			// Check if the source of the event was the button

			if (e.getSource() == dayButton) {
				SM.setActiveview(1);
				wp.getViewViewer();
				wp.getViewChoice();
				wp.getOverview();
			}
			if (e.getSource() == weekButton) {
				SM.setActiveview(2);
				wp.getViewViewer();
				wp.getViewChoice();
				wp.getOverview();
			}
			if (e.getSource() == monthButton) {
				SM.setActiveview(3);
				wp.getViewViewer();
				wp.getViewChoice();
				wp.getOverview();
			}
			if (e.getSource() == yearButton) {
				SM.setActiveview(4);
				wp.getViewViewer();
				wp.getViewChoice();
				wp.getOverview();
			}
			if (e.getSource() == prevWeekButton) {
				SM.setActiveview(2);
				wp.getViewViewer();
				wp.getViewChoice();
				wp.getOverview();
			}
			if (e.getSource() == nextWeekButton) {
				getNextWeek();
				SM.setActiveview(2);
				wp.getViewViewer();
				wp.getViewChoice();
				wp.getOverview();

			}

		}
	}

	private class ListenForSpinner implements ChangeListener {

		// This method is called when an event occurs

		public void stateChanged(ChangeEvent e) {
			if (e.getSource() == dateChoice) {
				String temp = getFocusDate.format(dateChoice.getValue());
				SM.setFocusedDate(temp);
				System.out.println(temp);
				wp.getViewViewer();
				wp.getOverview();
				//wp.getViewChoice();
				//wp.getViewChoice();
			}
			// Check if the source of the event was the button

		}
	}
}
