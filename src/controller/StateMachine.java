package controller;

import object.User;

public class StateMachine {
	int calEditStatus;
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
}
