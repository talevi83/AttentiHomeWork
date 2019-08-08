package tests;

import Data.WeatherData;
import RestAPI.WeatherRestActions;
import SeleniumActionsHelpers.WeatherActions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class WeatherTests {

    private WeatherActions weatherActions;

    private WeatherData seleniumData;
    private WeatherData apiData;

    @BeforeClass
    public void initInfraAndGetData() {
        try {
            weatherActions = new WeatherActions();

            boolean success = weatherActions.searchForLocationByZipCode();
            Assert.assertTrue(success, "Fail to get the today's forecast for the requested zip code.");

            seleniumData = weatherActions.getWeatherData();
            apiData = WeatherRestActions.getWeatherDataFromRestAPI();

            System.out.println("\n~~~~~~~~~~~ Collected data from Weather.com ~~~~~~~~~~~~~~");
            System.out.println(seleniumData.toString());
            System.out.println("\n~~~~~~~~~~~ Collected data from api.openweathermap.org ~~~~~~~~~~~~~~");
            System.out.println(apiData.toString());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @BeforeMethod
    public void printTestName(Method method) {
        String testName = method.getName();
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~ Start running test: " + testName + " ~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    @Test
    public void checkTemperatureDifference() {
        Assert.assertFalse(isHigherThanTenPercentDifference(seleniumData.getTemperature(), apiData.getTemperature()),
                "The difference between the two temperatures of the website [ " + seleniumData.getTemperature() + "] and the API [" + apiData.getTemperature() + "] is higher than 10%.");
    }

    @Test
    public void checkHumidityDifference() {
        Assert.assertFalse(isHigherThanTenPercentDifference(seleniumData.getHumidity(), apiData.getHumidity()),
                "The difference between the two humidity of the website [" + seleniumData.getHumidity() + "] and the API [" + apiData.getHumidity() + "] is higher than 10%.");
    }

    @Test
    public void checkPressureDifferences() {
        Assert.assertFalse(isHigherThanTenPercentDifference(seleniumData.getPressure(), apiData.getPressure()),
                "The difference between the two pressure of the website [" + seleniumData.getPressure() + "] and the API [" + apiData.getPressure() + "] is higher than 10%.");
    }

    @Test
    public void checkWindDifferences() {
        Assert.assertFalse(isHigherThanTenPercentDifference(seleniumData.getWind(), apiData.getWind()),
                "The difference between the two wind of the website [" + seleniumData.getWind() + "] and the API [" + apiData.getWind() + "] is higher than 10%.");
    }

    private boolean isHigherThanTenPercentDifference(double val1, double val2) {
        return ((val2 < val1 * 0.9 || val2 > val1 * 1.1) || (val1 < val2 * 0.9 || val1 > val2 * 1.1));
    }

}
