package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageobject.AbsPageObject;

public abstract class AbsBasePage extends AbsPageObject {
    private final String BASE_URL = System.getProperty("base.url", "https://otus.ru");

    private final String path;

    public AbsBasePage(WebDriver driver, String path) {
        super(driver);
        this.path = path;
    }

    public void open() {
        driver.get(BASE_URL + path);
    }

    String selectorId = "#id_%s";

    public WebElement setSelector(String string) {
        String elementsId = String.format(this.selectorId, string);
        return $(elementsId);
    }

    String locator = "//button[contains(@title,'%s')]";

    public WebElement setLocator(String string) {
        String elementsId = String.format(this.locator, string);
        return $x(elementsId);
    }


}
