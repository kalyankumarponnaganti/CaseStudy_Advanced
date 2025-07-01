package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utility.browserDriver;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.Assert.assertEquals;

public class loginCredentialsPage {

    public static String usernameField = "//input[@name='user-name']";
    public static String passwordField = "//input[@id='password']";
    public static String loginButton = "//input[@id='login-button']";


    private static WebDriver driver() {
        return browserDriver.getDriver();
    }

    public static void dismissPasswordPopup() {
        try {
            Robot robot = new Robot();
            Thread.sleep(2000); // Wait for the popup to appear
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void sendkeys_username(String username) {
        driver().findElement(By.xpath(usernameField)).sendKeys(username);
    }

    public static void sendkeys_password(String password) {
        driver().findElement(By.xpath(passwordField)).sendKeys(password);
    }

    public static void click_login_btn() {
        driver().findElement(By.xpath(loginButton)).click();
    }
    public static String bagAddItemBt = "//button[@id='add-to-cart-sauce-labs-backpack']";
    public static String lightAddItemBt = "//button[@id='add-to-cart-sauce-labs-bike-light']";
    public static String shirtAddItemBt = "//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']";
    public static String cartBt = "//a[@class='shopping_cart_link']";

    public static void click_add_items_btn()
    {
        driver().findElement(By.xpath(bagAddItemBt)).click();
        driver().findElement(By.xpath(lightAddItemBt)).click();
        driver().findElement(By.xpath(shirtAddItemBt)).click();
    }

    public static void click_cart_btn()
    {
        driver().findElement(By.xpath(cartBt)).click();
    }
    public static String finishBt = "//button[@id='finish']";

    public static void click_finish_btn()
    {
        driver().findElement(By.xpath(finishBt)).click();
    }

    public static String firstNameField = "//input[@id='first-name']";
    public static String lastNameField = "//input[@id='last-name']";
    //input[@id='last-name']
    public static String postalCodeField = "//input[@id='postal-code']";
    //input[@id='postal-code']
    public static String continueBt ="//input[@id='continue']";

    public static void sendkeys_firstName(String firstname)
    {
        driver().findElement(By.xpath(firstNameField)).sendKeys(firstname);
    }
    public static void sendkeys_lastName(String lastname)
    {
        driver().findElement(By.xpath(lastNameField)).sendKeys(lastname);
    }
    public static void sendkeys_postalCode(String postalcode)
    {
        driver().findElement(By.xpath(postalCodeField)).sendKeys(postalcode);
    }
    public static void click_continue_btn()
    {
        driver().findElement(By.xpath(continueBt)).click();
    }
    public static String finishTxt = "//h2[normalize-space()='Thank you for your order!']";
    public static String visibility_finish_msg(){
        String actualFinishMsg = driver().findElement(By.xpath(finishTxt)).getText();
        return actualFinishMsg;
    }

    public static void order_confirmation_validation(){
        String actualFinishMsg = visibility_finish_msg();
        assertEquals(actualFinishMsg, "Thank you for your order!");
    }
    public static String checkOutBt = "//button[@id='checkout']";

    public static void click_checkout_btn()
    {
        driver().findElement(By.xpath(checkOutBt)).click();
    }
}
