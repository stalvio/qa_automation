package ui.ua.prom.pages;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static ui.ua.prom.pages.CartPage.*;

public class SingleItemPage {

    static By itemImage = By.cssSelector("img[data-qaid=image_preview]");
    static By sellerLink = By.cssSelector("a[data-qaid=company_name]");
    static By buyButton = By.cssSelector("button[data-qaid=buy-button]");
    static By productName = By.cssSelector("h1[data-qaid=product_name]");
    static By advantageBlock = By.cssSelector("ul[data-qaid=advantage_block]");

    public static boolean isSingleItemPageOpen() {
        return $(itemImage).isDisplayed() && $(sellerLink).isDisplayed();
    }

    public static void waitForSingleItemPageToLoad() {
        $(itemImage).shouldBe(Condition.visible);
        $(advantageBlock).shouldBe(Condition.visible);
    }

    public static void addItemInCartAndCloseCartPage() {
        addItemInCart();
        closeCartIfOpen();
    }

    public static void addItemInCart() {
        $(buyButton).shouldBe(Condition.visible).click();
        waitForCartPageToOpen();
        waitForCartItemsToDisplayed();
    }

    public static String getItemName() {
        return $(productName).shouldBe(Condition.visible).getText();
    }
}
