package controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
	
	public StateMachine(User user){
		this.user=user;
		calEditStatus=0;
		activeCalendars=null;
		activeview=1;
		pagesLogin=1;
		date = new Date();
		System.out.println(sdf.format(date)); //2016/11/16 12:08:43
		focusedDate=sdf.format(date);
	}

	public int getCalEditStatus() {
		return calEditStatus;
	}
	public String getUnformattedDate(){
		String tempDate=sdf.format(date);
		String formatDate=tempDate.substring(0, 4)+tempDate.substring(5, 7)+tempDate.substring(8, 10);
		return formatDate;
	}
	public String getUnformattedFocusDate(){
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
	public void setUnformattedDate(String date) {
		// TODO format date and set focuseddate
		String formattedDate;
		formattedDate=date.substring(0,4)+"/"+date.substring(4,6)+"/"+date.substring(6,8);
		focusedDate=formattedDate;
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
		String focusDate=getUnformattedFocusDate();
		int tempYear=Integer.parseInt(focusDate.substring(0, 4));
		tempYear++;
		String newDate=tempYear+focusDate.substring(4);
		setUnformattedDate(newDate);
		System.out.println("New focusdate(+)= "+newDate);
	}
	public void backYear(){
		String focusDate=getUnformattedFocusDate();
		int tempYear=Integer.parseInt(focusDate.substring(0, 4));
		tempYear--;
		String newDate=tempYear+focusDate.substring(4);
		setUnformattedDate(newDate);
		System.out.println("New focusdate(-)= "+newDate);
	}
	
}
