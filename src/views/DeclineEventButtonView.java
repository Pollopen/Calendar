package views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import database.SQLManager;
import object.User;

public class DeclineEventButtonView extends JButton {

	private int buttonId;
	private WindowPanel wp;
	private User user;

	public DeclineEventButtonView(int sharedEventId, WindowPanel wp, User user) {

		this.wp = wp;
		this.user = user;
		this.setText("Neka");
		this.setBackground(new Color(255, 150, 150));

		buttonId = sharedEventId;

		ListenForButton lForButton = new ListenForButton();

		this.addActionListener(lForButton);

	}

	public int getButtonId() {
		return buttonId;
	}

	public void setButtonId(int buttonId) {
		this.buttonId = buttonId;
	}

	private class ListenForButton implements ActionListener {

		// This method is called when an event occurs

		public void actionPerformed(ActionEvent e) {

			SQLManager.declineSharedEvent(buttonId);

			user.reloadarrays();

			wp.getInviteView();

			wp.getOverview();

			wp.calChoiceList();

			wp.getSharedEventPage();

		}
	}

}
