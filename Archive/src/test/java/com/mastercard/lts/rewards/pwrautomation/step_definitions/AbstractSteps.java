package com.mastercard.lts.rewards.pwrautomation.step_definitions;

import com.mastercard.lts.rewards.pwrautomation.PerfectoTestResult;
import com.mastercard.lts.rewards.pwrautomation.PwrAutomationApplication;
import com.mastercard.lts.rewards.pwrautomation.device.DeviceCommand;
import com.mastercard.lts.rewards.pwrautomation.properties.PWRLanguageSpecificProperties;
import com.mastercard.lts.rewards.pwrautomation.utils.DeviceType;
import com.perfecto.reportium.client.ReportiumClient;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(classes = {PwrAutomationApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public class AbstractSteps {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Autowired
    protected DeviceType deviceType;

    @Autowired
    protected AppiumDriver<WebElement> driver;

    @Autowired
    protected ReportiumClient reportiumClient;

    @Autowired
    protected DeviceCommand deviceCommand;

    @Autowired
    protected PerfectoTestResult testResult;

    @Autowired
    protected PWRLanguageSpecificProperties pwrLanguageSpecificProperties;

    void waitExecutionFor(long noOFSeconds){
        try {
            Thread.sleep(noOFSeconds*1000);
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

