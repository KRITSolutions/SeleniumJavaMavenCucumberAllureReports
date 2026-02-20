package org.example.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.qameta.allure.Step;
import org.example.pages.XPathPracticePage;
import org.example.utilities.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * XPathPracticeSteps class for Cucumber step definitions
 * Contains step implementations for XPath practice page scenarios
 */
public class XPathPracticeSteps {
    private static final Logger logger = LoggerFactory.getLogger(XPathPracticeSteps.class);
    private WebDriver driver;
    private XPathPracticePage xpathPracticePage;

    /**
     * Initialize page object and driver
     */
    private void initializePageObject() {
        driver = DriverManager.getDriver();
        xpathPracticePage = new XPathPracticePage(driver);
    }

    // ============== Given Steps ==============

    @Given("User is on XPath practice page")
    @Step("User is on XPath practice page")
    public void userIsOnXPathPracticePage() {
        initializePageObject();
        logger.info("User is on XPath practice page");
        String title = driver.getTitle();
        logger.info("Page title: {}", title);
    }

    // ============== When Steps ==============

    @When("User enters email as {string}")
    @Step("User enters email as {string}")
    public void userEntersEmail(String email) {
        xpathPracticePage.enterEmail(email);
        logger.info("User entered email: {}", email);
    }

    @When("User enters password as {string}")
    @Step("User enters password as {string}")
    public void userEntersPassword(String password) {
        xpathPracticePage.enterPassword(password);
        logger.info("User entered password: {}", password);
    }

    @When("User clicks sign in button")
    @Step("User clicks sign in button")
    public void userClicksSignInButton() {
        xpathPracticePage.clickSignInButton();
        logger.info("User clicked sign in button");
    }

    @When("User clicks checkbox element")
    @Step("User clicks checkbox element")
    public void userClicksCheckbox() {
        xpathPracticePage.clickCheckbox();
        logger.info("User clicked checkbox");
    }

    @When("User clicks radio button")
    @Step("User clicks radio button")
    public void userClicksRadioButton() {
        xpathPracticePage.clickRadioButton();
        logger.info("User clicked radio button");
    }

    @When("User selects {string} from dropdown")
    @Step("User selects {string} from dropdown")
    public void userSelectsFromDropdown(String option) {
        xpathPracticePage.selectFromDropdown(option);
        logger.info("User selected option: {}", option);
    }

    @When("User enters email with placeholder as {string}")
    @Step("User enters email with placeholder as {string}")
    public void userEntersEmailWithPlaceholder(String email) {
        xpathPracticePage.enterEmailWithPlaceholder(email);
        logger.info("User entered email with placeholder: {}", email);
    }

    @When("User clicks button with data attribute")
    @Step("User clicks button with data attribute")
    public void userClicksButtonWithDataAttribute() {
        xpathPracticePage.clickButtonWithDataAttribute();
        logger.info("User clicked button with data attribute");
    }

    @When("User performs login with email {string} and password {string}")
    @Step("User performs login with email {string} and password {string}")
    public void userPerformsLogin(String email, String password) {
        xpathPracticePage.signIn(email, password);
        logger.info("User signed in with email: {} and password: {}", email, password);
    }

    // ============== Then Steps ==============

    @Then("Success message should be displayed")
    @Step("Success message should be displayed")
    public void successMessageDisplayed() {
        boolean isDisplayed = xpathPracticePage.isSuccessMessageDisplayed();
        Assert.assertTrue(isDisplayed, "Success message is not displayed");
        logger.info("Success message is displayed");
    }

    @Then("Error message should be displayed")
    @Step("Error message should be displayed")
    public void errorMessageDisplayed() {
        boolean isDisplayed = xpathPracticePage.isErrorMessageDisplayed();
        Assert.assertTrue(isDisplayed, "Error message is not displayed");
        logger.info("Error message is displayed");
    }

    @Then("Success message contains {string}")
    @Step("Success message contains {string}")
    public void successMessageContains(String expectedText) {
        String actualText = xpathPracticePage.getSuccessMessage();
        Assert.assertTrue(actualText.contains(expectedText),
            "Success message does not contain: " + expectedText);
        logger.info("Success message contains expected text: {}", expectedText);
    }

    @Then("Table element should be visible")
    @Step("Table element should be visible")
    public void tableElementVisible() {
        boolean isVisible = xpathPracticePage.isTableVisible();
        Assert.assertTrue(isVisible, "Table element is not visible");
        logger.info("Table element is visible");
    }

    @Then("Dynamic element should be visible")
    @Step("Dynamic element should be visible")
    public void dynamicElementVisible() {
        boolean isVisible = xpathPracticePage.isDynamicElementVisible();
        Assert.assertTrue(isVisible, "Dynamic element is not visible");
        logger.info("Dynamic element is visible");
    }

    @Then("User should see {string} message")
    @Step("User should see {string} message")
    public void userShouldSeeMessage(String messageType) {
        if (messageType.equalsIgnoreCase("success")) {
            Assert.assertTrue(xpathPracticePage.isSuccessMessageDisplayed(),
                "Success message not displayed");
        } else if (messageType.equalsIgnoreCase("error")) {
            Assert.assertTrue(xpathPracticePage.isErrorMessageDisplayed(),
                "Error message not displayed");
        }
        logger.info("User see {} message", messageType);
    }

    @Then("Login should be successful")
    @Step("Login should be successful")
    public void loginShouldBeSuccessful() {
        Assert.assertTrue(xpathPracticePage.isSuccessMessageDisplayed(),
            "Login was not successful");
        logger.info("Login was successful");
    }
}

