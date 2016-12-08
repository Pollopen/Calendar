package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import CalViewButton.DayButton;
import CalViewButton.EventButton;
import controller.StateMachine;
import object.Event;
import object.User;

public class MonthView extends JPanel{
	private JPanel tempJP, normalEventsPanel;
	private StateMachine SM;
	private String date, monthNum, firstDay;
	private String[] weekDays={"Måndag", "Tisdag", "onsdag", "Torsdag", "Fredag", "Lördag", "Söndag"};
	private Date focusedDate;
	private DateFormat getFocusDate = new SimpleDateFormat("yyyy/MM/dd");
	private DateFormat getWeekDay = new SimpleDateFormat("u");
	int tempNormalEvents;
	int tempFullDayEvents;
	private int weekDay, j, dayOfMonth;
	private User user;
	private Event[] filteredEventArray;
	private GridBagConstraints gbc;
	private Border etchedBorder;
	
	public MonthView(StateMachine SM, User user){
		etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		this.user=user;
		gbc= new GridBagConstraints();
		this.SM=SM;
		setPreferredSize(new Dimension(1175, 725));
		setLayout(new GridLayout(7,7));
		//setBackground(new Color(0, 255, 255));
		setVisible(true);
		date=SM.getFocusedDate();
		firstDay=date.substring(0, 8)+"01";
		dayOfMonth=getDaysOfMonth(firstDay);
		try {
			focusedDate=getFocusDate.parse(firstDay);
		} catch (ParseException e) {
			System.out.println("Date Conversion failed!");
		}
		weekDay = Integer.parseInt(getWeekDay.format(focusedDate));

		filteredEventArray=filterEvents();
		for (int i = 0; i < weekDays.length; i++) {
			tempJP=new JPanel();
			tempJP.setLayout(new GridBagLayout());
			tempJP.setBackground(new Color(100,100,100));
			tempJP.setVisible(true);
			add(tempJP);
			JLabel day=new JLabel(weekDays[i]);
			day.setFont(new Font("Serif", Font.PLAIN, 25));
			tempJP.add(day,gbc);
		}
		if(weekDay!=1){
			for (int i=0;i<(weekDay-1);i++){
				JLabel empty = new JLabel("");
				add(empty);	
			} 
		}
		j=1;
		while (true) {
			normalEventsPanel=null;
			tempFullDayEvents=0;
			tempNormalEvents=0;
			tempJP=new JPanel();
			tempJP.setLayout(new BorderLayout());
			tempJP.setBackground(new Color(200,200,200));
			tempJP.setVisible(true);
			tempJP.setBorder(etchedBorder);
			add(tempJP);
			//JLabel dayNumber = new JLabel(Integer.toString(j));
			//tempJP.add(dayNumber, BorderLayout.PAGE_START);
			String checkDate=getCheckDate(firstDay, Integer.toString(j));
			tempJP.add(new DayButton(Integer.toString(j),checkDate,1), BorderLayout.PAGE_START);
			for (int i = 0; i < filteredEventArray.length; i++) {
				String checkEventDayStart=(filteredEventArray[i].getStart_time()).substring(0,4)+(filteredEventArray[i].getStart_time()).substring(5,7)+(filteredEventArray[i].getStart_time()).substring(8,10);
				String checkEventDayEnd=(filteredEventArray[i].getEnd_time()).substring(0,4)+(filteredEventArray[i].getStart_time()).substring(5,7)+(filteredEventArray[i].getEnd_time()).substring(8,10);
				if(checkDate.equals(checkEventDayStart)||checkDate.equals(checkEventDayEnd)||checkIfInProgress(checkDate, checkEventDayStart, checkEventDayEnd)){
					if(filteredEventArray[i].getFullDay()==1){
						tempFullDayEvents++;
					}else{
						tempNormalEvents++;
					}
				}
			}
			
			if(tempFullDayEvents==0){
				JLabel empty = new JLabel("");
				tempJP.add(empty, BorderLayout.PAGE_END);	
			}
			gbc.gridx=0;
			gbc.gridy=0;
			for (int i = 0; i < filteredEventArray.length; i++) {
				String checkEventDayStart=(filteredEventArray[i].getStart_time()).substring(0,4)+(filteredEventArray[i].getStart_time()).substring(5,7)+(filteredEventArray[i].getStart_time()).substring(8,10);
				String checkEventDayEnd=(filteredEventArray[i].getEnd_time()).substring(0,4)+(filteredEventArray[i].getEnd_time()).substring(5,7)+(filteredEventArray[i].getEnd_time()).substring(8,10);
				System.out.println(filteredEventArray[i].getName()+" start: "+checkEventDayStart+" end: "+checkEventDayEnd+ "comparing with"+checkDate);
				if(checkDate.equals(checkEventDayStart)||checkDate.equals(checkEventDayEnd)||checkIfInProgress(checkDate, checkEventDayStart, checkEventDayEnd)){
							if(filteredEventArray[i].getFullDay()==1){
								if(tempFullDayEvents==1){
									tempJP.add(new EventButton(filteredEventArray[i].getName(),filteredEventArray[i]), BorderLayout.PAGE_END);
								}else if(tempFullDayEvents>=2){
									//tempJP.add(new JButton("..."), BorderLayout.PAGE_END);
									tempJP.add(new DayButton("...", checkDate, 3), BorderLayout.PAGE_END);
									tempFullDayEvents=-1;
								}
							}else{
								if(normalEventsPanel==null){
									normalEventsPanel = new JPanel();
									normalEventsPanel.setPreferredSize(new Dimension(150, 50));
									normalEventsPanel.setLayout(new GridBagLayout());
									normalEventsPanel.setOpaque(false);
									tempJP.add(normalEventsPanel, BorderLayout.CENTER);
								}
								if(gbc.gridx>=2){
									normalEventsPanel.add(new DayButton("...", checkDate, 2), gbc);
								}else{
									normalEventsPanel.add(new EventButton(filteredEventArray[i].getName().substring(0, 1),filteredEventArray[i]), gbc);
									gbc.gridx++;
								}
							}
						
					
				}
			}
			
			j++;
			if(j>dayOfMonth){
				break;
			}
		}
		for (int i = 0; i < (12-weekDay); i++) {
			JLabel empty = new JLabel("");
			add(empty);
		}
		updateUI();
		
	}
	private boolean checkLongEvent(Event event) {
		// TODO Auto-generated method stub
		return true;
	}
	private boolean checkIfInProgress(String checkDate, String checkEventDayStart, String checkEventDayEnd) {
		if(Integer.parseInt(checkDate)>Integer.parseInt(checkEventDayStart)&&Integer.parseInt(checkDate)<Integer.parseInt(checkEventDayEnd)){
			return true;
		}else{
			return false;
		}
		
	}
	private int getDaysOfMonth(String firstDay){
		monthNum=firstDay.substring(5, 7);
		switch (Integer.parseInt(monthNum)) {
			case 1:
				return 31;
			case 2:
				if(isLeapYear(firstDay)){
					return 29;
				}else{
					return 28;
				}
			case 3:
				return 31;
			case 4:
				return 30;
			case 5:
				return 31;
			case 6:
				return 30;
			case 7:
				return 31;
			case 8:
				return 31;
			case 9:
				return 30;
			case 10:
				return 31;
			case 11:
				return 30;
			case 12:
				return 31;
			default:
				return 31;
		}
	}
	public static boolean isLeapYear(String firstDay) {
		String year=firstDay.substring(0, 4);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(year));
		return cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365;
	}
	//format date for later comparison
	public String getCheckDate(String firstday, String j){
		String checkdate;
		if(Integer.parseInt(j)<=9){
			checkdate=firstday.substring(0, 4)+firstday.substring(5, 7)+"0"+j;
		}else{
			checkdate=firstday.substring(0, 4)+firstday.substring(5, 7)+j;
		}
		return checkdate;
	}
	//Checks and filter out the events that belong to this month
	public Event[] filterEvents(){
		Event [] filteredList = null;
		Event[] ea=user.getEventArray();
		object.Calendar[] ca=user.getCalArray();
		
		int matchNumber=0;
		String monthChecker=firstDay.substring(0, 4)+firstDay.substring(5, 7);
		
		for (int i = 0; i < ea.length; i++) {
			String eventStartChecker=(ea[i].getStart_time()).substring(0, 4)+(ea[i].getStart_time()).substring(5, 7);
			String eventEndChecker=(ea[i].getEnd_time()).substring(0, 4)+(ea[i].getEnd_time()).substring(5, 7);
			if(monthChecker.equals(eventStartChecker)||monthChecker.equals(eventEndChecker)){//is this event in this month?
				int[] aca=SM.getActiveCalendars();
				for (int j = 0; j < aca.length; j++) {//If calendar is active
					if(ca[aca[j]].getCal_id()==ea[i].getCal_id()){
						matchNumber++;
					}
				}
			}
		}
		filteredList=new Event[matchNumber];
		int k=0;
		for (int i = 0; i < ea.length; i++) {
			String eventStartChecker=(ea[i].getStart_time()).substring(0, 4)+(ea[i].getStart_time()).substring(5, 7);
			String eventEndChecker=(ea[i].getEnd_time()).substring(0, 4)+(ea[i].getEnd_time()).substring(5, 7);
			if(monthChecker.equals(eventStartChecker)||monthChecker.equals(eventEndChecker)){//is this event in this month?
				int[] aca=SM.getActiveCalendars();
				for (int j = 0; j < aca.length; j++) {//If calendar is active
					if(ca[aca[j]].getCal_id()==ea[i].getCal_id()){
						Event tempEvent=ea[i];
						filteredList[k]=tempEvent;
						k++;
						System.out.println(tempEvent.getName());
					}
				}
			}
		}
		return filteredList;
	}
	
	
}

