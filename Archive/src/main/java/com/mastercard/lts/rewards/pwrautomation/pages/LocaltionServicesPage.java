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

public class LocaltionServicesPage extends MobilePage{

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/continue_button")
    private
    WebElement continueButton;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/skip_button")
    private
    WebElement skipButton;

    @AndroidFindBy(id="com.android.permissioncontroller:id/permission_allow_button")
    private
    WebElement allowButton;

    @AndroidFindBy(id="com.android.permissioncontroller:id/permission_deny_button")
    private
    WebElement denyButton;

    @AndroidFindBy(id="com.android.permissioncontroller:id/permission_allow_always_button")
    private
    WebElement alwayAllowButton;

    @AndroidFindBy(id="com.android.permissioncontroller:id/permission_allow_foreground_only_button")
    private
    WebElement allowWhileUsingButton;


    public LocaltionServicesPage(AppiumDriver< WebElement > driver, DeviceType deviceType, int timeout) {
        super(driver, deviceType, timeout);
    }

    @Override
    protected List< ExpectedCondition< WebElement > > initConditions() {
        List<ExpectedCondition<WebElement>> conditions = new ArrayList<>();
        conditions.add(ExpectedConditions.visibilityOfElementLocated(By.id("com.mastercard.paywithrewards:id/continue_button")));
        conditions.add(ExpectedConditions.visibilityOfElementLocated(By.id("com.mastercard.paywithrewards:id/skip_button")));
        return conditions;
    }

    public void clickContinueForLocationServices(){
        waitExecutionFor(10);
        skipButton.click();
    }
}
