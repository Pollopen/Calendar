package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddEvent extends JPanel {
	private JPanel main, top, center, centerLeft, centerRight;
	private JLabel nameLabel, locationLabel, startTimeLabel, endTimeLabel, descriptionLabel;
	private JTextField nameField, locationField;
	private JTextArea descriptionArea;
	private GridBagConstraints gbc;
	private Index index;

	public AddEvent(Index index) {
		this.index = index;
		gbc = new GridBagConstraints();

		main = new JPanel();
		main.setPreferredSize(new Dimension(1175, 725));
		main.setLayout(new GridBagLayout());
		// main.setBackground(new Color(0, 255, 255));
		main.setVisible(true);

		this.add(main);

		top = new JPanel();
		top.setPreferredSize(new Dimension(1175, 20));
		top.setLayout(new GridBagLayout());
		top.setBackground(new Color(255, 0, 0));
		top.setVisible(true);

		gbc.gridx = 0;
		gbc.gridy = 0;

		main.add(top, gbc);

		center = new JPanel();
		center.setPreferredSize(new Dimension(1175, 705));
		center.setLayout(new GridBagLayout());
		center.setBackground(new Color(0, 255, 0));
		center.setVisible(true);

		gbc.gridx = 0;
		gbc.gridy = 1;

		main.add(center, gbc);

		centerLeft = new JPanel();
		centerLeft.setPreferredSize(new Dimension(700, 705));
		centerLeft.setLayout(new GridBagLayout());
		centerLeft.setBackground(new Color(0, 255, 0));
		centerLeft.setVisible(true);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		center.add(centerLeft, gbc);

		centerRight = new JPanel();
		centerRight.setPreferredSize(new Dimension(475, 705));
		centerRight.setLayout(new GridBagLayout());
		centerRight.setBackground(new Color(0, 0, 255));
		centerRight.setVisible(true);

		gbc.gridx = 1;
		gbc.gridy = 0;

		center.add(centerRight, gbc);

	}

}
