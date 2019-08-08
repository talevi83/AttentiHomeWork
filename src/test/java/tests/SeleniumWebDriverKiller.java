package tests;

import SeleniumActionsHelpers.SeleniumActions;
import org.testng.annotations.AfterSuite;

public class SeleniumWebDriverKiller {
    @AfterSuite
    public void killWebDriver() {
        SeleniumActions.getInstance().closeDriver();
    }
}
