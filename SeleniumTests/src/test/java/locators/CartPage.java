package locators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#container > div.b-cart > div.b-cart__header.js-stop-scroll.qa-cart-header > span")
    private WebElement pageTitle;

    public String getTitle() {
        return pageTitle.getText();
    }
}
