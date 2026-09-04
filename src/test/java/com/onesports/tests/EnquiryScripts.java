package com.onesports.tests;

import static com.onesports.base.BaseTest.driver;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.onesports.base.BaseTest;
import com.onesports.dataproviders.TestData;
import com.onesports.models.UserData;
import com.onesports.pageObjects.EnquiryPage;
import com.onesports.resources.ConfigManager;

public class EnquiryScripts extends BaseTest {

    @Test(priority = 1)
    public void verifyEnquiryPageLoads() {
        getDriver().get(ConfigManager.getStudentEnquiryUrl());
        EnquiryPage enquiryPage = new EnquiryPage(getDriver());
        Assert.assertTrue(enquiryPage.isEnquiryFormVisible(), "Enquiry form is not visible");
    }

    @Test(priority = 2, dataProvider = "enquiryData", dataProviderClass = TestData.class)
    public void submitEnquiryScript(UserData user) throws InterruptedException {
        getDriver().get(ConfigManager.getStudentEnquiryUrl());

        EnquiryPage enquiryPage = new EnquiryPage(getDriver());
        Assert.assertTrue(enquiryPage.isEnquiryFormVisible(), "Enquiry form is not visible");

        enquiryPage.fillEnquiryDetails(user);
        enquiryPage.verifyMobileOTP(user.getMobile());
        enquiryPage.fillCaptchaFromPage();
        enquiryPage.checkAgree();

        String parentWindow = getDriver().getWindowHandle();
        enquiryPage.submitEnquiry();

        // Wait until new tab opens
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        Set<String> allWindows = getDriver().getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(parentWindow)) {
                getDriver().switchTo().window(window);
                break;
            }
        }

        wait.until(ExpectedConditions.urlContains("b-tech-cse"));
        String newTabUrl = getDriver().getCurrentUrl();
        System.out.println("New Tab URL: " + newTabUrl);
        Assert.assertTrue(newTabUrl.contains("b-tech-cse"), "Expected URL not found in new tab");

        // switch back to parent to keep environment stable
        getDriver().switchTo().window(parentWindow);
    }

    @Test(priority = 3, dataProvider = "enquiryInvalidMobileData", dataProviderClass = TestData.class)
    public void validateMobileInput(String mobile, String expectedMessage) throws InterruptedException {
        getDriver().get(ConfigManager.getStudentEnquiryUrl());
        EnquiryPage enquiryPage = new EnquiryPage(getDriver());
        Assert.assertTrue(enquiryPage.isEnquiryFormVisible(), "Enquiry form is not visible");

        // enter mobile and click verify
        enquiryPage.enterMobile(mobile);
        enquiryPage.clickVerifyMobile();

        // short wait for either OTP input to appear (valid) or remain hidden (invalid)
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        boolean otpVisible = false;
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Enter 6-digit OTP']")));
            otpVisible = true;
        } catch (Exception e) {
            otpVisible = false;
        }

        if (expectedMessage == null || expectedMessage.isEmpty()) {
            // expected valid mobile -> OTP should be visible
            Assert.assertTrue(otpVisible, "Expected OTP input to be visible for mobile: " + mobile);
        } else {
            // expected invalid -> OTP should NOT be visible
            Assert.assertFalse(otpVisible, "OTP input was visible but expected validation message: " + expectedMessage);
        }
    }
}
