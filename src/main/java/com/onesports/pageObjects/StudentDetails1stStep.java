package com.onesports.pageObjects;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.onesports.utilities.Utils;

public class StudentDetails1stStep {

    private WebDriver driver;
    private WebDriverWait wait;

    public StudentDetails1stStep(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

     @FindBy(xpath = "//span[normalize-space()='Applicant and Program Details']")
    private WebElement applicantAndProgramDetailsStep;


    @FindBy(css = "select[formcontrolname='course']")
    private WebElement courseSelect;

    @FindBy(css = "select[formcontrolname='specialization1']")
    private WebElement specialization1Select;

    @FindBy(css = "input[type='file'][accept='.pdf,image/*']")
    private List<WebElement> uploadInputs;

    @FindBy(css = "select[formcontrolname='title']")
    private WebElement titleSelect;

    @FindBy(css = "input[formcontrolname='fullName']")
    private WebElement fullNameInput;

    @FindBy(css = "input[formcontrolname='surname']")
    private WebElement surnameInput;

    @FindBy(css = "input[formcontrolname='email']")
    private WebElement emailInput;

    @FindBy(css = "input[formcontrolname='mobile']")
    private WebElement mobileInput;

    @FindBy(css = "input[formcontrolname='altMobile']")
    private WebElement altMobileInput;

    @FindBy(css = "input[formcontrolname='dateOfBirth']")
    private WebElement dateOfBirthInput;

    @FindBy(css = "select[formcontrolname='gender']")
    private WebElement genderSelect;

    @FindBy(css = "select[formcontrolname='nationality']")
    private WebElement nationalitySelect;

    @FindBy(css = "input[formcontrolname='aadhaarNumber']")
    private WebElement aadhaarNumberInput;

    @FindBy(css = "select[formcontrolname='bloodGroup']")
    private WebElement bloodGroupSelect;

    @FindBy(css = "select[formcontrolname='caste']")
    private WebElement casteSelect;

    @FindBy(css = "select[formcontrolname='religion']")
    private WebElement religionSelect;

    @FindBy(css = "select[formcontrolname='admissionType']")
    private WebElement admissionTypeSelect;

    @FindBy(css = "select[formcontrolname='transportRequired']")
    private WebElement transportRequiredSelect;

    @FindBy(css = "select[formcontrolname='hostelRequired']")
    private WebElement hostelRequiredSelect;

    @FindBy(css = "input[formcontrolname='fatherName']")
    private WebElement fatherNameInput;

    @FindBy(css = "input[formcontrolname='fatherEmail']")
    private WebElement fatherEmailInput;

    @FindBy(css = "input[formcontrolname='fatherMobile']")
    private WebElement fatherMobileInput;

    @FindBy(css = "input[formcontrolname='fatherOccupation']")
    private WebElement fatherOccupationInput;

    @FindBy(css = "input[formcontrolname='fatherAnnualIncome']")
    private WebElement fatherAnnualIncomeInput;

    @FindBy(css = "input[formcontrolname='motherName']")
    private WebElement motherNameInput;

    @FindBy(css = "input[formcontrolname='motherEmail']")
    private WebElement motherEmailInput;

    @FindBy(css = "input[formcontrolname='motherMobile']")
    private WebElement motherMobileInput;

    @FindBy(css = "input[formcontrolname='motherOccupation']")
    private WebElement motherOccupationInput;

    @FindBy(css = "input[formcontrolname='motherAnnualIncome']")
    private WebElement motherAnnualIncomeInput;

    @FindBy(css = "select[formcontrolname='guardianSelected']")
    private WebElement guardianSelectedSelect;

    @FindBy(xpath = "//button[contains(normalize-space(.), 'Make Payment')]")
    private WebElement makePaymentButton;



    private WebElement waitForVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    private WebElement waitForClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    private void type(WebElement element, String value) {
        WebElement el = waitForVisible(element);
        el.clear();
        el.sendKeys(value);
    }

    private void selectByValue(WebElement element, String value) {
        WebElement el = waitForVisible(element);
        new Select(el).selectByVisibleText(value);
    }

    public void selectCourse(String courseValue) {
        selectByValue(courseSelect, courseValue);
    }

    public void selectSpecialization1(String specializationValue) {
        selectByValue(specialization1Select, specializationValue);
    }

    public void uploadPhoto(String filePath) {
        if (uploadInputs.size() > 0) {
            uploadInputs.get(0).sendKeys(filePath);
        }
    }

    public void uploadAadhar(String filePath) {
        if (uploadInputs.size() > 1) {
            uploadInputs.get(1).sendKeys(filePath);
        }
    }

    public void selectTitle(String titleValue) {
        selectByValue(titleSelect, titleValue);
    }

    public void enterFullName(String fullName) {
        type(fullNameInput, fullName);
    }

    public void enterSurname(String surname) {
        type(surnameInput, surname);
    }

    public void enterEmail(String email) {
        type(emailInput, email);
    }

    public void enterMobile(String mobile) {
        type(mobileInput, mobile);
    }

    public void enterAlternateMobile(String alternativeMobile) {
        type(altMobileInput, alternativeMobile);
    }

    public void enterDateOfBirth(String dateOfBirth) {
        type(dateOfBirthInput, dateOfBirth);
    }

    public void selectGender(String genderValue) {
        selectByValue(genderSelect, genderValue);
    }

    public void selectNationality(String nationalityValue) {
        selectByValue(nationalitySelect, nationalityValue);
    }

    public void enterAadhaarNumber(String aadhaarNumber) {
        type(aadhaarNumberInput, aadhaarNumber);
    }

    public void selectBloodGroup(String bloodGroupValue) {
        selectByValue(bloodGroupSelect, bloodGroupValue);
    }

    public void selectCaste(String casteValue) {
        selectByValue(casteSelect, casteValue);
    }

    public void selectReligion(String religionValue) {
        selectByValue(religionSelect, religionValue);
    }

    public void selectAdmissionType(String admissionTypeValue) {
        selectByValue(admissionTypeSelect, admissionTypeValue);
    }

    public void selectTransportRequired(String transportValue) {
        selectByValue(transportRequiredSelect, transportValue);
    }

    public void selectHostelRequired(String hostelValue) {
        selectByValue(hostelRequiredSelect, hostelValue);
    }

    public void enterFatherName(String fatherName) {
        type(fatherNameInput, fatherName);
    }

    public void enterFatherEmail(String fatherEmail) {
        type(fatherEmailInput, fatherEmail);
    }

    public void enterFatherMobile(String fatherMobile) {
        type(fatherMobileInput, fatherMobile);
    }

    public void enterFatherOccupation(String fatherOccupation) {
        type(fatherOccupationInput, fatherOccupation);
    }

    public void enterFatherAnnualIncome(String fatherAnnualIncome) {
        type(fatherAnnualIncomeInput, fatherAnnualIncome);
    }

    public void enterMotherName(String motherName) {
        type(motherNameInput, motherName);
    }

    public void enterMotherEmail(String motherEmail) {
        type(motherEmailInput, motherEmail);
    }

    public void enterMotherMobile(String motherMobile) {
        type(motherMobileInput, motherMobile);
    }

    public void enterMotherOccupation(String motherOccupation) {
        type(motherOccupationInput, motherOccupation);
    }

    public void enterMotherAnnualIncome(String motherAnnualIncome) {
        type(motherAnnualIncomeInput, motherAnnualIncome);
    }

    public void selectGuardianOption(String guardianOption) {
        selectByValue(guardianSelectedSelect, guardianOption);
    }

    public void clickMakePayment() throws InterruptedException {
        Utils.scrollToBottomOfPageFully(driver);
        waitForClickable(makePaymentButton).click();
    }

         public boolean isStepVisible(String stepName) {
    return wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.xpath("//span[normalize-space()='" + stepName + "']"))).isDisplayed();
}
}