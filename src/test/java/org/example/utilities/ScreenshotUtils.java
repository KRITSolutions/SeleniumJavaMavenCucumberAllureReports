package org.example.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import io.qameta.allure.Allure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ScreenshotUtils class for capturing screenshots
 * Attached screenshots to Allure reports
 */
public class ScreenshotUtils {
    private static final Logger logger = LoggerFactory.getLogger(ScreenshotUtils.class);

    /**
     * Take screenshot and attach to Allure report
     * @param driver WebDriver instance
     * @param screenshotName Name for the screenshot
     */
    public static void captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);

            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String fileName = screenshotName + "_" + timeStamp + ".png";

            FileInputStream fileInputStream = new FileInputStream(sourceFile);
            Allure.addAttachment(fileName, "image/png", fileInputStream, "png");

            logger.info("Screenshot '{}' captured and attached to Allure report", fileName);
        } catch (IOException e) {
            logger.error("Failed to capture screenshot: {}", e.getMessage());
        }
    }

    /**
     * Take screenshot on test failure
     * @param driver WebDriver instance
     * @param testName Name of the test
     */
    public static void takeScreenshotOnFailure(WebDriver driver, String testName) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);

            String fileName = "FAILURE_" + testName + "_" + System.currentTimeMillis() + ".png";
            FileInputStream fileInputStream = new FileInputStream(sourceFile);
            Allure.addAttachment(fileName, "image/png", fileInputStream, "png");

            logger.info("Failure screenshot '{}' captured and attached to Allure report", fileName);
        } catch (IOException e) {
            logger.error("Failed to capture failure screenshot: {}", e.getMessage());
        }
    }
}

