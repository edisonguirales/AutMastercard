package com.mastercard.lts.rewards.pwrautomation.pages;

import com.mastercard.lts.rewards.pwrautomation.utils.DeviceType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class TutorialPage extends MobilePage {
    @AndroidFindBy(id="com.mastercard.paywithrewards:id/tutorial_body")
    WebElement tutorialBody;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/tutorial_image")
    private
    WebElement tutorialImage;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/tutorial_done_button")
    private
    WebElement continueButton;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/skipTv")
    private
    WebElement skipButton;

    @AndroidFindBy(xpath = "//hierarchy//android.widget.Button[@text=\"OK\"]")
    private MobileElement okButton;

    TutorialPage(AppiumDriver<WebElement> driver, DeviceType deviceType, int timeout) {
        super(driver, deviceType, timeout);
    }

    @Override
    protected List<ExpectedCondition<WebElement>> initConditions() {
        waitUntilVisibleOf(By.id("com.mastercard.paywithrewards:id/tutorial_title"));
        List<ExpectedCondition<WebElement>> conditions = new ArrayList<>();
        conditions.add(ExpectedConditions.visibilityOf(tutorialImage));
        return conditions;
    }

    public void clickContinueButton(){
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOf(continueButton));
        continueButton.click();
    }

    public void clickSkipButton(){
        waitUntilVisibleOf(By.id("com.mastercard.paywithrewards:id/skipTv"));
        skipButton.click();
        waitExecutionFor(8);
    }
}
