package base;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
//MANAGES BROWSER LIFECYCLE

//This Driver Factory class is used to manage WebDriver instances efficiently - especially for Parallel execution
public class DriverFactory {

	/*If we use Only one driver instance shared across all tests (WebDriver driver = new ChromeDriver())
	 * Then, If tests run in parallel:
                Sessions clash
                Tests interfere with each other
                Random failures happen
                
     * ThreadLocal creates a separate copy of WebDriver for each thread. Instead of one shared driver, each test thread gets its own independent driver instance.
               ✔ No interference
               ✔ Safe parallel execution
               ✔ Better scalability
	 */
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();   //Creates a separate copy of WebDriver for each thread

    public static WebDriver getDriver() {         // Returns the current thread’s WebDriver
        return driver.get();
    }

    public static void initDriver(String browser) {

        switch (browser.toLowerCase()) {

            case "chrome":
                WebDriverManager.chromedriver().setup();     // Uses WebDriverManager to download/setup ChromeDriver automatically
                driver.set(new ChromeDriver());
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver.set(new EdgeDriver());
                break;

            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();         // removes thread-specific value to avoid memory leaks
        }
    }
}