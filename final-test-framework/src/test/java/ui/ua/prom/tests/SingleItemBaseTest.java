package ui.ua.prom.tests;

import org.testng.annotations.BeforeMethod;

import static ui.ua.prom.pages.steps.CommonSteps.openRandomSingleItemPage;

public class SingleItemBaseTest extends BaseTest {

    @BeforeMethod
    @Override
    public void setupTest() {
        super.setupTest();
        openRandomSingleItemPage();
    }
}
