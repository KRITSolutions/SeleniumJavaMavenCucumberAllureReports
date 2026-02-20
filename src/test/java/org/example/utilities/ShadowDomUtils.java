package org.example.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ShadowDomUtils - tiny utility to query shadow DOM using JS execution
 * Provides a method to return the first element matching a CSS selector across shadow roots
 * Note: This is a best-effort utility and supports common scenarios used in the tests.
 */
public class ShadowDomUtils {
    private static final Logger logger = LoggerFactory.getLogger(ShadowDomUtils.class);

    /**
     * Find element by CSS selector traversing shadow roots.
     * @param driver WebDriver instance
     * @param cssSelector CSS selector to search for
     * @return WebElement if found, otherwise null
     */
    public static WebElement findElementByCss(WebDriver driver, String cssSelector) {
        try {
            String script = "return (function(selector){\\n" +
                    "  function queryDeep(selector){\\n" +
                    "    const parts = selector.split(' ');\\n" +
                    "    function findIn(root, parts, index){\\n" +
                    "      if(!root) return null;\\n" +
                    "      const el = root.querySelector(parts[index]);\\n" +
                    "      if(!el) return null;\\n" +
                    "      if(index === parts.length - 1) return el;\\n" +
                    "      // try shadow root next\\n" +
                    "      const shadow = el.shadowRoot;\\n" +
                    "      return findIn(shadow, parts, index+1) || findIn(el, parts, index+1);\\n" +
                    "    }\\n" +
                    "    return findIn(document, parts, 0);\\n" +
                    "  }\\n" +
                    "  return queryDeep(selector);\\n" +
                    "})(arguments[0]);";

            Object res = ((JavascriptExecutor) driver).executeScript(script, cssSelector);
            if (res instanceof WebElement) {
                return (WebElement) res;
            }
            return null;
        } catch (Exception e) {
            logger.warn("ShadowDom lookup failed for {}: {}", cssSelector, e.getMessage());
            return null;
        }
    }
}
