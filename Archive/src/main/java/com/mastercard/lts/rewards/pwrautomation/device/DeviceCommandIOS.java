package com.mastercard.lts.rewards.pwrautomation.device;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;

@Profile("iOS")
@Component
public class DeviceCommandIOS implements DeviceCommand{
    @Autowired
    public AppiumDriver driver;
    public DeviceCommandIOS() { }

    public void clickHomeButton(){
        ((IOSDriver) driver).runAppInBackground(Duration.ofSeconds(5));
    }

    public void killApp(){
        driver.closeApp();
    }
 
    public void turnAirplaneMode(boolean on) throws InterruptedException {
        Map<String, Object> pars2 = new HashMap<>();
        pars2.put("property", "airplanemode");
        String reStr = (String) driver.executeScript("mobile:network.settings:get", pars2);
        switchToContext(driver,"NATIVE_APP");
        try {
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
            switchToContext(driver, "NATIVE_APP");
            driver.findElementByXPath("//*[@value=\"Wi-Fi\"]").click();

            switchToContext(driver, "NATIVE_APP");
            WebElement switchOnOff = driver.findElementByXPath("//*[@label=\"Wi-Fi\" and @class=\"UIASwitch\"]");
            switchOnOff.click();
            sleep(2000);
            switchOnOff.click();
        }catch(Exception ex){
            System.out.println(ex);

        }
    }

    @Override
    public void changeLanguage() throws InterruptedException {

    }

    public void relaunchApp(){

        //declare the Map for script parameters
        Map<String, Object> params = new HashMap<>();
        params.put("timeout", "5");
        driver.closeApp();
        //String res = (String) driver.executeScript("mobile:application:back", params);
    }
    // Switched driver context
    public static void switchToContext(RemoteWebDriver driver, String context) {
        RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", context);
        executeMethod.execute(DriverCommand.SWITCH_TO_CONTEXT, params);
    }
    
    
}

