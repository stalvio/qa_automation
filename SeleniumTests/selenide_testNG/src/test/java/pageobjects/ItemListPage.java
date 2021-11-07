package pageobjects;

import com.codeborne.selenide.Condition;
import org.junit.Assert;
import tests.BaseTests;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class ItemListPage extends BaseTests {

    static By goodsGroupName = By.cssSelector("div.row.catalog-name");
    static By goodList = By.cssSelector("header.good-description");

    public static String getGoodsGroupName() {
        return $(goodsGroupName).getText();
    }

    public static void addGood(String name) {
        $$(goodList).get(0).should(Condition.visible);
        $$(goodList).stream().filter(good -> good.getText().contains(name)).findFirst().get().sibling(2).
                $(By.cssSelector(".icon-cart")).click();
    }
}
