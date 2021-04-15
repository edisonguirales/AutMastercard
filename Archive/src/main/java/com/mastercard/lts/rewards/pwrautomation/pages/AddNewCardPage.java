package com.mastercard.lts.rewards.pwrautomation.pages;

import com.mastercard.lts.rewards.pwrautomation.utils.DeviceType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AddNewCardPage extends AbstractHomePage{

    @AndroidFindBy(id="viewPager")
    private
    WebElement viewPager;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/addNewCardBtn")
    private
    WebElement addNewCardButton;

    @AndroidFindBy(id="create_card_navigation_text")
    private
    WebElement createCardText;

    @AndroidFindBy(id="card_center_text")
    private
    WebElement cardCenterText;

    @AndroidFindBy(xpath = "//device/view//group//textfield")
    WebElement cardNumberTextField;

    @AndroidFindBy(xpath = "//device/view//group//textfield[1]")
    WebElement homeSection;

    @AndroidFindBy(id="buttonPanel")
    private
    WebElement popupButtonPanel;


    AddNewCardPage(AppiumDriver<WebElement> driver, DeviceType deviceType, int timeout) {
        super(driver, deviceType, timeout);
    }

    public void addNewCardProcess(){
        addNewCardButton.click();
        waitExecutionFor(1);
       /* cardCenterText.click();
        waitFor(ExpectedConditions.visibilityOfElementLocated(By.id("card_issuer")));*/
    }
    @Override
    protected List<ExpectedCondition<WebElement>> initConditions() {
        List<ExpectedCondition<WebElement>> conditions = new ArrayList<>();
        conditions.add(ExpectedConditions.visibilityOf(addNewCardButton));
        return conditions;
    }

    public void deletePreviouslyAddedCard(String cardNumber) {
        waitExecutionFor(5);
        if (driver.findElement(By.id("card_number")).getText().equalsIgnoreCase(cardNumber)) {
            driver.findElement(By.id("remove_button")).click();
            popupButtonPanel.findElement(By.id("button1")).click();
        } else {
            swipeRight();
            if(driver.findElement(By.id("card_number")).getText().equalsIgnoreCase(cardNumber)) {
                driver.findElement(By.id("remove_button")).click();
                waitExecutionFor(2);
                popupButtonPanel.findElement(By.id("button1")).click();
            }
        }
    }

    private void swipeRight() {
        Point pt = driver.findElement(By.id("cardBg")).getLocation();
        int xStart = pt.getX()*2;
        int yStart = pt.getY();
        int xEnd = -pt.getX()*8;
        int yEnd = pt.getY();
        TouchAction actionAndroid = new TouchAction(driver);
        actionAndroid.press(PointOption.point(xStart, yStart)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1500))).moveTo(PointOption.point(-xEnd, yEnd)).release().perform();
    }

    public void findHomeButton(){
        WebElement homeButton;
        try{
            clickSlideInMenuWithName("Home");
        }
        catch (Exception ex){
            System.out.println("Home button not found");
        }
    }
}

