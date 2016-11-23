package views;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Window extends JFrame{
	public Window()
	{
		super("En ruta");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(50,50);
		setPreferredSize(new Dimension(620,400));
		//ssetLayout(new GridLayout(3,1));
		add(new Register());
		
		pack();
		setVisible(true);
	
		
		
		
	}
}
