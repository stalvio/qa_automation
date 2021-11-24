package ui.ua.prom.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.ua.prom.pages.CartPage;

import static ui.ua.prom.pages.SingleItemPage.*;

public class SingleItemTest extends SingleItemBaseTest {

    @Test(description = "Item can be added to the shopping-cart using 'Купить' button on single-item page")
    public void itemCanBeAddedInCart() {
        String itemName = getItemName();
        addItemInCart();

        Assert.assertEquals(itemName, CartPage.getItemNameList().get(0));
    }
}
