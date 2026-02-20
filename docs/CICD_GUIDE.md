# CI/CD Integration Guide

## GitHub Actions Overview

The project includes GitHub Actions workflow for continuous integration and continuous deployment (CI/CD).

## Workflow Configuration

### File Location
`.github/workflows/test-automation.yml`

### Workflow Triggers

The workflow runs on:
1. **Push** - To main or develop branches
2. **Pull Request** - To main or develop branches
3. **Scheduled** - Daily at 8 AM UTC

### Configuration Details

```yaml
name: Selenium Test Automation - XPath Practice

on:
  push:
    branches: [ main, develop ]
  pull_request:
    branches: [ main, develop ]
  schedule:
    - cron: '0 8 * * *'
```

## Workflow Steps

### 1. Checkout Code
```yaml
- name: Checkout code
  uses: actions/checkout@v3
```
Clones the repository code into the runner environment.

### 2. Setup Java
```yaml
- name: Set up JDK 11
  uses: actions/setup-java@v3
  with:
    java-version: 11
    distribution: 'temurin'
    cache: maven
```
Installs Java 11 and caches Maven dependencies for faster builds.

### 3. Install Dependencies
```yaml
- name: Install Dependencies
  run: mvn clean install -DskipTests=true
```
Downloads and installs all project dependencies.

### 4. Run Tests
```yaml
- name: Run Cucumber Tests
  run: mvn test -Dbrowser=${{ matrix.browser }} -Dgroups="Smoke,Regression"
```
Executes Cucumber tests with specified browser and test groups.

### 5. Generate Reports
```yaml
- name: Generate Allure Report
  if: always()
  run: mvn allure:report
```
Creates Allure test reports after test execution.

### 6. Upload Artifacts
```yaml
- name: Upload Allure Report
  uses: actions/upload-artifact@v3
  with:
    name: allure-report-${{ matrix.browser }}
    path: target/site/allure-maven-plugin/
```
Uploads test reports and logs as artifacts.

## Matrix Strategy

The workflow tests multiple browsers in parallel:

```yaml
strategy:
  matrix:
    java-version: [11]
    browser: [chrome, firefox]
```

This creates separate jobs for:
- Chrome tests
- Firefox tests

Both run in parallel, reducing total CI/CD time.

## Modifying Workflow

### Change Trigger Branches

```yaml
on:
  push:
    branches: [ main, develop, staging ]  # Add staging
```

### Add Email Notifications

```yaml
- name: Send Email on Failure
  if: failure()
  uses: dawidd6/action-send-mail@v3
  with:
    server_address: smtp.gmail.com
    server_port: 465
    username: ${{ secrets.EMAIL_USERNAME }}
    password: ${{ secrets.EMAIL_PASSWORD }}
    subject: Test Build Failed
    body: Tests failed. Check artifacts for details.
    to: team@example.com
```

### Add Slack Notifications

```yaml
- name: Notify Slack
  if: always()
  uses: 8398a7/action-slack@v3
  with:
    status: ${{ job.status }}
    text: 'Test run completed'
    webhook_url: ${{ secrets.SLACK_WEBHOOK }}
```

### Change Test Tags

```yaml
- name: Run Cucumber Tests
  run: mvn test -Dbrowser=${{ matrix.browser }} -Dtags="@Critical"
```

## GitHub Secrets

Store sensitive data in GitHub Secrets:

1. Go to Repository Settings
2. Select Secrets and Variables → Actions
3. Click New Repository Secret

### Common Secrets

```
EMAIL_USERNAME = your-email@gmail.com
EMAIL_PASSWORD = your-app-password
SLACK_WEBHOOK = https://hooks.slack.com/services/...
DATABASE_URL = jdbc:mysql://...
API_KEY = your-api-key
```

### Using Secrets in Workflow

```yaml
- name: Run Tests
  env:
    API_KEY: ${{ secrets.API_KEY }}
    DB_URL: ${{ secrets.DATABASE_URL }}
  run: mvn test
```

## Workflow Status

### View Status
1. Go to Actions tab
2. Click on workflow run
3. View job status and logs

### Status Badges

Add to README.md:

```markdown
[![Test Automation](https://github.com/your-repo/SeleniumJavaMavenCucumberAllureReports/workflows/Selenium%20Test%20Automation/badge.svg)](https://github.com/your-repo/SeleniumJavaMavenCucumberAllureReports/actions)
```

## Troubleshooting

### Common Issues

**Chrome not found:**
```yaml
- name: Install Chrome
  run: sudo apt-get install -y chromium-browser
```

**Firefox not found:**
```yaml
- name: Install Firefox
  run: sudo apt-get install -y firefox
```

**Maven cache issues:**
```yaml
- name: Clear Maven Cache
  run: rm -rf ~/.m2/repository
```

**Out of disk space:**
```yaml
- name: Cleanup
  run: |
    sudo rm -rf /usr/share/dotnet
    sudo rm -rf /usr/local/lib/android
```

## Performance Optimization

### Parallel Execution

```yaml
strategy:
  matrix:
    java-version: [11]
    browser: [chrome, firefox, edge]
    testType: [smoke, regression, sanity]
```

### Caching Dependencies

```yaml
- uses: actions/setup-java@v3
  with:
    java-version: 11
    cache: 'maven'
```

### Fail Fast

```yaml
strategy:
  fail-fast: true  # Stop all jobs if one fails
  matrix:
    browser: [chrome, firefox]
```

## Advanced Configuration

### Environment Variables

```yaml
env:
  BROWSER: chrome
  APP_URL: https://selectorshub.com/xpath-practice-page/
  LOG_LEVEL: INFO
```

### Conditional Steps

```yaml
- name: Generate Report
  if: always()  # Run even if tests fail
  run: mvn allure:report

- name: Notify on Failure
  if: failure()  # Run only if previous step failed
  run: echo "Tests failed!"

- name: Archive Artifacts
  if: success()  # Run only if tests passed
  run: zip -r test-results.zip target/
```

### Job Dependencies

```yaml
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - run: mvn clean install

  test:
    needs: build  # Wait for build job to complete
    runs-on: ubuntu-latest
    steps:
      - run: mvn test
```

## Monitoring and Analytics

### Actions Insights

1. Go to Actions tab
2. View workflow execution history
3. Analyze pass/fail rates
4. Identify trends

### Custom Metrics

Track in dashboard:
- Test execution time
- Pass/fail rates
- Browser-specific failures
- Performance metrics

## Best Practices

1. **Keep Workflows Simple** - One responsibility per workflow
2. **Use Matrix Strategy** - Test multiple configurations in parallel
3. **Cache Dependencies** - Reduce build time
4. **Store Artifacts** - Keep reports for analysis
5. **Use Secrets** - Never commit sensitive data
6. **Monitor Logs** - Check for failures regularly
7. **Optimize Runtime** - Parallel execution when possible
8. **Schedule Wisely** - Run during off-peak hours
9. **Document Changes** - Update README for workflow changes
10. **Test Locally First** - Verify tests pass before pushing

## Integration with Other Services

### Jira Integration

```yaml
- name: Create Jira Issue
  if: failure()
  uses: atlassian/gajira-create@v3
  with:
    project: TEST
    issuetype: Bug
    summary: Automation tests failed
    description: Check GitHub Actions for details
```

### Allure Report Hosting

Host Allure reports on:
- GitHub Pages
- AWS S3
- Azure Blob Storage
- Custom server

### Test Management Integration

Connect with:
- TestRail
- Testrail
- Zephyr
- xRay

## Example Workflow Enhancements

### Full Featured Workflow

```yaml
name: Complete CI/CD Pipeline

on:
  push:
    branches: [main, develop]
  pull_request:
    branches: [main, develop]
  schedule:
    - cron: '0 8 * * *'

jobs:
  test:
    runs-on: ubuntu-latest
    strategy:
      matrix:
        java-version: [11]
        browser: [chrome, firefox]
    
    steps:
    - uses: actions/checkout@v3
    
    - name: Set up JDK
      uses: actions/setup-java@v3
      with:
        java-version: ${{ matrix.java-version }}
        cache: maven
    
    - name: Run Tests
      run: mvn clean test -Dbrowser=${{ matrix.browser }}
    
    - name: Generate Reports
      if: always()
      run: mvn allure:report
    
    - name: Upload Artifacts
      if: always()
      uses: actions/upload-artifact@v3
      with:
        name: reports-${{ matrix.browser }}
        path: target/site/
    
    - name: Publish Results
      if: always()
      uses: dorny/test-reporter@v1
      with:
        name: Test Results - ${{ matrix.browser }}
        path: target/cucumber-reports/cucumber.json
        reporter: java-junit
    
    - name: Notify Slack
      if: failure()
      uses: 8398a7/action-slack@v3
      with:
        status: failure
        text: Tests failed for ${{ matrix.browser }}
        webhook_url: ${{ secrets.SLACK_WEBHOOK }}
```

## References

- [GitHub Actions Documentation](https://docs.github.com/en/actions)
- [Workflow Syntax](https://docs.github.com/en/actions/using-workflows/workflow-syntax-for-github-actions)
- [Matrix Strategy](https://docs.github.com/en/actions/using-jobs/using-a-matrix-for-your-jobs)
- [Allure Reports](https://docs.qameta.io/allure/)

