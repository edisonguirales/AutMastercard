package com.mastercard.lts.rewards.pwrautomation.pages;

import com.mastercard.lts.rewards.pwrautomation.utils.DeviceType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Bean;

public abstract class AbstractHomePage extends MobilePage {
    @AndroidFindBy(id="com.mastercard.paywithrewards:id/toolbar_card_stack_button")
    private
    WebElement cardStackIcon;

    @AndroidFindBy(xpath = "//*[@class=\"android.widget.ImageButton\"]")
    WebElement burgerMenu;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/navigation_menu_list")
    WebElement slideMenu;

    public AbstractHomePage(AppiumDriver<WebElement> driver, DeviceType deviceType, int timeout) {
        super(driver, deviceType, timeout);
    }

    @Bean(name="wait")
    public WebDriverWait getWait() {
        return new WebDriverWait(driver, 30);
    }

    public void clickBurgerMenu(){
        burgerMenu.click();
    }

    public void clickAddNewCardIcon(){
        cardStackIcon.click();
    }

    public void isAddNewCardIconExists(){
        cardStackIcon.isDisplayed();
    }

    public void isNavigationMenuIconExists(){
        burgerMenu.isDisplayed();
    }

    public void clickSlideInMenuWithName(String menuName){
        try{
            clickBurgerMenu();
            selectSlideInMenuItem(menuName);

        }catch (Exception ex){

        }
    }
    public void selectSlideInMenuItem(String menuName){
        WebElement menuItem = slideMenu.findElement(By.xpath("//*[@text='"+menuName+"']"));
        menuItem.click();
    }
    public boolean slideInMenuContains(String menuName){
        try {
            waitFor(ExpectedConditions.visibilityOfElementLocated(By.id("com.mastercard.paywithrewards:id/navigation_menu_list")));
            return slideMenu.findElement(By.xpath("//*[contains(@text,\""+menuName+"\")]")).isDisplayed();
        }catch (Exception ex){
            return false;
        }
    }

    public void clickSignOut(String menuName) {
        slideMenu.findElement(By.xpath("//*[contains(@text,\"" + menuName + "\")]")).click();
    }

    boolean isPresentAndDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public boolean isPresentAndDisplayed(By by) {
        try {
            return driver.findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public void goBackToPreviousPage(){

        if(getDeviceType().equals(DeviceType.IOS)){

        }
        else{
            //android
            ((AndroidDriver)driver).pressKeyCode(AndroidKeyCode.BACK);
        }
    }

    public void scroll(){
        Dimension size=driver.manage().window().getSize();
        int y_start=(int)(size.height*0.75);
        int y_end=(int)(size.height*0.01);
        int x=size.width/2;

        if(getDeviceType().equals(DeviceType.IOS))		/* TouchAction did not work with XCUITestDriver for IOS-10 and above.So we are using IOSTouchAction */
        {
            IOSTouchAction action = new IOSTouchAction(driver);
            action.press(PointOption.point(x, y_start)).moveTo(PointOption.point(x,y_end-y_start)).release().perform();
        }
        //Temporarily commented as the page is not developed and DriverConfiguration.swipe is not supported in 17.3.2.0 MTAF version
        else
        {
            TouchAction actionAndroid = new TouchAction(driver);
            actionAndroid.longPress(PointOption.point(x, y_start)).moveTo(PointOption.point(x,y_end)).release().perform();
        }
    }

    public void swipeUp(){
        waitUntilVisibleOf(By.id("tab_layout"));
        WebElement scrollView = driver.findElement(By.id("tab_layout"));
        Dimension size=driver.manage().window().getSize();
        Point scrollViewPoint = scrollView.getLocation();
        int yStart=size.getHeight()-10;
        int yEnd=scrollViewPoint.getY()+15;
        int x=(size.width-scrollViewPoint.x)/2;
        if(getDeviceType().equals(DeviceType.IOS))		 //TouchAction did not work with XCUITestDriver for IOS-10 and above.So we are using IOSTouchAction
        {
            IOSTouchAction action = new IOSTouchAction(driver);
            action.press(PointOption.point(x, yStart)).moveTo(PointOption.point(x,yEnd-yStart)).release().perform();
        }
        else
        {
            /*TouchAction actionAndroid = new TouchAction(driver);
            actionAndroid.tap(new PointOption().withCoordinates(x, yStart)).
                    moveTo(new PointOption().withCoordinates(x,yEnd)).release().perform();*/
            TouchAction actionAndroid = new TouchAction(driver);
            actionAndroid.press(PointOption.point(x, yStart)).moveTo(PointOption.point(x,yEnd)).release().perform();
        }
    }
}
