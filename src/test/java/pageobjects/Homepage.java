package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Homepage {
    public WebDriver driver;

    public Homepage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[data-bdd='farefinder-option-bundles']")
    public WebElement bundlesButton;

    @FindBy(css = "[data-bdd='farefinder-package-bundleoption-flight']")
    public WebElement flightButton;

    @FindBy(css = "[data-bdd='farefinder-package-bundleoption-hotel']")
    public WebElement hotelButton;

    @FindBy(css = "[data-bdd='farefinder-package-bundleoption-car']")
    public WebElement carButton;

    @FindBy(css = "[data-bdd='farefinder-package-origin-location-input']")
    public WebElement flyFromInput;

    @FindBy(css = "[data-bdd='farefinder-package-destination-location-input']")
    public WebElement flyToInput;

    @FindBy(css = "[data-bdd='farefinder-package-startdate-input']")
    public WebElement departingDatePicker;

    @FindBy(css = "[data-bdd='farefinder-package-enddate-input']")
    public WebElement returningDatePicker;

    @FindBy(css = "[data-bdd='farefinder-package-search-button']")
    public WebElement searchButton;

}
