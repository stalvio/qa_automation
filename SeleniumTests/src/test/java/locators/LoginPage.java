package locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Email")
    private WebElement emailIcon;
    @FindBy(linkText = "Google")
    private WebElement googleIcon;
    @FindBy(linkText = "Facebook")
    private WebElement facebookIcon;
    @FindBy(id = "phone_field")
    private WebElement phoneInputField;
    @FindBy(css = "#react-portal > div:nth-child(3) > div > div._2tyTS._2X5s5 > div > div > div >" +
            " div._1KcTA._3Z31M._2VwIO._2IhEl._2Xu5n._1gd4o._3A4_L >" +
            " div._1KcTA._1pk_w._184R0._3SC6X._1uh7N._3Tjtv._1IyGd._945qm > div:nth-child(3) >" +
            " div > div > span")
    private WebElement registrationLink;
    @FindBy(css = ".VspXp._20d86._14vhB.HzxkA")
    private WebElement closeButton;
    @FindBy(xpath = "//*[@id=\"react-portal\"]/div[3]/div/div[2]/div/div/div/div[1]/div/span")
    private WebElement title;


    @FindBy(id = "email_field")
    private WebElement emailField;
    @FindBy(id = "emailConfirmButton")
    private WebElement emailConfirmationButton;
    @FindBy(id = "enterPassword")
    private WebElement passwordField;
    @FindBy(id = "enterPasswordConfirmButton")
    private WebElement passConfirmationButton;

    @FindBy(css = "#react-portal > div:nth-child(3) > div > div._2tyTS._2X5s5 > div > div > div >" +
            " div._1KcTA._3Z31M._2VwIO._2IhEl._2Xu5n._1gd4o._3A4_L._1sZ22 > form > div:nth-child(2) >" +
            " div:nth-child(2) > span")
    private WebElement errorField;

    public void loginWithEmail(String email, String password) {
        emailIcon.click();

        waiter.until(new ExpectedCondition<Boolean>() {

            @org.jetbrains.annotations.Nullable
            @Override
            public Boolean apply(@org.jetbrains.annotations.Nullable WebDriver driver) {
                return driver.findElement(By.id("email_field")).isDisplayed();
            }
        });
        emailField.sendKeys(email);

        waiter.until(new ExpectedCondition<Boolean>() {

            @org.jetbrains.annotations.Nullable
            @Override
            public Boolean apply(@org.jetbrains.annotations.Nullable WebDriver driver) {
                return emailConfirmationButton.isEnabled();
            }
        });

        emailConfirmationButton.click();

        passwordField.sendKeys(password);

        passConfirmationButton.click();
    }

    public boolean isErrorFieldDisplayed() {
        return errorField.isDisplayed();
    }
}
