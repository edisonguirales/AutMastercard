# PWR Automation Test
* * * 

## Getting Started
***
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes

### Prerequisites
1. JDK 8.0 (or above)
2. Apache Maven
3. Maven, Cucumber and Perfecto plugins if using an IDE

### Installing
Maven and Cucumber plugins can be installed directly from the IDE.  
>For Eclipse
>Go to `Help -> Eclipse Marketplace` and search the desired plugin at the search bar on the top, and install the plugins. 

To install Perfecto plugin, [click here](https://developers.perfectomobile.com/display/PD/Eclipse) for Eclipse and [here](https://developers.perfectomobile.com/display/PD/IntelliJ+IDEA) for IntelliJ

## Running the Test
* * * 
To run the test, run  
`clean install test -ParametersYouWantToPass`

Example:
mvn clean install test -Ddevice.name="Galaxy S7" -Dspring.profiles.active=android,settings,en_US -Dcucumber.options="--tags @happyPath" -Duser.name=minjie.xu@mastercard.com -Duser.password=minjiesPWHere

You can see the different parameters in the following sections to configure your testing. 

### Accessing Perfecto Lab
There are two ways to run the test via Perfecto
1. Access via security token  
Go to Perfecto Lab -> More -> Create Security Token. Copy the token and paste into a text file with .txt extension.  
Pass parameter `-Duser.security.token.path=Path/To/Your/Security/Token/File.txt`
>Ex: -Duser.security.token.path=/Users/e012345/Desktop/securityToken.txt 

2. Access via username and password, make sure you have your Perfecto credentials. And pass the parameter  
 `-Duser.name=email@mastercard.com -Duser.password=yourPassword`

### Specifying Device Model
To specify a device model to run the test, pass the parameter: 
 `-Ddevice.name="Device Model Name"`
>Ex: -Ddevice.name="Galaxy S7"

An important note: if no device model is specified from command line, there are default android devices to run the test on. 

### Specifying Test Language
To specify a language to run the test in, pass the parameter:  
`-Dspring.profiles.active=android,settings,language_properties`
>Ex: -Dspring.profiles.active=android,settings,en_US

where `en_US` is the language properties for English (United States).  
The first two properties file `android,settings` remains constant regardless of the language  

Below is a table for languages and their corresponding properties file names.   
| Language                 | Properties File |
|--------------------------|-----------------|
| English (United States)  | en_US           |
| English (United Kingdom) | en_GB           |
| French (Canada)          | fr_CA           |
| Polish (Poland)          | pl_PL           |
| Portuguese (Brazil)      | pt_BR           |
| Spanish (Mexico)         | es_MX           |
| Simplified Chinese       | zh_CN           |
| Traditional Chinese      | zh_HK           |
| Ukrainian (Ukraine)      | uk_UA           |       

### Specifying Cucumber Tags
to specify a particular group of test scenarios to run using cucumber tags:  
`-Dcucumber.options="--tags @Tags`
>Ex: -Dcucumber.options="--tags @smoke

### Run Test using Jenkins
* Login to production firewall from below url:
    >https://10.154.18.50/connect/PortalMain
* Access Pay with Rewards pipeline here
    >https://cd.mastercard.int/jenkins/job/PayWithRewards/job/PWRRegressionTest/
* Click `Build with Parameters`, configure the Device Model and language you want to run test run i.e. `Galaxy S7` and `en_US`        
* If you want to run test on multiple devices and languages, you can configure multiple device models and languages with comma separated. 

***
## Useful resources
* Perfecto Developer Portal:
    >https://developers.perfectomobile.com/display/PD/Documentation
* Perfecto Mobile Training
	* go to Degreed
    * search for perfecto, you will find a Perfecto Mobile course
* Cucumber
    >https://docs.cucumber.io/

***
## What's next
Add iOS automation to existing test cases
* Prerequisite:
	* iOS app needs to have accessibility or ID configured for elements to perform actions such as button click, swipe, scroll, etc. and for retrieving values and text
	* Good understanding of testing framework Cucumber, library Selenium, appium, platform Perfecto, Spring framework
	* Create iOS .ipa builds with Perfecto iPhone devices in provision that automation will run on. Suggest adding multiple devices in case of unavailability
* Automation configuration for iOS:
	* Setup iOS driver and report information in IOSDriverConfiguration.java class, driver is responsible for opening connection, initial setup and selecting devices etc.
	* Create an application-iOS.properties file that takes needed information for starting a connection
* Added iOS elements and tests
	* Test cases should remain close to android test cases. When iOS comes into picture, you can start updating for iOS page by page or test by test.
	* Go to page classes, start from adding @IOSFindBy() annotations to elements, note not all elements can use annotation, in that case, you can handle iOS by checking current device type running -- if(getDeviceType().equals(DeviceType.IOS)){}
	* Also need to handle actions such as swipe up & down on a view, that requires coding
	* Special actions such as toggle airplane mode, change language etc.
	* Update Steps classes if needed to ensure the verification of tests
* Others: (Nice to have)
	* Setup Jenkins pipeline for iOS automation
	* Update report for useful information
	* Multi language tests
* Challenges:
	* Perfecto may have limitations, or some features are not well documented
 
