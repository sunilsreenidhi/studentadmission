package com.onesports.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.onesports.base.BaseTest;
import com.onesports.pageObjects.LoginPage;
import com.onesports.pageObjects.StudentAddress3rdStep;
import com.onesports.pageObjects.StudentDashboard;
import com.onesports.pageObjects.StudentDetails1stStep;
import com.onesports.resources.ConfigManager;
import com.onesports.utilities.DbUtils;
import com.onesports.utilities.Utils;

public class StudentDetailsTest extends BaseTest {

    @Test
    public void verifyCourseAndSpecilaizationDropdownValues()
    {
        getDriver().get(ConfigManager.getStudentApplyUrl());
         LoginPage login = new LoginPage(getDriver());
      login.login(ConfigManager.getStudentEmail(), ConfigManager.getStudentPassword());
      StudentDashboard dashboard= new StudentDashboard(getDriver());
      dashboard.clickContinueApplication();
      StudentDetails1stStep studentdetails= new StudentDetails1stStep(getDriver());
     Assert.assertEquals(studentdetails.getSelectedCourse(), "UG-Business Administration");
    Assert.assertEquals(studentdetails.getSelectedSpecialization(), "BBA");
    }

    @Test
    public void verifyPhotoAndAdharUpload()
    {
         getDriver().get(ConfigManager.getStudentApplyUrl());
         LoginPage login = new LoginPage(getDriver());
      login.login(ConfigManager.getStudentEmail(), ConfigManager.getStudentPassword());
      StudentDashboard dashboard= new StudentDashboard(getDriver());
      dashboard.clickContinueApplication();
      StudentDetails1stStep studentdetails= new StudentDetails1stStep(getDriver());

      studentdetails.uploadPhoto("C:/Users/sunil/Downloads/sunilpic.jpg");
      Assert.assertEquals(studentdetails.getUploadFileName(), "sunilpic.jpg");

      studentdetails.uploadPhoto("C:/Users/sunil/Downloads/adhar.png");
    Assert.assertEquals(studentdetails.getUploadFileName(), "adhar.png");

    }

    @Test
    public void fillMandatoryDetailsAndClickNext() throws InterruptedException
    {
          getDriver().get(ConfigManager.getStudentApplyUrl());
         LoginPage login = new LoginPage(getDriver());
      login.login(ConfigManager.getStudentEmail(), ConfigManager.getStudentPassword());
      StudentDashboard dashboard= new StudentDashboard(getDriver());
      dashboard.clickContinueApplication();
         StudentDetails1stStep detailsPage = new StudentDetails1stStep(getDriver());
        detailsPage.fillBasicDetails();
        Assert.assertTrue(detailsPage.isStepVisible("Payment Tab"));
        DbUtils.resetCompletedSteps(358);
        getDriver().navigate().refresh();
        Assert.assertTrue(detailsPage.isStepVisible("Applicant and Program Details"));

    }

    @Test
    public void verifyValidationMessagesDisplaying() throws InterruptedException
    {

        getDriver().get(ConfigManager.getStudentApplyUrl());
         LoginPage login = new LoginPage(getDriver());
      login.login(ConfigManager.getStudentEmail(), ConfigManager.getStudentPassword());
      StudentDashboard dashboard= new StudentDashboard(getDriver());
      dashboard.clickContinueApplication();
    StudentAddress3rdStep address= new StudentAddress3rdStep(getDriver());      
        address.clearAddressFields();
//        Utils.scrollToBottomOfPage(getDriver());
      //  address.clickSaveAndContinue();
        Assert.assertEquals(
            address.getAddressError(),
            "Present Address Line1 is required");

    Assert.assertEquals(
            address.getPinCodeError(),
            "Present PinCode is required");
    
}
}
