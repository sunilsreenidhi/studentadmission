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

      @FindBy(css = "button i.mdi-account-outline")
    private By studentProfileIcon; 

     public boolean isUserProfileVisible() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    return wait.until(ExpectedConditions.visibilityOfElementLocated(studentProfileIcon)).isDisplayed();
}

    public void login(String email, String password) {
        Utils.waitForElementVisiblility(driver, emailField);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    public void loginTab() {
        loginTab.click();
    }

}