 package com.onesports.pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StudentAddress3rdStep {

    WebDriver driver;
    WebDriverWait wait;

    public StudentAddress3rdStep(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

     @FindBy(xpath = "//span[normalize-space()='Applicant and Program Details']")
    private WebElement applicantAndProgramDetailsStep;

    @FindBy(xpath = "//span[normalize-space()='Payment Tab']")
    private WebElement paymentTabStep;

    @FindBy(xpath = "//span[normalize-space()='Address Details']")
    private WebElement addressDetailsStep;

    @FindBy(xpath = "//span[normalize-space()='Academic Details']")
    private WebElement academicDetailsStep;

    @FindBy(xpath = "//span[normalize-space()='Upload Files']")
    private WebElement uploadFilesStep;

    @FindBy(xpath = "//span[normalize-space()='Declaration']")
    private WebElement declarationStep;

    public boolean isApplicantAndProgramDetailsActive() {
        return wait.until(ExpectedConditions.visibilityOf(applicantAndProgramDetailsStep)).isDisplayed();
    }

    public boolean isPaymentTabActive() {
        return wait.until(ExpectedConditions.visibilityOf(paymentTabStep)).isDisplayed();
    }

    public boolean isAddressDetailsActive() {
        return wait.until(ExpectedConditions.visibilityOf(addressDetailsStep)).isDisplayed();
    }

    public boolean isAcademicDetailsActive() {
        return wait.until(ExpectedConditions.visibilityOf(academicDetailsStep)).isDisplayed();
    }

    public boolean isUploadFilesActive() {
        return wait.until(ExpectedConditions.visibilityOf(uploadFilesStep)).isDisplayed();
    }

    public boolean isDeclarationActive() {
        return wait.until(ExpectedConditions.visibilityOf(declarationStep)).isDisplayed();
    }


    public boolean isStepVisible(String stepName) {
    return wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.xpath("//span[normalize-space()='" + stepName + "']"))).isDisplayed();
}

    
}