package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame {

	private JPanel center;
	
	public Window()
	{
		super("En ruta");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(50,50);
		setPreferredSize(new Dimension(620,400));
		setLayout(new GridLayout(1,3));
		
		center = new JPanel();
		center.setLayout(new GridLayout(1,1));
		center.setPreferredSize(new Dimension(620,400));
		center.setBackground(new Color(255,0,0));
		center.add(new Login(this));
		center.setVisible(true);
		
		add(center);
		
		
		pack();
		setVisible(true);
	}
	
	public void getRegisterPage()
	{
		center.removeAll();
		center.add(new Register(this));
		center.updateUI();
	}
	
	public void getLoginPage()
	{
		center.removeAll();
		center.add(new Login(this));
		center.updateUI();
	}
}
