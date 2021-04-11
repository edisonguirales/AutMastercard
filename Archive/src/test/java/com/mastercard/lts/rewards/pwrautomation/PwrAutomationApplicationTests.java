package com.mastercard.lts.rewards.pwrautomation;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		strict = true,
		monochrome = true,
		glue={"com.mastercard.lts.rewards.pwrautomation.step_definitions"},
//		features = "src/test/resources/features/home.feature",
		features = "src/test/resources/features",
		plugin = {"pretty", "html:target/cucumber-report/cucumber.html",
				"json:target/cucumber-report/cucumber.json",
				"junit:target/cucumber-report/cucumber.xml"})
public class PwrAutomationApplicationTests {
	@Test
	public void contextLoads() {
	}
}
