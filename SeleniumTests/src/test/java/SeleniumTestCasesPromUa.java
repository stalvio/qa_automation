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
    public void registerButtonIsDisabledWhenNameFiledEmpty() throws InterruptedException {
        WebElement registerButton = findElement(LocatorType.CSS, "#page-block > div > div._1KcTA._2mWCU.qv3iA._2fM7Z._30Ce5._1sZ22._2hSrY > div > div > div:nth-child(2) > div:nth-child(3) > button:nth-child(3)");
        registerButton.click();

        waiter.until(new ExpectedCondition<Boolean>() {

            @org.jetbrains.annotations.Nullable
            @Override
            public Boolean apply(@org.jetbrains.annotations.Nullable WebDriver driver) {
                return findElement(LocatorType.ID, "registrationConfirmButton").isDisplayed();
            }
        });

        WebElement emailField = findElement(LocatorType.ID, "registrationEmail");
        emailField.sendKeys(email);

        WebElement passWordField = findElement(LocatorType.ID, "createPassword");
        passWordField.sendKeys("1112233");

        WebElement submitButton = findElement(LocatorType.ID, "registrationConfirmButton");

        Assert.assertFalse(Boolean.parseBoolean(submitButton.getAttribute("disabled")));
    }

    @Test
    public void registerButtonIsDisabledWhenEmailFiledEmpty() throws InterruptedException {
        WebElement registerButton = findElement(LocatorType.CSS, "#page-block > div > div._1KcTA._2mWCU.qv3iA._2fM7Z._30Ce5._1sZ22._2hSrY > div > div > div:nth-child(2) > div:nth-child(3) > button:nth-child(3)");
        registerButton.click();

        waiter.until(new ExpectedCondition<Boolean>() {

            @org.jetbrains.annotations.Nullable
            @Override
            public Boolean apply(@org.jetbrains.annotations.Nullable WebDriver driver) {
                return findElement(LocatorType.ID, "registrationConfirmButton").isDisplayed();
            }
        });

        WebElement nameField = findElement(LocatorType.ID, "firstName");
        nameField.sendKeys("testUser");

        WebElement passWordField = findElement(LocatorType.ID, "createPassword");
        passWordField.sendKeys("1112233");

        WebElement submitButton = findElement(LocatorType.ID, "registrationConfirmButton");

        Assert.assertFalse(Boolean.parseBoolean(submitButton.getAttribute("disabled")));
    }

    @Test
    public void userCanRegister() throws InterruptedException {
        WebElement registerButton = findElement(LocatorType.CSS, "#page-block > div > div._1KcTA._2mWCU.qv3iA._2fM7Z._30Ce5._1sZ22._2hSrY > div > div > div:nth-child(2) > div:nth-child(3) > button:nth-child(3)");
        registerButton.click();

        waiter.until(new ExpectedCondition<Boolean>() {

            @org.jetbrains.annotations.Nullable
            @Override
            public Boolean apply(@org.jetbrains.annotations.Nullable WebDriver driver) {
                return findElement(LocatorType.ID, "registrationConfirmButton").isDisplayed();
            }
        });

        WebElement nameField = findElement(LocatorType.ID, "firstName");
        nameField.sendKeys("testUser");

        WebElement emailField = findElement(LocatorType.ID, "registrationEmail");
        emailField.sendKeys(email);

        WebElement passWordField = findElement(LocatorType.ID, "createPassword");
        passWordField.sendKeys("1112233");

        WebElement submitButton = findElement(LocatorType.ID, "registrationConfirmButton");
        submitButton.click();
    }

    @Test
    public void userCanNotRegisterWithExistedEmail() throws InterruptedException {
        WebElement registerButton = findElement(LocatorType.CSS, "#page-block > div > div._1KcTA._2mWCU.qv3iA._2fM7Z._30Ce5._1sZ22._2hSrY > div > div > div:nth-child(2) > div:nth-child(3) > button:nth-child(3)");
        registerButton.click();

        waiter.until(new ExpectedCondition<Boolean>() {

            @org.jetbrains.annotations.Nullable
            @Override
            public Boolean apply(@org.jetbrains.annotations.Nullable WebDriver driver) {
                return findElement(LocatorType.ID, "registrationConfirmButton").isDisplayed();
            }
        });

        WebElement nameField = findElement(LocatorType.ID, "firstName");
        nameField.sendKeys("testUser");

        WebElement emailField = findElement(LocatorType.ID, "registrationEmail");
        emailField.sendKeys("stalvio.neto@gmail.com");

        WebElement passWordField = findElement(LocatorType.ID, "createPassword");
        passWordField.sendKeys("1112233");

        WebElement submitButton = findElement(LocatorType.ID, "registrationConfirmButton");
        submitButton.click();
    }


    @Test
    public void userCanLoginWithValidEmail() throws InterruptedException {

        findElementAndClick(LocatorType.XPATH, "/html/body/div/div[1]/div[1]/div/div[2]/div/div/div[2]/div[3]/button[1]");

        WebElement emailIcon = findElement(LocatorType.LINK, "Email");
        emailIcon.click();

        waiter.until(new ExpectedCondition<Boolean>() {

            @org.jetbrains.annotations.Nullable
            @Override
            public Boolean apply(@org.jetbrains.annotations.Nullable WebDriver driver) {
                return findElement(LocatorType.ID, "email_field").isDisplayed();
            }
        });

        WebElement emailField = findElement(LocatorType.ID, "email_field");
        emailField.sendKeys("stalvio.neto@gmail.com");

        findElementAndClick(LocatorType.ID, "emailConfirmButton");
    }

    @Test
    public void userCanNotLoginWithInValidEmail() throws InterruptedException {

        findElementAndClick(LocatorType.XPATH, "//*[@id=\"page-block\"]/div/div[2]/div/div/div[2]/div[3]/button[1]");

        WebElement emailIcon = findElement(LocatorType.LINK, "Email");
        emailIcon.click();

        waiter.until(new ExpectedCondition<Boolean>() {

            @org.jetbrains.annotations.Nullable
            @Override
            public Boolean apply(@org.jetbrains.annotations.Nullable WebDriver driver) {
                return findElement(LocatorType.ID, "email_field").isDisplayed();
            }
        });


        WebElement emailField = findElement(LocatorType.ID, "email_field");
        emailField.sendKeys("st@gmail.com");

        findElementAndClick(LocatorType.ID, "emailConfirmButton");
    }

    @Test
    public void userCanSelectCategoryUsingShareLink() throws InterruptedException {

        WebElement shareLink = findElement(LocatorType.PART_LINK,
                "Акции");
        shareLink.click();

        waiter.until(new ExpectedCondition<Boolean>() {

            @org.jetbrains.annotations.Nullable
            @Override
            public Boolean apply(@org.jetbrains.annotations.Nullable WebDriver driver) {
                return findElement(LocatorType.CSS, "#page-block > div > div._3L2HZ > div " +
                        "> div > div > div > div > ul > li:nth-child(2) > div > div " +
                        "> div:nth-child(1) > div > div > a > span").isDisplayed();
            }
        });

        findElementAndClick(LocatorType.LINK, "Чай, кава, смаколики");

        Thread.sleep(2000);
    }

    @Test
    public void userCanSpecifyTargetLocation() throws InterruptedException {

        WebElement locationDropDown = findElement(LocatorType.CSS,
                "#page-block > div > header > div > div > div > div > div.yJNOx._28Dd5 " +
                        "> div > div > div.yJNOx._2CObz._1sJFD > button > span");
        locationDropDown.click();

        WebElement kharkovCity = waiter.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//*[@id=\"react-portal\"]/div[3]" +
                        "/div/div[2]/div/div[2]/div/div[2]" +
                        "/ul/li[1]/ul/li[7]/div")));
        kharkovCity.click();
    }

    @Test
    public void userCanCheckCart() throws InterruptedException {

        findElementAndClick(LocatorType.LINK, "Начать продавать на prom.ua");

        WebElement cartTitle = waiter.until(ExpectedConditions
                .presenceOfElementLocated(By.cssSelector("#features_slider > li:nth-child(1) > img")));

        Assert.assertTrue(findElement(LocatorType.XPATH, "//*[@id=\"join-now-tabbed-first" +
                "\"]/div[7]/button").isDisplayed());
    }

    @Test
    public void halloweenMenuItemIsPresent() throws InterruptedException {
        List<WebElement> menuItems = driver.findElements(By.tagName("li"));

        Assert.assertTrue(menuItems.stream().filter(item -> item.getText().contains("Halloween")).findAny().isPresent());
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

