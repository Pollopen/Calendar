package weekView;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import object.Event;

public class NormalEventWeek extends JButton implements ActionListener {
	private Event event;
	public NormalEventWeek(String name, Event event) {
		super(name);
		this.event=event;
		setToolTipText(this.event.getName());
		setMargin(new Insets(0, 0, 0, 0));
		setBackground(new Color(175,255,255));
		addActionListener(this);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(event.getName());
	}

}
