package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultsPage {

    public ResultsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[data-stid='lodging-card-responsive']")
    public WebElement resultListCard;
}
