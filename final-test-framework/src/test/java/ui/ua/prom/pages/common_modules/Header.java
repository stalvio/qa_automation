package ui.ua.prom.pages.common_modules;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.selector.WithText;
import org.openqa.selenium.By;
import ui.ua.prom.pages.CartPage;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static ui.ua.prom.pages.SingInPage.singInPage;

public class Header {

    static By singInIcon = By.cssSelector("button[data-qaid=sign-in]");
    static By singUpIcon = By.cssSelector("button[data-qaid=qa_register_btn]");
    static By myPromIcon = By.cssSelector("button[data-qaid=menu_btn]");
    static By cartIcon = By.cssSelector("button[data-qaid=shopping_cart]");
    static By cartCounter = By.cssSelector("[data-qaid=counter]");
    static By myPromMenu = By.cssSelector("div[data-placement=bottom-end]");

    public static void openSingInPage() {
        $(singInIcon).click();
    }

    public static void openSingUpPage() {
        $(singUpIcon).click();
    }

    public static boolean isUserLogged() {
        $(singInPage).should(Condition.disappear);
        return $(myPromIcon).isDisplayed();
    }

    public static void openCart() {
        $(cartIcon).shouldBe(Condition.visible).click();
        CartPage.waitForCartPageToOpen();
    }

    public static Integer getNumberOfItemInCart() {
        return Integer.valueOf($(cartCounter).shouldBe(Condition.visible).getText());
    }

    public static void logOut() {
        $(myPromIcon).hover();
        $(myPromMenu).shouldBe(Condition.visible);
        $(myPromMenu).$(withText("Выйти")).click();
    }
}
