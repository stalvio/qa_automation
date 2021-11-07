package tests;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import static com.codeborne.selenide.Selenide.open;

public class BaseTests {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("@BeforeSuite");
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("@BeforeMethod");
        Configuration.startMaximized = true;
        open("https://eldorado.ua/");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("@AfterMethod");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("@AfterSuite");
    }
}
