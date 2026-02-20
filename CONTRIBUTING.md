# Contributing Guidelines

## Code of Conduct

- Be respectful and inclusive
- Follow best practices
- Help others in the community
- Report issues responsibly

## How to Contribute

### 1. Fork and Clone
```bash
git clone https://github.com/your-fork/SeleniumJavaMavenCucumberAllureReports.git
cd SeleniumJavaMavenCucumberAllureReports
git remote add upstream https://github.com/original-repo/SeleniumJavaMavenCucumberAllureReports.git
```

### 2. Create Feature Branch
```bash
git checkout -b feature/your-feature-name
```

### 3. Make Changes
- Follow code style guidelines
- Write clean, readable code
- Add appropriate comments
- Include unit tests

### 4. Commit Changes
```bash
git add .
git commit -m "feat: Add descriptive commit message"
```

**Commit Message Format:**
- `feat:` New feature
- `fix:` Bug fix
- `docs:` Documentation update
- `test:` Test additions
- `refactor:` Code refactoring
- `style:` Code formatting
- `chore:` Maintenance tasks

### 5. Push and Create Pull Request
```bash
git push origin feature/your-feature-name
```

Then create Pull Request on GitHub

## Code Style Guidelines

### Java Conventions
```java
// Class names: PascalCase
public class XPathPracticePage {
    // Constants: UPPER_SNAKE_CASE
    private static final int DEFAULT_TIMEOUT = 10;
    
    // Variables: camelCase
    private String testData;
    
    // Methods: camelCase
    public void clickElement() {
        // Implementation
    }
}
```

### Naming Conventions
- **Classes:** PascalCase (e.g., `DriverManager`, `XPathPracticePage`)
- **Methods:** camelCase (e.g., `initializeDriver`, `clickElement`)
- **Constants:** UPPER_SNAKE_CASE (e.g., `DEFAULT_TIMEOUT`)
- **Variables:** camelCase (e.g., `emailAddress`, `isValid`)
- **Packages:** lowercase (e.g., `org.example.pages`)

### Documentation
```java
/**
 * Brief description of the method
 * 
 * @param driver WebDriver instance
 * @param locator Element locator
 * @return Result description
 * @throws Exception When something goes wrong
 */
public void methodName(WebDriver driver, By locator) {
    // Implementation
}
```

## Test Writing Guidelines

### Feature Files
```gherkin
@Tag
Feature: Clear description of feature

  Scenario: Clear description of what is being tested
    Given precondition
    When action is performed
    Then expected result
```

### Step Definitions
```java
@Given("User is on XPath practice page")
public void userIsOnXPathPracticePage() {
    initializePageObject();
    // Implementation
}
```

### Page Objects
```java
public class PageName extends BasePage {
    // Locators
    private static final By ELEMENT_LOCATOR = By.xpath("//xpath");
    
    // Constructor
    public PageName(WebDriver driver) {
        super(driver);
    }
    
    // Methods
    public void interactWithElement() {
        click(ELEMENT_LOCATOR);
    }
}
```

## Testing Standards

### Coverage Requirements
- Aim for 80%+ code coverage
- Write meaningful tests
- Include positive and negative scenarios
- Test edge cases

### Test Data
- Use `TestData` class for test data
- Never hardcode sensitive data
- Use descriptive data values
- Parameterize tests where possible

## Pull Request Process

1. **Update Documentation**
   - Update README.md if needed
   - Document new features
   - Add examples

2. **Test Your Changes**
   ```bash
   mvn clean test -Dbrowser=chrome
   mvn clean test -Dbrowser=firefox
   ```

3. **Check Code Quality**
   ```bash
   # Run tests
   mvn clean verify
   
   # Generate reports
   mvn allure:report
   ```

4. **Create Descriptive PR**
   - Clear title
   - Detailed description
   - Reference related issues
   - Include screenshots/videos if relevant

5. **Address Feedback**
   - Review comments
   - Make requested changes
   - Re-test thoroughly

6. **Merge**
   - Ensure all checks pass
   - Squash commits if requested
   - Merge and delete branch

## Issues and Feature Requests

### Reporting Issues
1. Check existing issues first
2. Provide detailed description
3. Include:
   - Environment (OS, Java version, Browser)
   - Steps to reproduce
   - Expected behavior
   - Actual behavior
   - Screenshots/logs

### Suggesting Features
1. Search existing features
2. Provide clear use case
3. Include examples
4. Explain benefits

## Review Process

- Code review by maintainers
- Automated tests must pass
- Documentation must be complete
- Code must follow style guidelines
- No conflicts with main branch

## Development Setup

### Essential Tools
- IntelliJ IDEA (recommended)
- Maven 3.6+
- Java 11+
- Git

### Recommended Plugins (IntelliJ)
- Cucumber for Java
- Gherkin
- TestNG
- Allure TestReport
- Git Integration

## Useful Commands

```bash
# Build project
mvn clean build

# Run all tests
mvn clean test

# Run specific test class
mvn clean test -Dtest=TestRunnerClass

# Generate reports
mvn allure:report

# View report
mvn allure:serve

# Check code quality
mvn clean verify

# Skip tests
mvn clean install -DskipTests

# Run with debug
mvn clean test -X
```

## Questions or Need Help?

- Open an issue on GitHub
- Contact maintainers
- Check documentation
- Review existing issues

## Recognition

Contributors will be:
- Added to CONTRIBUTORS.md
- Mentioned in release notes
- Recognized for their contributions

Thank you for contributing! 🎉

