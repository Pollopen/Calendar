package views;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import database.JavaDB;

public class aaa extends JFrame {
	
	
	
	public aaa()
	{
		super("Databas");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(50,50);
		setPreferredSize(new Dimension(620,400));
		setLayout(new GridLayout(1,1));
		JavaDB db = new JavaDB("localhost","root","","calendar");
		Object[][]data = db.getData("select *from user");
		Object[]fields = db.getColums();
		JTable table = new JTable(data,fields);
		JScrollPane scroll = new JScrollPane(table);
		add(scroll);
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		new aaa();

	}

}
