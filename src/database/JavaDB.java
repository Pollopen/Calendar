
package database;

import java.sql.*;

import javax.swing.JOptionPane;

public class JavaDB {
	private Connection con;
	private Object[] fields;
	private String host="hedsand.com", database="calendar", user="calendar", password="qePlz249ER713diT";

	public JavaDB() {
		try {
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database+ "?useSSL=false", user, password);
		} catch (Exception error) {
			JOptionPane.showMessageDialog(null, error);
		}
		fields = new Object[0];
	}

	public Object[] getColums() {
		return fields;

	}

	public Object[][] getData(String SQL) {
		Object[][] data = null;
		try {
			Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = statement.executeQuery(SQL);
			ResultSetMetaData md = rs.getMetaData();
			int countColumn = md.getColumnCount();
			int countRows = 0;

			/* Fyller på kolumnamnen */

			fields = new Object[countColumn];
			for (int i = 0; i < fields.length; i++) {
				fields[i] = md.getColumnName(i + 1);
			}

			/* Räknar antalet returrader för att klunna allokera en matris */

			while (rs.next())
				countRows++;
			rs.beforeFirst();
			data = new Object[countRows][countColumn];
			int row = 0;
			while (rs.next()) {
				for (int i = 0; i < countColumn; i++) {
					data[row][i] = rs.getString(i + 1);
				}
				row++;
			}

		} catch (Exception error) {
			JOptionPane.showMessageDialog(null, error);
		}
		return data;
	}

	public void execute(String SQL) {
		try {
			Statement statement = con.createStatement();
			statement.execute(SQL);
		} catch (Exception error) {
			JOptionPane.showMessageDialog(null, error);
		}
	}
}