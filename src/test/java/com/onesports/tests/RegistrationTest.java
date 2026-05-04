package com.onesports.tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.onesports.base.BaseTest;

import com.onesports.models.userData;
import com.onesports.pageObjects.RegistrationPage;
import com.onesports.utilities.DbUtils;

import com.onesports.utilities.Utils;

import com.onesports.dataproviders.TestData;


public class RegistrationTest extends BaseTest{

      @Test(dataProvider = "registrationData",dataProviderClass=TestData.class)
    public void registerUserTest(userData user ) throws InterruptedException 

  {   
        getDriver().get("https://suadm-stg.suh.edu.in/student/apply-now");
   //     driver.get(ConfigReader.getBaseURL());
           RegistrationPage reg = new RegistrationPage(getDriver());

           reg.fillBasicDetails(user);
           reg.verifyMobileOTP(user.getMobile());
           reg.completeRegistration(user);
           Assert.assertTrue(reg.isUserProfileVisible());
    }

    
    //@Test(dataProvider = "getexistingEnquiryRecord",dataProviderClass =TestData.class)
    public void registerExistingEnquiryRecordTest(String fullName,String surname,String email,
      String mobile,String altMobile,String state,String city, String course, String password) throws InterruptedException
    {
          getDriver().get("https://suadm-dev.suh.edu.in/apply-now");
           RegistrationPage reg = new RegistrationPage(getDriver());

        reg.enterFullName(fullName);
        reg.enterSurname(surname);
        reg.enterEmail(email);
        reg.enterMobile(mobile);
        reg.selectCity(city);
        reg.selectCourse(course);

        reg.clickVerifyMobile();
        String otp = DbUtils.getLatestOtp(mobile);
         System.out.println("Fetched OTP : " + otp);

          reg.enterOtp(otp);
        reg.clickOtpVerify();
        reg.enterPassword(password);
        reg.fillCaptcha();
        reg.checkAgree();
        Utils.scrollToBottomOfPageFully(getDriver());
        reg.submitApplication();
        


    }
  
}


