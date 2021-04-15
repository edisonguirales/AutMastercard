package com.mastercard.lts.rewards.pwrautomation.pages;

import com.mastercard.lts.rewards.pwrautomation.utils.DeviceType;
import com.mastercard.lts.rewards.pwrautomation.utils.ScrollViewType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class PromotionsPage extends AbstractHomePage {

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/offers_content")
    private
    WebElement offersContent;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/offer_image")
    private
    WebElement offerImage;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/offer_end_date")
    private
    WebElement offerEndDate;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/toolbar_cancel_button")
    private
    WebElement toolBarCancelButton;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/rewardsCalcTv")
    private
    WebElement calcButton;


    PromotionsPage(AppiumDriver<WebElement> driver, DeviceType deviceType, int timeout) {
        super(driver, deviceType, timeout);
    }

    @Override
    protected List<ExpectedCondition<WebElement>> initConditions() {
        List<ExpectedCondition<WebElement>> conditions = new ArrayList<>();
        conditions.add(ExpectedConditions.visibilityOf(offersContent));
        return conditions;
    }

    public int visibleItemsNumberInScrollView(ScrollViewType scrollViewType){
        if(scrollViewType.equals(ScrollViewType.PROMOTION)){
            return offersContent.findElements(By.className("android.widget.RelativeLayout")).size();
        }
        return 0;
    }

    public void clicksPromotion() {
        List< WebElement > elementsInList = offersContent.findElements(By.className("android.widget.RelativeLayout"));
        if (!elementsInList.isEmpty()) {
            elementsInList.get(0).click();
        }

    }

    public boolean offerImageIsDisplayed(){
        return isPresentAndDisplayed(offerImage);
    }

    public boolean offerEndDateIsDisplayed(){
        return isPresentAndDisplayed(offerEndDate);
    }

    public void clicksCancelButton(){
        toolBarCancelButton.click();
    }

    public void clickCalculatorInOfferPage(){
        waitUntilVisibleOf(By.id("com.mastercard.paywithrewards:id/rewardsCalcTv"));
        calcButton.click();
    }

    public void swipeUp() {
        WebElement scrollView = driver.findElement(By.id("com.mastercard.paywithrewards:id/offers_content"));
        Dimension size = driver.manage().window().getSize();
        Point scrollViewPoint = scrollView.getLocation();
        int yEnd = size.getHeight() + 15;
        int yStart = scrollViewPoint.getY() + 5;
        int x = (size.width - scrollViewPoint.x) / 2;
        TouchAction actionAndroid = new TouchAction(driver);
        actionAndroid.tap(new PointOption().withCoordinates(x, yStart)).
                moveTo(new PointOption().withCoordinates(x, yEnd)).release().perform();

    }

    public void swipeDown() {
        WebDriverWait wait = new WebDriverWait(driver,15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.mastercard.paywithrewards:id/offers_content")));
        WebElement scrollView = driver.findElement(By.id("com.mastercard.paywithrewards:id/offers_content"));
        Dimension size=driver.manage().window().getSize();
        Point scrollViewPoint = scrollView.getLocation();
        int yEnd=size.getHeight()-10;
        int yStart=scrollViewPoint.getY()+5;
        int x=(size.width-scrollViewPoint.x)/2;
        TouchAction actionAndroid = new TouchAction(driver);
        actionAndroid.press(PointOption.point(x, yStart)).moveTo(PointOption.point(x,yEnd)).release().perform();

    }

}

