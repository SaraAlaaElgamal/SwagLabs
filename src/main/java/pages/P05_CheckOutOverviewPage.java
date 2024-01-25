package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static pages.PageBase.longWait;
import static pages.PageBase.shortWait;
import static util.Utility.stringToFloat;

public class P05_CheckOutOverviewPage {
    //1- Define Web Driver
    WebDriver driver;
    //2- Define Constructor and Initialize the driver
    public P05_CheckOutOverviewPage(WebDriver driver){
        this.driver = driver;
    }
    //3- Define the Locators
    private final By overviewTitle = By.xpath("//div[@class=\"subheader\"]");
    private final By itemTotal = By.xpath("//div[@class=\"summary_info\"]/div[@class=\"summary_subtotal_label\"]");
    private final By tax = By.xpath("//div[@class=\"summary_info\"]/div[@class=\"summary_tax_label\"]");
    private final By total = By.xpath("//div[@class=\"summary_info\"]/div[@class=\"summary_total_label\"]");

    private final By finishBtn = By.xpath("//div[@class=\"cart_footer\"]/a[@class=\"btn_action cart_button\"]");
    //4- Define the Action Methods
    public float getItemTotal() {
        try{longWait(driver).until(ExpectedConditions.visibilityOfElementLocated(this.itemTotal));
        }catch (TimeoutException ex){
            ex.printStackTrace();
            System.out.println("The error is " + ex.getMessage());
        }
        String stringItemTotal = driver.findElement(this.itemTotal).getText().substring(13);
        return stringToFloat(stringItemTotal);
    }
    public float getTax() {
        try{shortWait(driver).until(ExpectedConditions.elementToBeClickable(this.tax));
        }catch (TimeoutException ex){
            ex.printStackTrace();
            System.out.println("The error is " + ex.getMessage());
        }
        String stringTax = driver.findElement(this.tax).getText().substring(6);
        return stringToFloat(stringTax);
    }
    public float getTotal() {
        try{shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(this.total));
        }catch (TimeoutException ex){
            ex.printStackTrace();
            System.out.println("The error is " + ex.getMessage());
        }
        String stringTotal = driver.findElement(this.total).getText().substring(8);
        return stringToFloat(stringTotal);
    }
    public P05_CheckOutOverviewPage clickFinishBtn() {
        try{shortWait(driver).until(ExpectedConditions.elementToBeClickable(this.finishBtn));
        }catch (TimeoutException ex){
            ex.printStackTrace();
            System.out.println("The error is " + ex.getMessage());
        }
        driver.findElement(this.finishBtn).click();
        return this;
    }
    public String verifyNavigateToOverviewPage(){
        try{longWait(driver).until(ExpectedConditions.elementToBeClickable(this.overviewTitle));
        }catch (TimeoutException ex){
            ex.printStackTrace();
            System.out.println("The error is " + ex.getMessage());
        }
        return driver.findElement(this.overviewTitle).getText();
    }
}
