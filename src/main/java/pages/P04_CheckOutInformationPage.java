package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static pages.PageBase.longWait;
import static pages.PageBase.shortWait;

public class P04_CheckOutInformationPage {
    //1- Define Web Driver
    WebDriver driver;
    //2- Define Constructor and Initialize the driver
    public P04_CheckOutInformationPage(WebDriver driver){
        this.driver = driver;
    }
    //3- Define the Locators
    private final By informationTitle = By.xpath("//div[@class=\"subheader\"]");
    private final By firstName = By.id("first-name");
    private final By lastName = By.id ("last-name");
    private final By zipCode = By.id("postal-code");
    private final By continueBtn = By.xpath("//div[@class=\"checkout_buttons\"]/input[@class=\"btn_primary cart_button\"]");
    //4- Define the Action Methods
    public P04_CheckOutInformationPage enterFirstName(String firstName) {
        try{longWait(driver).until(ExpectedConditions.visibilityOfElementLocated((this.firstName)));
        }catch (TimeoutException ex){
            ex.printStackTrace();
            System.out.println("The error is " + ex.getMessage());
        }
        driver.findElement(this.firstName).sendKeys(firstName);
        return this;
    }
    public P04_CheckOutInformationPage enterLastName(String lastName) {
        try{shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(this.lastName));
        }catch (TimeoutException ex){
            ex.printStackTrace();
            System.out.println("The error is " + ex.getMessage());
        }
        driver.findElement(this.lastName).sendKeys(lastName);
        return this;
    }
    public P04_CheckOutInformationPage enterZipCode(String zipCode) {
        try{shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(this.zipCode));
        }catch (TimeoutException ex){
            ex.printStackTrace();
            System.out.println("The error is " + ex.getMessage());
        }
        driver.findElement(this.zipCode).sendKeys(zipCode);
        return this;
    }
    public P04_CheckOutInformationPage clickContinueBtn() {
        try{shortWait(driver).until(ExpectedConditions.elementToBeClickable(this.continueBtn));
        }catch (TimeoutException ex){
            ex.printStackTrace();
            System.out.println("The error is " + ex.getMessage());
        }
        driver.findElement(this.continueBtn).click();
        return this;
    }
    public String verifyNavigateToInformationPage(){
        try{longWait(driver).until(ExpectedConditions.elementToBeClickable(this.informationTitle));
        }catch (TimeoutException ex){
            ex.printStackTrace();
            System.out.println("The error is " + ex.getMessage());
        }
        return driver.findElement(this.informationTitle).getText();
    }
}
