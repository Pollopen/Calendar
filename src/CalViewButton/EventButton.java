package CalViewButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import object.Event;

public class EventButton extends JButton implements ActionListener {
	private Event event;
	public EventButton(String text, Event event) {
		super(text);
		this.event=event;
		setToolTipText(this.event.getName());
		setMargin(new Insets(0, 0, 0, 0));
		setBackground(new Color(200,200,200));
		if(event.getFullDay()!=1){
			setPreferredSize(new Dimension(50, 50));
		}
		addActionListener(this);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(event.getName());
	}

}
