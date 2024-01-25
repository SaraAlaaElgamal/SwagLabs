package testcases;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P01_LoginPage;
import pages.P02_ProductsPage;
import retryTest.Retry;

import static util.Utility.getExcelData;


public class TC01_Login extends TestBase{
    //Declaration
    String email = getExcelData(0,0,"Credentials");
    String password = getExcelData(0,1,"Credentials");
    //Test Cases
    @Description("Login with Valid Data")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Login")
    @Test(priority = 1, description = "Login with Valid Data",retryAnalyzer = Retry.class)
    public void loginWithValidData_P() throws InterruptedException {
        new P01_LoginPage(driver).enterEmail(email).enterPassword(password).clickLoginBtn();
        //ToDo: Check if Login successfully
        Assert.assertTrue(new P02_ProductsPage(driver).validateLoginSuccessfully());

    }
    @Description("Login with Valid Email")
    @Severity(SeverityLevel.NORMAL)
    @Story("Login")
    @Test(priority = 2, description = "Login with Valid Email and Invalid Password")
    public void loginWithValidEmail_N() throws InterruptedException {
        password = getExcelData(0,1,"Invalid Data");
        new P01_LoginPage(driver).enterEmail(email).enterPassword(password).clickLoginBtn();
        Assert.assertTrue(new P01_LoginPage(driver).validateLoginWithInvalidCredentials());
    }
    @Description("Login with Valid Password")
    @Severity(SeverityLevel.NORMAL)
    @Story("Login")
    @Test(priority = 3, description = "Login with Invalid Email and Valid Password")
    public void loginWithValidPassword_N() throws InterruptedException {
        email = getExcelData(0,0,"Invalid Data");
        password = getExcelData(0,1,"Credentials");
        new P01_LoginPage(driver).enterEmail(email).enterPassword(password).clickLoginBtn();
        Assert.assertTrue(new P01_LoginPage(driver).validateLoginWithInvalidCredentials());
    }
    @Description("Login with Invalid Data")
    @Severity(SeverityLevel.NORMAL)
    @Story("Login")
    @Test(priority = 1, description = "Login with Invalid Data")
    public void loginWithInvalidData_N() throws InterruptedException {
        email = getExcelData(0,0,"Invalid Data");
        password = getExcelData(0,1,"Invalid Data");
        new P01_LoginPage(driver).enterEmail(email).enterPassword(password).clickLoginBtn();
        Assert.assertTrue(new P01_LoginPage(driver).validateLoginWithInvalidCredentials());
    }
}
