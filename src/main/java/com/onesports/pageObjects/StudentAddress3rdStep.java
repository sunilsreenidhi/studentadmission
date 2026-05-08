 package com.onesports.pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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

    @FindBy(xpath = "//h2[contains(text(),'Address Information')]")
    private WebElement addressHeaderElement;

    @FindBy(css = "select[formcontrolname='presentCountry']")
    private WebElement presentCountrySelect;

    @FindBy(css = "select[formcontrolname='presentState']")
    private WebElement presentStateSelect;

    @FindBy(css = "select[formcontrolname='presentDistrict']")
    private WebElement presentDistrictSelect;

    @FindBy(css = "select[formcontrolname='presentCity']")
    private WebElement presentCitySelect;

    @FindBy(css = "input[formcontrolname='presentAddressLine1']")
    private WebElement presentAddressLine1Input;

    @FindBy(css = "input[formcontrolname='presentAddressLine2']")
    private WebElement presentAddressLine2Input;

    @FindBy(css = "input[formcontrolname='presentPinCode']")
    private WebElement presentPinCodeInput;

    @FindBy(css = "input[formcontrolname='permanentSameAsPresent']")
    private WebElement permanentSameAsPresentCheckbox;

    @FindBy(css = "select[formcontrolname='permanentCountry']")
    private WebElement permanentCountrySelect;

    @FindBy(css = "select[formcontrolname='permanentState']")
    private WebElement permanentStateSelect;

    @FindBy(css = "select[formcontrolname='permanentDistrict']")
    private WebElement permanentDistrictSelect;

    @FindBy(css = "select[formcontrolname='permanentCity']")
    private WebElement permanentCitySelect;

    @FindBy(css = "input[formcontrolname='permanentAddressLine1']")
    private WebElement permanentAddressLine1Input;

    @FindBy(css = "input[formcontrolname='permanentAddressLine2']")
    private WebElement permanentAddressLine2Input;

    @FindBy(css = "input[formcontrolname='permanentPinCode']")
    private WebElement permanentPinCodeInput;

    @FindBy(xpath = "//button[normalize-space()='Previous']")
    private WebElement previousButton;

    @FindBy(xpath = "//button[contains(normalize-space(.), 'Save & Continue')]")
    private WebElement saveAndContinueButton;

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

    private void selectByVisibleText(WebElement element, String visibleText) {
        WebElement el = waitForClickable(element);
        new Select(el).selectByVisibleText(visibleText);
    }

    private void click(WebElement element) {
        waitForClickable(element).click();
    }

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

    public WebElement getAddressHeaderElement() {
    return addressHeaderElement;
}

     public void presentdistrictSelect(String district) {
        selectByVisibleText(presentDistrictSelect, district);
    }

    public void enterPresentAddressLine1(String addressLine1) {
        type(presentAddressLine1Input, addressLine1);
    }

    public void enterPresentAddressLine2(String addressLine2) {
        type(presentAddressLine2Input, addressLine2);
    }

    public void enterPresentPinCode(String pinCode) {
        type(presentPinCodeInput, pinCode);
    }

    public void selectPermanentState(String state) throws InterruptedException {
        Thread.sleep(500); // Wait for state options to load based on selected country
        selectByVisibleText(permanentStateSelect, state);
    }

     public void selectPermanentDistrict(String district) {
        selectByVisibleText(permanentDistrictSelect, district);
    }

    public void selectPermanentCity(String city) {
        selectByVisibleText(permanentCitySelect, city);
    }

     public void enterPermanentAddressLine1(String addressLine1) {
        type(permanentAddressLine1Input, addressLine1);
    }

    public void enterPermanentAddressLine2(String addressLine2) {
        type(permanentAddressLine2Input, addressLine2);
    }

    public void enterPermanentPinCode(String pinCode) {
        type(permanentPinCodeInput, pinCode);
    }

    public void clickPrevious() {
        click(previousButton);
    }

    public void clickSaveAndContinue() {
        click(saveAndContinueButton);
    }

    public boolean isStepVisible(String stepName) {
    return wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.xpath("//span[normalize-space()='" + stepName + "']"))).isDisplayed();
}



    
}