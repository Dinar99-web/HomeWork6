package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.TextBoxPage;

public class TextBoxTests {
    TextBoxPage textBoxPage = new TextBoxPage();

    @BeforeAll
    static void configureBrowserSettings() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }
    @Test
    void fillTextBoxTest() {
        String name = "Harry Potter";
        String email = "griffindor@rules.com";
        String currentAddress = "Hogwarts, Gryffindor Tower";
        String permanentAddress = "Little Whinging, Privet Drive";

        textBoxPage.openPage()
                .setFullName(name)
                .setEmail(email)
                .setCurrentAddress(currentAddress)
                .setPermanentAddress(permanentAddress)
                .submitForm()
                .checkResult(name, email, currentAddress, permanentAddress);
    }
}
