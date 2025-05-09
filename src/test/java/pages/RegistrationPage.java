package pages;

import com.codeborne.selenide.SelenideElement;
import components.CalendarComponent;
import components.RegistrationResultsModal;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    public RegistrationPage removeBanners() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }
    private final String TITLE_TEXT = "Student Registration Form";
    private CalendarComponent calendarComponent = new CalendarComponent();
    private RegistrationResultsModal registrationResultsModal = new RegistrationResultsModal();
    private final SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            dateOfBirthInput = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            hobbiesCheckbox = $("label[for=hobbies-checkbox-2]"),
            uploadPictureButton = $("#uploadPicture"),
            currentAddressInput = $("#currentAddress"),
            stateDropdown = $("#state"),
            cityDropdown = $("#city"),
            submitButton = $("#submit");

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        removeBanners();
        $(".practice-form-wrapper").shouldHave(text(TITLE_TEXT));
        return this;
    }
    public RegistrationPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);

        return this;
    }

    public RegistrationPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);

        return this;
    }

    public RegistrationPage setUserEmail(String userEmail) {
        userEmailInput.setValue(userEmail);

        return this;
    }

    public RegistrationPage setGender(String gender) {
        genderWrapper.$(byText(gender)).click();

        return this;
    }

    public RegistrationPage setUserNumber(String userNumber) {
        userNumberInput.setValue(userNumber);

        return this;
    }

    public RegistrationPage setBirthDate(String day, String month, String year) {
        dateOfBirthInput.click();
        calendarComponent.setDate(day, month, year);

        return this;
    }

    public RegistrationPage setSubject(String subject) {
        subjectsInput.setValue(subject).pressEnter();

        return this;
    }

    public RegistrationPage setHobby(String hobby) {
        $("label[for='" + hobby.toLowerCase() + "']").click();

        return this;
    }

    public RegistrationPage uploadPicture(String fileName) {
        uploadPictureButton.uploadFromClasspath(fileName);

        return this;
    }

    public RegistrationPage setCurrentAddress(String address) {
        currentAddressInput.setValue(address);

        return this;
    }

    public RegistrationPage setState(String state) {
        stateDropdown.click();
        $("#react-select-3-option-3").click();

        return this;
    }

    public RegistrationPage setCity(String city) {
        cityDropdown.click();
        $("#react-select-4-option-0").click();

        return this;
    }

    public void submitForm() {
        submitButton.click();
    }

    public RegistrationResultsModal verifyResultsModalAppears() {
        return registrationResultsModal.verifyModalAppears();
    }
}
