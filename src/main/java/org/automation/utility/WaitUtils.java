package org.automation.utility;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class WaitUtils {

    private final WebDriver driver;
    private final Duration timeout;

    public WaitUtils(WebDriver driver, Duration timeout) {
        this.driver = driver;
        this.timeout = timeout;
    }

    private WebDriverWait getWait() {
        return new WebDriverWait(driver, timeout);
    }

    public WebElement visible(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement visible(WebElement element) {
        return getWait().until(ExpectedConditions.visibilityOf(element));
    }

//    public WebElement clickable(By locator) {
//        return getWait().until(ExpectedConditions.elementToBeClickable(locator));
//    }

//    public void clickWhenClickable(WebElement element) {
//        // Wait until the element is clickable
//        getWait().until(ExpectedConditions.elementToBeClickable(element));
//
//        // Click immediately
//        element.click();
//    }

//
//    public void clickWhenClickable(By locator) {
//        WebElement element = getWait().until(ExpectedConditions.elementToBeClickable(locator));
//        element.click();
//    }



    public WebElement clickable(WebElement element) {
        return getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean invisible(By locator) {
        return getWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public boolean invisible(WebElement element) {
        return getWait().until(ExpectedConditions.invisibilityOf(element));
    }

    public WebElement presence(By locator) {
        return getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public boolean urlContains(String text) {
        return getWait().until(ExpectedConditions.urlContains(text));
    }

    public boolean titleContains(String text) {
        return getWait().until(ExpectedConditions.titleContains(text));
    }


    /** Wait for document.readyState === 'complete' */
    public void pageReady() {
        getWait().until(driver ->
                ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete")
        );
    }


    /** Wait until element has non-empty text */
    public WebElement visibleWithText(By locator) {
        return getWait().until(d -> {
            WebElement el = d.findElement(locator);
            return (el.isDisplayed() && !el.getText().trim().isEmpty()) ? el : null;
        });
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
}
