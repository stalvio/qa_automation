package ui.ua.prom.pages.steps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import ui.ua.prom.models.User;
import ui.ua.prom.pages.SingInPage;
import ui.ua.prom.pages.common_modules.Header;
import ui.ua.prom.pages.common_modules.SearchField;

import static com.codeborne.selenide.Selenide.$$;
import static ui.ua.prom.pages.MainPage.*;
import static ui.ua.prom.pages.MainPage.getListingBanner;
import static ui.ua.prom.pages.common_modules.SearchField.getPicklistItems;
import static ui.ua.prom.pages.common_modules.SearchField.openPickList;

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

    @Step("Navigate to random single-item page")
    public static void navigateToRandomPopularPickListItem() {
        openPickList();
        getPicklistItems().get(getRandomIndex(getPicklistItems().size())).click();
        getListingBanner().shouldBe(Condition.visible);
    }

    public static void openRandomSingleItemPage() {
        navigateToRandomPopularPickListItem();
        openRandomSingleItemFromList();
    }
}
