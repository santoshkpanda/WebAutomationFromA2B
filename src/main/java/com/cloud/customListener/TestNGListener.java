/**
 * 
 */
package com.cloud.customListener;

import com.cloud.managers.ExtentManager;
import com.cloud.managers.WebDriverManager;
import org.testng.ITestContext;
import org.testng.ITestResult;
import com.cloud.*;
import com.cloud.core.Log;

import java.io.IOException;

public class TestNGListener implements org.testng.ITestListener
{
	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onFinish(org.testng.ITestContext)
	 */
	public void onFinish(ITestContext context) {


		Log.info("Suite "+ context.getName() +" is FINISH");
		Log.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ TEST SUITE FINISH $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
	}

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onStart(org.testng.ITestContext)
	 */
	public void onStart(ITestContext context) {


		Log.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ TEST SUITE START $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		Log.info("Suite "+ context.getName() +" is Start");

	}

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onTestFailedButWithinSuccessPercentage(org.testng.ITestResult)
	 */
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onTestFailure(org.testng.ITestResult)
	 */
	public void onTestFailure(ITestResult result) {

		Log.info("Test case "+result.getName()+" is FAIL");
		Log.info("################################## TEST FAIL ################################");
		ExtentManager.extent_Logger.fail("Test case has been failed");
		try {
			WebDriverManager.captureScreenshot(result.getName());
		} catch (IOException e) {
			Log.error("Error while capturing screenshot "+ e.toString());
		}
	}

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onTestSkipped(org.testng.ITestResult)
	 */
	public void onTestSkipped(ITestResult result) 
	{

		Log.info("Test case "+result.getName()+" is skipped");
		Log.info("################################## TEST SKIP ################################");
	}

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onTestStart(org.testng.ITestResult)
	 */
	public void onTestStart(ITestResult result) 
	{
		Log.info("################################## Test START ################################");
		Log.info("Test case "+result.getName()+" is started");
	}

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onTestSuccess(org.testng.ITestResult)
	 */
	public void onTestSuccess(ITestResult result) {

		Log.info("Test case "+result.getName()+" is PASS");
		Log.info("################################## Test PASS ################################");
		ExtentManager.extent_Logger.pass("Test case has been passed");
	}

}
