package org.example.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ActionUtils class for handling advanced user actions
 * Provides methods for mouse actions, keyboard actions, and JavaScript execution
 */
public class ActionUtils {
    private static final Logger logger = LoggerFactory.getLogger(ActionUtils.class);

    /**
     * Scroll to element
     * @param driver WebDriver instance
     * @param element WebElement to scroll to
     */
    public static void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        logger.info("Scrolled to element");
    }

    /**
     * Scroll to element by locator
     * @param driver WebDriver instance
     * @param locator Element locator
     */
    public static void scrollToElement(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        scrollToElement(driver, element);
    }

    /**
     * Scroll page up
     * @param driver WebDriver instance
     * @param pixels Pixels to scroll up
     */
    public static void scrollPageUp(WebDriver driver, int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, -" + pixels + ");");
        logger.info("Scrolled page up by {} pixels", pixels);
    }

    /**
     * Scroll page down
     * @param driver WebDriver instance
     * @param pixels Pixels to scroll down
     */
    public static void scrollPageDown(WebDriver driver, int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, " + pixels + ");");
        logger.info("Scrolled page down by {} pixels", pixels);
    }

    /**
     * Execute JavaScript
     * @param driver WebDriver instance
     * @param script JavaScript code to execute
     * @return Result of script execution
     */
    public static Object executeJavaScript(WebDriver driver, String script) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Object result = js.executeScript(script);
        logger.info("Executed JavaScript: {}", script);
        return result;
    }

    /**
     * Hover over element
     * @param driver WebDriver instance
     * @param element WebElement to hover over
     */
    public static void hoverOverElement(WebDriver driver, WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
        logger.info("Hovered over element");
    }

    /**
     * Right click on element
     * @param driver WebDriver instance
     * @param element WebElement to right click
     */
    public static void rightClickOnElement(WebDriver driver, WebElement element) {
        Actions action = new Actions(driver);
        action.contextClick(element).perform();
        logger.info("Right clicked on element");
    }

    /**
     * Double click on element
     * @param driver WebDriver instance
     * @param element WebElement to double click
     */
    public static void doubleClickOnElement(WebDriver driver, WebElement element) {
        Actions action = new Actions(driver);
        action.doubleClick(element).perform();
        logger.info("Double clicked on element");
    }

    /**
     * Press key
     * @param element WebElement
     * @param key Key to press
     */
    public static void pressKey(WebElement element, Keys key) {
        element.sendKeys(key);
        logger.info("Pressed key: {}", key);
    }

    /**
     * Clear field and type text
     * @param element WebElement
     * @param text Text to type
     */
    public static void clearAndType(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
        logger.info("Cleared field and typed: {}", text);
    }
}

