package pageobjects;


import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    static By categoryMenuItems = By.cssSelector("div.main-category");
    static By subCategoryTitles = By.className(".node-item");

    public static void openMainCategoryPage(String categoryName) {
        $$(categoryMenuItems).stream().filter(item -> item.getText().contains(categoryName)).
                findFirst().get().click();
    }

    public static void openSubCategoryPage(String subCategoryName) {
        $(By.partialLinkText(subCategoryName)).should(Condition.visible).click();
    }
}
