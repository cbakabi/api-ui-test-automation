package automation.stepDefs;

import automation.operations.ExcelReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MyStepdefs {

  private static final String BASE_URL = "https://reqres.in";
  private static final Logger LOGGER = LoggerFactory.getLogger(MyStepdefs.class);
  private static final String EMAIL = "eve.holt@reqres.in";
  private static final String PASSWORD = "pistol";
  private static Response response;
  private WebDriver driver;

  @Given("I am a/an new/existing user requesting to {string}")
  public void iRegisterANewUserAccount(String requestType) {
    RestAssured.baseURI = BASE_URL;
    RequestSpecification request = RestAssured.given();
    request.header("Content-Type", "application/json");
    response =
        request
            .body("{ \"email\":\"" + EMAIL + "\", \"password\":\"" + PASSWORD + "\"}")
            .post("/api/" + requestType);
    LOGGER.info("Post request made");
  }

  @Given("I retrieve the resource")
  public void iRetrieveTheResource() {
    RestAssured.baseURI = BASE_URL;
    RequestSpecification request = RestAssured.given();
    request.header("Content-Type", "application/json");
    response = request.get("/api/unknown");
    LOGGER.info("Get request made to retrieve resource");
  }

  @Then("I receive the message containing {string}")
  public void iReceiveTheMessage(String message) {
    assertThat(response.getBody().jsonPath().toString().contains(message));
    LOGGER.info("Message received");
  }

  @Then("I get a {int} status code")
  public void iGetAStatusCode(int statusCode) {
    assertThat(response.getStatusCode()).isEqualTo(statusCode);

  }

  @And("the response message contains the following headers")
  public void theResponseMessageContainsTheFollowingHeaders(List<String> messages) {
    List<String> messageValue = messages;
    Iterator message = messageValue.iterator();
    while (message.hasNext()) {
      assertThat(response.getBody().asString()).contains(message.next().toString());
    }
  }

  @Given("I am on the yahoo page")
  public void iAmOnTheYahooPage() {
    System.setProperty("webdriver.chrome.driver", "src/test/java/chromedriver.exe");
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    driver.get("https://yahoo.com");
    LOGGER.info("Web automation testing begins");
  }

  @Given("I navigate to the login page")
  public void iNavigateToTheLoginPage() {
    driver.navigate().to("https://login.yahoo.com/");
  }

  @When("I enter the username in the login page")
  public void iEnterTheUsernameInTheLoginPage() {
    String idValue = "login-username";
    driver.findElement(By.id(idValue)).sendKeys(ExcelReader.readExcelValue(idValue));
  }

  @When("I click on the Next button")
  public void iClickOnTheNextButton() {
    driver.findElement(By.id("login-signin")).click();
  }

  @When("I enter the password in the login page")
  public void iEnterTheInTheLoginPage() {
    String idValue = "login-passwd";
    driver.findElement(By.id(idValue)).sendKeys(ExcelReader.readExcelValue(idValue));
  }

  @Then("I navigate to the Finance button")
  public void iNavigateToTheFinanceButton() {
    driver.navigate().to("https://uk.finance.yahoo.com/");
  }

  @When("I navigate to the calendar button from the Market Data tab")
  public void iNavigateToTheCalendarButtonFromTheMarketDataTab() {
    driver.navigate().to("https://uk.finance.yahoo.com/calendar");
  }

  @Then("I validate {string} is present on the 25th of July")
  public void iValidateTheFollowingValuesArePresentOnThe25ThJuly(String dayValue) {
    if (dayValue.equals("Earnings")) {
      assertThat(
              driver.equals(
                  "https://uk.finance.yahoo.com/calendar/earnings?from=2022-07-24&to=2022-07-30&day=2022-07-25"))
          .toString()
          .contains("earnings");
    } else if (dayValue.equals("Stock splits")) {
      assertThat(
              driver.equals(
                  "https://uk.finance.yahoo.com/calendar/splits?from=2022-07-24&to=2022-07-30&day=2022-07-25"))
          .toString()
          .contains("splits");
    } else if (dayValue.equals("Economic events"))  {
      assertThat(
              driver.equals(
                  "https://uk.finance.yahoo.com/calendar/economic?from=2022-07-24&to=2022-07-30&day=2022-07-25"))
          .toString()
          .contains("economic");
    }else{
      LOGGER.info("No such parameter found");
    }
  }

  @Then("I leave the yahoo page")
  public void iLeaveTheYahooPage() {
    driver.quit();
  }
}
