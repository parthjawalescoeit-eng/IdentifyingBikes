package org.automation.utility;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class CommonCode {

    private final WebDriver driver;
    private final Duration timeout;

    public CommonCode(WebDriver driver, Duration timeout) {
        this.driver = driver;
        this.timeout = timeout;
    }


    public WebDriverWait getWait() {
        return new WebDriverWait(driver, timeout);
    }

    public WebElement visible(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void clickWhenClickable(WebElement element) {
        // Wait until the element is clickable
        getWait().until(ExpectedConditions.elementToBeClickable(element)).click();
        // Click immediately
    }

    /** Wait for document.readyState === 'complete' */
    public void pageReady() {
        getWait().until(driver ->
                ((JavascriptExecutor) driver).executeScript("return document.readyState")
        );
    }

    /** Safe click: wait clickable, try WebDriver click, fallback to JS click */
    // WaitUtils.java (replace/add this method)
    public void safeClick(By locator) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                // Re-find fresh element each attempt
                WebElement el = getWait().until(ExpectedConditions.elementToBeClickable(locator));
                try {
                    el.click(); // native click
                    return;
                } catch (ElementClickInterceptedException e) {
                    // Scroll to center, try native again
                    ((JavascriptExecutor) driver).executeScript(
                            "arguments[0].scrollIntoView({block:'center', inline:'center'});", el);
                    el.click();
                    return;
                }
            } catch (StaleElementReferenceException sere) {
                // DOM changed; retry by looping
            } catch (Exception e) {
                // As a last resort, try JS click with a fresh element
                try {
                    WebElement fresh = getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
                    ((JavascriptExecutor) driver).executeScript(
                            "arguments[0].scrollIntoView({block:'center', inline:'center'});", fresh);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fresh);
                    return;
                } catch (StaleElementReferenceException ignored) {
                    // will retry loop
                }
            }
            attempts++;
        }
        throw new TimeoutException("safeClick failed after retries for locator: " + locator);
    }

    public void safeClickToWebElement(WebElement element) {
        WebDriverWait wait = getWait();
        int attempts = 0;
        while (attempts < 3) {
            try {
                // 1) Wait until clickable on a fresh reference
                WebElement el = wait.until(ExpectedConditions.elementToBeClickable(element));

                // 2) Scroll element to center (helps with sticky headers)
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView({block:'center', inline:'center'});", el);

                // 3) Try native click
                el.click();
                return;
            } catch (StaleElementReferenceException sere) {
                // Re-resolve the proxy and retry
            } catch (ElementClickInterceptedException e) {
                // Retry after small nudge/scroll
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView({block:'center', inline:'center'});", element);
            } catch (JavascriptException ignored) {
                // ignore and retry
            }

            attempts++;
            try { Thread.sleep(200); } catch (InterruptedException ignored) {}
        }

        // Final fallback: JS click on a fresh element reference
        try {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center', inline:'center'});", element);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        } catch (Exception ex) {
            throw new TimeoutException("safeClick(WebElement) failed after retries.", ex);
        }
    }
    public void enterText(WebElement element,String text) {

        WebElement el = getWait().until(ExpectedConditions.visibilityOf(element));
        // Bring element into view
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block: 'center'});", el);
        el.clear();
        el.sendKeys(text);

    }
    public boolean urlContains(String text) {
        return getWait().until(ExpectedConditions.urlContains(text));
    }
}
