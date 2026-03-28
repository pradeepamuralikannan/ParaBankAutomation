//TestNG hooks, runs before/after each test
package base;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utils.ConfigReader;

public class BaseTest {

	@BeforeMethod
	public void setUp() {
		DriverFactory.initDriver(ConfigReader.get("browser"));
		DriverFactory.getDriver().get(ConfigReader.get("baseURL"));
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		if(result.getStatus()==ITestResult.FAILURE) {
			
		}
		DriverFactory.quitDriver();
	}
}