package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.StateMachine;

public class MonthView extends JPanel{
	private JPanel month;
	private StateMachine SM;
	private String date;
	private Date focusedDate;
	private DateFormat getFocusDate = new SimpleDateFormat("yyyy/MM/dd");
	private DateFormat getWeekDay = new SimpleDateFormat("u");
	private DateFormat getMonth = new SimpleDateFormat("M");
	private int weekDay, j;
	public MonthView(StateMachine SM){
		this.SM=SM;
		setPreferredSize(new Dimension(1175, 725));
		setLayout(new GridBagLayout());
		setBackground(new Color(0, 255, 255));
		setVisible(true);

		date=SM.getFocusedDate();
		
		try {
			focusedDate=getFocusDate.parse(date);
		} catch (ParseException e) {
			System.out.println("Date Conversion failed!");
		}
		weekDay = Integer.parseInt(getWeekDay.format(focusedDate));
		System.out.println(weekDay);
		
		month = new JPanel();
		month.setLayout(new GridLayout(7,7));
		month.setVisible(true);
		month.setPreferredSize(new Dimension(1000, 600));
		month.setBackground(new Color(255, 0, 0));
		add(month);
		month.add(new JLabel("Måndag"));
		month.add(new JLabel("Tisdag"));
		month.add(new JLabel("Onsdag"));
		month.add(new JLabel("Torsdag"));
		month.add(new JLabel("Fredag"));
		month.add(new JLabel("Lördag"));
		month.add(new JLabel("Söndag"));
		if(weekDay!=1){
			for (int i=0;i<(weekDay-1);i++){
				JLabel empty = new JLabel("");
				month.add(empty);
			} 
		}
		
		j=1;
		while (true) {
			JLabel dayNumber = new JLabel(Integer.toString(j));
			j++;
			month.add(dayNumber);
			if(j>31){
				break;
			}
		}
		for (int i = 0; i < (11-weekDay); i++) {
			JLabel empty = new JLabel("");
			month.add(empty);
		}
		month.updateUI();
		
	}
}
