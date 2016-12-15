package views.calendar;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.StateMachine;
import object.Calendar;
import object.User;
import views.WindowPanel;

public class CalChooseList extends JPanel implements ListSelectionListener {

	private JList calChoiceList;
	private JLabel calChoiceTitle,calChoiceInfo, calChoiceTip;
	private DefaultListModel listModel;
	private StateMachine SM;
	private WindowPanel wp;
	private int[] list;
	private GridBagConstraints gbc;
	private User user;
	public CalChooseList(User user, StateMachine SM, WindowPanel wp){
		this.user=user;
		this.SM=SM;
		this.wp=wp;
		Calendar[] calarray= user.getCalArray();
		gbc = new GridBagConstraints();
		setLayout(new GridBagLayout());
		calChoiceTitle=new JLabel("Synliga kalendrar:");
		calChoiceTitle.setFont(new Font("Serif", Font.PLAIN, 25));
		calChoiceTip=new JLabel("Tips:");
		calChoiceTip.setFont(new Font("Serif", Font.PLAIN, 20));
		calChoiceInfo=new JLabel("Håll CTRL för flerval");
		calChoiceInfo.setFont(new Font("Serif", Font.PLAIN, 20));
		list=SM.getActiveCalendars();
		if(list==null || list.length>calarray.length){
			list = new int[calarray.length];
			for(int j = 0; j < calarray.length; j++) {
				list[j]=j;
			}	
		}
		listModel = new DefaultListModel();

		for (int i = 0; i < calarray.length; i++) {
			listModel.addElement(calarray[i].getName());
		}
        //Create the list and put it in a scroll pane.
        calChoiceList = new JList(listModel);
        calChoiceList.setCellRenderer(new ColorRenderer());
        calChoiceList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        calChoiceList.setSelectedIndices(list);
		SM.setActiveCalendars(list);
        calChoiceList.addListSelectionListener(this);
        calChoiceList.setVisibleRowCount(10);
        JScrollPane listScrollPane = new JScrollPane(calChoiceList);
        setOpaque(false);
        
        gbc.gridx = 0;
		gbc.gridy = 0;
        add(calChoiceTitle,gbc);
        gbc.gridx = 0;
		gbc.gridy = 1;
		add(listScrollPane,gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(calChoiceTip,gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(calChoiceInfo,gbc);
	}
	private class ColorRenderer extends DefaultListCellRenderer {
        public Component getListCellRendererComponent( JList list, Object value, int index, boolean isSelected, boolean cellHasFocus ) {
            Component c = super.getListCellRendererComponent( list, value, index, isSelected, cellHasFocus );  
            if(isSelected){
            	c.setBackground(SM.getColor(index));
            }
            return c;
        }
    }
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		int[] indecies = calChoiceList.getSelectedIndices();
		SM.setActiveCalendars(indecies);
		wp.getViewViewer();
		wp.getOverview();
	}

}
