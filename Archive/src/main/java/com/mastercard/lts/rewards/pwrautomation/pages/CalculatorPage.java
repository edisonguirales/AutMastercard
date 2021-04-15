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
import static com.mastercard.lts.rewards.pwrautomation.utils.TestContext.getValue;
public class CalculatorPage extends AbstractHomePage{

    CalculatorPage(AppiumDriver<WebElement> driver, DeviceType deviceType, int timeout) {
        super(driver, deviceType, timeout);
    }

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/anywhere_value")
    private
    WebElement anywhereValueText;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/enter_amount_button")
    private
    WebElement enterRewardsButton;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/enter_amount_to_spend_button")
    private
    WebElement enterCashAmountButton;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/calculator_help")
    private
    WebElement helperIcon;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/custom")
    private
    WebElement customTextField;

    @AndroidFindBy(id="buttonPanel")
    private
    WebElement popupButtonPanel;

    //Offers View
    @AndroidFindBy(id="com.mastercard.paywithrewards:id/offers")
    private
    WebElement offersList;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/current_points_balance")
    private
    WebElement rewardsPoints;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/tvThumb")
    private
    WebElement sliderAmount;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/point_value_with_promo")
    private
    WebElement promoPointValue;

    public void clicksOneOffer(){
        List<WebElement> elementsInList = offersList.findElements(By.className("android.widget.LinearLayout"));
        if(!elementsInList.isEmpty()){
            elementsInList.get(0).click();
        }

    }

    @Override
    protected List<ExpectedCondition<WebElement>> initConditions() {
        List<ExpectedCondition<WebElement>> conditions = new ArrayList<>();
        conditions.add(ExpectedConditions.visibilityOf(rewardsPoints));
        return conditions;
    }

    public void enterPointAmount(String point){
        waitUntilDisappear(By.xpath("//*[contains(@text,\""+getValue("calculator.use.rewards")+"\")]"));
        enterRewardsButton.click();
        WebElement pointTextField=customTextField.findElement(By.xpath("//*[@text,\"0\"]"));
        pointTextField.sendKeys(point);
        WebElement okButton=popupButtonPanel.findElement(By.xpath("//*[@text='OK']"));
        okButton.click();
    }
    public void enterCashAmount(String cashAmount){
        enterCashAmountButton.click();
        waitExecutionFor(3);
        customTextField.sendKeys(cashAmount);
        driver.getKeyboard().sendKeys(cashAmount);
        waitExecutionFor(2);
        WebElement okButton=popupButtonPanel.findElement(By.xpath("//*[@text='OK']"));
        okButton.click();
    }

    private double getCashValue(String cashValue){
        try {
            return Double.parseDouble(cashValue.replaceAll("[^\\d.]", ""));
        }catch (Exception ex){
            return 0;
        }
    }

    public void clickHelperIcon(){
        helperIcon.click();
        waitExecutionFor(2);
    }

    public boolean isHelpHintDisplayed(String helpHint){
        return driver.findElement(By.xpath("//*[contains(@text,\""+ helpHint +"\")]")).isDisplayed();
    }

    public boolean isPromotionalPointsDisplayed(){
        return rewardsPoints.isDisplayed();
    }

    public String getAvailablePoints(){
        return rewardsPoints.getText().replaceAll("[-+^$#:,]", "");
    }

    public String getPointValueAnyWhere(){
        return anywhereValueText.getText().replaceAll("[-+^$#:,]", "");

    }

    public void isEnterRewardsButtonDisplayed(){
        enterRewardsButton.isDisplayed();
    }

    public void isEnterCashAmountButtonDisplayed(){
        enterCashAmountButton.isDisplayed();
    }

    public void verifyOffers(){
        offersList.isDisplayed();
    }

    public void selectOffer(){
        waitUntilVisibleOf(By.id("com.mastercard.paywithrewards:id/offer_image"));
        waitExecutionFor(5);
        driver.findElement(By.id("com.mastercard.paywithrewards:id/offer_image")).click();
    }

    public void updatePointValueAnyWhere(String pointValue){
        enterRewardsButton.click();
        waitUntilVisibleOf(By.id("android:id/button1"));
        driver.getKeyboard().sendKeys(pointValue);
        waitExecutionFor(3);
        driver.findElement(By.id("android:id/button1")).click();
    }


    public String getSliderAmount(){
        waitExecutionFor(2);
        return sliderAmount.getText().replaceAll("[-+^$#:,]", "");
    }

    public String getPromotionalPointsValue(){
        return promoPointValue.getText();
    }

    public void swipeSlider(String direction){
        waitExecutionFor(2);
        if(direction.equals("left")) {
            swipeLeft();
        }else {
            swipeRight();
        }
    }

    private void swipeLeft(){
        Point pt = getSliderLocation();
        int xStart = pt.getX()*7;
        int yStart = pt.getY();
        int xEnd = pt.getX()+576;
        int yEnd = pt.getY();
        moveSlider(xStart, yStart, xEnd, yEnd);
    }

    public void slideToReflectGivenPoints(){
        Point pt = getSliderLocation();
        int xStart = pt.getX();
        int yStart = pt.getY();
        int xEnd = -pt.getX()*3;
        int yEnd = pt.getY();
        moveSlider(xStart, yStart, xEnd, yEnd);
    }

    private void swipeRight(){
        Point pt = getSliderLocation();
        int xStart = pt.getX();
        int yStart = pt.getY();
        int xEnd = -pt.getX()*16;
        int yEnd = pt.getY();
        moveSlider(xStart, yStart, xEnd, yEnd);
    }

    private void moveSlider(int xStart, int yStart, int xEnd, int yEnd) {
        TouchAction actionAndroid = new TouchAction(driver);
        actionAndroid.press(PointOption.point(xStart, yStart)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1500))).moveTo(PointOption.point(-xEnd,yEnd)).release().perform();
    }

    private Point getSliderLocation() {
        waitUntilVisibleOf(By.id("com.mastercard.paywithrewards:id/sbProgress"));
        WebElement progressBar = driver.findElement(By.id("com.mastercard.paywithrewards:id/sbProgress"));
        return progressBar.getLocation();
    }


    public void clickBackButton(){
        driver.findElementByClassName("android.widget.ImageButton").click();
    }
}
