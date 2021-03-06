package CalViewButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import controller.StateMachine;
import object.Event;
import object.User;
import views.WindowPanel;

public class EventButton extends JButton implements ActionListener {
	private Event event;
	private WindowPanel wp;
	private User user;

	public EventButton(String text, Event event, WindowPanel wp, StateMachine SM, User user) {

		super(text);
		this.event = event;
		this.wp = wp;
		this.user = user;

		setMargin(new Insets(0, 0, 0, 0));
		setBackground(SM.getColor(event.getColor()));
		setToolTipText(this.event.getName());
		if (event.getFullDay() != 1) {
			setPreferredSize(new Dimension(50, 50));
		}
		addActionListener(this);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		int tempCreatorId = event.getCreator_id();
		int tempUserId = user.getId();

		if (tempCreatorId == tempUserId) {
			wp.getDeleteAndEditEventPage(this.event);
		} else {
			JOptionPane.showMessageDialog(null,
					"Du �r inte �gare f�r det h�r eventet och f�r d�rf�r inte redigera det!");
		}

	}

}
