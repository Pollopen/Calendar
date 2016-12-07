package CalViewButton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class OverviewDayButton extends JButton implements ActionListener {
	private String date;
	public OverviewDayButton(String text, String date, boolean hasEvent) {
		super(text);
		this.date=date;
		addActionListener(this);
		if(hasEvent){
			setBackground(Color.RED);
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