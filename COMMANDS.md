# 🚀 Execution Commands & Cheat Sheet

## Quick Start Commands

### First Time Setup
```bash
# Clone repository
git clone https://github.com/your-repo/SeleniumJavaMavenCucumberAllureReports.git
cd SeleniumJavaMavenCucumberAllureReports

# Install dependencies
mvn clean install

# Run tests
mvn clean test

# View reports
mvn allure:serve
```

## Maven Test Execution Commands

### Basic Test Runs

#### Run All Tests
```bash
mvn clean test
```

#### Run Tests with Detailed Output
```bash
mvn clean test -X
```

#### Skip Tests (Just Build)
```bash
mvn clean install -DskipTests
```

#### Run Single Test Class
```bash
mvn test -Dtest=TestRunnerClass
```

### Running with Tags (Cucumber Filtering)

#### Run Smoke Tests Only
```bash
mvn clean test -Dtags="@Smoke"
```

#### Run Regression Tests Only
```bash
mvn clean test -Dtags="@Regression"
```

#### Run with Multiple Tags
```bash
mvn test -Dtags="@Smoke and @Forms"
mvn test -Dtags="@Smoke or @ElementTest"
```

#### Run Specific Feature
```bash
mvn test -Dcucumber.filter.tags="@XPathPractice"
```

#### Run All Tags Except One
```bash
mvn test -Dtags="not @SkipMe"
```

### Browser-Specific Commands

#### Run with Chrome (Default)
```bash
mvn clean test -Dbrowser=chrome
```

#### Run with Firefox
```bash
mvn clean test -Dbrowser=firefox
```

#### Run with Edge
```bash
mvn clean test -Dbrowser=edge
```

#### Run with All Browsers
```bash
# Create separate runs for each browser
mvn clean test -Dbrowser=chrome
mvn clean test -Dbrowser=firefox
mvn clean test -Dbrowser=edge
```

### Parallel Execution

#### Run Tests in Parallel
```bash
mvn test -DthreadCount=3
```

#### Run with Maximum Parallelism
```bash
mvn test -DthreadCount=5
```

#### Run Feature Files in Parallel
```bash
mvn test -DthreadCount=2 -Dbrowser=chrome
```

### Allure Reporting Commands

#### Generate Allure Report
```bash
mvn allure:report
```

#### View Allure Report in Browser
```bash
mvn allure:serve
```

#### Clean Allure Data
```bash
mvn allure:clean
```

#### Generate and View in One Command
```bash
mvn clean test && mvn allure:report && mvn allure:serve
```

### Combination Commands

#### Smoke Tests with Chrome and Reporting
```bash
mvn clean test -Dbrowser=chrome -Dtags="@Smoke" && mvn allure:serve
```

#### Regression Tests with Firefox Parallel
```bash
mvn clean test -Dbrowser=firefox -Dtags="@Regression" -DthreadCount=3
```

#### Complete Test Run with Report
```bash
mvn clean test -Dtags="@Smoke or @Regression" -DthreadCount=3 && mvn allure:report && mvn allure:serve
```

#### Development Mode (Fast Run)
```bash
mvn clean test -Dbrowser=chrome -Dtags="@Smoke" -DskipSomeTests=true
```

## Clean Up Commands

### Clean Build Directory
```bash
mvn clean
```

### Remove All Test Results
```bash
rm -rf target/
```

### Remove Allure Results
```bash
rm -rf target/allure-results/
```

### Remove Test Logs
```bash
rm -rf target/logs/
```

### Complete Clean (All Generated Files)
```bash
mvn clean && rm -rf target/
```

## IntelliJ IDEA Shortcuts

### Running Tests
```
Ctrl + Shift + F10   - Run selected test/class/feature
Ctrl + Shift + F9    - Debug selected test
Alt + Shift + F10    - Run configuration
Shift + F10          - Run
Shift + F9           - Debug
```

### Code Editing
```
Ctrl + B             - Go to definition
Ctrl + H             - View class hierarchy
Ctrl + Alt + L       - Format code
Ctrl + /             - Toggle comment
Ctrl + Shift + /     - Toggle block comment
Ctrl + X             - Delete line
Ctrl + D             - Duplicate line
Alt + Up/Down        - Move line up/down
```

### Build & Run
```
Ctrl + F9            - Build project
Ctrl + Shift + A     - Find action
Ctrl + Alt + O       - Optimize imports
Shift + F6           - Refactor/Rename
```

## Feature-Specific Commands

### Test XPath Specific Features
```bash
# Test XPath practice page scenarios
mvn test -Dcucumber.filter.tags="@XPathPractice"

# Test advanced XPath
mvn test -Dcucumber.filter.tags="@AdvancedXPath"

# Test form interactions
mvn test -Dtags="@Forms"

# Test element visibility
mvn test -Dtags="@ElementTest"
```

### Test Data-Specific Scenarios
```bash
# Positive tests only
mvn test -Dtags="@PositiveTest"

# Negative tests only
mvn test -Dtags="@NegativeTest"

# Both positive and negative
mvn test -Dtags="@PositiveTest or @NegativeTest"
```

## Useful Shell Commands

### List Test Files
```bash
# Find all feature files
find . -name "*.feature"

# Find all Java test classes
find . -name "*Test.java" -o -name "*Tests.java"

# Find all step definitions
find . -path "*/stepdefinitions/*.java"
```

### View Test Results
```bash
# View test log
cat target/logs/test_logs.log

# Search for errors in logs
grep "ERROR" target/logs/test_logs.log

# View last 50 lines of log
tail -50 target/logs/test_logs.log

# Real-time log viewing
tail -f target/logs/test_logs.log
```

### Git Commands for Testing
```bash
# Create feature branch for new test
git checkout -b feature/new-test-scenario

# Stage test files
git add src/test/

# Commit test changes
git commit -m "test: add new test scenarios"

# Push to remote
git push origin feature/new-test-scenario

# Create pull request for code review
# Then open GitHub and create PR
```

## Advanced Commands

### Run with Custom JVM Options
```bash
mvn test -DargLine="-Xmx1024m -Xms512m"
```

### Run with Specific Maven Profile
```bash
mvn test -P smoke-tests
```

### Skip Maven Build Checks
```bash
mvn test -DskipTests
```

### Run in Headless Mode (CI/CD)
```bash
mvn test -Dheadless=true
```

### Generate Coverage Report
```bash
mvn clean test jacoco:report
```

### Integration with Slack/Email
```bash
# Requires additional configuration
mvn test -DslackWebhook="your-webhook-url"
```

## Docker Commands (If Using Docker)

### Build Docker Image
```bash
docker build -t selenium-tests .
```

### Run Tests in Docker
```bash
docker run --rm selenium-tests mvn clean test
```

### Run with Selenium Hub
```bash
docker-compose up
mvn test -DhubUrl=http://localhost:4444
docker-compose down
```

## CI/CD Pipeline Commands

### GitHub Actions (Local Testing)
```bash
# Install act to test workflows locally
brew install act

# Run workflow locally
act -l
act -j test
```

### Jenkins (If Using Jenkins)
```bash
# Trigger Jenkins job
curl -X POST http://jenkins-url/job/test-automation/build

# View console output
curl http://jenkins-url/job/test-automation/lastBuild/consoleText
```

## Troubleshooting Commands

### Verify Environment
```bash
# Check Java version
java -version

# Check Maven version
mvn -version

# Check Git version
git --version

# Verify Maven settings
mvn help:system
```

### Clear Maven Cache
```bash
# Remove local repository
rm -rf ~/.m2/repository

# Update Maven dependencies
mvn clean dependency:resolve
```

### Debug Maven Build
```bash
# Verbose output
mvn clean test -X

# Skip tests and just compile
mvn clean compile

# Check dependency tree
mvn dependency:tree
```

### Check TestNG Configuration
```bash
# Validate testng.xml
mvn help:describe -Dplugin=org.apache.maven.plugins:maven-surefire-plugin
```

## Useful Combinations

### Development Workflow
```bash
# 1. Code changes
# 2. Format code
mvn clean compile -DskipTests

# 3. Run affected tests
mvn test -Dbrowser=chrome -Dtags="@Smoke"

# 4. Check reports
mvn allure:serve
```

### Pre-Commit Checks
```bash
# Build and run quick tests
mvn clean compile && mvn test -Dtags="@Smoke" -DthreadCount=2
```

### Continuous Integration
```bash
# Full test suite with reporting
mvn clean test -DthreadCount=4 && mvn allure:report
```

### Release Preparation
```bash
# Run all tests across browsers
for browser in chrome firefox edge; do
  mvn clean test -Dbrowser=$browser
done
mvn allure:report
```

## One-Liners for Common Tasks

### Quick Test Run
```bash
mvn clean test
```

### Quick Report View
```bash
mvn allure:serve
```

### Quick Smoke Test
```bash
mvn clean test -Dtags="@Smoke" && mvn allure:report && mvn allure:serve
```

### Full Test Suite
```bash
mvn clean test -DthreadCount=3 && mvn allure:report && mvn allure:serve
```

### Parallel Regression Tests
```bash
mvn clean test -Dtags="@Regression" -DthreadCount=4 && mvn allure:report
```

### Browser Matrix Testing
```bash
mvn clean test -Dbrowser=chrome && mvn clean test -Dbrowser=firefox && mvn allure:report
```

## Performance Tips

### Faster Test Execution
```bash
# Use parallel execution
mvn test -DthreadCount=4

# Skip compilation if no code changes
mvn test -o

# Use Maven daemon (mvnd)
mvnd test
```

### Faster Builds
```bash
# Skip tests and documentation
mvn clean install -DskipTests -Dorg.slf4j.simpleLogger.defaultLogLevel=WARN

# Use offline mode (requires initial setup)
mvn test -o
```

## Reference Table

| Task | Command |
|------|---------|
| Run all tests | `mvn clean test` |
| Run smoke tests | `mvn test -Dtags="@Smoke"` |
| Run with Firefox | `mvn test -Dbrowser=firefox` |
| Run in parallel | `mvn test -DthreadCount=3` |
| Generate reports | `mvn allure:report` |
| View reports | `mvn allure:serve` |
| Format code | `mvn clean compile` |
| Build without tests | `mvn install -DskipTests` |
| Debug tests | `mvn test -X` |
| Clean all | `mvn clean` |

---

**Pro Tip:** Create shell script aliases for frequently used commands:

```bash
# Add to ~/.bashrc or ~/.zshrc
alias mvntest='mvn clean test'
alias mvnreport='mvn allure:report && mvn allure:serve'
alias mvnsmoke='mvn clean test -Dtags="@Smoke"'
alias mvnregression='mvn clean test -Dtags="@Regression"'
```

Then use:
```bash
mvntest              # Runs all tests
mvnreport           # Generates and views report
mvnsmoke            # Runs smoke tests
mvnregression       # Runs regression tests
```

---

**Last Updated:** February 2026
**Version:** 1.0.0

