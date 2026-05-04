package com.onesports.models;

public class NetBankingPaymentData {
    private String bankName;
    private String bankCode;

    public NetBankingPaymentData(String bankName, String bankCode) {
        this.bankName = bankName;
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBankCode() {
        return bankCode;
    }
}
