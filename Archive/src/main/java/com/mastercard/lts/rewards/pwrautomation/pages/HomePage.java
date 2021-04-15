package com.mastercard.lts.rewards.pwrautomation.pages;

import java.util.ArrayList;
import java.util.List;

import com.mastercard.lts.rewards.pwrautomation.dbsetup.DatabaseSetup;
import com.mastercard.lts.rewards.pwrautomation.utils.DeviceType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends AbstractHomePage{


    private DatabaseSetup dbSetup = new DatabaseSetup();

    @AndroidFindBy(id="card_issuer")
    WebElement cardIssue;

    @AndroidFindBy(id="rewards_home_header_text")
    private
    WebElement banner;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/cardDetailsLayout")
    private
    WebElement cardDetails;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/pwr_activation_text")
    private
    WebElement pwrActivationText;


    @AndroidFindBy(id="com.mastercard.paywithrewards:id/pwr_info_text")
    private
    WebElement pwrInfoText;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/enable_pwr")
    private
    WebElement enablePWR;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/never_button")
    private
    WebElement disablePWR;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/next_purchase_button")
    private
    WebElement nextPurchase;

    @AndroidFindBy(id="com.mastercard.paywithrewards:id/all_purchase_button")
    private
    WebElement allPurchases;


    @AndroidFindBy(id="com.mastercard.paywithrewards:id/pointsTitleTv")
    public WebElement availablePoints;
    @AndroidFindBy(id="com.mastercard.paywithrewards:id/cashTitleTv")
    public WebElement availableCash;
    @AndroidFindBy(id="com.mastercard.paywithrewards:id/card_number")
    public WebElement cardNumber;
    @AndroidFindBy(id="com.mastercard.paywithrewards:id/rewardsTitleTv")
    public WebElement calculatorButton;

    @AndroidFindBy(id="rewards_home_header")
    private
    WebElement rewardsHomeHeader;


    @AndroidFindBy(id="offers_content")
    WebElement offersContent;

    @AndroidFindBy(id="content")
    WebElement offerPopupContent;

    @AndroidFindBy(id="dialog_name")
    private
    WebElement offerDialog;

    HomePage(AppiumDriver<WebElement> driver, DeviceType deviceType, int timeout) {
        super(driver, deviceType, timeout);
    }

    @AndroidFindBy(id="points_worth_container")
    private
    WebElement offerPointsCalculatorContainer;

    protected List<ExpectedCondition<WebElement>> initConditions() {
        List<ExpectedCondition<WebElement>> conditions = new ArrayList<>();
        conditions.add(ExpectedConditions.visibilityOf(cardDetails));
        conditions.add(ExpectedConditions.visibilityOf(cardNumber));
        return conditions;
    }
    public void clickBanner()
    {
        banner.click();
        waitExecutionFor(2);
    }

    public void selectControlWithText(String controlName){
        WebElement control = rewardsHomeHeader.findElement(By.xpath("//*[contains(@text,\""+controlName+"\")]"));
        control.click();
    }
    public boolean tabInScrollViewContains(String tabName){
        try{
//            String deviceName = System.getProperty("device.name");
//            if(deviceName.equalsIgnoreCase("Galaxy S8+")) {
//                tabName = tabName.toUpperCase();
//            }
            WebElement scrollView = driver.findElement(By.id("com.mastercard.paywithrewards:id/tab_layout"));
            return scrollView.findElement(By.xpath("//*[contains(@text,\""+tabName+"\")]")).isDisplayed();
        }catch (Exception ex){
            return false;
        }
    }
    public void selectTabInScrollView(String tabName){
        WebElement scrollView = driver.findElement(By.id("com.mastercard.paywithrewards:id/tab_layout"));
      /*  String deviceName = System.getProperty("device.name");
        if(deviceName.equalsIgnoreCase("Galaxy S8+")){
            tabName = tabName.toUpperCase();
        }*/
        WebElement tabControl = scrollView.findElement(By.xpath("//*[contains(@text,\""+tabName+"\")]"));
        waitExecutionFor(2);
        tabControl.click();
        waitExecutionFor(1);
    }

    public void clicksCalculatorIcon(){
        waitUntilVisibleOf(By.id("com.mastercard.paywithrewards:id/rewardsTitleTv"));
        calculatorButton.click();
    }

    public void clickActivatePWR() {
        try {
            enablePWR.click();
        } catch (Exception e) {
            disablePWR.click();
            waitExecutionFor(3);
            enablePWR.click();

        }
    }

    public void clickDeActivatePWR() {
        try {
            disablePWR.click();
        } catch (Exception e) {
            enablePWR.click();
            waitExecutionFor(3);
            nextPurchase.click();
            waitExecutionFor(3);
            disablePWR.click();

        }
    }

    public void clickNextPurchase() {
        waitExecutionFor(2);
        nextPurchase.click();
    }

    public void clickAllPurchases() {
        waitExecutionFor(2);
        allPurchases.click();
    }

    public void findHomeButton(){
        WebElement homeButton = driver.findElement(By.xpath("//device/view/group[1]/group[1]/group[1]/group[1]/group[1]/view[1]/group[1]/group[1]/group[1]/group[2]/button[1]"));
        homeButton.click();
    }

    public void dataBaseSetUp(String transactionAmount, String settlementAmount, String billingAmount,String acceptorName,String accountNumber, String programID, String serviceID,String acquiringInsIDCode, String cardAcceptorIDCode) throws Exception {
        dbSetup.dataBaseConnectionSetUp(transactionAmount,settlementAmount,billingAmount,acceptorName,accountNumber,programID,serviceID,acquiringInsIDCode,cardAcceptorIDCode);
    }

}
