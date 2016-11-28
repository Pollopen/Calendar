package views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

public class AddEvent extends JPanel {
	private JPanel main, top, center, centerLeft, centerRight;
	private JLabel nameLabel, locationLabel, startTimeLabel, endTimeLabel, descriptionLabel;
	private JTextField nameField, locationField;
	private JSpinner.DateEditor startDateSpinner, endDateSpinner;
	private JSpinner startTimeSpinner, endTimeSpinner;
	private JTextArea descriptionArea;
	private GridBagConstraints gbc;
	private Index index;

	public AddEvent(Index index) {
		this.index = index;
		gbc = new GridBagConstraints();

		// Panel management

		main = new JPanel();
		main.setPreferredSize(new Dimension(1175, 725));
		main.setLayout(new GridBagLayout());
		// main.setBackground(new Color(0, 255, 255));
		main.setVisible(true);

		this.add(main);

		top = new JPanel();
		top.setPreferredSize(new Dimension(1175, 20));
		top.setLayout(new GridBagLayout());
		// top.setBackground(new Color(255, 0, 0));
		top.setVisible(true);

		gbc.gridx = 0;
		gbc.gridy = 0;

		main.add(top, gbc);

		center = new JPanel();
		center.setPreferredSize(new Dimension(1175, 705));
		center.setLayout(new GridBagLayout());
		// center.setBackground(new Color(0, 255, 0));
		center.setVisible(true);

		gbc.gridx = 0;
		gbc.gridy = 1;

		main.add(center, gbc);

		centerLeft = new JPanel();
		centerLeft.setPreferredSize(new Dimension(700, 705));
		centerLeft.setLayout(new GridBagLayout());
		// centerLeft.setBackground(new Color(0, 255, 0));
		centerLeft.setVisible(true);

		gbc.gridx = 0;
		gbc.gridy = 0;

		center.add(centerLeft, gbc);

		centerRight = new JPanel();
		centerRight.setPreferredSize(new Dimension(475, 705));
		centerRight.setLayout(new GridBagLayout());
		// centerRight.setBackground(new Color(0, 0, 255));
		centerRight.setVisible(true);

		gbc.gridx = 1;
		gbc.gridy = 0;

		center.add(centerRight, gbc);

		// Everything on panels

		// Event label + event field
		nameLabel = new JLabel("Event namn");
		nameLabel.setFont(new Font("Serif", Font.PLAIN, 20));

		gbc.gridx = 0;
		gbc.gridy = 0;

		centerLeft.add(nameLabel, gbc);

		nameField = new JTextField();
		nameField.setPreferredSize(new Dimension(300, 30));

		gbc.gridx = 0;
		gbc.gridy = 1;

		centerLeft.add(nameField, gbc);

		// Event location label + location field

		locationLabel = new JLabel("Plats");
		locationLabel.setFont(new Font("Serif", Font.PLAIN, 20));

		gbc.gridx = 0;
		gbc.gridy = 2;

		centerLeft.add(locationLabel, gbc);

		locationField = new JTextField();
		locationField.setPreferredSize(new Dimension(300, 30));

		gbc.gridx = 0;
		gbc.gridy = 3;

		centerLeft.add(locationField, gbc);

		// Event start time + end time

		startTimeLabel = new JLabel("Start på event");
		startTimeLabel.setFont(new Font("Serif", Font.PLAIN, 20));

		gbc.gridx = 0;
		gbc.gridy = 4;

		centerLeft.add(startTimeLabel, gbc);

		endTimeLabel = new JLabel("Slut på event");
		endTimeLabel.setFont(new Font("Serif", Font.PLAIN, 20));

		gbc.gridx = 0;
		gbc.gridy = 5;

		centerLeft.add(endTimeLabel, gbc);
			
		startTimeSpinner = new JSpinner(new SpinnerDateModel());
		startDateSpinner = new JSpinner.DateEditor(startTimeSpinner, "HH:mm:ss");
		startDateSpinner.setPreferredSize(new Dimension(300,30));
		
		gbc.gridx = 0;
		gbc.gridy = 6;
		
		centerLeft.add(startDateSpinner, gbc);

	}

}
