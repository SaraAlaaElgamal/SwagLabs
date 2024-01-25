package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static pages.PageBase.longWait;
import static pages.PageBase.shortWait;

public class P01_LoginPage {
    //1- Define Web Driver
    WebDriver driver;
    //2- Define Constructor and Initialize the driver
    public P01_LoginPage(WebDriver driver){
        this.driver = driver;
    }
    //3- Define the Locators
    private final By email = By.id("user-name");
    private final By password = By.id ("password");
    private final By loginBtn = By.id("login-button");
    private final By errorMessage = By.xpath("//h3[@data-test='error']");

    //4- Define the Action Methods
    public P01_LoginPage enterEmail(String email) {
        try{longWait(driver).until(ExpectedConditions.visibilityOfElementLocated(this.email));
        }catch (TimeoutException ex){
            ex.printStackTrace();
            System.out.println("The error is " + ex.getMessage());
        }
        driver.findElement(this.email).sendKeys(email);
        return this;
    }
    public P01_LoginPage enterPassword(String password) {
        try{shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(this.password));
        }catch (TimeoutException ex){
            ex.printStackTrace();
            System.out.println("The error is " + ex.getMessage());
        }
        driver.findElement(this.password).sendKeys(password);
        return this;
    }
    public P01_LoginPage clickLoginBtn() {
        try{shortWait(driver).until(ExpectedConditions.elementToBeClickable(this.loginBtn));
        }catch (TimeoutException ex){
            ex.printStackTrace();
            System.out.println("The error is " + ex.getMessage());
        }
        driver.findElement(this.loginBtn).click();
        return this;
    }
    public Boolean validateLoginWithInvalidCredentials(){
        try{longWait(driver).until(ExpectedConditions.visibilityOfElementLocated(this.errorMessage));
        }catch (TimeoutException ex){
            ex.printStackTrace();
            System.out.println("The error is " + ex.getMessage());
        }
        return driver.findElement(this.errorMessage).getText().equals("Epic sadface: Username and password do not match any user in this service");
    }
}
