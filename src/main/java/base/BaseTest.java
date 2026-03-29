//TestNG hooks, runs before/after each test
package base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utils.ConfigReader;
import utils.ScreenshotUtils;

public class BaseTest {
	
	protected WebDriver driver;

	@BeforeMethod
	public void setUp() {
		DriverFactory.initDriver(ConfigReader.get("browser"));   //driver created inside DriverFactory
		driver =DriverFactory.getDriver();                       //assigning to this.driver
		driver.get(ConfigReader.get("baseUrl"));
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		if(result.getStatus()==ITestResult.FAILURE) {
			ScreenshotUtils.capture(result.getName());
		}
		DriverFactory.quitDriver();
	}
}