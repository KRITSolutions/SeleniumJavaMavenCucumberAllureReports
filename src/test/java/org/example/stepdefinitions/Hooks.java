package org.example.stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.example.utilities.DriverManager;
import org.example.utilities.ConfigReader;
import org.example.utilities.ScreenshotUtils;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.qameta.allure.Allure;

/**
 * Hooks class for Cucumber lifecycle management
 * Handles before and after scenario setup/teardown
 */
public class Hooks {
    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    /**
     * Before hook - Runs before each scenario
     * Initializes WebDriver and navigates to base URL
     * @param scenario Current scenario
     */
    @Before
    public void beforeScenario(Scenario scenario) {
        logger.info("========================================");
        logger.info("Starting scenario: {}", scenario.getName());
        logger.info("========================================");

        Allure.feature("XPath Practice Tests");
        Allure.story(scenario.getName());

        // Initialize WebDriver
        String browser = ConfigReader.getBrowser();
        DriverManager.initializeDriver(browser);

        // Navigate to application URL
        String url = ConfigReader.getApplicationUrl();
        DriverManager.getDriver().navigate().to(url);
        logger.info("Navigated to URL: {}", url);

        // Maximize window
        DriverManager.getDriver().manage().window().maximize();
    }

    /**
     * After hook - Runs after each scenario
     * Takes screenshot on failure and closes WebDriver
     * @param scenario Current scenario
     */
    @After
    public void afterScenario(Scenario scenario) {
        try {
            WebDriver driver = DriverManager.getDriver();

            if (scenario.isFailed()) {
                logger.error("Scenario FAILED: {}", scenario.getName());
                ScreenshotUtils.takeScreenshotOnFailure(driver, scenario.getName());
            } else {
                logger.info("Scenario PASSED: {}", scenario.getName());
                ScreenshotUtils.captureScreenshot(driver, scenario.getName() + "_SUCCESS");
            }
        } catch (Exception e) {
            logger.error("Error during afterScenario: {}", e.getMessage());
        } finally {
            // Close WebDriver
            DriverManager.quitDriver();
            logger.info("========================================");
            logger.info("Finished scenario: {}", scenario.getName());
            logger.info("========================================");
        }
    }
}

