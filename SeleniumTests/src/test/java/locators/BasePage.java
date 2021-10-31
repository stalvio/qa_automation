package locators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait waiter;

    public BasePage (WebDriver driver) {
        this.driver = driver;
        this.waiter = new WebDriverWait(driver, 5);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 50), this);
    }
}