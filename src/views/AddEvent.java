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
	private JPanel main, top, left, right;
	private JLabel nameLabel, locationLabel, startTimeLabel, endTimeLabel, descriptionLabel;
	private JTextField nameField, locationField;
	private JTextArea descriptionArea;
	private GridBagConstraints gbc;
	private Index index;
	
	public AddEvent(Index index)
	{
		this.index = index;
		gbc = new GridBagConstraints();
	
		main = new JPanel();
		main.setPreferredSize(new Dimension(1175, 675));
		//main.setLayout(new GridBagLayout());
		main.setBackground(new Color(0,255,255));
		main.setVisible(true);
	
		this.add(main);
		
	}
	
	

}
