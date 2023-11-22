package extensions;

import annotation.Driver;
import factory.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Field;
import java.security.PrivilegedAction;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

import static java.security.AccessController.doPrivileged;


public class UIExtension implements BeforeAllCallback, AfterEachCallback, BeforeEachCallback {


    public WebDriver driver;

    private Set<Field> getAnnotatedFields(ExtensionContext extensionContext) {
        Set<Field> set = new HashSet<>();
        Class<?> testClass = extensionContext.getTestClass().get();
        while (testClass != null) {
            for (Field field : testClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(Driver.class)) {
                    set.add(field);
                }
            }
            testClass = testClass.getSuperclass();
        }
        return set;
    }


    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        WebDriverManager.chromedriver().setup();
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) {
        driver = new WebDriverFactory().create("--start-maximized");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Set<Field> fields = getAnnotatedFields(extensionContext);
        for (Field field : fields) {
            if (field.getType().getName().equals(WebDriver.class.getName())) {
                doPrivileged((PrivilegedAction<Void>)
                        () -> {
                            try {
                                field.setAccessible(true);
                                field.set(extensionContext.getTestInstance().get(), driver);
                            } catch (IllegalAccessException e) {
                                throw new Error(String.format("Could not access or set webdriver in field: %s - is this field public?", field), e);
                            }
                            return null;
                        }
                );
            }
        }
    }


    @Override
    public void afterEach(ExtensionContext extensionContext) {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}
