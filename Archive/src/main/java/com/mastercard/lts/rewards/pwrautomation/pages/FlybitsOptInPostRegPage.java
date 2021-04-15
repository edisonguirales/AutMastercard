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

public class FlybitsOptInPostRegPage extends MobilePage{

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/btn_continue")
    private
    WebElement continueButton;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/checkbox_optin")
    private
    WebElement checkBoxOption;

    public FlybitsOptInPostRegPage(AppiumDriver< WebElement > driver, DeviceType deviceType, int timeout) {
        super(driver, deviceType, timeout);
    }

    @Override
    protected List< ExpectedCondition< WebElement > > initConditions() {
        List<ExpectedCondition<WebElement>> conditions = new ArrayList<>();
//        conditions.add(ExpectedConditions.visibilityOfElementLocated(By.id("com.mastercard.paywithrewards:id/opt_out_confirmation_btn")));
        conditions.add(ExpectedConditions.visibilityOfElementLocated(By.id("com.mastercard.paywithrewards:id/checkbox_optin")));
        conditions.add(ExpectedConditions.visibilityOfElementLocated(By.id("com.mastercard.paywithrewards:id/btn_continue")));
        return conditions;
    }

    public void clickContinueForFlybitsOptIn(){
        waitExecutionFor(5);
        checkBoxOption.click();
        waitExecutionFor(5);
        continueButton.click();
    }
}
