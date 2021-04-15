package com.mastercard.lts.rewards.pwrautomation.device.driver;


import com.mastercard.lts.rewards.pwrautomation.properties.PWRLanguageSpecificProperties;
import com.mastercard.lts.rewards.pwrautomation.utils.DeviceType;
import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.client.ReportiumClientFactory;
import com.perfecto.reportium.model.Job;
import com.perfecto.reportium.model.PerfectoExecutionContext;
import com.perfecto.reportium.model.Project;
import com.perfecto.reportium.test.TestContext;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

import javax.annotation.PostConstruct;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.mastercard.lts.rewards.pwrautomation.utils.IOUtils.getSecurityToken;

@Configuration
@Profile("android")
@Order(-11)
public class AndroidDriverConfiguration{

	private static final String JOB_NAME = "PWR Automation Android";
	private static final String ANDROID_TAG = "android";
    @Value("${perfecto.hub.url}")
    private String hubUrl;

    @Value("${perfecto.device.model}")
    private String model;

    @Value("${user.security.token.path}")
    private String securityTokePath;

    @Value("${user.name}")
    private String user;

    @Value("${user.password}")
    private String password;

    @Value("${pwr.automation.app.package}")
    private String appPackage;

    @Value("${pwr.automation.app.repository}")
    private String appRepository;

    @Autowired
    protected PWRLanguageSpecificProperties pwrLanguageSpecificProperties;

    @PostConstruct
    public void setupProperties(){
        com.mastercard.lts.rewards.pwrautomation.utils.TestContext.setLanguageSpecificProperties(pwrLanguageSpecificProperties);
    }

    private AppiumDriver driver;

    @Bean
    public DeviceType getDeviceType(){
        return DeviceType.ANDROID;
    }

    @Bean
    public AppiumDriver initializeAppiumDriver(){
        synchronized (this) {
            if (driver == null) {
                try {
//                    driver = new AndroidDriver(new URL(hubUrl), buildCapabilities());
                    driver = new AndroidDriver(new URL("https://mastercard.perfectomobile.com/nexperience/perfectomobile/wd/hub"), buildCapabilities());
                } catch (MalformedURLException e) {
                    throw new IllegalArgumentException("Invalid url for creating DriverConfiguration");
                }
            }
        }
        return driver;
    }

    private ReportiumClient reportiumClient;

    private String returnAppVersion()
    {
    	Pattern pattern = Pattern.compile("PRIVATE:PayWithRewards-4.0.19.261-NEW-MIDDLETIER-MTF-SIGNED.apk");
		Matcher matcher = pattern.matcher(appRepository);
		return matcher.matches()? matcher.group(1):"0";
    }

    private Optional<String> returnLanguageProp()
    {
         List<String> properties = Arrays.asList((System.getProperty("spring.profiles.active")).split(","));
         return properties.stream().filter(languageProperty -> !("android".equals(languageProperty) || "settings".equals(languageProperty))).findFirst();
    }

    private String[] returnReportContextTags()
    {
    	List<String> tags = new ArrayList<>();
    	tags.add(ANDROID_TAG);
    	if(returnLanguageProp().isPresent()) {
    		tags.add(returnLanguageProp().get());
    	}
    	String commandLine=System.getProperty("cucumber.options");
    	if(!StringUtils.isEmpty(commandLine)&&commandLine.length()>7) {
        	commandLine= commandLine.substring(7);
        	tags.addAll(Arrays.asList(commandLine.split(",")));
    	}
    	return tags.stream().toArray(String[]::new);
    }


    @Bean(name="reportiumClient")
    public ReportiumClient reportiumClient(){
        if(reportiumClient==null){
            PerfectoExecutionContext perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
            		.withJob(new Job(JOB_NAME,1))
            		.withProject(new Project("Pay With Rewards", returnAppVersion()))
                    .withContextTags(returnReportContextTags())
                    .withWebDriver(this.driver)
                    .build();
            reportiumClient = new ReportiumClientFactory().createPerfectoReportiumClient(perfectoExecutionContext);
            reportiumClient.testStart("PWR Test Android",new TestContext(""));
        }
        return reportiumClient;
    }

    private DesiredCapabilities buildCapabilities(){
        DesiredCapabilities capabilities = new DesiredCapabilities("", "", Platform.ANY);
//        String deviceName = System.getProperty("device.name");
//        if(deviceName != null)
//        {
//            this.model = deviceName;
//        }

        String sessionID = System.getProperty("session.id");

        if(!this.user.isEmpty() && !this.password.isEmpty()) {
            capabilities.setCapability("user",this.user);
            capabilities.setCapability("password",this.password);
        }
        else {
//            capabilities.setCapability("securityToken",getSecurityToken(securityTokePath));
            capabilities.setCapability("securityToken","eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICI5YjgwYWViZS01MGYxLTRmMjQtYTFlNC1hMzg2MmY5ZjEzYTEifQ.eyJqdGkiOiI5NGFjZmRlZC03Njg3LTRhZDYtODc4NS0zNTIyMGZlNzllNzYiLCJleHAiOjAsIm5iZiI6MCwiaWF0IjoxNjE0MzYzOTQ0LCJpc3MiOiJodHRwczovL2F1dGgucGVyZmVjdG9tb2JpbGUuY29tL2F1dGgvcmVhbG1zL21hc3RlcmNhcmQtcGVyZmVjdG9tb2JpbGUtY29tIiwiYXVkIjoiaHR0cHM6Ly9hdXRoLnBlcmZlY3RvbW9iaWxlLmNvbS9hdXRoL3JlYWxtcy9tYXN0ZXJjYXJkLXBlcmZlY3RvbW9iaWxlLWNvbSIsInN1YiI6ImI0YTZmZjAxLWFhYWItNDA2MC04ZDMxLTY4MGM5ZWZlZGU4ZSIsInR5cCI6Ik9mZmxpbmUiLCJhenAiOiJvZmZsaW5lLXRva2VuLWdlbmVyYXRvciIsIm5vbmNlIjoiZTdlMThiZmEtYWFkNC00NzljLTgyZTEtZjUwNGM2YTI0YjZhIiwiYXV0aF90aW1lIjowLCJzZXNzaW9uX3N0YXRlIjoiZDY1NjdlZDUtMWY2Ny00YjZhLTg1NTUtYjdmNTU4MjMxMTQ3IiwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsicmVwb3J0aXVtIjp7InJvbGVzIjpbInJlcG9ydF9hZG1pbiJdfSwiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJvcGVuaWQgb2ZmbGluZV9hY2Nlc3MifQ.LAGYYqBa9z0M8HKOiXzz_DCQ7OjUowoaLUd9f-jBB_U");
        }

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "10");
        capabilities.setCapability("location", "NA-US-PHX");
        capabilities.setCapability("resolution", "1440x3040");
        capabilities.setCapability("manufacturer", "Samsung");
        capabilities.setCapability("model", "Galaxy S10");
        capabilities.setCapability("deviceSessionId", "34bf07f6-19eb-4b4d-8b7e-ecd927bcaa8d");
        capabilities.setCapability("unicodeKeyboard", true);


       /* capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "7.0");
//        capabilities.setCapability("alocation", "NA-US-PHX");
//        capabilities.setCapability("resolution", "1440x3040");
        capabilities.setCapability("model",model);
        capabilities.setCapability("model", "Galaxy S6");
//        capabilities.setCapability("autoLaunch",true);
//        capabilities.setCapability("app",appRepository);
//        capabilities.setCapability("noReset", true);
//        capabilities.setCapability("fullReset", true);
//        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("deviceSessionId", sessionID);
        capabilities.setCapability("unicodeKeyboard", true);*/
        return capabilities;
    }
}
