package com.qa.managers;

import com.pages.LoginPage;
import com.pages.PlaceOrder;
import com.qa.util.ElementUtil;
import org.openqa.selenium.WebDriver;

public class PageObjectManager extends DriverManager {
    public LoginPage loginPage;
    public PlaceOrder placeOrder;
    public DriverManager driverManager;
    public ElementUtil elementUtil;
    protected WebDriver driver = getDriver();


    public void launchBrowser() {
        driverManager = new DriverManager();
        loginPage = new LoginPage(driver);
        placeOrder = new PlaceOrder(driver);
        elementUtil = new ElementUtil(driver);

    }


}
