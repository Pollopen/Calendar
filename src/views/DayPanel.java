package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.DateHandler;
import object.Event;
import weekView.FullDayEventWeek;
import weekView.NormalEventWeek;

public class DayPanel extends JPanel {
	private Event[] eventArray,fullDay,normal;
	private GridBagConstraints gbc = new GridBagConstraints();
	private String date;
	public DayPanel(Event[] eventArray,String date, int h, boolean inweekview) {
		this.eventArray=eventArray;
		this.date=date;
		System.out.println("date= "+date);
		
		
		//setBorder(BorderFactory.createLineBorder(new Color(0, 255, 0)));
		setLayout(new GridBagLayout());
		if(h==0){
			setBorder(BorderFactory.createMatteBorder(
					1, 2, 2, 1, Color.BLACK));
		}else if(h==6){
			setBorder(BorderFactory.createMatteBorder(
					1, 1, 2, 2, Color.BLACK));
		}else{
			setBorder(BorderFactory.createMatteBorder(
					1, 1, 2, 1, Color.BLACK));
		}
		JPanel fullDayEvents = new JPanel();
		JPanel normalEvents = new JPanel();
		if(inweekview){
			fullDayEvents.setPreferredSize(new Dimension(157, 50));
			//fullDayEvents.setBackground(new Color(255, 0, 0));
		
			normalEvents.setPreferredSize(new Dimension(157, 595));
			//normalEvents.setBackground(new Color(0, 255, 0));
		}else{
			fullDayEvents.setPreferredSize(new Dimension(1115, 50));
			//fullDayEvents.setBackground(new Color(255, 0, 0));
		
			normalEvents.setPreferredSize(new Dimension(1115, 595));
			//normalEvents.setBackground(new Color(0, 255, 0));
		}
		
		gbc.gridx=0;
		gbc.gridy=0;
		add(fullDayEvents,gbc);
		gbc.gridx=0;
		gbc.gridy=1;
		add(normalEvents,gbc);
		int tempFullDayEvents=0;
		int tempNormalEvents=0;
		for (int i = 0; i < eventArray.length; i++) {
			//String checkEventDayStart=eventArray[i].getStart_time();
			//String checkEventDayEnd=eventArray[i].getEnd_time();
			//System.out.println("startTime: "+checkEventDayStart);
			//System.out.println("endTime: "+checkEventDayEnd);
			if(eventArray[i].getFullDay()==1){
				tempFullDayEvents++;
			}else{
				tempNormalEvents++;
			}
		}
		fullDay=new Event[tempFullDayEvents];
		int fd=0;
		normal=new Event[tempNormalEvents];
		int n=0;
		for (int i = 0; i < eventArray.length; i++) {
			if(eventArray[i].getFullDay()==1){
				fullDay[fd]=eventArray[i];
				fd++;
			}else{
				normal[n]=eventArray[i];
				n++;
			}
		}
		System.out.println("FulldayEvents: "+tempFullDayEvents+"normal events: "+tempNormalEvents);

		//String checkEventDayStart=eventArray[i].getStart_time();
		//String checkEventDayEnd=eventArray[i].getEnd_time();
		//System.out.println("startTime: "+checkEventDayStart);
		//System.out.println("endTime: "+checkEventDayEnd);
		fullDayEvents.setLayout(new GridLayout(1, fullDay.length));
		for (int i = 0; i < fullDay.length; i++) {
			fullDayEvents.add(new FullDayEventWeek(fullDay[i].getName(),fullDay[i]));
		}
		normalEvents.setLayout(new GridLayout(1, normal.length));
		//normalEvents.setBackground(new Color(255, 0, 0));
		//int color=35;
		for (int i = 0; i < normal.length; i++) {
			JPanel tempPanel = new JPanel(null);
			//tempPanel.setBackground(new Color(0, color, 0));
			//color+=35;
			normalEvents.add(tempPanel);
			int height=595;
			if(checkIfInProgress(normal[i])){
				NormalEventWeek tempEventButton=new NormalEventWeek(normal[i].getName(),normal[i]);
				if(inweekview){
					tempEventButton.setBounds(0, 0, 157/normal.length , height);
				}else{
					tempEventButton.setBounds(0, 0, 1115/normal.length , height);
				}
				
				tempPanel.add(tempEventButton);
			}else{
				int tempStartPoint = getStart(normal[i]);
				int tempLength = getLength(normal[i]);
				
				NormalEventWeek tempEventButton=new NormalEventWeek(normal[i].getName(),normal[i]);
				if((tempLength+tempStartPoint)>595){
					tempLength=height-tempStartPoint;
				}
				if(inweekview){
					tempEventButton.setBounds(0, tempStartPoint, 157/normal.length , tempLength);
				}else{
					tempEventButton.setBounds(0, tempStartPoint, 1115/normal.length , tempLength);
				}
				
				tempPanel.add(tempEventButton);
			}
		}
	}
	private boolean checkIfInProgress(Event event){
		String tempStart=DateHandler.convertToEasyDate(event.getStart_time());
		String tempEnd=DateHandler.convertToEasyDate(event.getEnd_time());
		if((Integer.parseInt(tempStart)<Integer.parseInt(date))&&(Integer.parseInt(tempEnd)>Integer.parseInt(date))){
			return true;
		}else{
			return false;
			
		}
	}
	private int getLength(Event event){
		String tempStartTime=event.getStart_time();
		String tempEndTime=event.getEnd_time();
		String tempStartDate=DateHandler.convertToEasyDate(tempStartTime);
		String tempEndDate=DateHandler.convertToEasyDate(tempEndTime);
		//if end AND start in this day
		if((Integer.parseInt(date)==Integer.parseInt(tempStartDate))&&(Integer.parseInt(date)==Integer.parseInt(tempEndDate))){
			int hour=Integer.parseInt(tempEndTime.substring(11, 13))-Integer.parseInt(tempStartTime.substring(11, 13));
			int halfHour=Integer.parseInt(tempEndTime.substring(14, 16))-Integer.parseInt(tempStartTime.substring(14, 16));
			int startTime=0;
			//hour
			startTime+=(24.7*hour);
			//half hour
			startTime+=(0.41*halfHour);
			System.out.println(startTime);
			return startTime;
		}
		//if events starts before this day
		if(Integer.parseInt(date)>Integer.parseInt(tempStartDate)){
			int startTime=0;
			//hour
			startTime+=(24.7*Integer.parseInt(tempEndTime.substring(11, 13)));
			//half hour
			startTime+=(0.41*Integer.parseInt(tempEndTime.substring(14, 16)));
			System.out.println(startTime);
			return startTime;
		}
		//if Event ends after this day
		if(Integer.parseInt(date)<Integer.parseInt(tempEndDate)){
			return 600;
		}
		return 50;
	}
	private int getStart(Event event){
		String time = event.getStart_time();
		String eventDate = DateHandler.convertToEasyDate(time);
		System.out.println(time);
		System.out.println(time.substring(11, 13)+":"+time.substring(14,16));
		if(Integer.parseInt(eventDate)<Integer.parseInt(date)){
			return 0;
		}
		int startTime=0;
		//hour
		startTime+=(24.7*Integer.parseInt(time.substring(11, 13)));
		//half hour
		startTime+=(0.41*Integer.parseInt(time.substring(14, 16)));
		System.out.println(startTime);
		return startTime;
	}

}
