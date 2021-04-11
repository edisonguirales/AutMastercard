package com.mastercard.lts.rewards.pwrautomation.step_definitions;

import io.cucumber.java.Scenario;
import org.junit.After;
import org.junit.Before;

public class CommonSteps extends AbstractSteps {
    @Before
    public void beforeScenario(Scenario scenario){
        reportiumClient.testStep(scenario.getName());
    }
    @After
    public void afterScenario(Scenario scenario){

        if(scenario.isFailed()) {
            //setTestResult(TestResultFactory.createFailure(scenario.getStatus(),null));
            //TODO: Update failed message to be more descriptive
            testResult.setTestResultFailed(scenario.getStatus().toString());
        }
    }

}
