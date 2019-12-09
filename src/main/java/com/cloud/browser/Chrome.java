/**
 * 
 */
package com.cloud.browser;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.cloud.core.Log;
import com.cloud.fileHandler.PropertyFileHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Chrome implements IBrowser {

	public WebDriver Driver;
	private static final Logger LOGGER = Logger.getLogger(Class.class.getName());


	public Chrome()
	{
		try{
			System.setProperty( "webdriver.chrome.driver","./Binaries/chromedriver" );
			Log.info("Opening the browser chrome ");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
            Driver = new ChromeDriver(options);
			Log.info("Browser opened");
			Driver.manage().window().maximize();
			//return new ChromeDriver(options);
		}
		catch(Exception E){
			Log.error(E.toString());
		}
	}

	/* (non-Javadoc)
	 * @see testBase.Browser#OpenBrowser()
	 */
	public WebDriver OpenBrowser()
	{
		try
		{
			Driver.get(PropertyFileHandler.getPropertyValue("URL"));
			Log.info("Type URL :"+ PropertyFileHandler.getPropertyValue("URL"));
			Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			return Driver;
		}
		catch(Exception E)
		{
			Log.error("Error while opening browser \n"+ E.toString());
			E.printStackTrace();
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see testBase.Browser#CloseBroser()
	 */
	public WebDriver CloseBroser()
	{
		Driver.quit();
		return null;
	}







}
