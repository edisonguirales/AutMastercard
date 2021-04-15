package com.mastercard.lts.rewards.pwrautomation.enums;

public enum AuthAccountIdType {
    BAN("BAN"),
    BCN("BCN"),
    RANAC("RANAC");

    private final String value;

    AuthAccountIdType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AuthAccountIdType fromValue(String v) {
        for (AuthAccountIdType userIdType : AuthAccountIdType.values()) {
            if (userIdType.value.equalsIgnoreCase(v)) {
                return userIdType;
            }
        }
        return null;
    }
}
