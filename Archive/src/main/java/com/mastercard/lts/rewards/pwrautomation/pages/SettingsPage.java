package com.mastercard.lts.rewards.pwrautomation.pages;

import com.mastercard.lts.rewards.pwrautomation.utils.DeviceType;
import com.mastercard.lts.rewards.pwrautomation.utils.Direction;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mastercard.lts.rewards.pwrautomation.utils.TestContext.getValue;

public class SettingsPage extends AbstractHomePage {
    private AndroidDriver androidDriver;

    @AndroidFindBy(xpath = "//device/view/group/group/group/text")
    WebElement bubbleText;

    @AndroidFindBy(id = "rewards_setting_title_text")
    private
    WebElement settingsTitle;

    @AndroidFindBy(id = "never_button")
    private
    WebElement noPurchasesOption;


    SettingsPage(AppiumDriver<WebElement> driver, DeviceType deviceType, int timeout) {
        super(driver, deviceType, timeout);
    }

    @AndroidFindBy(id = "pager")
    WebElement scrollView;
    @AndroidFindBy(id = "rewards_setting_header_text")
    private
    WebElement rewardsSettings;
    @AndroidFindBy(id = "com.mastercard.paywithrewards:id/app_notifications_header_text")
    WebElement communicationPreferences;
    @AndroidFindBy(id = "com.mastercard.paywithrewards:id/email_notifications_header_text")
    WebElement emailPreferences;
    @AndroidFindBy(id = "support_text")
    WebElement supportText;
    @AndroidFindBy(id = "minimum_cash")
    private
    WebElement mininumCash;
    @AndroidFindBy(id = "maximum_cash")
    private
    WebElement maximumCash;
    @AndroidFindBy(id = "com.mastercard.paywithrewards:id/buttonPanel")
    private
    WebElement closeButton;

    @AndroidFindBy(id = "com.mastercard.paywithrewards:id/redeemed_points_switch")
    public WebElement redemptionReminderSwitch;
    @AndroidFindBy(id = "com.mastercard.paywithrewards:id/rewards_reminder_switch")
    public WebElement rewardsReminderSwitch;
    @AndroidFindBy(id = "com.mastercard.paywithrewards:id/ineligible_purchase_switch")
    public WebElement ineligiblePurchaseSwitch;
    @AndroidFindBy(id = "com.mastercard.paywithrewards:id/email_notifications_address")
    private WebElement emailAddress;
    @AndroidFindBy(id = "com.mastercard.paywithrewards:id/email_notification_switch")
    public WebElement emailNotificationSwitch;
    @AndroidFindBy(id = "com.mastercard.paywithrewards:id/personalized_content_switch")
    public WebElement personalisedSW;

    @AndroidFindBy(id = "android:id/button1")
    public WebElement okButton;

    @AndroidFindBy(id = "rewards_settings_help")
    private
    WebElement rewardsHelpIcon;

    @AndroidFindBy(id = "com.mastercard.paywithrewards:id/app_notification_help")
    private
    WebElement appNotificationHelpIcon;

    @AndroidFindBy(id = "com.mastercard.paywithrewards:id/email_notification_help")
    private
    WebElement emailNotificationHelpIcon;

    @AndroidFindBy(id = "purchase_minimum_help_view")
    private
    WebElement purchaseMinHelpHint;

    @AndroidFindBy(id = "purchase_maximum_help_view")
    private
    WebElement purchaseMaxHelpHint;

    @AndroidFindBy(id = "com.mastercard.paywithrewards:id/settings_content")
    private
    WebElement settingsContent;

    @AndroidFindBy(id = "rewards_setting_control_view")
    private
    WebElement rewardsSettingControlView;

    @Override
    protected List<ExpectedCondition<WebElement>> initConditions() {
        List<ExpectedCondition<WebElement>> conditions = new ArrayList<ExpectedCondition<WebElement>>();
        conditions.add(ExpectedConditions.visibilityOf(communicationPreferences));
        conditions.add(ExpectedConditions.visibilityOf(emailPreferences));
        return conditions;
    }

    public void selectTabInScrollView(String tabName) {
        WebElement scrollView = driver.findElement(By.id("tab_layout"));
        WebElement tabControl = scrollView.findElement(By.xpath("//*[contains(@text,\"" + tabName + "\")]"));
        tabControl.click();
    }

    private void findElementInScrollView(WebElement element) {
        int counter = 0;
        while (counter < 5) {
            if (isPresentAndDisplayed(element)) {
                break;
            } else {
                tinySwipe(Direction.UP);
                counter++;
            }
        }
    }

    private void findElementInScrollViewDown(WebElement element) {
        int counter = 0;
        while (counter < 5) {
            if (isPresentAndDisplayed(element)) {
                break;
            } else {
                tinySwipe(Direction.DOWN);
                waitExecutionFor(1);
                counter++;
            }
        }
    }

    public boolean rewardsSettingsSectionsExist() {
        try {
            waitUntilDisappear(By.xpath("//*[contains(@text,\"" + getValue("home.control.redeemed.msg") + "\")]"));
            return rewardsSettings.isDisplayed();
        } catch (Exception ex) {
            return false;
        }
    }

    public String sectionsInSettingsPageContains(String sectionName) {
        if (sectionName.equalsIgnoreCase(getValue("home.pwr.settings"))) {
            findElementInScrollView(rewardsSettings);
            return rewardsSettings.getText();
        } else if (sectionName.equalsIgnoreCase(getValue("home.communication.preferences"))) {
            findElementInScrollView(communicationPreferences);
            return communicationPreferences.getText();
        } else {
            findElementInScrollView(emailPreferences);
            return emailPreferences.getText();
        }
    }

    public void enterValueToMinCashField(String value) {
        waitUntilVisibleOf(By.id("minimum_cash"));
        mininumCash.click();
        sendKeysFromKeyBoard(value);
        clickDoneButton();
    }

    public void turnSwitchOn(WebElement switchElement) {
//        findElementInScrollView(switchElement);
        boolean switchOriginalValue = switchValueOn(switchElement.getText());
        if (!switchOriginalValue) {
            switchElement.click();
            //switchValueOn(switchElement.getText());
        }
    }

    public void clickSwitch(WebElement switchElement) {
        findElementInScrollView(switchElement);
        switchElement.click();
    }

    public void turnSwitchOff(WebElement switchElement) {
        findElementInScrollView(switchElement);
        boolean switchOriginalValue = switchValueOn(switchElement.getText());
        if (switchOriginalValue) {
            switchElement.click();
           // switchValueOff(switchElement.getText());
        }
    }

    public boolean getRedemptionReminderSwitchText(WebElement switchElement) {
        return switchValueOn(switchElement.getText());
    }

    public void enterEmailAddress(String emailId) {
        waitUntilVisibleOf(By.id("com.mastercard.paywithrewards:id/email_notifications_address"));
        clickEmailField();
        emailAddress.clear();
        sendKeysFromKeyBoard(emailId);
        clickDoneButton();
    }

    public void clickDoneButton() {
        Map<String, Object> params = new HashMap<>();
        params.put("label", "Done");
        driver.executeScript("mobile:button-text:click", params);
    }

    public String validateInvalidEmailMessage(String invalidEmail) {
        return driver.findElement(By.xpath("//*[contains(@text,\"" + invalidEmail + "\")]")).getText();
    }

    public void clickCloseButton() {
        closeButton.findElement(By.id("android:id/button1")).click();
    }

    public String clickPurchaseButton(String text) {
        try {
            WebElement purchaseButton = driver.findElement(By.xpath("//*[contains(@text,\"" + text + "\")]"));
            purchaseButton.click();
            waitExecutionFor(4);
            return purchaseButton.getText();
        } catch (Exception ignored){}
        return null;
    }

    public boolean isNoPurchaseOptionIsSelected() {
        return noPurchasesOption.isSelected();
    }

    public void validateToggles() {
        redemptionReminderSwitch.isEnabled();
        redemptionReminderSwitch.click();
    }

    public void clickEmailField() {
        waitExecutionFor(2);
        emailAddress.click();
    }

    public String getMinCashFieldText() {
        return getMinMaxFieldValue(mininumCash.getText());
    }

    public String getCurrencySymbolText() {
        return mininumCash.getText();
    }

    public String getMaxCashFieldText() {
        return getMinMaxFieldValue(maximumCash.getText());
    }

    public String getEmailAddress() {
        return emailAddress.getText();
    }

    private void tinySwipe(Direction direction) {
        Point scrollViewPoint = settingsContent.getLocation();
        Dimension size = settingsContent.getSize();
        int x = scrollViewPoint.getX() + size.getWidth() / 2;
        int y_down = scrollViewPoint.getY() + (int) (size.getHeight() * 0.5);
        int y_up = scrollViewPoint.getY() + (int) (size.getHeight() * 0.2);
        TouchAction actionAndroid = new TouchAction(driver);
        actionAndroid.longPress(PointOption.point(x, direction == Direction.DOWN ? y_up : y_down)).moveTo(PointOption.point(x, direction == Direction.DOWN ? y_down : y_up)).release().perform();

    }


    public void swipeDownToSeeRewardsTitle() {
        findElementInScrollViewDown(settingsTitle);
    }

    public void swipeDownToSeePersonalisedSW() {
        findElementInScrollView(personalisedSW);
    }


    public void swipeUpToSeeRewardsTitle() {
        findElementInScrollViewDown(settingsTitle);
    }


    public void swipeDown() {
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.mastercard.paywithrewards:id/settings_content")));
        WebElement scrollView = driver.findElement(By.id("com.mastercard.paywithrewards:id/settings_content"));
        Dimension size=driver.manage().window().getSize();
        Point scrollViewPoint = scrollView.getLocation();
        int yEnd=size.getHeight()-10;
        int yStart=scrollViewPoint.getY()+5;
        int x=(size.width-scrollViewPoint.x)/2;
        TouchAction actionAndroid = new TouchAction(driver);
        actionAndroid.press(PointOption.point(x, yStart)).moveTo(PointOption.point(x,yEnd)).release().perform();

    }

    public void swipeUp() {
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.mastercard.paywithrewards:id/settings_content")));
        WebElement scrollView = driver.findElement(By.id("com.mastercard.paywithrewards:id/settings_content"));
        Dimension size=driver.manage().window().getSize();
        Point scrollViewPoint = scrollView.getLocation();
        int yEnd = size.getHeight() + 15;
        int yStart = scrollViewPoint.getY() + 5;
        int x = (size.width - scrollViewPoint.x) / 2;
        TouchAction actionAndroid = new TouchAction(driver);
        actionAndroid.press(PointOption.point(x, yStart)).moveTo(PointOption.point(x,yEnd)).release().perform();
    }




    private String getMinMaxFieldValue(String cashValue) {
        try {
            return cashValue.replaceAll("[-+^$#:,]", "");
        } catch (Exception ex) {
            return "";
        }
    }

    public void clickHintIcon(String iconName) {
    }

    public void clickCommPreferenceHelpHintIcon() {
        appNotificationHelpIcon.click();
    }
    public void clickEmailNotificationHelpHintIcon() {
        waitExecutionFor(10);
        emailNotificationHelpIcon.click();
    }

    public void clickSlideInMenuWithName(String menuName) {
        try {
            burgerMenu.click();
            waitFor(ExpectedConditions.visibilityOfElementLocated(By.id("navigation_menu_list")));
            WebElement menuItem = slideMenu.findElement(By.xpath("//*[@text='" + menuName + "']"));
            menuItem.click();

        } catch (Exception ex) {

        }
    }

    public void selectControlWithText(String controlName){
        WebElement control = rewardsSettingControlView.findElement(By.xpath("//*[contains(@text,\""+controlName+"\")]"));
        control.click();
    }
}
