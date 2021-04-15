package com.mastercard.lts.rewards.pwrautomation.utils;

import org.apache.commons.lang3.StringUtils;

public enum Direction {
    DOWN,
    UP;
    public static Direction getByValue(String value) {
        if(StringUtils.isNotEmpty(value)){
            return Direction.valueOf(value.toUpperCase());
        }
        throw new IllegalArgumentException("Invalid Direction");
    }
}
