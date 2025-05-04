package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import com.github.javafaker.Faker;

import java.util.Arrays;
import java.util.List;

public class JavaFakerTest {
    RegistrationPage registrationPage = new RegistrationPage();
    Faker faker = new Faker();

    @BeforeAll
    static void configureBrowserSettings() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fillTheFormTest() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String userEmail = faker.internet().emailAddress();
        List<String> genders = Arrays.asList("Male", "Female", "Other");
        String gender = genders.get(faker.number().numberBetween(0, genders.size()));
        String userNumber = faker.phoneNumber().subscriberNumber(10);
        String day = String.valueOf(faker.number().numberBetween(1, 28));
        List<String> months = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
        String month = months.get(faker.number().numberBetween(0,months.size()));
        String year = String.valueOf(faker.number().numberBetween(1900, 2025));
        String subject = "English";
        String hobby = "Reading";
        String picture = "test.jpg";
        String currentAddress = faker.address().fullAddress();
        String state = "Rajasthan";
        String city = "Jaipur";


        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(userEmail)
                .setGender(gender)
                .setUserNumber(userNumber)
                .setBirthDate (day, month, year)
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
