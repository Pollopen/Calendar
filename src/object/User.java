package object;

import database.JavaDB;

public class User {
	int id;
	String email;
	String fname;
	String sname;
	String created;
	Calendar[] calArray;
	Event[] eventArray;
	
	private JavaDB db = new JavaDB("localhost","root","","calendar");
	
	public User(int userid, String useremail, String userfname, String usersname, String usercreated){
		id=userid;
		email=useremail;
		fname=userfname;
		sname=usersname;
		created=usercreated;
	}
	public void reloadarrays(/*Array for which calendars to ignore*/){
		Object[][]data = db.getData("select * from Calendar where creator_id = "+id);
		int numberofresults=data.length;
		int eventnum;
		calArray = new Calendar[numberofresults];
		Object[][]data2 = db.getData("SELECT COUNT(event.event_id) FROM calendar INNER JOIN event ON calendar.cal_id = event.cal_id WHERE calendar.creator_id = 1");
		eventArray = new Event[Integer.parseInt((String) data2[0][0])];
		for(int i=0;i<=numberofresults;i++){
			calArray[i]= new Calendar(Integer.parseInt((String) data[i][0]), Integer.parseInt((String) data[i][1]), (String) data[i][2], (String) data[i][3], (String) data[i][4], (String) data[i][5], Integer.parseInt((String) data[i][6]));
			Object[][]data3 = db.getData("SELECT * FROM event where cal_id = "+calArray[i].getCal_id());
			eventnum=data3.length;
			for(int j=0;j<=eventnum;j++){
				
			}
			
		}
	}
	public void getAll() {
		System.out.println(id);
		System.out.println(email);
		System.out.println(fname);
		System.out.println(sname);
		System.out.println(created);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}
	/*
	 * public int getCreated() { return created; }
	 * 
	 * public void setCreated(int created) { this.created = created; }
	 * 
	 */
}
