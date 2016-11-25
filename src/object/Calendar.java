package object;

import javax.swing.JPanel;

public class Calendar extends JPanel {
	int cal_id;
	int creator_id;
	String name;
	String description;
	String created;
	String edited;
	int Notification;
	
	public Calendar(int caid,int crid, String cname, String cdesc, String ccreated, String cedited, int not){
		cal_id=caid;
		creator_id=crid;
		name=cname;
		description=cdesc;
		created=ccreated;
		edited=cedited;
		Notification=not;
	}

	public int getCal_id() {
		return cal_id;
	}

	public void setCal_id(int cal_id) {
		this.cal_id = cal_id;
	}
	

}
