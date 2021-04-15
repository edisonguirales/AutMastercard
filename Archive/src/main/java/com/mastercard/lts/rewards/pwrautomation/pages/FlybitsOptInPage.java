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

public class FlybitsOptInPage extends MobilePage{

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/continue_button")
    private
    WebElement continueButton;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/toolbar_right_bar_button")
    private
    WebElement notNowButton;

    public FlybitsOptInPage(AppiumDriver< WebElement > driver, DeviceType deviceType, int timeout) {
        super(driver, deviceType, timeout);
    }

    @Override
    protected List< ExpectedCondition< WebElement > > initConditions() {
        List<ExpectedCondition<WebElement>> conditions = new ArrayList<>();
        conditions.add(ExpectedConditions.visibilityOfElementLocated(By.id("com.mastercard.paywithrewards:id/continue_button")));
        conditions.add(ExpectedConditions.visibilityOfElementLocated(By.id("com.mastercard.paywithrewards:id/toolbar_right_bar_button")));
        return conditions;
    }

    public void clickContinueForFlybitsOptIn(){
        waitExecutionFor(10);
        continueButton.click();
    }
}
