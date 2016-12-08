package views;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import object.Event;

public class DayPanel extends JPanel {
	
LinkedList<Event> lista;
	
	
		public DayPanel()
		{
			setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0)));
			lista = new LinkedList<Event>();
			setLayout(null);
		}
		
		
		public void adEvent(Event e)
		{
			lista.add(e);
			
			/*TAs bort  */
			JLabel temp = new JLabel("event something");
			temp.setBounds(1,250,this.getWidth(),100);
			add(temp);
		}
		
		
//		public void paint(Graphics page)
//		{
//			page.setColor(new Color(0,90,255));
//			page.fillRect(0, 100,100, 250);
//		}

}
