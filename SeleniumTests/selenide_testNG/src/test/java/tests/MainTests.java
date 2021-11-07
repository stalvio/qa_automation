package tests;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.Header;
import pageobjects.ItemListPage;
import pageobjects.MainPage;

import static pageobjects.Header.*;

public class MainTests extends BaseTests {

    @Test(dataProvider = "data-provider")
    public void canChangeCity(String cityName) {
        Header.selectCity(cityName);
        Assert.assertTrue(getCurrentCityName().equals(cityName));
    }

    @DataProvider(name = "data-provider")
    public Object[] dpCityName() {
        return new Object[]{"Буча", "Киев"};
    }

    @Test(dataProvider = "category-data-provider")
    public void openMainCategoryPage(String mainCategoryName, String subCategoryName) throws InterruptedException {
        MainPage.openMainCategoryPage(mainCategoryName);
        MainPage.openSubCategoryPage(subCategoryName);
        Assert.assertTrue(ItemListPage.getGoodsGroupName().equals(subCategoryName.toUpperCase()));
    }

    @DataProvider(name = "category-data-provider")
    public Object[][] dpCategoriesName() {
        return new Object[][]{{"Смартфоны и телефоны", "Смартфоны SAMSUNG"}, {"Посуда", "Формы для выпечки"}};
    }

    @Test
    public void userCanSwitchLanguage() throws InterruptedException {
        String currentLang = getCurrentLanguage();
        Header.changeLanguage();
        Assert.assertFalse(currentLang.equals(getCurrentLanguage()));
    }

    @Test
    public void basketIsInitiallyEmpty() {
        Header.hoverCartIcon();
        Assert.assertTrue(Header.isEmptyBasketAlertDisplayed());
    }

    @Test(dataProvider = "category-data-provider-2")
    public void goodsCanBeAddedToBasket(String mainCategoryName, String subCategoryName, String goodName) throws InterruptedException {
        MainPage.openMainCategoryPage(mainCategoryName);
        MainPage.openSubCategoryPage(subCategoryName);
        ItemListPage.addGood(goodName);
    }

    @DataProvider(name = "category-data-provider-2")
    public Object[][] dpGoodName() {
        return new Object[][]{{"Смартфоны и телефоны", "Смартфоны SAMSUNG", "Galaxy M52"}};
    }
}
