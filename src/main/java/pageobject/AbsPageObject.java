package pageobject;

import helper.Helper;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import waiters.Waiters;

import java.util.List;

public abstract class AbsPageObject {
    protected WebDriver driver;
    protected Actions actions;
    protected Waiters waiters;
    protected Helper helper;
    public org.apache.logging.log4j.Logger log = LogManager.getLogger("AutomationFramework");

    public AbsPageObject(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.waiters = new Waiters(driver, 10);
        this.helper = new Helper(driver, actions);
        PageFactory.initElements(driver, this);
    }

    public WebElement $(String cssSelector) {
        return driver.findElement(By.cssSelector(cssSelector));
    }

    public List<WebElement> $$(String cssSelector) {
        return driver.findElements(By.cssSelector(cssSelector));
    }

    public WebElement $x(String xPathLocator) {
        return driver.findElement(By.xpath(xPathLocator));
    }

    public List<WebElement> $$x(String xPathLocator) {
        return driver.findElements(By.xpath(xPathLocator));
    }
}
