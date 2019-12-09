/**
 * 
 */
package com.cloud.browser;

import com.cloud.core.Log;
import com.cloud.fileHandler.PropertyFileHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;


public class Firefox implements IBrowser {

	public WebDriver Driver;
	private static final Logger LOGGER = Logger.getLogger(Class.class.getName());

	/* (non-Javadoc)
	 * @see com.cloud.browser.IBrowser#OpenBrowser()
	 */
	public WebDriver OpenBrowser() throws IOException {
		System.setProperty("webdriver.gecko.driver","./Binaries/geckodriver");

		Driver = new FirefoxDriver();
		Driver.get(PropertyFileHandler.getPropertyValue("URL"));
		Log.info("Type URL :"+ PropertyFileHandler.getPropertyValue("URL"));
		Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return Driver;

	}

	/* (non-Javadoc)
	 * @see com.cloud.browser.IBrowser#CloseBroser()
	 */
	public WebDriver CloseBroser() {
		// TODO Auto-generated method stub
		return null;
	}

}
