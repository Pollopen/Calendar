package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controller.StateMachine;
import object.User;

public class YearView extends JPanel{
	private StateMachine SM;
	private String focusDate;
	private Border etchedBorder;
	private TitledBorder title;
	private WindowPanel wp;
	public YearView(StateMachine SM, User user, WindowPanel wp){
		etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		title = BorderFactory.createTitledBorder(
                etchedBorder, SM.getFocusedDate().substring(0,4));
		title.setTitleJustification(TitledBorder.CENTER);
		title.setTitleFont(new Font("Serif", Font.PLAIN, 25));
		
		setBorder(title);
		this.SM=SM;
		this.wp=wp;
		focusDate=SM.getFocusedDate();
		setPreferredSize(new Dimension(1175, 725));
		setLayout(new GridLayout(3, 4));
		
		for (int i = 0; i < 12; i++) {
			add(new MonthOverview(SM, user, changeMonth(focusDate, i),true,wp));
		}
		
	}
	private Date changeMonth(String focusedDate, int month){
		Date date = null;
		String tempDate;
		if(month<=8){
			//    01
			tempDate=focusedDate.substring(0, 5)+"0"+(month+1)+focusedDate.substring(7);
		}else{
			tempDate=focusedDate.substring(0, 5)+(month+1)+focusedDate.substring(7);
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
