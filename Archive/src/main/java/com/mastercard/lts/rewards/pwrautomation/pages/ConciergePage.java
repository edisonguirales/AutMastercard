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

public class ConciergePage extends MobilePage{

    public ConciergePage(AppiumDriver< WebElement > driver, DeviceType deviceType, int timeout) {
        super(driver, deviceType, timeout);
    }

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/notifications")
    private
    WebElement notificationsIcon;

    @Override
    protected List< ExpectedCondition< WebElement > > initConditions() {
        List<ExpectedCondition<WebElement>> conditions = new ArrayList<>();
        conditions.add(ExpectedConditions.visibilityOfElementLocated(By.id("com.mastercard.paywithrewards:id/feedholder_fragment_content")));
        conditions.add(ExpectedConditions.visibilityOfElementLocated(By.id("com.mastercard.paywithrewards:id/notifications")));
        return conditions;
    }

    public void clickNotificationsIcon()
    {
        waitExecutionFor(2);
        notificationsIcon.click();
        waitExecutionFor(2);
    }
    public void clickBackButton(){
        waitExecutionFor(5);
        driver.findElement(By.xpath("//*[@content-desc=\"Navigate up\"]")).click();
        waitExecutionFor(5);
    }
}
