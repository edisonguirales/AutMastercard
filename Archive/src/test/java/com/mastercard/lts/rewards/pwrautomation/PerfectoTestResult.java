package com.mastercard.lts.rewards.pwrautomation;

import com.perfecto.reportium.test.result.TestResult;
import com.perfecto.reportium.test.result.TestResultFactory;
import org.springframework.stereotype.Component;

@Component
public class PerfectoTestResult {
    public boolean isFailed;
    private TestResult testResult;
    public PerfectoTestResult(){
        isFailed=false;
        this.testResult = TestResultFactory.createSuccess();
    }
    public TestResult getTestResult(){
        if(this.testResult==null){
            this.testResult = TestResultFactory.createSuccess();
        }
        return this.testResult;
    }
    public void setTestResultSuccessful(){
        this.testResult = TestResultFactory.createSuccess();
    }
    public void setTestResultFailed(String reason){
        isFailed = true;
        this.testResult = TestResultFactory.createFailure(reason,null);
    }
}
