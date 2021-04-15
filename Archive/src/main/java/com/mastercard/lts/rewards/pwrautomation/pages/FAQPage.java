package com.mastercard.lts.rewards.pwrautomation.pages;

import com.mastercard.lts.rewards.pwrautomation.utils.DeviceType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class FAQPage extends AbstractHomePage {

    public FAQPage(AppiumDriver<WebElement> driver, DeviceType deviceType, int timeout) {
        super(driver, deviceType, timeout);
    }
    @AndroidFindBy(id="com.mastercard.paywithrewards:id/faqs")
    WebElement faqs;
    @AndroidFindBy(id="spinner_container")
    WebElement spinnerContainer;
    @AndroidFindBy(xpath = "//*[@class=\"android.widget.ListView\"]")
    WebElement programSelection;

    @Override
    protected List<ExpectedCondition<WebElement>> initConditions() {
        List<ExpectedCondition<WebElement>> conditions = new ArrayList<>();
        conditions.add(ExpectedConditions.visibilityOf(faqs));
        return conditions;
    }
    public void clickSlideInMenuWithName(String menuName){
        try{
            burgerMenu.click();
            waitFor(ExpectedConditions.visibilityOfElementLocated(By.id("com.mastercard.paywithrewards:id/navigation_menu_list")));
            WebElement menuItem = slideMenu.findElement(By.xpath("//*[@text='"+menuName+"']"));
            menuItem.click();

        }catch (Exception ex){

        }
    }
    public void selectProgramWithText(String text){
        spinnerContainer.click();
        waitUntilVisibleOf(By.xpath("//*[@class=\"android.widget.ListView\"]"));
        //wait.until(ExpectedConditions.visibilityOf(programSelection));
        List<WebElement> options = programSelection.findElements(By.className("android.widget.CheckedTextView"));
        if(options.size()>0){
            for(WebElement option:options){
                if(option.getText().contains(text)){
                    option.click();
                    break;
                }
            }
        }
    }
    public boolean faqIsDisplayed(){
        return isPresentAndDisplayed(faqs);
    }
}
