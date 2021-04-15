package com.mastercard.lts.rewards.pwrautomation.device;

public interface DeviceCommand {
    void killApp();
    void relaunchApp();
    void turnAirplaneMode(boolean on) throws InterruptedException;
    void changeLanguage() throws InterruptedException;
}
