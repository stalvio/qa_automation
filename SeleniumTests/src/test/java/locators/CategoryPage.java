package locators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CategoryPage extends BasePage {

    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div._1KcTA.ggTpW._1sZ22._3-zi4.FpIl2")
    private List<WebElement> categoryItems;

    public WebElement getCategoryItemByName(String categoryName) {
        return categoryItems.stream().filter(item -> item.getText().contains(categoryName)).findFirst().get();
    }

}
