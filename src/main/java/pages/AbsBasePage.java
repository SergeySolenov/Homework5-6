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

    public WebElement setSelector(String string) {
        String selectorId = "#id_%s";
        String elementsId = String.format(selectorId, string);
        return $(elementsId);
    }

    public WebElement setLocator(String string) {
        String locator = "//button[contains(@title,'%s')]";
        String elementsId = String.format(locator, string);
        return $x(elementsId);
    }

    public WebElement setGenderLocator(String string){
        String genderLocator = "//option[contains(@value, '%s')]";
        String elementsId = String.format(genderLocator, string);
        return $x(elementsId);
    }

}
