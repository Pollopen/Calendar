package controller;

import java.util.Calendar;

public class DateHandler {
	public static String StringDateToCalendar(String date){
		String newDate;
		try {
			String day = date.substring(8, 10);
			newDate=date.substring(0,4);
			int tempInt=Integer.parseInt(date.substring(5,7));
			tempInt--;
			if(tempInt<=9){
				if(tempInt==0){
					newDate+="00";
				}
				newDate+="0"+Integer.toString(tempInt);	
			}else{
				newDate+=Integer.toString(tempInt);
			}
			if(Integer.parseInt(day)<=9){
				newDate+=""+day;
			}else{
				newDate+=day;
			}
			
		} catch (IndexOutOfBoundsException e) {
			String day = date.substring(6, 8);
			newDate=date.substring(0,4);
			int tempInt=Integer.parseInt(date.substring(4,6));
			tempInt--;
			if(tempInt<=9){
				if(tempInt==0){
					newDate+="00";
				}
				newDate+="0"+Integer.toString(tempInt);	
			}else{
				newDate+=Integer.toString(tempInt);
			}
			if(Integer.parseInt(day)<=9){
				newDate+=""+day;
			}else{
				newDate+=day;
			}
		}
		return newDate;
	}
	//take both normal/easy date ALWAYS returns easyDate
	public static String CalendarStringToDateString(String date){
		String newDate;
		try {
			String day = date.substring(8, 10);
			newDate=date.substring(0,4);
			int tempInt=Integer.parseInt(date.substring(5,7));
			tempInt++;
			if(tempInt<=9){
				if(tempInt==0){
					newDate+="00";
				}
				newDate+="0"+Integer.toString(tempInt);	
			}else{
				newDate+=Integer.toString(tempInt);
			}
			if(Integer.parseInt(day)<=9){
				newDate+=""+day;
			}else{
				newDate+=day;
			}
			
		} catch (IndexOutOfBoundsException e) {
			String day = date.substring(6, 8);
			newDate=date.substring(0,4);
			int tempInt=Integer.parseInt(date.substring(4,6));
			tempInt++;
			if(tempInt<=9){
				if(tempInt==0){
					newDate+="00";
				}
				newDate+="0"+Integer.toString(tempInt);	
			}else{
				newDate+=Integer.toString(tempInt);
			}
			if(Integer.parseInt(day)<=9){
				newDate+=""+day;
			}else{
				newDate+=day;
			}
		}
		return newDate;
	}
	// EASYDATE= 20160101
	public static String convertToEasyDate(String date){
		String newDate=date.substring(0, 4)+date.substring(5, 7)+date.substring(8, 10);
		return newDate;
	}
	// NORMAL = 2016/01/01
	public static String convertFromEasyDate(String date){
		String newDate=date.substring(0,4)+"/"+date.substring(4,6)+"/"+date.substring(6,8);
		return newDate;
	}
	// adds time after date " 12:08:43"
	public static String addTimeToDate(String date){
		String newDate=date+" 01:01:01";
		return newDate;
	}
	public static int getDaysOfMonth(String firstDay){
		String monthNum=firstDay.substring(5, 7);
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
	//returns EASYDATE
	public static String addToDateString(String date, int value){
		String newDate;
		Calendar cal = Calendar.getInstance();
		date=DateHandler.StringDateToCalendar(date);
		try {
			int test = Integer.parseInt(date.substring(8, 10));
		} catch (Exception e) {
			date=DateHandler.convertFromEasyDate(date);
		}
		cal.set(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(5, 7)), Integer.parseInt(date.substring(8, 10)));
		cal.add(Calendar.DATE, value);
		newDate = Integer.toString(cal.get(Calendar.YEAR));
		if(cal.get(Calendar.MONTH)<=9){
			newDate+="0"+Integer.toString(cal.get(Calendar.MONTH));
		}else{
			newDate+=Integer.toString(cal.get(Calendar.MONTH));
		}
		if(cal.get(Calendar.DATE)<=9){
			newDate+="0"+Integer.toString(cal.get(Calendar.DATE));
		}else{
			newDate+=Integer.toString(cal.get(Calendar.DATE));
		}
		newDate=DateHandler.CalendarStringToDateString(newDate);
		return newDate;
	}
}
