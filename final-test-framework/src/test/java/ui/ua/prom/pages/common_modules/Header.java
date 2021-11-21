package ui.ua.prom.pages.common_modules;


import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static ui.ua.prom.pages.SingInPage.singInPage;

public class Header {

    static By singInIcon = By.cssSelector("button[data-qaid=sign-in]");
    static By singUpIcon = By.cssSelector("button[data-qaid=qa_register_btn]");
    static By myPromIcon = By.cssSelector("button[data-qaid=menu_btn]");
    static By cartIcon = By.cssSelector("button[data-qaid=shopping_cart]");
    static By cartCounter = By.cssSelector("[data-qaid=counter]");

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

    public static Integer getNumberOfItemInCart() {
        return Integer.valueOf($(cartCounter).shouldBe(Condition.visible).getText());
    }

}
