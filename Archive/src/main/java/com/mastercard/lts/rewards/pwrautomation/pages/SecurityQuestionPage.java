package com.mastercard.lts.rewards.pwrautomation.pages;

import com.mastercard.lts.rewards.pwrautomation.utils.DeviceType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class SecurityQuestionPage extends MobilePage {

    @AndroidFindBy(id = "com.mastercard.paywithrewards:id/answer")
    private
    WebElement securityAnswerTextField;

    @AndroidFindBy(id = "com.mastercard.paywithrewards:id/questions")
    private
    WebElement securityQuestion;

    @AndroidFindBy(id = "android:id/text1")
    private
    WebElement securityQuestionDropdown;

    @AndroidFindBy(id = "com.mastercard.paywithrewards:id/security_question_card_container")
    private
    WebElement securityQuestionCardContainer;

    @AndroidFindBy(id = "com.mastercard.paywithrewards:id/submit_button")
    private
    WebElement submitButton;

    @AndroidFindBy(id = "com.mastercard.paywithrewards:id/security_question_help")
    private
    WebElement securityQuestionHelp;

    @AndroidFindBy(xpath = "//hierarchy//android.widget.Button[@text=\"OK\"]")
    private MobileElement okButton;


    SecurityQuestionPage(AppiumDriver<WebElement> driver, DeviceType deviceType, int timeout) {
        super(driver, deviceType, timeout);
    }

    @Override
    protected List<ExpectedCondition<WebElement>> initConditions() {
        List<ExpectedCondition<WebElement>> conditions = new ArrayList<>();
        conditions.add(ExpectedConditions.visibilityOfElementLocated(By.id("com.mastercard.paywithrewards:id/questions")));
        return conditions;
    }

    public void clickOnSecurityQuestionHelpHint(String helpHint) {
        waitExecutionFor(3);
        securityQuestionHelp.click();
        driver.findElement(By.xpath("//*[contains(@text,\""+helpHint+"\")]")).isDisplayed();
    }

    public boolean validateSecurityQuestions(String securityQuestion){
        try{
            return driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector())" +
                    ".scrollIntoView(new UiSelector().text(\"" + securityQuestion + "\"));")).isDisplayed();
        }catch (Exception ex){
            return false;
        }
    }

    public void clickSecurityQuestionSelector() {
        waitUntilVisibleOf(By.id("com.mastercard.paywithrewards:id/questions"));
        waitExecutionFor(12);
        securityQuestionDropdown.click();
        waitExecutionFor(8);
    }

    public void selectSecurityQuestion(String selection) {
        waitUntilVisibleOf(By.className("android.widget.ListView"));
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector())" +
                ".scrollIntoView(new UiSelector().text(\"" + selection + "\"));")).click();
    }

    public void enterSecurityQuestionAnswer(String answer) {
        securityAnswerTextField.clear();
        sendKeysFromKeyBoard(answer);
        waitUntilVisibleOf(By.id("com.mastercard.paywithrewards:id/submit_button"));
        driver.hideKeyboard();
        submitButton.click();
//        waitUntilVisibleOf(By.id("tutorial_image"));
    }

    public void clickOkButton() {
        okButton.click();
    }
}
