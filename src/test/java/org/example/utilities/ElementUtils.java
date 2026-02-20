package org.example.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ElementUtils class for element-related operations
 * Provides methods for element handling, dropdown selection, etc.
 */
public class ElementUtils {
    private static final Logger logger = LoggerFactory.getLogger(ElementUtils.class);

    /**
     * Get all text from multiple elements
     * @param driver WebDriver instance
     * @param locator Element locator
     * @return List of text from elements
     */
    public static List<String> getElementsText(WebDriver driver, By locator) {
        List<WebElement> elements = driver.findElements(locator);
        logger.info("Found {} elements", elements.size());
        return elements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    /**
     * Count elements
     * @param driver WebDriver instance
     * @param locator Element locator
     * @return Count of elements
     */
    public static int countElements(WebDriver driver, By locator) {
        int count = driver.findElements(locator).size();
        logger.info("Element count: {}", count);
        return count;
    }

    /**
     * Check if element exists
     * @param driver WebDriver instance
     * @param locator Element locator
     * @return true if element exists
     */
    public static boolean isElementPresent(WebDriver driver, By locator) {
        boolean isPresent = driver.findElements(locator).size() > 0;
        logger.info("Element {} present: {}", locator, isPresent);
        return isPresent;
    }

    /**
     * Select dropdown option by visible text
     * @param driver WebDriver instance
     * @param dropdownLocator Dropdown element locator
     * @param optionText Option text to select
     */
    public static void selectDropdownByVisibleText(WebDriver driver, By dropdownLocator, String optionText) {
        WebElement dropdown = driver.findElement(dropdownLocator);
        Select select = new Select(dropdown);
        select.selectByVisibleText(optionText);
        logger.info("Selected dropdown option: {}", optionText);
    }

    /**
     * Select dropdown option by value
     * @param driver WebDriver instance
     * @param dropdownLocator Dropdown element locator
     * @param value Option value to select
     */
    public static void selectDropdownByValue(WebDriver driver, By dropdownLocator, String value) {
        WebElement dropdown = driver.findElement(dropdownLocator);
        Select select = new Select(dropdown);
        select.selectByValue(value);
        logger.info("Selected dropdown option by value: {}", value);
    }

    /**
     * Select dropdown option by index
     * @param driver WebDriver instance
     * @param dropdownLocator Dropdown element locator
     * @param index Option index to select
     */
    public static void selectDropdownByIndex(WebDriver driver, By dropdownLocator, int index) {
        WebElement dropdown = driver.findElement(dropdownLocator);
        Select select = new Select(dropdown);
        select.selectByIndex(index);
        logger.info("Selected dropdown option by index: {}", index);
    }

    /**
     * Get all dropdown options
     * @param driver WebDriver instance
     * @param dropdownLocator Dropdown element locator
     * @return List of option texts
     */
    public static List<String> getDropdownOptions(WebDriver driver, By dropdownLocator) {
        WebElement dropdown = driver.findElement(dropdownLocator);
        Select select = new Select(dropdown);
        List<String> options = select.getOptions()
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        logger.info("Retrieved {} dropdown options", options.size());
        return options;
    }

    /**
     * Get selected dropdown option
     * @param driver WebDriver instance
     * @param dropdownLocator Dropdown element locator
     * @return Selected option text
     */
    public static String getSelectedDropdownOption(WebDriver driver, By dropdownLocator) {
        WebElement dropdown = driver.findElement(dropdownLocator);
        Select select = new Select(dropdown);
        String selectedOption = select.getFirstSelectedOption().getText();
        logger.info("Selected option: {}", selectedOption);
        return selectedOption;
    }

    /**
     * Wait for all elements to load
     * @param driver WebDriver instance
     * @param locator Element locator
     * @return true if elements are present
     */
    public static boolean waitForElementsToLoad(WebDriver driver, By locator) {
        try {
            Thread.sleep(2000);
            return isElementPresent(driver, locator);
        } catch (InterruptedException e) {
            logger.error("Interrupted while waiting for elements: {}", e.getMessage());
            return false;
        }
    }
}

