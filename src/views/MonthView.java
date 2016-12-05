package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPanel;

import controller.StateMachine;

public class MonthView extends JPanel{
	private JPanel month;
	private StateMachine SM;
	private String date;
	private Date focusedDate;
	private DateFormat getFocusDate = new SimpleDateFormat("yyyy/MM/dd");
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
		
		
		month = new JPanel();
		month.setLayout(new GridLayout(7,7));
		
	}
}
