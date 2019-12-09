/**
 * 
 */
package com.cloud.browser;

import org.openqa.selenium.WebDriver;

import java.io.IOException;

public interface IBrowser
{	
	WebDriver OpenBrowser() throws IOException;
	WebDriver CloseBroser();
}
