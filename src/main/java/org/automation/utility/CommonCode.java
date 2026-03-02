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

    public WebElement visible(WebElement element) {
        return getWait().until(ExpectedConditions.visibilityOf(element));
    }

    public void clickWhenClickable(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public WebElement clickable(WebElement element) {
        return getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    /** Wait for document.readyState === 'complete' */
    public void pageReady() {
        getWait().until(driver ->
                ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete")
        );
    }

    /** Safe click: wait clickable, try WebDriver click, fallback to JS click */
    public void safeClick(By locator) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                WebElement el = getWait().until(ExpectedConditions.elementToBeClickable(locator));
                try {
                    el.click();
                    return;
                } catch (ElementClickInterceptedException e) {
                    ((JavascriptExecutor) driver).executeScript(
                            "arguments[0].scrollIntoView({block:'center', inline:'center'});", el);
                    el.click();
                    return;
                }
            } catch (StaleElementReferenceException sere) {
                // Retry loop for stale element
            } catch (Exception e) {
                try {
                    WebElement fresh = getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
                    ((JavascriptExecutor) driver).executeScript(
                            "arguments[0].scrollIntoView({block:'center', inline:'center'});", fresh);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fresh);
                    return;
                } catch (StaleElementReferenceException ignored) {}
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
                WebElement el = wait.until(ExpectedConditions.elementToBeClickable(element));
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView({block:'center', inline:'center'});", el);
                el.click();
                return;
            } catch (StaleElementReferenceException | ElementClickInterceptedException | JavascriptException e) {
                // Retry logic for common selenium exceptions
            }

            attempts++;
            try { Thread.sleep(200); } catch (InterruptedException ignored) {}
        }

        // Final fallback: JS click
        try {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center', inline:'center'});", element);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        } catch (Exception ex) {
            throw new TimeoutException("safeClick(WebElement) failed after retries.", ex);
        }
    }

    public void enterText(WebElement element, String text) {
        WebElement el = getWait().until(ExpectedConditions.visibilityOf(element));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block: 'center'});", el);
        el.clear();
        el.sendKeys(text);
    }

    public boolean urlContains(String text) {
        return getWait().until(ExpectedConditions.urlContains(text));
    }
}