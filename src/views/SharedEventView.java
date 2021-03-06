package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import database.SQLManager;
import object.User;

public class SharedEventView extends JPanel {

	private Object[][] SQLResult;
	private JPanel mainPanel, acceptEventPanel, noEventPanel, buttonPanel, acceptEventLeftPanel, acceptEventRightPanel;
	private JButton updateButton;
	private JLabel eventNameLabel, noEventLabel, eventCreatorLabel;
	private JScrollPane scroll;
	private GridBagConstraints gbc;
	private WindowPanel wp;
	private User user;

	public SharedEventView(WindowPanel wp, User user) {

		this.wp = wp;
		this.user = user;

		this.setPreferredSize(new Dimension(1175, 725));
		this.setLayout(new BorderLayout());
		this.setBackground(new Color(0, 255, 255));
		this.setVisible(true);

		gbc = new GridBagConstraints();

		buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(1175, 50));
		buttonPanel.setLayout(new GridBagLayout());

		this.add(BorderLayout.NORTH, buttonPanel);

		updateButton = new JButton("Uppdatera!");

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 10, 0);

		buttonPanel.add(updateButton, gbc);

		mainPanel = new JPanel();
		// mainPanel.setPreferredSize(new Dimension(1175, 675));
		mainPanel.setLayout(new GridBagLayout());

		scroll = new JScrollPane(mainPanel);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.revalidate();
		mainPanel.revalidate();

		// mainPanel.setBackground(new Color(255, 0, 0));

		this.add(BorderLayout.CENTER, scroll);

		SQLResult = SQLManager.getSharedEvent();

		if (SQLResult.length > 0) {
			for (int i = 0; i < SQLResult.length; i++) {
				acceptEventPanel = new JPanel();
				acceptEventPanel.setPreferredSize(new Dimension(1075, 100));
				acceptEventPanel.setLayout(new GridBagLayout());
				acceptEventPanel.setBackground(new Color(100, 100, 100));
				acceptEventPanel.revalidate();
				// acceptEventPanel.setBorder(BorderFactory.createLineBorder(new
				// Color(0, 0, 0)));

				gbc.gridx = 0;
				gbc.gridy = i;
				gbc.insets = new Insets(0, 0, 10, 0);

				mainPanel.add(acceptEventPanel, gbc);

				acceptEventLeftPanel = new JPanel();
				acceptEventLeftPanel.setPreferredSize(new Dimension(800, 100));
				acceptEventLeftPanel.setLayout(new GridBagLayout());
				acceptEventLeftPanel.setBackground(new Color(200, 200, 200));
				// acceptEventLeftPanel.setBackground(new Color(0, 0, 255));

				gbc.gridx = 0;
				gbc.gridy = 0;
				gbc.insets = new Insets(0, 0, 0, 0);

				acceptEventPanel.add(acceptEventLeftPanel, gbc);

				acceptEventRightPanel = new JPanel();
				acceptEventRightPanel.setPreferredSize(new Dimension(275, 100));
				acceptEventRightPanel.setLayout(new GridBagLayout());
				acceptEventRightPanel.setBackground(new Color(200, 200, 200));
				// acceptEventRightPanel.setBackground(new Color(0, 255, 0));

				gbc.gridx = 1;
				gbc.gridy = 0;
				gbc.insets = new Insets(0, 0, 0, 0);

				acceptEventPanel.add(acceptEventRightPanel, gbc);

				eventNameLabel = new JLabel();
				eventNameLabel.setText(SQLResult[i][5].toString());
				eventNameLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));

				gbc.gridx = 0;
				gbc.gridy = 0;
				gbc.insets = new Insets(0, 0, 10, 0);

				acceptEventLeftPanel.add(eventNameLabel, gbc);

				eventCreatorLabel = new JLabel();
				eventCreatorLabel.setText("Event �gare: " + SQLResult[i][6].toString());

				gbc.gridx = 0;
				gbc.gridy = 1;
				gbc.insets = new Insets(0, 0, 0, 0);

				acceptEventLeftPanel.add(eventCreatorLabel, gbc);

				gbc.gridx = 0;
				gbc.gridy = 0;
				gbc.insets = new Insets(0, 0, 0, 10);

				int sharedEventId = Integer.parseInt((String) SQLResult[i][0]);

				// System.out.println(sharedEventId);

				acceptEventRightPanel.add(new AcceptEventButtonView(sharedEventId, wp, user), gbc);

				gbc.gridx = 1;
				gbc.gridy = 0;
				gbc.insets = new Insets(0, 0, 0, 0);

				acceptEventRightPanel.add(new DeclineEventButtonView(sharedEventId, wp, user), gbc);

			}

		} else {

			noEventPanel = new JPanel();
			noEventPanel.setPreferredSize(new Dimension(1075, 625));
			noEventPanel.setLayout(new GridBagLayout());

			noEventLabel = new JLabel();
			noEventLabel.setText("Du har inga nya event inbjudningar");
			noEventLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));

			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.insets = new Insets(0, 0, 10, 0);

			noEventPanel.add(noEventLabel);

			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.insets = new Insets(0, 0, 10, 0);

			mainPanel.add(noEventPanel, gbc);

		}

		ListenForButton lForButton = new ListenForButton();

		updateButton.addActionListener(lForButton);

	}

	private class ListenForButton implements ActionListener {

		// This method is called when an event occurs

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == updateButton) {

				wp.getInviteView();
				wp.getSharedEventPage();
			}

		}
	}

}
