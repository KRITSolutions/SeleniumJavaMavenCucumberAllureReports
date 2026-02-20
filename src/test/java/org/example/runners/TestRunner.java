package org.example.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * TestRunner class for executing Cucumber feature files with TestNG
 * Configures Cucumber execution with plugins, feature files, and step definitions
 */
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"org.example.stepdefinitions"},
    plugin = {
        "pretty",
        "html:target/cucumber-reports/index.html",
        "json:target/cucumber-reports/cucumber.json",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
    },
    monochrome = true,
    dryRun = false,
    tags = "@Smoke or @Regression"
)
public class TestRunner extends AbstractTestNGCucumberTests {

    /**
     * Provide scenarios for parallel execution
     * @return Scenario scenarios
     */
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}

