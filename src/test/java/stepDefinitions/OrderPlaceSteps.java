package stepDefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.qa.managers.PageObjectManager;
import com.qa.util.Xls_Reader;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class OrderPlaceSteps extends PageObjectManager {
    Xls_Reader excelData = new Xls_Reader("./src/test/resources/testdata/dataset_01.xlsx");
    String productDetails = "Product_details";
    String checkoutDetails = "checkout_details";


    @Before
    public void beforeScenario() {
        launchBrowser();
    }


    @And("Clear my shopping cart")
    public void clearMyShoppingCart() {
        placeOrder.gotoCartPage();
        if (placeOrder.isShoppingCartEmpty() < 1) {
            System.out.println("Shopping cart clearing");
            placeOrder.clickClearShoppingCart_inCartPage();
            System.out.println("Shopping cart cleared");
        } else {
            System.out.println("Shopping cart already empty");
        }
    }

    @And("I have added product\\(s) in my cart page")
    public void iHaveAddedProductSInMyCart() throws Throwable {
        int _totalProducts = excelData.getRowCount(productDetails);
        for (int i = 1; i < _totalProducts; i++) {
            int rowNum = i + 1;
            placeOrder.gotoProductDetailsPage(excelData.getCellData(productDetails, "Product_URL_Path", rowNum));
            ExtentCucumberAdapter.addTestStepLog(i + "st Product Name: " + excelData.getCellData(productDetails, "Product_Name", rowNum));
            placeOrder.inputProductQTY(excelData.getCellData(productDetails, "ProductAddQty", rowNum));
        }
    }

    @And("Verify all added product\\(s) name and nett price showing in my cart page")
    public void verifyAllAddedProductSNameAndNettPriceShowingInMyCart() {
        int _totalProducts = excelData.getRowCount(productDetails);
        placeOrder.gotoCartPage();
        for (int i = 1; i < _totalProducts; i++) {
            int rowNum = i + 1;
            String ex_productName = excelData.getCellData(productDetails, "Product_Name", rowNum);
            String ex_productPrice = excelData.getCellData(productDetails, "Product_Price", rowNum);
            //String ex_productAddQty = excelData.getCellData(productDetails, "ProductAddQty", rowNum);


            //Product Name
            String ac_productName = placeOrder.getProductName(i);
            Assert.assertEquals(ex_productName, ac_productName);
            ExtentCucumberAdapter.addTestStepLog(i + "st Product name: " + ac_productName);

            //Product Price
            String ac_productUnitPrice = placeOrder.getProductNetPriceExVat(i);
            Assert.assertEquals(ex_productPrice, ac_productUnitPrice);
            ExtentCucumberAdapter.addTestStepLog(i + "st Product unit price: " + ac_productUnitPrice);

            //Product total
            //String ex_totalPrice = helperUtil.multiplyOfTwoStringValue(ex_productPrice, ex_productAddQty);
            //String ac_totalPrice = placeOrder.getProductNetPriceExVat(i);
        }

    }

    @When("I have click checkout button in my cart page")
    public void iHaveClickCheckoutButtonInMyCartPage() {
        elementUtil.scrollToPixel(400);
        placeOrder.clickCheckoutButton_inCartPage();
    }

    @And("I have click next button at delivery section in checkout page")
    public void iHaveClickNextButtonAtDeliverySectionInCheckoutPage() {
        elementUtil.scrollToPixel(500);
        String dateOptionNum = excelData.getCellData(checkoutDetails, "DeliveryDateOption", 2);
        placeOrder.selectDeliveryDate(Float.valueOf(dateOptionNum).intValue());
        placeOrder.clickNextButton_inCheckoutPage();
    }

    @Then("I have input customer purchase order number at payment section in checkout page")
    public void iHaveInputCustomerPurchaseOrderNumberAtPaymentSectionInCheckoutPage() {
        elementUtil.scrollToPixel(600);
        String cpo = excelData.getCellData(checkoutDetails, "Customer_Purchase_Order", 2);
        placeOrder.inputCustomerPO_inCheckoutPage(cpo);

    }

    @And("I have click confirm button at payment section in checkout page")
    public void iHaveClickConfirmButtonAtPaymentSectionInCheckoutPage() {
        placeOrder.clickConfirmButton_inCheckoutPage();
    }

    @Then("Verify my order place successfully")
    public void verifyMyOrderPlaceSuccessfully() {
        String ex_sucMsg = excelData.getCellData(checkoutDetails, "Order_Place_Message", 2);
        String ac_sucMsg = placeOrder.getOrderSucMsg_inCheckoutPage();
        System.out.println("Expected data: " + ex_sucMsg);
        System.out.println("Actual data: " + ac_sucMsg);
        Assert.assertEquals(ex_sucMsg, ac_sucMsg);
    }

    @And("Verify my order place in erp")
    public void verifyMyOrderPlaceInErp() {
        String ex_orderNumPattern = excelData.getCellData(checkoutDetails, "Order_Number_Failed_Pattern", 2).trim();
        String[] ac_orderNum = placeOrder.getOrderNumber_inCheckoutPage().split(":");
        System.out.println(ex_orderNumPattern + ": Should not be in order num");
        System.out.println("Actual Order ID: " + ac_orderNum[1]);
        ExtentCucumberAdapter.addTestStepLog("Order number: " + ac_orderNum[1]);
        Assert.assertFalse(ac_orderNum[1].trim().startsWith(ex_orderNumPattern));

    }

}
