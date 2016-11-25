package object;

import javax.swing.JPanel;

public class Calendar extends JPanel {
	int cal_id;
	int creator_id;
	String name;
	String description;
	String created;
	String edited;
	int notification;

	public Calendar(int caid, int crid, String cname, String cdesc, String ccreated, String cedited, int not) {
		cal_id = caid;
		creator_id = crid;
		name = cname;
		description = cdesc;
		created = ccreated;
		edited = cedited;
		notification = not;
	}

	public void getAll() {
		System.out.print("Kalender id: " + cal_id + " ");
		System.out.print("Skapar: " + creator_id + " ");
		System.out.print("Kalender namn: " + name + " ");
		System.out.print("Kalender beskrivning: " + description + " ");
		System.out.print("Skapad: " + created + " ");
		// System.out.print("Ändrad: "+edited+" ");
		System.out.println("Notifikation: " + notification + " ");
	}

	public int getCal_id() {
		return cal_id;
	}

	public void setCal_id(int cal_id) {
		this.cal_id = cal_id;
	}

}
