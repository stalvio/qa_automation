package ui.ua.prom.resources;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "search-field-request-data")
    public Object[] searchRequestDataProv() {
        return new Object[]{"ламп", "футболк"};
    }

    @DataProvider(name = "bottom-top-price-data")
    public Object[] bottomTopPriceDataProv() {
        return new Object[][] {
                {"400" , "800"}
        };
    }
}
