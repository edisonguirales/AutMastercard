package com.mastercard.lts.rewards.pwrautomation.step_definitions;
import com.mastercard.lts.rewards.pwrautomation.enums.AuthAccountIdType;
import com.mastercard.lts.rewards.pwrautomation.enums.AuthProcessingCode;
import com.mastercard.lts.rewards.pwrautomation.exception.ApiException;
import com.mastercard.lts.rewards.pwrautomation.model.AuthorizationRequest;
import com.mastercard.lts.rewards.pwrautomation.pages.*;
import com.mastercard.lts.rewards.pwrautomation.service.AuthorizationService;
import com.mastercard.lts.rewards.pwrautomation.utils.ScrollViewType;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static com.mastercard.lts.rewards.pwrautomation.utils.TestContext.getValue;

public class HomeSteps extends AbstractSteps {

    private static ThreadLocal<Boolean> toggleValue = ThreadLocal.withInitial(() -> null);

    private HomePage homePage;
    private AddNewCardPage addNewCardPage;
    private FAQPage faqPage;
    private CalculatorPage calculatorPage;
    private SettingsPage settingsPage;
    private ActivityPage activityPage;
    private PromotionsPage promotionsPage;

    private AuthorizationService authorizationService;

    public HomeSteps(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }


    @When("^the client sees home page")
    public void the_client_sees_home_page() {
        homePage= MobilePageFactory.buildInstance(driver, HomePage.class, deviceType);
    }

    @When("^the client should click on activate pay with rewards button")
    public void the_client_clicks_activate_pwr_button(){
        homePage.clickActivatePWR();
    }

    @When("^the client should click on deactivate pay with rewards button")
    public void the_client_clicks_de_activate_pwr_button(){
        homePage.clickDeActivatePWR();
    }

    @When("^the client post an auth with pwr turned off to do the reprocess")
    public void the_client_deactivate_pwr_and_post_auth() {
//        postAnAuth();
    }

    public void postAnAuth(String transactionAmount, String settlementAmount, String billingAmount,String acceptorName,String accountNumber,String programID, String serviceID, String acquiringInsIDCode, String cardAcceptorIDCode) {
        AuthorizationRequest authorizationRequest = new AuthorizationRequest();
        authorizationRequest.setAccountId(accountNumber);
        authorizationRequest.setAccountIdType(AuthAccountIdType.BAN);
        authorizationRequest.setExternalId(RandomStringUtils.randomAlphabetic(12));
        authorizationRequest.setAcquiringInstitutionIdCode(acquiringInsIDCode);
        authorizationRequest.setCardAcceptorIdCode(acquiringInsIDCode);
        authorizationRequest.setCardAcceptorNameAndLocation(acceptorName);
        authorizationRequest.setCardholderBillingAmount(billingAmount);
        authorizationRequest.setCardholderBillingCurrencyCode("USD");
        authorizationRequest.setIssuerCountryCode("USA");
        authorizationRequest.setMerchantCategoryCode("3412");
        authorizationRequest.setProcessingCode(AuthProcessingCode.PURCHASE);
        authorizationRequest.setQueueTransaction("N");
        authorizationRequest.setTransactionDateAndTime("2021-03-08T09:15:00-06:00");
        try {
            authorizationService.postAuthorizations(authorizationRequest, "/authorizations");
        }catch (ApiException | IOException apie)
        {
            apie.printStackTrace();
        }
    }


    @When("^the client clicks on next purchase button")
    public void the_client_clicks_next_purchases_button(){
        homePage.clickNextPurchase();
    }

    @When("^the client clicks on all purchases button")
    public void the_client_clicks_all_purchases_button(){
        homePage.clickAllPurchases();
    }

    @When("^the client sees promotions page")
    public void the_client_sees_promotions_page() {
        promotionsPage= MobilePageFactory.buildInstance(driver, PromotionsPage.class, deviceType);
    }

    @When("^the client clicks add new card icon")
    public void the_client_clicks_add_new_card_icon(){
        homePage.clickAddNewCardIcon();
    }

    @When("^the user verifies add new card icon and navigation menu exists")
    public void verifies_new_card_and_navigation_menu_icons(){
        homePage.isAddNewCardIconExists();
        homePage.isNavigationMenuIconExists();

    }

    @Then("^the client clicks banner")
    public void the_client_clicks_banner() throws Throwable{
        homePage= MobilePageFactory.buildInstance(driver, HomePage.class, deviceType);
        homePage.clickBanner();
    }

    @Then("^validate the available points, available cash, card number-last 4 digits and calculator icon is visible on home page")
    public void all_elements_are_visible_on_home_page(){
        Assert.assertTrue("Available points balance should be visible on home page",homePage.availablePoints.isDisplayed());
        Assert.assertTrue("Available cash balance should be visible on home page",homePage.availableCash.isDisplayed());
        Assert.assertTrue("Last four digit card number should be visible on home page",homePage.cardNumber.isDisplayed());
        Assert.assertTrue("Calculator button should be visible on home page",homePage.calculatorButton.isDisplayed());
    }
    @Then("^tabs exist on home page")
    public void tabs_exist_on_home_page(List<String> tabNames) {
        String promotions = new String("الإعدادات ".getBytes(), StandardCharsets.UTF_8);
        String activity = new String("نشاط".getBytes(), StandardCharsets.UTF_8);
        String settings = new String("الترقيات".getBytes(), StandardCharsets.UTF_8);
        List<String> arabicStrings = new ArrayList<>();
        arabicStrings.add(promotions);
        arabicStrings.add(activity);
        arabicStrings.add(settings);
        for (String tabName:arabicStrings) {
            String name = getValue(tabName);
//            String lang = System.getProperty("session.id");
            Assert.assertTrue("Tab name "+name+" should be displayed",homePage.tabInScrollViewContains(tabName));
        }
    }

    @Then("^user enters the invalid email and validate the error message")
    public void validate_invalid_emails(List<String> emailIds){
        for (String emailId:emailIds) {
           settingsPage.enterEmailAddress(emailId.trim());
            Assert.assertEquals(getValue("home.email.invalid.msg"),settingsPage.validateInvalidEmailMessage(getValue("home.email.invalid.msg")));
            settingsPage.clickCloseButton();
        }
    }

    @Then("^user verifies the valid emails")
    public void validate_valid_emails(List<String> emailIds){
        for (String emailId:emailIds) {
            settingsPage.enterEmailAddress(emailId.trim());
        }
    }

    @Then("^validate slide in menu contains items")
    public void slide_in_menu_contains_items(List<String> items){
        homePage.clickBurgerMenu();
        String home = new String("الصفحة الرئيسية".getBytes(), StandardCharsets.UTF_8);
        String authSettings = new String("إعدادات المصادقة".getBytes(), StandardCharsets.UTF_8);
        String tc = new String("الشروط والقواعد".getBytes(), StandardCharsets.UTF_8);
        String tutorial = new String("الدورة التعليمية".getBytes(), StandardCharsets.UTF_8);
        String faq = new String("التعليمات".getBytes(), StandardCharsets.UTF_8);
        String signOut = new String("خروج".getBytes(), StandardCharsets.UTF_8);
        List<String> arabicStrings = new ArrayList<>();
        arabicStrings.add(home);
        arabicStrings.add(authSettings);
        arabicStrings.add(tc);
        arabicStrings.add(tutorial);
        arabicStrings.add(faq);
        arabicStrings.add(signOut);
        for (String item:arabicStrings) {
//            String name = getValue(item);
            Assert.assertTrue("Menu item should be displayed",homePage.slideInMenuContains(item));
        }
        //return home page
        homePage.selectSlideInMenuItem("الصفحة الرئيسية");
//        homePage.selectSlideInMenuItem(getValue("home.menu.home"));
    }

    @Then("^the user clicks on the (.+) button in the side menu")
    public void clicks_sign_out(String signOutButton){
        homePage.clickBurgerMenu();
            String name = getValue(signOutButton);
            homePage.clickSignOut(name);
    }

    @When("^the client navigates to page(.+)$")
    public void the_client_navigates_to_page(String pageName){
        String menuName = getValue(pageName);
        String arabicMenuName;
        assert menuName != null;
        switch (menuName)
        {
            case "Home":
                arabicMenuName = "الصفحة الرئيسية";
                break;
            case "Authentication Settings":
                arabicMenuName = "إعدادات المصادقة";
                break;
            case "Terms & Conditions":
                arabicMenuName = "الشروط والقواعد";
                break;
            case "Tutorial":
                arabicMenuName = "الدورة التعليمية";
                break;
            case "FAQ":
                arabicMenuName = "التعليمات";
                break;
            case "Sign Out":
                arabicMenuName = "خروج";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + menuName);
        }

        homePage= MobilePageFactory.buildInstance(driver, HomePage.class, deviceType);
        homePage.clickSlideInMenuWithName(arabicMenuName);
    }

    @Then("^the client selects three segment control (.+)$")
    public void the_client_selects_three_segment_control(String controlName) {
        homePage.selectControlWithText(getValue(controlName));
    }

    @Then("^the user clicks on the segment control with (.+)$")
    public void the_user_clicks_on_three_segment_control(String controlName) {
        settingsPage.selectControlWithText(getValue(controlName));
    }

    @Then("^the client selects tab control(.+)$")
    public void the_client_selects_tab_control(String controlName) {
        String tabName = getValue(controlName);
        String  arabicTabName;
        assert tabName != null;
        switch (tabName)
       {
           case "PROMOTIONS":
               arabicTabName = "الترقيات NEW MC EN";
               break;

           case "ACTIVITY":
                arabicTabName = "نشاط";
                break;

           case "SETTINGS":
                arabicTabName = "الإعدادات NEW MC EN";
               break;
           default:
               throw new IllegalStateException("Unexpected value: " + tabName);
       }
//        homePage.selectTabInScrollView(getValue(controlName));
        homePage.selectTabInScrollView(arabicTabName);
    }

    @Then("^the client verifies the activity list")
    public void the_client_verifies_activity_list() {
        activityPage.verifiesActivityList();
    }

    @Then("^the client swipes the page up")
    public void the_client_swipes_page_up() {
        promotionsPage.swipeUp();
    }

    @Then("^the client validates the transaction description text(.+)$")
    public void the_client_validates_transaction_text(String descText) {
     Assert.assertEquals(getValue(descText),activityPage.readRedeemText(getValue(descText)));
    }

    @Then("^the client validates the redeem button")
    public void client_validates_redeem_button() {
        Assert.assertTrue(activityPage.verifyRedeemButtonIsDisplayed());
    }

    @Then("^the client validates the activity help hint(.+)$")
    public void the_client_validates_activity_help_hint(String activityPageHelpHint){
        activityPage.clickActivityHelpHintIcon();
        Assert.assertTrue("Hint text isn't displayed",activityPage.isPresentAndDisplayed(By.xpath("//*[contains(@text,\""+getValue(activityPageHelpHint)+"\")]")));
       // waitExecutionFor(2);
    }

    @Then("^the client sees add new card page")
    public void the_client_sees_add_new_card_page(){
        addNewCardPage = MobilePageFactory.buildInstance(driver,AddNewCardPage.class,deviceType);
    }

    @Then("^the client sees activity page")
    public void the_client_sees_activity_page(){
        activityPage = MobilePageFactory.buildInstance(driver,ActivityPage.class,deviceType);
    }

    @Then("^the client clicks on the redeem button")
    public void the_client_clicks_redeem_button(){
        activityPage.clickOnRedeemButton();
    }

    @Then("^the client clicks the card acceptor name")
    public void the_client_activity_name(){
        activityPage.clickActivityName();
    }

    @Then("^the client clicks confirmation for the redeem")
    public void the_client_clicks_confirms_the_redeem() {
        activityPage.clickOnConfirmRedeemButton();
    }

    @Then("^the user verifies successfully redeemed confirmation message(.+)$")
    public void the_user_verifies_redeemed_message(String successMessage) {
        Assert.assertEquals(activityPage.verifiesSuccessfulRedemption(),getValue(successMessage));
    }

    @Then("^the client deletes the previously added card(.+)$")
    public void the_client_clicks_on_the_card(String cardNumber){
        addNewCardPage.deletePreviouslyAddedCard(cardNumber.trim());
    }
    @Then("^validate the message(.+)$")
    public void the_client_validates_no_recent_activity_message(String message) {
        Assert.assertEquals(getValue(message),activityPage.getNoRecentActivityText());
    }

    @Then("^the client enters point amount (.+)$")
    public void the_client_enters_point_amount(String pointAmount) {
        calculatorPage.enterPointAmount(pointAmount);
    }
    @Then("^the client enters cash value (.+)$")
    public void the_client_enters_cash_value(String cashAmount) throws Throwable{
        calculatorPage.enterCashAmount(cashAmount);
        calculatorPage.clicksOneOffer();
        calculatorPage.getPointValueAnyWhere();
        calculatorPage.getPromotionalPointsValue();
    }
    @Then("^the client chooses to add a new card")
    public void the_client_chooses_to_add_new_card(){
        addNewCardPage.addNewCardProcess();
    }

    @Then("^the client returns home page from (.+)$")
    public void the_client_returns_home_page_from(String page) {
        if (page.equalsIgnoreCase("AddNewCardPage")) {
//            addNewCardPage.clickSlideInMenuWithName(getValue("home.menu.home"));
            addNewCardPage.clickSlideInMenuWithName("الصفحة الرئيسية");
        }else if(page.equalsIgnoreCase("FAQPage")){
//        	faqPage.clickSlideInMenuWithName(getValue("home.menu.home"));
        	faqPage.clickSlideInMenuWithName("الصفحة الرئيسية");
        }else if(page.equalsIgnoreCase("SettingsPage")){
            settingsPage.swipeDown();
            settingsPage.swipeDown();
        }else if(page.equalsIgnoreCase("ActivityPage")){
            ActivityPage activityPage = MobilePageFactory.buildInstance(driver,ActivityPage.class,deviceType);
            activityPage.swipeDown();
        }else{
//            calculatorPage.clickSlideInMenuWithName(getValue("home.menu.home"));
            calculatorPage.clickSlideInMenuWithName(getValue("الصفحة الرئيسية"));
        }
    }


    @When("^the client swipes down from Activity Page")
    public void the_client_swipes_down_on_activity_page(){
//        activityPage.swipeDownWithNoActivity();
        activityPage.swipeDown();
        activityPage.swipeDown();
    }

    @When("^the client swipes down from Promotions Page")
    public void the_client_swipes_down_on_promotions_page(){
        promotionsPage.swipeDown();
        promotionsPage.swipeDown();
    }

    @Then("^the client returns from Activity Page to home page")
    public void the_client_returns_home_page(){
//        activityPage.swipeDownWithNoActivity();
        activityPage.swipeDown();
    }

    @Then("^the client returns to home page from Activity Page")
    public void the_client_returns_home_page_from_activity_Page() throws Throwable {
//        activityPage.swipeDownWithNoActivity();
        activityPage.clickSlideInMenuWithName("Home");
    }

    @Then("^the client sees program selection and select program (.+)*")
    public void the_client_sees_program_selection_and_select_program(String programName) throws Throwable{
        faqPage.selectProgramWithText(getValue(programName));
    }

    @Then("^the client sees FAQ page")
    public void the_client_sees_FAQ_page(){
        faqPage = MobilePageFactory.buildInstance(driver,FAQPage.class,deviceType);
    }

    @Then("^the client sees FAQ of selected program")
    public void the_client_sees_FAQ_of_selected_program(){
        Assert.assertTrue("FAQ is displayed for selected program",faqPage.faqIsDisplayed());
    }

    @Then("^there are more than (\\d+) offers")
    public void there_are_offers(int offerMin){
        Assert.assertThat("Number of offers",promotionsPage.visibleItemsNumberInScrollView(ScrollViewType.PROMOTION),greaterThanOrEqualTo(offerMin));
    }

    @Then("^the client clicks one offer and sees the offer image and offer end date")
    public void the_client_clicks_one_offer(){
        promotionsPage.clicksPromotion();
        waitExecutionFor(3);
        Assert.assertTrue("Offer Image should be displayed",promotionsPage.offerImageIsDisplayed());
        Assert.assertTrue("Offer end date should be displayed",promotionsPage.offerEndDateIsDisplayed());
    }

    @Then("^the client clicks the calculator button on the home page")
    public void the_client_clicks_the_calculator_button(){
        homePage.clicksCalculatorIcon();
    }

    @Then("^the client will click the calculator button in the offer page")
    public void the_client_clicks_the_calculator_button_in_offer_page(){
        promotionsPage.clickCalculatorInOfferPage();
    }

    @Then("^the client validates offer points")
    public void the_client_validates_offer_points(){
        Assert.assertTrue(calculatorPage.isPromotionalPointsDisplayed());
    }

    @Then("^the client will get slider minimum amount")
    public void the_client_gets_slider_min_amount(){
        String sliderAmount = calculatorPage.getSliderAmount();
        Assert.assertEquals("0",sliderAmount);
    }

    @Then("^the client will also get slider maximum amount and compare with available points")
    public void the_client_gets_slider_max_amount(){
        int pointValue = Integer.parseInt(calculatorPage.getAvailablePoints().replace(",",""));
        int sliderAmount = Integer.parseInt(calculatorPage.getSliderAmount().replace(",",""));
        Assert.assertEquals(pointValue*2.,sliderAmount,0);
    }

    @Then("^the client checks for the current cash value listed under points anywhere")
    public void the_client_check_for_any_where_point_value(){
        assertPromotionalValue(calculatorPage.getPointValueAnyWhere());
    }

    @Then("^the client verifies the enter rewards button exists")
    public void the_client_verifies_enter_rewards_button(){
        calculatorPage.isEnterRewardsButtonDisplayed();
    }

    @Then("^the client also verifies the enter cash amount button exists")
    public void the_client_verifies_enter_cash_amount_button(){
        calculatorPage.isEnterCashAmountButtonDisplayed();
    }

    @Then("^the client should see the burn offers below card calculator under calculator page")
    public void the_client_should_offers(){
        calculatorPage.verifyOffers();
    }

    @Then("^the client selects one of the offer and should see the promotional value updated")
    public void the_client_selects_offer_and_see_promotional_value_updated(){
        calculatorPage.selectOffer();
        assertPromotionalValue(calculatorPage.getPromotionalPointsValue());
    }

    @Then("^the client now unselect the offer selected before and should see the promotional value updated back to empty string")
    public void the_client_unselects_offer_and_see_promotional_value_reverted(){
        calculatorPage.selectOffer();
        Assert.assertTrue(calculatorPage.getPromotionalPointsValue().equalsIgnoreCase("---"));
    }

    private void assertPromotionalValue(String promotionalPointsValue) {
        Assert.assertTrue(promotionalPointsValue.equalsIgnoreCase("---"));
    }

    @Then("^the client enters points (.+) manually and the point value anywhere should change to the cash value based on the program's conversion rate(.+)$")
    public void the_client_updates_any_where_point_value(String pointValue,String conversionRate){
        calculatorPage.updatePointValueAnyWhere(pointValue);
        int sliderAmount = Integer.parseInt(pointValue)*Integer.parseInt(conversionRate.trim());
        float pointValueAnywhere = Float.parseFloat(calculatorPage.getPointValueAnyWhere());
        float pointsValue = pointValueAnywhere*100;
        Assert.assertEquals(sliderAmount, (int)pointsValue);
    }

    @Then("^the client will validate the promotional points value")
    public void the_client_validates_value_for_offer_points(){
        waitExecutionFor(2);
        assertPromotionalValue(calculatorPage.getPromotionalPointsValue());
    }
    @Then("^client moves the slider control to (.+) to get min or max value")
    public void the_client_validates_slider_control_for_available_points(String directionToMove){
        calculatorPage.swipeSlider(directionToMove.trim());
    }

    @Then("^the client enter the points manually to points anywhere to (.+)$")
    public void the_client_enters_points_manually(String pointValue){
        calculatorPage.updatePointValueAnyWhere(pointValue.trim());
        float pointValueAnywhere = Float.parseFloat(calculatorPage.getPointValueAnyWhere());
        float pointsValue = pointValueAnywhere*100;
        Assert.assertEquals(String.valueOf((int)pointsValue),calculatorPage.getSliderAmount());
    }

    @Then("^enters the cash value (.+) manually")
    public void the_client_enters_cash_manually(String cashValue){
        calculatorPage.enterCashAmount(cashValue.trim());
    }

    @Then("^verifies slider to maximum value which is twice the current points the user has")
    public void the_client_verifies_slider_amount_and_points_value(){
        int initialSliderAmt = Integer.parseInt(calculatorPage.getSliderAmount().replace(",",""))*2;
        calculatorPage.swipeSlider("left");
        calculatorPage.swipeSlider("right");
        String sliderAmtAfterSwipe = calculatorPage.getSliderAmount().replace(",","");
        Assert.assertEquals(Integer.toString(initialSliderAmt),sliderAmtAfterSwipe);
    }

    @Then("^verifies value shows under Anywhere Points, slider shows points based on the program conversion rate (.+), and promo points has dashed line")
    public void the_client_verifies_given_scenario_params(String conversionRate){
        DecimalFormat toTheFormat = new DecimalFormat("0.00");
        String sliderAmount = toTheFormat.format(Double.parseDouble(calculatorPage.getSliderAmount().replace(",",""))*Double.parseDouble(conversionRate.trim()));
        String pointsAnyWhere = calculatorPage.getPointValueAnyWhere().replace("$","");
        Assert.assertEquals(sliderAmount,pointsAnyWhere);
        verify_promotional_value();
    }

    @Then("^client will move the slider control to reflect some points")
    public void the_client_moves_slider_to_reflect_given_points(){
        calculatorPage.slideToReflectGivenPoints();
    }

    @Then("^the point value anywhere should change to reflect dollar based on program's conversion rate(.+)$")
    public void the_client_verifies_points_any_where_value(String conversionRate){
        waitExecutionFor(2);
        int sliderAmount = Integer.parseInt(calculatorPage.getSliderAmount())*Integer.parseInt(conversionRate.trim());
        float pointValueAnywhere = Float.parseFloat(calculatorPage.getPointValueAnyWhere());
        Assert.assertEquals(sliderAmount, (int)pointValueAnywhere);
    }

    @Then("^the dashed line through promo points section")
    public void verify_promotional_value(){
        Assert.assertTrue(calculatorPage.getPromotionalPointsValue().equalsIgnoreCase("---"));
    }

    @Then("^verify point value anywhere should change back to the user's current cash value")
    public void verify_points_value_any_where(){
         String sliderAMount = calculatorPage.getSliderAmount();
         Assert.assertEquals(calculatorPage.getAvailablePoints(),sliderAMount);
    }

    @Then("^the client clicks back button on the calculator page")
    public void the_client_clicks_back_button_on_app(){
        calculatorPage.clickBackButton();
    }


    @Then("^the client should see help popup with message containing (.+) and dismiss by clicking icon")
    public void the_client_should_see_help_popup_and_dismiss_by_clicking_icon(String message){
        String msgText = getValue(message);
        waitExecutionFor(10);
        calculatorPage.clickHelperIcon();
        Assert.assertTrue("Help message should be displayed",calculatorPage.isHelpHintDisplayed(msgText));
    }

    @Then("^the client clicks cancel button")
    public void the_client_clicks_cancel_button(){
        promotionsPage.clicksCancelButton();
    }


    @Then("^user sees the calculator page")
    public void the_client_should_see_calculator_page(){
        calculatorPage = MobilePageFactory.buildInstance(driver,CalculatorPage.class,deviceType);
    }
    @Then("^the client sees settings page")
    public void the_client_sees_settings_page(){
        settingsPage=MobilePageFactory.buildInstance(driver,SettingsPage.class,deviceType);
        //Assert.assertTrue("Rewards setting not disappeared",settingsPage.rewardsSettingsSectionsExist());
    }

    @Then("^validate the settings page has the sections(.+)")
    public void verify_sections_exists_on_settings_page(String sectionName) {
        String name = getValue(sectionName);
        assert name != null;
        String deviceName = System.getProperty("device.name");
        if(deviceName.equalsIgnoreCase("Galaxy S8+")) {
            name = name.toUpperCase();
        }
        Assert.assertEquals(name, settingsPage.sectionsInSettingsPageContains(name));
    }

    @Then("^user verifies pay with rewards is turned off")
    public void verify_pwr_is_turned_off() {
        Assert.assertTrue(settingsPage.isNoPurchaseOptionIsSelected());
    }

    @Then("^user verifies purchase minimum amount is (.+)")
    public void verifies_purchase_minimum(String minPurchaseAmount) {
        Assert.assertEquals(settingsPage.getMinCashFieldText(),minPurchaseAmount.trim());
    }

    @Then("^the client clicks on the email field")
    public void the_client_clicks_on_email_field() {
        waitExecutionFor(2);
        settingsPage.clickEmailField();
    }


    @Then("^the client clicks on the done button")
    public void the_client_clicks_on_done_button() {
        settingsPage.clickDoneButton();
    }

    @Then("^the client validates the error message(.+)$")
    public void the_client_validate_invalid_email_error_message(String errorMessage) {
        Assert.assertEquals(getValue(errorMessage),settingsPage.validateInvalidEmailMessage(getValue(errorMessage)));
        settingsPage.clickCloseButton();
    }

    @Then("^the client validates the email(.+)$")
    public void the_client_validates_email(String emailAddress) {
        Assert.assertEquals(emailAddress.trim(),settingsPage.getEmailAddress());
    }


    @Then("^the client can enter value (.+) to (.+) field")
    public void the_client_enters_value_to_min_cash_field(String value,String fieldName) {
        if(fieldName.trim().equalsIgnoreCase("minimum cash")) {
            settingsPage.enterValueToMinCashField(value.replaceAll("[^\\d.]", ""));
        }
    }

    @Then("the user swipes (.+) in settings page")
    public void the_swipes_up_to_see_settings_page(String swipeDirection) {
        if (swipeDirection.equalsIgnoreCase("up")) {
            settingsPage.swipeUpToSeeRewardsTitle();
        } else {
            settingsPage.swipeDownToSeeRewardsTitle();
        }
    }

    @Then("^the client validates the currency symbol(.+)$")
    public void the_client_validates_currency_symbol(String symbol) throws Throwable{
        System.out.println("Currency Symbol"+settingsPage.getCurrencySymbolText());
            Assert.assertTrue(settingsPage.getCurrencySymbolText().contains(symbol.trim()));

    }

    @Then("^the client clicks on activity selector")
    public void the_client_clicks_activity_selector() {
        activityPage.clickActivitySelector();
    }

    @Then("^the client clicks on load more button under eligible purchases")
    public void the_client_clicks_load_more_button() {
        activityPage.clickLoadMoreButton();
    }

    @Then("^the client verifies (.+) toast message with (.+)$")
    public void the_client_verifies_the_toast_message(String toastMessage, String SwitchValue) {
        if (SwitchValue.trim().equalsIgnoreCase("on")) {
            Assert.assertTrue(activityPage.verifiesToastMessage(getValue(toastMessage)));
        } else {
            Assert.assertFalse(activityPage.verifiesToastMessage(getValue(toastMessage)));
        }
    }

    @Then("^the user cancels the if the rating dialog displays")
    public void the_user_cancels_rating_dialog()  {
       activityPage.cancelRatingDialog();
    }

    @Then("^verify toast is not displayed(.+)")
    public void client_verifies_toast_is_not_displayed(String toastMessage) {
        Assert.assertFalse(activityPage.verifiesToastMessage(getValue(toastMessage)));
    }

    @Then("^the client selects activity (.+)$")
    public void the_client_clicks_continue_button(String activityToSelect) {

//        activityPage.scrollToActivityToSelect(activityToSelect.trim());
        activityPage.scrollToActivityToSelect("المشتريات المؤهلة");
    }

    @Then("^the client clicks on the transaction points")
    public void the_client_clicks_on_reedemed_text() {
        activityPage.clickActivityList(ScrollViewType.ACTIVITY);
    }

    @Then("^the client validates the pop up message(.+)$")
    public void the_client_validates_pop_up_message(String acceptorName) {
        List<String> popUpMessage = activityPage.readPopUpMessageText();
        if(popUpMessage!=null)
        {
           Assert.assertTrue(popUpMessage.contains(acceptorName.trim()));
        }
    }

    @Then("^the client cannot enter value (.+) to (.+) field")
    public void the_client_cannot_enter_value_to_minimum_cash_field(String value, String fieldName) {
        if(fieldName.equalsIgnoreCase("minimum cash")) {
            settingsPage.enterValueToMinCashField(value);
            Assert.assertEquals(settingsPage.getMinCashFieldText(), value);
        }
    }
    @Then("^the user updates the (.+) switch")
    public void the_user_changes_switch_value(String toggleSwitch) {
        if (toggleSwitch.trim().equalsIgnoreCase("Redemption Reminder")) {
            settingsPage.clickSwitch(settingsPage.redemptionReminderSwitch);
        } else if (toggleSwitch.trim().equalsIgnoreCase("Rewards Reminder")) {
            settingsPage.clickSwitch(settingsPage.rewardsReminderSwitch);
        } else if (toggleSwitch.trim().equalsIgnoreCase("Ineligible Purchases")) {
            settingsPage.clickSwitch(settingsPage.ineligiblePurchaseSwitch);
        } else {
            settingsPage.clickSwitch(settingsPage.emailNotificationSwitch);
        }
    }

    @Then("^the client toggles (.+) Switch(.+)$")
    public void the_client_toggles_switch(String toggleSwitch,String switchValue) {
        if (toggleSwitch.trim().equalsIgnoreCase("Redemption Reminder")) {
            validateSwitch(switchValue, settingsPage.redemptionReminderSwitch);
        } else if (toggleSwitch.trim().equalsIgnoreCase("Rewards Reminder")) {
            validateSwitch(switchValue, settingsPage.rewardsReminderSwitch);
        } else if (toggleSwitch.trim().equalsIgnoreCase("Ineligible Purchases")) {
            validateSwitch(switchValue, settingsPage.ineligiblePurchaseSwitch);
        } else {
            settingsPage.turnSwitchOn(settingsPage.emailNotificationSwitch);
        }
    }

    private void validateSwitch(String switchValue, WebElement switchElement) {
        waitExecutionFor(5);
        if (switchValue.trim().equalsIgnoreCase("on")) {
            settingsPage.turnSwitchOn(switchElement);
        } else {
            settingsPage.turnSwitchOff(switchElement);
        }
    }

    @Then("^the client turns of the RedemptionReminderSwitch")
    public void the_client_turns_off_RedemptionReminderSwtich() {
//        Assert.assertFalse(settingsPage.turnReminderSwitchOff(settingsPage.redemptionReminderSwitch));
    }

    @Then("^the IneligiblePurchaseSwitch is turned off")
    public void the_client_turns_off_ineligiblePurchaseSwitch() {
//        Assert.assertFalse(settingsPage.turnReminderSwitchOff(settingsPage.ineligiblePurchaseSwitch));
    }

    @Then("^the client gets redemption reminder switch initial value")
    public void the_client_sets_redemption_reminder_switch_on() {
        boolean initialValue = settingsPage.getRedemptionReminderSwitchText(settingsPage.redemptionReminderSwitch);
        toggleValue.set(initialValue);
    }

    @Then("^the client clicks button with text and verify if settings are changed")
    public void the_client_clicks_button_with_text(List<String> list) {
        for(String text :list){
            String settingsText = settingsPage.clickPurchaseButton(getValue(text));
            Assert.assertEquals(getValue(text), settingsText.trim());
        }
    }

    @Then("^the client validates the toggles switches")
    public void the_client_validates_toggles() {
        Assert.assertEquals(toggleValue.get(),settingsPage.getRedemptionReminderSwitchText(settingsPage.redemptionReminderSwitch));
    }

    @Then("^the client clicks hint icon (.+) and sees message(.+)$")
    public void the_client_clicks_hint_icon_and_sees_message(String iconName, String message) {
        String helpHintIconName = Objects.requireNonNull(getValue(iconName));
        if (helpHintIconName.equalsIgnoreCase(getValue("home.communication.preferences"))) {
            settingsPage.clickCommPreferenceHelpHintIcon();
        }else if (helpHintIconName.equalsIgnoreCase(getValue("home.email.preferences"))){
            settingsPage.clickEmailNotificationHelpHintIcon();
        }
        Assert.assertTrue("Hint text isn't displayed",settingsPage.isPresentAndDisplayed(By.xpath("//*[contains(@text,\""+getValue(message)+"\")]")));
    }

    @Then("^the client clicks hint icon (.+) and not see message(.+)$")
    public void the_client_clicks_hint_icon(String iconName, String message) {
        settingsPage.clickHintIcon(Objects.requireNonNull(getValue(iconName)));
        Assert.assertFalse("Hint text is still displayed",settingsPage.isPresentAndDisplayed(By.xpath("//*[contains(@text,\""+getValue(message)+"\")]")));
    }

    @Then("^run the database transaction with the following parameters (.+)and(.+)and(.+) and (.+) and (.+) and (.+) and (.+) and (.+) and (.+)$")
    public void the_client_runs_database_transaction_included_acceptor_name(String transactionAmount, String settlementAmount, String billingAmount,String acceptorName,String accountNumber,String programID, String serviceID, String acquiringInsIDCode, String cardAcceptorIDCode) throws Throwable{
        homePage.dataBaseSetUp(transactionAmount.trim(),settlementAmount.trim(),billingAmount.trim(),acceptorName.trim(),accountNumber.trim(),programID.trim(),serviceID.trim(),acquiringInsIDCode.trim(),cardAcceptorIDCode.trim());
    }

    @Then("^post the transaction with the following parameters (.+)and(.+)and(.+) and (.+) and (.+) and (.+) and (.+) and (.+) and (.+)$")
    public void the_client_post_a_transaction(String transactionAmount, String settlementAmount, String billingAmount,String acceptorName,String accountNumber,String programID, String serviceID, String acquiringInsIDCode, String cardAcceptorIDCode) {
        postAnAuth(transactionAmount.trim(),settlementAmount.trim(),billingAmount.trim(),acceptorName.trim(),accountNumber.trim(),programID.trim(),serviceID.trim(),acquiringInsIDCode.trim(),cardAcceptorIDCode.trim());
    }

    @Then("^the client closes the app")
    public void close() throws Throwable {
        driver.closeApp();
        reportiumClient.testStop(testResult.getTestResult());
        driver.close();
        driver.quit();
    }
}
