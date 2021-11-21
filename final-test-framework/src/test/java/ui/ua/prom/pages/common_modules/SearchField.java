package ui.ua.prom.pages.common_modules;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class SearchField {

    static By searchField = By.cssSelector("input._3tZPe");
    static By itemsPickList = By.cssSelector("._1KcTA._2-LvQ");
    static By submitButton = By.cssSelector(".VspXp._2Nito._3dQ3K._1eW42._3z4MG._3gvGh._2jLre._39p0b.B0CZe._29wUy");

    static By searchResults = By.cssSelector("._1NEkz ._1tCLn");

    public static boolean isItemsPickListDisplayed() {
        return $(itemsPickList).isDisplayed();
    }

    public static void typeSearchRequest(String request) {

        $(searchField).should(Condition.visible).sendKeys(request);
        $(submitButton).should(Condition.enabled);
    }

    public static void submitSearchRequest(String request) {
        typeSearchRequest(request);
        $(submitButton).should(Condition.visible).click();
    }

    public static boolean isPickListResultsRelevantToRequest(String request) {
        return $$(searchResults).stream().allMatch(item -> item.getText().contains(request));
    }
}
