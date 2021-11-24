package ui.ua.prom.tests;

import org.testng.annotations.*;
import ui.ua.prom.pages.AppConfig;
import ui.ua.prom.utils.Driver;

import static com.codeborne.selenide.Selenide.*;

public class BaseTest {

    @BeforeSuite
    public void setup() {
        Driver.initDriver();
    }

    @BeforeMethod
    public void setupTest() {
        open(AppConfig.baseUrl);
    }

    @AfterMethod
    public void clearCookies() {
        Driver.clearCookies();
    }

    @AfterSuite
    public void tearDown() {
        Driver.close();
    }
}
