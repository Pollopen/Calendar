package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import database.SQLManager;

public class ManageInviteView extends JPanel {

	private JButton calButton, eventButton;
	private JLabel inviteLabel;
	private GridBagConstraints gbc;
	private int calInvites, eventInvites;
	private WindowPanel wp;

	public ManageInviteView(WindowPanel wp) {

		this.wp = wp;
		this.setPreferredSize(new Dimension(200, 200));
		this.setLayout(new GridBagLayout());
		this.setVisible(true);

		eventInvites = SQLManager.getAmountOfEventInvites();

		calInvites = SQLManager.getAmountOfCalInvites();

		gbc = new GridBagConstraints();

		calButton = new JButton();
		calButton.setMargin(new Insets(0, 0, 0, 0));
		calButton.setText("(" + calInvites + ")");
		calButton.setToolTipText("Antal kalender inbjudningar");
		calButton.setBackground(new Color(220, 220, 220));
		calButton.setPreferredSize(new Dimension(50, 40));

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(5, 0, 5, 10);

		this.add(calButton, gbc);

		eventButton = new JButton();
		eventButton.setMargin(new Insets(0, 0, 0, 0));
		eventButton.setText("(" + eventInvites + ")");
		eventButton.setToolTipText("Antal event inbjudningar");
		eventButton.setBackground(new Color(220, 220, 220));
		eventButton.setPreferredSize(new Dimension(50, 40));

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(5, 0, 5, 0);

		this.add(eventButton, gbc);

		ListenForButton lForButton = new ListenForButton();

		calButton.addActionListener(lForButton);
		eventButton.addActionListener(lForButton);

	}

	private class ListenForButton implements ActionListener {

		// This method is called when an event occurs

		public void actionPerformed(ActionEvent e) {
			// Check if the source of the event was the button

			if (e.getSource() == calButton) {
				wp.getSharedCalPage();
			}

			if (e.getSource() == eventButton) {
				wp.getSharedEventPage();
			}

		}

	}

}
