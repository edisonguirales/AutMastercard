package com.mastercard.lts.rewards.pwrautomation.step_definitions;

import com.mastercard.lts.rewards.pwrautomation.device.DeviceCommand;
import com.mastercard.lts.rewards.pwrautomation.pages.*;
import com.mastercard.lts.rewards.pwrautomation.utils.DeviceType;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mastercard.lts.rewards.pwrautomation.utils.TestContext.getValue;

public class RegistrationSteps {

    @Autowired
    private AppiumDriver<WebElement> driver;

    private DeviceType deviceType;
    private DeviceCommand deviceCommand;


    private SplashPage splashPage;
    private TCPage tcPage;
    private PINPage pinPage;
    private ConfirmPinPage confirmPinPage;
    private RegistrationPage registrationPage;
    private DataProtectionPolicyPage dataProtectionPolicyPage;
    private FlybitsOptInPage flybitsOptInPage;
    private LocaltionServicesPage localtionServicesPage;

    private TutorialPage tutorialPage;
    private TermsPage termsPage;
    private SecurityQuestionPage securityQuestionPage;

    public RegistrationSteps(AppiumDriver< WebElement > driver, DeviceType deviceType, DeviceCommand deviceCommand) {
        this.deviceType = deviceType;
        this.deviceCommand = deviceCommand;
    }

    @When("^the client sees splash page")
    public void the_client_sees_splash_page() {
        Map<String, Object> params = new HashMap<>();

        params.put("file", "PRIVATE:PayWithRewards-4.0.19.257-SIGNED-MTF.apk");
        params.put("instrument", "noinstrument");
        driver.executeScript("mobile:application:install", params);

        Map<String, Object> openApp = new HashMap<>();
        openApp.put("name", "Pay with Rewards");
        driver.executeScript("mobile:application:open", openApp);
        splashPage = MobilePageFactory.buildInstance(driver, SplashPage.class, deviceType);
    }

    @Then("^the client clicks continue button")
    public void the_client_clicks_continue_button() {
        splashPage.clickContinueBtn();
    }

    @Then("^the client should click continue button on TC page")
    public void the_client_clicks_continue_button_on_tc_page() {
        tcPage.clickContinueBtn();
    }

    @Then("^the client has to verify the terms toast message(.+)")
    public void the_client_verifies_the_toast_message(String toastMessage)  {
        Assert.assertTrue(termsPage.verifiesToastMessage(getValue(toastMessage)));
    }

    @Then("^the client will see the sequential pin error toast message(.+)")
    public void the_client_verifies_sequential_pin_toast_message(String toastMessage)  {
        Assert.assertTrue(pinPage.verifiesToastMessage(getValue(toastMessage)));
    }

    @When("^the client clicks home button and relaunch the app")
    public void the_client_clicks_home_button_and_relaunch_the_app() {
        deviceCommand.relaunchApp();
        //deviceCommand.turnAirplaneMode(true);
    }

    @When("^the client opens settings app and change the language")
    public void the_client_changes_device_language() throws Throwable {
        deviceCommand.changeLanguage();
    }

    @When("^the client clicks home button")
    public void the_client_clicks_home_button() {
        deviceCommand.relaunchApp();
    }

    @Then("^the client clicks back button and relaunch the app")
    public void the_client_clicks_back_button_and_relaunches_app() {
        tcPage.relaunchAppAfterHittingBackButton();
    }

    @When("^the client relaunches the app")
    public void the_client_relaunches_app() {
        registrationPage.relaunchApp();
    }

    @When("^the client clicks on the back button in pin page")
    public void the_client_clicks_back_button() {
        pinPage.hitBackButton();
    }

    @When("^the client clicks on the back button in registration page")
    public void the_client_clicks_back_button_in_registration_page() {
        registrationPage.hitBackButton();
    }

    @When("^the client goes to T&C page")
    public void the_client_goes_to_TC_page() {
        tcPage = MobilePageFactory.buildInstance(driver, TCPage.class, deviceType);
    }

    @Then("^the client goes to Terms page")
    public void the_client_goes_to_terms_page() throws InterruptedException {
        termsPage = MobilePageFactory.buildInstance(driver, TermsPage.class, deviceType);
    }

    @Then("^validate country drop down (.+) and app title(.+)")
    public void validate_app_name_and_country_dropdown(String dropDownTxt, String appTitle) throws Throwable {
        String appName = tcPage.validateAppTitle();
        String countryDropdown = tcPage.validateCountryDropdownExists();
        Assert.assertEquals(getValue(dropDownTxt), countryDropdown);
        Assert.assertEquals(getValue(appTitle), appName);
    }

    @When("^the client clicks country selector")
    public void the_client_clicks_country_selector() {
        tcPage.clickCountrySelector();
    }

    @When("^the client clicks security question dropdown")
    public void the_client_clicks_security_question_dropdown() {
        waitExecutionFor(5);
        securityQuestionPage.clickSecurityQuestionSelector();
    }

    @Then("^validate the dropdown has the (.+)$")
    public void validate_country_dropdown_has_countries(String countryName) {
        String selectedCountry = tcPage.validateCountryDropdownValues(getValue(countryName));
        Assert.assertEquals(getValue(countryName), selectedCountry);
    }

    @Then("^the client selects country (.+)$")
    public void the_client_clicks_continue_button(String countryName) {
        tcPage.scrollToAnElementByText(getValue(countryName));
    }

    @Then("^the client selects acknowledge checkbox on Terms page")
    public void the_client_selects_acknowledge_checkbox() {
        termsPage.clickCheckbox();
    }

    @Then("^the client clicks decline button on Terms page")
    public void the_client_clicks_decline_button() {
        termsPage.clickDeclineButton();
    }

    @Then("^validate Accept and Decline button is enabled")
    public void validate_AD_button_is_enabled() {
        Assert.assertTrue(termsPage.validateAcceptButton());
        Assert.assertTrue(termsPage.validateDeclineButton());
    }

    @Then("^validate OK and Cancel button is enabled")
    public void validate_OK_and_Cancel_button_is_enabled() {
        Assert.assertTrue(termsPage.validateOKButton());
        Assert.assertTrue(termsPage.validateCancelButton());
        termsPage.clickCancelButton();
    }

    @Then("^the client clicks Cancel button on Terms page")
    public void the_client_clicks_Cancel_button() {
        termsPage.clickCancelButton();
    }

    @When("^the client sees PIN page")
    public void the_client_sees_PIN_page() {
        pinPage = MobilePageFactory.buildInstance(driver, PINPage.class, deviceType);
    }

    @When("^the client sees Confirm Pin page")
    public void the_client_sees_Confirm_Pin_page() {
        confirmPinPage = MobilePageFactory.buildInstance(driver, ConfirmPinPage.class, deviceType);
    }

    @Then("^the client enters PIN code (.+)$")
    public void the_client_enters_PIN_code(String pinCode) {
        pinPage.enterPinCode(pinCode.trim());
        waitExecutionFor(3);
    }

    @Then("^the client goes back to the select country page")
    public void the_client_clicks_cancel_button() {
        pinPage.hitCancelButton();
    }


    @When("^client clicks the Back Space to remove the existing pin")
    public void the_client_clicks_backspace() {
        pinPage.hitBackSpace();
    }

    @Then("^client validates the pin field is empty in pin page")
    public void the_validates_pin() {
        List<WebElement> webElementList = pinPage.validatePin();
        webElementList.forEach(webElement -> {
            Assert.assertEquals("-", webElement.getText());
        });
    }

    @Then("^the client confirms PIN code (.+)")
    public void the_client_confirms_PIN_code(String pin) {
        confirmPinPage.enterPinCode(pin);
    }


    @When("^the client sees registration page")
    public void the_client_sees_registration_page() {
        registrationPage = MobilePageFactory.buildInstance(driver, RegistrationPage.class, deviceType);
    }

    @When("^the client sees data protection policy page")
    public void the_client_sees_data_protection_policy_page() {
        dataProtectionPolicyPage = MobilePageFactory.buildInstance(driver, DataProtectionPolicyPage.class, deviceType);
    }

    @When("^the client sees Security Question page")
    public void the_client_sees_security_question_page() {
        securityQuestionPage = MobilePageFactory.buildInstance(driver, SecurityQuestionPage.class, deviceType);
    }

    @Then("^validate the pin number help hint(.+)$")
    public void validate_pin_number_help_hint(String pinHelpHint) {
        Assert.assertTrue(pinPage.validatePinHelpHint(getValue(pinHelpHint)));
    }

    @Then("^validate the card number help hint(.+)$")
    public void validate_card_number_help_hint(String helpHint) {
        Assert.assertTrue(registrationPage.validateHelpHint(getValue(helpHint)));
    }

    @When("^the client validates the security question help hint icon(.+)$")
    public void the_client_clicks_on_security_question_info_symbol(String securityHelpHint) {
        securityQuestionPage.clickOnSecurityQuestionHelpHint(getValue(securityHelpHint));
    }

    @Then("^validate the security questions")
    public void validate_security_questions(List<String> tabNames){
        for (String tabName:tabNames) {
            String name = getValue(tabName);
            Assert.assertTrue("Security Question "+name+" should be displayed",securityQuestionPage.validateSecurityQuestions(name));
        }
    }

    @When("^the client enters card number(.+)$")
    public void the_client_enters_card_number(String cardNumber) {
        registrationPage.enterCreditCardNumber(cardNumber.trim());
    }

    @Then("^the client clicks Ok button in registration page")
    public void the_client_clicks_ok_in_registration_page() {
        registrationPage.clickOkButton();
    }

    @Then("^the client clicks Ok button in security question page")
    public void the_client_clicks_ok_in_security_question_page() {
        securityQuestionPage.clickOkButton();
    }

    @Then("^validate the text box is empty for card number")
    public void validate_invalid_card_error_message() {
        Assert.assertEquals("", registrationPage.validateInValidCardErrorMessage());
    }

//    @Then("^validate the lock screen")
//    public void validate_lock_screen() throws Throwable{
//        registrationPage.validateLockScreen();
//    }


    @Then("^validate the error message(.+)")
    public void validate_error_message(String alertMessage) {
        Assert.assertEquals(getValue(alertMessage), registrationPage.validateAlertMessage());
    }

    @Then("^validate the agreement alert message(.+)")
    public void validate_agreement_alert_message(String alertMessage) {
        String msg = getValue(alertMessage);
        Assert.assertEquals(msg, termsPage.validateAlertMessage());
    }

    @Then("^the client selects security question with option(.+)$")
    public void the_client_selects_security_question_with_option(String option) {
        securityQuestionPage.selectSecurityQuestion(getValue(option));
    }

    @Then("^the client enters security question answer as(.+)$")
    public void the_client_enters_security_question_answer(String answer) {
        securityQuestionPage.enterSecurityQuestionAnswer(answer.trim());
    }

    @When("^the client sees Terms page")
    public void the_client_sees_term_page() {
        termsPage = MobilePageFactory.buildInstance(driver, TermsPage.class, deviceType);
    }

    @Then("^the client clicks checkbox on Terms page")
    public void the_client_clicks_checkbox() {
        termsPage.clickCheckbox();
    }

    @Then("^the client clicks accept button on Terms page")
    public void the_client_clicks_accept_button_on_term_page() {
        termsPage.clickAcceptButton();
    }

    @Then("^the client clicks save preferences button on Data Protection Policy page")
    public void the_client_clicks_save_preferences_button_on_dpp_page() {
        dataProtectionPolicyPage.clickSavePreferencesButton();
    }

    @Then("^the client sees the invalid card error toast message(.+)$")
    public void the_client_validates_invalid_card_toast_message(String invalidCard) {
        registrationPage.verifiesToastMessage(getValue(invalidCard));
    }

    @Then("^the client validated the toast message")
    public void the_client_validates_toast_message_on_term_page() {
        termsPage.clickAcceptButton();
    }

    @Then("^the client clicks OK button on Terms page")
    public void the_client_clicks_ok_button_on_term_page() {
        termsPage.clickOKButton();
    }

    @When("^the client sees Tutorial page")
    public void the_client_sees_tutorial_page() {
        tutorialPage = MobilePageFactory.buildInstance(driver, TutorialPage.class, deviceType);
    }

    @When("^the client sees Flybits opt-in page")
    public void the_client_sees_flybits_opt_in_page() {
        flybitsOptInPage = MobilePageFactory.buildInstance(driver, FlybitsOptInPage.class, deviceType);
    }

    @Then("^the client sees Location Services page")
    public void the_client_sees_location_services_page() {
        localtionServicesPage = MobilePageFactory.buildInstance(driver, LocaltionServicesPage.class, deviceType);
    }

    @Then("^the client sees tutorial message and swipe to next in(.+)$")
    public void the_client_sees_tutorial_message_and_swipe_to_next(String tutorialBody) {
//        tutorialPage.swipeLeft(tutorialBody.trim());
        tutorialPage.clickSkipButton();
    }

    @Then("^the client sees tutorial message Eligible and clicks continue button")
    public void the_client_sees_tutorial_message_and_clicks_continue_button() {
        tutorialPage.clickContinueButton();
    }

    @Then("^the client clicks continue button in the Flybits page")
    public void the_client_clicks_continue_button_on_flybits_page() {
        flybitsOptInPage.clickContinueForFlybitsOptIn();
    }

    @Then("^the client clicks skip button in Location Services page")
    public void the_client_clicks_continue_button_on_location_services_page() {
        localtionServicesPage.clickContinueForLocationServices();
    }

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
