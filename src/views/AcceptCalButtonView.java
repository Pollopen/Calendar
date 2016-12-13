package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import database.SQLManager;

public class AcceptCalButtonView extends JButton {
	
	
	private int buttonId;
	private WindowPanel wp;

	public AcceptCalButtonView(int sharedEventId, WindowPanel wp) {

		this.wp = wp;
		
		this.setText("Acceptera");
		
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
	
				SQLManager.acceptSharedCal(buttonId);
				
				wp.getSharedCalPage();

		}
	}


}
