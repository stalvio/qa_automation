package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import static pageobjects.Header.*;
import static pageobjects.LoginPage.*;
import static pageobjects.TermsAndConditionsPage.*;

public class TermsAndConditionsTests extends BaseTests {

    @Test
    public void pageIsAccessibleFromLogin() {
       openLoginPage();
       openTermsAndConditionsPage();
       Assert.assertTrue(verifyTermsAndConditionsPageTitle());
    }
}
