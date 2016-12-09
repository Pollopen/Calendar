package views;

import java.awt.Color;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import object.Event;

public class DayPanel extends JPanel {

	LinkedList<Event> lista;

	public DayPanel() {
		setBorder(BorderFactory.createLineBorder(new Color(0, 255, 0)));
		lista = new LinkedList<Event>();
		setLayout(null);

	}

	public void addEvent(Event e) {
		lista.add(e);

		/* TAs bort */
		JLabel temp = new JLabel("event something");
		temp.setBounds(1, 250, 100, 100);
		temp.setBackground(new Color(255, 0, 0));
		temp.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
		add(temp);
	}
	
	public void setupEvent()
	{
		for(int i = 0; i < lista.size();i++)
		{
			JLabel test = new JLabel("hheeeej");
			test.setBounds(0, 165, 200, 500);
		}
	}

	// public void paint(Graphics page)
	// {
	// page.setColor(new Color(0,90,255));
	// page.fillRect(0, 100,100, 250);
	// }

}
