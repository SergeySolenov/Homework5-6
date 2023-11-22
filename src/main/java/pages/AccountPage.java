package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends AbsBasePage {
    public AccountPage(WebDriver driver) {
        super(driver, "/");
    }

    @FindBy(xpath = "//section//div[contains(@class, '1og4wiw')][2]")
    private WebElement icon;
    @FindBy(xpath = "//a[text()='Мой профиль']")
    private WebElement context;

    public void enterLK() {
        waiters.waitElementVisible(icon);
        Actions action = new Actions(driver);
        action.moveToElement(icon).perform();
        context.click();
    }
}
