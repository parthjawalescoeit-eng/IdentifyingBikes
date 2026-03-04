package org.automation.utility;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;

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
        getWait().until(ExpectedConditions.elementToBeClickable(element)).click();
    }
    public WebElement clickable(WebElement element) {
        return getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForNewWindowToOpen(int element) {
         getWait().until(ExpectedConditions.numberOfWindowsToBe(element+1));
    }

    public void pageReady() {
        getWait().until(driver ->
                ((JavascriptExecutor) driver).executeScript("return document.readyState")
        );
    }

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
            } catch (Exception e) {
                try {
                    WebElement fresh = getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
                    ((JavascriptExecutor) driver).executeScript(
                            "arguments[0].scrollIntoView({block:'center', inline:'center'});", fresh);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fresh);
                    return;
                } catch (StaleElementReferenceException ignored) {
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
                WebElement el = wait.until(ExpectedConditions.elementToBeClickable(element));
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView({block:'center', inline:'center'});", el);
                el.click();
                return;
            } catch (StaleElementReferenceException sere) {
            } catch (ElementClickInterceptedException e) {
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView({block:'center', inline:'center'});", element);
            } catch (JavascriptException ignored) {

            }
            attempts++;
            try { Thread.sleep(200); } catch (InterruptedException ignored) {}
        }

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
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block: 'center'});", el);
        el.clear();
        el.sendKeys(text);
    }


    public WebElement visible(WebElement element) {
        return getWait().until(ExpectedConditions.visibilityOf(element));
    }

    public boolean urlContains(String text) {
        return getWait().until(ExpectedConditions.urlContains(text));
    }

    public void selectByText(WebElement element, String text) {
        WebElement el = getWait().until(ExpectedConditions.visibilityOf(element));
        Select select = new Select(el);
        select.selectByVisibleText(text);
    }

    public void selectByPartialText(WebElement element, String partialText) {
        WebElement el = getWait().until(ExpectedConditions.visibilityOf(element));
        Select select = new Select(el);
        List<WebElement> options = select.getOptions();
        for (WebElement option : options) {
            if (option.getText().toLowerCase().contains(partialText.toLowerCase())) {
                select.selectByVisibleText(option.getText());
                return;
            }
        }
    }
}