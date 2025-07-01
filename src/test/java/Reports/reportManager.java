package Reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utility.browserDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class reportManager {
    private static ExtentReports extentReports;
    private static ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<>();
    private static ThreadLocal<String> currentTestCase = new ThreadLocal<>();

    // Create and configure the ExtentReports instance
    public static void initReports() {
        if (extentReports == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("report.html");
            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);
        }
    }

    public static void flushReports() {
        if (extentReports != null) {
            extentReports.flush();
        }
    }

    public static void createTest(String testName, String description) {
        ExtentTest test = extentReports.createTest(testName, description);
        extentTestThread.set(test);
        currentTestCase.set(testName.replaceAll("\\s+", "")); // e.g., Test1
    }

    public static ExtentTest getTest() {
        return extentTestThread.get();
    }

    public static void unload() {
        extentTestThread.remove();
        currentTestCase.remove();
    }

    public static String captureScreenshot(String baseFilename) {
        File src = ((TakesScreenshot) browserDriver.getDriver()).getScreenshotAs(OutputType.FILE);
        // Timestamp
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String testCase = currentTestCase.get() != null ? currentTestCase.get() : "General";
        String screenshotDir = "Screenshots/" + testCase;
        String fileName = baseFilename.replace(" ", "_") + "_" + timestamp + ".png";
        String fullPath = screenshotDir + "/" + fileName;

        try {
            Files.createDirectories(Paths.get(screenshotDir));
            Files.copy(src.toPath(), Paths.get(fullPath), StandardCopyOption.REPLACE_EXISTING);
            return fullPath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void attachScreenshot(String title) {
        String path = captureScreenshot(title.replace(" ", "_") + ".png");
        if (path != null) {
            getTest().addScreenCaptureFromPath(path, title);
        }
    }
}
