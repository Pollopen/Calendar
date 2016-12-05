package views;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JPanel;

public class WeekView extends JPanel {

	private Calendar weekOfMonth, weekOfYear;
	private Date today;
	private SimpleDateFormat sdf;

	public WeekView() {

		this.setLayout(new GridLayout(7, 1));
		this.setPreferredSize(new Dimension(1175, 725));
		this.setVisible(true);

		weekOfMonth.get(Calendar.WEEK_OF_MONTH);
		weekOfYear.get(Calendar.WEEK_OF_YEAR);

		today = new Date();
		sdf = new SimpleDateFormat("MM/dd/yyy");

		System.out.println(weekOfMonth);

		System.out.println(today);

	}

}
