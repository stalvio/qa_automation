package ui.ua.prom.utils;

import com.codeborne.selenide.*;
import org.openqa.selenium.*;
import ui.ua.prom.pages.AppConfig;

import static com.codeborne.selenide.Selenide.*;

public class Driver {

    public static void initDriver() {

        TestConfig.initConfig();

        Configuration.timeout = 10000;
        Configuration.startMaximized = true;

        if (TestConfig.isHeadless()) {
            Configuration.headless = true;
        } else {
            Configuration.headless = false;
        }

        switch (TestConfig.browser) {
            case "chrome":
                Configuration.browser = Browsers.CHROME;
                break;
            case "firefox":
                Configuration.browser = Browsers.FIREFOX;
                break;
            default:
                Configuration.browser = Browsers.CHROME;
                break;
        }
    }

    public static WebDriver getCurrentDriver() {
        return WebDriverRunner.driver().getWebDriver();
    }

    public static void clearCookies() {
        open(AppConfig.baseUrl);
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    public static void close() {
        getCurrentDriver().quit();
    }
}

