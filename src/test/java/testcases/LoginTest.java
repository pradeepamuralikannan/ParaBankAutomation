package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import base.DriverFactory;
import pages.LoginPage;
import utils.ConfigReader;

public class LoginTest extends BaseTest {
	
	@Test(description="Valid login should land on account overview")
	public void testValidLogin() {
		
		LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
		loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));
		Assert.assertTrue(driver.getTitle().contains("Overview"));
		
	}

	@Test(description = "Invalid login should show error message")
	public void testInvalidLogin() {
		LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
		loginPage.login("wrongname","password");
		Assert.assertEquals(loginPage.getErrorMessage(),
	            "The username and password could not be verified.");		
	}
}
