package locators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "li._1tCLn")
    private List<WebElement> menuItems;
    @FindBy(css = "input._3tZPe")
    private WebElement searchField;
    @FindBy(css = "#page-block > div > header > div > div > div > div > div.yJNOx._28Dd5 > div > div >" +
            " div.yJNOx._2CObz._1sJFD > button")
    private WebElement locationMenu;
    @FindBy(css = "#page-block > div > header > div > div > div > div > div:nth-child(3) >" +
            " div > div:nth-child(3) > button")
    private WebElement cartIcon;

    public CategoryPage navigateToCategoryPageByName(String itemName) {
        menuItems.stream().filter(item -> item.getText().contains(itemName)).findFirst().get().click();
        return new CategoryPage(driver);
    }

    public WebElement getMenuItemByName(String name) {
        return menuItems.stream().filter(item -> item.getText().contains(name)).findFirst().get();
    }

    public LocationPage navigateToLocationPage() {
        locationMenu.click();
        return new LocationPage(driver);
    }

    public String getLocation() {
        return locationMenu.getText();
    }

    public CartPage navigateToCart() {
        cartIcon.click();
        return new CartPage(driver);
    }

}
