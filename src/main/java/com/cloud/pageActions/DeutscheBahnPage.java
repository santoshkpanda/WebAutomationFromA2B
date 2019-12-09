package com.cloud.pageActions;

import com.cloud.core.Actions;
import com.cloud.core.Constants;
import com.cloud.core.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;
import java.util.List;

public class DeutscheBahnPage extends Actions {

    private WebDriver browser;

     public DeutscheBahnPage(WebDriver Driver) {
        super(Driver);
        this.browser = Driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(Driver, Constants.DRIVER_WAIT), this);
    }

    @FindBy(xpath = "//div[@id='language-selector']")
    private WebElement languageSelector;

    @FindBy(xpath = "//a[contains(text(),'English')]")
    private WebElement englishLanguageSelect;

    @FindBy(xpath = "//span[text()='Offers']/parent::a")
    private WebElement offersSection;

    @FindBy(xpath = "//input[@id='locS0']")
    private WebElement fromLocationEle;

    @FindBy(xpath = "//input[@id='locZ0']")
    private WebElement toLocationEle;

    @FindBy(id="REQ0JourneyDate")
    private WebElement dateOfDeparture;

    @FindBy(id="searchConnectionButton")
    private WebElement searchButton;

    @FindBy(xpath = "//tbody[contains(@class, 'boxShadow  scheduledCon')]")
    private WebElement tripDetailsTable;

    @FindBy(id = "resultsOverview")
    private WebElement resultViewTable;

    @FindBy(id="querySummaryTextual")
    private WebElement overViewSummary;

    /**
     * This method will search for Trips from departure location to destination location from DeutscheBahn Page
     * @param fromLocation
     * @param toLocation
     * @param departureDate
     */
    public void searchForTripsFromDeutscheBahnPage(String fromLocation, String toLocation, String departureDate){
        type(fromLocationEle, fromLocation);
        type(toLocationEle, toLocation);
        type(dateOfDeparture, departureDate);
        click(searchButton);
        waitForElementToVisisble(tripDetailsTable);
        Assert.assertTrue(isDisplayed(tripDetailsTable), "Trips are not displayed for searched locations");
    }

    /**
     * This method will return the location of trip which is fastest in available table entries
     * @return
     */
    public int getFastestTripIndex() {
        waitForElementToVisisble(tripDetailsTable);
        int smallestTripIndex = 0;
        float smallestTripDuration = 0;
        List<WebElement> currentDuration = browser.findElements(By.xpath("//td[@class='duration lastrow']"));

        for (int iterator = 0; iterator < currentDuration.size() ; iterator++) {
            String tempVal = currentDuration.get(iterator).getText().replace(':', '.').trim();
            if(iterator==0){
                smallestTripDuration = Float.parseFloat(tempVal);
            }

            if(Float.parseFloat(tempVal) < smallestTripDuration){
                smallestTripIndex = iterator;
                smallestTripDuration = Float.parseFloat(tempVal);
            }

        }
        Log.info("The smallest entry found, "+ smallestTripDuration + " at index "+ smallestTripIndex);
        return smallestTripIndex+1;
    }


    /**
     * This method will select the fastest trip from the available trips
     * @param fastestTripIndex
     */
    public void selectFastestOffer(int fastestTripIndex){
        String generateXpath = "(//td[@class='fareStd button-inside tablebutton center']//a)["+fastestTripIndex+"]";
        WebElement offerButton = browser.findElement(By.xpath(generateXpath));
        click(offerButton);
        waitForElementToVisisble(overViewSummary);
        Assert.assertTrue(isDisplayed(overViewSummary), "Overall Summary is not displayed");
    }
}
