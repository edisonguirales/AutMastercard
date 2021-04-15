package com.mastercard.lts.rewards.pwrautomation.device.driver;

import com.mastercard.lts.rewards.pwrautomation.utils.DeviceType;
import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.client.ReportiumClientFactory;
import com.perfecto.reportium.model.Job;
import com.perfecto.reportium.model.PerfectoExecutionContext;
import com.perfecto.reportium.model.Project;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;

import static com.mastercard.lts.rewards.pwrautomation.utils.IOUtils.getSecurityToken;

@Component
@Profile("iOS")
public class IOSDriverConfiguration{

    @Value("${perfecto.hub.url}") private String  hubUrl;
    @Value("${perfecto.device.model}") private String  model;
    @Value("${user.security.token.path}") private String  securityTokePath;
    @Value("${perfecto.device.platformVersion}") private String  platformVersion;
    @Value("${pwr.automation.app.repository}") private String  repository;
    @Value("${pwr.automation.app.bundleId}") private String  bundleId;

    private AppiumDriver driver;

    @Bean
    public DeviceType getDeviceType(){
        return DeviceType.IOS;
    }


    @Bean
    public AppiumDriver initializeAppiumDriver(){
        synchronized (this) {
            if (driver == null) {
                try {
                    driver = new IOSDriver(new URL(hubUrl), buildCapabilities());
                } catch (MalformedURLException e) {
                    throw new IllegalArgumentException("Invalid url for creating DriverConfiguration");
                }
            }
        }
        return driver;
    }



    private ReportiumClient reportiumClient;

    @Bean(name="reportiumClient")
    public ReportiumClient reportiumClient(){
        if(reportiumClient==null){
            PerfectoExecutionContext perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
                    .withJob(new Job("PWR Test", 20180502))
                    .withProject(new Project("Pay With Rewards", "1.0"))
                    .withContextTags("iOSNativeAppTests")
                    .withWebDriver(this.driver)
                    .build();
            reportiumClient = new ReportiumClientFactory().createPerfectoReportiumClient(perfectoExecutionContext);
        }
        return reportiumClient;
    }

    private DesiredCapabilities buildCapabilities(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("securityToken",getSecurityToken(securityTokePath));
        capabilities.setCapability("autoLaunch",true);
        capabilities.setCapability("fullReset",false);
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("manufacturer", "Apple");
        capabilities.setCapability("platformVersion", this.platformVersion);
        capabilities.setCapability("model", this.model);
        capabilities.setCapability("app",this.repository);
        capabilities.setCapability("bundleId", this.bundleId);
        return capabilities;
    }
}
