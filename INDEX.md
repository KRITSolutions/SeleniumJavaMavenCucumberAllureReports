# 📚 Project Index & Navigation Guide

## 🎯 Quick Navigation

### Start Here
1. **New to the project?** → Read [README.md](README.md)
2. **Setting up for first time?** → Follow [SETUP.md](SETUP.md)
3. **Want quick commands?** → Check [QUICK_REFERENCE.md](QUICK_REFERENCE.md)
4. **Need to contribute?** → See [CONTRIBUTING.md](CONTRIBUTING.md)

## 📖 Documentation Structure

### 📋 Main Documentation Files

| File | Purpose | Audience |
|------|---------|----------|
| [README.md](README.md) | Project overview, features, installation | Everyone |
| [SETUP.md](SETUP.md) | Step-by-step setup instructions | New users, DevOps |
| [PROJECT_SUMMARY.md](PROJECT_SUMMARY.md) | Complete project details | Project managers |
| [QUICK_REFERENCE.md](QUICK_REFERENCE.md) | Common commands and patterns | Developers |
| [CONTRIBUTING.md](CONTRIBUTING.md) | Code standards, contribution process | Contributors |
| [CHECKLIST.md](CHECKLIST.md) | Implementation status, progress | Team leads |

### 📚 Detailed Guides (in `/docs` folder)

| File | Topic | Use When |
|------|-------|----------|
| [docs/XPATH_GUIDE.md](docs/XPATH_GUIDE.md) | XPath selectors & best practices | Writing locators |
| [docs/WRITING_TESTS.md](docs/WRITING_TESTS.md) | Test scenarios & patterns | Creating test cases |
| [docs/CICD_GUIDE.md](docs/CICD_GUIDE.md) | GitHub Actions & CI/CD | Setting up automation |

## 🗂️ Project Structure

### Root Directory Files
```
SeleniumJavaMavenCucumberAllureReports/
├── pom.xml                    # Maven configuration
├── testng.xml                 # TestNG configuration
├── .gitignore                 # Git ignore rules
├── README.md                  # Main documentation
├── SETUP.md                   # Setup instructions
├── CONTRIBUTING.md            # Contribution guidelines
├── PROJECT_SUMMARY.md         # Project overview
├── QUICK_REFERENCE.md         # Quick command reference
├── CHECKLIST.md               # Implementation checklist
└── .github/
    └── workflows/
        └── test-automation.yml # GitHub Actions CI/CD
```

### Source Code Structure
```
src/test/
├── java/org/example/
│   ├── utilities/             # Helper classes (7 files)
│   │   ├── DriverManager.java
│   │   ├── WaitUtils.java
│   │   ├── ScreenshotUtils.java
│   │   ├── ConfigReader.java
│   │   ├── ActionUtils.java
│   │   ├── ElementUtils.java
│   │   └── TestData.java
│   ├── pages/                 # Page Object Model (2 files)
│   │   ├── BasePage.java
│   │   └── XPathPracticePage.java
│   ├── stepdefinitions/       # Cucumber steps (2 files)
│   │   ├── Hooks.java
│   │   └── XPathPracticeSteps.java
│   └── runners/               # Test execution (1 file)
│       └── TestRunner.java
└── resources/
    ├── features/              # Test scenarios (2 files)
    │   ├── XPathPractice.feature
    │   └── AdvancedXPath.feature
    ├── config.properties      # Configuration
    └── logback.xml           # Logging configuration
```

### Documentation Structure
```
docs/
├── XPATH_GUIDE.md            # XPath selector patterns
├── WRITING_TESTS.md          # Test writing guidelines
└── CICD_GUIDE.md            # CI/CD configuration guide
```

## 📝 File-by-File Overview

### Configuration Files

#### `pom.xml`
- **Purpose:** Maven build configuration
- **Contains:** Dependencies, plugins, build settings
- **Edit When:** Adding new dependencies or Maven plugins
- **Key Sections:** Properties, dependencies, build plugins

#### `config.properties`
- **Purpose:** Application configuration
- **Contains:** URL, browser, wait timeouts, logging levels
- **Edit When:** Changing target URL or browser type
- **Loaded By:** ConfigReader utility class

#### `logback.xml`
- **Purpose:** Logging configuration
- **Contains:** Log appenders, patterns, package levels
- **Edit When:** Changing log format or adding log files

#### `testng.xml`
- **Purpose:** TestNG test suite configuration
- **Contains:** Test suites, parallel execution settings
- **Edit When:** Changing test execution strategy

### Utility Classes (in `src/test/java/org/example/utilities/`)

#### `DriverManager.java`
- **Purpose:** WebDriver lifecycle management
- **Key Methods:** initializeDriver(), getDriver(), quitDriver()
- **Used By:** Hooks, all test steps
- **Important:** Thread-local WebDriver management

#### `WaitUtils.java`
- **Purpose:** Explicit wait handling
- **Key Methods:** waitForElementToBeVisible(), waitForElementToBeClickable(), etc.
- **Used By:** BasePage methods
- **Important:** Prevents flaky tests

#### `ScreenshotUtils.java`
- **Purpose:** Screenshot capture and Allure integration
- **Key Methods:** captureScreenshot(), takeScreenshotOnFailure()
- **Used By:** Hooks on test failure
- **Important:** Automatic failure screenshots

#### `ConfigReader.java`
- **Purpose:** Configuration property reading
- **Key Methods:** getProperty(), getApplicationUrl(), getBrowser()
- **Used By:** DriverManager, test setup
- **Important:** Centralized configuration access

#### `ActionUtils.java`
- **Purpose:** Advanced user actions
- **Key Methods:** scrollToElement(), executeJavaScript(), hover(), etc.
- **Used By:** Complex test scenarios
- **Optional:** Advanced interactions

#### `ElementUtils.java`
- **Purpose:** Element manipulation utilities
- **Key Methods:** countElements(), getDropdownOptions(), selectDropdownByVisibleText()
- **Used By:** Form interaction tests
- **Optional:** Dropdown and collection handling

#### `TestData.java`
- **Purpose:** Centralized test data
- **Contains:** Valid/invalid credentials, test data maps
- **Used By:** Step definitions
- **Update When:** Adding new test data

### Page Object Classes (in `src/test/java/org/example/pages/`)

#### `BasePage.java`
- **Purpose:** Common page object functionality
- **Key Methods:** click(), enterText(), getText(), getAttribute()
- **Extends:** All page objects extend this
- **Important:** DRY principle implementation

#### `XPathPracticePage.java`
- **Purpose:** XPath practice page interactions
- **Key Methods:** enterEmail(), enterPassword(), signIn(), completeLoginFlow()
- **Locators:** XPath expressions for all elements
- **Extends:** BasePage

### Step Definition Classes (in `src/test/java/org/example/stepdefinitions/`)

#### `Hooks.java`
- **Purpose:** Cucumber lifecycle management
- **Methods:** beforeScenario() [@Before], afterScenario() [@After]
- **Responsibilities:** Driver setup, cleanup, screenshot on failure
- **Important:** Runs before and after each scenario

#### `XPathPracticeSteps.java`
- **Purpose:** Cucumber step implementations
- **Annotations:** @Given, @When, @Then
- **Methods:** One for each Gherkin step
- **Uses:** Page objects and assertions

### Runner Classes (in `src/test/java/org/example/runners/`)

#### `TestRunner.java`
- **Purpose:** Cucumber test execution
- **Annotation:** @CucumberOptions
- **Extends:** AbstractTestNGCucumberTests
- **Configures:** Features, glue paths, plugins, parallel execution

### Feature Files (in `src/test/resources/features/`)

#### `XPathPractice.feature`
- **Scenarios:** 8+ practical test cases
- **Tags:** @Smoke, @Regression, @PositiveTest, @NegativeTest
- **Topics:** Login, forms, elements, messages

#### `AdvancedXPath.feature`
- **Scenarios:** 8+ advanced XPath tests
- **Tags:** @XPathByAttribute, @XPathByText, @XPathParentChild, etc.
- **Topics:** Advanced selectors, XPath functions, axes

## 🚀 Common Tasks & Where to Find Help

### I want to...

#### Run Tests
```bash
mvn clean test
```
**See:** [QUICK_REFERENCE.md](QUICK_REFERENCE.md) → Maven Commands

#### Write a New Test
**Read:** [docs/WRITING_TESTS.md](docs/WRITING_TESTS.md)
**Follow:** Feature file template and step definition examples

#### Find XPath for Element
**Read:** [docs/XPATH_GUIDE.md](docs/XPATH_GUIDE.md)
**Try:** Browser DevTools console with XPath tester

#### Setup Project First Time
**Follow:** [SETUP.md](SETUP.md)
**Steps:** Java → Maven → Clone → Install → Run

#### Troubleshoot Failing Test
**Check:** [QUICK_REFERENCE.md](QUICK_REFERENCE.md) → Troubleshooting
**Steps:** Check logs → Verify XPath → Add waits

#### Configure CI/CD
**Read:** [docs/CICD_GUIDE.md](docs/CICD_GUIDE.md)
**File:** `.github/workflows/test-automation.yml`

#### Contribute Code
**Read:** [CONTRIBUTING.md](CONTRIBUTING.md)
**Follow:** Fork → Branch → Commit → Push → PR

#### Understand Architecture
**Read:** [PROJECT_SUMMARY.md](PROJECT_SUMMARY.md)
**See:** Architecture diagrams and design patterns

## 📊 Quick Stats

| Metric | Count |
|--------|-------|
| Java Files | 14 |
| Feature Files | 2 |
| Test Scenarios | 16+ |
| Utility Classes | 7 |
| Page Objects | 2 |
| Documentation Files | 10 |
| Configuration Files | 3 |
| Total Lines of Code | 3000+ |

## 🎓 Learning Path

### Beginner
1. Read [README.md](README.md)
2. Follow [SETUP.md](SETUP.md)
3. Check [QUICK_REFERENCE.md](QUICK_REFERENCE.md)
4. Run example tests

### Intermediate
1. Read [docs/XPATH_GUIDE.md](docs/XPATH_GUIDE.md)
2. Read [docs/WRITING_TESTS.md](docs/WRITING_TESTS.md)
3. Modify existing test scenarios
4. Create new feature files

### Advanced
1. Read [docs/CICD_GUIDE.md](docs/CICD_GUIDE.md)
2. Review [CONTRIBUTING.md](CONTRIBUTING.md)
3. Extend utility classes
4. Implement custom solutions

## ✅ Verification Checklist

After setup, verify:

- [ ] Maven installed: `mvn -version`
- [ ] Java installed: `java -version`
- [ ] Project builds: `mvn clean install`
- [ ] Tests run: `mvn clean test`
- [ ] Reports generate: `mvn allure:report`
- [ ] IntelliJ recognizes project
- [ ] Git initialized: `git status`

## 🔗 External Links

### Official Documentation
- [Selenium WebDriver](https://www.selenium.dev/documentation/)
- [Cucumber](https://cucumber.io/docs/cucumber/)
- [TestNG](https://testng.org/doc/)
- [Allure Reports](https://docs.qameta.io/allure/)
- [Maven](https://maven.apache.org/)

### Tools & Resources
- [GitHub Actions](https://docs.github.com/en/actions)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/)
- [SelectorHub XPath Practice](https://selectorshub.com/xpath-practice-page/)

## 📞 Getting Help

1. **Check Documentation** - Most questions answered in `/docs`
2. **Review Examples** - Look at existing tests and utilities
3. **Check Logs** - Review `target/logs/` for error details
4. **Read QUICK_REFERENCE** - Quick solutions to common issues
5. **Open Issue** - If still stuck, document and share

## 🎉 What's Next?

1. ✅ Clone the repository
2. ✅ Follow SETUP.md for installation
3. ✅ Run `mvn clean test` to execute tests
4. ✅ View Allure reports: `mvn allure:serve`
5. ✅ Explore codebase and documentation
6. ✅ Write your first test scenario

---

**Last Updated:** February 2026
**Status:** Complete and Production Ready ✅
**Version:** 1.0.0

