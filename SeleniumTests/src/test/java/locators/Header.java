package locators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Header extends BasePage {

    public Header(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "/html/body/div/div[1]/div[1]/div/div[2]/div/div/div[2]/div[3]/button[1]")
    private WebElement loginButton;
    @FindBy(css = "#page-block > div > div._1KcTA._2mWCU.qv3iA._2fM7Z._30Ce5._1sZ22._2hSrY > div > div >" +
            " div:nth-child(2) > div:nth-child(2) > button")
    private WebElement myPromIcon;
    @FindBy(css = "#page-block > div > div._1KcTA._2mWCU.qv3iA._2fM7Z._30Ce5._1sZ22._2hSrY > div > div >" +
            " div:nth-child(2) > div:nth-child(3) > button:nth-child(3)")
    private WebElement registrationButton;

    public LoginPage openLoginPage() {
        loginButton.click();
        return new LoginPage(driver);
    }

    public RegistrationPage openRegistrationPage() {
        registrationButton.click();
        return new RegistrationPage(driver);
    }

    public boolean isUserLogged() {
        return myPromIcon.isDisplayed();
    }
}
