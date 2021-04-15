package com.mastercard.lts.rewards.pwrautomation.pages;

import com.mastercard.lts.rewards.pwrautomation.configuration.ToastMessageReader;
import com.mastercard.lts.rewards.pwrautomation.utils.DeviceType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class TermsPage extends MobilePage {

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/terms_checkbox")
    private
    WebElement checkbox;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/accept_button")
    private
    WebElement acceptButton;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/decline_button")
    private
    MobileElement declineButton;

    @AndroidFindBy(id="android:id/button1")
    private
    MobileElement okButton;

    @AndroidFindBy(id="android:id/button2")
    private
    MobileElement cancelButton;

    TermsPage(AppiumDriver<WebElement> driver, DeviceType deviceType, int timeout) {
        super(driver,deviceType,timeout);
    }

    private ToastMessageReader toastMessageReader = new ToastMessageReader(driver,getDeviceType(),30);

    @Override
    protected List<ExpectedCondition<WebElement>> initConditions() {
        waitExecutionFor(3);
        List<ExpectedCondition<WebElement>> conditions = new ArrayList<>();
        conditions.add(ExpectedConditions.visibilityOf(checkbox));
        return conditions;
    }
    public void clickCheckbox(){
        waitUntilVisibleOf(By.id("com.mastercard.paywithrewards:id/terms_checkbox"));
        if(checkbox.getAttribute("checked").equals("false")){
            checkbox.click();
        }
    }
    public void clickAcceptButton(){
        waitUntilVisibleOf(By.id("com.mastercard.paywithrewards:id/accept_button"));
        acceptButton.click();
    }

    public boolean verifiesToastMessage(String toastMessage) {
        return toastMessageReader.readAndVerifyToastMessage(toastMessage);
    }

    public void clickOKButton(){
        okButton.click();
    }

    public void clickDeclineButton(){
        declineButton.click();
    }

    public boolean validateAcceptButton(){
        return acceptButton.isEnabled();
    }

    public boolean validateDeclineButton(){
        return declineButton.isEnabled();
    }
    public void clickCancelButton(){
        cancelButton.click();
    }

    public boolean validateCancelButton(){
        return cancelButton.isEnabled();
    }

    public boolean validateOKButton(){
        return okButton.isEnabled();
    }
    public String validateAlertMessage(){
        waitExecutionFor(2);
        return driver.findElement(By.id("android:id/message")).getText();
    }

}
