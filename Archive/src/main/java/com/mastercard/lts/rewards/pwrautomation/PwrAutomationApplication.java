package com.mastercard.lts.rewards.pwrautomation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PwrAutomationApplication {
	//@Autowired
	//private DriverConfiguration factory;
	public static void main(String[] args) {
		SpringApplication.run(PwrAutomationApplication.class, args);
	}

	//public static void main(String[] args) {
		//SpringApplication.run(PwdAutomationApplication.class, args);
//		DesiredCapabilities capabilities = new DesiredCapabilities("mobileOS","", Platform.ANDRIOD);
//		String host = "mastercard.perfectomobile.com";
//		capabilities.setCapability("user", "");
//		capabilities.setCapability("password", "");
//      capabilities.setCapability("securityToken","eyJhbGciOiJSUzI1NiJ9.eyJqdGkiOiIwYTFiYzQ1NS0zZDAwLTRjNWUtOGJiMS05MjU2NDkxMWZhNTYiLCJleHAiOjAsIm5iZiI6MCwiaWF0IjoxNTI0NDk0NDM3LCJpc3MiOiJodHRwczovL2F1dGgucGVyZmVjdG9tb2JpbGUuY29tL2F1dGgvcmVhbG1zL21hc3RlcmNhcmQtcGVyZmVjdG9tb2JpbGUtY29tIiwiYXVkIjoib2ZmbGluZS10b2tlbi1nZW5lcmF0b3IiLCJzdWIiOiJlMjM1M2U0YS0wY2IzLTQ1OWItOThiNC1kOTllZmU3ZTExYjciLCJ0eXAiOiJPZmZsaW5lIiwiYXpwIjoib2ZmbGluZS10b2tlbi1nZW5lcmF0b3IiLCJzZXNzaW9uX3N0YXRlIjoiN2IxYjE1OTEtYjk1MC00ODgzLTgxODUtYzI3YjhmMWU1OTdhIiwiY2xpZW50X3Nlc3Npb24iOiI3ZjY2OWJiMi03NTk1LTRhOGYtYTlhYS03NWVjM2I2ZTNmYjQiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50Iiwidmlldy1wcm9maWxlIl19fX0.exSg-hJU8-zpoYDfZSZG6mKNZ9cxymeyZUdvzSP_W76MWh_SdykoMWKSw1XrdwwzNPeF9mUd_vK2esr3Y3lxVAJQjhePYuXBRZoJuWZ57xonNW6WEOkkydloMA8d8QLebz3wEf66QFyZIGn6mm0uFa8jNdn6YsZwu7p1xVJIv2k3eJ3Q65Sesx1kIkGqmGgoe33wcn_S4_LczWjyzgNhgw9HbKrzHXUfMcS3x31hdYJht3wicSMkf0sSLeCzjP1X-t07sLCRly3ZSxJyJu2tif9Ff6MW0xVnbFe6oz0arp1Tp7kchuDj9SUYfoet2cdn4EoeJ4WXf31R16s4ysIYow");

//		//TODO: Change your device ID
//		capabilities.setCapability("deviceName", "C6004DE9");
//
//		// Use the automationName capability to define the required framework - Appium (this is the default) or PerfectoMobile.
//		capabilities.setCapability("automationName", "Appium");
//		//PerfectoLabUtils.setExecutionIdCapability(capabilities, host);
//		AndroidDriver DriverConfiguration = new AndroidDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), capabilities);
//		// IOSDriver DriverConfiguration = new IOSDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), capabilities);
//		DriverConfiguration.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//
//		// Reporting client. For more details, see http://developers.perfectomobile.com/display/PD/Reporting
//		PerfectoExecutionContext perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
//				.withProject(new Project("My Project", "1.0"))
//				.withJob(new Job("My Job", 45))
//				.withContextTags("tag1")
//				.withWebDriver(DriverConfiguration)
//				.build();
//		ReportiumClient reportiumClient = new ReportiumClientFactory().createPerfectoReportiumClient(perfectoExecutionContext);
//
//		try {
//			reportiumClient.testStart("My test name", new TestContext("tag2", "tag3"));
//
//			// write your code here
//
//			// reportiumClient.testStep("step1"); // this is a logical step for reporting
//			// add commands...
//			// reportiumClient.testStep("step2");
//			// more commands...
//
//			reportiumClient.testStop(TestResultFactory.createSuccess());
//		} catch (Exception e) {
//			reportiumClient.testStop(TestResultFactory.createFailure(e.getMessage(), e));
//			e.printStackTrace();
//		} finally {
//			try {
//				DriverConfiguration.quit();
//
//				// Retrieve the URL to the DigitalZoom Report (= Reportium Application) for an aggregated view over the execution
//				String reportURL = reportiumClient.getReportUrl();
//
//				// Retrieve the URL to the Execution Summary PDF Report
//				String reportPdfUrl = (String)(DriverConfiguration.getCapabilities().getCapability("reportPdfUrl"));
//				// For detailed documentation on how to export the Execution Summary PDF Report, the Single Test report and other attachments such as
//				// video, images, device logs, vitals and network files - see http://developers.perfectomobile.com/display/PD/Exporting+the+Reports
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//		System.out.println("Run ended");
//	}
}
