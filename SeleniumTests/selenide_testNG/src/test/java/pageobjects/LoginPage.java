package pageobjects;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    static By phoneInputField = By.cssSelector("input.new-not-require-input");
    static By submitButton = By.id("login-or-register-button");
    static By termsAndConditionsIcon = By.linkText("Политикой конфиденциальности");

    public static void submitPhoneNumber(String phone) {
        $(phoneInputField).sendKeys(phone);
        $(submitButton).should(Condition.enabled).click();
    }

    public static void openTermsAndConditionsPage() {
        $(termsAndConditionsIcon).should(Condition.visible).click();
    }
}
