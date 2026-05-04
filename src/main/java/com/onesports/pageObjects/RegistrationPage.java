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

import com.onesports.models.userData;
import com.onesports.utilities.DbUtils;
import com.onesports.utilities.Utils;


public class RegistrationPage  {
    

    private WebDriver driver;
    private WebDriverWait wait;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait  = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // -------- WebElements (with @FindBy) --------

    // Tabs
    @FindBy(css="select[formcontrolname='title']")
    private WebElement initials;
    
    @FindBy(xpath = "//div[contains(@class,'tab-text-reset')]//button[.//span[text()='Register']]")
    private WebElement registerTab;

    @FindBy(xpath = "//div[contains(@class,'tab-text-reset')]//button[.//span[text()='Login']]")
    private WebElement loginTab;

    // Name / Email
    @FindBy(css = "input[formcontrolname='firstName']")
    private WebElement fullNameInput;

    @FindBy(css = "input[formcontrolname='lastName']")
    private WebElement surnameInput;

    @FindBy(css = "input[formcontrolname='email']")
    private WebElement emailInput;

    // Mobile & Alternate mobile
    @FindBy(css = "select[formcontrolname='countryCode1']")
    private WebElement countryCode1Select;

    @FindBy(css = "input[formcontrolname='mobile']")
    private WebElement mobileInput;

    @FindBy(xpath = "//span[contains(text(),'Verify Phone')]")
    private WebElement verifyMobileBtn;

    @FindBy(css = "input[placeholder='Enter 6-digit OTP']")
    private WebElement otpInput;

    @FindBy(xpath = "//button/p[contains(text(),'Verify OTP')]")
    private WebElement otpVerifyBtn;

    
    @FindBy(css = "select[formcontrolname='countryCode2']")
    private WebElement countryCode2Select;

    @FindBy(css = "input[formcontrolname='altMobile']")
    private WebElement altMobileInput;

    // State / City
    @FindBy(css = "select[formcontrolname='state']")
    private WebElement stateSelect;

    @FindBy(css = "select[formcontrolname='city']")
    private WebElement citySelect;

    // Course & Specializations
    @FindBy(css = "select[formcontrolname='course']")
    private WebElement courseSelect;

    @FindBy(css = "select[formcontrolname='specialization']")
    private WebElement specialization1Select;

    @FindBy(css = "select[formcontrolname='specialization1']")
    private WebElement specialization2Select;

    // Password & Captcha
    @FindBy(css = "input[formcontrolname='password']")
    private WebElement passwordInput;

    @FindBy(css = "input[formcontrolname='captcha']")
    private WebElement captchaInput;

    @FindBy(xpath = "//button[i[contains(@class,'mdi-autorenew')]]")
    private WebElement captchaRefreshBtn;

     @FindBy(xpath = "//label[contains(.,'Captcha')]/following::span[contains(@class,'font-mono')][1]")
    private WebElement captchaTextSpan;


    // Agreement & Submit
    @FindBy(css = "input[formcontrolname='agree']")
    private WebElement agreeCheckbox;

    @FindBy(xpath = "//button[@type='submit']//span[contains(.,'Submit Application')]")
    private WebElement submitButtonTextSpan; // span inside button

     @FindBy(xpath = "//button[.//i[contains(@class,'mdi-account-outline')]]")
    private By studentProfileIcon; 
   
    // -------- Private helpers --------

    public boolean isUserProfileVisible() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    return wait.until(ExpectedConditions.visibilityOfElementLocated(studentProfileIcon)).isDisplayed();
}

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

    private void selectByVisibleText(WebElement element, String value) {
        WebElement el = waitForVisible(element);
        new Select(el).selectByValue(value);
    }

    // -------- Actions / Page methods --------

    public void clickRegisterTab() {
        waitForClickable(registerTab).click();
    }

    public void clickLoginTab() {
        waitForClickable(loginTab).click();
    }

     public void selectInitials(String visibleText) {
        selectByVisibleText(initials, visibleText);
    }

    // Name / Email
    public void enterFullName(String fullName) {
        type(fullNameInput, fullName);
    }

    public void enterSurname(String surname) {
        type(surnameInput, surname);
    }

    public void enterEmail(String email) {
        type(emailInput, email);
    }

    // Mobile section
    public void selectPrimaryCountryCode(String visibleText) {
        // e.g. "🇮🇳 +91"
        selectByVisibleText(countryCode1Select, visibleText);
    }

    public void enterMobile(String mobile) {
        type(mobileInput, mobile);
    }

    public void clickVerifyMobile() {
        waitForClickable(verifyMobileBtn).click();
   // verifyMobileBtn.click();
}

    public void enterOtp(String otp) {
    otpInput.clear();
    otpInput.sendKeys(otp);
}

    public void clickOtpVerify() {
    otpVerifyBtn.click();
}


    // Alternate mobile section
    public void selectAlternateCountryCode(String visibleText) {
        selectByVisibleText(countryCode2Select, visibleText);
    }

    public void enterAlternateMobile(String altMobile) {
        type(altMobileInput, altMobile);
    }

    // State / City
    public void selectState(String state) {
        selectByVisibleText(stateSelect, state);
    }

    public void selectCity(String city) {
        selectByVisibleText(citySelect, city);
    }

    // Course & Specialization
    public void selectCourse(String course) {
        selectByVisibleText(courseSelect, course);
    }

    public void selectSpecialization1(String spec1) {
        // make sure it is enabled in the test
        selectByVisibleText(specialization1Select, spec1);
    }

    public void selectSpecialization2(String spec2) {
        selectByVisibleText(specialization2Select, spec2);
    }

    // Password & Captcha
    public void enterPassword(String password) {
        type(passwordInput, password);
    }

    public void enterCaptcha(String captcha) {
        type(captchaInput, captcha);
    }

     public void fillCaptcha() {
    String code = captchaTextSpan.getText().trim();
    captchaInput.sendKeys(code);

    }

    public void refreshCaptcha() {
        waitForClickable(captchaRefreshBtn).click();
    }

    // Agreement & Submit
    public void checkAgree() {
        WebElement checkbox = waitForClickable(agreeCheckbox);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void submitApplication() {
        // click on the span’s parent button
        WebElement button = (WebElement) submitButtonTextSpan.findElement(org.openqa.selenium.By.xpath("./ancestor::button[1]"));
        waitForClickable(button).click();
    }

    public void fillBasicDetails(userData user) {
    selectInitials(user.getInitials());
    enterFullName(user.getFullName());
    enterSurname(user.getSurname());
    enterEmail(user.getEmail());
    enterMobile(user.getMobile());
    selectCity(user.getCity());
    selectCourse(user.getCourse());
    }

    public void verifyMobileOTP(String mobile)
    {
        clickVerifyMobile();
        String otp = DbUtils.getLatestOtp(mobile);
        enterOtp(otp);
        clickOtpVerify();
    }

    public void completeRegistration(userData user) throws InterruptedException {
    {     
    enterPassword(user.getPassword());
    fillCaptcha();
    checkAgree();
    Utils.scrollToBottomOfPageFully(driver);
    submitApplication();
    }
}

}
