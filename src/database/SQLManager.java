package database;

import javax.swing.JOptionPane;

import org.mindrot.jbcrypt.BCrypt;

import object.User;
import views.Window;
import views.WindowPanel;

public class SQLManager {
	private static JavaDB db = new JavaDB();
	private static User user;

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
				user.getAll();
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

	public static boolean register(Window window, String fname, String sname, String email, char[] pass1,
			char[] pass2) {
		int pass1Length = pass1.length;
		int pass2Length = pass2.length;
		boolean passwordMatch = true;
		String hashed = "";
		// password=String.valueOf(pass1);
		// System.out.println(password);
		if (pass1Length == pass2Length) {
			for (int i = 1; i <= pass1Length; i++) {
				if (pass1[i - 1] == pass2[i - 1]) {
				} else {
					passwordMatch = false;
					return false;
				}
			}

		} else {
			passwordMatch = false;
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
	public static boolean addCalender(String calName, String calDesc){
		String SQL = "INSERT INTO calendar(creator_id, name, description) VALUES('" + user.getId() + "','" + calName + "','"
				+ calDesc + "');";
		db.execute(SQL);
		return true;
	}
	public static boolean manageCalender(int calId, String calName, String calDesc){
		return false;
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
