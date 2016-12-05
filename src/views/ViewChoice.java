package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.StateMachine;

public class ViewChoice extends JPanel {
	private JButton dayButton,weekButton, monthButton, yearButton;
	private GridBagConstraints gbc;
	private ListenForButton lForButton;
	private StateMachine SM;
	private WindowPanel wp;
	private JSpinner dateChoice;
	private DateFormat getFocusDate = new SimpleDateFormat("yyyy/MM/dd");
	private Date focusedDate;
	
	public ViewChoice(StateMachine SM, WindowPanel wp){
		this.SM=SM;
		this.wp=wp;
		setOpaque(false);
		gbc=new GridBagConstraints();
		setLayout(new GridBagLayout());
		lForButton=new ListenForButton();
		dayButton= new JButton("Dag");
		dayButton.addActionListener(lForButton);
		weekButton= new JButton("Vecka");
		weekButton.addActionListener(lForButton);
		monthButton= new JButton("Månad");
		monthButton.addActionListener(lForButton);
		yearButton= new JButton("År");
		yearButton.addActionListener(lForButton);
		
		switch (SM.getActiveview()) {
		case 1:
			dayButton.setEnabled(false);
			break;
		case 2:
			weekButton.setEnabled(false);
			break;
		case 3:
			monthButton.setEnabled(false);
			break;
		case 4:
			yearButton.setEnabled(false);
			break;
		default:
			break;
		}
		
		gbc.gridx = 0;
		gbc.gridy = 0;
        add(dayButton,gbc);
        gbc.gridx = 1;
		gbc.gridy = 0;
        add(weekButton,gbc);
        gbc.gridx = 2;
		gbc.gridy = 0;
        add(monthButton,gbc);
        gbc.gridx = 3;
		gbc.gridy = 0;
        add(yearButton,gbc);
        
        try {
			focusedDate=getFocusDate.parse(SM.getFocusedDate());
		} catch (ParseException e) {
			System.out.println("Date Conversion failed!");
		}
        
        dateChoice = new JSpinner(new SpinnerDateModel(focusedDate, null, null,Calendar.DAY_OF_MONTH));
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateChoice, "dd/MM/yy");
        dateChoice.setEditor(dateEditor);
        
		gbc.gridx = 4;
		gbc.gridy = 0;
		
		add(dateChoice);
		
		ListenForSpinner lForSpinner = new ListenForSpinner();
		

		dateChoice.addChangeListener(lForSpinner);
		
	}
	
	private class ListenForButton implements ActionListener {

		// This method is called when an event occurs

		public void actionPerformed(ActionEvent e) {

			// Check if the source of the event was the button
			
			if (e.getSource() == dayButton) {
				SM.setActiveview(1);
				wp.getViewViewer();
				wp.getViewChoice();
			}
			if (e.getSource() == weekButton) {
				SM.setActiveview(2);
				wp.getViewViewer();
				wp.getViewChoice();
			}
			if (e.getSource() == monthButton) {
				SM.setActiveview(3);
				wp.getViewViewer();
				wp.getViewChoice();
			}
			if (e.getSource() == yearButton) {
				SM.setActiveview(4);
				wp.getViewViewer();
				wp.getViewChoice();
			}
			
		}
	}
	private class ListenForSpinner implements ChangeListener {

		// This method is called when an event occurs

		public void stateChanged(ChangeEvent e) {
			if (e.getSource() == dateChoice) {
				String temp = getFocusDate.format(dateChoice.getValue());
				SM.setFocusedDate(temp);
				System.out.println(temp);
				wp.getViewViewer();
				wp.getViewChoice();
			}
			// Check if the source of the event was the button
			
		}
	}
}
