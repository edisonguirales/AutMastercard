package com.mastercard.lts.rewards.pwrautomation.pages;

import com.mastercard.lts.rewards.pwrautomation.configuration.ToastMessageReader;
import com.mastercard.lts.rewards.pwrautomation.utils.DeviceType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class RegistrationPage extends MobilePage {
    @AndroidFindBy(id = "com.mastercard.paywithrewards:id/card_number")
    private WebElement creditCardNumberTextField;

    @AndroidFindBy(xpath = "//hierarchy//android.widget.Button[@text=\"OK\"]")
    private MobileElement okButton;

    RegistrationPage(AppiumDriver<WebElement> driver, DeviceType deviceType, int timeout) {
        super(driver, deviceType, timeout);
    }

    private ToastMessageReader toastMessageReader = new ToastMessageReader(driver,getDeviceType(),30);

    @AndroidFindBy(id = "com.mastercard.paywithrewards:id/add_new_card_help")
    private MobileElement cardHelpHint;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/message")
    private
    WebElement message;

    @Override
    protected List<ExpectedCondition<WebElement>> initConditions() {
        List<ExpectedCondition<WebElement>> conditions = new ArrayList<>();
        conditions.add(ExpectedConditions.visibilityOfElementLocated(By.id("com.mastercard.paywithrewards:id/card_issuer")));
        conditions.add(ExpectedConditions.elementToBeClickable(creditCardNumberTextField));
        return conditions;
    }

    public void enterCreditCardNumber(String creditCardNumber) {
        driver.findElement(By.className("android.widget.EditText")).click();
        for (char cardNumberDigit : creditCardNumber.toCharArray()) {
            driver.getKeyboard().sendKeys(String.valueOf(cardNumberDigit));
        }
        driver.findElement(By.id("com.mastercard.paywithrewards:id/accept_button")).click();
    }

    public String validateAlertMessage() {
        waitUntilVisibleOf(By.id("com.mastercard.paywithrewards:id/message"));
        return message.getText();
    }

    public void hitBackButton() {
        ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
    }

    public void relaunchApp() {
        driver.launchApp();
    }

    public void clickOkButton() {
        okButton.click();
    }

//    public void validateLockScreen(){
//        waitExecutionFor(3);
//       String element = driver.findElement(By.xpath("//*[contains(@text,\"The maximum number of attempts has been exceeded. Please wait until the timer expires to try again.\")]")).getText();
//       Assert.assertEquals(element,"The maximum number of attempts has been exceeded. Please wait until the timer expires to try again.");
//    }

    public boolean validateHelpHint(String helpHint) {
        waitUntilVisibleOf(By.id("com.mastercard.paywithrewards:id/add_new_card_help"));
        cardHelpHint.click();
        waitUntilVisibleOf(By.xpath("//*[contains(@text,\"" + helpHint + "\")]"));
        return driver.findElement(By.xpath("//*[contains(@text,\"" + helpHint + "\")]")).isDisplayed();
    }

    public String validateInValidCardErrorMessage() {
        return creditCardNumberTextField.getText();
    }

    public boolean verifiesToastMessage(String toastMessage) {
        return toastMessageReader.readAndVerifyToastMessage(toastMessage);
    }
}
