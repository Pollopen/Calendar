package views;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Window extends JFrame {
	
	// public static boolean getLoginStatus = false;
	// public static boolean getRegisterStatus = false;
	
	public Window()
	{
		super("En ruta");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(50,50);
		setPreferredSize(new Dimension(620,400));
		setLayout(new GridLayout(1,3));
		
		add(new Login());
		
		/*
		if(getLoginStatus == true)
		{
			add(new Login());
		}
		if(getRegisterStatus == true) {
			add(new Register());
		} 
		*/
		
		
		
		pack();
		setVisible(true);
	}
}
