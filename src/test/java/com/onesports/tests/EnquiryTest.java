package com.onesports.tests;

import static com.onesports.base.BaseTest.driver;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.onesports.base.BaseTest;
import com.onesports.dataproviders.TestData;
import com.onesports.models.UserData;
import com.onesports.pageObjects.EnquiryPage;

public class EnquiryTest extends BaseTest {

    @Test(dataProvider = "enquiryData", dataProviderClass = TestData.class)
    public void submitEnquiryTest(UserData user) throws InterruptedException {
        getDriver().get("https://suadm-stg.suh.edu.in/enquiry");

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
      String newTabTitle=  getDriver().getCurrentUrl();
        System.out.println("New Tab Title: " + newTabTitle);
        Assert.assertTrue(newTabTitle.contains("b-tech-cse"), "Expected URL not found");
    }
}
