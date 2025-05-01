package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ex.ElementShould;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class RegistrationFormTests {
    RegistrationPage registrationPage = new RegistrationPage();

    @BeforeAll
    static void configureBrowserSettings() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fillTheFormTest() {
        registrationPage.openPage()
                .setFirstName("Harry")
                .setLastName("Potter")
                .setUserEmail("griffindor@rules.com")
                .setGender("Male")
                .setUserNumber("9871335846")
                .setBirthDate("31", "July", "1980")
                .setSubject("English")
                .setHobby("Reading")
                .uploadPicture("test.jpg")
                .setCurrentAddress("Hogwarts")
                .setState("Rajasthan")
                .setCity("Jaipur")
                .submitForm();

        registrationPage.verifyResultsModalAppears()
                .verifyResult("Student Name", "Harry Potter")
                .verifyResult("Student Email", "griffindor@rules.com")
                .verifyResult("Gender", "Male")
                .verifyResult("Mobile", "9871335846")
                .verifyResult("Date of Birth", "31 July,1980")
                .verifyResult("Subjects", "English")
                .verifyResult("Hobbies", "Reading")
                .verifyResult("Picture", "test.jpg")
                .verifyResult("Address", "Hogwarts")
                .verifyResult("State and City", "Rajasthan Jaipur")
                .closeModal();
    }

    @Test
    void minimalDataTest() {
        registrationPage.openPage()
                .setFirstName("Ron")
                .setLastName("Weasley")
                .setGender("Male")
                .setUserNumber("1234567890")
                .submitForm();

        registrationPage.verifyResultsModalAppears()
                .verifyResult("Student Name", "Ron Weasley")
                .verifyResult("Gender", "Male")
                .verifyResult("Mobile", "1234567890")
                .closeModal();
    }
    @Test
    void negativeTest() {
        registrationPage.openPage()
                .setFirstName("Draco")
                .setLastName("Malfoy")
                .submitForm();
        assertThrows(ElementShould.class, () -> {
            registrationPage.verifyResultsModalAppears();
        });
    }
}

