package helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Helper {
    protected WebDriver driver;
    protected Actions actions;

    public Helper(WebDriver driver, Actions actions) {
        this.driver = driver;
        this.actions = actions;
    }
    public void  clearAndEnter(WebElement el, String text) {
        el.clear();
        el.sendKeys(text);
    }
}
