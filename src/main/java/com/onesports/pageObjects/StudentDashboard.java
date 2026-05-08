package com.onesports.pageObjects;

import java.time.Duration;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StudentDashboard {

    private WebDriver driver;
    private WebDriverWait wait;

    public StudentDashboard(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }


    // start application button
    @FindBy(xpath = "//a[contains(text(), 'Start Application')]")
    private WebElement startApplicationBtn;

    @FindBy(xpath = "//a[normalize-space()='Continue Application']")
    private WebElement continueApplicationBtn;


    public void clickStartApplication() {
        
    try {

        waitForClickable(startApplicationBtn)
                .click();
    } catch (StaleElementReferenceException e) {

        
        waitForClickable(startApplicationBtn)
                .click();
    }
    }
private WebElement waitForClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void clickContinueApplication() {
        waitForClickable(continueApplicationBtn).click();

    }
}