package ui.ua.prom.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.ua.prom.pages.CartPage;

import static ui.ua.prom.pages.SingleItemPage.*;

public class SingleItemTest extends SingleItemBaseTest {

    @Test
    public void itemCanBeAddedInCart() {
        String itemName = getItemName();
        addItemInCart();

        Assert.assertEquals(getItemName(), CartPage.getItemNameList().get(0));
    }
}
