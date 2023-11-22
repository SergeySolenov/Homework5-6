package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverFactory {
    private final String BROWSER_NAME = System.getProperty("browser", "chrome");
    public WebDriver create(String options) {
        switch(BROWSER_NAME) {
            case "chrome": {
                if (options != null) {
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments(options);
                    return new ChromeDriver(chromeOptions);
                } else return new ChromeDriver();
            }
            case "firefox": {
                if (options != null) {
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments(options);
                    return new FirefoxDriver(firefoxOptions);
                } else return new FirefoxDriver();
            }
            default: return null;
        }
    }
}

