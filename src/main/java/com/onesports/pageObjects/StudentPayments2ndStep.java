package com.onesports.pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.onesports.utilities.Utils;

public class StudentPayments2ndStep {

    private WebDriver driver;
    private WebDriverWait wait;

    public StudentPayments2ndStep(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // -------- WebElements (with @FindBy) --------

    // Progress Steps
    @FindBy(xpath = "//div[contains(@class,'flex items-center justify-between')]//span[contains(text(),'Applicant and Program Details')]")
    private WebElement step1Completed;

    @FindBy(xpath = "//div[contains(@class,'flex items-center justify-between')]//span[contains(text(),'Payment Tab')]")
    private WebElement step2Active;

    @FindBy(xpath = "//div[contains(@class,'flex items-center justify-between')]//span[contains(text(),'Address Details')]")
    private WebElement step3Pending;

    @FindBy(xpath = "//div[contains(@class,'flex items-center justify-between')]//span[contains(text(),'Academic Details')]")
    private WebElement step4Pending;

    @FindBy(xpath = "//div[contains(@class,'flex items-center justify-between')]//span[contains(text(),'Upload Files')]")
    private WebElement step5Pending;

    @FindBy(xpath = "//div[contains(@class,'flex items-center justify-between')]//span[contains(text(),'Declaration')]")
    private WebElement step6Pending;

    // Applicant Information Section
    @FindBy(xpath = "//h4[contains(text(),'Applicant Information')]")
    private WebElement applicantInfoHeader;

    @FindBy(xpath = "//span[contains(text(),'Full Name')]/following-sibling::span[contains(@class,'capitalize')]")
    private WebElement fullNameValue;

    @FindBy(xpath = "//span[contains(text(),'Email')]/following-sibling::span")
    private WebElement emailValue;

    @FindBy(xpath = "//span[contains(text(),'Mobile')]/following-sibling::span")
    private WebElement mobileValue;

    // Payment Summary Section
    @FindBy(xpath = "//h3[contains(text(),'Payment Summary')]")
    private WebElement paymentSummaryHeader;

    @FindBy(xpath = "//span[contains(text(),'Application Fee')]/following-sibling::span")
    private WebElement applicationFeeValue;

    @FindBy(xpath = "//span[contains(text(),'Sub Total')]/following-sibling::span")
    private WebElement subTotalValue;

    @FindBy(xpath = "//span[contains(text(),'Payment Gateway Charges')]/following-sibling::span")
    private WebElement gatewayChargesValue;

    @FindBy(xpath = "//span[contains(text(),'Total Amount')]/following-sibling::span")
    private WebElement totalAmountValue;

    // Proceed to Payment Button
    @FindBy(xpath = "//button[.//text()[contains(.,'Proceed to Secure Payment')]]")
    private WebElement proceedToPaymentBtn;
    
    //Continue Application Button after Payment Success
    @FindBy(xpath = "//button[.//span[contains(., 'Continue Application')]]")
    private WebElement continueApplicationBtn;


    // -------- Private helpers --------

    private WebElement waitForVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    private WebElement waitForClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    private String getText(WebElement element) {
        return waitForVisible(element).getText();
    }

    // -------- Actions / Page methods --------

    public boolean isStep2Active() {
        return waitForVisible(step2Active).isDisplayed();
    }

    public String getFullName() {
        return getText(fullNameValue);
    }

    public String getEmail() {
        return getText(emailValue);
    }

    public String getMobile() {
        return getText(mobileValue);
    }

    public String getApplicationFee() {
        return getText(applicationFeeValue);
    }

    public String getSubTotal() {
        return getText(subTotalValue);
    }

    public String getGatewayCharges() {
        return getText(gatewayChargesValue);
    }

    public String getTotalAmount() {
        return getText(totalAmountValue);
    }

    public PaymentGatewayPage clickProceedToPayment() {
        waitForClickable(proceedToPaymentBtn).click();
        return new PaymentGatewayPage(driver);
    }

    public void clickContinueApplication() {
         
          waitForClickable(continueApplicationBtn);
          try {
        Thread.sleep(1000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    
    }    
     // JS click (most reliable for React apps)
    ((JavascriptExecutor) driver)
            .executeScript(
                    "arguments[0].click();",
                    continueApplicationBtn);
    }

    public boolean isPaymentSummaryVisible() {
        return waitForVisible(paymentSummaryHeader).isDisplayed();
    }

    public boolean isApplicantInfoVisible() {
        return waitForVisible(applicantInfoHeader).isDisplayed();
    }

    public boolean clickContinueApplicationButtonIsVisible() {
        try {
            return waitForClickable(continueApplicationBtn).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}