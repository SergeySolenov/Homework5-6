import annotation.Driver;
import extensions.UIExtension;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import pages.AccountPage;
import pages.LoginPage;
import pages.MainPage;
import pages.ProfilePage;

import static data.CityData.ASTRAKHAN;
import static data.ContryData.RUSSIA;
import static data.EnglishLevelData.BEGINNER;
import static data.NamesData.*;
import static data.WorkData.COMPANY;
import static data.WorkData.JOB;
import static properties.UserData.*;

@ExtendWith(UIExtension.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OtusTest {
    @Driver
    public WebDriver driver;
    private final Logger log = LogManager.getLogger("AutomationFramework");

    @Test
    public void FillingDataTest() {
        log.info("Главная страница");
        new MainPage(driver).open();
        log.info("Вход в аккаунт");
        new MainPage(driver).clickButtonEnter();
        log.info("Вход в личный кабинет");
        new LoginPage(driver).login();
        log.info("Раздел О себе");
        new AccountPage(driver).enterLK();
        log.info("Ввод данных");
        new ProfilePage(driver).addPersonalInformation();
    }

    @Test
    public void ReadingDataTest() {
        log.info("Главная страница");
        new MainPage(driver).open();
        log.info("Вход в аккаунт");
        new MainPage(driver).clickButtonEnter();
        log.info("Вход в личный кабинет");
        new LoginPage(driver).login();
        log.info("Раздел О себе");
        new AccountPage(driver).enterLK();
        log.info("Проверка данных");
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage
                .assertField(FIRST_NAME, (new ProfilePage(driver)
                        .setSelector(FNAME.getName()))
                        .getAttribute("value"), "Некорректное имя");
        profilePage
                .assertField(TFIRST_NAME, (profilePage.setSelector(FNAME_LATIN.getName()))
                        .getAttribute("value"), "Некорректный транслит имени");
        profilePage
                .assertField(LAST_NAME, (profilePage.setSelector(LNAME.getName()))
                        .getAttribute("value"), "Некорректная фамилия");
        profilePage
                .assertField(TLAST_NAME, (profilePage.setSelector(LNAME_LATIN.getName()))
                        .getAttribute("value"), "Некорректный транслит фамилии");
        profilePage
                .assertField(NICK_NAME, (profilePage.setSelector(BLOG_NAME.getName()))
                        .getAttribute("value"), "Некорректное блог имя");
        profilePage
                .assertBirthDay(DATE);
        profilePage
                .assertField(RUSSIA.getName(), (profilePage.setLocator(RUSSIA.getName()))
                        .getAttribute("title"), "Некорректная страна");
        profilePage
                .assertField(ASTRAKHAN.getName(), (profilePage.setLocator(ASTRAKHAN.getName()))
                        .getAttribute("title"), "Некорректный город");
        profilePage
                .assertField(BEGINNER.getName(), (profilePage.setLocator(BEGINNER.getName()))
                        .getAttribute("title"), "Некорректный уровень языка");

        profilePage
                .assertField(JOB_PLACE, (profilePage.setSelector(COMPANY.getName()))
                        .getAttribute("value"), "Некорректная компания");
        profilePage
                .assertField(POSITION, (profilePage.setSelector(JOB.getName()))
                        .getAttribute("value"), "Некорректная должность");

    }
}
