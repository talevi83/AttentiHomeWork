# Attenti Home Work
This project contains tests for Metric Conversion website and for weather.com vs openweathermap api.
This project is running by TestNG suite, therefore you need to run the test via the suite XML file:
The file is in this path in the project: /src/test/resources/TestPlan/RunTests.xml, click on this file right click, then run.

<b>Design:<b>
The project divided into several classes:<br>
<b>Under src/main/java/ there are the following packages:</b><<br>
<b>Data</b> - contains WeatherData - a class that holds weather data such as Temperature, Humidity, Wind, Pressure.<br>
<b>Properties</b> - contains a Singleton class that reading the project properties from conf.properties file (locate in /src/test/resources)<br>
<b>RestAPI- contains classes that operate the Rest API operations:</b> 
 * RestAPI - basic class of the Rest API actions of sending requests.
 * RestApiResponse - This class holds the response that we get from the requests.
 * RestRequestBuilder - Builder class for creating Rest request in a more convenient way.
 * WeatherRestRequest - this class holds Rest Actions specific for getting weather data from the API.<br>
   this class is using the RestRequestBuilder and the RestApiResponse.<br>

<b>SeleniumActionsHelpers - contins classes for selenium actions:</b> 
 * SeleniumActions - this is the main selenium class which starts the webdriver and holds 
   methods for actions on the web page - this class is a Singleton.<br>
 * CalcActions - this class is been used to operate the calculations on Metric Conversion website by using methods from SeleniumActions class.<br>
 * WeatherActions - this class is been used to operate the actions over the Weather website using the class SeleniumActions.

<b>Under src/main/test there are the following classes:</b>
 * MetricConvertionTests - this class contains the tests for the Metric Conversion.
 * WeatherTests - this class contains the tests for the weather, Weather.com vs openweathermap API.
 * SeleniumWebDriverKiller - this class's role is to close the webdriver after the tests are done (using SeleniumActions).
 
 * under src/main/test/resources:
   * ChromeDrivers - keep the chromedrivers for linux and for macOS.
   * TestPlan - holds the TestNG XML suite file that run's the test classes.
       The test classes run first and after them the class SeleniumWebDriverKiller.
    
   * conf.properties - holds project parameters such as Xpaths, Url's Texts, Urls, API Key (appid) for the weather API, zip code for the weather location, etc..<br>
   
 
 


