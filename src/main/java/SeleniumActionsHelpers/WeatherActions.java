package SeleniumActionsHelpers;
import Data.WeatherData;
import Properties.PropertiesHolder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WeatherActions {

    private PropertiesHolder prop;
    private SeleniumActions seleniumActions;

    public WeatherActions() throws Exception {
        this.prop = PropertiesHolder.getInstance();
        this.seleniumActions = SeleniumActions.getInstance();
        seleniumActions.openWebPage(prop.getProperty("weatherUrl"));
    }


    public boolean searchForLocationByZipCode() throws Exception {
        String zip = prop.getProperty("zipCode");
        String searchBarXpath = prop.getProperty("searchCity");
        String todayXpath = prop.getProperty("todayWeather");
        seleniumActions.navigateToByXpath(todayXpath);

        System.out.println("Looking for the search bar");
        seleniumActions.waitForElementToBeVisible(By.xpath(searchBarXpath));
        seleniumActions.waitForElementToBeClickable(By.xpath(searchBarXpath));
        WebElement searchBar = seleniumActions.getElementByXpath(searchBarXpath);
        System.out.println("Clicking on it");
        searchBar.click();
        System.out.println("Sending keys " + zip);
        searchBar.sendKeys(zip);


        String firstResultXpath = prop.getProperty("firstResult");
        System.out.println("Waiting to first result to be visible");
        seleniumActions.waitForElementToBeVisible(By.xpath(firstResultXpath));
        System.out.println("Getting first result element");
        WebElement firstResultFromSearch = seleniumActions.getElementByXpath(firstResultXpath);
        System.out.println("Clicking on the first result from the search");
        firstResultFromSearch.click();

        if(seleniumActions.getTitle().contains("Weather Forecast and Conditions")) return true;
        else return false;
    }

    public WeatherData getWeatherData() throws Exception {
        WeatherData data = new WeatherData();

        System.out.println("\nGetting weather information from the web page");
        String humidityXpath = prop.getProperty("humidity");
        WebElement humidity = seleniumActions.getElementByXpath(humidityXpath);
        data.setHumidity(getTextFromWebElementToInt(humidity));

        String todayTempXpath = prop.getProperty("todayTemp");
        WebElement temp = seleniumActions.getElementByXpath(todayTempXpath);
        data.setTemperature(getTextFromWebElementToInt(temp));

        String windXpath = prop.getProperty("wind");
        WebElement wind = seleniumActions.getElementByXpath(windXpath);
        data.setWind(getTextFromWebElementToDouble(wind));

        String presureXpath = prop.getProperty("pressure");
        WebElement presure = seleniumActions.getElementByXpath(presureXpath);
        data.setPressure(getTextFromWebElementToDouble(presure));

        return data;
    }

    private int getTextFromWebElementToInt(WebElement elm) throws Exception {
        int value;
        try{
            value = Integer.parseInt(elm.getText().replaceAll("[^\\d.]", "").trim());
        }catch (Exception e) {
            throw new Exception("Unable to parse value: " + elm.getText() + " to int");
        }
        return value;
    }

    private double getTextFromWebElementToDouble(WebElement elm) throws Exception {
        double value;
        try{
            value = Double.parseDouble(elm.getText().replaceAll("[^\\d.]", "").trim());
        }catch (Exception e) {
            throw new Exception("Unable to parse value: " + elm.getText() + " to int");
        }
        return value;
    }

}
