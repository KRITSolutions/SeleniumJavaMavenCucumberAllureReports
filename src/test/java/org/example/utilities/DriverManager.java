package org.example.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DriverManager class for WebDriver initialization and management
 * Handles browser initialization and teardown
 */
public class DriverManager {
    private static final Logger logger = LoggerFactory.getLogger(DriverManager.class);
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * Initialize WebDriver based on browser type
     * @param browserType Browser type (chrome, firefox, edge)
     * @return WebDriver instance
     */
    public static WebDriver initializeDriver(String browserType) {
        logger.info("Initializing WebDriver for browser: {}", browserType);

        WebDriver webDriver;
        String browser = browserType.toLowerCase();

        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                webDriver = new EdgeDriver();
                break;
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
                break;
        }

        driver.set(webDriver);
        logger.info("WebDriver initialized successfully");
        return webDriver;
    }

    /**
     * Get the current WebDriver instance
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {
        return driver.get();
    }

    /**
     * Quit and close the WebDriver
     */
    public static void quitDriver() {
        if (driver.get() != null) {
            logger.info("Closing WebDriver");
            driver.get().quit();
            driver.remove();
        }
    }

    /**
     * Clear the ThreadLocal driver
     */
    public static void removeDriver() {
        driver.remove();
    }
}

