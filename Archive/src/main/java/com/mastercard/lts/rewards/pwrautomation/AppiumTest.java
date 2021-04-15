package com.mastercard.lts.rewards.pwrautomation;

import com.mastercard.lts.rewards.pwrautomation.pages.MobilePageFactory;
import com.mastercard.lts.rewards.pwrautomation.pages.SplashPage;
import com.mastercard.lts.rewards.pwrautomation.pages.TCPage;
import com.mastercard.lts.rewards.pwrautomation.utils.DeviceType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.mastercard.lts.rewards.pwrautomation.utils.PerfectoLabUtils.downloadReport;


/**
 * This template is for users that use DigitalZoom Reporting (ReportiumClient).
 * For any other use cases please see the basic template at https://github.com/PerfectoCode/Templates.
 * For more programming samples and updated templates refer to the Perfecto Documentation at: http://developers.perfectomobile.com/
 */
//@SpringBootApplication
    //TODO: Can it be deleted??
public class AppiumTest {

    public static void main(String[] args) throws MalformedURLException{
        //SpringApplication.run(AppiumTest.class, args);
        System.out.println("Run started");
        String browserName = "mobileOS";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("autoLaunch",true);
        capabilities.setCapability("securityToken","eyJhbGciOiJSUzI1NiJ9.eyJqdGkiOiIwYTFiYzQ1NS0zZDAwLTRjNWUtOGJiMS05MjU2NDkxMWZhNTYiLCJleHAiOjAsIm5iZiI6MCwiaWF0IjoxNTI0NDk0NDM3LCJpc3MiOiJodHRwczovL2F1dGgucGVyZmVjdG9tb2JpbGUuY29tL2F1dGgvcmVhbG1zL21hc3RlcmNhcmQtcGVyZmVjdG9tb2JpbGUtY29tIiwiYXVkIjoib2ZmbGluZS10b2tlbi1nZW5lcmF0b3IiLCJzdWIiOiJlMjM1M2U0YS0wY2IzLTQ1OWItOThiNC1kOTllZmU3ZTExYjciLCJ0eXAiOiJPZmZsaW5lIiwiYXpwIjoib2ZmbGluZS10b2tlbi1nZW5lcmF0b3IiLCJzZXNzaW9uX3N0YXRlIjoiN2IxYjE1OTEtYjk1MC00ODgzLTgxODUtYzI3YjhmMWU1OTdhIiwiY2xpZW50X3Nlc3Npb24iOiI3ZjY2OWJiMi03NTk1LTRhOGYtYTlhYS03NWVjM2I2ZTNmYjQiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50Iiwidmlldy1wcm9maWxlIl19fX0.exSg-hJU8-zpoYDfZSZG6mKNZ9cxymeyZUdvzSP_W76MWh_SdykoMWKSw1XrdwwzNPeF9mUd_vK2esr3Y3lxVAJQjhePYuXBRZoJuWZ57xonNW6WEOkkydloMA8d8QLebz3wEf66QFyZIGn6mm0uFa8jNdn6YsZwu7p1xVJIv2k3eJ3Q65Sesx1kIkGqmGgoe33wcn_S4_LczWjyzgNhgw9HbKrzHXUfMcS3x31hdYJht3wicSMkf0sSLeCzjP1X-t07sLCRly3ZSxJyJu2tif9Ff6MW0xVnbFe6oz0arp1Tp7kchuDj9SUYfoet2cdn4EoeJ4WXf31R16s4ysIYow");
        capabilities.setCapability("fullReset",true);
        //capabilities.setCapability("model","iPhone.*");
        //capabilities.setCapability("deviceName","B6F871ED63A374FD8C7361E228E5AD58D67FC1B3");
        capabilities.setCapability("platformName", "iOS");
        //capabilities.setCapability("platformVersion", "10.2");
        capabilities.setCapability("manufacturer", "Apple");
        capabilities.setCapability("model", "iPhone-7");
//        capabilities.setCapability("location", "NA-US-PHX");
        //capabilities.setCapability("fullReset",true);

        capabilities.setCapability("app","PUBLIC:GRP/PWR/iOS/Test/PayWithRewards2.ipa");
        capabilities.setCapability("bundleId", "com.mastercard.loyalty");
//        //capabilities.setCapability("deviceName", args[3]);
        String host = "mastercard.perfectomobile.com";

        AppiumDriver driver = new IOSDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), capabilities);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        try {
            // write your code here
            //driver.launchApp();
            SplashPage splashPage = MobilePageFactory.buildInstance(driver,SplashPage.class, DeviceType.IOS);
            splashPage.clickContinueBtn();

            TCPage tcPage = MobilePageFactory.buildInstance(driver,TCPage.class,DeviceType.IOS);
            tcPage.scrollToCountryToSelect("United States");
            //splashPage.clickAllowNotificationButton();
//            SplashPage splashPage = new SplashPage(driver,1000);
//            splashPage.clickContinueBtn();
//            TCPage selectCountryPage = new TCPage(driver,20);
//            selectCountryPage.getCountrySelectorText();
//            selectCountryPage.clickCountrySelector();
//            selectCountryPage.scrollToCountryToSelect("United States");

        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                // Close the browser
                driver.close();

                // Download a pdf version of the execution report
                downloadReport(driver, "pdf", "/Users/e062015/Desktop/report.pdf");
            }
            catch(Exception e){
                e.printStackTrace();
            }
            // Release the driver
            driver.quit();
        }

        System.out.println("Run ended");
//        System.out.println("Run ended");
////        DriverConfiguration.close();
////        DriverConfiguration.quit();
    }

    private static void switchToContext(RemoteWebDriver driver, String context) {
        RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
        Map<String,String> params = new HashMap<String,String>();
        params.put("name", context);
        executeMethod.execute(DriverCommand.SWITCH_TO_CONTEXT, params);
    }

    private static String getCurrentContextHandle(RemoteWebDriver driver) {
        RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
        String context =  (String) executeMethod.execute(DriverCommand.GET_CURRENT_CONTEXT_HANDLE, null);
        return context;
    }

    private static List<String> getContextHandles(RemoteWebDriver driver) {
        RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
        List<String> contexts =  (List<String>) executeMethod.execute(DriverCommand.GET_CONTEXT_HANDLES, null);
        return contexts;
    }
}
