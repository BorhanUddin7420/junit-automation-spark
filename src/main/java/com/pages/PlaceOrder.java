package com.pages;

import com.qa.managers.FileReaderManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PlaceOrder {
    private final String baseURL = FileReaderManager.getInstance().getConfigReader().getApplicationUrl();
    //1. Element Locators:
    private final By qtyPlusField = By.xpath("//*[contains(@class, 'add-to-cart-qty-wrapper')]/button[contains(@class, 'counter-plus')]");
    private final By cartCheckoutBtn = By.xpath("//*[@id='checkout']");
    private final By checkoutNextBtn = By.xpath("//*[@name='nextstep']");
    private final By checkoutDeliveryDateDropDown = By.xpath("//*[@id='DeliveryDateString']");
    private final By checkoutDeliveryDateLoadingProgress = By.xpath("//*[@id='delivery-dates-loading-progress']");
    private final By checkoutCustomerPOInput = By.xpath("//*[@id='CustomerReferenceAsPO']");
    private final By checkoutConfirmBtn = By.xpath("//*[@name='nextstep' and @value='Confirm']");
    private final By checkoutSucMsg = By.xpath("//div[contains(@class, 'order-completed')]//div[@class='title']/strong");
    private final By checkoutOrderNum = By.xpath("//div[contains(@class, 'order-completed')]//div[@class='order-number']/strong");
    private final By shoppingCartEmpty = By.xpath("//div[contains(@class,'no-data')]");
    private final By clearShoppingCartButton = By.xpath("//*[@id='shopping-cart-form']/div[1]/div/button[2]");
    WebDriver driver;

    //2. Constructor
    public PlaceOrder(WebDriver driver) {
        this.driver = driver;
    }

    public void gotoProductDetailsPage(String url_path) {
        driver.navigate().to(baseURL + "/" + url_path);
    }

    public int isShoppingCartEmpty() {
        return driver.findElements(shoppingCartEmpty).size();
    }

    public void clickClearShoppingCart_inCartPage() {
        driver.findElement(clearShoppingCartButton).click();
    }

    public void inputProductQTY(String qty) throws InterruptedException {
        int productQty = Float.valueOf(qty).intValue();
        WebElement el1 = driver.findElement(qtyPlusField);

        for (int i = 0; i < productQty; i++) {
            el1.click();
            Thread.sleep(2000);
        }
    }

    public void gotoCartPage() {
        driver.navigate().to(baseURL + "/cart");
    }

    public String getProductName(int ItemRow) {
        return driver.findElement(By.xpath("(//*[@id='shopping-cart-form']//a[contains(@class, 'product-name')])[" + ItemRow + "]")).getText();
    }

    public String getProductNetPriceExVat(int ItemRow) {
        By netPriceExVat = By.xpath("(//*[@id='shopping-cart-form']//td[contains(@id,'unitPrice')]/span)[" + ItemRow + "]");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(netPriceExVat));
        return element.getText();
    }

    public String getProductTotalPrice(int ItemRow) {
        return driver.findElement(By.xpath("(//*[@id='shopping-cart-form']//td[contains(@id,'unitPrice')]/span)[" + ItemRow + "]")).getText();
    }

    public void clickCheckoutButton_inCartPage() {
        driver.findElement(cartCheckoutBtn).click();
    }

    public void selectDeliveryDate(int optionNum) {
        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.invisibilityOfElementLocated(checkoutDeliveryDateLoadingProgress));
        driver.findElement(checkoutDeliveryDateDropDown).click();
        driver.findElement(By.xpath("//*[@id='DeliveryDateString']/option[" + optionNum + "]")).click();
    }


    public void clickNextButton_inCheckoutPage() {
        driver.findElement(checkoutNextBtn).click();

    }

    public void inputCustomerPO_inCheckoutPage(String customerPO) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(checkoutCustomerPOInput));
        element.sendKeys(customerPO);


    }

    public void clickConfirmButton_inCheckoutPage() {
        driver.findElement(checkoutConfirmBtn).click();
    }

    public String getOrderSucMsg_inCheckoutPage() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutSucMsg));
        return element.getText();
    }

    public String getOrderNumber_inCheckoutPage() {
        return driver.findElement(checkoutOrderNum).getText();
    }


}
