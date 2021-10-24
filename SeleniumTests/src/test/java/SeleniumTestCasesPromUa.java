import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.charset.Charset;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.google.common.base.Predicates.notNull;
import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.Assume.assumeThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class SeleniumTestCasesPromUa {

    static WebDriver driver;
    static String email;
    WebDriverWait waiter;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        email = generateRandomString();
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

        waiter.until(new ExpectedCondition<Boolean>() {

            @org.jetbrains.annotations.Nullable
            @Override
            public Boolean apply(@org.jetbrains.annotations.Nullable WebDriver driver) {
                return findElement(LocatorType.ID, "enterPassword").isDisplayed();
            }
        });
}

    @Test
    public void userCanNotLoginWithInValidEmail() throws InterruptedException {

        findElementAndClick(LocatorType.XPATH, "/html/body/app-root/div/div/rz-header/header/div/div/ul/li[3]/rz-user/button");

        WebElement emailField = findElement(LocatorType.ID, "auth_email");
        emailField.sendKeys("st@gmail.com");

        WebElement passField = findElement(LocatorType.ID, "auth_pass");
        passField.sendKeys("112233");

        findElementAndClick(LocatorType.CSS, ".button.button--large.button--green.auth-modal__submit.ng-star-inserted");
    }

    @Test
    public void userCanSelectCategory() throws InterruptedException {

        WebElement firstCategoryLink = findElement(LocatorType.XPATH,
                "/html/body/app-root/div/div/rz-main-page/div/main/main-page-content/app-fat-menu-tablet/nav/ul/li[4]/a");
        System.out.println(firstCategoryLink.getAttribute("role"));
        System.out.println(firstCategoryLink.isDisplayed());
        firstCategoryLink.click();
    }

    @Test
    public void userCanNavigateToAllSalesPage() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        findElementAndClick(LocatorType.XPATH, "/html/body/app-root/div/div/rz-main-page/div/main/main-page-content/top-slider/ul/li/a");
        Thread.sleep(4000);
    }

    @Test
    public void userCanNavigateToSupportPage() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement headerButton = findElement(LocatorType.CSS, "button.header__button");
        headerButton.click();

        WebElement supportButton = driver.findElement(By.partialLinkText("Информация о компании"));
        WebDriverWait wait10 = new WebDriverWait(driver, 10);
        wait10.until(elementToBeClickable(supportButton));
        // wait10.until(new SuccessfulClick(btn));
        supportButton.click();
        Actions actions = new Actions(driver);
        actions.moveToElement(supportButton).click().build().perform();

        Thread.sleep(4000);
    }

    @Test
    public void implicitlyWaitTest() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement itemMenu = driver.findElement(By.xpath("/html/body/app-root/div/div/rz-main-page/div/aside/main-page-sidebar/sidebar-fat-menu/div/ul/li[1]/a"));
        itemMenu.click();

        findElementAndClick(LocatorType.CSS, ".tile-cats__heading.tile-cats__heading_type_center.ng-star-inserted");

        WebElement asusLaptopItem = driver.findElement(By.partialLinkText("Ноутбук Asus TUF"));
        asusLaptopItem.click();
    }

    @Test
    public void explicitlyWaitTest() {
        WebElement itemMenu = driver.findElement(By.xpath("/html/body/app-root/div/div/rz-main-page/div/aside/main-page-sidebar/sidebar-fat-menu/div/ul/li[1]/a"));
        itemMenu.click();

        findElementAndClick(LocatorType.CSS, ".tile-cats__heading.tile-cats__heading_type_center.ng-star-inserted");

        WebElement asusLaptopItem = driver.findElement(By.partialLinkText("Ноутбук Asus TUF"));
        asusLaptopItem.click();
        var waiter = new WebDriverWait(driver, 10);
        waiter.until(new ExpectedCondition<Boolean>() {

            @org.jetbrains.annotations.Nullable
            @Override
            public Boolean apply(@org.jetbrains.annotations.Nullable WebDriver driver) {
                return driver.findElement(By.xpath("//*[@id=\"#scrollArea\"]/div[1]/div[1]/div/product-gallery-main/z-gallery-main-thumbnails-list/div/div/figure/div/rz-gallery-main-content-image/img")).isDisplayed();
            }
        });
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

