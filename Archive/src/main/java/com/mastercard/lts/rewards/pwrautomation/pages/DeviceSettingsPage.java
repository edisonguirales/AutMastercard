package com.mastercard.lts.rewards.pwrautomation.pages;


import com.mastercard.lts.rewards.pwrautomation.utils.DeviceType;
import com.mastercard.lts.rewards.pwrautomation.utils.Direction;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.WaitOptions;

import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.collections.map.HashedMap;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.mastercard.lts.rewards.pwrautomation.utils.LanguageConstants.*;
import static com.mastercard.lts.rewards.pwrautomation.utils.TestContext.getValue;

import java.io.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DeviceSettingsPage extends MobilePage {

    @AndroidFindBy(id="dashboard_container")
    WebElement dashBoard;

    @AndroidFindBy(id="dragList")
    WebElement dragList;

    @AndroidFindBy(id="add_language")
    WebElement addLanguage;

    @AndroidFindBy(id="decor_content_parent")
    WebElement decorContentParent;

    @AndroidFindBy(id="action_bar")
    WebElement backButton;

    private static ThreadLocal<Integer> counter = ThreadLocal.withInitial(() -> null);

    private static Map<String, String> settingLangMap = new HashedMap();

    {
        settingLangMap.put(SETTINGS_ENGLISH, ENGLISH);
        settingLangMap.put(SETTINGS_CHINESE, CHINESE);
        settingLangMap.put(SETTINGS_PORTUGUESE, PORTUGUESE);
        settingLangMap.put(SETTINGS_SIMPLIFIEDCHINESE, SIMPLIFIEDCHINESE);
        settingLangMap.put(SETTINGS_SPANISH,  SPANISH);
        settingLangMap.put(SETTINGS_POLISH, POLISH);
        settingLangMap.put(SETTINGS_FRENCH, FRENCH);
        //settingLangMap.put(SETTINGS_ENGLISHUK, ENGLISHUK);
    }
    
    private static Map<String, String> propertyMap = new HashedMap();
    {
    	propertyMap.put("en_US",  ENGLISH);
    	propertyMap.put("pt_BR",  PORTUGUESE);
    	propertyMap.put("pl_PL",  POLISH);
    	propertyMap.put("fr_CA",  FRENCH);
    	propertyMap.put("zh_HK",  CHINESE);
    	propertyMap.put("zh_CN",  SIMPLIFIEDCHINESE);
    	propertyMap.put("es_MX",  SPANISH);
    	propertyMap.put("uk_UA",  UKRIANIAN);
    	//propertyMap.put("en_UK",  ENGLISHUK);
    }

    public DeviceSettingsPage(AppiumDriver<WebElement> driver, DeviceType deviceType, int timeout) {
        super(driver, deviceType, timeout);
    }
    @Override
    protected List<ExpectedCondition<WebElement>> initConditions() {
        List<ExpectedCondition<WebElement>> conditions = new ArrayList<>();
        if(getDeviceType().equals(DeviceType.IOS)){
        }else {
            conditions.add(ExpectedConditions.elementToBeClickable(dashBoard));
        }
        return conditions;
    }
    public void tinySwipe(Direction direction,WebElement webElement){
        Point scrollViewPoint = webElement.getLocation();
        Dimension size=webElement.getSize();
        int x=scrollViewPoint.getX()+size.getWidth()/2;
        int y_down=scrollViewPoint.getY()+(int)(size.getHeight()*0.8);
        int y_up = scrollViewPoint.getY()+(int)(size.getHeight()*0.5);
        if(getDeviceType().equals(DeviceType.IOS))		/* TouchAction did not work with XCUITestDriver for IOS-10 and above.So we are using IOSTouchAction */
        {
            IOSTouchAction action = new IOSTouchAction(driver);
            action.press(PointOption.point(x, direction==Direction.DOWN?y_up:y_down)).moveTo(PointOption.point(x,direction==Direction.DOWN?y_down:y_up)).release().perform();
        }
        else
        {
            TouchAction actionAndroid = new TouchAction(driver);
            actionAndroid.longPress(PointOption.point(x, direction==Direction.DOWN?y_up:y_down)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(PointOption.point(x,direction==Direction.DOWN?y_down:y_up)).release().perform();
        }
    }

    public boolean findElementInScrollView(By by,WebElement webElement,Direction direction){
        int counter = 0;
        boolean isPresent = false;
        if(getDeviceType().equals(DeviceType.IOS)){}else {
            while (counter < 23) {
                if (isPresentAndDisplayed(by) ){
                    isPresent = true;
                    break;
                } else {
                    tinySwipe(direction,webElement);
                    waitExecutionFor(1);
                    counter++;
                }
            }
        }
        return isPresent;
    }


    public void swipeUp() {
        WebElement scrollView = driver.findElement(By.id("com.mastercard.paywithrewards:id/pager"));
        Dimension size = driver.manage().window().getSize();
        Point scrollViewPoint = scrollView.getLocation();
        int yEnd = size.getHeight() + 15;
        int yStart = scrollViewPoint.getY() + 5;
        int x = (size.width - scrollViewPoint.x) / 2;
        TouchAction actionAndroid = new TouchAction(driver);
        actionAndroid.tap(new PointOption().withCoordinates(x, yStart)).
                moveTo(new PointOption().withCoordinates(x, yEnd)).release().perform();

    }

    public void findAppLanguage(String appText)
    {
        String language = settingLangMap.get(appText);
        this.compareLanguages(language == null ? ENGLISH : language);
    }

    public void compareLanguages(String language)
    {
        String propertyFiles = System.getProperty("spring.profiles.active");
        List<String> languageProperty = Arrays.asList(propertyFiles.split(","));
        String newLang = "";
        String languagePropFile = "";
        for(int i=0;i<languageProperty.size();i++)
        {
        	if(!("android".equals(languageProperty.get(i)) || "settings".equals(languageProperty.get(i))))
            {
        		languagePropFile = languageProperty.get(i);
            }

        }

        if(!(languagePropFile.equals("null")))
        {
        	newLang = propertyMap.get(languagePropFile);
        }
        else{
        	newLang = propertyMap.get("en_US");
        }

        if(!language.equalsIgnoreCase(newLang)) {
            getLanguageProperties(language, newLang);
        }
        if(!language.equalsIgnoreCase(ENGLISH)&&!newLang.equalsIgnoreCase(ENGLISH)&& counter.get()!=null)
         {
            getLanguageProperties(language, ENGLISH);
         }
         counter.set(1);
    }

    private void getLanguageProperties(String language, String newLang) {
        String generalManagement = getValue("language." + language.toLowerCase() + ".general.management");
        String languageAndInput = getValue("language." + language.toLowerCase() + ".language.and.input");
        String edit = getValue("language." + language.toLowerCase() + ".edit");
        String existingLang = getValue("language." + language.toLowerCase() + ".existing.language");
        String newLanguage = getValue("language." + newLang.toLowerCase() + ".language");
        String newLangWithCountry = getValue("language." + newLang.toLowerCase() + ".language.with.country");
        String newLangCountry = getValue("language." + newLang.toLowerCase() + ".language.country");
        String delete = getValue("language." + language.toLowerCase() + ".delete");
        String ok = getValue("language." + language.toLowerCase() + ".ok");
        this.changeLanguageSettings(generalManagement, languageAndInput, edit, existingLang, newLanguage, newLangWithCountry, newLangCountry, delete, ok);
    }

    public void changeLanguageSettings(String generalManagement,String languageAndInput,String edit, String existingLang,String newLanguage, String newLangWithCountry,String newLangCountry,String delete,String ok )
    {
        if(findElementInScrollView(By.xpath("//*[contains(@text,\""+generalManagement+"\")]"),dashBoard,Direction.UP)) {
            driver.findElement(By.xpath("//*[contains(@text,\""+generalManagement+"\")]")).click();
        }
        waitUntilVisibleOf(By.xpath("//*[contains(@text,\""+languageAndInput+"\")]"));
        driver.findElement(By.xpath("//*[contains(@text,\""+languageAndInput+"\")]")).click();
        waitUntilVisibleOf(By.id("summary"));
        driver.findElement(By.id("summary")).click();
        driver.findElement(By.xpath("//*[contains(@text,\""+edit+"\")]")).click();
        boolean multipleCountriesExists = selectLanguage(existingLang,delete,ok);
        if(!multipleCountriesExists)
        {
            backButton.findElement(By.className("android.widget.ImageButton")).click();
        }
        waitUntilVisibleOf(By.id("add_language"));
        addLanguage.click();
        waitUntilVisibleOf(By.id("decor_content_parent"));

        if (findElementInScrollView(By.xpath("//*[@text = \"" + newLanguage + "\"]"), decorContentParent, Direction.UP)) {
            driver.findElement(By.xpath("//*[@text = \"" + newLanguage + "\"]")).click();
        } else {
            findElementInScrollView(By.xpath("//*[@text = \"" + newLangWithCountry + "\"]"), decorContentParent, Direction.DOWN);
            driver.findElement(By.xpath("//*[@text = \"" + newLangWithCountry + "\"]")).click();
        }
        waitUntilVisibleOf(By.id("decor_content_parent"));
        findElementInScrollView(By.xpath("//*[contains(@text,\""+newLangCountry+"\")]"), decorContentParent,Direction.UP);
        driver.findElement(By.xpath("//*[contains(@text,\""+newLangCountry+"\")]")).click();
        driver.findElement(By.xpath("//*[contains(@text,\""+edit+"\")]")).click();
        selectLanguage(newLanguage,delete,ok);

    }

    private boolean selectLanguage(String language, String delete, String ok ) {
        boolean multipleCountriesExists = false;
        int numberOfCountries = dragList.findElements(By.id("label")).size();
        List<WebElement> listOfCountries = dragList.findElements(By.id("label"));
        if(numberOfCountries>1) {
            for (int i = 0; i < numberOfCountries; i++) {
                if (listOfCountries.get(i).getText().contains(language)) {
                    listOfCountries.get(i).click();
                }
                listOfCountries.get(i).click();
            }
            waitUntilVisibleOf(By.xpath("//*[contains(@text,\""+delete+"\")]"));
            driver.findElement(By.xpath("//*[contains(@text,\""+delete+"\")]")).click();
            waitUntilVisibleOf(By.xpath("//*[contains(@text,\""+ok+"\")]"));
            driver.findElement(By.xpath("//*[contains(@text,\""+ok+"\")]")).click();
            multipleCountriesExists = true;
        }
        return multipleCountriesExists;
    }
}







