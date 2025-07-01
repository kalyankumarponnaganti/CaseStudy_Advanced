package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.browserDriver;
import java.time.Duration;
import static Reports.reportManager.*;
import static org.junit.Assert.assertEquals;
import static pages.loginCredentialsPage.*;

public class sauceDemoSteps {

    @Given("User successfully enters the login credentials username {string} and password {string} and testcase {string}")
    public void user_provides_login_details_sauce_demo_site(String username, String password, String testcase) throws Exception {

        String testDesc = "Login and place an order using user: " + username;
        createTest(testcase, testDesc); // reportManager now handles Screenshots/TestX
                getTest().info("Entering username and password");
                sendkeys_username(username);
                sendkeys_password(password);
                attachScreenshot("Login Page");
                Thread.sleep(3000);
                click_login_btn();
        getTest().info("Login submitted");
        Thread.sleep(3000);
        try {
            WebDriverWait wait = new WebDriverWait(browserDriver.getDriver(), Duration.ofSeconds(5));

            WebElement changePasswordPopup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("change-password-popup")));
            WebElement skipButton = changePasswordPopup.findElement(By.xpath("//button[text()='Skip']"));
            skipButton.click();

            getTest().info("Change password popup handled");
            System.out.println("Change password popup handled.");
        } catch (TimeoutException e) {

            System.out.println("No change password popup detected.");
            getTest().info("No change password popup detected");
        }
        getTest().info("Login button clicked");
        attachScreenshot("Success Login Page");
        Thread.sleep(3000);
    }

    @When("User adds items to cart and checkout")
    public void user_adds_items() throws Exception {
        getTest().info("Adding products to cart");
        click_add_items_btn();
        attachScreenshot("Items Added");
        Thread.sleep(3000);
        click_cart_btn();
        attachScreenshot("Cart View");
        Thread.sleep(3000);
        click_checkout_btn();
        getTest().info("Checkout button clicked");
        Thread.sleep(3000);
    }

    @And("User proceeds to fills information firstname {string} and lastname {string} and postalcode {string} and continue")
    public void user_checks_fill_info(String firstname, String lastname, String postalcode) throws Exception {
        sendkeys_firstName(firstname);
        sendkeys_lastName(lastname);
        sendkeys_postalCode(postalcode);
        attachScreenshot("User Info Filled");
        click_continue_btn();
        getTest().info("Continue button clicked");
        Thread.sleep(3000);
        click_finish_btn();
        attachScreenshot("Order Finished");
        Thread.sleep(3000);
    }

    @Then("User should see order confirmation message \"THANK YOU FOR YOUR ORDER\"")
        public void order_confirmation_validation() throws Exception {
        attachScreenshot("Order Confirmation");
        String actualFinishMsg = visibility_finish_msg();
            assertEquals(actualFinishMsg, "Thank you for your order!");
        getTest().pass("Order confirmed successfully");
        }
}
