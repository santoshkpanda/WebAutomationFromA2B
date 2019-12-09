package com.cloud.core;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;


public class Log
{
	private static Logger Log = Logger.getLogger(com.cloud.core.Log.class.getClass());
	// Keep constructor and keep in DOMCOnfig in cosnt

	
	public static void info(String Message)
	{
		DOMConfigurator.configure("log4j.xml");
		Log.info(Message);
		System.out.println(Message);
	}

	public static void warn(String Message)
	{
		DOMConfigurator.configure("log4j.xml");
		Log.warn(Message);
		System.out.println(Message);
	}

	public static void error(String Message)
	{
		DOMConfigurator.configure("log4j.xml");
		Log.error(Message);
		System.out.println(Message);
	}

	public static void fatal(String Message)
	{
		DOMConfigurator.configure("log4j.xml");
		Log.fatal(Message);
		System.out.println(Message);

	}

}
