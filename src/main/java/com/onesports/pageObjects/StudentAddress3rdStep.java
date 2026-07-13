 package com.onesports.pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

    private By permanentAddressCheckbox = By.cssSelector("input[formcontrolname='permanentSameAsPresent']");

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

    By presentAddressLine1ErrorMsg= By.xpath("//span[contains(normalize-space(.), 'Present Address Line1 is required')]");
        By presentPinCodeErrorMsg= By.xpath("//span[contains(normalize-space(.), 'Present PinCode is required')]");

    By presentAddressLine1InputBy = By.cssSelector("input[formcontrolname='presentAddressLine1']");

    By presentAddressLine2InputBy = By.cssSelector("input[formcontrolname='presentAddressLine2']");
     By presentPinCodeInputBy = By.cssSelector("input[formcontrolname='presentPinCode']");

    @FindBy(css = "input[formcontrolname='permanentSameAsPresent']")
    private WebElement permanentSameAsPresentCheckbox;

    @FindBy(css = "select[formcontrolname='permanentCountry']")
    private WebElement permanentCountrySelect;

    private By permanentStateSelect = By.cssSelector("select[formcontrolname='permanentState']");

    @FindBy(css = "select[formcontrolname='permanentDistrict']")
    private By permanentDistrictSelect = By.cssSelector("select[formcontrolname='permanentDistrict']");

     private By permanentCitySelect = By.cssSelector("select[formcontrolname='permanentCity']");

    private By permanentAddressLine1Input = By.cssSelector("input[formcontrolname='permanentAddressLine1']");
 
    private By permanentAddressLine2Input = By.cssSelector("input[formcontrolname='permanentAddressLine2']");
    private By permanentPinCodeInput = By.cssSelector("input[formcontrolname='permanentPinCode']");
 
    @FindBy(xpath = "//button[normalize-space()='Previous']")
    private WebElement previousButton;
    
    private By saveAndContinueButton = By.xpath("//button[contains(normalize-space(.), 'Save & Continue')]");


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
       wait.until(ExpectedConditions.elementToBeClickable(presentAddressLine1InputBy)).sendKeys(addressLine1);
    }

    public void enterPresentAddressLine2(String addressLine2) {
        wait.until(ExpectedConditions.elementToBeClickable(presentAddressLine2InputBy)).sendKeys(addressLine2);
    }

    public void enterPresentPinCode(String pinCode) {
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(presentPinCodeInputBy)));
        wait.until(ExpectedConditions.elementToBeClickable(presentPinCodeInputBy)).sendKeys(pinCode);
    }

    public void selectPermanentState(String state) throws InterruptedException {
         wait.until(ExpectedConditions.elementToBeClickable(permanentStateSelect));
        new Select(driver.findElement(permanentStateSelect)).selectByVisibleText(state);
    }

     public void    selectPermanentDistrict(String district) {
        wait.until(ExpectedConditions.elementToBeClickable(permanentDistrictSelect));
        new Select(driver.findElement(permanentDistrictSelect)).selectByVisibleText(district);
    }

    public void selectPermanentCity(String city) {
         wait.until(ExpectedConditions.elementToBeClickable(permanentCitySelect));
        new Select(driver.findElement(permanentCitySelect)).selectByVisibleText(city);
    }

     public void enterPermanentAddressLine1(String addressLine1) {
      WebElement element = wait.until(
            ExpectedConditions.elementToBeClickable(
                    permanentAddressLine1Input));
                    element.sendKeys(addressLine1);
    }

    public void enterPermanentAddressLine2(String addressLine2) {
         WebElement element = wait.until(
            ExpectedConditions.elementToBeClickable(
                    permanentAddressLine2Input));
                    element.sendKeys(addressLine2);
    }

    public void enterPermanentPinCode(String pinCode) {
          WebElement element = wait.until(
            ExpectedConditions.elementToBeClickable(
                    permanentPinCodeInput));
                    element.sendKeys(pinCode);
    }

    public void clickPrevious() {
        click(previousButton);
    }

    public void clickSaveAndContinue() {
          wait.until(ExpectedConditions.refreshed(
            ExpectedConditions.elementToBeClickable(
                    saveAndContinueButton)))
        .click();
    }

    public boolean isStepVisible(String stepName) {
    return wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.xpath("//span[normalize-space()='" + stepName + "']"))).isDisplayed();
}

public void clickPermanentCheckbox() {
    wait.until(ExpectedConditions.elementToBeClickable(permanentAddressCheckbox)).click();

}

public void clearField(By locator) {

    WebElement element = wait.until(
            ExpectedConditions.elementToBeClickable(locator));

    element.click();

    element.sendKeys(Keys.CONTROL + "a");

    element.sendKeys(Keys.DELETE);

    element.sendKeys(Keys.TAB);
}

public void clearAddressFields() {

    clearField(presentAddressLine1InputBy);

    clearField(presentPinCodeInputBy);
}

public String getAddressError() {

    return wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                    presentAddressLine1ErrorMsg))
            .getText()
            .trim();
}

public String getPinCodeError() {

    return wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                    presentPinCodeErrorMsg))
            .getText()
            .trim();
}

    
}