package controller;

import database.JavaDB;
import views.Window;

public class Program{

	public static void main(String[] args) {
	JavaDB db = new JavaDB("localhost","root","","calendar");
	new Window();
	//String SQL="insert into user(email,password,fname,sname) values('kevinhedsand@yahoo.se','123','Kevin','Hedsand');";
	//db.execute(SQL);
	
	Object[][]data = db.getData("select *from user");
	
			for(int i=0;i<data.length;i++)
			{
				for(int j=0;j<data[i].length;j++)
				{
					System.out.print(data[i][j]+" ");
				}
				System.out.println();
			}
			
			Object[]fields = db.getColums();
			for(Object f: fields)
				System.out.print(f+" ");

	}

}
