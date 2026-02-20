package org.example.utilities;

import java.util.HashMap;
import java.util.Map;

/**
 * TestData class for storing test data
 * Provides methods to access test data for various scenarios
 */
public class TestData {

    // Valid user credentials
    public static final String VALID_EMAIL = "practice@example.com";
    public static final String VALID_PASSWORD = "Test@123";

    // Invalid user credentials
    public static final String INVALID_EMAIL = "invalid@example.com";
    public static final String INVALID_PASSWORD = "wrongpassword";

    // Test data for XPath practice
    private static final Map<String, String> testDataMap = new HashMap<>();

    static {
        // Initialize test data
        testDataMap.put("email", "testuser@example.com");
        testDataMap.put("password", "TestPassword123");
        testDataMap.put("firstName", "John");
        testDataMap.put("lastName", "Doe");
        testDataMap.put("country", "USA");
        testDataMap.put("phone", "1234567890");
    }

    /**
     * Get test data value by key
     * @param key Data key
     * @return Data value
     */
    public static String getTestData(String key) {
        return testDataMap.getOrDefault(key, "");
    }

    /**
     * Get all test data
     * @return Map of test data
     */
    public static Map<String, String> getAllTestData() {
        return new HashMap<>(testDataMap);
    }

    /**
     * Get email addresses for testing
     * @return Array of email addresses
     */
    public static String[] getTestEmails() {
        return new String[]{
            "test1@example.com",
            "test2@example.com",
            "test3@example.com",
            "practice@example.com"
        };
    }

    /**
     * Get passwords for testing
     * @return Array of passwords
     */
    public static String[] getTestPasswords() {
        return new String[]{
            "Password1",
            "Password2",
            "Password3",
            "Test@123"
        };
    }
}

