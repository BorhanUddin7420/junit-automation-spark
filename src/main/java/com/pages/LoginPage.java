package com.pages;

import com.qa.managers.FileReaderManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private final String baseURL = FileReaderManager.getInstance().getConfigReader().getApplicationUrl();
    WebDriver driver;

    //1. Element Locators:
    private By emailID = By.id("Email");
    private By password = By.id("Password");
    private By signInBtn = By.id("login-button");
    private By loginBtn = By.xpath("(//a[contains(.,'Log in')])[2]");
    private By loginPageErrorMsg = By.xpath("//div[contains(@class,'message-error')]");
    private By accountName = By.xpath("//div[contains(@class, 'customer')]");


    //2. Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void gotoApplication() {
        driver.navigate().to(baseURL);
    }

    public void clickLoginButton_HomePage() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement el1 = driver.findElement(loginBtn);
        wait.until(ExpectedConditions.elementToBeClickable(el1));
        el1.click();
    }

    public void inputIname(String Iname) {
        WebElement el1 = driver.findElement(emailID);
        el1.sendKeys(Iname);
    }

    public void inputPassword(String pwd) {
        WebElement el1 = driver.findElement(password);
        el1.sendKeys(pwd);
    }

    public void clickLoginButton_loginPage() {
        WebElement el1 = driver.findElement(signInBtn);
        el1.click();

    }

    public boolean verifyadminamtrationButtonShowing() {
        return driver.findElement(By.xpath("//*[@href='/Admin']")).isDisplayed();

    }

    public void clickadminamtrationButton() {
        WebElement el1 = driver.findElement(By.xpath("//*[@href='/Admin']"));
        el1.click();
    }


    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getPageURL() {
        return driver.getCurrentUrl();
    }


    public String getLoginPageErrorMsg() {
        return driver.findElement(loginPageErrorMsg).getText().replaceAll("\\r\\n|\\r|\\n", " ");
    }

    public String getAccountName() {
        return driver.findElement(accountName).getText().trim();
    }

}
