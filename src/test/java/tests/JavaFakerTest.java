package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.RegistrationPage;
import utils.TestDataGenerator;

import java.util.Map;

public class JavaFakerTest {
    RegistrationPage registrationPage = new RegistrationPage();
    TestDataGenerator testData = new TestDataGenerator();

    @BeforeAll
    static void configureBrowserSettings() {
        // Получаем параметры из Jenkins или используем значения по умолчанию
        String browser = System.getProperty("browser", "chrome");
        String browserVersion = System.getProperty("browserVersion", "128.0");
        String browserSize = System.getProperty("browserSize", "1920x1080");
        String remoteUrl = System.getProperty("remoteUrl", "https://user1:1234@selenoid.autotests.cloud/wd/hub");
        String baseUrl = System.getProperty("baseUrl", "https://demoqa.com");
        System.getProperty("selenoid_login", "user1");
        System.getProperty("selenoid_password", "1234");

        Configuration.baseUrl = baseUrl;
        Configuration.browser = browser;
        Configuration.browserVersion = browserVersion;
        Configuration.browserSize = browserSize;
        Configuration.pageLoadStrategy = "eager";
        Configuration.remote = remoteUrl;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }
    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

    }

    @Test
    @Tag("demoqa")
    void fillTheFormTest() {
        String firstName = testData.getFirstName();
        String lastName = testData.getLastName();
        String userEmail = testData.getEmail();
        String gender = testData.getGender();
        String userNumber = testData.getPhoneNumber();
        String day = testData.getDayOfMonth();
        String month = testData.getMonth();
        String year = testData.getYear();
        String subject = testData.getSubject();
        String hobby = testData.getHobby();
        String picture = testData.getPictureName();
        String currentAddress = testData.getAddress();
        String state = testData.getState();
        String city = testData.getCity(state);

        openRegistrationPage();
        setFirstName(firstName);
        setLastName(lastName);
        setUserEmail(userEmail);
        setGender(gender);
        setUserNumber(userNumber);
        setBirthDate(day, month, year);
        setSubject(subject);
        setHobby(hobby);
        uploadPicture(picture);
        setCurrentAddress(currentAddress);
        setState(state);
        setCity(city);
        submitForm();

        verifyResults(firstName, lastName, userEmail, gender, userNumber, day, month, year, subject, hobby, picture, currentAddress, state, city);
    }

    @Step("Открываем страницу регистрации")
    public void openRegistrationPage() {
        registrationPage.openPage();
    }

    @Step("Заполняем имя: {firstName}")
    public void setFirstName(String firstName) {
        registrationPage.setFirstName(firstName);
    }
    @Step("Заполняем фамилию: {lastName}")
    public void setLastName(String lastName) {
        registrationPage.setLastName(lastName);
    }

    @Step("Заполняем email: {userEmail}")
    public void setUserEmail(String userEmail) {
        registrationPage.setUserEmail(userEmail);
    }

    @Step("Выбираем пол: {gender}")
    public void setGender(String gender) {
        registrationPage.setGender(gender);
    }

    @Step("Заполняем номер телефона: {userNumber}")
    public void setUserNumber(String userNumber) {
        registrationPage.setUserNumber(userNumber);
    }

    @Step("Заполняем дату рождения: {day}, {month}, {year}")
    public void setBirthDate(String day, String month, String year) {
        registrationPage.setBirthDate(day, month, year);
    }

    @Step("Заполняем предмет: {subject}")
    public void setSubject(String subject) {
        registrationPage.setSubject(subject);
    }

    @Step("Выбираем хобби: {hobby}")
    public void setHobby(String hobby) {
        registrationPage.setHobby(hobby);
    }

    @Step("Загружаем картинку: {picture}")
    public void uploadPicture(String picture) {
        registrationPage.uploadPicture(picture);
    }

    @Step("Заполняем текущий адрес: {currentAddress}")
    public void setCurrentAddress(String currentAddress) {
        registrationPage.setCurrentAddress(currentAddress);
    }

    @Step("Выбираем штат: {state}")
    public void setState(String state) {
        registrationPage.setState(state);
    }

    @Step("Выбираем город: {city}")
    public void setCity(String city) {
        registrationPage.setCity(city);
    }

    @Step("Отправляем форму")
    public void submitForm() {
        registrationPage.submitForm();
    }

    @Step("Проверяем результаты: Student Name: {firstName} {lastName}, Student Email: {userEmail}, Gender: {gender}, Mobile: {userNumber}, Date of Birth: {day} {month},{year}, Subjects: {subject}, Hobbies: {hobby}, Picture: {picture}, Address: {currentAddress}, State and City: {state} {city}")
    public void verifyResults(String firstName, String lastName, String userEmail, String gender, String userNumber, String day, String month, String year, String subject, String hobby, String picture, String currentAddress, String state, String city) {
        registrationPage.verifyResultsModalAppears()
                .verifyResult("Student Name", firstName + " " + lastName)
                .verifyResult("Student Email", userEmail)
                .verifyResult("Gender", gender)
                .verifyResult("Mobile", userNumber)
                .verifyResult("Date of Birth", day + " " + month + "," + year)
                .verifyResult("Subjects", subject)
                .verifyResult("Hobbies", hobby)
                .verifyResult("Picture", picture)
                .verifyResult("Address", currentAddress)
                .verifyResult("State and City", state + " " + city)
                .closeModal();
    }
}