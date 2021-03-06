package views.calendar.addedit;

import javax.swing.DefaultListModel;
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


public class CalEditList extends JPanel implements ListSelectionListener {

	private JList calEditList;
	private DefaultListModel listModel;
	private StateMachine SM;
	private WindowPanel wp;
	private CalAddEdit cae;
	public CalEditList(Calendar[] calarray, StateMachine SM, WindowPanel wp, CalAddEdit cae, User user) {
		this.SM = SM;
		this.wp = wp;
		this.cae=cae;
		listModel = new DefaultListModel();

		for (int i = 0; i < calarray.length; i++) {
			if(calarray[i].getCreator_id()==user.getId()){
				listModel.addElement(calarray[i].getName());
			}
			
		}
		// Create the list and put it in a scroll pane.
		calEditList = new JList(listModel);
		calEditList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		calEditList.setSelectedIndex(this.SM.getCalEditStatus());
		calEditList.addListSelectionListener(this);
		calEditList.setVisibleRowCount(10);
		JScrollPane listScrollPane = new JScrollPane(calEditList);
		setOpaque(false);
		add(listScrollPane);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		int index = calEditList.getSelectedIndex();
		SM.setCalEditStatus(index);
		// wp.calendarEdit(index);
		cae.calList();												
		cae.calendarEdit(SM.getCalEditStatus());
	}

}
