@registrationFlow
Feature: client go through registration

#  @smoke @regression
#  Scenario: client goes to the settings page and changes language
#    When the client opens settings app and change the language
#    Then the client clicks home button


  @regression
  Scenario: client goes to splash page and clicks continue button
    When the client sees splash page
    Then the client clicks continue button
    And the client sees PIN page

  @regression
  Scenario: client sees help hint for first time and also enters sequential PIN code
    When the client sees PIN page
    Then validate the pin number help hint registration.create.four.digit.passcode
    And the client sees PIN page

  @regression
  Scenario Outline: client enters the sequential pin and validate the error message
    When the client sees PIN page
    And the client enters PIN code <pinCode>
#    And the client will see the sequential pin error toast message registration.sequential.pin.toast.msg
    Then client validates the pin field is empty in pin page
    Examples:
      | pinCode |
      | 1234    |
      | 4567    |

  @regression
  Scenario: client reenters the sequential pin and validate the error message
    When the client sees PIN page
    And the client enters PIN code 1234
#    And the client will see the sequential pin error toast message registration.sequential.pin.toast.msg
    Then client validates the pin field is empty in pin page

  @regression
  Scenario: client enters mismatched PIN code validates the error message and then enters valid pin
    When the client sees PIN page
    Then the client enters PIN code 1357
    And the client sees Confirm Pin page
    And the client confirms PIN code 1212
    Then client validates the pin field is empty in pin page
    Then the client enters PIN code 1357
    And the client sees Confirm Pin page
    And the client confirms PIN code 1357
    And the client sees registration page

  @regression
  Scenario Outline: Client tries to enter the invalid card number in card number field.
    When the client sees registration page
    And the client enters card number <cardNumber>
    Then validate the text box is empty for card number
#    And the client sees the invalid card error toast message registration.invalid.card.toast.msg
    Examples:
      | cardNumber       |
      | 5555555555555555 |

  @regression
  Scenario: client enters valid credit card number, security questions and enters security answer
    When the client sees registration page
    And the client enters card number 5104425500000313
    Then the client sees Security Question page
#    And the client validates the security question help hint icon registration.security.question.helphint
    And the client clicks security question dropdown
    And the client selects security question with option registration.security.question.ssn
    And the client enters security question answer as 1234


  @regression
  Scenario: client goes to the Terms page, click Decline button and validate OK and Cancel button are enabled
    When the client goes to Terms page
    And the client selects acknowledge checkbox on Terms page
    Then the client clicks decline button on Terms page
    And validate OK and Cancel button is enabled

  @regression
  Scenario: client goes to the Terms page, clicks Decline and Cancel button
    When the client goes to Terms page
    And the client selects acknowledge checkbox on Terms page
    And the client clicks decline button on Terms page
    And the client clicks Cancel button on Terms page
    Then the client goes to Terms page

  @regression
  Scenario: client go to the Terms page, clicks Accept, and validate OK and Cancel button are enabled
    When the client goes to Terms page
    And the client selects acknowledge checkbox on Terms page
    And the client clicks accept button on Terms page
    And validate OK and Cancel button is enabled

  @regression
  Scenario: client goes to the Terms page, clicks accept and Cancel button
    When the client goes to Terms page
    And the client selects acknowledge checkbox on Terms page
    And the client clicks accept button on Terms page
    And the client clicks Cancel button on Terms page
    Then the client goes to Terms page

  @regression
  Scenario: client go to the Terms page, clicks Accept, Ok button and sees the data privacy page
    When the client goes to Terms page
    And the client selects acknowledge checkbox on Terms page
    And the client clicks accept button on Terms page
    Then validate the agreement alert message registration.terms.conditions
    And the client clicks OK button on Terms page
    And the client sees data protection policy page

  @regression
  Scenario: client go to the Data Protection Policy page page, clicks Save Preferences and sees the tutorial page
    When the client sees data protection policy page
    And the client clicks save preferences button on Data Protection Policy page
    And the client sees Tutorial page

  @smoke @regression
  Scenario: client go through tutorial page
    When the client sees Tutorial page
    Then the client sees tutorial message and swipe to next in tutorial_body
#    And  the client sees tutorial message and swipe to next in tutorial_body
#    And the client sees tutorial message Eligible and clicks continue button
#
  @smoke @regression
  Scenario: client go through Flybits Optin page and click continue button
    When the client sees Flybits opt-in page
    Then the client clicks continue button in the Flybits page
    Then the client sees Location Services page
    Then the client clicks skip button in Location Services page
#
##  If we run this scenario then the app will be locked as we enter invalid card num ber thrice
##  @regression @registration @unhappyPath
##  Scenario: Client enters invalid and inactive credit card numbers
##    When the client sees registration page
##    Then the client enters card number  5101350000000006
##    And validate the text box is empty for card number
##
##  @smoke @registration @happyPath
##  Scenario: Client enters valid credit card number but which was CANCELED already
##    When the client sees registration page
##    And the client enters card number 5424181176774110
##    And the client clicks Ok button in registration page
##    And the client enters card number 5424181176774110
##    Then validate the lock screen
#
##  @regression
##  Scenario: Client enters valid credit card number but was not registered with PWR
##    When the client sees registration page
##    And the client enters card number 5424181176774110
##    Then validate the error message registration.card.not.registered.error.msg
##    And the client clicks Ok button in registration page

#  @smoke @regression
#  Scenario: client close the app
#    When the client closes the app
