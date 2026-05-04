package com.onesports.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.onesports.base.BaseTest;
import com.onesports.pageObjects.LoginPage;
import com.onesports.pageObjects.StudentDashboard;
import com.onesports.pageObjects.StudentDetails1stStep;

public class LoginTest extends BaseTest {
    
   @Test
    public void validLoginTest() {
        getDriver().get("https://suadm-stg.suh.edu.in/student/apply-now");
        LoginPage login = new LoginPage(getDriver());
        login.loginTab();
        login.login("Ankit1777549551391@yopmail.com", "Sree@123");
     //   Assert.assertTrue(login.isUserProfileVisible(), "Login failed - Profile icon not visible");
        
        StudentDashboard dashboard = new StudentDashboard(getDriver());
        dashboard.clickContinueApplication();

        StudentDetails1stStep detailsPage = new StudentDetails1stStep(getDriver());
        Assert.assertTrue(detailsPage.isStepVisible("Applicant and Program Details"), "Failed to navigate to Student Details step");
        
            }

    }

