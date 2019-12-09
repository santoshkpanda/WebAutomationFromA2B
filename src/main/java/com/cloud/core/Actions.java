/**
 * 
 */
package com.cloud.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Actions {
	private static WebDriver driverInstance;
	public WebDriverWait wait;
	WebDriver driver;

	public Actions(WebDriver Driver) {
		this.driverInstance = Driver;
		wait = new WebDriverWait( driverInstance, Constants.DRIVER_WAIT );


	}

	/**
	 * Click on webelement
	 * @param ElementToClick
	 */
	protected void click(WebElement ElementToClick) {
		try {
			if(ElementToClick !=null)
			{
				wait.until(ExpectedConditions.elementToBeClickable(ElementToClick));
				ElementToClick.click();
			}

			else
			{
				Log.error("Element could not found");
			}

		} catch (Exception e) {
			Log.error( "Error while clicking the element" + e.toString() );
		}
	}

	/**
	 * Enter text in textbox
	 * @param ElementToType
	 * @param StringToType
	 */
	public void type(WebElement ElementToType, String StringToType)
	{
		try {
			if(ElementToType  !=null)
			{
				wait.until(ExpectedConditions. visibilityOf(ElementToType));
				ElementToType.clear();
				ElementToType.sendKeys(StringToType);
				Log.info("Enter the text, "+StringToType);
			}

			else
			{
				Log.error("Element could not found");
			}

		} catch (Exception e) {
			Log.error( "Error while writing the element" + e.toString() );
		}
	}

	/**
	 * Submit button
	 * @param ElementToSubmit
	 */
	public void submit(WebElement ElementToSubmit)
	{
		try {
			if(ElementToSubmit !=null)
			{
				wait.until(ExpectedConditions.visibilityOf(ElementToSubmit));
				ElementToSubmit.submit();
				Log.info("Submit the webelement");
			}

			else
			{
				Log.error("Element could not found");
			}

		} catch (Exception e) {
			Log.error("Error while submitting the element"+ e.toString());
		}
	}

	/**
	 * Wait for element till it is visible
	 * @param elementToCheck
	 */
	public void waitForElementToVisisble(WebElement elementToCheck){
		try {
			wait.until(ExpectedConditions.visibilityOf(elementToCheck));
		}
		catch (Exception ex){
			Log.error("Element not visible");
		}
	}


	/** This function will return whether the element given is displayed or not
	 * @param ElementToCheck
	 * @return
	 */
	public boolean isDisplayed(WebElement ElementToCheck){
		WebDriverWait wait = new WebDriverWait(driverInstance, Constants.DRIVER_WAIT);
		try{
			wait.until(ExpectedConditions.visibilityOf(ElementToCheck));
			return ElementToCheck.isDisplayed();
		}
		catch(Exception E){
			Log.error("Element is not getting displayed");
			return false;
		}
	}

	public static void switchToAnotherWindow(int windowIndex){
		Set handles = driverInstance.getWindowHandles();
		String[] individualHandle = new String[handles.size()];
		Iterator it = handles.iterator();
		int i =0;
		while(it.hasNext())
		{
			individualHandle[i] = (String) it.next();
			i++;
		}
		Log.info("Switching to Window "+windowIndex);
		driverInstance.switchTo().window(individualHandle[windowIndex]);
	}
}





