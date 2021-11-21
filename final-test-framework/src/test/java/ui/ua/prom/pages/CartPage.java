package ui.ua.prom.pages;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;

public class CartPage {

    static By cartPage = By.id("shoppingCartIframe");
    static By closeButton = By.cssSelector("button[data-qaid=close]");
    static By cartItems = By.cssSelector("a[data-qaid=close]");

    public static void closeCartIfOpen() {
        System.out.println(cartPageOpen());
        if(cartPageOpen())
            $(closeButton).should(Condition.visible).click();
        $(cartPage).shouldBe(Condition.disappear);
    }

    public static boolean cartPageOpen() {
        return $(cartPage).isDisplayed();
    }

    public static void waitForCartPageToOpen() {
        $(cartPage).shouldBe(Condition.visible);
    }

    public static List<String> getItemNameList() {
        return $$(cartItems).stream().map(item -> item.$("a[data-qaid=product-name]")
                .getText())
                .collect(Collectors.toList());
    }

}
