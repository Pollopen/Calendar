package views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import database.SQLManager;

public class NotificationView extends JPanel {

	private GridBagConstraints gbc;
	private JPanel main;
	private JLabel eventStartTime, eventStartLabel, eventEndTime, eventEndLabel, eventNameLabel, generalEventName,
			nextEventLabel;
	private Object[][] result;
	private String eventStartString, eventEndString, eventNameString;

	public NotificationView() {

		this.setPreferredSize(new Dimension(200, 150));
		this.setLayout(new GridLayout(1, 1));
		this.setVisible(true);

		result = SQLManager.getClosestEvent();

		gbc = new GridBagConstraints();

		main = new JPanel();
		main.setPreferredSize(new Dimension(200, 150));
		main.setLayout(new GridBagLayout());
		main.setVisible(true);

		this.add(main);

		if (result.length == 0) {
			nextEventLabel = new JLabel();
			nextEventLabel.setText("Du har inga kommande event!");
			nextEventLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));

			main.add(nextEventLabel);
		} else {
			eventNameString = (String) result[0][1];
			eventStartString = (String) result[0][2];
			eventEndString = (String) result[0][3];

			nextEventLabel = new JLabel();
			nextEventLabel.setText("Nästa event!");
			nextEventLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));

			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.insets = new Insets(0, 0, 10, 0);
			// gbc.anchor = GridBagConstraints.WEST;

			main.add(nextEventLabel, gbc);

			generalEventName = new JLabel();
			generalEventName.setText("Event namn: ");

			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.insets = new Insets(0, 0, 10, 10);
			// gbc.anchor = GridBagConstraints.WEST;

			main.add(generalEventName, gbc);

			eventNameLabel = new JLabel();
			eventNameLabel.setText(eventNameString);

			gbc.gridx = 1;
			gbc.gridy = 1;
			gbc.insets = new Insets(0, 0, 10, 10);
			// gbc.anchor = GridBagConstraints.WEST;

			main.add(eventNameLabel, gbc);

			eventStartLabel = new JLabel();
			eventStartLabel.setText("Start datum:");

			gbc.gridx = 0;
			gbc.gridy = 2;
			gbc.insets = new Insets(0, 0, 10, 10);
			// gbc.anchor = GridBagConstraints.WEST;

			main.add(eventStartLabel, gbc);

			eventStartTime = new JLabel();
			eventStartTime.setText(eventStartString.substring(0, 16));

			gbc.gridx = 1;
			gbc.gridy = 2;
			gbc.insets = new Insets(0, 0, 10, 10);
			// gbc.anchor = GridBagConstraints.WEST;

			main.add(eventStartTime, gbc);

			eventEndLabel = new JLabel();
			eventEndLabel.setText("Slut datum:");

			gbc.gridx = 0;
			gbc.gridy = 3;
			gbc.insets = new Insets(0, 0, 10, 10);
			// gbc.anchor = GridBagConstraints.WEST;

			main.add(eventEndLabel, gbc);

			eventEndTime = new JLabel();
			eventEndTime.setText(eventEndString.substring(0, 16));

			gbc.gridx = 1;
			gbc.gridy = 3;
			gbc.insets = new Insets(0, 0, 0, 10);
			// gbc.anchor = GridBagConstraints.WEST;

			main.add(eventEndTime, gbc);
		}

	}

}
