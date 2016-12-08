package views;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.NetworkInterface;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.StateMachine;

public class ViewChoice extends JPanel {
	private JButton todayButton, dayButton, weekButton, monthButton, yearButton, prevWeekButton, nextWeekButton, prevMonthButton, nextDayButton, nextMonthButton, prevDayButton, prevYearButton, nextYearButton;
	private GridBagConstraints gbc;
	private JLabel empty;
	private GregorianCalendar gc;
	private ListenForButton lForButton;
	private SimpleDateFormat sdf;
	private StateMachine SM;
	private WindowPanel wp;
	private Calendar cal;
	private String date;
	private JSpinner dateChoice;
	private DateFormat getFocusDate = new SimpleDateFormat("yyyy/MM/dd");
	private Date focusedDate;
	private int week;
	private static int nextWeeksDate = 7;
	private static int prevWeeksDate = -7;

	public ViewChoice(StateMachine SM, WindowPanel wp) {
		this.SM = SM;
		this.wp = wp;
		setOpaque(false);
		
		cal = Calendar.getInstance();
		gbc = new GridBagConstraints();
		setPreferredSize(new Dimension(1150, 40));
		setLayout(new GridLayout(1,18));
		
		try {
			focusedDate = getFocusDate.parse(SM.getFocusedDate());
		} catch (ParseException e) {
			System.out.println("Date Conversion failed!");
		}
		lForButton = new ListenForButton();
		dayButton = new JButton("Dag");
		dayButton.addActionListener(lForButton);
		weekButton = new JButton("Vecka");
		weekButton.addActionListener(lForButton);
		monthButton = new JButton("Månad");
		monthButton.addActionListener(lForButton);
		yearButton = new JButton("År");
		yearButton.addActionListener(lForButton);
		prevDayButton = new JButton("<Dag");
		prevDayButton.addActionListener(lForButton);
		nextDayButton = new JButton("Dag>");
		nextDayButton.addActionListener(lForButton);
		prevWeekButton = new JButton("<Vecka");
		prevWeekButton.addActionListener(lForButton);
		nextWeekButton = new JButton("Vecka>");
		nextWeekButton.addActionListener(lForButton);
		prevMonthButton = new JButton("<Månad");
		prevMonthButton.addActionListener(lForButton);
		nextMonthButton = new JButton("Månad>");
		nextMonthButton.addActionListener(lForButton);
		prevYearButton = new JButton("<År");
		prevYearButton.addActionListener(lForButton);
		nextYearButton = new JButton("År>");
		nextYearButton.addActionListener(lForButton);
		todayButton = new JButton("Idag");
		todayButton.addActionListener(lForButton);
		empty = new JLabel("");
		
		dateChoice = new JSpinner(new SpinnerDateModel(focusedDate, null, null, Calendar.DAY_OF_MONTH));
		JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateChoice, "dd/MM/yy");
		dateChoice.setEditor(dateEditor);

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
		add(prevYearButton);
		
		add(prevMonthButton);
		
		add(prevWeekButton);
		add(prevDayButton);
		
		add(dateChoice);
		
		add(nextDayButton);
		
		add(nextWeekButton);
		
		add(nextMonthButton);
		
		add(nextYearButton);
		
		JPanel tempJP=new JPanel();
		tempJP.setLayout(new GridBagLayout());
		add(tempJP);
		tempJP.add(todayButton, gbc);
		
		
		add(dayButton);
		add(weekButton);
		add(monthButton);
		add(yearButton);

		

		ListenForSpinner lForSpinner = new ListenForSpinner();

		dateChoice.addChangeListener(lForSpinner);

	}

	public String addZero(int value) {
		if (value <= 9)
			return "0" + value;
		else
			return "" + value;
	}
	
	public void getPrevWeek()
	{
		String formatPrevDate = "";
		
		prevWeeksDate -= 7;

		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), prevWeeksDate);

		formatPrevDate = cal.get(Calendar.YEAR) + "/" + addZero(cal.get(Calendar.MONTH) + 1) + "/"
				+ addZero(cal.get(Calendar.DAY_OF_MONTH));
		
		SM.setFocusedDate(formatPrevDate);

		System.out.println("------------------------------------------------------");
		System.out.println("Sju dagar bak: " + prevWeeksDate);
		System.out.println(SM.getFocusedDate());
		System.out.println("Formaterat datum " + formatPrevDate);
		System.out.println("------------------------------------------------------");
	}

	public void getNextWeek() {
		
		String formatDate = "";

		nextWeeksDate += 7;

		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), nextWeeksDate);

		formatDate = cal.get(Calendar.YEAR) + "/" + addZero(cal.get(Calendar.MONTH) + 1) + "/"
				+ addZero(cal.get(Calendar.DAY_OF_MONTH));

		SM.setFocusedDate(formatDate);

		System.out.println("------------------------------------------------------");
		System.out.println("Sju dagar framåt: " + nextWeeksDate);
		System.out.println(SM.getFocusedDate());
		System.out.println("Formaterat datum " + formatDate);
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
				getPrevWeek();
				wp.getViewViewer();
				wp.getViewChoice();
				wp.getOverview();
			}
			if (e.getSource() == nextWeekButton) {
				getNextWeek();
				wp.getViewViewer();
				wp.getViewChoice();
				wp.getOverview();

			}
			if (e.getSource() == todayButton){
				SM.setFocusedToday();
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
