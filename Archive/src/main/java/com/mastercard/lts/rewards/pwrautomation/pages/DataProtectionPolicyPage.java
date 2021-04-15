package com.mastercard.lts.rewards.pwrautomation.pages;

import java.util.ArrayList;
import java.util.List;

import com.mastercard.lts.rewards.pwrautomation.utils.DeviceType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DataProtectionPolicyPage extends MobilePage{

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/save_preferences")
    private
    WebElement savePreferences;

    public DataProtectionPolicyPage(AppiumDriver< WebElement > driver, DeviceType deviceType, int timeout) {
        super(driver, deviceType, timeout);
    }

    @Override
    protected List< ExpectedCondition< WebElement > > initConditions() {
        List<ExpectedCondition<WebElement>> conditions = new ArrayList<>();
        conditions.add(ExpectedConditions.visibilityOfElementLocated(By.id("com.mastercard.paywithrewards:id/save_preferences")));
        conditions.add(ExpectedConditions.visibilityOfElementLocated(By.id("com.mastercard.paywithrewards:id/request_to_delete")));
        return conditions;
    }

    public void clickSavePreferencesButton(){
        waitUntilVisibleOf(By.id("com.mastercard.paywithrewards:id/save_preferences"));
        savePreferences.click();
    }
}
