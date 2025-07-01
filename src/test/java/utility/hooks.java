package utility;

import Reports.reportManager;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

import java.awt.*;
import java.io.File;

public class hooks {


    @BeforeAll
    public static void beforeAllTests() {
        reportManager.initReports(); // only once
    }

    @Before
    public void setUP(io.cucumber.java.Scenario scenario) throws InterruptedException, Exception{
        browserDriver.setDriver();
    }

    @After
    public void tearDown(io.cucumber.java.Scenario scenario) throws Exception{

        if (scenario.isFailed()) {
            reportManager.getTest().fail("Scenario Failed: " + scenario.getName());
        } else {
            reportManager.getTest().pass("Scenario Passed: " + scenario.getName());
        }
        reportManager.unload();
        browserDriver.quitDriver();
    }

    @AfterAll
    public static void afterAllTests() throws Exception {
        reportManager.flushReports();
        Desktop.getDesktop().browse(new File("report.html").toURI());
    }


}