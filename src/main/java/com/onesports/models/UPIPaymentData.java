package com.onesports.models;

public class UPIPaymentData {
    private String upiId;
    private String upiProvider; // GOOGLE_PAY, PHONEPE, PAYTM, etc.

    public UPIPaymentData(String upiId, String upiProvider) {
        this.upiId = upiId;
        this.upiProvider = upiProvider;
    }

    public String getUpiId() {
        return upiId;
    }

    public String getUpiProvider() {
        return upiProvider;
    }
}
