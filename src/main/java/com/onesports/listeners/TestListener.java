package com.onesports.listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.onesports.base.BaseTest;
import com.onesports.utilities.ScreenshotUtil;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object testInstance = result.getInstance();
        if (testInstance instanceof BaseTest) {
            WebDriver driver = ((BaseTest) testInstance).getDriver();
            if (driver != null) {
                String screenshotPath = ScreenshotUtil.takeScreenshot(driver, result.getName());
                if (screenshotPath != null) {
                    Reporter.log("Saved screenshot for failed test: " + screenshotPath + "<br>");
                    Reporter.log("<a href='file://" + screenshotPath + "' target='_blank'>Open screenshot</a><br>");
                } else {
                    Reporter.log("Failed to capture screenshot for test: " + result.getName() + "<br>");
                }
            }
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        // no-op
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // no-op
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // no-op
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // no-op
    }

    @Override
    public void onStart(ITestContext context) {
        // no-op
    }

    @Override
    public void onFinish(ITestContext context) {
        // no-op
    }
}
