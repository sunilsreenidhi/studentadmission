package com.onesports.pageObjects;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaymentGatewayPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By gatewayFrameLocator = By.cssSelector("iframe[id^='easebuzz-checkout-frame-v2-']");
    private By gatewayOverlayLocator = By.cssSelector("div[id^='easebuzz-container-v2-']");
    private By paymentOptionLocator = By.xpath("//*[contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'upi') or contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'net banking') or contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'credit card') or contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'debit card') or contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'pay now') or contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'make payment')]");

    public PaymentGatewayPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        waitForGatewayOverlay();
        switchToGatewayFrame();
        waitForGatewayContent();
    }

    private void waitForGatewayOverlay() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(gatewayOverlayLocator));
    }

    private void switchToGatewayFrame() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(gatewayFrameLocator));
    }

    private void waitForGatewayContent() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(paymentOptionLocator));
    }

    public boolean isGatewayLoaded() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(paymentOptionLocator)) != null;
        } catch (Exception e) {
            return false;
        }
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }
}
