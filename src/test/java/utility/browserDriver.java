package utility;

import java.io.File;
import java.lang.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class browserDriver {

    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static ChromeOptions options;

    public static ExtentReports extentReports;

    public static File file;

    public browserDriver() {
    }

    public static void setDriver() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver.exe");
        System.setProperty("webdriver.http.factory", "jdk-http-client");

        options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--incognito", "--start-maximized");

        driver.set(new ChromeDriver(options));
        getDriver().get("https://www.saucedemo.com/");
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

}
