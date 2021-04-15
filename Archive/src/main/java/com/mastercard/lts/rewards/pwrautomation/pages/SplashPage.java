package com.mastercard.lts.rewards.pwrautomation.pages;

import com.mastercard.lts.rewards.pwrautomation.utils.DeviceType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class SplashPage extends MobilePage {

    @AndroidFindBy(id = "com.mastercard.paywithrewards:id/continue_button")
    private WebElement continueBtn;


    SplashPage(AppiumDriver<WebElement> driver, DeviceType deviceType, int timeout) {
        super(driver, deviceType, timeout);
    }

    @Override
    protected List<ExpectedCondition<WebElement>> initConditions() {
        waitExecutionFor(2);
        List<ExpectedCondition<WebElement>> conditions = new ArrayList<>();
        conditions.add(ExpectedConditions.elementToBeClickable(continueBtn));
        return conditions;
    }

    public void clickContinueBtn(){
        if(getDeviceType().equals(DeviceType.IOS)){
            //Allow Push notification for iOS
            WebElement allowButton = driver.findElement(By.xpath("//*[@label='Allow']"));
            allowButton.click();
            //driver.switchTo().alert().accept();
            //New version
            driver.findElement(By.xpath("//*[@label='Cancel']")).click();
            continueBtn.click();

        }else{
            continueBtn.click();
        }
    }

}
