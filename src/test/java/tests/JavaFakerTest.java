package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import utils.TestDataGenerator;

public class JavaFakerTest {
    RegistrationPage registrationPage = new RegistrationPage();
    TestDataGenerator testData = new TestDataGenerator();

    @BeforeAll
    static void configureBrowserSettings() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
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

        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(userEmail)
                .setGender(gender)
                .setUserNumber(userNumber)
                .setBirthDate(day, month, year)
                .setSubject(subject)
                .setHobby(hobby)
                .uploadPicture(picture)
                .setCurrentAddress(currentAddress)
                .setState(state)
                .setCity(city)
                .submitForm();

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