package locators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LocationPage extends BasePage {

    public LocationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "li div.mrs-regionList__wrapper__LRFe-")
    private List<WebElement> locations;

    public void selectLocationByName(String name) {
        locations.stream().filter(location -> location.getText().equals(name)).findFirst().get().click();
    }
}
