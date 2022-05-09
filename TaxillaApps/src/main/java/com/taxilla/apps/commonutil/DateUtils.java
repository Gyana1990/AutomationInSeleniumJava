package com.taxilla.apps.commonutil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils 
{
	public  static String CurrentDateTimeMM_dd_YYYY_HH_mm_ss()
	{
		DateFormat dateFormat = new SimpleDateFormat("MM_dd_YYYY_HH.mm.ss");
		Date date = new Date();
		String date1= dateFormat.format(date);
		return date1;
	}

	public  static String taxillaCurrentDateTimeMMddYYYYHHmmss()
	{
		DateFormat dateFormat = new SimpleDateFormat("MMddYYYYHHmmss");
		Date date = new Date();
		String date1= dateFormat.format(date);
		return date1;
	}
	public  static String CurrentDateTimeMM_dd_yyyy_hh_mm_ss_a()
	{
		DateFormat dateFormat = new SimpleDateFormat("MM_dd_yyyy_hh_mm_ss_a");
		Date date = new Date();
		String date1= dateFormat.format(date);
		//System.out.println("Current date and time is : " +date1);
		return date1;
	}
	public  static String multipleDateFormat()
	{
		//        
		Date date = new Date();   
		DateFormat dateFormat1 = new SimpleDateFormat("MM");
		DateFormat dateFormat2 = new SimpleDateFormat("dd");
		DateFormat dateFormat3 = new SimpleDateFormat("yyyy");
		DateFormat dateFormat4 = new SimpleDateFormat("hh");
		DateFormat dateFormat5 = new SimpleDateFormat("mm a");

		String Month = dateFormat1.format(date);
		if (Month.substring(0,1).contentEquals("0"))
		{
			Month= Month.substring(1,2);
		}

		String Day = dateFormat2.format(date);
		if(Day.substring(0,1).contentEquals("0"))
		{
			Day = Day.substring(1, 2);
		}

		String Year = dateFormat3.format(date);

		String hours = dateFormat4.format(date); 
		if (hours.substring(0,1).contentEquals("0"))
		{
			hours= hours.substring(1,2);
		}
		String MinAMPM = dateFormat5.format(date);

		String date1 = Month + "/" + Day+ "/" + Year + " "+ hours + ":" + MinAMPM ;

		return date1;
	}      

	public  static String CurrentDateTimeMM_dd_YYYY()
	{
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
		Date date = new Date();
		String date1= dateFormat.format(date);
		//      System.out.println("Current date and time is : " +date1);
		return date1;
	}

	public  static String FutureDateByDays(int NoOfdays)
	{
		String Day, Month, Year;
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
		Date date = new Date();
		String date1= dateFormat.format(date);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, NoOfdays);  // number of days to add
		date1 = dateFormat.format(c.getTime());

		Month = date1.substring(0,2);
		Day = date1.substring(3,5);
		Year = date1.substring(6,10);
		if (Month.substring(0,1).contentEquals("0"))
		{
			Month= Month.substring(1,2);
		}

		if(Day.substring(0,1).contentEquals("0"))
		{
			Day = Day.substring(1, 2);
		}

		date1 = Month + "/" + Day+ "/" + Year;

		return date1;
	} 

	public  static String CurrentDateTimeM_d_YYYY()
	{

		Date date = new Date();   
		DateFormat dateFormat1 = new SimpleDateFormat("MM");
		DateFormat dateFormat2 = new SimpleDateFormat("dd");
		DateFormat dateFormat3 = new SimpleDateFormat("yyyy");

		String Month = dateFormat1.format(date);
		if (Month.substring(0,1).contentEquals("0"))
		{
			Month= Month.substring(1,2);
		}

		String Day = dateFormat2.format(date);
		if(Day.substring(0,1).contentEquals("0"))
		{
			Day = Day.substring(1, 2);
		}

		String Year = dateFormat3.format(date);



		String date1 = Month + "/" + Day+ "/" + Year;

		return date1;	    	

	} 
	
	public  static String CurrentDateTime() throws Exception
    {
           DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss_a");
           Date date = new Date();
           String date1= dateFormat.format(date);
           return date1;
    }

	
	 public  static String CurrentDateTimeMM_dd_YYYY1()
	    {
	           DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
	           Date date = new Date();
	           String date1= dateFormat.format(date);
	           Calendar c = Calendar.getInstance();
	           c.add(Calendar.DATE, 20);  // number of days to add
	           date1 = dateFormat.format(c.getTime());
	           return date1;
	    }
	 
	    
	    public  static String FutureDateByDays1(int NoOfdays)
		{
			String Day, Month, Year;
	    	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss_a");
			Date date = new Date();
			String date1= dateFormat.format(date);
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, NoOfdays);  // number of days to add
			date1 = dateFormat.format(c.getTime());
		    return date1;
		} 
	    
	    
	    public  static String Duration(String Date1, String Date2) throws Exception
	    {
//			String StartDateTime = "01/14/2012 09:29:58";
//			String EndDateTime = "01/16/2012 10:31:48";
			
			String StartDateTime = Date1;
			String EndDateTime = Date2;
			
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	    	Date D1=null, D2=null;
	        D1 = df.parse(StartDateTime);
	        D2 = df.parse(EndDateTime);
	        long diff = D2.getTime() - D1.getTime();
	        long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);
			
//			System.out.print(diffDays + " days, ");
//			System.out.print(diffHours + " hours, ");
//			System.out.print(diffMinutes + " minutes, ");
//			System.out.print(diffSeconds + " seconds.");
//			String Duration = diffDays + " Day(s), "+ diffHours + " Hour(s), "+ diffMinutes + " Min(s), "+ diffSeconds +" Sec(s)."; 
			String Duration = diffDays + ":"+ diffHours + ":"+ diffMinutes + ":"+ diffSeconds;
			return Duration;
	        
	        
	    } 
	    
	    public  static String CurrentTime() throws Exception
	    {
	           DateFormat dateFormat = new SimpleDateFormat("HHmmss");
	           Date date = new Date();
	           String date1= dateFormat.format(date);
	           return date1;
	    } 
}
