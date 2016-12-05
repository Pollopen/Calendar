package controller;

import object.User;

public class StateMachine {
	private int calEditStatus;
	private int eventEditStatus;
	private int activeview;//1=day 2=week 3=month 4=year
	private int pagesLogin;//1=login 2=register 3=logged in (index)
	private int[] activeCalendars;
	private User user;
	
	public StateMachine(User user){
		this.user=user;
		calEditStatus=0;
		activeCalendars=null;
		activeview=1;
		pagesLogin=1;
	}

	public int getCalEditStatus() {
		return calEditStatus;
	}

	public void setCalEditStatus(int calEditStatus) {
		this.calEditStatus = calEditStatus;
	}

	public int getEventEditStatus() {
		return eventEditStatus;
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
}
