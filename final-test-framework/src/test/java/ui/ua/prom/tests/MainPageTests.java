package ui.ua.prom.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.ua.prom.models.User;
import ui.ua.prom.pages.CartPage;
import ui.ua.prom.pages.common_modules.Header;
import ui.ua.prom.pages.common_modules.SearchField;
import ui.ua.prom.resources.TestDataProvider;
import ui.ua.prom.utils.RetryAnalyzer;

import java.util.List;

import static ui.ua.prom.pages.MainPage.*;
import static ui.ua.prom.pages.SingleItemPage.isSingleItemPageOpen;
import static ui.ua.prom.pages.common_modules.Header.logOut;
import static ui.ua.prom.pages.common_modules.SearchField.*;
import static ui.ua.prom.pages.steps.CommonSteps.findAndAddFirstItemByName;
import static ui.ua.prom.pages.steps.CommonSteps.logInWithEmail;

public class MainPageTests extends BaseTest {
    private final User registeredUser = new User("stalvio.neto@gmail.com", "111222");
    private final String tShirtRequest = "футболк";
    private final String lampRequest = "лампы";

    @Test(description = "Search field's pick-list contains relevant to the request search results",
            dataProvider = "search-field-request-data", dataProviderClass = TestDataProvider.class)
    public void pickListContainsRelevantItems(String request) {
        SearchField.submitSearchRequest(request);

        Assert.assertTrue(isPickListResultsRelevantToRequest(request));
    }

    @Test(description = "Searched items can be sorted by price from lower to higher",
            dataProvider = "search-field-request-data", dataProviderClass = TestDataProvider.class)
    public void itemsCanBeSortedByPriceFromLowToHigh(String request) {
        SearchField.submitSearchRequest(request);
        sortItemByLowerPrice();

        Assert.assertTrue(areItemsSortedFromLowerToHigherPrice());
    }

    @Test(description = "Searched items can be sorted by price from higher to lower",
            dataProvider = "search-field-request-data", dataProviderClass = TestDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void itemsCanBeSortedByPriceFromHighToLow(String request) {
        SearchField.submitSearchRequest(request);
        sortItemByHigherPrice();

        Assert.assertTrue(areItemsSortedFromHigherToLowerPrice());
    }

    @Test(description = "Verify if user can filter displayed items by price",
            dataProvider = "bottom-top-price-data", dataProviderClass = TestDataProvider.class,
            retryAnalyzer = RetryAnalyzer.class)
    public void userCanSetPriceRange(String bottomPrice, String topPrice) {
        SearchField.submitSearchRequest(tShirtRequest);
        sortItemByHigherPrice();
        submitPriceFilterRange(bottomPrice, topPrice);

        Assert.assertTrue(getFirstItemPrice() <= Double.parseDouble(topPrice)
                && getLastItemPrice() >= Double.parseDouble(bottomPrice));
    }

    @Test(description = "Verify if any random item from the displayed list can be added in cart",
            dataProvider = "number-of-items-to-add-data",
            dataProviderClass = TestDataProvider.class,
            retryAnalyzer = RetryAnalyzer.class)
    public void userCanAddAnyItemWithinList(Integer randomNumberOfItems) {
        SearchField.submitSearchRequest(lampRequest);
        sortItemByHigherPrice();
        addRandomItems(randomNumberOfItems);

        Integer currentNumberOfItemInCart = Header.getNumberOfItemInCart();

        Assert.assertTrue(currentNumberOfItemInCart.equals(randomNumberOfItems));
    }

    @Test(description = "Verify that user can navigate to any single-item page in the list of items",
            dataProvider = "search-field-request-data",
            dataProviderClass = TestDataProvider.class)
    public void userCanNavigateToAnySingleItemPage(String searchRequest) {
        SearchField.submitSearchRequest(searchRequest);
        openRandomSingleItemFromList();

        Assert.assertTrue(isSingleItemPageOpen());
    }

    @Test(description = "Verify if cart contains the added item",
            dataProvider = "search-request-items-to-buy-data",
            dataProviderClass = TestDataProvider.class)
    public void cartContainsAddedItem(String request, String itemName) {
        SearchField.submitSearchRequest(request);
        addSingleItemByName(itemName);
        
        List<String> cartItemNames = CartPage.getItemNameList();

        for (String title : cartItemNames) {
            Assert.assertTrue(title.contains(itemName));
        }
    }

    @Test(description = "Verify if cart contains the same item after logout and the following login")
    public void userCartContainsSameItemsAfterReLogin() {
        logInWithEmail(registeredUser);
        findAndAddFirstItemByName("штора");
        List<String> userCartBeforeLogOut = CartPage.getItemNameList();

        logOut();

        logInWithEmail(registeredUser);
        List<String> userCartAfterLogIn = CartPage.getItemNameList();

        Assert.assertEquals(userCartBeforeLogOut, userCartAfterLogIn);
    }
}
