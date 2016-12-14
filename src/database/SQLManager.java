package database;

import javax.swing.JOptionPane;

import org.mindrot.jbcrypt.BCrypt;

import object.User;
import views.Window;
import views.WindowPanel;

public class SQLManager {
	private static JavaDB db = new JavaDB();
	private static User user;
	private static int tempEventId, tempCalId;

	public static boolean checkLogin(String emailfield, char[] passfield, Window window, WindowPanel windowpanel) {
		String loginPassHashed = "";
		Object[][] data = db.getData("select * from user where email = '" + emailfield + "'");
		int accfound = 0;
		try {
			loginPassHashed = (String) data[0][2];
			accfound = 1;
		} catch (ArrayIndexOutOfBoundsException e1) {
			JOptionPane.showMessageDialog(window,
					"Vi hittar inget konto kopplat till den email-adressen, var god försök igen!",
					"Inloggning misslyckades!", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		if (accfound == 1) {
			char[] loginPassCandidate = passfield;
			if (BCrypt.checkpw(String.valueOf(loginPassCandidate), loginPassHashed)) {
				System.out.println("It matches");
				user = new User(Integer.parseInt((String) data[0][0]), (String) data[0][1], (String) data[0][3],
						(String) data[0][4], (String) data[0][5]);
				// user = new user (int,string,string,string);
				user.reloadarrays();
				windowpanel.sendUser(user);
				return true;
			} else {
				// System.out.println("It does not match");
				JOptionPane.showMessageDialog(window,
						"Du har angivit fel lösenord för det hör kontot, var god försök igen!",
						"Inloggning misslyckades!", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
		}
		return false;
	}

	public static boolean register(Window window, String fname, String sname, String email, char[] pass1,char[] pass2) {
		int pass1Length = pass1.length;
		int pass2Length = pass2.length;
		boolean passwordMatch = true;
		String hashed = "";
		
		if(fname.length()<2){
			JOptionPane.showMessageDialog(window, "Förnamn behöver vara åtminstone 2 bokstäver",
					"Registrering misslyckades!", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if(sname.length()<2){
			JOptionPane.showMessageDialog(window, "Efternamn behöver vara åtminstone 2 bokstäver",
					"Registrering misslyckades!", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if(pass1.length<5){
			JOptionPane.showMessageDialog(window, "Lösenordet behöver vara minst 5 tecken",
					"Registrering misslyckades!", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if(email.contains("@")&&email.contains(".")&&email.length()>4){
			
		}else{
			JOptionPane.showMessageDialog(window, "Emailen är felaktig eller för kort (Minst 5 tecken)",
					"Registrering misslyckades!", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		
		if (pass1Length == pass2Length) {
			for (int i = 1; i <= pass1Length; i++) {
				if (pass1[i - 1] == pass2[i - 1]) {
				} else {
					passwordMatch = false;
					JOptionPane.showMessageDialog(window, "Lösenorden stämmer inte överens!",
							"Registrering misslyckades!", JOptionPane.INFORMATION_MESSAGE);
					return false;
				}
			}

		} else {
			passwordMatch = false;
			JOptionPane.showMessageDialog(window, "Lösenorden är inte lika långa!", "Registrering misslyckades!",
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		if (passwordMatch) {
			hashed = BCrypt.hashpw(String.valueOf(pass1), BCrypt.gensalt());
			String SQL = "INSERT INTO user(email,password,fname,sname) VALUES('" + email + "','" + hashed + "','"
					+ fname + "','" + sname + "');";
			db.execute(SQL);

			SQL = "SELECT * FROM user WHERE email = '" + email + "'";
			Object[][] data = db.getData(SQL);
			int user_id = Integer.parseInt((String) data[0][0]);

			SQL = "INSERT INTO calendar(creator_id,name,description,notification) VALUES('" + user_id
					+ "','Min kalender','Det här är din standard kalender',1);";
			db.execute(SQL);

			JOptionPane.showMessageDialog(window, "Tack för att du registrerade dig, " + fname + " " + sname,
					"Registrering lyckades!", JOptionPane.INFORMATION_MESSAGE);
			return true;
		}
		return false;
	}

	public static boolean addCalendar(String calName, String calDesc) {
		String SQL = "INSERT INTO calendar(creator_id, name, description) VALUES('" + user.getId() + "','" + calName
				+ "','" + calDesc + "');";
		db.execute(SQL);
		return true;
	}

	public static boolean editCalendar(int calId, String calName, String calDesc) {

		tempCalId = calId;

		String SQL = "UPDATE calendar SET name='" + calName + "', description='" + calDesc + "' WHERE cal_id= '" + calId
				+ "'";
		db.execute(SQL);
		return true;
	}

	public static boolean manageCalendar(int calId, String calName, String calDesc) {
		String SQL = "INSERT INTO calendar(creator_id, name, description) VALUES('" + user.getId() + "','" + calName
				+ "','" + calDesc + "');";
		db.execute(SQL);
		return true;
	}

	public static boolean removeCalendar(int calID) {
		String SQL = "DELETE FROM calendar WHERE cal_id = " + calID;
		db.execute(SQL);
		return true;
	}

	public static boolean addEvent(String inputEventName, String inputEventLocation, String inputEventTextArea,
			int inputFullDayEvent, int inputCreateEventForCalendarId, String formatStartDate, String formatEndDate) {

		String SQL = "INSERT INTO event(cal_id, creator_id, name, location, description, start_time, end_time, notification, full_day) VALUES('"
				+ inputCreateEventForCalendarId + "','" + user.getId() + "','" + inputEventName + "','"
				+ inputEventLocation + "','" + inputEventTextArea + "','" + formatStartDate + "','" + formatEndDate
				+ "','1','" + inputFullDayEvent + "' )";

		db.execute(SQL);

		SQL = "SELECT MAX(event_id) FROM event WHERE cal_id = '" + inputCreateEventForCalendarId
				+ "' AND creator_id = '" + user.getId() + "' AND name = '" + inputEventName + "' AND description = '"
				+ inputEventTextArea + "' AND start_time = '" + formatStartDate + "' AND end_time = '" + formatEndDate
				+ "' AND notification = 1 AND full_day = '" + inputFullDayEvent + "'";

		Object[][] data = db.getData(SQL);

		for (int i = 0; i < data.length; i++) {
			tempEventId = Integer.parseInt((String) data[0][0]);

			System.out.println(tempEventId + " AYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY ");

		}

		db.execute(SQL);

		return true;
	}

	public static Object[][] getSharedEvent() {

		String SQL = "SELECT shared_event.se_id, shared_event.event_id, shared_event.user_id, event.creator_id, shared_event.created, event.name FROM shared_event LEFT JOIN event ON shared_event.event_id = event.event_id WHERE shared_event.user_id = "
				+ user.getId() + " AND accepted = 0";

		Object[][] data = db.getData(SQL);

		db.execute(SQL);

		return data;
	}

	public static Object[][] getSharedCal() {

		String SQL = "SELECT shared_calendar.sc_id, shared_calendar.cal_id, shared_calendar.user_id, calendar.creator_id, shared_calendar.created, calendar.name FROM shared_calendar LEFT JOIN calendar ON shared_calendar.cal_id = calendar.cal_id WHERE shared_calendar.user_id = "
				+ user.getId() + " AND accepted = 0";

		Object[][] data = db.getData(SQL);

		db.execute(SQL);

		return data;
	}

	public static boolean acceptSharedEvent(int buttonId) {

		String SQL = "UPDATE shared_event SET accepted = 1 WHERE se_id = " + buttonId;

		db.execute(SQL);

		return true;
	}

	public static boolean declineSharedEvent(int buttonId) {

		String SQL = "DELETE FROM shared_event WHERE se_id = " + buttonId;

		db.execute(SQL);

		return true;

	}

	public static boolean acceptSharedCal(int buttonId) {

		String SQL = "UPDATE shared_calendar SET accepted = 1 WHERE sc_id = " + buttonId;

		db.execute(SQL);

		return true;
	}

	public static boolean declineSharedCal(int buttonId) {

		String SQL = "DELETE FROM shared_calendar WHERE sc_id = " + buttonId;

		db.execute(SQL);

		return true;

	}

	public static boolean editEvent(String inputEventName, String inputEventLocation, String inputEventTextArea,
			int inputFullDayEvent, int inputEventId, String formatStartDate, String formatEndDate) {

		String SQL = "UPDATE event SET name = '" + inputEventName + "', location = '" + inputEventLocation
				+ "', description = '" + inputEventTextArea + "', start_time = '" + formatStartDate + "', end_time = '"
				+ formatEndDate + "' WHERE event_id = " + inputEventId;

		db.execute(SQL);

		SQL = "SELECT MAX(event_id) FROM event WHERE creator_id = '" + user.getId() + "' AND name = '" + inputEventName
				+ "' AND description = '" + inputEventTextArea + "' AND start_time = '" + formatStartDate
				+ "' AND end_time = '" + formatEndDate + "' AND notification = 1 AND full_day = '" + inputFullDayEvent
				+ "'";

		Object[][] data = db.getData(SQL);

		for (int i = 0; i < data.length; i++) {
			tempEventId = Integer.parseInt((String) data[0][0]);

			System.out.println(tempEventId + " AYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY ");

		}

		db.execute(SQL);
		return true;
	}

	public static boolean deleteEvent(int eventId) {

		String SQL = "DELETE FROM event WHERE event_id = " + eventId;

		db.execute(SQL);

		return true;
	}

	public static Object[][] searchForUser(String inputSearch) {

		String SQL = "SELECT user.user_id, CONCAT(user.fname,' ', user.sname) AS name, user.email FROM user WHERE user_id != "
				+ user.getId() + " AND (LCASE(CONCAT(user.fname,' ', user.sname)) LIKE LCASE('%" + inputSearch
				+ "%')  OR LCASE(email) LIKE LCASE('%" + inputSearch + "%'))";

		Object[][] data = db.getData(SQL);

		db.execute(SQL);

		// System.out.println(data);

		return data;

	}

	public static boolean sendEventInvite(int tempSelectedUserId) {

		String SQL = "INSERT INTO shared_event(event_id, user_id, accepted, notification) VALUES('" + tempEventId
				+ "', '" + tempSelectedUserId + "', '0', '1')";

		db.execute(SQL);

		return true;
	}

	public static boolean sendCalInvite(int tempSelectedUserId) {

		String SQL = "INSERT INTO shared_calendar(cal_id, user_id, accepted, notification) VALUES('" + tempCalId
				+ "', '" + tempSelectedUserId + "', '0', '1')";

		db.execute(SQL);

		return true;
	}

	public static Object[][] getClosestEvent() {

		String SQL = "SELECT event_id, name, start_time, end_time FROM event WHERE creator_id = " + user.getId()
				+ " AND start_time > NOW() ORDER BY start_time LIMIT 1";

		Object[][] data = db.getData(SQL);

		db.execute(SQL);

		return data;
	}

	public static JavaDB getDb() {
		return db;
	}

	public static void setDb(JavaDB db) {
		SQLManager.db = db;
	}

	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		SQLManager.user = user;
	}

}
