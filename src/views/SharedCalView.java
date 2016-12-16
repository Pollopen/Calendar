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

public class SharedCalView extends JPanel {

	private Object[][] SQLResult;
	private JPanel mainPanel, acceptCalPanel, noCalPanel, buttonPanel, acceptCalLeftPanel, acceptCalRightPanel;
	private JButton updateButton;
	private JLabel calNameLabel, noCalLabel, calCreatorLabel;
	private JScrollPane scroll;
	private GridBagConstraints gbc;
	private WindowPanel wp;
	private User user;

	public SharedCalView(WindowPanel wp, User user) {

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
		// mainPanel.setBackground(new Color(255, 0, 0));

		scroll = new JScrollPane(mainPanel);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.revalidate();
		mainPanel.revalidate();

		this.add(BorderLayout.CENTER, scroll);

		SQLResult = SQLManager.getSharedCal();

		if (SQLResult.length > 0) {
			for (int i = 0; i < SQLResult.length; i++) {
				acceptCalPanel = new JPanel();
				acceptCalPanel.setPreferredSize(new Dimension(1075, 100));
				acceptCalPanel.setLayout(new GridBagLayout());
				acceptCalPanel.setBackground(new Color(100, 100, 100));
				acceptCalPanel.revalidate();
				// acceptEventPanel.setBorder(BorderFactory.createLineBorder(new
				// Color(0, 0, 0)));

				gbc.gridx = 0;
				gbc.gridy = i;
				gbc.insets = new Insets(0, 0, 10, 0);

				mainPanel.add(acceptCalPanel, gbc);

				acceptCalLeftPanel = new JPanel();
				acceptCalLeftPanel.setPreferredSize(new Dimension(800, 100));
				acceptCalLeftPanel.setLayout(new GridBagLayout());
				acceptCalLeftPanel.setBackground(new Color(200, 200, 200));
				// acceptEventLeftPanel.setBackground(new Color(0, 0, 255));

				gbc.gridx = 0;
				gbc.gridy = 0;
				gbc.insets = new Insets(0, 0, 0, 0);

				acceptCalPanel.add(acceptCalLeftPanel, gbc);

				acceptCalRightPanel = new JPanel();
				acceptCalRightPanel.setPreferredSize(new Dimension(275, 100));
				acceptCalRightPanel.setLayout(new GridBagLayout());
				acceptCalRightPanel.setBackground(new Color(200, 200, 200));
				// acceptEventRightPanel.setBackground(new Color(0, 255, 0));

				gbc.gridx = 1;
				gbc.gridy = 0;
				gbc.insets = new Insets(0, 0, 0, 0);

				acceptCalPanel.add(acceptCalRightPanel, gbc);

				calNameLabel = new JLabel();
				calNameLabel.setText(SQLResult[i][5].toString());
				calNameLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));

				gbc.gridx = 0;
				gbc.gridy = 0;
				gbc.insets = new Insets(0, 0, 0, 0);

				acceptCalLeftPanel.add(calNameLabel, gbc);
				
				calCreatorLabel = new JLabel();
				calCreatorLabel.setText("Kalender ägare: " + SQLResult[i][6].toString());

				gbc.gridx = 0;
				gbc.gridy = 1;
				gbc.insets = new Insets(0, 0, 0, 0);

				acceptCalLeftPanel.add(calCreatorLabel, gbc);

				gbc.gridx = 0;
				gbc.gridy = 0;
				gbc.insets = new Insets(0, 0, 0, 10);

				int sharedEventId = Integer.parseInt((String) SQLResult[i][0]);

				// System.out.println(sharedEventId);

				acceptCalRightPanel.add(new AcceptCalButtonView(sharedEventId, wp, user), gbc);

				gbc.gridx = 1;
				gbc.gridy = 0;
				gbc.insets = new Insets(0, 0, 0, 0);

				acceptCalRightPanel.add(new DeclineCalButtonView(sharedEventId, wp, user), gbc);

			}

		} else {

			noCalPanel = new JPanel();
			noCalPanel.setPreferredSize(new Dimension(1075, 625));
			noCalPanel.setLayout(new GridBagLayout());

			noCalLabel = new JLabel();
			noCalLabel.setText("Du har inga nya kalender inbjudningar");
			noCalLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));

			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.insets = new Insets(0, 0, 10, 0);

			noCalPanel.add(noCalLabel);

			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.insets = new Insets(0, 0, 10, 0);

			mainPanel.add(noCalPanel, gbc);

		}

		ListenForButton lForButton = new ListenForButton();

		updateButton.addActionListener(lForButton);

	}

	private class ListenForButton implements ActionListener {

		// This method is called when an event occurs

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == updateButton) {
				wp.getInviteView();
				wp.getSharedCalPage();
			}

		}
	}

}
