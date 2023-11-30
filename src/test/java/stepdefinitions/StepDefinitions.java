package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.After;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import pageobjects.Homepage;
import pageobjects.ResultsPage;


import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StepDefinitions {

    private WebDriver driver;

    public void startDriver(String url){

        ChromeOptions co = new ChromeOptions();
        co.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", new File("src/test/resources/drivers/chromedriver.exe").getPath());
        driver = new ChromeDriver(co);
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
    public void iEnterFlightFromTo(String string1, String string2) throws InterruptedException {
        Homepage homepage = new Homepage(driver);
        homepage.flyFromInput.sendKeys(string1);
        Thread.sleep(500);
        homepage.flyFromInput.sendKeys(Keys.TAB);
        homepage.flyToInput.sendKeys(string2);
        Thread.sleep(500);
        homepage.flyToInput.sendKeys(Keys.TAB);
    }

    @And("I select as a departing date the next day and returning after {int} days")
    public void iSelectAsADepartingDateTheNextDayAndReturningAfterDays(int days) {
        Homepage homepage = new Homepage(driver);
        homepage.departingDatePicker.click();
        LocalDate currentDate = LocalDate.now();
        LocalDate tomorrowDate = currentDate.plusDays(1);

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String formattedCurrentDate = tomorrowDate.format(inputFormatter);

        LocalDate date = LocalDate.parse(formattedCurrentDate, inputFormatter);

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");

        String formattedTomorrowDate = date.format(outputFormatter);
        String xPathExpression = "//td[contains(@aria-label,'" + formattedTomorrowDate + "')]";

        LocalDate futureDate = tomorrowDate.plusDays(days);

        String formattedFutureDate = futureDate.format(inputFormatter);

        LocalDate fDate = LocalDate.parse(formattedFutureDate, inputFormatter);

        String formattedFDate = fDate.format(outputFormatter);
        String fXPathExpression = "//td[contains(@aria-label,'" + formattedFDate + "')]";

        WebElement tomorrowDatePicker = driver.findElement(By.xpath(xPathExpression));
        tomorrowDatePicker.click();
        WebElement futureDatePicKer = driver.findElement(By.xpath(fXPathExpression));
        futureDatePicKer.click();
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
