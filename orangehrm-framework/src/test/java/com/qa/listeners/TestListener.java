package com.qa.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.qa.tests.BaseTest;
import com.qa.utils.ExtentManager;
import com.qa.utils.ScreenshotUtils;

public class TestListener implements ITestListener {

    private static final Logger log = LogManager.getLogger(TestListener.class);
    private static final ExtentReports extent = ExtentManager.getInstance();
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();            // CHANGED: one private copy per thread

    @Override
    public void onTestStart(ITestResult result) {
        log.info("START: {}", result.getName());
        test.set(extent.createTest(result.getName()));                                  // CHANGED: .set(...)
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("PASS: {}", result.getName());
        test.get().pass("Test passed");                                                 // CHANGED: .get()
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("FAIL: {} ({})", result.getName(), result.getThrowable().getClass().getSimpleName());
        test.get().fail(result.getThrowable());                                         // CHANGED: .get()

        Object testClass = result.getInstance();

        if (testClass instanceof BaseTest) {
            WebDriver driver = ((BaseTest) testClass).getDriver();
            String name = result.getName() + "_" + System.currentTimeMillis();
            String path = ScreenshotUtils.takeScreenshot(driver, name);
            if (path != null) {
                test.get().addScreenCaptureFromPath(path);                              // CHANGED: .get()
            }
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}