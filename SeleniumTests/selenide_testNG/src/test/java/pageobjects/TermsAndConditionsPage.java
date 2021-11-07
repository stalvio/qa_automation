package pageobjects;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class TermsAndConditionsPage {

    static By titleName = By.cssSelector("#page-footer-content-id > div > div > div > div " +
            "> p:nth-child(1)");

    public static boolean verifyTermsAndConditionsPageTitle() {
        return $(titleName).should(Condition.visible).getText().contains("ПОЛИТИКА КОНФИДЕНЦИАЛЬНОСТИ ПРИ");
    }
}
