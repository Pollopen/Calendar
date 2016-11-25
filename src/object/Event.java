package object;

import java.awt.Point;

import javax.swing.JPanel;

public class Event extends JPanel {

	private int event_id;
	private int calendar_id;
	private int creator_id;
	private String name;
	private String loc;
	private String description;
	private String created;
	private String start_time;
	private String length;
	private String edited;
	private boolean notification;

	public Event(int e_id, int cal_id, int create_id, String event_name, String event_location, String event_desc,
			String event_created, String event_start, String event_length, String event_edited, boolean event_notice) {
		event_id = e_id;
		calendar_id = cal_id;
		creator_id = create_id;
		name = event_name;
		loc = event_location;
		description = event_desc;
		created = event_created;
		start_time = event_start;
		length = event_length;
		edited = event_edited;
		notification = event_notice;
	}

	public int getEvent_id() {
		return event_id;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}

	public int getCal_id() {
		return calendar_id;
	}

	public void setCal_id(int cal_id) {
		this.calendar_id = cal_id;
	}

	public int getCreator_id() {
		return creator_id;
	}

	public void setCreator_id(int creator_id) {
		this.creator_id = creator_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public boolean isNotification() {
		return notification;
	}

	public void setNotification(boolean notification) {
		this.notification = notification;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getEdited() {
		return edited;
	}

	public void setEdited(String edited) {
		this.edited = edited;
	}

}
