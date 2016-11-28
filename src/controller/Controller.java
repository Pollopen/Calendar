package controller;

import database.JavaDB;
import views.Window;

public class Controller {

	public static void main(String[] args) {
		//JavaDB db = new JavaDB("localhost", "root", "", "calendar");
		new Window();
		// String SQL="insert into user(email,password,fname,sname)
		// values('kevinhedsand@yahoo.se','123','Kevin','Hedsand');";
		// db.execute(SQL);

		// Object[][]data = db.getData("select *from user");

	}

}
