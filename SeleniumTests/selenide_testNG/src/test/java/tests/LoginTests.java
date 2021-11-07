package tests;

import org.junit.Assert;
import org.testng.annotations.Test;

import static pageobjects.Header.*;
import static pageobjects.LoginPage.*;
import static pageobjects.TermsAndConditionsPage.*;

public class LoginTests extends BaseTests {

    String validPhone = "965810731";
    String inValidPhone = "65810731";

    @Test
    public void canLoginWithValidCredentials() {
        openLoginPage();
        submitPhoneNumber(validPhone);
    }

    @Test
    public void canNotLoginWithInValidCredentials() {
        openLoginPage();
        submitPhoneNumber(inValidPhone);
    }

    @Test
    public void canNavigateToTermAndConditionPage() {
        openLoginPage();
        openTermsAndConditionsPage();
        Assert.assertTrue(verifyTermsAndConditionsPageTitle());
    }
}
