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


	}

}
