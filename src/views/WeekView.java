package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class WeekView extends JPanel {

	private JPanel mondayPanel, tuesdayPanel, wednesdayPanel, thursdayPanel, fridayPanel, saturdayPanel, sundayPanel;
	private JLabel mondayLabel, tuesdayLabel, wednesdayLabel, thursdayLabel, fridayLabel, saturdayLabel, sundayLabel;
	private Calendar cal;
	private Date today;
	private SimpleDateFormat sdf;

	public WeekView() {

		this.setLayout(new GridLayout(1, 7));
		this.setPreferredSize(new Dimension(1175, 725));
		this.setVisible(true);

		today = new Date();
		sdf = new SimpleDateFormat("MM-dd-yyy");

		cal = Calendar.getInstance();

		System.out.println(sdf.format(today));

		getMondayView();
		getTuesdayView();
		getWednesdayView();
		getThursdayView();
		getFridayView();
		getSaturdayView();
		getSundayView();

	}

	public void getMondayView() {
		mondayPanel = new JPanel();
		mondayPanel.setPreferredSize(new Dimension(165, 725));
		mondayPanel.setLayout(new GridLayout(2, 1));
		mondayPanel.setVisible(true);
		mondayPanel.setBackground(new Color(255, 0, 0));

		add(mondayPanel);
	}

	public void getTuesdayView() {

		tuesdayPanel = new JPanel();
		tuesdayPanel.setPreferredSize(new Dimension(165, 725));
		tuesdayPanel.setLayout(new GridLayout(2, 1));
		tuesdayPanel.setVisible(true);
		tuesdayPanel.setBackground(new Color(0, 255, 0));
		add(tuesdayPanel);

		tuesdayLabel = new JLabel();
		tuesdayLabel.setText("Tisdag");

		tuesdayPanel.add(tuesdayLabel);
	}

	public void getWednesdayView() {
		wednesdayPanel = new JPanel();
		wednesdayPanel.setPreferredSize(new Dimension(165, 725));
		wednesdayPanel.setLayout(new GridLayout(2, 1));
		wednesdayPanel.setVisible(true);
		wednesdayPanel.setBackground(new Color(0, 0, 255));

		add(wednesdayPanel);
	}
	
	public void getThursdayView() {
		thursdayPanel = new JPanel();
		thursdayPanel.setPreferredSize(new Dimension(165, 725));
		thursdayPanel.setLayout(new GridLayout(2, 1));
		thursdayPanel.setVisible(true);
		thursdayPanel.setBackground(new Color(255, 0, 0));

		add(thursdayPanel);
	}
	
	public void getFridayView() {
		fridayPanel = new JPanel();
		fridayPanel.setPreferredSize(new Dimension(165, 725));
		fridayPanel.setLayout(new GridLayout(2, 1));
		fridayPanel.setVisible(true);
		fridayPanel.setBackground(new Color(0, 255, 0));

		add(fridayPanel);
	}
	
	public void getSaturdayView() {
		saturdayPanel = new JPanel();
		saturdayPanel.setPreferredSize(new Dimension(165, 725));
		saturdayPanel.setLayout(new GridLayout(2, 1));
		saturdayPanel.setVisible(true);
		saturdayPanel.setBackground(new Color(0, 0, 255));

		add(saturdayPanel);
	}
	
	public void getSundayView() {
		sundayPanel = new JPanel();
		sundayPanel.setPreferredSize(new Dimension(165, 725));
		sundayPanel.setLayout(new GridLayout(2, 1));
		sundayPanel.setVisible(true);
		sundayPanel.setBackground(new Color(255, 0, 0));

		add(sundayPanel);
	}
	

}
