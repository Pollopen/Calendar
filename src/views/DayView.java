package views;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DayView extends JPanel {
	
	public DayView() {
		
		this.setLayout(new GridLayout(1, 1));
		this.setPreferredSize(new Dimension(1175, 725));
		this.setVisible(true);
		
	}

}
