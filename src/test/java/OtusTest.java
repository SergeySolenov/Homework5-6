import annotation.Driver;
import extensions.UIExtension;
import helper.FakerData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.AccountPage;
import pages.LoginPage;
import pages.MainPage;
import pages.ProfilePage;

import java.time.LocalDate;

import static data.CityData.ASTRAKHAN;
import static data.ContryData.RUSSIA;
import static data.EnglishLevelData.BEGINNER;
import static data.GenderData.MALE;
import static data.NamesData.*;
import static data.WorkData.COMPANY;
import static data.WorkData.JOB;
import static java.time.Month.OCTOBER;

@ExtendWith(UIExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OtusTest {
    public final String firstName = "Алексей"; //FakerData.genFirstName();
    public final String tFirstName = FakerData.translate(firstName);
    public final String lastName = "Краснов"; //FakerData.genLastName();
    public final String tLastName = FakerData.translate(lastName);
    public final String nickName = FakerData.translate(firstName);
    public final LocalDate date = LocalDate.of(1999, OCTOBER,30);
    public final String contactVk = "https://vk.com/club3774399";
    public final String contactTg = "@Faker";
    public final String jobPlace = "Otus";
    public final String position = "student";
    @Driver
    public WebDriver driver;
    private final Logger log = LogManager.getLogger("AutomationFramework");
    @Order(1)
    @Test
    public void fillingDataTest() {
        log.info("Главная страница");
        new MainPage(driver).open();
        log.info("Вход в аккаунт");
        new MainPage(driver).clickButtonEnter();
        log.info("Вход в личный кабинет");
        new LoginPage(driver).login();
        log.info("Раздел О себе");
        new AccountPage(driver).enterLK();
        log.info("Ввод данных");
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage
                .addPersonalInformation(firstName, lastName, tFirstName, tLastName, nickName);
        profilePage
                .addBirdhday(date);
        profilePage
                .setCountryInfo(RUSSIA, ASTRAKHAN);
        profilePage
                .setEnglishLevelInfo(BEGINNER);
        profilePage.
                addSocialNetworks(contactVk, contactTg);
        profilePage
                .setGender(MALE);
        profilePage
                .addCompanyAndJob(jobPlace, position);
        profilePage
                .save();
    }
    @Order(2)
    @Test
    public void readingDataTest() {
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
                .assertField(firstName, (new ProfilePage(driver)
                        .setSelector(FNAME.getName()))
                        .getAttribute("value"), "Некорректное имя");
        profilePage
                .assertField(tFirstName, (profilePage.setSelector(FNAME_LATIN.getName()))
                        .getAttribute("value"), "Некорректный транслит имени");
        profilePage
                .assertField(lastName, (profilePage.setSelector(LNAME.getName()))
                        .getAttribute("value"), "Некорректная фамилия");
        profilePage
                .assertField(tLastName, (profilePage.setSelector(LNAME_LATIN.getName()))
                        .getAttribute("value"), "Некорректный транслит фамилии");
        profilePage
                .assertField(nickName, (profilePage.setSelector(BLOG_NAME.getName()))
                        .getAttribute("value"), "Некорректное блог имя");
        profilePage
                .assertBirthDay(date);
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
                .assertField(MALE.getName(), (profilePage.setGenderLocator(MALE.getName()))
                        .getAttribute("value"), "Некорректный пол");
        profilePage
                .assertField(jobPlace, (profilePage.setSelector(COMPANY.getName()))
                        .getAttribute("value"), "Некорректная компания");
        profilePage
                .assertField(position, (profilePage.setSelector(JOB.getName()))
                        .getAttribute("value"), "Некорректная должность");

    }
}
