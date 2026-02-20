# Selenium Java Maven Cucumber Allure Reports Framework

A comprehensive test automation framework using Selenium WebDriver, Java, Maven, Cucumber, TestNG, and Allure Reports for testing the SelectorHub XPath Practice Page.

## 📋 Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
- [Test Reports](#test-reports)
- [CI/CD Integration](#cicd-integration)
- [Contributing](#contributing)

## 🎯 Features

- **Selenium WebDriver 4.15.0** - Latest WebDriver for browser automation
- **Cucumber 7.14.0** - BDD framework for writing human-readable test scenarios
- **TestNG 7.8.1** - Testing framework with parallel execution support
- **Allure Reports 2.25.0** - Beautiful and comprehensive test reports
- **WebDriverManager 5.6.3** - Automatic WebDriver management
- **Page Object Model (POM)** - Maintainable and scalable test structure
- **Logback Logging** - Comprehensive logging configuration
- **GitHub Actions** - CI/CD pipeline for automated testing
- **Parallel Execution** - Multi-threaded test execution for faster results

## 📦 Prerequisites

- **Java 11+** - JDK 11 or higher
- **Maven 3.6+** - For dependency management and build
- **Git** - For version control
- **Chrome/Firefox/Edge Browser** - For test execution

## 📁 Project Structure

```
SeleniumJavaMavenCucumberAllureReports/
├── src/
│   ├── main/
│   │   └── java/org/example/
│   │       └── App.java
│   └── test/
│       ├── java/org/example/
│       │   ├── pages/              # Page Object Model classes
│       │   │   ├── BasePage.java
│       │   │   └── XPathPracticePage.java
│       │   ├── stepdefinitions/    # Cucumber step definitions
│       │   │   ├── Hooks.java
│       │   │   └── XPathPracticeSteps.java
│       │   ├── runners/            # Test runners
│       │   │   └── TestRunner.java
│       │   └── utilities/          # Utility classes
│       │       ├── DriverManager.java
│       │       ├── WaitUtils.java
│       │       ├── ScreenshotUtils.java
│       │       ├── ConfigReader.java
│       │       ├── ActionUtils.java
│       │       └── ElementUtils.java
│       └── resources/
│           ├── features/           # Cucumber feature files
│           │   ├── XPathPractice.feature
│           │   └── AdvancedXPath.feature
│           ├── config.properties    # Configuration properties
│           └── logback.xml         # Logging configuration
├── .github/
│   └── workflows/
│       └── test-automation.yml     # GitHub Actions workflow
├── pom.xml                         # Maven configuration
├── README.md                       # This file
└── .gitignore                      # Git ignore rules
```

## 🚀 Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-repo/SeleniumJavaMavenCucumberAllureReports.git
   cd SeleniumJavaMavenCucumberAllureReports
   ```

2. **Install dependencies:**
   ```bash
   mvn clean install
   ```

3. **Verify installation:**
   ```bash
   mvn --version
   java -version
   ```

## ⚙️ Configuration

### Update Configuration Properties

Edit `src/test/resources/config.properties`:

```properties
# Application URL
app.url=https://selectorshub.com/xpath-practice-page/

# Browser selection (chrome, firefox, edge)
browser=chrome

# Wait timeouts (in seconds)
implicit.wait=10
explicit.wait=10

# Logging level
log.level=INFO
```

### Browser Configuration

The framework supports:
- **Chrome** (default)
- **Firefox**
- **Edge**

## 🧪 Running Tests

### Run All Tests
```bash
mvn clean test
```

### Run Specific Browser
```bash
mvn clean test -Dbrowser=firefox
```

### Run Specific Tags
```bash
mvn clean test -Dtags="@Smoke"
mvn clean test -Dtags="@Regression"
```

### Run Specific Feature File
```bash
mvn clean test -Dcucumber.filter.tags="@XPathPractice"
```

### Run Tests in Parallel
```bash
mvn clean test -DthreadCount=3
```

### Run Tests with Detailed Logging
```bash
mvn clean test -X
```

## 📊 Test Reports

### Generate Allure Report

1. **After test execution:**
   ```bash
   mvn allure:report
   ```

2. **View the report:**
   ```bash
   mvn allure:serve
   ```

The report will open in your default browser at `http://localhost:4040`

### Report Contents
- Test execution summary
- Detailed test steps with screenshots
- Failed test analysis
- Test timeline
- Test trends

## 🔄 CI/CD Integration

### GitHub Actions Workflow

The framework includes a GitHub Actions workflow (`.github/workflows/test-automation.yml`) that:

- Runs tests on push to main/develop branches
- Executes tests on pull requests
- Runs daily scheduled tests
- Tests multiple browsers (Chrome, Firefox)
- Uploads test results and artifacts
- Generates Allure reports

### Workflow Configuration

Edit `.github/workflows/test-automation.yml` to:
- Change branch triggers
- Modify schedule
- Add environment variables
- Update artifact retention

## 📝 Writing Tests

### Feature File Example

```gherkin
@Smoke @XPathPractice
Feature: XPath Practice Page - Login
  
  Background:
    Given User is on XPath practice page
  
  @PositiveTest
  Scenario: User can successfully login
    When User performs login with email "test@example.com" and password "Test@123"
    Then Login should be successful
```

### Step Definition Example

```java
@When("User performs login with email {string} and password {string}")
public void userPerformsLogin(String email, String password) {
    xpathPracticePage.signIn(email, password);
}
```

## 🛠️ Utility Classes

### DriverManager
Handles WebDriver initialization and management with thread-local support.

### WaitUtils
Provides explicit wait methods for element visibility, clickability, and presence.

### ScreenshotUtils
Captures screenshots and attaches them to Allure reports.

### ConfigReader
Reads configuration from properties file.

### ActionUtils
Handles advanced user actions like scrolling, JavaScript execution, and mouse interactions.

### ElementUtils
Provides element-related operations like dropdown selection and element counting.

## 🔐 Best Practices

1. **Use Page Object Model** - Maintain locators in page classes
2. **Implement Waits** - Use explicit waits instead of Thread.sleep()
3. **Add Meaningful Logs** - Log important steps for debugging
4. **Use Descriptive Names** - Make test scenarios clear and understandable
5. **Handle Exceptions** - Implement proper error handling
6. **Tag Tests** - Use tags for test categorization and filtering
7. **Take Screenshots** - Capture screenshots on failures
8. **Use Constants** - Define magic strings as constants

## 🐛 Troubleshooting

### Common Issues

**WebDriver not found:**
- Ensure WebDriverManager is configured
- Check internet connection for driver download

**Cucumber step not found:**
- Verify step definition class is in correct package
- Check glue path in TestRunner

**Element not found:**
- Verify XPath selector is correct
- Use WaitUtils for dynamic elements
- Check page load time

**Report not generated:**
- Ensure allure-results directory exists
- Check Maven Allure plugin configuration
- Run: `mvn clean allure:report`

## 📚 References

- [Selenium Documentation](https://www.selenium.dev/documentation/)
- [Cucumber Documentation](https://cucumber.io/docs/cucumber/)
- [Allure Report Documentation](https://docs.qameta.io/allure/)
- [TestNG Documentation](https://testng.org/doc/)
- [Maven Documentation](https://maven.apache.org/)

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 📧 Contact

For questions or suggestions, please contact the development team.

---

**Last Updated:** February 2026
**Version:** 1.0.0

