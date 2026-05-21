package com.onesports.tests;

import static com.onesports.base.BaseTest.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mongodb.internal.connection.tlschannel.util.Util;
import com.onesports.base.BaseTest;
import com.onesports.models.NetBankingPaymentData;
import com.onesports.models.UPIPaymentData;
import com.onesports.pageObjects.LoginPage;
import com.onesports.pageObjects.PaymentGatewayPage;
import com.onesports.pageObjects.StudentAcademicDetails4thStep;
import com.onesports.pageObjects.StudentAddress3rdStep;
import com.onesports.pageObjects.StudentDashboard;
import com.onesports.pageObjects.StudentDeclaration6thStep;
import com.onesports.pageObjects.StudentDetails1stStep;
import com.onesports.pageObjects.StudentPayments2ndStep;
import com.onesports.pageObjects.StudentUploadFiles5thStep;
import com.onesports.resources.ConfigManager;
import com.onesports.utilities.Utils;

public class LoginTest extends BaseTest {

   @Test(groups = {"smoke"})
   public void verifyLoginPageLoadsAndLogoDisplayed() throws Throwable {
        getDriver().get(ConfigManager.getStudentApplyUrl());
        LoginPage login = new LoginPage(getDriver());
        login.loginTab();
        Assert.assertTrue(login.LogoisDisplayed(), "Login failed - Dashboard breadcrumb not visible");
    }
 
   @Test(groups = {"smoke"})
   public void validLoginTestwithMobileAndPassword() throws Throwable {
        getDriver().get(ConfigManager.getStudentApplyUrl());
        LoginPage login = new LoginPage(getDriver());
        login.loginTab();
        login.login(ConfigManager.getStudentMobile(), ConfigManager.getStudentPassword());
        Assert.assertTrue(login.LogoisDisplayed());
   }

      @Test(groups = {"smoke"})
      public void validLoginTestwithEmailAndPassword() throws Throwable {
        getDriver().get(ConfigManager.getStudentApplyUrl());
        LoginPage login = new LoginPage(getDriver());
        login.loginTab();
        login.login(ConfigManager.getStudentEmail(), ConfigManager.getStudentPassword());
         Assert.assertTrue(login.LogoisDisplayed());
   }
  
  
  // @Test
    public void loginToFillApplication() throws Throwable {
        getDriver().get("https://suadm-stg.suh.edu.in/student/apply-now");
        LoginPage login = new LoginPage(getDriver());
        login.loginTab();
        login.login("9547720146", "Sree@123");
     //   Assert.assertTrue(login.isUserProfileVisible(), "Login failed - Profile icon not visible");
        
        StudentDashboard dashboard = new StudentDashboard(getDriver());
   //     dashboard.clickStartApplication();
        dashboard.clickContinueApplication();

      //    StudentDetails1stStep detailsPage = new StudentDetails1stStep(getDriver());
      // Assert.assertTrue(detailsPage.isStepVisible("Applicant and Program Details"), "Failed to navigate to Student Details step");
        
      //     detailsPage.fillBasicDetails();   
      //    Assert.assertTrue(detailsPage.isStepVisible("Payment Tab"), "Failed to navigate to Payment page");
           
          StudentPayments2ndStep paymentPage = new StudentPayments2ndStep(getDriver());
      //   paymentPage.clickProceedToPayment();

      //    PaymentGatewayPage paymentGateway = new PaymentGatewayPage(getDriver());
      //     paymentGateway.completeNetBankingPayment();
         Utils.scrollToBottomOfPageFully(getDriver());
      //    String paymentSuccessMessage =getDriver().findElement(By.xpath("//h4[normalize-space()='Payment Successful!'] ")).getText();
      //    Utils.waitForElementToBeVisible(getDriver(), By.xpath("//h4[normalize-space()='Payment Successful!']"));
      //    Assert.assertEquals(paymentSuccessMessage, "Payment Successful!");
     //     paymentPage.clickContinueApplication();


         StudentAddress3rdStep addressPage = new StudentAddress3rdStep(getDriver());
         Utils.waitForElementVisiblility(getDriver(), addressPage.getAddressHeaderElement());
         addressPage.presentdistrictSelect("Hyderabad");
         addressPage.enterPresentAddressLine1("Banjara Hills");
         addressPage.enterPresentPinCode("500034");
         Utils.scrollToBottomOfPageFully(getDriver());
         addressPage.selectPermanentState("Goa");
         addressPage.selectPermanentDistrict("North Goa");
         addressPage.selectPermanentCity("Panaji");
         addressPage.enterPermanentAddressLine1("Near Panaji Church");
         addressPage.enterPermanentPinCode("403004");
         addressPage.clickSaveAndContinue();

        StudentAcademicDetails4thStep academicDetailsPage = new StudentAcademicDetails4thStep(getDriver());
         Utils.scrollToTop(getDriver());
         academicDetailsPage.fillTenthStandardDetails("dempi", "miramar", " National Institute of Open Schooling (NIOS) ", "english", "", "2024", "", "", "", "77");
         Utils.scrollToBottomOfPageFully(getDriver());
         academicDetailsPage.clickSaveAndContinue();

         StudentUploadFiles5thStep uploadFilesPage = new StudentUploadFiles5thStep(getDriver());
         Utils.scrollToTop(getDriver());
         uploadFilesPage.uploadSignature("C:/Users/sunil/Downloads/sign.jpeg");
         Thread.sleep(2000);
         uploadFilesPage.uploadTenthCertificate("C:/Users/sunil/Downloads/tenthcertificate.png");
         Utils.scrollToBottomOfPageFully(getDriver());
         uploadFilesPage.clickSaveAndContinue();

         StudentDeclaration6thStep declarationPage = new StudentDeclaration6thStep(getDriver());
         declarationPage.setDeclarationAgreement(true);
         declarationPage.clickConfirmAndSubmit(); 
      }

    }

