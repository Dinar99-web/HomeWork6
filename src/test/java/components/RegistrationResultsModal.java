package components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class RegistrationResultsModal {
    private final String MODAL_TITLE = "Thanks for submitting the form";
    private final SelenideElement modalContent = $(".modal-content");
    private final SelenideElement closeButton = $("#closeLargeModal");

    public RegistrationResultsModal verifyModalAppears() {
        modalContent.shouldHave(text(MODAL_TITLE));

        return this;
    }
    public RegistrationResultsModal verifyResult(String key, String value) {
        $$("table tr").findBy(text(key)).$("td", 1).shouldHave(text(value));
        return this;
    }

    public void closeModal() {
        closeButton.click();
    }
}
