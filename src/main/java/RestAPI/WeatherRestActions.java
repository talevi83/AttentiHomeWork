package RestAPI;

import Data.WeatherData;
import Properties.PropertiesHolder;
import org.json.JSONObject;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

public class WeatherRestActions {

    public static WeatherData getWeatherDataFromRestAPI() throws Exception {
        RestRequestBuilder rest = new RestRequestBuilder();
        PropertiesHolder prop = PropertiesHolder.getInstance();
        Map<String, String> attributes = new HashMap<String, String>();
        attributes.put("zip", prop.getProperty("zipCode") + "," + prop.getProperty("countryCode"));
        attributes.put("units", prop.getProperty("unit"));
        attributes.put("appid", prop.getProperty("appid"));
        RestApiResponse response = rest.setBaseUrl(prop.getProperty("baseUrlAPI")).addAttribute(attributes).setRestMethod("get").build();
        Assert.assertEquals(response.getResponseCode(), 200, "The response code is not 200. something went wrong. response code = " + response.getResponseCode());

        return extractDataFromResponse(response);
    }

    private static WeatherData extractDataFromResponse(RestApiResponse response) {
        String responseStr = response.getResultBody();
        JSONObject json = new JSONObject(responseStr);
        JSONObject main = json.getJSONObject("main");
        double temp = main.getDouble("temp");
        double pressure = main.getInt("pressure") * 0.029;      // convert the pressure value from 'hPa' to 'in'
        int humidity = main.getInt("humidity");

        JSONObject windObj = json.getJSONObject("wind");
        double wind = windObj.getDouble("speed");
        WeatherData restData = new WeatherData();
        restData.setTemperature((int)temp);
        restData.setPressure(pressure);
        restData.setWind(wind);
        restData.setHumidity(humidity);

        return restData;
    }
}
