package ui.ua.prom.pages;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;

public class CartPage {

    static By cartPage = By.id("shoppingCartIframe");
    static By closeButton = By.cssSelector("button[data-qaid=close]");
    static By cartItems = By.cssSelector("a[data-qaid=close]");

    @Step("Close cart page if it's open")
    public static void closeCartIfOpen() {
        System.out.println(cartPageOpen());
        if(cartPageOpen())
            $(closeButton).should(Condition.visible).click();
        $(cartPage).shouldBe(Condition.disappear);
    }

    @Step("Cart page is open")
    public static boolean cartPageOpen() {
        return $(cartPage).isDisplayed();
    }

    @Step("Wait for Cart page to open")
    public static void waitForCartPageToOpen() {
        $(cartPage).shouldBe(Condition.visible);
    }

    @Step("Get all name of all items in Cart")
    public static List<String> getItemNameList() {
        return $$(cartItems).stream().map(item -> item.$("a[data-qaid=product-name]")
                .getText())
                .collect(Collectors.toList());
    }

}
