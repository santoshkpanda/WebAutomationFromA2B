package com.cloud.managers;

import com.cloud.browser.Chrome;
import com.cloud.browser.Firefox;
import com.cloud.browser.IBrowser;
import com.cloud.core.Log;
import com.cloud.fileHandler.PropertyFileHandler;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class WebDriverManager {

    private static ThreadLocal<WebDriver> Driver=new ThreadLocal<>();

    private static final Logger LOGGER = Logger.getLogger(Class.class.getName());

    public static WebDriver getDriver(){
        return Driver.get();
    }

    private static void setDriver(WebDriver driver){
        Driver.set(driver);
    }

    private WebDriver initialiseDriver(String browserName){
        WebDriver currentDriverSession = null;
        try {
            LOGGER.info("Browser Selected, "+browserName);
            switch (browserName) {
                case "CHROME":
                    IBrowser  GetBrowser = new Chrome();
                    currentDriverSession = GetBrowser.OpenBrowser();
                    break;
                case "FIREFOX":
                    IBrowser GetBrowser1 = new Firefox();
                    currentDriverSession = GetBrowser1.OpenBrowser();
                default:
                    break;
            }
        } catch (IOException e) {
            Log.error(e.toString());
        }
        return currentDriverSession;
    }

    public void startWebDriverInstance(String node){
        WebDriver currentWebDriverSession = initialiseDriver(node);
        setDriver(currentWebDriverSession);
    }

    public static void stopWebDriver() throws IOException, InterruptedException {
        //WebDriverManager.getDriver().quit();
        getDriver().close();
    }

    /**
     * Method to capture screenshot and save in Screenshot folder
     * @param screenshotName
     * @throws IOException
     */
    public static void captureScreenshot(String screenshotName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) Driver.get();
        File source = ts.getScreenshotAs(OutputType.FILE);
        String dest = "./screenShots/"+screenshotName+".jpeg";
        File destination = new File(dest);
        FileUtils.copyFile(source, destination);
    }
}
