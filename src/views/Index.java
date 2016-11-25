package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class Index extends JPanel {

	private JPanel mainPanel, leftPanel, rightPanel, upperLeftPanel, upperRightPanel;
	private Window window;

	public Index(Window window) {
		this.window = window;

		// Main panel
		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(1400, 800));
		mainPanel.setLayout(new GridLayout(2, 2));
		mainPanel.setVisible(true);
		mainPanel.setBackground(new Color(255, 0, 255));

		// Upper left panel
		upperLeftPanel = new JPanel();
		upperLeftPanel.setPreferredSize(new Dimension(200, 100));
		upperLeftPanel.setLayout(new GridLayout(2, 2));
		upperLeftPanel.setVisible(true);
		upperLeftPanel.setBackground(new Color(255, 0, 0));

		// Upper right panel
		upperRightPanel = new JPanel();
		upperRightPanel.setPreferredSize(new Dimension(1200, 100));
		upperRightPanel.setLayout(new GridLayout(2, 2));
		upperRightPanel.setVisible(true);
		upperRightPanel.setBackground(new Color(0, 255, 0));

		// Left panel
		leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(200, 700));
		leftPanel.setLayout(new GridLayout(2, 2));
		leftPanel.setVisible(true);
		leftPanel.setBackground(new Color(0, 0, 255));

		// Left panel
		rightPanel = new JPanel();
		rightPanel.setPreferredSize(new Dimension(1200, 700));
		rightPanel.setLayout(new GridLayout(2, 2));
		rightPanel.setVisible(true);
		rightPanel.setBackground(new Color(100, 100, 100));

		this.add(mainPanel);

		mainPanel.add(upperLeftPanel);
		mainPanel.add(upperRightPanel);
		mainPanel.add(leftPanel);
		mainPanel.add(rightPanel);

	}

}
