package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//BasePage class—a core part of the Page Object Model (POM). It’s designed to avoid duplicate code and provide reusable methods for all your page classes.

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    //constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;   // Assigns driver from test class
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));  // Initializes WebDriverWait (15 sec timeout)
        PageFactory.initElements(driver, this);   // Initializes all @FindBy elements in child classes
    }
    
  //Waits until element is clickable. prevents ElementNotInteractableException and ElementClickInterceptedException
    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click(); 
    }

    protected void type(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element)).clear();
        element.sendKeys(text);
    }

    protected String getText(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }
    
    protected boolean isDisplayed(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
    }

    protected void waitForElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}