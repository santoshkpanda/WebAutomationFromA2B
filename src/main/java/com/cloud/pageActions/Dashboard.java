package com.cloud.pageActions;

import com.cloud.core.Actions;
import com.cloud.core.Constants;
import com.cloud.core.Log;
import com.cloud.managers.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class Dashboard extends Actions {

    private WebDriver browser;

    public Dashboard(WebDriver Driver) {
        super(Driver);
        this.browser = Driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(Driver, Constants.DRIVER_WAIT), this);
    }

    @FindBy(xpath = "//a[@data-key='signin']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[contains(@class,'ssc-destinations__departure')]//input")
    private WebElement fromSearchBox;

    @FindBy(xpath = "//div[contains(@class,'ssc-destinations__departure')]//li[1]")
    private WebElement departureSuggestion;

    @FindBy(xpath = "//div[contains(@class,'ssc-destinations__arrival')]//input")
    private WebElement destinationSearchBox;

    @FindBy(xpath = "//div[contains(@class,'ssc-destinations__arrival')]//li[1]")
    private WebElement destinationSuggestion;

    @FindBy(xpath = "//button[@class='ssc-submit']")
    private WebElement searchButton;

    @FindBy(xpath = "//li[contains(@class, 'day--today')]/button")
    private WebElement getTodaysDateElement;

    @FindBy(xpath = "//button[contains(@class,'emission-modal__button')]")
    private WebElement gotItButton;


    /**
     * This method will navigate to Login screen from dashboard page
     * @return Login screen object
     */
    public LoginPage navigateToLoginPage(){
        click(loginButton);
        return new LoginPage(browser);
    }

    /**
     * Search for trip with given departure and destination on Dashboard page
     * @param departure
     * @param destination
     */
    public void searchShortestDurationTrip(String departure, String destination){
        Log.info("Entering trip details");
        type(fromSearchBox, departure);
        click(departureSuggestion);
        type(destinationSearchBox, destination);
        click(destinationSuggestion);
        click(searchButton);
        switchToAnotherWindow(0);
    }
}
