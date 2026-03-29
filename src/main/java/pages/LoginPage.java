package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) { // replaced `DriverFactory.getDriver()` with just `driver` — since `driver` is
											// now inherited, there's no need to call `DriverFactory` directly inside
											// test classes.
		super(driver);
	}

	@FindBy(name = "username")
	private WebElement usernameField;

	@FindBy(name = "password")
	private WebElement passwordField;

	@FindBy(xpath = "//input[@value='Log In']")
	private WebElement loginButton;

	@FindBy(className = "error")
	private WebElement errorMessage;

	public void login(String username, String password) {
		type(usernameField, username);
		type(passwordField, password);
		click(loginButton);
	}

	public String getErrorMessage() {
		return getText(errorMessage);
	}
}
