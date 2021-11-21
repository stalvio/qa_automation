package ui.ua.prom.tests;

import org.testng.annotations.BeforeMethod;

import static ui.ua.prom.pages.common_modules.Header.openSingInPage;

public class SingInBaseTest extends BaseTest {

    @BeforeMethod
    @Override
    public void setupTest() {
        super.setupTest();
        openSingInPage();
    }
}
