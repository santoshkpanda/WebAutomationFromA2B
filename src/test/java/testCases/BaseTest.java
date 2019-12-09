package testCases;

import com.cloud.core.Constants;
import com.cloud.managers.ExtentManager;
import com.cloud.managers.WebDriverManager;
import org.apache.log4j.xml.DOMConfigurator;
import com.cloud.fileHandler.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.LinkedHashMap;

public class BaseTest extends ExcelReader
{
	private WebDriverManager webDriverManager;
    private ExcelReader loadTestCaseRepo;
    private static LinkedHashMap<String, String[]> testCaseRepo;




    /**
     * @return
     */
    WebDriver getDriver(){
        return WebDriverManager.getDriver();
    }

	@BeforeTest
	public void initTestSuite(){

        /*Initializing Test Cases Module*/
        loadTestCaseRepo = new ExcelReader();
        testCaseRepo = loadTestCaseRepo.getTestCases(Constants.TEST_CASE_REPO,"Test Cases");

        /*Initializing Logger Module*/
        DOMConfigurator.configure("log4j.xml");

        /*Initializing Reporting Module */
        ExtentManager.extent_Report = ExtentManager.getInstance();
	}

	@BeforeMethod
    @Parameters({"browserName"})
    public void initializeTestCase(String browserName){
        /*Initializing Browser*/
        webDriverManager = new WebDriverManager();
        webDriverManager.startWebDriverInstance(browserName);
    }

    @AfterTest
    public void afterTest()
    {
        ExtentManager.extent_Report.flush();
        ExtentManager.getInstance().flush();
    }


    @AfterMethod
    public void afterSuite() throws IOException, InterruptedException {
        getDriver().quit();
    }

    /**
     * This method will find the details of test cases from Test Case document
     * @param TestCaseID
     */
    void initTestCase(String TestCaseID)
    {
        ExtentManager.markup = null;
        String testCat = testCaseRepo.get(TestCaseID)[0];
        String testCaseName = testCaseRepo.get(TestCaseID)[1];
        String testDesc = testCaseRepo.get(TestCaseID)[2];
        testDesc = testDesc.replace("\n", "<br/>");
        ExtentManager.extent_Logger = ExtentManager.extent_Report.createTest(TestCaseID + " : " + testCaseName, testDesc);
        ExtentManager.extent_Logger.assignCategory(testCat);
        ExtentManager.extent_Logger.info("Test case initialize");
    }
}

