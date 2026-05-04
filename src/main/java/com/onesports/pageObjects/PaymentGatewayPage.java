package com.onesports.pageObjects;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.onesports.models.*;

public class PaymentGatewayPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By gatewayFrameLocator = By.cssSelector("iframe[id^='easebuzz-checkout-frame-v2-']");
    private By gatewayOverlayLocator = By.cssSelector("div[id^='easebuzz-container-v2-']");
    private By paymentOptionLocator = By.xpath("//*[contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'upi') or contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'net banking') or contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'credit card') or contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'debit card') or contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'pay now') or contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'make payment')]");

    // Payment mode locators
    private By creditCardModeLocator = By.xpath("//span[contains(text(), 'Credit Card')]//ancestor::div[contains(@class, 'payment-mode')]");
    private By debitCardModeLocator = By.xpath("//span[contains(text(), 'Debit Card')]//ancestor::div[contains(@class, 'payment-mode')]");
    private By upiModeLocator = By.xpath("//span[contains(text(), 'UPI')]//ancestor::div[contains(@class, 'payment-mode')]");
    private By netBankingModeLocator = By.xpath("//span[contains(text(), 'NetBanking')]//ancestor::div[contains(@class, 'payment-mode')]");
    private By walletModeLocator = By.xpath("//span[contains(text(), 'Wallets')]//ancestor::div[contains(@class, 'payment-mode')]");

    // Card payment locators
    private By cardNumberFieldLocator = By.name("cardnumber");
    private By cardholderNameLocator = By.name("cardholdername");
    private By expiryDateLocator = By.name("expirydate");
    private By cvvLocator = By.name("cvv");

    // UPI payment locators
    private By upiIdFieldLocator = By.name("upiid");
    private By upiProviderSelectLocator = By.xpath("//select[@name='upiprovider']");

    // NetBanking payment locators
    private By bankSelectLocator = By.xpath("//select[@name='bank']");

    // Wallet payment locators
    private By walletTypeSelectLocator = By.xpath("//select[@name='wallettype']");

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

    public int switchToGatewayFrame() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(gatewayFrameLocator));
        return 0;
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

    // Payment mode selection methods
    public void selectPaymentMode(PaymentMode mode) {
        switch(mode) {
            case CREDIT_CARD:
                selectCreditCardMode();
                break;
            case DEBIT_CARD:
                selectDebitCardMode();
                break;
            case UPI:
                selectUPIMode();
                break;
            case NET_BANKING:
                selectNetBankingMode();
                break;
            case WALLET:
                selectWalletMode();
                break;
        }
    }

    private void selectCreditCardMode() {
        WebElement creditCardElement = wait.until(ExpectedConditions.elementToBeClickable(creditCardModeLocator));
        creditCardElement.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(cardNumberFieldLocator));
    }

    private void selectDebitCardMode() {
        WebElement debitCardElement = wait.until(ExpectedConditions.elementToBeClickable(debitCardModeLocator));
        debitCardElement.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(cardNumberFieldLocator));
    }

    private void selectUPIMode() {
        WebElement upiElement = wait.until(ExpectedConditions.elementToBeClickable(upiModeLocator));
        upiElement.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(upiIdFieldLocator));
    }

    private void selectNetBankingMode() {
        WebElement netBankingElement = wait.until(ExpectedConditions.elementToBeClickable(netBankingModeLocator));
        netBankingElement.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(bankSelectLocator));
    }

    private void selectWalletMode() {
        WebElement walletElement = wait.until(ExpectedConditions.elementToBeClickable(walletModeLocator));
        walletElement.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(walletTypeSelectLocator));
    }

    // Card payment methods
    public void payWithCreditCard(CardPaymentData cardData) {
        selectPaymentMode(PaymentMode.CREDIT_CARD);
        enterCardDetails(cardData);
        clickPayButton();
    }

    public void payWithDebitCard(CardPaymentData cardData) {
        selectPaymentMode(PaymentMode.DEBIT_CARD);
        enterCardDetails(cardData);
        clickPayButton();
    }

    private void enterCardDetails(CardPaymentData cardData) {
        WebElement cardNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(cardNumberFieldLocator));
        cardNumberField.clear();
        cardNumberField.sendKeys(cardData.getCardNumber());

        WebElement nameField = driver.findElement(cardholderNameLocator);
        nameField.clear();
        nameField.sendKeys(cardData.getCardholderName());

        WebElement expiryField = driver.findElement(expiryDateLocator);
        expiryField.clear();
        expiryField.sendKeys(cardData.getExpiryMonth() + "/" + cardData.getExpiryYear());

        WebElement cvvField = driver.findElement(cvvLocator);
        cvvField.clear();
        cvvField.sendKeys(cardData.getCvv());
    }

    // UPI payment methods
    public void payWithUPI(UPIPaymentData upiData) {
        selectPaymentMode(PaymentMode.UPI);
        WebElement upiIdField = wait.until(ExpectedConditions.visibilityOfElementLocated(upiIdFieldLocator));
        upiIdField.clear();
        upiIdField.sendKeys(upiData.getUpiId());
        
        selectUPIProvider(upiData.getUpiProvider());
        clickPayButton();
    }

    private void selectUPIProvider(String provider) {
        WebElement providerSelect = driver.findElement(upiProviderSelectLocator);
        providerSelect.click();
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//option[contains(text(), '" + provider + "')]")
        ));
        option.click();
    }

    // NetBanking payment methods
    public void payWithNetBanking(NetBankingPaymentData bankData) {
        selectPaymentMode(PaymentMode.NET_BANKING);
        selectBank(bankData.getBankName());
        clickPayButton();
    }

    private void selectBank(String bankName) {
        WebElement bankSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(bankSelectLocator));
        bankSelect.click();
        WebElement bankOption = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//option[contains(text(), '" + bankName + "')]")
        ));
        bankOption.click();
    }

    // Wallet payment methods
    public void payWithWallet(WalletPaymentData walletData) {
        selectPaymentMode(PaymentMode.WALLET);
        selectWalletProvider(walletData.getWalletType());
        clickPayButton();
    }

    private void selectWalletProvider(String walletType) {
        WebElement walletSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(walletTypeSelectLocator));
        walletSelect.click();
        WebElement walletOption = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//option[contains(text(), '" + walletType + "')]")
        ));
        walletOption.click();
    }

    private void clickPayButton() {
        WebElement payButton = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[contains(text(), 'Pay') or contains(text(), 'Proceed')]")
        ));
        payButton.click();
    }
}
