package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame {

	private JPanel center;

	public Window() {
		super("En ruta");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(1420, 870));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
		Point newLocation = new Point(middle.x - (1420 / 2), 
		                              middle.y - (870 / 2));
		setLocation(newLocation);
		setLayout(new GridLayout(1, 3));
		setResizable(false);
		this.add(new WindowPanel(this));

		pack();
		setVisible(true);
	}

}
