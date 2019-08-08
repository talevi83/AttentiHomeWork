# Attenti Home Work
This project contains tests for Metric Conversion website and for weather.com vs openweathermap api.
This project is running by TestNG suite, therefore you need to run the test via the suite XML file:
The file is in this path in the project: /src/test/resources/TestPlan/RunTests.xml, click on this file right click, then run.

Design:
The project devided into several clsses:<br>
Under src/main/java/ there are the following packages:<br>
Data - contains WeatherData - a class that holds weather data such as Temperature, Humidity, Wind, Pressure.<br>
Properties - contains a Singleton class that reading the project properties from conf.properties file (locate in /src/test/resources)<br>
RestAPI - contains classes that operates the Rest API operations:
 * RestAPI - basic class of the Rest API actions of sending requests.
 * RestApiResponse - This class holds the response that we get from the requests.
 * RestRequestBuilder - Builder class for creating Rest request in more convinient way.
 * WeatherRestRequest - this class holds Rest Actions specific for getting weather data from the api.<br>
   this class is using the RestRequestBuilder and the RestApiResponse.<br>

SeleniumActionsHelpers - contins classes for selenium actions: 
 * SeleniumActions - this is the main selenium class which start the webdriver and holds 
   methods for actions on the web page - this class is a Singleton.<br>
 * CalcActions - this class is been used to operate the calculations on Metric Conversion website by using methods from SeleniumActions class.<br>
 * WeatherActions - this class is been used to operate the actions over the Weather website using the class SeleniumActions.

Under src/main/test there are the following classes:
 * MetricConvertionTests - this class contains the tests for the Metric Conversion.
 * WeatherTests - this class contains the tests for the weather, Weather.com vs openweathermap api.
 * SeleniumWebDriverKiller - this class's role is to close the webdriver after the tests are done (using SeleniumActions).
 
 Also under src/main/test/resources:
  * ChromeDrivers - keep the chromedrivers for linux and for macOS.
  * TestPlan - holds the TestNG XML suite file that run's the test classes.
    The test classes run's first and after them the class SeleniumWebDriverKiller.
    
  * conf.properties - holds project parameters such as Xpaths, Url's Texts, Urls, API Key (appid) for the weather api, zip code for the weather location etc..<br>
   
 
 


