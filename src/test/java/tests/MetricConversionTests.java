package tests;

import SeleniumActionsHelpers.CalcActions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class MetricConversionTests {
    private CalcActions calcActions;

    @BeforeClass
    public void initInfra() {
        try {
            calcActions = new CalcActions();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @BeforeMethod
    public void nameBefore(Method method) {
        String testName = method.getName();
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~ Start running test: " + testName + " ~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }

    @Test
    public void checkCelciusToFahrenheitCalculation(){
        try {
            double degreeInCelcius = 1000;
            double far = calcActions.metricConvert((int)degreeInCelcius, CalcActions.Convert.Celsius_To_Fahrenheit);
            double expected = degreeInCelcius * 1.8 + 32;
            Assert.assertEquals(far, expected, "The temperature conversion is not correct. expected: " + expected + " but got: " + far);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void checkMeterToFeetConvertion() {
        try {
            int meter = 1000;
            double feet = calcActions.metricConvert(meter, CalcActions.Convert.Meter_To_Feet);
            double expected = meter * 3.28084;
            Assert.assertEquals(feet, expected, "The length conversion is not correct. expected: " + expected + " but got: " + feet + "\n");
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void checkOuncesToGram() {
        try{
            int oz = 1000;
            double gram = calcActions.metricConvert(oz, CalcActions.Convert.Ounces_To_Grams);
            double expected = oz * 28.3495;
            Assert.assertEquals(gram, expected, "The weight calculation is not correct. expected: " + expected + " but got: " + gram);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}
