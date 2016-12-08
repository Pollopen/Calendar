package CalViewButton;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import controller.StateMachine;

public class OverviewDayButton extends JButton implements ActionListener {
	private Border etchedBorder;
	private String date;
	public OverviewDayButton(String text, String date, boolean hasEvent, StateMachine SM) {
		super(text);
		etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		this.date=date;
		addActionListener(this);
		setMargin(new Insets(0, 0, 0, 0));
		if(date.equals(SM.getFormattedDate())){
			setForeground(Color.RED);
		}
		if(hasEvent){
			setBackground(new Color(150,150,150));
			setOpaque(true);
		}
		
		// TODO Auto-generated constructor stub
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(date);
	}

}