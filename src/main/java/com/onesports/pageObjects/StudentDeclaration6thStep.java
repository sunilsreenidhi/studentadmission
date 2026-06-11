package com.onesports.pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StudentDeclaration6thStep {

    private WebDriver driver;
    private WebDriverWait wait;

    public StudentDeclaration6thStep(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[formcontrolname='applicantName']")
    private WebElement applicantNameField;

    @FindBy(css = "input[formcontrolname='parentName']")
    private WebElement parentNameField;

    @FindBy(css = "input[formcontrolname='declarationDate']")
    private WebElement declarationDateField;


    By declarationCheckboxBy = By.cssSelector("input[formcontrolname='isAgreed']");

    @FindBy(xpath = "//button[normalize-space()='Previous']")
    private WebElement previousButton;

    By confirmSubmitButtonBy = By.xpath("//button[normalize-space()='Confirm & Submit Application']");

    private WebElement waitForVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    private void waitForClickableAndClick(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    private void typeText(WebElement element, String text) {
        WebElement el = waitForVisible(element);
        el.clear();
        el.sendKeys(text);
    }

    public void enterApplicantName(String name) {
        typeText(applicantNameField, name);
    }

    public void enterParentName(String parentName) {
        typeText(parentNameField, parentName);
    }

    public void enterDeclarationDate(String date) {
        typeText(declarationDateField, date);
    }

    public void setDeclarationAgreement(boolean agreed) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(declarationCheckboxBy));
        if (el.isSelected() != agreed) {
            el.click();
        }
    }

    public void clickPrevious() {
        waitForClickableAndClick(previousButton);
    }

    public void clickConfirmAndSubmit() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmSubmitButtonBy)).click();
    }

    public void confirmAndSubmitApplication(String applicantName, String parentName, String declarationDate) {
        enterApplicantName(applicantName);
        enterParentName(parentName);
        enterDeclarationDate(declarationDate);
        setDeclarationAgreement(true);
        clickConfirmAndSubmit();
    }
}
