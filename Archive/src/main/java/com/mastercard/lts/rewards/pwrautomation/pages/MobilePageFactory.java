package com.mastercard.lts.rewards.pwrautomation.pages;

import com.mastercard.lts.rewards.pwrautomation.utils.DeviceType;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class MobilePageFactory{
    public static <T extends MobilePage> T buildInstance(AppiumDriver<WebElement> mobileDriver, Class<T> clazz, DeviceType deviceType) {
        if (clazz.isAssignableFrom(SplashPage.class)) {
            return (T) new SplashPage(mobileDriver, deviceType,60);
        } else if (clazz.isAssignableFrom(TCPage.class)) {
            return (T) new TCPage(mobileDriver, deviceType,60);
        } else if (clazz.isAssignableFrom(PINPage.class)) {
            return (T) new PINPage(mobileDriver, deviceType, 30);
        }else if (clazz.isAssignableFrom(ConfirmPinPage.class)) {
            return (T) new ConfirmPinPage(mobileDriver, deviceType, 30);
        }  else if(clazz.isAssignableFrom(RegistrationPage.class)){
            return (T) new RegistrationPage(mobileDriver, deviceType, 30);
        }  else if(clazz.isAssignableFrom(SecurityQuestionPage.class)){
            return (T) new SecurityQuestionPage(mobileDriver, deviceType, 30);
        } else if(clazz.isAssignableFrom(TermsPage.class)){
            return (T) new TermsPage(mobileDriver,deviceType,30);
        } else if(clazz.isAssignableFrom(TutorialPage.class)){
            return (T) new TutorialPage(mobileDriver, deviceType, 30);
        } else if(clazz.isAssignableFrom(HomePage.class)){
            return (T) new HomePage(mobileDriver, deviceType, 30);
        } else if(clazz.isAssignableFrom(AddNewCardPage.class)){
            return (T) new AddNewCardPage(mobileDriver, deviceType, 30);
        } else if(clazz.isAssignableFrom(CalculatorPage.class)){
            return (T) new CalculatorPage(mobileDriver, deviceType, 30);
        } else if(clazz.isAssignableFrom(SettingsPage.class)){
            return (T) new SettingsPage(mobileDriver, deviceType, 30);
        } else if(clazz.isAssignableFrom(PromotionsPage.class)){
            return (T) new PromotionsPage(mobileDriver, deviceType, 30);
        } else if(clazz.isAssignableFrom(FAQPage.class)){
            return (T) new FAQPage(mobileDriver, deviceType, 30);
        } else if(clazz.isAssignableFrom(ActivityPage.class)){
            return (T) new ActivityPage(mobileDriver, deviceType, 30);
        } else if(clazz.isAssignableFrom(DeviceSettingsPage.class)){
            return (T) new DeviceSettingsPage(mobileDriver, deviceType, 30);
        }else if(clazz.isAssignableFrom(DataProtectionPolicyPage.class)){
            return (T) new DataProtectionPolicyPage(mobileDriver, deviceType, 30);
        }else if(clazz.isAssignableFrom(FlybitsOptInPage.class)){
            return (T) new FlybitsOptInPage(mobileDriver, deviceType, 30);
        }else if(clazz.isAssignableFrom(LocaltionServicesPage.class)){
            return (T) new LocaltionServicesPage(mobileDriver, deviceType, 30);
        }else if(clazz.isAssignableFrom(ConciergePage.class)){
            return (T) new ConciergePage(mobileDriver, deviceType, 30);
        }else if(clazz.isAssignableFrom(FlybitsNotificationsPage.class)){
            return (T) new FlybitsNotificationsPage(mobileDriver, deviceType, 30);
        }else if(clazz.isAssignableFrom(FlybitsOptInPostRegPage.class)){
            return (T) new FlybitsOptInPostRegPage(mobileDriver, deviceType, 30);
        }else {
            throw new IllegalArgumentException();
        }
    }
}
