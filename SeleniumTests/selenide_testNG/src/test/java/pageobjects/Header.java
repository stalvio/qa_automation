package pageobjects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class Header {

    static By shopsLink = By.linkText("Магазины");
    static By shareLink = By.linkText("Bce Акции");
    static By cityLink = By.cssSelector(".valign-wrapper.city-select.display-mobile");
    static By citiesList = By.cssSelector("li.city-select-item div");
    static By eldogadoLogo = By.tagName("figure");

    static By searchField = By.cssSelector("input.search-input");
    static By loginIcon = By.cssSelector("div.header-icon-wrapper.account-wrapper");
    static By cartIcon = By.cssSelector("#content > div.data-root > div.bottom-tier-wrapper > div > " +
            "div.custom-column-content > div.custom-column-content-icons-wrapper.custom-column-content-icons-wrapper-not-authorized > " +
            "div.wrapper-cart-link > a > div > img");
    static By emptyBasketAlert = By.cssSelector("div.empty-basket-preview");

    static By ukrIcon = By.className("uk-lang-link");
    static By rusIcon = By.className("ru-lang-link");
    static By currentLanguage = By.cssSelector("a.active-lang-link");

    public static void openLoginPage() {
        $(loginIcon).click();
    }

    public static void openMainPage() {
        $(eldogadoLogo).click();
    }

    public static void openCart() {
        $(cartIcon).click();
    }

    public static void hoverCartIcon() {
        $(cartIcon).hover();
    }

    public static boolean isEmptyBasketAlertDisplayed() {
        return $(emptyBasketAlert).isDisplayed();
    }

    public static void selectCity(String cityName) {
        $(cityLink).click();
        $(".city-select-content").should(visible);
        $$(citiesList).stream().filter(city -> city.getText().equals(cityName)).findFirst().get().click();
        $(".city-select-content").shouldNotBe(visible);
    }

    public static String getCurrentCityName() {
        return $(cityLink).should(visible).getText();
    }

    public static String getCurrentLanguage() {
        return $(currentLanguage).getText();
    }

    public static void changeLanguage() {
        String currentLanguage = $("a.active-lang-link").getText();
        switch (currentLanguage) {
            default:
            case "УКР":
                $(rusIcon).click();
                break;
            case "РУС":
                $(ukrIcon).click();
                break;
        }
    }
}
