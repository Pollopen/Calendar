package controller;

import object.User;

public class StateMachine {
	private int calEditStatus;
	private int eventEditStatus;
	User user;
	
	public StateMachine(User user){
		this.user=user;
		
	}
	
	public int getCalEditStatus(){
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
}
