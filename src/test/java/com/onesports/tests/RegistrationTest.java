package com.onesports.tests;
import static com.onesports.base.BaseTest.driver;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.onesports.base.BaseTest;
import com.onesports.models.UserData;
import com.onesports.pageObjects.PaymentGatewayPage;
import com.onesports.pageObjects.RegistrationPage;
import com.onesports.pageObjects.StudentAcademicDetails4thStep;
import com.onesports.pageObjects.StudentAddress3rdStep;
import com.onesports.pageObjects.StudentDashboard;
import com.onesports.pageObjects.StudentDeclaration6thStep;
import com.onesports.pageObjects.StudentDetails1stStep;
import com.onesports.pageObjects.StudentPayments2ndStep;
import com.onesports.pageObjects.StudentUploadFiles5thStep;
import com.onesports.resources.ConfigManager;
import com.onesports.utilities.DbUtils;
import com.onesports.utilities.PaymentUtils;
import com.onesports.utilities.Utils;
import com.sun.net.httpserver.Authenticator.Retry;
import com.onesports.dataproviders.TestData;


public class RegistrationTest extends BaseTest{

   @Test(priority = 1, groups = {"smoke"})
    public void verifyRegistrationPageLoads() {

        getDriver().get(ConfigManager.getStudentApplyUrl());
        RegistrationPage register = new RegistrationPage(getDriver());
        Assert.assertTrue(
                register.isRegisterTabVisible(),
                "Registration page not loaded"
        );
    }

      @Test(priority = 2, groups = {"smoke"})
    public void verifyMandatoryFieldsVisible() {
   getDriver().get(ConfigManager.getStudentApplyUrl());
        RegistrationPage register =
                new RegistrationPage(getDriver());
        Assert.assertTrue(register.isInitialsFieldVisible());
        Assert.assertTrue(register.isFullNameFieldVisible());
        Assert.assertTrue(register.isSurnameFieldVisible());
        Assert.assertTrue(register.isMobileFieldVisible());
        Assert.assertTrue(register.isEmailFieldVisible());
        Assert.assertTrue(register.isCityFieldVisible());
        Assert.assertTrue(register.isCourseDropdownVisible());
        Assert.assertTrue(register.isPasswordFieldVisible());
        Assert.assertTrue(register.isCaptchaFieldVisible());
        Assert.assertTrue(register.isTermsCheckboxVisible());
        Assert.assertTrue(register.isSubmitButtonVisible());
    }

    @Test(priority = 5, groups = {"smoke"})
    public void verifyCourseDropdownSelectable() {
   getDriver().get(ConfigManager.getStudentApplyUrl());

        RegistrationPage register =
                new RegistrationPage(getDriver());

        register.selectCourse("UG");

        Assert.assertEquals(
                register.getSelectedCourse(),
                "UG-Engineering"
        );
    }

@Test(priority = 6, groups = {"smoke"})
    public void verifySpecializationDependency() {
         getDriver().get(ConfigManager.getStudentApplyUrl());
        RegistrationPage register =
                new RegistrationPage(getDriver());

        register.selectCourse("UG");

        Assert.assertTrue(
                register.isSpecializationDropdownEnabled(),
                "Specialization dropdown should be enabled when UG-Engineering is selected"
        );
    }


  @Test(groups ={"smoke"}, dataProvider = "registrationData",dataProviderClass=TestData.class)
  public void registerValidNewUser(UserData user) throws Throwable
  { 

        getDriver().get(ConfigManager.getStudentApplyUrl());
           RegistrationPage reg = new RegistrationPage(getDriver());
           reg.fillBasicDetails(user);
           reg.verifyMobileOTP(user.getMobile());
           reg.completeRegistration(user);
           Assert.assertTrue(reg.isUserProfileVisible());
        }

     @Test(dataProvider = "registrationData",dataProviderClass=TestData.class)
      public void registerUserToFillApplication(UserData user ) throws Throwable 

  {   
        getDriver().get(ConfigManager.getStudentApplyUrl());
           RegistrationPage reg = new RegistrationPage(getDriver());

           reg.fillBasicDetails(user);
           reg.verifyMobileOTP(user.getMobile());
           reg.completeRegistration(user);

           StudentDashboard dashboard = new StudentDashboard(getDriver());
        dashboard.clickStartApplication();
      
         StudentDetails1stStep detailsPage = new StudentDetails1stStep(getDriver());
      Assert.assertTrue(detailsPage.isStepVisible("Applicant and Program Details"), "Failed to navigate to Student Details step");
        
         detailsPage.fillBasicDetails();   
         Assert.assertTrue(detailsPage.isStepVisible("Payment Tab"), "Failed to navigate to Payment page");
          
         // API Payment
// int paymentId = PaymentUtils.initiatePayment(getDriver());

// Thread.sleep(3000);

// // verify payment status
// String status = PaymentUtils.getPaymentStatus(paymentId);

// System.out.println(status);

// // refresh application page
// getDriver().navigate().refresh();
          StudentPayments2ndStep paymentPage = new StudentPayments2ndStep(getDriver());
        paymentPage.clickProceedToPayment();

         PaymentGatewayPage paymentGateway = new PaymentGatewayPage(getDriver());
          paymentGateway.completeNetBankingPayment();
          Set<String> windows= getDriver().getWindowHandles();
          System.out.println("All windows: " + windows);

          for(String window: windows) {
              getDriver().switchTo().window(window);
              System.out.println("window id" +window);
              System.out.println("Current window title: " + getDriver().getTitle());
              System.out.println("Current window URL: " + getDriver().getCurrentUrl());
              System.out.println("-----------------------------------");
          }


        Utils.scrollToBottomOfPage(getDriver());
          // Utils.scrollToBottomOfPageFully(getDriver());
         String paymentSuccessMessage =getDriver().findElement(By.xpath("//h4[normalize-space()='Payment Successful!'] ")).getText();
         Utils.waitForElementToBeVisible(getDriver(), By.xpath("//h4[normalize-space()='Payment Successful!']"));
         Assert.assertEquals(paymentSuccessMessage, "Payment Successful!");
         paymentPage.clickContinueApplication();

         StudentAddress3rdStep addressPage = new StudentAddress3rdStep(getDriver());
         Utils.waitForElementVisiblility(getDriver(), addressPage.getAddressHeaderElement());
        
         addressPage.presentdistrictSelect("Hyderabad");
          System.out.println("entered district");

         addressPage.enterPresentAddressLine1("Banjara Hills");
                   System.out.println("entered present address line 1");

         addressPage.enterPresentPinCode("500034");
                   System.out.println("entered present pin code");

         Utils.scrollToBottomOfPage(getDriver());
                   System.out.println("scrolled to bottom of page");

        //         addressPage.clickPermanentCheckbox();
  
         addressPage.selectPermanentState("Goa");
         addressPage.selectPermanentDistrict("North Goa");
         addressPage.selectPermanentCity("Panaji");
         addressPage.enterPermanentAddressLine1("Near Panaji Church");
         addressPage.enterPermanentPinCode("403004");
         addressPage.clickSaveAndContinue();

        StudentAcademicDetails4thStep academicDetailsPage = new StudentAcademicDetails4thStep(getDriver());
         Utils.scrollToTop(getDriver());
         academicDetailsPage.fillTenthStandardDetails("dempo", "miramar", " National Institute of Open Schooling (NIOS) ", "english", "", "2024", "", "", "", "77");
         Utils.scrollToBottomOfPageFully(getDriver());
         academicDetailsPage.clickSaveAndContinue();

         StudentUploadFiles5thStep uploadFilesPage = new StudentUploadFiles5thStep(getDriver());
         Utils.scrollToTop(getDriver());
         uploadFilesPage.uploadSignature("C:/Users/sunil/Downloads/sign.jpeg");
         uploadFilesPage.uploadTenthCertificate("C:/Users/sunil/Downloads/tenthcertificate.png");
         Utils.scrollToBottomOfPageFully(getDriver());
         uploadFilesPage.clickSaveAndContinue();

         StudentDeclaration6thStep declarationPage = new StudentDeclaration6thStep(getDriver());
         declarationPage.setDeclarationAgreement(true);
         declarationPage.clickConfirmAndSubmit(); 
      }
    }