package com.onesports.tests;

import static com.onesports.base.BaseTest.driver;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mongodb.internal.connection.tlschannel.util.Util;
import com.onesports.base.BaseTest;
import com.onesports.listeners.Retry;
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

    @Test(groups = {"smoke"}, retryAnalyzer = Retry.class)
    public void verifyMandatoryFieldsVisible() throws Throwable {
        getDriver().get(ConfigManager.getStudentApplyUrl());
        LoginPage login = new LoginPage(getDriver());
        login.loginTab();
        Assert.assertTrue(login.isEmailFieldVisible(), "Email field is not visible");
        Assert.assertTrue(login.isPasswordFieldVisible(), "Password field is not visible");
    }
 
   @Test(groups = {"smoke"}, retryAnalyzer = Retry.class)
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

   @Test(groups = {"smoke"})
   public void verifyLogout() throws Throwable {
        getDriver().get(ConfigManager.getStudentApplyUrl());
        LoginPage login = new LoginPage(getDriver());
        login.loginTab();
      login.login(ConfigManager.getStudentEmail(), ConfigManager.getStudentPassword());
      login.logout();
      Assert.assertTrue(login.isEmailFieldVisible(), "Logout failed - Login page not displayed");
   }

   @Test(groups = {"smoke"})
   public void verifyLoginButtonIsClickable() throws Throwable {
        getDriver().get(ConfigManager.getStudentApplyUrl());
        LoginPage login = new LoginPage(getDriver());
        login.loginTab();
        Assert.assertTrue(login.isLoginButtonClickable(), "Login failed - Login button is not clickable");
    }
  
//  @Test(groups = {"smoke"}, priority = 1)
    public void verifySessionCreatedAfterLogin() throws Throwable {
        getDriver().get(ConfigManager.getStudentApplyUrl());
        LoginPage login = new LoginPage(getDriver());
        login.loginTab();
         login.login(ConfigManager.getStudentEmail(), ConfigManager.getStudentPassword());
         Assert.assertTrue(login.LogoisDisplayed(), "Login failed - Dashboard breadcrumb not visible");

         JavascriptExecutor js =
        (JavascriptExecutor) getDriver();

   String token = (String) js.executeScript(
            "return window.localStorage.getItem('applicant_token');"
    );

    System.out.println("Applicant Token : " + token);

    Assert.assertNotNull(token,
            "Session token was not created after login");
    }
   

  //  @Test(groups = {"regression"}, priority = 2)
    public void verifyRedirectToLoginPageAfterSessionExpiry() throws Throwable {
        getDriver().get(ConfigManager.getStudentApplyUrl());
        LoginPage login = new LoginPage(getDriver());
        login.loginTab();
         login.login(ConfigManager.getStudentEmail(), ConfigManager.getStudentPassword());
         Assert.assertTrue(login.LogoisDisplayed(), "Session did not expire - Still logged in after refresh");
          JavascriptExecutor js =
        (JavascriptExecutor) getDriver();

    js.executeScript("window.localStorage.clear();");
    js.executeScript("window.sessionStorage.clear();");
         getDriver().navigate().refresh();
         Assert.assertTrue(login.isEmailFieldVisible(), "Session did not expire - Still logged in after refresh");
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

