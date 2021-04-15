package com.mastercard.lts.rewards.pwrautomation.device;

import com.mastercard.lts.rewards.pwrautomation.pages.DeviceSettingsPage;
import com.mastercard.lts.rewards.pwrautomation.utils.DeviceType;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Arrays;

import static com.mastercard.lts.rewards.pwrautomation.utils.LanguageConstants.*;

@Profile("android")
@Component
public class DeviceCommandAndroid implements DeviceCommand{

    private String existingLanguage;

    @Autowired
    public AppiumDriver driver;

    @Autowired
    public DeviceType deviceType;

    public DeviceCommandAndroid() { }

    public void killApp(){
        driver.closeApp();
    }

    public void relaunchApp(){
        ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.HOME);
        waitExecutionFor(5);
        //declare the Map for script parameters
        Map<String, Object> params = new HashMap<>();

        params.put("name", "Pay with Rewards");
        driver.executeScript("mobile:application:close", params);

        HashMap<String, String> openApp = new HashMap<>();
        openApp.put("name", "Pay with Rewards");
        driver.executeScript("mobile:application:open", openApp);
    }

    public void changeLanguage() {
        Map<String, Object> appText = new HashMap<>();
        List<String> settingsInDiffLang = Arrays.asList(SETTINGS_ENGLISH, SETTINGS_CHINESE,SETTINGS_PORTUGUESE, SETTINGS_SIMPLIFIEDCHINESE, SETTINGS_SPANISH, SETTINGS_POLISH,SETTINGS_FRENCH);
        for (Object language : settingsInDiffLang) {
            try {
                appText.put("name", language);
                driver.executeScript("mobile:application:open", appText);
                existingLanguage = language.toString();
                break;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        DeviceSettingsPage deviceSettingsPage = new DeviceSettingsPage(driver,deviceType,30);
        deviceSettingsPage.findAppLanguage(existingLanguage);
    }

    public void turnAirplaneMode(boolean on){
        //((AndroidDriver) driver).toggleAirplaneMode();
        Map<String, Object> pars = new HashMap<>();
        pars.put("property", "airplanemode");
        String reStr = (String) ((RemoteWebDriver)driver).executeScript("mobile:network.settings:get", pars);
        Map<String, Object> appName = new HashMap<>();
        appName.put("name", "Settings");
        try {
            driver.executeScript("mobile:application:open", appName);
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            driver.executeScript("mobile:application:close", appName);
        } catch (Exception e) {
            System.out.println(e);

        }
        try {
            driver.executeScript("mobile:application:open", appName);
        } catch (Exception e) {
            System.out.println(e);

        }

//        Map<String, Object> pars2 = new HashMap<>();
//        pars2.put("airplanemode", "enabled");
//        ((RemoteWebDriver)driver).executeScript("mobile:network.settings:set", pars2);
//        Map params1 = new HashMap<>(); // Set the "wifi" value to turn off the Wifi
//        params1.put("wifi", "disabled");
//        driver.executeScript("mobile:network.settings:set", params1); // Set the "wifi" value to turn the Wifi back on params1.put("wifi", "enabled");
//        reStr = (String) driver.executeScript("mobile:network.settings:get", pars);
    }
    public void waitExecutionFor(long noOFSeconds){
        try {
            Thread.sleep(noOFSeconds*1000);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void switchToContext(RemoteWebDriver driver, String context) {
        RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", context);
        executeMethod.execute(DriverCommand.SWITCH_TO_CONTEXT, params);
    }

}
