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
    test.fail(result.getThrowable());

    Object testInstance = result.getInstance();

    if (testInstance instanceof BaseTest) {

        WebDriver driver =
                ((BaseTest) testInstance).getDriver();

        if (driver != null) {

            String screenshotPath =
                    ScreenshotUtil.takeScreenshot(
                            driver,
                            result.getMethod().getMethodName());

            try {

                test.fail(
                        "Screenshot on failure",
                        MediaEntityBuilder
                                .createScreenCaptureFromPath(
                                        screenshotPath)
                                .build());

            } catch (Exception e) {

                test.fail("Unable to attach screenshot : "
                        + e.getMessage());

                e.printStackTrace();
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
