package SeleniumActionsHelpers;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import Properties.PropertiesHolder;
import org.openqa.selenium.support.ui.Select;


public class CalcActions {
    private PropertiesHolder prop;
    private SeleniumActions seleniumActions;

    public CalcActions() throws Exception {
        this.prop = PropertiesHolder.getInstance();
        this.seleniumActions = SeleniumActions.getInstance();
        seleniumActions.openWebPage(prop.getProperty("matricUrl"));
    }

    public double metricConvert(int valueToConvert, Convert convert) throws Exception {
        String url = "";
        try{
            if(!seleniumActions.getTitle().equals("Metric Conversion charts and calculators")){
                System.out.println("Navigating to 'Metric Conversions' home page.");
                navigateBackToStartingPage();
            }

            switch (convert) {
                case Celsius_To_Fahrenheit:
                    System.out.println("Navigating to Temperature conversion");
                    String temperature = prop.getProperty("Temperature");
                    seleniumActions.clickOnUrlByText(temperature);

                    System.out.println("Navigating to 'Celsius To Fahrenheit' conversion");
                    String celsiusToFahrenheitTxt = prop.getProperty("CelsiusToFahrenheit");
                    seleniumActions.clickOnUrlByText(celsiusToFahrenheitTxt);
                    break;
                case Meter_To_Feet:
                    System.out.println("Navigating to Length conversion");
                    String length = prop.getProperty("Length");
                    seleniumActions.clickOnUrlByText(length);

                    System.out.println("Navigating to 'Meter To Feet' conversion");
                    String meterToFeet = prop.getProperty("MeterToFeet");
                    seleniumActions.clickOnUrlByText(meterToFeet);
                    break;
                case Ounces_To_Grams:
                    System.out.println("Navigating to Weight conversion");
                    String weight = prop.getProperty("Weight");
                    seleniumActions.clickOnUrlByText(weight);

                    System.out.println("Navigating to 'Ounces To Grams' conversion");
                    String ouncesToGrams = prop.getProperty("OuncesToGrams");
                    seleniumActions.clickOnUrlByText(ouncesToGrams);
                    break;
            }

            convertValue(valueToConvert);

            System.out.println("Selecting format of the result");
            Select select = new Select(seleniumActions.getElementConvertById(prop.getProperty("format")));
            select.selectByVisibleText("Decimal");

            System.out.println("Selecting accuracy of the result");
            select = new Select(seleniumActions.getElementConvertById(prop.getProperty("accuracy")));
            select.selectByVisibleText("6 significant figures");

            Thread.sleep(1000);

            String convertedValue = getConvertedValue();
            convertedValue = convertedValue.replaceAll("[^\\d.]", "");
            double ans;
            try{
                ans = Double.parseDouble(convertedValue);
            } catch (Exception e) {
                throw new Exception("Unable to parse  value [" + convertedValue + "] to double.\n" + e.getMessage() );
            }


            return ans;
        } catch (Exception e1) {
            throw new Exception("Unable to get the webpage from the url: " + url + "\n" + e1.getMessage());
        }
    }

    public void convertValue(double value) throws Exception {
        System.out.println("Sending value [" + value + "] to convert");
        String convertId = prop.getProperty("argumentConvertId");
        WebElement elm = seleniumActions.getElementConvertById(convertId);
        elm.sendKeys("" + value);
        elm.sendKeys(Keys.ENTER);
    }

    private String getConvertedValue() throws Exception {
        WebElement elm;
        String conversionId = prop.getProperty("convertionAnswerId");

        System.out.println("Getting the converted value");
        elm = seleniumActions.getElementConvertById(conversionId);

        String ans = elm.getText();
        if(ans == null || ans.equals("") ){
            throw new Exception("Unable to get calculated value.");
        }
        String ansVal = ans.split("=")[1];
        return ansVal;
    }

    public void navigateBackToStartingPage() throws Exception {
        try {
            seleniumActions.clickOnUrlByText(prop.getProperty("metricHome"));
        } catch (Exception e) {
            try {
                seleniumActions.openWebPage(prop.getProperty("metricUrl"));
            } catch (Exception ex) {
                throw new Exception("Unable to load the web page --> " + ex.getMessage());
            }
        }
    }

    public enum Convert{
        Celsius_To_Fahrenheit,
        Meter_To_Feet,
        Ounces_To_Grams
    }

}
