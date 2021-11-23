package ui.ua.prom.pages.steps;

import io.qameta.allure.Step;
import ui.ua.prom.models.User;
import ui.ua.prom.pages.SingInPage;
import ui.ua.prom.pages.common_modules.Header;
import ui.ua.prom.pages.common_modules.SearchField;

import static ui.ua.prom.pages.MainPage.addSingleItemByName;

public class CommonSteps {

    @Step("Search and then add the first relevant item in the result-list")
    public static void findAndAddFirstItemByName(String name) {
        SearchField.submitSearchRequest(name);
        addSingleItemByName(name);
    }

    @Step("Log in with user email credentials")
    public static void logInWithEmail(User user) {
        Header.openSingInPage();
        SingInPage.singInWithEmail(user);
    }
}
