package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * XPathPracticePage class for SelectorHub XPath Practice Page
 * Contains locators and methods for interacting with the practice page
 */
public class XPathPracticePage extends BasePage {

    // Locators - updated to handle various page structures
    // Primary locators with fallbacks for broader patterns
    private static final By USER_EMAIL_INPUT = By.xpath(
        "//input[@id='userId'] | //input[@name='email'] | //input[@type='email'] | //input[contains(@placeholder, 'email') or contains(@placeholder, 'Email')]");

    private static final By USER_PASSWORD_INPUT = By.xpath(
        "//input[@id='password'] | //input[@name='password'] | //input[@type='password']");

    private static final By SIGNUP_BUTTON = By.xpath(
        "//button[@id='signInBtn'] | //button[contains(text(), 'Sign In')] | //button[contains(text(), 'Login')] | //button[@type='submit']");

    private static final By BUTTON_BY_CLASS = By.xpath("//button[@class='btn btn-default']");

    private static final By LINK_BY_PARTIAL_TEXT = By.xpath("//a[contains(text(), 'Practice')]");

    private static final By TABLE_ELEMENT = By.xpath("//table[@class='table'] | //table");

    private static final By DYNAMIC_ELEMENT = By.xpath("//div[@class='dynamic'] | //div[contains(@class, 'dynamic')]");

    private static final By SUCCESS_MESSAGE = By.xpath(
        "//span[@class='success'] | //*[contains(text(), 'success')] | //*[contains(@class, 'success')]");

    private static final By ERROR_MESSAGE = By.xpath(
        "//span[@class='error'] | //*[contains(text(), 'error')] | //*[contains(@class, 'error')]");

    private static final By CHECKBOX_ELEMENT = By.xpath("//input[@type='checkbox']");

    private static final By RADIO_BUTTON = By.xpath("//input[@type='radio']");

    private static final By DROPDOWN_ELEMENT = By.xpath(
        "//select[@id='country'] | //select | //*[contains(@id, 'country')]");

    private static final By INPUT_FIELD_WITH_PLACEHOLDER = By.xpath(
        "//input[@placeholder='Email'] | //input[contains(@placeholder, 'Email')] | //input[@type='email']");

    private static final By BUTTON_WITH_DATA_ATTRIBUTE = By.xpath(
        "//button[@data-qa='submit-btn'] | //button[contains(@data-qa, 'submit')]");

    private static final By PARENT_ELEMENT_BY_CHILD = By.xpath("//div[contains(.//input[@type='text'], '')]");

    // Constructor
    public XPathPracticePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Enter email in user email field
     * @param email Email to enter
     */
    public void enterEmail(String email) {
        enterText(USER_EMAIL_INPUT, email);
    }

    /**
     * Enter password in password field
     * @param password Password to enter
     */
    public void enterPassword(String password) {
        enterText(USER_PASSWORD_INPUT, password);
    }

    /**
     * Click on Sign In button
     */
    public void clickSignInButton() {
        click(SIGNUP_BUTTON);
    }

    /**
     * Click button by class
     */
    public void clickButtonByClass() {
        click(BUTTON_BY_CLASS);
    }

    /**
     * Get success message text
     * @return Success message text
     */
    public String getSuccessMessage() {
        return getText(SUCCESS_MESSAGE);
    }

    /**
     * Get error message text
     * @return Error message text
     */
    public String getErrorMessage() {
        return getText(ERROR_MESSAGE);
    }

    /**
     * Check if success message is displayed
     * @return true if displayed
     */
    public boolean isSuccessMessageDisplayed() {
        return isElementDisplayed(SUCCESS_MESSAGE);
    }

    /**
     * Check if error message is displayed
     * @return true if displayed
     */
    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(ERROR_MESSAGE);
    }

    /**
     * Click on checkbox element
     */
    public void clickCheckbox() {
        click(CHECKBOX_ELEMENT);
    }

    /**
     * Click on radio button
     */
    public void clickRadioButton() {
        click(RADIO_BUTTON);
    }

    /**
     * Select option from dropdown
     * @param optionValue Value of option to select
     */
    public void selectFromDropdown(String optionValue) {
        // Try multiple patterns to find the option
        By dropdownOption = By.xpath(
            "//select[@id='country']/option[@value='" + optionValue + "'] | " +
            "//select/option[@value='" + optionValue + "'] | " +
            "//option[contains(text(), '" + optionValue + "')]");
        click(dropdownOption);
    }

    /**
     * Enter text in email input field
     * @param email Email to enter
     */
    public void enterEmailWithPlaceholder(String email) {
        enterText(INPUT_FIELD_WITH_PLACEHOLDER, email);
    }

    /**
     * Click button with data attribute
     */
    public void clickButtonWithDataAttribute() {
        click(BUTTON_WITH_DATA_ATTRIBUTE);
    }

    /**
     * Check if table element is visible
     * @return true if visible
     */
    public boolean isTableVisible() {
        return isElementDisplayed(TABLE_ELEMENT);
    }

    /**
     * Check if dynamic element is visible
     * @return true if visible
     */
    public boolean isDynamicElementVisible() {
        return isElementDisplayed(DYNAMIC_ELEMENT);
    }

    /**
     * Sign in with email and password
     * @param email Email address
     * @param password Password
     */
    public void signIn(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickSignInButton();
    }

    /**
     * Perform complete login flow
     * @param email Email address
     * @param password Password
     * @return true if login successful
     */
    public boolean completeLoginFlow(String email, String password) {
        signIn(email, password);
        return isSuccessMessageDisplayed();
    }
}

