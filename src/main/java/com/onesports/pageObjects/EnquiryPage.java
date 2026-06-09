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

import com.mongodb.internal.connection.tlschannel.util.Util;
import com.onesports.models.UserData;
import com.onesports.utilities.DbUtils;
import com.onesports.utilities.Utils;

public class EnquiryPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public EnquiryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h2[normalize-space()='Enquiry Form']")
    private WebElement enquiryFormHeader;

    @FindBy(css = "select[formcontrolname='title']")
    private WebElement titleSelect;

    @FindBy(css = "input[formcontrolname='name']")
    private WebElement nameInput;

    @FindBy(css = "input[formcontrolname='surname']")
    private WebElement surnameInput;

    @FindBy(css = "input[formcontrolname='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//button[.//span[text()='Verify Mobile Number']]")
    private WebElement verifyMobileBtn;

    @FindBy(css = "input[placeholder='Enter 6-digit OTP']")
    private WebElement otpInput;

    @FindBy(xpath = "//button/p[contains(text(),'Verify OTP')]")
    private WebElement otpVerifyBtn;

    @FindBy(css = "select[formcontrolname='countryCode1']")
    private WebElement countryCode1Select;

    @FindBy(css = "input[formcontrolname='mobile']")
    private WebElement mobileInput;

    @FindBy(css = "select[formcontrolname='countryCode2']")
    private WebElement countryCode2Select;

    @FindBy(css = "input[formcontrolname='altMobile']")
    private WebElement altMobileInput;

    @FindBy(css = "select[formcontrolname='state']")
    private WebElement stateSelect;

    @FindBy(css = "select[formcontrolname='city']")
    private WebElement citySelect;

    @FindBy(css = "select[formcontrolname='course']")
    private WebElement courseSelect;

    @FindBy(css = "select[formcontrolname='specialization']")
    private WebElement specialization1Select;

    @FindBy(css = "select[formcontrolname='specialization1']")
    private WebElement specialization2Select;

    @FindBy(xpath = "//label[contains(.,'Captcha')]/following::span[contains(@class,'font-mono')][1]")
    private WebElement captchaTextSpan;

    @FindBy(css = "input[formcontrolname='captcha']")
    private WebElement captchaInput;

    @FindBy(xpath = "//button[@title='Generate new security code']")
    private WebElement captchaRefreshBtn;

    @FindBy(css = "input[formcontrolname='agree']")
    private WebElement agreeCheckbox;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    public boolean isEnquiryFormVisible() {
        return wait.until(ExpectedConditions.visibilityOf(enquiryFormHeader)).isDisplayed();
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
        new Select(el).selectByVisibleText(value);
    }

    public void selectTitle(String title) {
        selectByVisibleText(titleSelect, title);
    }

    public void enterName(String name) {
        type(nameInput, name);
    }

    public void enterSurname(String surname) {
        type(surnameInput, surname);
    }

    public void enterEmail(String email) {
        type(emailInput, email);
    }

    public void selectPrimaryCountryCode(String visibleText) {
        selectByVisibleText(countryCode1Select, visibleText);
    }

    public void enterMobile(String mobile) throws InterruptedException {
        Thread.sleep(1000);
        type(mobileInput, mobile);
    }

     public void clickVerifyMobile() {
        waitForClickable(verifyMobileBtn).click();
}



public void enterOtp(String otp) {
    otpInput.clear();
    otpInput.sendKeys(otp);
}

    public void clickOtpVerify() {
    otpVerifyBtn.click();
}

public void verifyMobileOTP(String mobile)
    {
        clickVerifyMobile();
        String otp = DbUtils.getLatestOtp(mobile);
        enterOtp(otp);
        clickOtpVerify();
    }

    public void selectAlternateCountryCode(String visibleText) {
        selectByVisibleText(countryCode2Select, visibleText);
    }

    public void enterAlternateMobile(String altMobile) {
        type(altMobileInput, altMobile);
    }

    public void selectState(String state) {
        selectByVisibleText(stateSelect, state);
    }

    public void selectCity(String city) {
        selectByVisibleText(citySelect, city);
    }

    public void selectCourse(String course) {
        selectByVisibleText(courseSelect, course);
    }

    public void selectSpecialization1(String specialization) {
        selectByVisibleText(specialization1Select, specialization);
    }

    public void selectSpecialization2(String specialization) {
        selectByVisibleText(specialization2Select, specialization);
    }

    public String getCaptchaText() {
        return waitForVisible(captchaTextSpan).getText().trim();
    }

    public void enterCaptcha(String captcha) {
        type(captchaInput, captcha);
    }

    public void fillCaptchaFromPage() {
        String code = getCaptchaText();
        enterCaptcha(code);
    }

    public void refreshCaptcha() {
        waitForClickable(captchaRefreshBtn).click();
    }

    public void checkAgree() {
        WebElement checkbox = waitForClickable(agreeCheckbox);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void submitEnquiry() {
        Utils.scrollTOElement(driver, submitButton);
        waitForClickable(submitButton).click();
    }

    public void fillEnquiryDetails(UserData user) throws InterruptedException {
        selectTitle(user.getInitials());
        enterName(user.getFullName());
        enterSurname(user.getSurname());
        enterEmail(user.getEmail());
        enterMobile(user.getMobile());

        if (user.getAltCode() != null && !user.getAltCode().isEmpty()) {
            selectAlternateCountryCode(user.getAltCode());
        }

        if (user.getAltMobile() != null && !user.getAltMobile().isEmpty()) {
            enterAlternateMobile(user.getAltMobile());
        }

        if (user.getState() != null && !user.getState().isEmpty()) {
            selectState(user.getState());
        }

        if (user.getCity() != null && !user.getCity().isEmpty()) {
            selectCity(user.getCity());
        }

        if (user.getCourse() != null && !user.getCourse().isEmpty()) {
            selectCourse(user.getCourse());
        }

        if (user.getSpec1() != null && !user.getSpec1().isEmpty()) {
            selectSpecialization1(user.getSpec1());
        }

        if (user.getSpec2() != null && !user.getSpec2().isEmpty()) {
            selectSpecialization2(user.getSpec2());
        }
        Utils.scrollToBottomOfPage(driver);
    }
}
