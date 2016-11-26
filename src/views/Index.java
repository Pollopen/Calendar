package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class Index extends JPanel {

	private JPanel mainPanel, leftPanel, leftPanel1, leftPanel2, leftPanel3, leftPanel4, rightPanel, rightPanel1, rightPanel2, upperLeftPanel, upperRightPanel;
	private GridBagConstraints gbc;

	private Window window;

	public Index(Window window) {
		this.window = window;

		MenuList menu = new MenuList();
        window.setJMenuBar(menu.createMenuBar(window));
		
		// Main panel
		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(1400, 800));
		mainPanel.setLayout(new GridBagLayout());
		mainPanel.setVisible(true);
		mainPanel.setBackground(new Color(0, 0, 255));
		this.add(mainPanel);

		gbc = new GridBagConstraints();

		// Upper left panel
		upperLeftPanel = new JPanel();
		upperLeftPanel.setPreferredSize(new Dimension(200, 100));
		upperLeftPanel.setLayout(new GridLayout(2, 2));
		upperLeftPanel.setVisible(true);
		upperLeftPanel.setBackground(new Color(255, 0, 0));

		gbc.gridx = 0;
		gbc.gridy = 0;

		mainPanel.add(upperLeftPanel, gbc);

		// Upper right panel
		upperRightPanel = new JPanel();
		upperRightPanel.setPreferredSize(new Dimension(1200, 100));
		upperRightPanel.setLayout(new GridLayout(2, 2));
		upperRightPanel.setVisible(true);
		upperRightPanel.setBackground(new Color(0, 255, 0));

		gbc.gridx = 1;
		gbc.gridy = 0;

		mainPanel.add(upperRightPanel, gbc);

		// Left panel
		leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(200, 700));
		leftPanel.setLayout(new GridBagLayout());
		leftPanel.setVisible(true);
		leftPanel.setBackground(new Color(0, 0, 255));

		gbc.gridx = 0;
		gbc.gridy = 1;

		mainPanel.add(leftPanel, gbc);
		
		leftPanel1 = new JPanel();
		leftPanel1.setPreferredSize(new Dimension(200, 100));
		leftPanel1.setVisible(true);
		leftPanel1.setBackground(new Color(0, 0, 255));
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		leftPanel.add(leftPanel1, gbc);
		
		leftPanel2 = new JPanel();
		leftPanel2.setPreferredSize(new Dimension(200, 200));
		leftPanel2.setVisible(true);
		leftPanel2.setBackground(new Color(100, 100, 100));
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		
		leftPanel.add(leftPanel2, gbc);
		
		leftPanel3 = new JPanel();
		leftPanel3.setPreferredSize(new Dimension(200, 200));
		leftPanel3.setVisible(true);
		leftPanel3.setBackground(new Color(255, 0, 255));
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		
		leftPanel.add(leftPanel3, gbc);
		
		leftPanel4 = new JPanel();
		leftPanel4.setPreferredSize(new Dimension(200, 200));
		leftPanel4.setVisible(true);
		leftPanel4.setBackground(new Color(255, 255, 0));
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		
		leftPanel.add(leftPanel4, gbc);

		// Right panel
		rightPanel = new JPanel();
		rightPanel.setPreferredSize(new Dimension(1200, 700));
		rightPanel.setLayout(new GridBagLayout());
		rightPanel.setVisible(true);
		rightPanel.setBackground(new Color(150, 150, 150));

		gbc.gridx = 1;
		gbc.gridy = 1;

		mainPanel.add(rightPanel, gbc);
		
		rightPanel1 = new JPanel();
		rightPanel1.setPreferredSize(new Dimension(1175, 675));
		rightPanel1.setVisible(true);
		rightPanel1.setBackground(new Color(255, 0, 0));

		gbc.gridx = 0;
		gbc.gridy = 0;

		rightPanel.add(rightPanel1, gbc);
		

	}

}