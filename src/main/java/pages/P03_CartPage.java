package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static pages.PageBase.longWait;
import static pages.PageBase.shortWait;
import static util.Utility.stringToFloat;

public class P03_CartPage {
    //1- Define Web Driver
    WebDriver driver;
    //2- Define Constructor and Initialize the driver
    public P03_CartPage(WebDriver driver){
        this.driver = driver;
    }
    //3- Define the Locators
    private final By checkOutButton = By.xpath("//html/body/div/div[2]/div[3]/div/div[2]/a[@class=\"btn_action checkout_button\"]");
    private final By myCartTitle = By.xpath("//div[@class=\"subheader\"]");
    private final By continueShoppingBtn = By.xpath("//a[@class=\"btn_secondary\"]");
    //4- Define the Action Methods

    public P03_CartPage clickCheckOutBtn() {
        try{longWait(driver).until(ExpectedConditions.elementToBeClickable(this.checkOutButton));
        }catch (TimeoutException ex){
            ex.printStackTrace();
            System.out.println("The error is " + ex.getMessage());
        }
        driver.findElement(this.checkOutButton).click();
        return this;
    }
    public float getTotalPrice(int noOfItems){
        try{longWait(driver).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"cart_item\"][1]/div[2]/div[@class=\"item_pricebar\"]/div")));
        }catch (TimeoutException ex){
            ex.printStackTrace();
            System.out.println("The error is " + ex.getMessage());
        }
        float totalPrice = 0;
        String stPrice;
        for (int index = 1 ; index <= noOfItems; index++) {
            stPrice = driver.findElement(By.xpath("//div[@class=\"cart_item\"]["+index+"]/div[2]/div[@class=\"item_pricebar\"]/div")).getText();
            totalPrice += stringToFloat(stPrice);
        }
        System.out.println("totalPrices in Cart Page = "+totalPrice);
        return totalPrice;
    }
    public P03_CartPage clickContinueShoppingBtn() {
        try{shortWait(driver).until(ExpectedConditions.elementToBeClickable(this.continueShoppingBtn));
        }catch (TimeoutException ex){
            ex.printStackTrace();
            System.out.println("The error is " + ex.getMessage());
        }
        driver.findElement(this.continueShoppingBtn).click();
        return this;
    }
    public String verifyNavigateToMyCartsPage(){
        try{longWait(driver).until(ExpectedConditions.elementToBeClickable(this.myCartTitle));
        }catch (TimeoutException ex){
            ex.printStackTrace();
            System.out.println("The error is " + ex.getMessage());
        }
        return driver.findElement(this.myCartTitle).getText();
    }
}
