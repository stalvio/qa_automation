package tests;

import com.sun.tools.javac.Main;
import dto.User;
import locators.*;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class SeleniumTestCasesPromUa {

    static WebDriver driver;
    static String email;
    static WebDriverWait waiter;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        email = generateRandomString();
        waiter = new WebDriverWait(driver, 10);
    }

    @Before
    public void setupTest() {
        driver.get("https://prom.ua/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void closeUp() {
        driver.quit();
    }

    @Test
    public void registerButtonIsDisabledWhenNameFieldEmpty() throws InterruptedException {
        User user = new User("", email, "1112233");

        Header header = new Header(driver);
        RegistrationPage registrationPage = header.openRegistrationPage();

        waiter.until(new ExpectedCondition<Boolean>() {

            @org.jetbrains.annotations.Nullable
            @Override
            public Boolean apply(@org.jetbrains.annotations.Nullable WebDriver driver) {
                return findElement(LocatorType.ID, "registrationConfirmButton").isDisplayed();
            }
        });
        registrationPage.registryUser(user);

        Assert.assertFalse(Boolean.parseBoolean(registrationPage.getConfirmationButton().getAttribute("disabled")));
    }

    @Test
    public void registerButtonIsDisabledWhenEmailFieldEmpty() throws InterruptedException {
        User user = new User("testUser", "", "1112233");

        Header header = new Header(driver);
        RegistrationPage registrationPage = header.openRegistrationPage();

        waiter.until(new ExpectedCondition<Boolean>() {

            @org.jetbrains.annotations.Nullable
            @Override
            public Boolean apply(@org.jetbrains.annotations.Nullable WebDriver driver) {
                return findElement(LocatorType.ID, "registrationConfirmButton").isDisplayed();
            }
        });
        registrationPage.registryUser(user);

        Assert.assertFalse(Boolean.parseBoolean(registrationPage.getConfirmationButton().getAttribute("disabled")));
    }

    @Test
    public void userCanRegister() throws InterruptedException {
        User user = new User("testUser", email, "1112233");

        Header header = new Header(driver);
        RegistrationPage registrationPage = header.openRegistrationPage();

        waiter.until(new ExpectedCondition<Boolean>() {

            @org.jetbrains.annotations.Nullable
            @Override
            public Boolean apply(@org.jetbrains.annotations.Nullable WebDriver driver) {
                return findElement(LocatorType.ID, "registrationConfirmButton").isDisplayed();
            }
        });
        registrationPage.registryUser(user);
    }

    @Test
    public void userCanNotRegisterWithExistedEmail() throws InterruptedException {
        String existedEmail = "stalvio.neto@gmail.com";
        User user = new User("testUser", existedEmail, "1112233");

        Header header = new Header(driver);
        RegistrationPage registrationPage = header.openRegistrationPage();

        waiter.until(new ExpectedCondition<Boolean>() {

            @org.jetbrains.annotations.Nullable
            @Override
            public Boolean apply(@org.jetbrains.annotations.Nullable WebDriver driver) {
                return findElement(LocatorType.ID, "registrationConfirmButton").isDisplayed();
            }
        });
        registrationPage.registryUser(user);
    }

    @Test
    public void userCanLoginWithValidEmail() throws InterruptedException {

        Header header = new Header(driver);
        LoginPage loginPage = header.openLoginPage();

        loginPage.loginWithEmail("stals_007@gmail.com", "112233");

        Assert.assertTrue(header.isUserLogged());
    }

    @Test
    public void userCanNotLoginWithInValidEmail() throws InterruptedException {

        Header header = new Header(driver);
        LoginPage loginPage = header.openLoginPage();

        loginPage.loginWithEmail("st@gmail.com", "112233");

        Assert.assertTrue(loginPage.isErrorFieldDisplayed());
    }

    @Test
    public void userCanSelectCategoryUsingShareLink() throws InterruptedException {

        MainPage mainPage = new MainPage(driver);
        CategoryPage categoryPage = mainPage.navigateToCategoryPageByName("Акции");

        waiter.until(new ExpectedCondition<Boolean>() {

            @org.jetbrains.annotations.Nullable
            @Override
            public Boolean apply(@org.jetbrains.annotations.Nullable WebDriver driver) {
                return findElement(LocatorType.CSS, "#page-block > div > div._3L2HZ > div " +
                        "> div > div > div > div > ul > li:nth-child(2) > div > div " +
                        "> div:nth-child(1) > div > div > a > span").isDisplayed();
            }
        });

        categoryPage.getCategoryItemByName("Чай, кава, смаколики").click();
    }

    @Test
    public void userCanSpecifyTargetLocation() throws InterruptedException {

        MainPage mainPage = new MainPage(driver);
        LocationPage locationPage = mainPage.navigateToLocationPage();

        locationPage.selectLocationByName("Харьков");

        Assert.assertTrue(mainPage.getLocation().equals("Харьков"));
    }

    @Test
    public void userCanCheckCart() throws InterruptedException {

        MainPage mainPage = new MainPage(driver);
        CartPage cartPage = mainPage.navigateToCart();
    }

    @Test
    public void halloweenMenuItemIsPresent() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.getMenuItemByName("Halloween").isDisplayed());
    }

    public void findElementAndClick(LocatorType type, String selector) {
        findElement(type, selector).click();
    }

    public WebElement findElement(LocatorType type, String selector) {

        switch (type) {
            default:
            case ID:
                return driver.findElement(By.id(selector));
            case CSS:
                return driver.findElement(By.cssSelector(selector));
            case XPATH:
                return driver.findElement(By.xpath(selector));
            case CLASS:
                return driver.findElement(By.className(selector));
            case LINK:
                return driver.findElement(By.linkText(selector));
            case TAG:
                return driver.findElement(By.tagName(selector));
            case PART_LINK:
                return driver.findElement(By.partialLinkText(selector));
        }
    }

    private static String generateRandomString() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-", "") + "@gmail.com";
    }

    enum LocatorType {
        ID, XPATH, CSS, CLASS, LINK, PART_LINK, TAG
    }
}

