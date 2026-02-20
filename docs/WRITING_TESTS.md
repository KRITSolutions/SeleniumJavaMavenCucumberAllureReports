# Writing Tests Guide

## Test Structure

Every test should follow the Arrange-Act-Assert (AAA) pattern:

1. **Arrange** - Set up test data and prerequisites
2. **Act** - Perform the action being tested
3. **Assert** - Verify the results

## Cucumber Feature Files

### Basic Feature File Structure

```gherkin
@Tag1 @Tag2
Feature: Feature description
  Short feature narrative
  
  Background:
    Given common setup steps
  
  @Smoke
  Scenario: Scenario description
    Given precondition
    When action
    Then assertion
```

### Scenario Examples

#### Example 1: Form Submission
```gherkin
@Smoke @Forms
Scenario: User can submit form with valid data
  Given User is on login page
  When User enters email "test@example.com"
  And User enters password "TestPass123"
  And User clicks login button
  Then Success message is displayed
  And User is redirected to dashboard
```

#### Example 2: Data-Driven Testing
```gherkin
@DataDriven @LoginTests
Scenario Outline: Login with different credentials
  Given User is on login page
  When User enters email "<email>"
  And User enters password "<password>"
  And User clicks login button
  Then User should see "<result>" message
  
  Examples:
    | email              | password   | result  |
    | valid@example.com  | Valid123   | Success |
    | invalid@example.com| Wrong123   | Error   |
    | empty@example.com  |            | Error   |
```

#### Example 3: Complex Workflow
```gherkin
@Regression @ComplexScenario
Scenario: Complete user registration and login flow
  Given User is on registration page
  When User fills registration form with:
    | Field    | Value           |
    | Email    | newuser@test.com|
    | Password | SecurePass123   |
    | Name     | John Doe        |
  And User submits registration form
  Then Registration success message is displayed
  
  Given User is on login page
  When User logs in with email "newuser@test.com" and password "SecurePass123"
  Then User profile page is displayed
```

## Step Definitions

### Basic Step Definition Pattern

```java
package org.example.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.example.pages.PageName;
import org.example.utilities.DriverManager;
import org.testng.Assert;

public class StepDefinitionClass {
    
    private PageName pageName;
    
    @Given("User is on page")
    public void userIsOnPage() {
        DriverManager.getDriver().navigate().to(ConfigReader.getApplicationUrl());
    }
    
    @When("User performs action")
    public void userPerformsAction() {
        pageName = new PageName(DriverManager.getDriver());
        pageName.performAction();
    }
    
    @Then("Expected result should occur")
    public void verifyResult() {
        Assert.assertTrue(pageName.isResultVisible(), "Result not found");
    }
}
```

### Common Step Definitions

**Given Steps (Setup):**
```java
@Given("User is on XPath practice page")
public void userIsOnXPathPracticePage() {
    driver = DriverManager.getDriver();
    driver.navigate().to(ConfigReader.getApplicationUrl());
    xpathPage = new XPathPracticePage(driver);
}

@Given("User has valid credentials")
public void userHasValidCredentials() {
    email = "test@example.com";
    password = "TestPassword123";
}
```

**When Steps (Action):**
```java
@When("User enters email {string}")
public void userEntersEmail(String email) {
    xpathPage.enterEmail(email);
}

@When("User performs login with email {string} and password {string}")
public void userPerformsLogin(String email, String password) {
    xpathPage.signIn(email, password);
}

@When("User waits for {int} seconds")
public void userWaits(int seconds) {
    try {
        Thread.sleep(seconds * 1000);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
}
```

**Then Steps (Assertion):**
```java
@Then("Success message should be displayed")
public void verifySuccessMessage() {
    Assert.assertTrue(xpathPage.isSuccessMessageDisplayed(), 
        "Success message not displayed");
}

@Then("Error message contains {string}")
public void verifyErrorMessage(String expectedText) {
    String actualMessage = xpathPage.getErrorMessage();
    Assert.assertTrue(actualMessage.contains(expectedText),
        "Expected text not found in error message");
}
```

## Page Object Model

### Page Class Structure

```java
package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    
    // Locators
    private static final By EMAIL_INPUT = By.xpath("//input[@id='email']");
    private static final By PASSWORD_INPUT = By.xpath("//input[@id='password']");
    private static final By LOGIN_BUTTON = By.xpath("//button[@id='login-btn']");
    private static final By ERROR_MESSAGE = By.xpath("//div[@class='error']");
    
    // Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    // Methods
    public void enterEmail(String email) {
        enterText(EMAIL_INPUT, email);
    }
    
    public void enterPassword(String password) {
        enterText(PASSWORD_INPUT, password);
    }
    
    public void clickLoginButton() {
        click(LOGIN_BUTTON);
    }
    
    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(ERROR_MESSAGE);
    }
    
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }
}
```

### Locator Best Practices

```java
// Good - Using specific attributes
private static final By EMAIL_INPUT = By.id("email");
private static final By LOGIN_BTN = By.xpath("//button[@id='login']");

// Good - Using data attributes
private static final By SUBMIT_BTN = By.xpath("//*[@data-qa='submit']");

// Avoid - Using generic selectors
// private static final By INPUT = By.xpath("//input");

// Avoid - Using absolute paths
// private static final By EMAIL = By.xpath("/html/body/div/form/input[1]");
```

## Test Data Management

### Using TestData Class

```java
public class TestData {
    public static final String VALID_EMAIL = "test@example.com";
    public static final String VALID_PASSWORD = "TestPass123";
    public static final String INVALID_EMAIL = "invalid@test.com";
    
    private static final Map<String, String> testDataMap = new HashMap<>();
    
    static {
        testDataMap.put("firstName", "John");
        testDataMap.put("lastName", "Doe");
    }
}

// Usage in steps
@When("User enters valid email")
public void userEntersValidEmail() {
    xpathPage.enterEmail(TestData.VALID_EMAIL);
}
```

### Parameterized Test Data

```gherkin
Scenario Outline: Login with multiple users
  When User enters email "<email>"
  And User enters password "<password>"
  Then User should see "<message>"
  
  Examples:
    | email              | password     | message |
    | user1@example.com  | Password123  | Success |
    | user2@example.com  | Password456  | Success |
    | invalid@test.com   | WrongPass    | Error   |
```

## Assertions

### Common Assertions

```java
// Element assertions
Assert.assertTrue(element.isDisplayed(), "Element not visible");
Assert.assertFalse(element.isEnabled(), "Element should be disabled");

// Text assertions
Assert.assertEquals(actualText, expectedText, "Text mismatch");
Assert.assertTrue(actualText.contains(expectedText), "Text not found");

// State assertions
Assert.assertTrue(isLoginSuccessful(), "Login failed");
Assert.assertFalse(isErrorMessageVisible(), "Error displayed unexpectedly");

// Collection assertions
Assert.assertEquals(actualList.size(), expectedSize, "List size mismatch");
Assert.assertTrue(actualList.containsAll(expectedList), "Missing items");
```

### Custom Assertions

```java
public class CustomAssertions {
    
    public static void assertElementVisible(WebElement element, String message) {
        Assert.assertTrue(element.isDisplayed(), message);
    }
    
    public static void assertElementPresent(WebDriver driver, By locator) {
        Assert.assertTrue(driver.findElements(locator).size() > 0, 
            "Element not found: " + locator);
    }
    
    public static void assertTextContains(String actual, String expected) {
        Assert.assertTrue(actual.contains(expected),
            "Expected: " + expected + " not found in: " + actual);
    }
}

// Usage
CustomAssertions.assertElementVisible(element, "Button should be visible");
```

## Hooks and Setup/Teardown

### Before and After Hooks

```java
@Before
public void beforeScenario(Scenario scenario) {
    logger.info("Starting: " + scenario.getName());
    DriverManager.initializeDriver("chrome");
}

@After
public void afterScenario(Scenario scenario) {
    if (scenario.isFailed()) {
        logger.error("Failed: " + scenario.getName());
        ScreenshotUtils.takeScreenshotOnFailure(DriverManager.getDriver(), scenario.getName());
    }
    DriverManager.quitDriver();
}
```

## Best Practices

### 1. Test Naming
```gherkin
# Good - Clear and descriptive
Scenario: User can login with valid credentials

# Bad - Vague
Scenario: Test login

# Good - Specific outcome
Scenario: User sees error message with invalid email format

# Bad - Implementation details
Scenario: Click button and check alert
```

### 2. Test Independence
```gherkin
# Good - Self-contained
Scenario: New user can complete registration
  Given User is on registration page
  When User fills form and submits
  Then Account is created

# Bad - Depends on other tests
Scenario: User can login
  # Assumes registration scenario ran first
```

### 3. Single Responsibility
```gherkin
# Good - One thing to test
Scenario: Email validation error shows for invalid format
  When User enters invalid email "notanemail"
  Then Email error message is displayed

# Bad - Testing multiple things
Scenario: Form validation
  When User enters invalid data
  And User leaves fields empty
  And User submits form
  Then Multiple error messages appear
```

### 4. Avoid Technical Details
```gherkin
# Good - Business language
Scenario: User can purchase items

# Bad - Technical details
Scenario: Click add to cart button and verify POST request response

# Good - User perspective
Scenario: User receives confirmation email after checkout

# Bad - Database queries
Scenario: Order is inserted into database with status=completed
```

## Running Tests

### From Command Line
```bash
# Run all tests
mvn clean test

# Run specific scenario
mvn clean test -Dcucumber.filter.tags="@Smoke"

# Run specific feature
mvn test -Dcucumber.options="src/test/resources/features/Login.feature"

# Run with multiple tags
mvn test -Dcucumber.filter.tags="@Smoke and @Forms"
```

### From IntelliJ
1. Right-click feature file → "Run Feature"
2. Right-click scenario → "Run Scenario"
3. Right-click TestRunner → "Run TestRunner"

## Troubleshooting

### Step Not Found
```
UndefinedStepException: Step "User enters email" is undefined
```

**Solution:**
- Check step definition exists
- Verify glue path in runner
- Match step text exactly
- Check imports in step file

### Element Not Found
```
NoSuchElementException: no such element
```

**Solution:**
- Verify XPath is correct
- Add explicit waits
- Check element is not in iframe
- Ensure page is fully loaded

### Flaky Tests
**Solutions:**
- Use explicit waits instead of Thread.sleep()
- Add retry logic
- Increase timeout values
- Check for JavaScript execution delays

## Example Complete Test

```gherkin
@Smoke @LoginTest
Feature: User Login Functionality
  As a user
  I want to login with my credentials
  So that I can access my account

  Background:
    Given User is on login page

  Scenario: Successful login with valid credentials
    When User enters email "test@example.com"
    And User enters password "ValidPass123"
    And User clicks login button
    Then Dashboard page is displayed
    And Welcome message contains user name

  Scenario: Login fails with invalid password
    When User enters email "test@example.com"
    And User enters password "InvalidPass"
    And User clicks login button
    Then Error message is displayed
    And Error message says "Invalid credentials"
```

## References
- [Cucumber Best Practices](https://cucumber.io/docs/gherkin/best-practices/)
- [TestNG Assertions](https://testng.org/doc/documentation-main.html#assertions)
- [Selenium Best Practices](https://www.selenium.dev/documentation/test_practices/)

