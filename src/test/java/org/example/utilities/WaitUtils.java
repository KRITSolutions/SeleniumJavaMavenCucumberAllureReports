package org.example.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * WaitUtils class for handling explicit waits
 * Provides methods for waiting for elements to be visible, clickable, etc.
 */
public class WaitUtils {
    private static final Logger logger = LoggerFactory.getLogger(WaitUtils.class);
    // Increased default timeout to reduce transient failures
    private static final int DEFAULT_TIMEOUT = 20;

    /**
     * Wait for element to be visible
     * @param driver WebDriver instance
     * @param locator Element locator
     * @return true if element is visible within timeout
     */
    public static boolean waitForElementToBeVisible(WebDriver driver, By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            logger.info("Element {} is visible", locator);
            return true;
        } catch (Exception e) {
            logger.error("Element {} is not visible within {} seconds", locator, DEFAULT_TIMEOUT);
            return false;
        }
    }

    /**
     * Wait for element to be clickable
     * @param driver WebDriver instance
     * @param locator Element locator
     * @return true if element is clickable within timeout
     */
    public static boolean waitForElementToBeClickable(WebDriver driver, By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            logger.info("Element {} is clickable", locator);
            return true;
        } catch (Exception e) {
            logger.error("Element {} is not clickable within {} seconds", locator, DEFAULT_TIMEOUT);
            return false;
        }
    }

    /**
     * Wait for element to be present in DOM
     * @param driver WebDriver instance
     * @param locator Element locator
     * @return true if element is present within timeout
     */
    public static boolean waitForElementToBePresent(WebDriver driver, By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            logger.info("Element {} is present in DOM", locator);
            return true;
        } catch (Exception e) {
            logger.error("Element {} is not present in DOM within {} seconds", locator, DEFAULT_TIMEOUT);
            return false;
        }
    }

    /**
     * Wait for text to be present in element
     * @param driver WebDriver instance
     * @param locator Element locator
     * @param text Text to wait for
     * @return true if text is present within timeout
     */
    public static boolean waitForTextToBePresentInElement(WebDriver driver, By locator, String text) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
            wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
            logger.info("Text '{}' is present in element {}", text, locator);
            return true;
        } catch (Exception e) {
            logger.error("Text '{}' is not present in element {} within {} seconds", text, locator, DEFAULT_TIMEOUT);
            return false;
        }
    }
}
