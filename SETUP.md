# Getting Started Guide

## Quick Start

### 1. Prerequisites Installation

#### Windows

**Java Installation:**
- Download JDK 11 or higher from [Oracle](https://www.oracle.com/java/technologies/downloads/)
- Set JAVA_HOME environment variable
- Verify: `java -version`

**Maven Installation:**
- Download Maven from [Apache Maven](https://maven.apache.org/download.cgi)
- Extract and set M2_HOME environment variable
- Add `%M2_HOME%\bin` to PATH
- Verify: `mvn -version`

**Git Installation:**
- Download Git from [Git Official](https://git-scm.com/)
- Follow installation wizard
- Verify: `git --version`

#### macOS

```bash
# Install Java
brew install openjdk@11

# Install Maven
brew install maven

# Install Git
brew install git
```

#### Linux (Ubuntu/Debian)

```bash
# Update package manager
sudo apt-get update

# Install Java
sudo apt-get install openjdk-11-jdk

# Install Maven
sudo apt-get install maven

# Install Git
sudo apt-get install git
```

### 2. Clone and Setup Project

```bash
# Clone repository
git clone https://github.com/your-repo/SeleniumJavaMavenCucumberAllureReports.git
cd SeleniumJavaMavenCucumberAllureReports

# Install dependencies
mvn clean install

# Verify setup
mvn test -Dbrowser=chrome -Dtags="@Smoke"
```

### 3. IntelliJ IDEA Setup

1. **Open Project:**
   - File → Open → Select project folder
   - Select "Trust Project"

2. **Configure JDK:**
   - File → Project Structure → Project
   - Set JDK to 11
   - Click Apply

3. **Configure Maven:**
   - File → Settings → Build, Execution, Deployment → Maven
   - Set Maven home path
   - Click Apply

4. **Install Plugins:**
   - File → Settings → Plugins
   - Search and install:
     - Cucumber for Java
     - Gherkin
     - TestNG
   - Click Apply

5. **Run Tests:**
   - Right-click on TestRunner class
   - Select "Run TestRunner"

## Common Tasks

### Running Tests

**From IntelliJ:**
- Right-click on feature file → "Run Feature"
- Right-click on TestRunner → "Run TestRunner"

**From Command Line:**
```bash
# Run all tests
mvn clean test

# Run specific feature
mvn clean test -Dcucumber.filter.tags="@XPathPractice"

# Run with specific browser
mvn clean test -Dbrowser=firefox
```

### Viewing Reports

```bash
# Generate and view Allure report
mvn allure:report
mvn allure:serve

# View test logs
cat target/logs/test_logs.log
```

### Writing New Tests

1. **Create Feature File:**
   - Add `.feature` file in `src/test/resources/features/`
   - Write scenarios in Gherkin syntax

2. **Create Step Definitions:**
   - Add steps in `src/test/java/org/example/stepdefinitions/`
   - Use `@Given`, `@When`, `@Then` annotations

3. **Create Page Objects:**
   - Add page class in `src/test/java/org/example/pages/`
   - Extend BasePage
   - Define locators and methods

## IDE Configuration Files

### IntelliJ IDEA

1. **Code Style:**
   - File → Settings → Editor → Code Style
   - Set line length to 120
   - Enable automatic formatting on save

2. **Run Configurations:**
   - Run → Edit Configurations
   - Add "Cucumber java" configuration
   - Set feature paths and glue paths

3. **Debugger:**
   - Set breakpoints in test code
   - Run in Debug mode
   - Use debugging tools

## Browser Driver Setup

### Automatic (Recommended)
WebDriverManager handles automatic driver setup. No manual configuration needed.

### Manual Setup (Optional)
1. Download drivers from:
   - Chrome: [ChromeDriver](https://chromedriver.chromium.org/)
   - Firefox: [GeckoDriver](https://github.com/mozilla/geckodriver/releases)
   - Edge: [EdgeDriver](https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/)

2. Update DriverManager.java with driver path

## Next Steps

1. Read [README.md](README.md) for detailed documentation
2. Check [Writing Tests Guide](docs/WRITING_TESTS.md)
3. Review [XPath Selectors Guide](docs/XPATH_GUIDE.md)
4. Explore [CI/CD Integration](docs/CICD_GUIDE.md)

