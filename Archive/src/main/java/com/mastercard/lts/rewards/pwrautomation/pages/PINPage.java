package com.mastercard.lts.rewards.pwrautomation.pages;

import com.mastercard.lts.rewards.pwrautomation.configuration.ToastMessageReader;
import com.mastercard.lts.rewards.pwrautomation.utils.DeviceType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class PINPage extends MobilePage {

//    @AndroidFindBy(id="com.anypresence.android.push_testa:id/card_issuer")
//    WebElement cardView;
//    @AndroidFindBy(xpath = "")
//    WebElement firstPinCode;
    @AndroidFindBy(id = "com.mastercard.paywithrewards:id/passcode_help_view")
    private
    WebElement helpText;

   /* @AndroidFindBy(id = "toolbar_cancel_button")
    private
    WebElement cancelButton;*/

    private ToastMessageReader toastMessageReader = new ToastMessageReader(driver,getDeviceType(),30);

    PINPage(AppiumDriver<WebElement> driver, DeviceType deviceType, int timeout) {
        super(driver, deviceType, timeout);

    }

    @Override
    protected List<ExpectedCondition<WebElement>> initConditions() {
        List<ExpectedCondition<WebElement>> conditions = new ArrayList<>();
        conditions.add(ExpectedConditions.visibilityOfElementLocated(By.id("com.mastercard.paywithrewards:id/passcode_root")));
        return conditions;
    }

    public void enterPinCode(String code){
        for(char codeDigit:code.toCharArray()){
                driver.getKeyboard().sendKeys(String.valueOf(codeDigit));
            }
    }

    public void hitBackButton()
    {
        ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
        waitExecutionFor(5);
    }

    public void hitCancelButton()
    {
        waitUntilVisibleOf(By.id("toolbar_cancel_button"));
//        cancelButton.click();
    }

    public void hitBackSpace()
    {
        for(int i=0;i<4;i++)
        {
            ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACKSPACE);
        }
        waitExecutionFor(3);
    }


    public List<WebElement> validatePin(){
       WebElement passcode = driver.findElement(By.id("com.mastercard.paywithrewards:id/passcode_field"));
       return passcode.findElements(By.xpath("//*[contains(@text,\"-\")]"));
    }

    public boolean validatePinHelpHint(String pinHelpHint ){
        waitUntilVisibleOf(By.id("com.mastercard.paywithrewards:id/passcode_help_view"));
//        helpText.click();
//        waitUntilVisibleOf(By.xpath("//*[contains(@text,\""+pinHelpHint+"\")]"));
        return driver.findElement(By.xpath("//*[contains(@text,\""+pinHelpHint+"\")]")).isDisplayed();
    }

    public boolean verifiesToastMessage(String toastMessage) {
        return toastMessageReader.readAndVerifyToastMessage(toastMessage);
    }
}
