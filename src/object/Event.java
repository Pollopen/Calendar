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
	private String end_time;
	private String edited;
	private int notification;
	private int fullDay;

	public Event(int e_id, int cal_id, int create_id, String event_name, String event_location, String event_desc,
			String event_created, String event_start, String event_end, String event_edited, int event_notice,
			int event_full_day) {
		event_id = e_id;
		calendar_id = cal_id;
		creator_id = create_id;
		name = event_name;
		loc = event_location;
		description = event_desc;
		created = event_created;
		start_time = event_start;
		end_time = event_end;
		edited = event_edited;
		notification = event_notice;
		fullDay = event_full_day;
	}

	public void getAll() {
		System.out.print(event_id);
		System.out.print(calendar_id);
		System.out.print(creator_id);
		System.out.print(name);
		System.out.print(loc);
		System.out.print(description);
		System.out.print(created);
		System.out.print(start_time);
		System.out.print(end_time);
		System.out.print(edited);
		System.out.println(notification);
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

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String length) {
		this.end_time = length;
	}

	public int isNotification() {
		return notification;
	}

	public void setNotification(int notification) {
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

	public int getEvent_full_day() {
		return fullDay;
	}

	public void setEvent_full_day(int event_full_day) {
		this.fullDay = event_full_day;
	}

	public int getCalendar_id() {
		return calendar_id;
	}

	public void setCalendar_id(int calendar_id) {
		this.calendar_id = calendar_id;
	}

	public int getFullDay() {
		return fullDay;
	}

	public void setFullDay(int fullDay) {
		this.fullDay = fullDay;
	}

	public int getNotification() {
		return notification;
	}

}
