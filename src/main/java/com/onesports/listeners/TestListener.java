package com.onesports.listeners;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.onesports.base.BaseTest;
import com.onesports.utilities.ExtentReportsTestNG;
import com.onesports.utilities.ScreenshotUtil;

public class TestListener implements ITestListener {

    private static ExtentReports extent = ExtentReportsTestNG.getReportObject();
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        // Test suite start
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
        test.log(Status.INFO, "Test started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = extentTest.get();
        test.log(Status.FAIL, result.getThrowable());

        Object testInstance = result.getInstance();
        if (testInstance instanceof BaseTest) {
            WebDriver driver = ((BaseTest) testInstance).getDriver();
            if (driver != null) {
                String screenshotPath = ScreenshotUtil.takeScreenshot(driver, result.getMethod().getMethodName());
                if (screenshotPath != null) {
                    try {
                        test.fail("Screenshot captured on failure",
                                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                    } catch (Exception e) {
                        test.fail("Screenshot could not be attached: " + e.getMessage());
                    }
                    Reporter.log("Saved screenshot for failed test: " + screenshotPath + "<br>");
                    Reporter.log("<a href='file://" + screenshotPath + "' target='_blank'>Open screenshot</a><br>");
                } else {
                    Reporter.log("Failed to capture screenshot for test: " + result.getName() + "<br>");
                }
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP, "Test skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        extentTest.get().log(Status.WARNING, "Failed but within success percentage");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
