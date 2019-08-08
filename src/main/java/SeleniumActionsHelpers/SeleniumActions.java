package SeleniumActionsHelpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class SeleniumActions {
    private WebDriver driver;
    private static SeleniumActions seleniumActions;

    public static SeleniumActions getInstance() {
        if(seleniumActions == null) {
            seleniumActions = new SeleniumActions();
        }
        return seleniumActions;
    }

    private SeleniumActions() {
        init();
    }

    /***
     * Initialize the WebDriver and the properties from the conf.properties file
     */
    private void init() {

        // Initialize the WebDriver system property by the running OS
        String os = System.getProperty("os.name");
        if(os.toLowerCase().equals("linux")) {
            System.out.println("This is a Linux OS -> opening linux webdriver...");
            System.setProperty("webdriver.chrome.driver", "./src/test/resources/ChromeDrivers/chromedriver_linux");
        } else {
            System.setProperty("webdriver.chrome.driver", "./src/test/resources/ChromeDrivers/chromedriver_mac");
        }

        // Setting tmp folder to save browser logs into it as a text file.
        System.out.println("Assign temp folder to logs..");
        File tmpFolder = new File("/tmp/WebDriverLogs/");
        if(!tmpFolder.exists()) {
            if(!tmpFolder.mkdirs()) {
                System.err.println("Unable to create the directory for the web browser logs. the scenario will continue but without saving the logs.");
            }
        }
        String logFilePath = tmpFolder.getAbsolutePath() + "/logs_" + System.currentTimeMillis() + ".txt";
        System.out.println("Tmp folder assigned!");
        System.out.println("Setting logs property");
        System.setProperty("webdriver.chrome.logfile", logFilePath);        // redirecting browser logs into a log file in the tmp directory
        System.setProperty("webdriver.chrome.verboseLogging", "true");
        System.out.println("Logs property assigned!");

        System.out.println("Setting no images on browser");
        // Initialize the chrome webdriver with no image loading for faster web page loading
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--blink-settings=imagesEnabled=false");


        System.out.println("Set the chromedriver options and maximize the window.");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    public void closeDriver() {
        System.out.println("Closing WebDriver");
        driver.close();
    }

    public void openWebPage(String url) {
        System.out.println("trying to reach " + url + " \n");
        driver.get(url);
    }

    public void clickOnUrlByText(String txt) {
        waitForElementToBeVisible(By.linkText(txt));
        waitForElementToBeClickable(By.linkText(txt));
        driver.findElement(By.linkText(txt)).click();
    }

    public WebElement getElementConvertById(String id) {
        waitForElementToBeVisible(By.id(id));
        return driver.findElement(By.id(id));
    }


    public void waitForElementToBeVisible(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitForElementToBeClickable(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(by));
    }

    public void navigateToByXpath(String xpath) throws Exception {
        try {
            waitForElementToBeClickable(By.xpath(xpath));
            driver.findElement(By.xpath(xpath)).click();
        } catch (Exception e) {
            throw new Exception("Unable to navigate to page by the given xpath '" + xpath + "'");
        }
    }

    public WebElement getElementByXpath(String xpath) {
        waitForElementToBeVisible(By.xpath(xpath));
        return driver.findElement(By.xpath(xpath));
    }

    public String getTitle() {
        return driver.getTitle();
    }
}
