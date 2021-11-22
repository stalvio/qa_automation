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
import static ui.ua.prom.pages.common_modules.Header.logOut;
import static ui.ua.prom.pages.common_modules.SearchField.*;
import static ui.ua.prom.pages.steps.CommonSteps.findAndAddFirstItemByName;
import static ui.ua.prom.pages.steps.CommonSteps.logInWithEmail;

public class MainPageTests extends BaseTest {
    private final User registeredUser = new User("stalvio.neto@gmail.com", "111222");


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

    @Test(description = "Check if user can filter displayed items by price",
            dataProvider = "bottom-top-price-data", dataProviderClass= TestDataProvider.class,
            retryAnalyzer = RetryAnalyzer.class)
    public void userCanSetPriceRange(String bottomPrice, String topPrice) {
        SearchField.submitSearchRequest("футболк");
        sortItemByHigherPrice();
        submitPriceFilterRange(bottomPrice, topPrice);

        Assert.assertTrue( getFirstItemPrice() <= Double.parseDouble(topPrice)
                && getLastItemPrice() >= Double.parseDouble(bottomPrice));
    }

    @Test(description = "Check if any random item from the displayed list can be added in cart",
            dataProvider = "number-of-items-to-add-data",
            dataProviderClass= TestDataProvider.class,
            retryAnalyzer = RetryAnalyzer.class)
    public void userCanAddAnyItemWithinList(Integer randomNumberOfItems) {
        SearchField.submitSearchRequest("лампы");
        sortItemByHigherPrice();
        addRandomItems(randomNumberOfItems);

        Integer currentNumberOfItemInCart = Header.getNumberOfItemInCart();

        Assert.assertTrue(currentNumberOfItemInCart.equals(randomNumberOfItems));
    }

    @Test(description = "check if cart contains the added item",
            dataProvider = "search-request-items-to-buy-data",
            dataProviderClass= TestDataProvider.class)
    public void cartContainsAddedItem(String request, String itemName) {
        SearchField.submitSearchRequest(request);
        addSingleItemByName(itemName);
        List<String> cartItemNames = CartPage.getItemNameList();

        for(String title: cartItemNames) {
            Assert.assertTrue(title.contains(itemName));
        }
    }

    @Test(description = "Check if cart contains the same item after logout and the following login")
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
