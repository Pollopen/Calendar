package views;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class NotificationView extends JPanel {
	
	private GridBagConstraints gbc;
	private JLabel eventStartTime, eventEndTime, eventName;

	public NotificationView() {
		
		this.setPreferredSize(new Dimension(200, 50));
		this.setLayout(new GridBagLayout());
		this.setVisible(true);
		
		
		
		
	}

}
