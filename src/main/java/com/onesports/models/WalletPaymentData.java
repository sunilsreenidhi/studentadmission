package com.onesports.models;

public class WalletPaymentData {
    private String walletType; // GOOGLE_PAY, PHONEPE, PAYTM, MOBIKWIK, etc.
    private String walletId; // Optional: wallet account ID if needed

    public WalletPaymentData(String walletType) {
        this.walletType = walletType;
        this.walletId = null;
    }

    public WalletPaymentData(String walletType, String walletId) {
        this.walletType = walletType;
        this.walletId = walletId;
    }

    public String getWalletType() {
        return walletType;
    }

    public String getWalletId() {
        return walletId;
    }
}
