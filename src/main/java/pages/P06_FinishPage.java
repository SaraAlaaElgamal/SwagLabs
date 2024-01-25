package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static pages.PageBase.longWait;

public class P06_FinishPage {
    //1- Define Web Driver
    WebDriver driver;
    //2- Define Constructor and Initialize the driver
    public P06_FinishPage(WebDriver driver){
        this.driver = driver;
    }
    //3- Define the Locators
    private final By thankYouText = By.xpath("//h2[@class=\"complete-header\"]");
    //4- Define the Action Methods
    public Boolean verifyOrderCompletedSuccessfully (){
        try{longWait(driver).until(ExpectedConditions.elementToBeClickable(this.thankYouText));
        }catch (TimeoutException ex){
            ex.printStackTrace();
            System.out.println("The error is " + ex.getMessage());
        }
        return driver.findElement(this.thankYouText).getText().equals("THANK YOU FOR YOUR ORDER");
    }
}
