/**
 * 
 */
package com.cloud.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils
{


	private static final DateFormat df = new SimpleDateFormat("dd.MM.yy");

	/**
	 * Get current date
	 * @return
	 */
	public static String getCurrentTime()
	{
		Date date = new Date();
		Log.info("Current Time is, "+df.format(date));
		return df.format(date);
	}

	/**
	 * Get future date
	 * @param days
	 * @return
	 */
	public static String dateAfterCurrentDate(int days){
		Calendar cal = Calendar.getInstance();
		//Displaying current date in the desired format
		Log.info("Current Date: "+df.format(cal.getTime()));

		//Number of Days to add
		cal.add(Calendar.DAY_OF_MONTH, days);
		//Date after adding the days to the current date
		String newDate = df.format(cal.getTime());
		//Displaying the new Date after addition of Days to current date
		Log.info("Date after Addition: "+newDate);
		return newDate;
	}
}
