package com.onesports.pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StudentUploadFiles5thStep {

      private WebDriver driver;
    private WebDriverWait wait;

    public StudentUploadFiles5thStep(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
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

    @FindBy(css = "input#signature")
    private WebElement signatureUploadInput;

    @FindBy(css = "input#x_cert")
    private WebElement tenthCertificateUploadInput;

    @FindBy(css = "input#xii_cert")
    private WebElement twelfthCertificateUploadInput;

    @FindBy(css = "input#diploma")
    private WebElement diplomaUploadInput;

    @FindBy(css = "input#scorecard")
    private WebElement scorecardUploadInput;

    @FindBy(css = "input#passport_front")
    private WebElement passportFrontUploadInput;

    @FindBy(css = "input#passport_back")
    private WebElement passportBackUploadInput;

    @FindBy(xpath = "//button[normalize-space()='Previous']")
    private WebElement previousButton;

    @FindBy(xpath = "//button[contains(normalize-space(.), 'Save & Continue')]")
    private WebElement saveAndContinueButton;

    private void uploadFile(WebElement fileInput, String filePath) {
        wait.until(ExpectedConditions.visibilityOf(fileInput));
        fileInput.sendKeys(filePath);
    }

    public void uploadSignature(String filePath) {
        uploadFile(signatureUploadInput, filePath);
    }

    public void uploadTenthCertificate(String filePath) {
        uploadFile(tenthCertificateUploadInput, filePath);
    }

    public void uploadTwelfthCertificate(String filePath) {
        uploadFile(twelfthCertificateUploadInput, filePath);
    }

    public void uploadDiplomaCertificate(String filePath) {
        uploadFile(diplomaUploadInput, filePath);
    }

    public void uploadQualifyingScorecard(String filePath) {
        uploadFile(scorecardUploadInput, filePath);
    }

    public void uploadPassportFront(String filePath) {
        uploadFile(passportFrontUploadInput, filePath);
    }

    public void uploadPassportBack(String filePath) {
        uploadFile(passportBackUploadInput, filePath);
    }

    public boolean isStepVisible(String stepName) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                org.openqa.selenium.By.xpath("//span[normalize-space()='" + stepName + "']"))).isDisplayed();
    }

    public void clickPrevious() {
        wait.until(ExpectedConditions.elementToBeClickable(previousButton)).click();
    }

    public void clickSaveAndContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(saveAndContinueButton)).click();
    }
}
    

