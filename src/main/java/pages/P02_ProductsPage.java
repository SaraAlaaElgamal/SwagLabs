package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static pages.PageBase.longWait;
import static pages.PageBase.shortWait;
import static util.Utility.stringToFloat;

public class P02_ProductsPage {
    //1- Define Web Driver
    WebDriver driver;
    //2- Define Constructor and Initialize the driver
    public P02_ProductsPage(WebDriver driver){
        this.driver = driver;
    }
    //3- Define the Locators
    private final By addToCart = By.xpath("//div[@class=\"inventory_list\"]/div[1]/div[3]/button[@class=\"btn_primary btn_inventory\"]");
    private final By price = By.xpath ("//div[@class=\"inventory_list\"]/div[1]/div[3]/div[@class=\"inventory_item_price\"]");
    private final By cart = By.id("shopping_cart_container");
    private final By productTitle = By.xpath("//div[@class=\"product_label\"]");
    private final By removeBtn = By.xpath("//div[@class=\"inventory_list\"]/div/div[3]/button[@class=\"btn_secondary btn_inventory\"]");
    private final By numOfProducts = By.xpath("//span[@class=\"fa-layers-counter shopping_cart_badge\"]");
    //4- Define the Action Methods
    public float addElementsToCart(int noOfItems) {
        try{shortWait(driver).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class=\"inventory_list\"]/div[1]/div[3]/button[@class=\"btn_primary btn_inventory\"]")));
        }catch (TimeoutException ex){
            ex.printStackTrace();
            System.out.println("The error is " + ex.getMessage());
        }
        float totalPrice = 0;
        for (int index = 1; index <= noOfItems ; index++) {
            driver.findElement(By.xpath("//div[@class=\"inventory_list\"]/div[" + index + "]/div[3]/button[@class=\"btn_primary btn_inventory\"]")).click();
            totalPrice += getPrice(index);
        }
        return totalPrice;
    }
    public float getPrice(int index) {
        String stPrice = driver.findElement(By.xpath("//div[@class=\"inventory_list\"]/div[" + index + "]/div[3]/div[@class=\"inventory_item_price\"]")).getText();
        return stringToFloat(stPrice.substring(1));
    }
    public float addRandomElementsToCart(List<Integer> index,int max,int noOfItems) {
        try{longWait(driver).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class=\"inventory_list\"]/div[" + index.get(0) + "]/div[3]/button[@class=\"btn_primary btn_inventory\"]")));
        }catch (TimeoutException ex){
            ex.printStackTrace();
            System.out.println("The error is " + ex.getMessage());
        }
        float totalPrice = 0;
        for (int i = 0 ; i < noOfItems; i++) {
            driver.findElement(By.xpath("//div[@class=\"inventory_list\"]/div[" + index.get(i) + "]/div[3]/button[@class=\"btn_primary btn_inventory\"]")).click();
            totalPrice += getPrice(index.get(i));
            System.out.println("Add Item No "+index.get(i));
        }
        return totalPrice;
    }
    public float removeRandomElement (List<Integer> index, float totalPrice, int randomIndex) {
        final By removeItem = By.xpath("//div[@class=\"inventory_list\"]/div[" + index.get(randomIndex - 1) + "]/div[3]/button[@class=\"btn_secondary btn_inventory\"]");
        try {
            longWait(driver).until(ExpectedConditions.visibilityOfElementLocated(removeItem));
        } catch (TimeoutException ex) {
            ex.printStackTrace();
            System.out.println("The error is " + ex.getMessage());
        }
        System.out.println("Delete Item No " + randomIndex);
        driver.findElement(removeItem).click();
        totalPrice -= getPrice(index.get(randomIndex - 1));
        System.out.println("After remove item the total price is " + totalPrice);
        return totalPrice;
        }
    public P02_ProductsPage clickCartBtn() {
            try{longWait(driver).until(ExpectedConditions.elementToBeClickable(this.cart));
            }catch (TimeoutException ex){
                ex.printStackTrace();
                System.out.println("The error is " + ex.getMessage());
            }
            driver.findElement(this.cart).click();
            return this;
        }

    public Boolean validateLoginSuccessfully(){
        try{longWait(driver).until(ExpectedConditions.visibilityOfElementLocated(this.productTitle));
        }catch (TimeoutException ex){
            ex.printStackTrace();
            System.out.println("The error is " + ex.getMessage());
        }
        return driver.findElement(this.productTitle).getText().equals("Products");
    }
    public Boolean validateAddToCartSuccessfully(int max){
        try{longWait(driver).until(ExpectedConditions.visibilityOfElementLocated(this.numOfProducts));
        }catch (TimeoutException ex){
            ex.printStackTrace();
            System.out.println("The error is " + ex.getMessage());
        }
        return driver.findElement(this.numOfProducts).getText().equals(String.valueOf(max));
    }

}
