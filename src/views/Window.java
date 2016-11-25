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
		setPreferredSize(new Dimension(1400, 800));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
		Point newLocation = new Point(middle.x - (1400 / 2), 
		                              middle.y - (800 / 2));
		setLocation(newLocation);
		setLayout(new GridLayout(1, 3));
		setResizable(false);

		center = new JPanel();
		center.setLayout(new GridLayout(1, 1));
		center.setPreferredSize(new Dimension(1400, 800));
		center.setBackground(new Color(255, 0, 0));
		center.add(new Login(this));
		center.setVisible(true);

		add(center);

		pack();
		setVisible(true);
	}
	
	public void getIndexPage() {
		center.removeAll();
		center.add(new Index(this));
		center.updateUI();
	}

	public void getRegisterPage() {
		center.removeAll();
		center.add(new Register(this));
		center.updateUI();
	}

	public void getLoginPage() {
		this.setJMenuBar(null);
		center.removeAll();
		center.add(new Login(this));
		center.updateUI();
	}
}
