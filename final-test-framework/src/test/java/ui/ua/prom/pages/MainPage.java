package ui.ua.prom.pages;

import com.codeborne.selenide.*;
import com.codeborne.selenide.selector.WithText;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.*;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;
import static ui.ua.prom.pages.CartPage.*;

public class MainPage {

    static By sortByScoreButton = By.cssSelector("span[data-qaid=sort_by_-score]");
    static By sortByLowerPriceButton = By.cssSelector("span[data-qaid=sort_by_price]");
    static By sortByHigherPriceButton = By.cssSelector("span[data-qaid=sort_by_-price]");

    static By itemList = By.cssSelector(".yJNOx.js-productad");
    static By itemsPriceList = By.cssSelector("span[data-qaid=product_price]");

    static By listingBanner = By.cssSelector("div[data-qaid=listing-banner]");

    static By bottomPriceInput = By.cssSelector("input[data-qaid=price_local__gte]");
    static By topPriceInput = By.cssSelector("input[data-qaid=price_local__lte]");
    static By acceptPriceButton = By.cssSelector("button[data-qaid=accept_price]");

    static By mainBanner = By.cssSelector("img[data-qaid=banner_img]");

    @Step("Sort items from lowest price to highest one")
    public static void sortItemByLowerPrice() {
        $(sortByLowerPriceButton).should(Condition.visible).click();
        itemsListIsVisible();
        getListingBanner().shouldBe(Condition.visible);
    }

    @Step("Sort items from highest price to lowest one")
    public static void sortItemByHigherPrice() {
        $(sortByHigherPriceButton).should(Condition.visible).click();
        itemsListIsVisible();
        getListingBanner().shouldBe(Condition.visible);
    }

    @Step("Check if items are sorted from lowest price to highest one")
    public static boolean areItemsSortedFromLowerToHigherPrice() {
        $(listingBanner).shouldBe(Condition.visible);

        List<Double> priceList = $$(itemsPriceList).stream()
                .map(item -> Double.valueOf(item.getAttribute("data-qaprice")))
                .collect(Collectors.toList());
        List<Double> sortedList = priceList.stream().sorted().collect(Collectors.toList());

        return (priceList.equals(sortedList));
    }

    @Step("Check if items are sorted from highest price to lowest one")
    public static boolean areItemsSortedFromHigherToLowerPrice() {
        itemsListIsVisible();

        List<Double> priceList = $$(itemsPriceList).stream()
                .map(item -> Double.valueOf(item.getAttribute("data-qaprice")))
                .collect(Collectors.toList());
        List<Double> sortedList = priceList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        return (priceList.equals(sortedList));
    }

    @Step("Set the price range for displayed items")
    public static void submitPriceFilterRange(String bottomPrice, String topPrice) {
        setBottomPrice(bottomPrice);
        setTopPrice(topPrice);
        $(acceptPriceButton).shouldBe(Condition.enabled).click();
        getListingBanner().shouldBe(Condition.visible);
    }

    @Step("Set the item's lowest price to display")
    public static void setBottomPrice(String bottomPrice) {
        $(bottomPriceInput).shouldBe(Condition.visible).sendKeys(bottomPrice);
    }

    @Step("Set the item's top price to display")
    public static void setTopPrice(String topPrice) {
        $(topPriceInput).shouldBe(Condition.visible).sendKeys(topPrice);
    }

    @Step("The first item from in the list is returned")
    public static Double getFirstItemPrice() {
        return Double.parseDouble($$(itemsPriceList).first().getAttribute("data-qaprice"));
    }

    @Step("The last item is the list is returned")
    public static Double getLastItemPrice() {
        return Double.parseDouble($$(itemsPriceList).last().getAttribute("data-qaprice"));
    }

    @Step("The random item is added in Cart")
    public static void addRandomItems(int numberOfItems) {
        List<Integer> selectedIndexes = new ArrayList<>();

        Integer listSize = $$(itemList).size();

        for(int i=0; i < numberOfItems; i++) {
            Integer index = getRandomIndex(listSize);

            while(selectedIndexes.contains(index)) {
                index = getRandomIndex(listSize);
            }

            if(cartPageOpen()) {
                $$(itemList).get(index).$("a[data-qaid=buy-button]").should(Condition.visible).doubleClick();
            }else {
                $$(itemList).get(index).$("a[data-qaid=buy-button]").should(Condition.visible).click();
            }
            waitForCartPageToOpen();
            CartPage.closeCartIfOpen(); 
            selectedIndexes.add(index);
        }
    }

    @Step("{name} is added to Cart")
    public static void addSingleItemByName(String name) {
        $$(itemList).stream().filter(item -> item.$("a[data-qaid=product_link]").getAttribute("title").contains(name))
                .findFirst().get().parent().$("a[data-qaid=buy-button]").click();

        waitForCartPageToOpen();
        CartPage.closeCartIfOpen();
    }

    @Step("Multiple {names} items are added to Cart")
    public static void addMultipleItemsByNames(String[] names) {
        for(String name: names)
            addSingleItemByName(name);
    }

    @Step("Wait for list of items to displayed")
    private static void itemsListIsVisible() {
        $(listingBanner).shouldBe(Condition.visible);
    }

    @Step("Get random item index")
    private static Integer getRandomIndex(Integer listSize) {
        Random random = new Random();
        return random.nextInt(listSize);
    }

    @Step("Get listing banner")
    public static SelenideElement getListingBanner() {
        return $(listingBanner);
    }
}
