package CalViewButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import controller.StateMachine;
import object.Event;
import views.WindowPanel;

public class DayButton extends JButton implements ActionListener {
	private String date;
	private StateMachine SM;
	private WindowPanel wp;
	public DayButton(String text, String date, int isdatenumber,StateMachine SM, WindowPanel wp) {// 1=date number 2= normal event 3= full day event
		super(text);
		this.wp=wp;
		this.SM=SM;
		this.date=date;
		setPreferredSize(new Dimension(10, 20));
		addActionListener(this);
		setMargin(new Insets(0, 0, 0, 0));
		
		if(date.equals(SM.getEasyDate())&&isdatenumber==1){
			setForeground(Color.RED);
		}
		if(isdatenumber>=2){
			setToolTipText("Mer event finns!");
			setBackground(new Color(200,200,200));
			if (isdatenumber==2) {
				setPreferredSize(new Dimension(50, 50));
			}
			
		}else{
			//setBorderPainted(false);
			setFocusPainted(false);
			setContentAreaFilled(false);
		}
		
		// TODO Auto-generated constructor stub
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(date);
		SM.setEasyDate(date);
		SM.setActiveview(1);
		wp.getViewViewer();
		wp.getViewChoice();
		wp.getOverview();
	}

}