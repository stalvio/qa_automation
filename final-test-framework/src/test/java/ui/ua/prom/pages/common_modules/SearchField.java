package ui.ua.prom.pages.common_modules;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static ui.ua.prom.pages.MainPage.*;

public class SearchField {

    static By searchField = By.cssSelector("input._3tZPe");
    static By pickList = By.cssSelector("._1KcTA._2q9cL._3v4OG._1BvwO._3iCbA");
    static By itemsPickList = By.cssSelector("._1KcTA._2-LvQ");
    static By submitButton = By.cssSelector(".VspXp._2Nito._3dQ3K._1eW42._3z4MG._3gvGh._2jLre._39p0b.B0CZe._29wUy");

    static By searchResults = By.cssSelector("._1NEkz._1tCLn");
    static By popularPickListItems = By.cssSelector("a[data-qaid=popular]");

    @Step("Open search tool pick-list")
    public static void openPickList() {
        $(searchField).should(Condition.visible).click();
        $(itemsPickList).shouldBe(Condition.visible);
    }

    @Step("User types a '{request}' in search field")
    public static void typeSearchRequest(String request) {
        $(searchField).should(Condition.visible).sendKeys(request);
        $(pickList).shouldBe(Condition.visible);
        $(submitButton).should(Condition.enabled);
    }

    @Step("User types a '{request}' in search field")
    public static void submitSearchRequest(String request) {
        typeSearchRequest(request);
        $(submitButton).should(Condition.visible).click();
        getListingBanner().shouldBe(Condition.visible);
    }

    @Step("Check whether pick-list contains relevant item to the user request")
    public static boolean isPickListResultsRelevantToRequest(String request) {
        return $$(searchResults).stream().allMatch(item -> item.getText().contains(request));
    }

    @Step("Get item list in the opened pick-list")
    public static List<SelenideElement> getPicklistItems() {
        return $$(popularPickListItems);
    }
}
