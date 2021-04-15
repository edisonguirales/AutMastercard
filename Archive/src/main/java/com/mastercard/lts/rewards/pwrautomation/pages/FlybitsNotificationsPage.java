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

public class FlybitsNotificationsPage extends MobilePage{

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/flybits_con_fragment_feed_lytNoData")
    private
    WebElement noData;

    public FlybitsNotificationsPage(AppiumDriver< WebElement > driver, DeviceType deviceType, int timeout) {
        super(driver, deviceType, timeout);
    }

    @Override
    protected List< ExpectedCondition< WebElement > > initConditions() {
        List<ExpectedCondition<WebElement>> conditions = new ArrayList<>();
        conditions.add(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/content")));
        return conditions;
    }

    public boolean verifyEmptyNotificationMessage(String noNotificationText){
        waitExecutionFor(6);
        return noData.findElement(By.xpath("//*[contains(@text,\"" + noNotificationText + "\")]")).isDisplayed();
    }

    public void clickBackButton(){
        waitExecutionFor(5);
        driver.findElement(By.xpath(" //*[@content-desc=\"Navigate up\"]")).click();
        waitExecutionFor(5);
    }

}
