package com.cloud.pageActions;

import com.cloud.core.Actions;
import com.cloud.core.Constants;
import com.cloud.core.Log;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.PublicImpl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

public class LoginPage extends Actions {

    LoginPage(WebDriver Driver) {
        super(Driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(Driver, Constants.DRIVER_WAIT), this);
    }


    @FindBy(xpath = "//input[@name='user-reception-email']")
    private WebElement emailIdTextBox;

    @FindBy(xpath = "//input[@name='user-reception-password']")
    private WebElement passwordTextBox;

    @FindBy(xpath = "//button[contains(text(),'Log In')]")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@id='user-reception__error-box'][2]")
    private WebElement errorMessage;

    /**
     * This method will login into application with given credentials
     * @param userName
     * @param password
     */
    public void loginInApp(String userName, String password){
        Log.info("Login in application with "+ userName);
        type(emailIdTextBox, userName);
        type(passwordTextBox, password);
        //submit(loginButton);
        click(loginButton);
        Assert.assertFalse(isDisplayed(errorMessage), "Login failed");
    }
}
