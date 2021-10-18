package stepDefinitions;

import com.qa.managers.FileReaderManager;
import com.qa.managers.PageObjectManager;
import com.qa.util.Xls_Reader;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginPageSteps extends PageObjectManager {
    private final String baseURL = FileReaderManager.getInstance().getConfigReader().getApplicationUrl();
    Xls_Reader dataset01 = new Xls_Reader("./src/test/resources/testdata/dataset_01.xlsx");
    String user_details = "User_Info";

    @Before
    public void beforeScenario() {
        launchBrowser();
    }


    @Given("I am at home page")
    public void I_am_at_iia_nop_commerce_home_page() {
        loginPage.gotoApplication();
        String pageURL = loginPage.getPageURL();
        System.out.println("Home Page URL: " + pageURL);
        Assert.assertTrue(pageURL.startsWith(baseURL));
    }

    @When("I click login button from home page")
    public void I_click_login_button_from_home_page() {
        loginPage.clickLoginButton_HomePage();
    }

    @Then("I input {string} as email at login page")
    public void I_input_as_email_at_login_page(String Iname) {
        loginPage.inputIname(Iname);
    }

    @Then("I input {string} as password at login page")
    public void I_input_as_password_at_login_page(String pwd) {
        loginPage.inputPassword(pwd);
    }

    @Then("I click login button at login page")
    public void I_click_button_at_login_page() {
        loginPage.clickLoginButton_loginPage();
    }

    @Then("verify I login successfully and administration menu showing")
    public void verify_I_login_successfully_and_administration_menu_showing() {
        Assert.assertTrue(loginPage.verifyadminamtrationButtonShowing());
    }

    @When("I click administration button")
    public void I_click_administration_button() {
        loginPage.clickadminamtrationButton();
    }

    @Then("verify I successfully navigated to admin dashboard")
    public void verify_I_successfully_navigated_to_admin_dashboard() {
        String adminPageTitle = loginPage.getPageTitle();
        System.out.println("Dashboard page title: " + adminPageTitle);
        Assert.assertEquals("Dashboard/admin", adminPageTitle);
    }

    @Then("verify error message for login {string}")
    public void verifyErrorMessageForLogin(String msg) {
        String errorMsg = loginPage.getLoginPageErrorMsg();
        System.out.println("Login error message: " + errorMsg);
        Assert.assertEquals(msg, errorMsg);

    }

    @Then("Mahbubur successfully login with valid credentials in his account")
    public void mahbuburSuccessfullyLoginWithValidCredentialsInHisAccount() {
        String user_email = dataset01.getCellData(user_details, "Email", 2);
        String user_pwd = dataset01.getCellData(user_details, "Password", 2);
        String fname = dataset01.getCellData(user_details, "First-name", 2);
        String lname = dataset01.getCellData(user_details, "Last-name", 2);

        System.out.println("User email :" + user_email);
        System.out.println("User password :" + user_pwd);

        loginPage.clickLoginButton_HomePage();
        loginPage.inputIname(user_email);
        loginPage.inputPassword(user_pwd);
        loginPage.clickLoginButton_loginPage();

        String accName = loginPage.getAccountName();
        Assert.assertEquals(fname + " " + lname, accName);
    }

    @And("I have login with valid credentials in my account")
    public void iHaveLoginWithValidCredentialsInMyAccount() {
        String user_email = dataset01.getCellData(user_details, "Email", 2);
        String user_pwd = dataset01.getCellData(user_details, "Password", 2);

        System.out.println("User email :" + user_email);
        System.out.println("User password :" + user_pwd);

        loginPage.clickLoginButton_HomePage();
        loginPage.inputIname(user_email);
        loginPage.inputPassword(user_pwd);
        loginPage.clickLoginButton_loginPage();
    }

    @Then("Verify I have login into my account successfully")
    public void verifyIHaveLoginIntoMyAccountSuccessfully() {
        String fname = dataset01.getCellData(user_details, "First-name", 2);
        String lname = dataset01.getCellData(user_details, "Last-name", 2);

        String accName = loginPage.getAccountName();
        Assert.assertEquals(fname + " " + lname, accName);
    }
}
