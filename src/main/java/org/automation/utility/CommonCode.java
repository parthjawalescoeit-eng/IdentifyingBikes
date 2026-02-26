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
        getWait().until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void pageReady() {
        getWait().until(driver ->
                ((JavascriptExecutor) driver).executeScript("return document.readyState")
        );
    }

    public void safeClick(By locator) {
        WebElement el = getWait().until(ExpectedConditions.elementToBeClickable(locator));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'center'});", el);
        el.click();
    }

    public void safeClickToWebElement(WebElement element) {
        WebElement el = getWait().until(ExpectedConditions.elementToBeClickable(element));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'center'});", el);
        el.click();
    }
    public void enterText(WebElement element,String text) {

        WebElement el = getWait().until(ExpectedConditions.visibilityOf(element));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block: 'center'});", el);
        el.clear();
        el.sendKeys(text);

    }
}
