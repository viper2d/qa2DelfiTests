package pageObjectTest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class BaseFunctions {
    WebDriver driver;
    private static final Logger LOGGER = LogManager.getLogger(BaseFunctions.class);

    private static final String FF_DRIVER_PATH = "/usr/bin/geckodriver";

    public BaseFunctions() {
        LOGGER.info("Setting driver property");
        System.setProperty("webdriver.gecko.driver", FF_DRIVER_PATH);

        LOGGER.info("Initialising driver");
        this.driver = new FirefoxDriver();

        LOGGER.info("Maximizing window");
        driver.manage().window().maximize();
    }

    public void goToUrl(String url) {
        LOGGER.info("Opening URL: " + url);
        driver.get(url);
    }

    public WebElement getElement(By locator) {
        LOGGER.info("Getting element");
        return driver.findElement(locator);
    }

    public List<WebElement> findElements(By element) {
        LOGGER.info("Getting elements");
        return driver.findElements(element);
    }

    public void clickElement(By locator) {
        LOGGER.info("Click element");
        driver.findElement(locator).click();
    }

}
