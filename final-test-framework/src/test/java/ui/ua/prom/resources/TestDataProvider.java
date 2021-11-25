package ui.ua.prom.resources;

import org.testng.annotations.DataProvider;

import java.util.Random;

public class TestDataProvider {

    @DataProvider(name = "search-field-request-data")
    public Object[] searchRequestDataProv() {
        return new Object[] {"adapter", "samsung"};
    }

    @DataProvider(name = "bottom-top-price-data")
    public Object[][] bottomTopPriceDataProv() {
        return new Object[][] {
                {"400","800"}
        };
    }

    @DataProvider(name = "search-request-items-to-buy-data")
    public Object[][] itemsToBuyDataProvider() {
        return new Object[][]{
                {"ноут", "Блок питания для ноута HP 19.5V 4.62A 90W 4.8x1.7 мм + кабель питания (2091) KM"}
        };
    }

    @DataProvider(name = "number-of-items-to-add-data")
    public Object[] numberOfItemToAdd() {
        Random random = new Random();
        return new Object[] {random.nextInt(3) + 1};
    }
}

