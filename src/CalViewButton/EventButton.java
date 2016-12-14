package CalViewButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import object.Event;
import views.WindowPanel;

public class EventButton extends JButton implements ActionListener {
	private Event event;
	private WindowPanel wp;
	
	public EventButton(String text, Event event, WindowPanel wp) {
		super(text);
		this.event=event;
		this.wp = wp;

		setMargin(new Insets(0, 0, 0, 0));
		setBackground(new Color(175,255,255));
		if(event.getFullDay()!=1){
			setPreferredSize(new Dimension(50, 50));
		}
		addActionListener(this);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(" ------------------------------------------ ");
		System.out.println(event.getName() + " Ayyyyyyyyyyyyyyyyy ");
		event.getAll();
		
		wp.getDeleteAndEditEventPage();
		
		System.out.println(" ------------------------------------------ ");
	}
	

}
