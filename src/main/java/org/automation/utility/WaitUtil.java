package org.automation.utility;


import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class WaitUtil {

    private final WebDriver driver;
    private static final int timeout=10;
    private static WebDriverWait wait;

    public WaitUtil(WebDriver driver, int timeout, WebDriverWait wait) {
        this.driver = driver;
        this.wait=wait;
    }
    public static WebElement waitForElementClickable(WebDriver driver, WebElement element) {
        // Create the wait instance here using the driver passed from the Page Object
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForElementVisible(WebDriver driver, WebElement webElement) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

        wait.until(ExpectedConditions.visibilityOf(webElement));
    }
}


