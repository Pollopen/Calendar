package weekView;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import controller.StateMachine;
import object.Event;
import object.User;
import views.WindowPanel;

public class NormalEventWeek extends JButton implements ActionListener {
	private Event event;

	private WindowPanel wp;
	private User user;
	private StateMachine SM;

	public NormalEventWeek(String name, Event event, StateMachine SM, WindowPanel wp, User user) {

		super(name);
		this.event = event;
		this.wp = wp;
		this.user = user;
		this.SM = SM;

		setToolTipText(this.event.getName());
		setMargin(new Insets(0, 0, 0, 0));
		setBackground(SM.getColor(event.getColor()));
		addActionListener(this);
		// TODO Auto-generated constructor stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		int tempCreatorId = event.getCreator_id();
		int tempUserId = user.getId();

		if (tempCreatorId == tempUserId) {
			wp.getDeleteAndEditEventPage(this.event);
		} else {
			JOptionPane.showMessageDialog(null,
					"Du är inte ägare för det här eventet och får därför inte redigera det!");
		}
	}
}
