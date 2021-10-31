package locators;

import dto.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "firstName")
    private WebElement nameField;
    @FindBy(id = "registrationEmail")
    private WebElement emailField;
    @FindBy(id = "createPassword")
    private WebElement passwordField;
    @FindBy(id = "registrationConfirmButton")
    private WebElement confirmationButton;

    public void registryUser(User user) {
        nameField.sendKeys(user.getName());
        emailField.sendKeys(user.getEmail());
        passwordField.sendKeys(user.getPassword());

        confirmationButton.click();
    }

    public WebElement getNameField() {
        return nameField;
    }

    public WebElement getEmailField() {
        return emailField;
    }

    public WebElement getPasswordField() {
        return passwordField;
    }

    public WebElement getConfirmationButton() {
        return confirmationButton;
    }
}
