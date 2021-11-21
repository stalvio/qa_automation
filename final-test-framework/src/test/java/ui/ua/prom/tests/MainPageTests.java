package ui.ua.prom.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.ua.prom.pages.CartPage;
import ui.ua.prom.pages.common_modules.Header;
import ui.ua.prom.pages.common_modules.SearchField;
import ui.ua.prom.resources.TestDataProvider;
import ui.ua.prom.utils.RetryAnalyzer;

import static ui.ua.prom.pages.MainPage.*;
import static ui.ua.prom.pages.common_modules.SearchField.*;

public class MainPageTests extends BaseTest {

    @Test(dataProvider = "search-field-request-data", dataProviderClass= TestDataProvider.class)
    public void pickListContainsRelevantItems(String request) {
        SearchField.submitSearchRequest(request);

        Assert.assertTrue(isPickListResultsRelevantToRequest(request));
    }

    @Test(dataProvider = "search-field-request-data", dataProviderClass= TestDataProvider.class)
    public void itemsCanBeSortedByPriceFromLowToHigh(String request) {
        SearchField.submitSearchRequest(request);
        sortItemByLowerPrice();

        Assert.assertTrue(areItemsSortedFromLowerToHigherPrice());
    }

    @Test(dataProvider = "search-field-request-data", dataProviderClass= TestDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void itemsCanBeSortedByPriceFromHighToLow(String request) {
        SearchField.submitSearchRequest(request);
        sortItemByHigherPrice();

        Assert.assertTrue(areItemsSortedFromHigherToLowerPrice());
    }

    @Test(dataProvider = "bottom-top-price-data", dataProviderClass= TestDataProvider.class,
            retryAnalyzer = RetryAnalyzer.class)
    public void userCanSetPriceRange(String bottomPrice, String topPrice) {
        SearchField.submitSearchRequest("футболк");
        sortItemByHigherPrice();
        submitPriceFilterRange(bottomPrice, topPrice);

        Assert.assertTrue( getFirstItemPrice() <= Double.parseDouble(topPrice)
                && getLastItemPrice() >= Double.parseDouble(bottomPrice));
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void userCanAddAnyItemWithinList() {
        SearchField.submitSearchRequest("лампы");
        sortItemByHigherPrice();
        addRandomItems(3);

        Integer actualNumber = Header.getNumberOfItemInCart();

        Assert.assertTrue(actualNumber.equals(3));
    }

    @Test
    public void cartContainsOnlyAddedItems() {
        SearchField.submitSearchRequest("лампы");
        addItemByName("Кемпинговая лампа Лампа");

    }
}
