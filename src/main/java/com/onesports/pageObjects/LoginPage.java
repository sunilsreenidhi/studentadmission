package com.onesports.pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.onesports.utilities.Utils;

public class LoginPage {
    
    private WebDriver driver;
    private WebDriverWait wait; 
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }   

    @FindBy(css = "img[alt*='SU-MYADMIT360 Logo']")
    private WebElement loginPageLogoElement;
    
    @FindBy(xpath  ="//button[normalize-space()='Login']")
    private WebElement loginTab;

    @FindBy(css="input[formcontrolname='email']")
    private WebElement emailField;

    @FindBy(css="input[formcontrolname='password']")
    private WebElement passwordField;

    @FindBy(xpath="//button[@type='submit' and contains(., 'Login')]")
    private WebElement loginButton;

    @FindBy(xpath="//span[contains(., 'Remember Me')]")
    private WebElement rememberMeCheckbox;

    @FindBy(xpath="//button[contains(text(),'Forgot Password?')]")
    private WebElement forgotPasswordLink;

    @FindBy(xpath="//span[text()='Dashboard']")
    private WebElement dashboardBreadcrumb;

    @FindBy(xpath="//div[i[contains(@class, 'mdi-account-outline')]]")
    private WebElement studentProfileIcon;

    @FindBy(xpath="//button[.//div[text()='Logout']]")
    private WebElement logoutButton;

    public WebElement dashboardBreadcrumbVisible() {
    return dashboardBreadcrumb;
     }

    public void login(String email, String password) {
           wait.until(ExpectedConditions.refreshed(
            ExpectedConditions.visibilityOf(emailField)));  // stale element handling
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    public void loginTab() {
         wait.until(ExpectedConditions.refreshed(
            ExpectedConditions.visibilityOf(loginTab)));
        loginTab.click();
            wait.until(ExpectedConditions.refreshed(
            ExpectedConditions.visibilityOf(emailField)));
    }

    public boolean LogoisDisplayed() {
        return wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(loginPageLogoElement))).isDisplayed();
      //  Utils.waitForElementToBeVisiblee(driver, loginPageLogoElement);

    }

    public boolean isEmailFieldVisible() {
        return wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(emailField))).isDisplayed();
    }

    public boolean isPasswordFieldVisible() {
        return wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(passwordField))).isDisplayed();
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(studentProfileIcon)).click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

    public boolean isLoginButtonClickable() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(loginButton));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}