package utils;

import base.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private static WebDriverWait getWait(int timeoutSeconds) {
        return new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(timeoutSeconds));
    }

    // Wait until element is visible on screen
    public static WebElement waitForVisibility(WebElement element, int timeoutSeconds) {
        return getWait(timeoutSeconds).until(
            ExpectedConditions.visibilityOf(element)
        );
    }

    // Wait until element is clickable
    public static WebElement waitForClickability(WebElement element, int timeoutSeconds) {
        return getWait(timeoutSeconds).until(
            ExpectedConditions.elementToBeClickable(element)
        );
    }

    // Wait until element disappears — useful for loaders/spinners
    public static boolean waitForInvisibility(WebElement element, int timeoutSeconds) {
        return getWait(timeoutSeconds).until(
            ExpectedConditions.invisibilityOf(element)
        );
    }

    // Wait for a specific text to appear inside an element
    public static boolean waitForText(WebElement element, String text, int timeoutSeconds) {
        return getWait(timeoutSeconds).until(
            ExpectedConditions.textToBePresentInElement(element, text)
        );
    }

    // Wait until element is present in DOM (not necessarily visible)
    public static WebElement waitForPresence(By locator, int timeoutSeconds) {
        return getWait(timeoutSeconds).until(
            ExpectedConditions.presenceOfElementLocated(locator)
        );
    }

    // Wait for page URL to contain a specific fragment
    public static boolean waitForUrlContains(String urlFragment, int timeoutSeconds) {
        return getWait(timeoutSeconds).until(
            ExpectedConditions.urlContains(urlFragment)
        );
    }

    // Overloaded versions using default timeout from config
    public static WebElement waitForVisibility(WebElement element) {
        int timeout = Integer.parseInt(ConfigReader.get("explicitWait"));
        return waitForVisibility(element, timeout);
    }

    public static WebElement waitForClickability(WebElement element) {
        int timeout = Integer.parseInt(ConfigReader.get("explicitWait"));
        return waitForClickability(element, timeout);
    }
}