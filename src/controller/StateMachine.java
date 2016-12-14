package controller;

import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import object.Event;
import object.User;

public class StateMachine {
	private int calEditStatus;
	private int eventEditStatus;
	private int activeview;//1=day 2=week 3=month 4=year
	private int pagesLogin;//1=login 2=register 3=logged in (index)
	private int[] activeCalendars;
	private String focusedDate;
	private User user;
	private DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	private Date date;
	private Color[] eventColor;
	
	public StateMachine(User user){
		this.user=user;
		calEditStatus=0;
		activeCalendars=null;
		activeview=3;
		pagesLogin=1;
		date = new Date();
		focusedDate=sdf.format(date);
		eventColor=genColorArray();
	}

	private Color[] genColorArray() {
		Color[] temp = new Color[10];
		temp[0]= new Color(154,255,143);
		temp[1]= new Color(143,210,255);
		temp[2]= new Color(255,143,210);
		temp[3]= new Color(210,255,143);
		temp[4]= new Color(197,158,255);
		temp[5]= new Color(255,244,143);
		temp[6]= new Color(255,143,154);
		temp[7]= new Color(143,255,188);
		temp[8]= new Color(188,143,255);
		temp[9]= new Color(186,255,82);
		return temp;
	}

	public int getCalEditStatus() {
		return calEditStatus;
	}
	public String getEasyDate(){
		String tempDate=sdf.format(date);
		String formatDate=tempDate.substring(0, 4)+tempDate.substring(5, 7)+tempDate.substring(8, 10);
		return formatDate;
	}
	public String getEasyFocusDate(){
		String tempDate=focusedDate;
		String formatDate=tempDate.substring(0, 4)+tempDate.substring(5, 7)+tempDate.substring(8, 10);
		return formatDate;
	}

	public void setCalEditStatus(int calEditStatus) {
		this.calEditStatus = calEditStatus;
	}

	public int getEventEditStatus() {
		return eventEditStatus;
	}
	
	public void setFocusedToday(){
		focusedDate=sdf.format(date);
	}

	public void setEventEditStatus(int eventEditStatus) {
		this.eventEditStatus = eventEditStatus;
	}

	public int[] getActiveCalendars() {
		return activeCalendars;
	}

	public void setActiveCalendars(int[] activeCalendars) {
		this.activeCalendars = activeCalendars;
	}

	public int getActiveview() {
		return activeview;
	}

	public void setActiveview(int activeview) {
		this.activeview = activeview;
	}

	public int getPagesLogin() {
		return pagesLogin;
	}

	public void setPagesLogin(int pagesLogin) {
		this.pagesLogin = pagesLogin;
	}
	public void setEasyDate(String date) {
		// TODO format date and set focuseddate
		String formattedDate;
		formattedDate=date.substring(0,4)+"/"+date.substring(4,6)+"/"+date.substring(6,8);
		focusedDate=formattedDate;
	}
	//can go forward X days(5) or back X days (-5)
		public void addToFocusDate(int value){
			Calendar cal = Calendar.getInstance();
			String focusedDate = getFocusedDate();
			focusedDate=DateHandler.StringDateToCalendar(focusedDate);
			try {
				int test = Integer.parseInt(focusedDate.substring(8, 10));
			} catch (Exception e) {
				focusedDate=DateHandler.convertFromEasyDate(focusedDate);
			}
			cal.set(Integer.parseInt(focusedDate.substring(0, 4)), Integer.parseInt(focusedDate.substring(5, 7)), Integer.parseInt(focusedDate.substring(8, 10)));
			String newFocusedDate;
			cal.add(Calendar.DATE, value);
			newFocusedDate = Integer.toString(cal.get(Calendar.YEAR));
			if(cal.get(Calendar.MONTH)<=9){
				newFocusedDate+="0"+Integer.toString(cal.get(Calendar.MONTH));
			}else{
				newFocusedDate+=Integer.toString(cal.get(Calendar.MONTH));
			}
			if(cal.get(Calendar.DATE)<=9){
				newFocusedDate+="0"+Integer.toString(cal.get(Calendar.DATE));
			}else{
				newFocusedDate+=Integer.toString(cal.get(Calendar.DATE));
			}
			
			
			newFocusedDate=DateHandler.CalendarStringToDateString(newFocusedDate);
			
			setEasyDate(newFocusedDate);
		}

	public Date getdate() {
		return date;
	}

	public String getFocusedDate() {
		return focusedDate;
	}

	public void setFocusedDate(String focusedDate) {
		this.focusedDate = focusedDate;
	}
	public void forwardYear() {
		String focusDate=getEasyFocusDate();
		int tempYear=Integer.parseInt(focusDate.substring(0, 4));
		tempYear++;
		String newDate=tempYear+focusDate.substring(4);
		setEasyDate(newDate);
	}
	public void backYear(){
		String focusDate=getEasyFocusDate();
		int tempYear=Integer.parseInt(focusDate.substring(0, 4));
		tempYear--;
		String newDate=tempYear+focusDate.substring(4);
		setEasyDate(newDate);
	}
	public Color getColor(int num){
		while (num>10){
			num-=10;
		}
		Color temp=eventColor[num];
		return temp;
	}

	public int getIndexOfCal(object.Calendar[] calArray, int calID) {
		for (int i = 0; i < calArray.length; i++) {
			if(calID==calArray[i].getCal_id()){
				return i;
			}		
		}
		return 1;
	}
	
}
