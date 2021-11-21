package ui.ua.prom.pages;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;
import static ui.ua.prom.pages.CartPage.*;

public class MainPage {



    //Sort-filters
    static By sortByScoreButton = By.cssSelector("span[data-qaid=sort_by_-score]");
    static By sortByLowerPriceButton = By.cssSelector("span[data-qaid=sort_by_price]");
    static By sortByHigherPriceButton = By.cssSelector("span[data-qaid=sort_by_-price]");

    static By itemList = By.cssSelector(".yJNOx.js-productad");
    static By itemsPriceList = By.cssSelector("span[data-qaid=product_price]");

    static By listingBanner = By.cssSelector("div[data-qaid=listing-banner]");

    static By bottomPriceInput = By.cssSelector("input[data-qaid=price_local__gte]");
    static By topPriceInput = By.cssSelector("input[data-qaid=price_local__lte]");
    static By acceptPriceButton = By.cssSelector("button[data-qaid=accept_price]");

    public static void sortItemByLowerPrice() {
        $(sortByLowerPriceButton).should(Condition.visible).click();
        itemsListIsVisible();
    }

    public static void sortItemByHigherPrice() {
        $(sortByHigherPriceButton).should(Condition.visible).click();
        itemsListIsVisible();
    }

    public static boolean areItemsSortedFromLowerToHigherPrice() {
        $(listingBanner).shouldBe(Condition.visible);

        List<Double> priceList = $$(itemsPriceList).stream()
                .map(item -> Double.valueOf(item.getAttribute("data-qaprice")))
                .collect(Collectors.toList());
        List<Double> sortedList = priceList.stream().sorted().collect(Collectors.toList());

        return (priceList.equals(sortedList));
    }

    public static boolean areItemsSortedFromHigherToLowerPrice() {
        itemsListIsVisible();

        List<Double> priceList = $$(itemsPriceList).stream()
                .map(item -> Double.valueOf(item.getAttribute("data-qaprice")))
                .collect(Collectors.toList());
        List<Double> sortedList = priceList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        return (priceList.equals(sortedList));
    }

    public static void submitPriceFilterRange(String bottomPrice, String topPrice) {
        setBottomPrice(bottomPrice);
        setTopPrice(topPrice);
        $(acceptPriceButton).shouldBe(Condition.enabled).click();
        itemsListIsVisible();
    }

    public static void setBottomPrice(String bottomPrice) {
        $(bottomPriceInput).shouldBe(Condition.visible).sendKeys(bottomPrice);
    }

    public static void setTopPrice(String topPrice) {
        $(topPriceInput).shouldBe(Condition.visible).sendKeys(topPrice);
    }

    public static Double getFirstItemPrice() {
        return Double.parseDouble($$(itemsPriceList).first().getAttribute("data-qaprice"));
    }

    public static Double getLastItemPrice() {
        return Double.parseDouble($$(itemsPriceList).last().getAttribute("data-qaprice"));
    }

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

    public static void addItemByName(String name) {
        for (String title : $$(itemList).stream().map(item -> item.$("a[data-qaid=product_link]").getAttribute("title")).collect(Collectors.toList()))
        {
    }
        $$(itemList).stream().filter(item -> item.$("a[data-qaid=product_link]").getAttribute("title").contains(name))
                .findFirst().get().click();

        waitForCartPageToOpen();
        CartPage.closeCartIfOpen();
    }

    private static void itemsListIsVisible() {
        $(listingBanner).shouldBe(Condition.visible);
    }

    private static Integer getRandomIndex(Integer listSize) {
        Random random = new Random();
        return random.nextInt(listSize);
    }

}
