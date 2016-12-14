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

	private JavaDB db = new JavaDB();

	public User(int userid, String useremail, String userfname, String usersname, String usercreated) {
		id = userid;
		email = useremail;
		fname = userfname;
		sname = usersname;
		created = usercreated;
	}

	public void reloadarrays() {
		Object[][] data = db.getData("select * from calendar where creator_id = " + id);
		Object[][] data2 = db.getData(
				"select calendar.* from shared_calendar RIGHT JOIN calendar on shared_calendar.cal_id=calendar.cal_id where shared_calendar.user_id = "
						+ id + " AND shared_calendar.accepted=1");

		int numberofresults1 = data.length;
		int numberofresults2 = data2.length;
		int numberofresultsTotal = numberofresults1 + numberofresults2 + 1;
		int eventnum;
		int i1 = 0;
		calArray = new Calendar[numberofresultsTotal];
		for (int i = 0; i < numberofresults1; i++) {
			calArray[i] = new Calendar(Integer.parseInt((String) data[i][0]), Integer.parseInt((String) data[i][1]),
					(String) data[i][2], (String) data[i][3], (String) data[i][4], (String) data[i][5],
					Integer.parseInt((String) data[i][6]));
			i1++;
		}
		for (int i = 0; i < numberofresults2; i++) {
			calArray[i1] = new Calendar(Integer.parseInt((String) data2[i][0]), Integer.parseInt((String) data2[i][1]),
					(String) data2[i][2], (String) data2[i][3], (String) data2[i][4], (String) data2[i][5],
					Integer.parseInt((String) data2[i][6]));
			i1++;
		}
		calArray[i1] = new Calendar(0, 0, "Delade Event", "Här finns dem eventen du är inbjuden till!", null, null, 1);
		Object[][] data3 = db.getData(
				"SELECT * FROM calendar RIGHT JOIN event ON calendar.cal_id = event.cal_id WHERE calendar.creator_id = "
						+ id);
		Object[][] data4 = db.getData(
				"SELECT event.* FROM shared_calendar RIGHT JOIN calendar on shared_calendar.cal_id=calendar.cal_id RIGHT JOIN event ON calendar.cal_id = event.cal_id WHERE shared_calendar.user_id = "
						+ id + " AND shared_calendar.accepted=1");
		Object[][] data5 = db.getData(
				"SELECT event.* FROM shared_event RIGHT JOIN event ON shared_event.event_id=event.event_id WHERE shared_event.user_id = "
						+ id + " AND shared_event.accepted=1");

		int enum1 = data3.length;
		int enum2 = data4.length;
		int enum3 = data5.length;
		eventnum = enum1 + enum2 + enum3;
		eventArray = new Event[eventnum];
		int j1 = 0;
		for (int j = 0; j < enum1; j++) {
			eventArray[j] = new Event(Integer.parseInt((String) data3[j][7]), Integer.parseInt((String) data3[j][8]),
					Integer.parseInt((String) data3[j][9]), (String) data3[j][10], (String) data3[j][11],
					(String) data3[j][12], (String) data3[j][13], (String) data3[j][14], (String) data3[j][15],
					(String) data3[j][16], Integer.parseInt((String) data3[j][17]),
					Integer.parseInt((String) data3[j][18]));
			j1++;
		}
		for (int j = 0; j < enum2; j++) {
			eventArray[j1] = new Event(Integer.parseInt((String) data4[j][0]), Integer.parseInt((String) data4[j][1]),
					Integer.parseInt((String) data4[j][2]), (String) data4[j][3], (String) data4[j][4],
					(String) data4[j][5], (String) data4[j][6], (String) data4[j][7], (String) data4[j][8],
					(String) data4[j][9], Integer.parseInt((String) data4[j][10]),
					Integer.parseInt((String) data4[j][11]));
			j1++;
		}
		for (int j = 0; j < enum3; j++) {
			eventArray[j1] = new Event(Integer.parseInt((String) data5[j][0]), 0,
					Integer.parseInt((String) data5[j][2]), (String) data5[j][3], (String) data5[j][4],
					(String) data5[j][5], (String) data5[j][6], (String) data5[j][7], (String) data5[j][8],
					(String) data5[j][9], Integer.parseInt((String) data5[j][10]),
					Integer.parseInt((String) data5[j][11]));
			j1++;
		}
	}

	public void getAll() {
		System.out.println(id);
		System.out.println(email);
		System.out.println(fname);
		System.out.println(sname);
		System.out.println(created);
	}

	public int getStandardCal() {
		return calArray[0].getCal_id();
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

	public Calendar[] getCalArray() {
		return calArray;
	}

	public Event[] getEventArray() {
		return eventArray;
	}
}
