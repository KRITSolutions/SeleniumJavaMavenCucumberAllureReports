package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.example.utilities.WaitUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * BasePage class providing common functionality for all page objects
 * Contains common locators and methods used across pages
 */
public class BasePage {
    protected static final Logger logger = LoggerFactory.getLogger(BasePage.class);
    protected WebDriver driver;

    // Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Find element by locator with fallbacks for iframes and shadow DOM (simple xpath->css conversion)
     * @param locator Element locator
     * @return WebElement
     */
    protected WebElement findElement(By locator) {
        try {
            return driver.findElement(locator);
        } catch (Exception e) {
            logger.warn("Default findElement failed for {}: {}", locator, e.getMessage());
            // Try simple xpath -> css conversion and shadow DOM lookup
            String locatorStr = locator.toString();
            if (locatorStr.contains("By.xpath:")) {
                String xpath = locatorStr.substring(locatorStr.indexOf("By.xpath:") + 9).trim();
                String css = convertXpathToCss(xpath);
                if (css != null) {
                    try {
                        // Try finding via normal css first
                        return driver.findElement(By.cssSelector(css));
                    } catch (Exception ignore) {
                        // If normal find fails, attempt shadow DOM deep search
                        try {
                            WebElement el = org.example.utilities.ShadowDomUtils.findElementByCss(driver, css);
                            if (el != null) return el;
                        } catch (Exception ex) {
                            logger.warn("Shadow DOM lookup failed for css {}: {}", css, ex.getMessage());
                        }
                    }
                }
            }

            // As a last resort try searching frames (switch back to default first)
            try {
                driver.switchTo().defaultContent();
                List<WebElement> frames = driver.findElements(By.tagName("iframe"));
                for (int i = 0; i < frames.size(); i++) {
                    try {
                        driver.switchTo().frame(i);
                        WebElement el = driver.findElement(locator);
                        if (el != null) return el;
                    } catch (Exception ignored) {
                        // continue
                    } finally {
                        driver.switchTo().defaultContent();
                    }
                }
            } catch (Exception ex) {
                logger.warn("Frame fallback search failed: {}", ex.getMessage());
            }

            // Re-throw original exception for visibility
            throw e;
        }
    }

    /**
     * Click on element with retry and recovery strategies
     * @param locator Element locator
     */
    protected void click(By locator) {
        int maxAttempts = 3;
        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            try {
                // Wait for clickable and try regular click
                if (WaitUtils.waitForElementToBeClickable(driver, locator)) {
                    WebElement el = findElement(locator);
                    el.click();
                    logger.info("Clicked on element: {}", locator);
                    return;
                }
            } catch (org.openqa.selenium.ElementClickInterceptedException e) {
                logger.warn("Click intercepted (attempt {}/{}): {}", attempt + 1, maxAttempts, e.getMessage());

                // Recovery: scroll and try JS click
                try {
                    WebElement el = findElement(locator);
                    ((org.openqa.selenium.JavascriptExecutor) driver)
                        .executeScript("arguments[0].scrollIntoView({behavior:'auto',block:'center'});", el);
                    Thread.sleep(300);
                    ((org.openqa.selenium.JavascriptExecutor) driver)
                        .executeScript("arguments[0].click();", el);
                    logger.info("JS clicked on element after scroll: {}", locator);
                    return;
                } catch (Exception ex) {
                    logger.warn("JS click recovery failed: {}", ex.getMessage());
                }
            } catch (Exception e) {
                logger.warn("Click attempt {}/{} failed: {}", attempt + 1, maxAttempts, e.getMessage());
                if (attempt < maxAttempts - 1) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        // Final attempt: try frame switching
        logger.warn("Standard click failed, trying frame fallback for {}", locator);
        if (switchToFrameContaining(locator)) {
            try {
                WebElement el = findElement(locator);
                el.click();
                logger.info("Clicked element in iframe: {}", locator);
                driver.switchTo().defaultContent();
                return;
            } catch (Exception ex) {
                logger.warn("Click in frame failed: {}", ex.getMessage());
                try {
                    ((org.openqa.selenium.JavascriptExecutor) driver)
                        .executeScript("arguments[0].click();", findElement(locator));
                    driver.switchTo().defaultContent();
                    return;
                } catch (Exception ex2) {
                    logger.warn("JS click in frame failed: {}", ex2.getMessage());
                }
            }
        }

        throw new RuntimeException("Unable to click element after " + maxAttempts + " attempts: " + locator);
    }

    /**
     * Enter text into element
     * @param locator Element locator
     * @param text Text to enter
     */
    protected void enterText(By locator, String text) {
        // Try waiting in current context first
        if (WaitUtils.waitForElementToBeVisible(driver, locator)) {
            WebElement element = findElement(locator);
            element.clear();
            element.sendKeys(text);
            logger.info("Entered text '{}' into element: {}", text, locator);
            return;
        }

        // Fallback: try frames
        logger.warn("Element {} not visible in default context - trying frames", locator);
        if (switchToFrameContaining(locator)) {
            if (WaitUtils.waitForElementToBeVisible(driver, locator)) {
                WebElement element = findElement(locator);
                element.clear();
                element.sendKeys(text);
                logger.info("Entered text '{}' into element inside iframe: {}", text, locator);
                driver.switchTo().defaultContent();
                return;
            }
        }

        throw new RuntimeException("Unable to enter text into element: " + locator + " - not visible or not present");
    }

    /**
     * Get text from element
     * @param locator Element locator
     * @return Text content
     */
    protected String getText(By locator) {
        WaitUtils.waitForElementToBeVisible(driver, locator);
        String text = findElement(locator).getText();
        logger.info("Retrieved text from element {}: {}", locator, text);
        return text;
    }

    /**
     * Get attribute value
     * @param locator Element locator
     * @param attributeName Attribute name
     * @return Attribute value
     */
    protected String getAttribute(By locator, String attributeName) {
        WaitUtils.waitForElementToBeVisible(driver, locator);
        String attributeValue = findElement(locator).getAttribute(attributeName);
        logger.info("Retrieved attribute '{}' from element {}: {}", attributeName, locator, attributeValue);
        return attributeValue;
    }

    /**
     * Check if element is displayed
     * @param locator Element locator
     * @return true if displayed
     */
    protected boolean isElementDisplayed(By locator) {
        try {
            boolean isDisplayed = findElement(locator).isDisplayed();
            logger.info("Element {} is displayed: {}", locator, isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            logger.info("Element {} is not displayed", locator);
            return false;
        }
    }

    /**
     * Wait and click with retry
     * @param locator Element locator
     */
    protected void clickWithRetry(By locator) {
        int maxRetries = 3;
        for (int i = 0; i < maxRetries; i++) {
            try {
                click(locator);
                return;
            } catch (Exception e) {
                logger.warn("Attempt {} to click element {} failed: {}", i + 1, locator, e.getMessage());
                if (i == maxRetries - 1) {
                    throw e;
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    /**
     * Convert a small subset of XPath expressions to CSS selectors
     * Handles simple attribute equals and child option patterns used in the project
     * @param xpath XPath string
     * @return CSS selector or null if not convertible
     */
    private String convertXpathToCss(String xpath) {
        try {
            xpath = xpath.trim();
            // Simple conversion: just extract tag and attributes manually instead of regex
            // This avoids escaping issues

            // Pattern: //input[@id='userId'] -> input#userId
            if (xpath.contains("[@id='") && !xpath.contains("|")) {
                int tagStart = xpath.indexOf("//") + 2;
                int tagEnd = xpath.indexOf("[");
                if (tagEnd > tagStart) {
                    String tag = xpath.substring(tagStart, tagEnd);
                    int idStart = xpath.indexOf("[@id='") + 6;
                    int idEnd = xpath.indexOf("']", idStart);
                    if (idEnd > idStart) {
                        String id = xpath.substring(idStart, idEnd);
                        return tag + "#" + id;
                    }
                }
            }

            return null;
        } catch (Exception e) {
            logger.warn("Failed to convert xpath to css: {} -> {}", xpath, e.getMessage());
            return null;
        }
    }

    /**
     * Try to find an iframe that contains the given locator and switch to it
     * @param locator Element locator to search for
     * @return true if switched to a frame containing the element
     */
    private boolean switchToFrameContaining(By locator) {
        try {
            List<WebElement> frames = driver.findElements(By.tagName("iframe"));
            for (int i = 0; i < frames.size(); i++) {
                try {
                    driver.switchTo().defaultContent();
                    driver.switchTo().frame(i);
                    if (WaitUtils.waitForElementToBePresent(driver, locator)) {
                        logger.info("Found element {} inside iframe index {}", locator, i);
                        return true;
                    }
                } catch (Exception e) {
                    // continue searching other frames
                }
            }
            // no matching iframe found, ensure default content
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            logger.warn("Error while searching frames for element {}: {}", locator, e.getMessage());
        }
        return false;
    }
}
