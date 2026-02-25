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
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.ignoring(StaleElementReferenceException.class);
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(ElementClickInterceptedException.class);
        return wait;
    }

    public WebElement visible(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement visible(WebElement element) {
        return getWait().until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement clickable(By locator) {
        return getWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

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


    public boolean urlToBe(String expectedUrl) {
        return getWait().until(ExpectedConditions.urlToBe(expectedUrl));
    }
    public boolean attributeValueToBe(WebElement element, String expectedValue, String s) {
        return getWait().until(ExpectedConditions.attributeToBe(element, "value", expectedValue));
    }

    public boolean titleContains(String text) {
        return getWait().until(ExpectedConditions.titleContains(text));
    }

    public void frameAndSwitch(By iframeLocator) {
        getWait().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeLocator));
    }
    public boolean numberOfWindowsToBe(int count) {
        return getWait().until(ExpectedConditions.numberOfWindowsToBe(count));
    }
}
