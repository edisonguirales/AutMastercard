Feature: client go through home page

  @regression
  Scenario: client goes activity page and then goes to the home page.
    When the client sees activity page
    And the client swipes down from Activity Page
    Then the client sees home page

  @regression
  Scenario: client goes through home page and validate promotions,settings and activity tabs. Also validates Total Points,Last four digits of card number,Calculator Icon
    When the client sees home page
    Then validate the available points, available cash, card number-last 4 digits and calculator icon is visible on home page
    And tabs exist on home page
      | home.tab.promotions |
      | home.tab.activity   |
      | home.tab.settings   |

  @regression @postRegistration
  Scenario: client goes through home page and validates the side menu with navigation tabs
    When the client sees home page
    Then validate slide in menu contains items
      | home.menu.home                    |
      | home.menu.authentication.settings |
      | home.menu.terms.conditions        |
      | home.menu.tutorial                |
      | home.menu.faq                     |
      | home.menu.signout                 |

  @regression
  Scenario: clients goes through calculator page and validate scroll bar, points and points value
    When the client sees home page
    And the client clicks the calculator button on the home page
    Then user sees the calculator page
#    And the client should see help popup with message containing home.calculator.see.points.msg and dismiss by clicking icon
    And the client validates offer points
#    And client moves the slider control to left to get min or max value
#    And the client will get slider minimum amount
#    And client moves the slider control to right to get min or max value
#    And the client will also get slider maximum amount and compare with available points
    And the client checks for the current cash value listed under points anywhere
    And the client verifies the enter rewards button exists
    And the client also verifies the enter cash amount button exists
    And the client should see the burn offers below card calculator under calculator page
    And the client selects one of the offer and should see the promotional value updated
    And the client now unselect the offer selected before and should see the promotional value updated back to empty string


  @regression @postRegistration
  Scenario Outline: clients goes through calculator page and move the slider to reflect some points
    When user sees the calculator page
    And the client enters points <pointValue> manually and the point value anywhere should change to the cash value based on the program's conversion rate<conversionRate>
    And the dashed line through promo points section
    Examples:
      | pointValue | conversionRate |
      | 100        | 1                |

    @regression @postRegistration #check with Mike
  Scenario: clients manually enters 0 in points and validate the points value anywhere cash value
    And user sees the calculator page
    And the client enter the points manually to points anywhere to 0
    And verify point value anywhere should change back to the user's current cash value

#    @regression @postRegistration
#  Scenario Outline: clients manually enters the cash value and verifies value shows on left side under Anywhere Points, slider shows points based on the program conversion rate, and promo points has dashed line
#    When user sees the calculator page
#    And enters the cash value <cashValue> manually
#    Then verifies value shows under Anywhere Points, slider shows points based on the program conversion rate <conversionRate>, and promo points has dashed line
#    Examples:
#      | cashValue | conversionRate |
#      | 200       | 1              |

  @regression
  Scenario: clients select one of the offers with slider NOT at 0 and verifies point value with promo changes from dash to actual dollar value
    When user sees the calculator page
    Then the client selects one of the offer and should see the promotional value updated

  @regression
  Scenario: client goes back to the home page from Calculator page
    When user sees the calculator page
    Then the client returns home page from CalculatorPage
    And the client sees activity page
    And the client swipes down from Activity Page
    Then the client sees home page

  #---------------------------------------------------Promotions Page----------------------------------------------------------------


  @regression
  Scenario: client tap's on a promotion to see the modal with the offer's detail
    When the client sees home page
    And the client selects tab control home.tab.promotions
    Then the client sees promotions page
    Then there are more than 1 offers
    Then the client clicks one offer and sees the offer image and offer end date
    And the client will click the calculator button in the offer page
    And user sees the calculator page
    And the client validates offer points
    And the client will validate the promotional points value
    Then the client clicks back button on the calculator page
    Then the client clicks cancel button
    Then the client sees promotions page
    And the client selects tab control home.tab.activity
    And the client sees activity page
    And the client swipes down from Activity Page
    Then the client sees home page

#---------------------------------------------------Settings Page----------------------------------------------------------------

  @regression @postRegistration
  Scenario: client goes through settings page and verifies Reward Settings, Communication Preferences, Email Settings
    When the client sees home page
    And the client selects tab control home.tab.settings
    And the client sees settings page

  @regression
  Scenario: client goes through settings page and validates with invalid emails
    When the client sees settings page
    And user enters the invalid email and validate the error message
      | abcdef       |
      | abcdef@gmail |
      | ""           |

  @regression
  Scenario: client goes through settings page and validates with valid emails
    When the client sees settings page
    And user verifies the valid emails
      | 123456@mastercard.com  |
      | e075876@mastercard.com |
      | mobileship@mc.com  |

  @regression
  Scenario: client goes through settings page and verify the email address
    When the client sees settings page
    And the client clicks on the email field
    Then the client validates the email m*********@mc.com
    And the client clicks on the done button

  @regression
  Scenario Outline: client goes through settings page and verify the settings saved even after exit
    When the client sees settings page
    And the user updates the <SwitchName> switch
    And the client gets redemption reminder switch initial value
    And the client clicks home button
    And the client sees PIN page
    And the client enters PIN code 1357
    And the client sees activity page
    And the client swipes down from Activity Page
    Then the client sees home page
    And the client selects tab control home.tab.settings
    And the client sees settings page
    And the client validates the toggles switches
    Examples:
      |SwitchName|
      |Redemption Reminder|
      |Rewards Reminder|
      |Ineligible Purchases|
      |EMAIL PREFERENCES|
#
##  @regression
##  Scenario Outline: client goes through settings page and verify the help hints
##    When the client sees settings page
##    And the client clicks hint icon <helpHintIcon> and sees message <helpHintMessage>
##    Examples:
##      | helpHintIcon                   | helpHintMessage              |
##      | home.communication.preferences | home.decision.pwr            |
##      | home.email.preferences         | home.email.notifications.opt |

  @regression
  Scenario: client click on the navigation menu and goes to FAQ page
    When the client sees settings page
    Then the client returns home page from SettingsPage
    And the client sees home page
    And the client navigates to page home.menu.faq
    And the client sees FAQ page
    And the client sees FAQ of selected program
    And the client returns home page from FAQPage
    And the client selects tab control home.tab.activity
    And the client sees activity page
    Then the client returns home page from ActivityPage

#  @regression
#  Scenario: client adds a new card
#    When the client sees home page
#    Then the client clicks add new card icon
#    Then the client sees add new card page
#    And the client chooses to add a new card
#    Then the client sees registration page
#    Then the client enters card number 5104425500000263
#    And the client sees Security Question page
#    And the client clicks security question dropdown
#    Then the client selects security question with option home.security.question.last.four.digits.ssn
#    Then the client enters security question answer as 1234
#    Then the client returns home page from AddNewCardPage
#    And the client sees activity page
#    And the client swipes down from Activity Page
#    Then the client sees home page

#  @regression
#  Scenario: client clicks on the activate PWR and then click on next purchase
#    When the client sees home page
##    Then the client clicks on activate pay with rewards button
#    Then the client post an auth with pwr turned off to do the reprocess
##    And the client clicks on next purchase button

  @regression
  Scenario Outline: client initiate the transaction by changing banner tab, redemption switch value and verifies successful redemption push notification
    When the client sees home page
    Then the client should click on activate pay with rewards button
    And the client clicks on all purchases button
    And the client selects tab control home.tab.settings
    And the client sees settings page
    And the client toggles Redemption Reminder Switch <SwitchValue>
    And the client selects tab control home.tab.activity
    And the client sees activity page
    And post the transaction with the following parameters <TransactionAmount> and <SettlementAmount> and <BillingAmount> and <cardAcceptor> and <accountNumber> and <programID> and <serviceID> and <AcquiringInsIDCode> and <CardAcceptorIDCode>
    Then the client returns home page from ActivityPage
    Examples:
      | TransactionAmount | SettlementAmount | BillingAmount | cardAcceptor | accountNumber       | programID | serviceID | AcquiringInsIDCode | CardAcceptorIDCode | SwitchValue |
      | 000000000100      | 000000000100     | 000000000100  | MobileAutomationTest     | 5104425500000313000 | 10442     | 1638      | 3583               | 889444000485847    | on          |

  @regression
  Scenario Outline: client initiate the transaction by changing banner tab, redemption switch value and verifies successful redemption push notification
    When the client sees home page
    Then the client should click on activate pay with rewards button
    And the client clicks on next purchase button
    And the client selects tab control home.tab.settings
    And the client sees settings page
    And the client toggles Redemption Reminder Switch <SwitchValue>
    And the client selects tab control home.tab.activity
    And the client sees activity page
    And post the transaction with the following parameters <TransactionAmount> and <SettlementAmount> and <BillingAmount> and <cardAcceptor> and <accountNumber> and <programID> and <serviceID> and <AcquiringInsIDCode> and <CardAcceptorIDCode>
    Then the client returns home page from ActivityPage
    Examples:
      | TransactionAmount | SettlementAmount | BillingAmount | cardAcceptor | accountNumber       | programID | serviceID | AcquiringInsIDCode | CardAcceptorIDCode | SwitchValue |
      | 000000000100      | 000000000100     | 000000000100  | MobileAutomationTest      | 5104425500000313000 | 10442     | 1638      | 3583               | 889444000485847    | off          |

  @regression
  Scenario Outline: client initiate the transaction by selecting no purchases and by changing redemption switch value and also verifies successful redemption push notification
    When the client sees home page
    Then the client should click on deactivate pay with rewards button
    And the client selects tab control home.tab.settings
    And the client sees settings page
    And post the transaction with the following parameters <TransactionAmount> and <SettlementAmount> and <BillingAmount> and <cardAcceptor> and <accountNumber> and <programID> and <serviceID> and <AcquiringInsIDCode> and <CardAcceptorIDCode>
    And the client selects tab control home.tab.activity
    And the client sees activity page
    And the client clicks on activity selector
    And the client selects activity <ActivityName>
    And the client clicks on load more button under eligible purchases
    And the client clicks on the redeem button
    And the client clicks confirmation for the redeem
#    And the user verifies successfully redeemed confirmation message activity.success.redemption.dialog.msg
    And the client clicks confirmation for the redeem
#    And the user cancels the if the rating dialog displays
    Then the client returns home page from ActivityPage
    Examples:
     | TransactionAmount | SettlementAmount | BillingAmount | cardAcceptor | ActivityName       | accountNumber       | programID | serviceID | AcquiringInsIDCode | CardAcceptorIDCode | SwitchValue |
     | 000000000100      | 000000000100     | 000000000100  | MobileAutomationTest      | Eligible Purchases | 5204740009900006000 | 10442     | 1638      | 3583               | 889444000485847    | on          |
