package com.mastercard.lts.rewards.pwrautomation.pages;

import com.mastercard.lts.rewards.pwrautomation.utils.DeviceType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class TCPage extends MobilePage {
    @AndroidFindBy(id="country_spinner")
    @iOSFindBy(xpath = "//*[@class=\"UIATextField\"] or //XCUIElementTypeTextField")
    private MobileElement countrySelector;

    @AndroidFindBy(xpath = "//device/view//list")
    private MobileElement countryList;

    @AndroidFindBy(id="toolbar_title")
    private MobileElement toolBarText;

    @AndroidFindBy(id="continueTv")
    private MobileElement continueButton;

 /*   @AndroidFindBy(id="cancelTv")
    private MobileElement cancelButton;
*/

    TCPage(AppiumDriver<WebElement> driver, DeviceType deviceType, int timeout) {
        super(driver, deviceType, timeout);
    }
    public String getCountrySelectorText() {
        if(getDeviceType().equals(DeviceType.IOS)){
            return "";
        }else{
            return countrySelector.getText();
        }
    }
    public void clickCountrySelector(){
        countrySelector.click();
        waitUntilVisibleOf(By.xpath("//device/view//list"));
    }

    public void relaunchAppAfterHittingBackButton()
    {
        ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
        waitExecutionFor(5);
        driver.launchApp();
    }

    public String validateAppTitle(){
        return toolBarText.getText();
    }

    public String validateCountryDropdownExists(){
        return countrySelector.findElement(By.id("android:id/text1")).getText();
    }

    public String validateCountryDropdownValues(String countryName){
        WebElement listView = driver.findElementByClassName("android.widget.ListView");
        String selectedCountry = listView.findElement(By.xpath("//*[contains(@text,\""+countryName+"\")]")).getText();
        listView.findElement(By.xpath("//*[contains(@text,\""+countryName+"\")]")).click();
        return selectedCountry;
    }

    public void scrollToCountryToSelect(String countryName){
        int counter = 0;
        if(getDeviceType().equals(DeviceType.IOS)){}else {
            while (counter < 20) {
                if (driver.findElementsByXPath("//*[@text=\""+countryName+"\"]").size() > 0) {
                    driver.findElementsByXPath("//*[@text=\""+countryName+"\"]").get(0).click();
                    break;
                } else {
                    scroll();
                    waitExecutionFor(2);
                    counter++;
                }
            }
            countrySelector.isSelected();
        }
    }
    public void scrollToAnElementByText(String text) {
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector())" +
                ".scrollIntoView(new UiSelector().text(\"" + text + "\"));")).click();
    }

    private WebElement getOkButton() {
        WebElement tcAlert = driver.findElement(By.id("buttonPanel"));
        return tcAlert.findElement(By.id("android:id/button1"));
    }
    public void clickOkButton(){
        getOkButton().click();
        waitExecutionFor(2);
    }

    private void scroll(){
        Dimension size=driver.manage().window().getSize();
        int yStart=(int)(size.height*0.75);
        int yEnd=(int)(size.height*0.01);
        int x=size.width/2;

        if(getDeviceType().equals(DeviceType.IOS))		/* TouchAction did not work with XCUITestDriver for IOS-10 and above.So we are using IOSTouchAction */
        {
            IOSTouchAction action = new IOSTouchAction(driver);
            action.press(PointOption.point(x, yStart)).moveTo(PointOption.point(x,yEnd-yStart)).release().perform();
        }
        //Temporarily commented as the page is not developed and DriverConfiguration.swipe is not supported in 17.3.2.0 MTAF version
        else
        {
            TouchAction actionAndroid = new TouchAction(driver);
            actionAndroid.longPress(PointOption.point(x, yStart)).moveTo(PointOption.point(x,yEnd)).release().perform();
        }
    }
    @Override
    protected List<ExpectedCondition<WebElement>> initConditions() {
        List<ExpectedCondition<WebElement>> conditions = new ArrayList<>();
        if(getDeviceType().equals(DeviceType.IOS)){

        }else {
            conditions.add(ExpectedConditions.elementToBeClickable(countrySelector));
        }
        return conditions;
    }
    public void clickContinueBtn(){
        if(getDeviceType().equals(DeviceType.IOS)){
        }else {
             waitExecutionFor(10);
             continueButton.click();
        }
    }
}
