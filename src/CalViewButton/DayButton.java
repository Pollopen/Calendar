package CalViewButton;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import object.Event;

public class DayButton extends JButton implements ActionListener {
	private String date;
	public DayButton(String text, String date, int isdatenumber) {// 1=date number 2= normal event 3= full day event
		super(text);
		this.date=date;
		setPreferredSize(new Dimension(10, 20));
		addActionListener(this);
		if(isdatenumber>=2){
			setToolTipText("Mer event finns!");
			if (isdatenumber==2) {
				setPreferredSize(new Dimension(50, 50));
			}
			
		}
		// TODO Auto-generated constructor stub
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(date);
	}

}