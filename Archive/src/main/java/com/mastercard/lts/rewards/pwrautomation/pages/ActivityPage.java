package com.mastercard.lts.rewards.pwrautomation.pages;

import com.mastercard.lts.rewards.pwrautomation.configuration.ToastMessageReader;
import com.mastercard.lts.rewards.pwrautomation.utils.DeviceType;
import com.mastercard.lts.rewards.pwrautomation.utils.ScrollViewType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static com.mastercard.lts.rewards.pwrautomation.utils.TestContext.getValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActivityPage extends AbstractHomePage {

    ActivityPage(AppiumDriver<WebElement> driver, DeviceType deviceType, int timeout) {
        super(driver, deviceType, timeout);
    }

    protected String profile;

    private ToastMessageReader toastMessageReader = new ToastMessageReader(driver,getDeviceType(),30);

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/toolbar_title")
    private
    WebElement toolbarTitle;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/empty_activity_text")
    private
    WebElement emptyActivityText;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/spinner_filter")
    private
    WebElement activityFilter;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/activity_redeem_button")
    private
    WebElement redeemButton;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/redeem_button")
    private
    WebElement confirmAndCloseRedeem;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/activity_list")
    private
    WebElement activityList;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/activity_filter_view")
    private
    WebElement activityFilterView;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/activity_state_text")
    WebElement activityText;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/confirm_button")
    private
    WebElement closeConfirmBtn;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/dialog_message")
    private
    WebElement redeemConfirmationMessage;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/activity_help")
    private
    WebElement activityHelpHint;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/activity_content")
    WebElement activityContent;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/dialog_rating_button_cancel")
    WebElement cancelRatingDialog;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/edit_keyword")
    WebElement editKeyboard;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/activity_name")
    WebElement activityName;

    @Override
    protected List< ExpectedCondition< WebElement > > initConditions() {
        List<ExpectedCondition<WebElement>> conditions = new ArrayList<>();
        waitExecutionFor(5);
        conditions.add(ExpectedConditions.visibilityOf(activityHelpHint));
        return conditions;
    }

    public String getNoRecentActivityText()
    {
        return emptyActivityText.getText();
    }

    public void clickOnRedeemButton()
    {
        redeemButton.click();
    }

    public void clickActivityName()
    {
        activityName.click();
    }

    public void clickOnConfirmRedeemButton()
    {
        waitUntilVisibleOf(By.id("com.mastercard.paywithrewards:id/redeem_button"));
        confirmAndCloseRedeem.click();
    }

    public String verifiesSuccessfulRedemption() {
        waitUntilVisibleOf(By.id("dialog_content"));
        return redeemConfirmationMessage.getText();
}

    public boolean verifiesActivityList()
    {
        waitUntilVisibleOf(By.id("activity_list"));
        return activityList.isDisplayed();

    }

    public boolean verifiesToastMessage(String toastMessage) {
        return toastMessageReader.readAndVerifyToastMessage(toastMessage);
    }

    public void cancelRatingDialog() {
        if (!editKeyboard.isEnabled()) {
            cancelRatingDialog.click();
        }
    }

    public void clickActivitySelector()
    {
        waitExecutionFor(5);
        activityFilterView.findElement(By.id("com.mastercard.paywithrewards:id/spinner_filter")).click();
        waitExecutionFor(2);
    }

    public void clickLoadMoreButton()
    {
        waitExecutionFor(2);
        activityList.findElement(By.id("com.mastercard.paywithrewards:id/layout_load_more")).click();
        waitExecutionFor(2);
    }

    public void clickActivityList(ScrollViewType scrollViewType) {
            if (scrollViewType.equals(ScrollViewType.ACTIVITY)) {
                List<WebElement> elementsInList = activityList.findElements(By.className("android.widget.LinearLayout"));
                if (elementsInList.size() > 0) {
                  elementsInList.get(0).findElement(By.id("activity_state_logo")).click();
                }

            }
        }

    public String readRedeemText(String descText) {
        String transactionText = null;
        List<WebElement> elementsInList = activityList.findElements(By.className("android.widget.LinearLayout"));
        if (elementsInList.size() > 0) {
            transactionText = elementsInList.get(0).findElement(By.xpath("//*[contains(@text,\"" + descText + "\")]")).getText();
        }
        return transactionText;
    }

    public boolean verifyRedeemButtonIsDisplayed() {
        Boolean redeemButtonExists = null;
        List<WebElement> elementsInList = activityList.findElements(By.className("android.widget.LinearLayout"));
        if (elementsInList.size() > 0) {
            redeemButtonExists = redeemButton.isDisplayed();
        }
        return redeemButtonExists;
    }

    public List<String> readPopUpMessageText()
    {
        waitUntilVisibleOf(By.id("dialog_content"));
        String acceptorName = redeemConfirmationMessage.findElement(By.id("title")).getText();
        String message = redeemConfirmationMessage.findElement(By.id("dialog_message")).getText();
        List<String> popUpMessages = new ArrayList<>() ;
        popUpMessages.add(acceptorName);
        popUpMessages.add(message);
        closeConfirmBtn.click();
        return popUpMessages;
    }

    public void clickActivityHelpHintIcon(){
        activityHelpHint.click();
    }

    public void scrollToActivityToSelect(String activityName){
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector())" +
                ".scrollIntoView(new UiSelector().text(\"" + activityName + "\"));")).click();
        waitExecutionFor(3);
    }

    public boolean scrollToFindRedeemText(String activityName){
        int counter = 0;
        Boolean redeemText = false;
        if(getDeviceType().equals(DeviceType.IOS)){}else {
            while (counter < 4) {
                if (driver.findElementsByXPath("//*[@text=\""+activityName+"\"]").size() > 0) {
                    redeemText =  driver.findElementsByXPath("//*[@text=\""+activityName+"\"]").get(0).isDisplayed();
                    break;
                } else {
                    scroll();
                    waitExecutionFor(2);
                    counter++;
                }
            }
        }
        return redeemText;
    }

    public void swipeUp() {
        WebElement scrollView = driver.findElement(By.id("tab_layout"));
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.mastercard.paywithrewards:id/activity_content")));
        WebElement scrollView = driver.findElement(By.id("com.mastercard.paywithrewards:id/activity_content"));
        Dimension size=driver.manage().window().getSize();
        Point scrollViewPoint = scrollView.getLocation();
        int yEnd=size.getHeight()-10;
        int yStart=scrollViewPoint.getY()+5;
        int x=(size.width-scrollViewPoint.x)/2;
        TouchAction actionAndroid = new TouchAction(driver);
        actionAndroid.press(PointOption.point(x, yStart)).moveTo(PointOption.point(x,yEnd)).release().perform();

    }

    public void swipeDownWithNoActivity() {
            WebElement scrollView = driver.findElement(By.id("empty_activity_text"));
            Dimension size = driver.manage().window().getSize();
            Point scrollViewPoint = scrollView.getLocation();
            int yEnd = (size.getHeight() - 15);
            int yStart = (scrollViewPoint.getY() + 5);
            int x = (size.width - scrollViewPoint.x) / 2;
             TouchAction actionAndroid = new TouchAction(driver);
             actionAndroid.press(PointOption.point(x, yStart)).moveTo(PointOption.point(x, yEnd)).release().perform();
            }

    }

