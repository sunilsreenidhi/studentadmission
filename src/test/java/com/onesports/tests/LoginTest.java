package com.onesports.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.onesports.base.BaseTest;
import com.onesports.models.NetBankingPaymentData;
import com.onesports.models.UPIPaymentData;
import com.onesports.pageObjects.LoginPage;
import com.onesports.pageObjects.PaymentGatewayPage;
import com.onesports.pageObjects.StudentDashboard;
import com.onesports.pageObjects.StudentDetails1stStep;
import com.onesports.pageObjects.StudentPayments2ndStep;
import com.onesports.utilities.Utils;

public class LoginTest extends BaseTest {
    
   @Test
    public void validLoginTest() throws InterruptedException {
        getDriver().get("https://suadm-stg.suh.edu.in/student/apply-now");
        LoginPage login = new LoginPage(getDriver());
        login.loginTab();
        login.login("9391178280", "Sree@123");
     //   Assert.assertTrue(login.isUserProfileVisible(), "Login failed - Profile icon not visible");
        
        StudentDashboard dashboard = new StudentDashboard(getDriver());
        dashboard.clickStartApplication();

        StudentDetails1stStep detailsPage = new StudentDetails1stStep(getDriver());
        Assert.assertTrue(detailsPage.isStepVisible("Applicant and Program Details"), "Failed to navigate to Student Details step");
        
         detailsPage.uploadPhoto("C:/Users/sunil/Downloads/sunilpic.jpg");
         detailsPage.uploadAadhar("C:/Users/sunil/Downloads/adhar.png");
         detailsPage.enterDateOfBirth("01/01/2000");
         detailsPage.selectGender("Male");
         detailsPage.enterAadhaarNumber("123456789012");
         detailsPage.selectBloodGroup("A+");
         detailsPage.selectAdmissionType("Regular");
         detailsPage.selectTransportRequired("No");
         detailsPage.selectHostelRequired("No");
         detailsPage.enterFatherName("Harri");
         detailsPage.enterMotherName("Sree");
         detailsPage.clickMakePayment();
         Assert.assertTrue(detailsPage.isStepVisible("Payment Tab"), "Failed to navigate to Payment page");
           
         StudentPayments2ndStep paymentPage = new StudentPayments2ndStep(getDriver());
         paymentPage.clickProceedToPayment();
         PaymentGatewayPage paymentGateway = new PaymentGatewayPage(getDriver());

        // paymentGateway.isGatewayLoaded();
         getDriver().switchTo().frame(paymentGateway.switchToGatewayFrame());
         Assert.assertTrue(paymentGateway.isGatewayLoaded(), "Payment gateway did not load successfully");
         
  
         paymentGateway.selectPaymentMode("Net Banking");
         NetBankingPaymentData netBankingData = new NetBankingPaymentData("Axis Bank");
         paymentGateway.payWithNetBanking(netBankingData);
      
      }

    }

