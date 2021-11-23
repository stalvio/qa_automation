package ui.ua.prom.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ui.ua.prom.models.User;
import ui.ua.prom.pages.common_modules.Header;

import static com.codeborne.selenide.Selenide.*;

public class SingInPage {

    static public By singInPage = By.cssSelector("._2tyTS._2X5s5");
    static By emailButton = By.cssSelector("div[data-qaid=email_btn]");
    static By emailInputField = By.id("email_field");
    static By emailSubmitButton = By.id("emailConfirmButton");
    static By passwordInputField = By.id("enterPassword");
    static By passwordSubmitButton = By.id("enterPasswordConfirmButton");
    static By emailIsNotRegisteredMessage = By.cssSelector("span[data-qaid=error_field]");

    @Step("Check if Sign-in page is open")
    public static boolean isSignInPageOpen() {
        return $(singInPage).isDisplayed();
    }

    @Step("Login with email address and password")
    public static void singInWithEmail(User user) {
        clickEmailButton();
        submitEmail(user.getEmail());
        submitPassword(user.getPassword());
    }

    @Step("Click email login button")
    public static void clickEmailButton() {
        $(emailButton).click();
    }

    @Step("Submit \"{email}\" - email address")
    public static void submitEmail(String email) {
        $(emailInputField).should(Condition.visible).sendKeys(email);
        $(emailSubmitButton).should(Condition.enabled).click();
    }

    @Step("Submit password")
    public static void submitPassword(String password) {
        $(passwordInputField).should(Condition.visible).sendKeys((password));
        $(passwordSubmitButton).should(Condition.enabled).click();
    }

    @Step("Get error message")
    public static String getEmailErrorMessage() {
        return $(emailIsNotRegisteredMessage).should(Condition.visible).getText();
    }
}
