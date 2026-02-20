package org.example.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ConfigReader class for reading configuration from properties files
 * Handles URL, browser type, and other configuration parameters
 */
public class ConfigReader {
    private static final Logger logger = LoggerFactory.getLogger(ConfigReader.class);
    private static Properties properties;

    static {
        try {
            properties = new Properties();
            FileInputStream file = new FileInputStream("src/test/resources/config.properties");
            properties.load(file);
            logger.info("Configuration file loaded successfully");
        } catch (IOException e) {
            logger.error("Failed to load configuration file: {}", e.getMessage());
        }
    }

    /**
     * Get property value by key
     * @param key Property key
     * @return Property value
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Get application URL
     * @return URL
     */
    public static String getApplicationUrl() {
        return getProperty("app.url");
    }

    /**
     * Get browser type
     * @return Browser type (chrome, firefox, edge)
     */
    public static String getBrowser() {
        return getProperty("browser");
    }

    /**
     * Get implicit wait timeout
     * @return Timeout in seconds
     */
    public static int getImplicitWait() {
        return Integer.parseInt(getProperty("implicit.wait"));
    }

    /**
     * Get explicit wait timeout
     * @return Timeout in seconds
     */
    public static int getExplicitWait() {
        return Integer.parseInt(getProperty("explicit.wait"));
    }
}

