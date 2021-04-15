package com.mastercard.lts.rewards.pwrautomation.pages;

import com.mastercard.lts.rewards.pwrautomation.utils.DeviceType;
import com.mastercard.quality.engineering.mtaf.bindings.pages.AbstractMobilePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.function.Function;

public abstract class MobilePage extends AbstractMobilePage{

    private DeviceType deviceType;

    public MobilePage(AppiumDriver<WebElement> driver, DeviceType deviceType, int timeout) {
        super(driver,timeout);
        this.deviceType = deviceType;
        waitFor(initConditions());
    }
    protected DeviceType getDeviceType(){
        return this.deviceType;
    }

    void waitExecutionFor(long noOFSeconds){
        try {
            Thread.sleep(noOFSeconds*1000);
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void waitUntilDisappear(By by){
        WebElement waitElement = null;
        //Sets FluentWait Setup
        FluentWait<AppiumDriver> fwait = new FluentWait<AppiumDriver>(driver)
                .withTimeout(Duration.of(3, ChronoUnit.SECONDS))
                .pollingEvery(Duration.of(500,ChronoUnit.MILLIS))
                .ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class);

        //First checking to see if the loading indicator is found
        // we catch and throw no exception here in case they aren't ignored
        try {
            waitElement = fwait.until(new Function<AppiumDriver, WebElement>() {
                public WebElement apply(AppiumDriver driver) {
                return driver.findElement(by);
            }
            });
        } catch (Exception e) {
        }

        //checking if loading indicator was found and if so we wait for it to
        //disappear
        if (waitElement != null) {
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        }
    }
    void waitUntilVisibleOf(By by){
        WebElement waitElement = null;
        //Sets FluentWait Setup
        FluentWait<AppiumDriver> fwait = new FluentWait<AppiumDriver>(driver)
                .withTimeout(Duration.of(3, ChronoUnit.SECONDS))
                .pollingEvery(Duration.of(500,ChronoUnit.MILLIS))
                .ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class);

        //First checking to see if the loading indicator is found
        // we catch and throw no exception here in case they aren't ignored
        try {
            waitElement = fwait.until(new Function<AppiumDriver, WebElement>() {
                public WebElement apply(AppiumDriver driver) {
                    return driver.findElement(by);
                }
            });
        } catch (Exception e) {
        }

        //checking if loading indicator was found and if so we wait for it to
        //disappear
        if (waitElement != null) {
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        }
    }
    void sendKeysFromKeyBoard(String keys){
        driver.getKeyboard().sendKeys(keys);
    }

    public void swipeLeft(String pageId){
        waitUntilVisibleOf(By.id("com.mastercard.paywithrewards:id/tutorial_image"));
        WebElement pageBody =driver.findElementById("com.mastercard.paywithrewards:id/tutorial_body");
        Point pt = pageBody.getLocation();
        Dimension dimension=pageBody.getSize();
        int x_start = pt.getX()+dimension.getWidth()/2;
        int y_start = pt.getY()+dimension.getHeight()/2;
        int x_end = pt.getX() + dimension.getWidth()/2 + 5 ;
        int y_end = pt.getY() + dimension.getWidth()/2 + 5 ;
        TouchAction actionAndroid = new TouchAction(driver);
        actionAndroid.press(PointOption.point(x_start, y_start)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1500))).moveTo(PointOption.point(-x_end,y_end)).release().perform();

        if(getDeviceType().equals(DeviceType.ANDROID))		/* TouchAction did not work with XCUITestDriver for IOS-10 and above.So we are using IOSTouchAction */
        {
            //TouchAction actionAndroid = new TouchAction(driver);
            //actionAndroid.longPress(PointOption.point(x_start, y)).moveTo(PointOption.point(x_end,y)).release().perform();
        }
        //Temporarily commented as the page is not developed and driver.swipe is not supported in 17.3.2.0 MTAF version
        else
        {
            //IOSTouchAction action = new IOSTouchAction(driver);
            //action.press(x, y_start).moveTo(x,y_end-y_start).release().perform();
        }
    }

    public boolean isPresentAndDisplayed(By by) {
        try {
            return driver.findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    boolean switchValueOn(String value){
        return value.equalsIgnoreCase("ON");
    }

    boolean switchValueOff(String value){
        return value.equalsIgnoreCase("OFF");
    }
}
