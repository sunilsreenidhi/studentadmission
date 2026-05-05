package com.onesports.tests;

import static com.onesports.base.BaseTest.driver;

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
        login.login("9213869217", "Sree@123");
     //   Assert.assertTrue(login.isUserProfileVisible(), "Login failed - Profile icon not visible");
        
        StudentDashboard dashboard = new StudentDashboard(getDriver());
   //     dashboard.clickStartApplication();
        dashboard.clickContinueApplication();

         StudentDetails1stStep detailsPage = new StudentDetails1stStep(getDriver());
      Assert.assertTrue(detailsPage.isStepVisible("Applicant and Program Details"), "Failed to navigate to Student Details step");
        
         detailsPage.fillBasicDetails();   
         Assert.assertTrue(detailsPage.isStepVisible("Payment Tab"), "Failed to navigate to Payment page");
           
         StudentPayments2ndStep paymentPage = new StudentPayments2ndStep(getDriver());
         paymentPage.clickProceedToPayment();

         PaymentGatewayPage paymentGateway = new PaymentGatewayPage(getDriver());
         paymentGateway.completeNetBankingPayment();

      
      }

    }

