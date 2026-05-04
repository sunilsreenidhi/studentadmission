package com.onesports.models;

public class CardPaymentData {
    private String cardNumber;
    private String cardholderName;
    private String expiryMonth;
    private String expiryYear;
    private String cvv;
    private String cardBrand; // VISA, MASTERCARD, etc.

    public CardPaymentData(String cardNumber, String cardholderName, String expiryMonth, 
                          String expiryYear, String cvv, String cardBrand) {
        this.cardNumber = cardNumber;
        this.cardholderName = cardholderName;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.cvv = cvv;
        this.cardBrand = cardBrand;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public String getExpiryMonth() {
        return expiryMonth;
    }

    public String getExpiryYear() {
        return expiryYear;
    }

    public String getCvv() {
        return cvv;
    }

    public String getCardBrand() {
        return cardBrand;
    }
}
