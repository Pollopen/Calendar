package views;

import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPanel;

import controller.StateMachine;
import object.User;

public class YearView extends JPanel{
	private StateMachine SM;
	private String focusDate;
	public YearView(StateMachine SM, User user){
		this.SM=SM;
		focusDate=SM.getFocusedDate();
		setLayout(new GridLayout(3, 4));
		for (int i = 0; i < 12; i++) {
			add(new MonthOverview(SM, user, changeMonth(focusDate, i),true));
		}
		
	}
	private Date changeMonth(String focusedDate, int month){
		Date date = null;
		String tempDate;
		if(month<=10){
			//    01
			tempDate=focusedDate.substring(0, 5)+"0"+(month+1)+focusedDate.substring(7);
			System.out.println(tempDate);
		}else{
			tempDate=focusedDate.substring(0, 5)+(month+1)+focusedDate.substring(7);
			System.out.println(tempDate);
		}
		DateFormat tempFormat = new SimpleDateFormat("yyyy/MM/dd");
		try {
			date = tempFormat.parse(tempDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return date;
	}
}
