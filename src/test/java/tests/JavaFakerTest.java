package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import utils.TestDataGenerator;

public class JavaFakerTest {
    RegistrationPage registrationPage = new RegistrationPage();

    @BeforeAll
    static void configureBrowserSettings() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fillTheFormTest() {
        String firstName = TestDataGenerator.getFirstName();
        String lastName = TestDataGenerator.getLastName();
        String userEmail = TestDataGenerator.getEmail();
        String gender = TestDataGenerator.getGender();
        String userNumber = TestDataGenerator.getPhoneNumber();
        String day = TestDataGenerator.getDayOfMonth();
        String month = TestDataGenerator.getMonth();
        String year = TestDataGenerator.getYear();
        String subject = TestDataGenerator.getSubject();
        String hobby = TestDataGenerator.getHobby();
        String picture = TestDataGenerator.getPictureName();
        String currentAddress = TestDataGenerator.getAddress();
        String state = TestDataGenerator.getState();
        String city = TestDataGenerator.getCity(state);

        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .submitForm();
    }
}