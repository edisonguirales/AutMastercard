package com.mastercard.lts.rewards.pwrautomation.enums;


import com.mastercard.lts.rewards.pwrautomation.exception.InvalidRequest;

public enum AuthProcessingCode {
    PURCHASE("00"),
    PURCHASE_WITH_CASHBACK("09");

    private final String value;

    AuthProcessingCode(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static AuthProcessingCode fromValue(String v) {
        for (AuthProcessingCode processingCode : AuthProcessingCode.values()) {
            if (processingCode.value.equalsIgnoreCase(v)) {
                return processingCode;
            }
        }
        throw new InvalidRequest("processingCode must be either PURCHASE or PURCHASE_WITH_CASHBACK");
    }
}
