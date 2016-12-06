package CalViewButton;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import object.Event;

public class EventButton extends JButton implements ActionListener {
	public EventButton(String text, Event event) {
		super(text);
		if(event.getFullDay()!=1){
			setPreferredSize(new Dimension(150, 50));
		}
		addActionListener(this);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}

}
