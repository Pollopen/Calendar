package views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import controller.StateMachine;

public class DayWeekAddEvent extends JButton implements ActionListener {
	private StateMachine SM;
	private String date;
	private WindowPanel wp;
	public DayWeekAddEvent(String name, String date, StateMachine SM, WindowPanel wp) {
		super(name);
		this.SM=SM;
		this.wp=wp;
		this.date=date;
		setBackground(new Color(200, 200, 200));
		addActionListener(this);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		SM.setEasyDate(date);
		wp.getViewViewer();
		wp.getViewChoice();
		wp.getOverview();
		wp.getAddEventPage();
	}

}
