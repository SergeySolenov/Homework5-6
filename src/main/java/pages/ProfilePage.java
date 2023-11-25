package pages;

import data.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static data.NamesData.*;
import static data.SocialNetworkData.TELEGRAM;
import static data.SocialNetworkData.VK;
import static data.WorkData.COMPANY;
import static data.WorkData.JOB;

public class ProfilePage extends AbsBasePage {


    public ProfilePage(WebDriver driver) {
        super(driver, "/");
    }

    @FindBy(xpath = "//input[contains(@name, 'date_of_birth')]")
    private WebElement birthday;
    @FindBy(xpath = "//input[@name= 'country']/following::div[1]")
    private WebElement countryInfo;
    @FindBy(xpath = "//input[@data-title= 'Город']/following::div[1]")
    private WebElement cityInfo;
    @FindBy(xpath = "//input[@data-title='Уровень знания английского языка']/ancestor::div[contains(@class,'select')]")
    private WebElement englishLevelInfo;
    @FindBy(xpath = "//input[contains(@id,'id_ready_to_relocate_1')]")
    private WebElement readyRelocate;
    @FindBy(xpath = "//span[contains(text(),'Да')]")
    private WebElement readyRelocateYes;
    @FindBy(xpath = "//input[@title='Полный день']")
    private WebElement elFullDay;
    @FindBy(xpath = "//span[contains(text(), 'Полный день')]")
    private WebElement elFullDayClick;
    @FindBy(xpath = "//input[@title = 'Гибкий график']")
    private WebElement elflexiblesSchedule;
    @FindBy(xpath = "//span[contains(text(), 'Гибкий график')]")
    private WebElement elflexiblesScheduleClick;
    @FindBy(xpath = "//input[@title = 'Удаленно']")
    private WebElement elDist;
    @FindBy(xpath = "//span[contains(text(), 'Удаленно')]")
    private WebElement elDistClick;
    @FindBy(xpath = "//button[contains(@title,'Сохранить и продолжить')]")
    private WebElement SaveAndContinue;
    @FindBy(xpath = "//div[@class='nav-sidebar']//a[@title='Персональные данные']")
    private WebElement PersonalData;
    @FindBy(xpath = "//span[@class='placeholder']")
    private WebElement listContact;
    @FindBy(xpath = "(//button[@data-value='vk'])[last()]")
    private WebElement listContactVK;
    @FindBy(xpath = "(//button[@data-value='telegram'])[last()]")
    private WebElement listContactTelegram;
    @FindBy(xpath = "//button[contains(text(),'Добавить')]")
    private WebElement buttonAdd;
    //    @FindBy(xpath = "//option[contains(@value, 'f')]")
//    private WebElement gender;
    @FindBy(css = "a[title='Добавить']")
    private WebElement addButtonDevelop;
    @FindBy(css = "#id_experience-0-experience")
    private WebElement languageDevelopField;
    @FindBy(css = "#id_experience-0-experience > option:nth-child(3)")
    private WebElement timeDevelopField;
    @FindBy(xpath = "//option[contains(text(), 'Только начал')]")
    private WebElement timeDevelop;
    @FindBy(css = "button[title='Сохранить и заполнить позже']")
    private WebElement SaveAndFillLater;


    public void addPersonalInformation(String firstName, String lastName, String tfirstName, String tlastName, String nickName) {
        helper.clearAndEnter(setSelector(FNAME.getName()), firstName);
        helper.clearAndEnter(setSelector(LNAME.getName()), lastName);
        helper.clearAndEnter(setSelector(FNAME_LATIN.getName()), tfirstName);
        helper.clearAndEnter(setSelector(LNAME_LATIN.getName()), tlastName);
        helper.clearAndEnter(setSelector(BLOG_NAME.getName()), nickName);
    }

    public void addBirdhday(LocalDate date) {
        helper.clearAndEnter(birthday, String.valueOf(date));
    }

    public void setCountryInfo(ContryData contryData, CityData cityData) {
        countryInfo.click();
        setLocator(contryData.getName()).click();
        waiters.waitForCondition(ExpectedConditions.attributeToBe(cityInfo, "disabled", "disabled"));
        waiters.waitForCondition(ExpectedConditions.not(ExpectedConditions.attributeToBe(cityInfo, "disabled", "disabled")));
        cityInfo.click();
        setLocator(cityData.getName()).click();
    }

    public void setEnglishLevelInfo(EnglishLevelData englishLevelData) {
        englishLevelInfo.click();
        waiters.waitElementVisible(setLocator(englishLevelData.getName()));
        setLocator(englishLevelData.getName()).click();
    }

    public void addSocialNetworks(String contactVk, String contactTg) {
        choiceRadioButton();
        clickCheckBox(elFullDay, elFullDayClick);
        clickCheckBox(elflexiblesSchedule, elflexiblesScheduleClick);
        clickCheckBox(elDist, elDistClick);
        log.info("Удаляем старые контакты и перезаходим в личный кабинет");
        deleteContacts();
        SaveAndContinue.submit();
        PersonalData.click();
        log.info("Вводим новые контакты");
        listContact.click();
        listContactVK.click();
        helper.clearAndEnter(setSelector(VK.getName()), contactVk);
        buttonAdd.click();
        listContact.click();
        listContactTelegram.click();
        helper.clearAndEnter(setSelector(TELEGRAM.getName()), contactTg);
        log.info("Заполняем оставшиеся поля");
    }

    public void setGender(GenderData gender) {
        setSelector(NamesData.GENDER.getName()).click();
        setGenderLocator(gender.getName()).click();
    }

    public void addCompanyAndJob(String company, String job) {
        helper.clearAndEnter(setSelector(COMPANY.getName()), company);
        helper.clearAndEnter(setSelector(JOB.getName()), job);
    }

    //    deleteDevelopmentExperience();
//        addButtonDevelop.click();
    public void save() {
        SaveAndFillLater.submit();
        log.info("Нажимаем сохранить");
    }

    public void choiceRadioButton() {
        if (!readyRelocateYes.isSelected()) {
            readyRelocateYes.click();
        }
    }

    public void clickCheckBox(WebElement el, WebElement elClick) {
        if (!el.isSelected()) {
            elClick.click();
        }
    }

    private void deleteContacts() {
        int i = 1;
        do {
            String strSelector = "div.js-formset-row:nth-child(" + i + ") > div:nth-child(4) > div:nth-child(2) > button:nth-child(1)";
            if (isDisplayed(By.cssSelector(strSelector))) {
                break;
            } else {
                $(strSelector).click();
            }
            i++;
        } while (i < 20);


    }

    boolean isDisplayed(By by) {
        try {
            return !driver.findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    private void deleteDevelopmentExperience() {
        int i = 1;
        do {
            String strSelector = "div:nth-child(" + i + ") > div.experience-row__remove.ic-close.js-formset-delete";
            if (isDisplayed(By.cssSelector(strSelector))) {
                break;
            } else {
                driver.findElement(By.cssSelector(strSelector)).click();
            }
            i++;
        } while (i < 20);
    }

    public void assertField(String actual, String expected, String message) {
        Assertions.assertEquals(expected, actual, message);

    }

    public void assertBirthDay(LocalDate date) {
        LocalDate actualData = LocalDate.parse(birthday.getAttribute("value"), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        Assertions.assertTrue(date.isEqual(actualData), "Дата рождения не совпадает");
    }
}


