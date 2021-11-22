package ui.ua.prom.tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

import ui.ua.prom.models.ErrorText;
import ui.ua.prom.models.User;


import static ui.ua.prom.pages.SingInPage.*;
import static ui.ua.prom.pages.common_modules.Header.*;

public class SingInTest extends SingInBaseTest {

    private final User registeredUser = new User("stalvio.neto@gmail.com", "111222");
    private final User unRegisteredUser = new User("unregistered@gmail.com", "");
    private final User userWithInvalidPassword = new User("stalvio.neto@gmail.com", "222111");
    private final User userWithInvalidEmailFormat = new User("stal", "");


    @Test(description = "Login page is open after clicking login-icon in Header",
            suiteName = "PageAccessibility")
    public void loginPageAccessibleFromHeader() {

        Assert.assertTrue(isSignInPageOpen());
    }

    @Test(description = "User is logged when using valid email and password")
    @Severity(SeverityLevel.CRITICAL)
    public void registeredUserCanSingIn() {
        singInWithEmail(registeredUser);

        Assert.assertTrue(isUserLogged());
    }

    @Test(description = "User is not logged when using unregistered email")
    public void unRegisteredUserCanNotSingIn () {
        clickEmailButton();
        submitEmail(unRegisteredUser.getEmail());

        Assert.assertEquals(ErrorText.EMAIL_NOT_REGISTERED.message, getEmailErrorMessage());
    }

    @Test(description = "User is not logged when using valid email and invalid password")
    public void noSingInWithInvalidPassword() {
        singInWithEmail(userWithInvalidPassword);

        Assert.assertEquals(ErrorText.WRONG_USER_CREDENTIALS.message, getEmailErrorMessage());
    }

    @Test(description = "User is not logged when using invalid email format")
    public void noSingInWithInvalidEmailFormat () {
        clickEmailButton();
        submitEmail(userWithInvalidEmailFormat.getEmail());

        Assert.assertEquals(ErrorText.WRONG_EMAIL_FORMAT.message, getEmailErrorMessage());
    }
}

