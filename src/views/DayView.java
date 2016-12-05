package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DayView extends JPanel {
	
	private JPanel gridPanel;
	private JLabel dateLabel;
	private Date today;
	private SimpleDateFormat sdf;
	
	
	public DayView() {
		
		this.setLayout(new GridLayout(2, 1));
		this.setPreferredSize(new Dimension(1175, 725));
		this.setVisible(true);
		
		today = new Date();
		sdf = new SimpleDateFormat("MM-dd-yyy");
		
		dateLabel = new JLabel(sdf.format(today).toString());
		dateLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		
		add(dateLabel);
		
		gridPanel = new JPanel();
		gridPanel.setPreferredSize(new Dimension(1175, 675));
		gridPanel.setBackground(new Color(255, 0, 0));
		
		add(gridPanel);

		
	}

}
