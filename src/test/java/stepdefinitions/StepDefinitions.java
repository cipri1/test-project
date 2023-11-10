package stepdefinitions;

import java.util.Date;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.After;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.Homepage;
import pageobjects.ResultsPage;


import java.io.File;

public class StepDefinitions {

    private WebDriver driver;

    public void startDriver(String url){

        System.setProperty("webdriver.chrome.driver", new File("chromedriver.exe").getPath());
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();

    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("I navigate on {string} website")
    public void iNavigateOnWebsite(String site) {
        startDriver(site);
    }

    @And("I select all travel methods")
    public void iSelectAllTravelMethods() {
        Homepage homepage = new Homepage(driver);
        homepage.bundlesButton.click();
        homepage.flightButton.click();
        homepage.hotelButton.click();
        homepage.carButton.click();
    }

    @And("I enter flight from {string} to {string}")
    public void iEnterFlightFromTo(String string1, String string2) {
        Homepage homepage = new Homepage(driver);
        homepage.flyFromInput.sendKeys(string1);
        homepage.flyToInput.sendKeys(string2);
    }

    @And("I select as a departing date the next day and returning after {int} days")
    public void iSelectAsADepartingDateTheNextDayAndReturningAfterDays(int days) {
        Date today = new Date();
    }

    @When("I click find deal button")
    public void iClickFindDealButton() {
        Homepage homepage = new Homepage(driver);
        homepage.searchButton.click();
    }

    @Then("I expect there is at least one result returned")
    public void iExpectThereIsAtLeastOneResultReturned() {
        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertTrue(resultsPage.resultListCard.isDisplayed());
    }
}
