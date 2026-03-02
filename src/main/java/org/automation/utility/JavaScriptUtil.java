package org.automation.utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {

    private WebDriver driver;
    private JavascriptExecutor js;

    public JavaScriptUtil(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
    }

    public void clickByJS(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }

    public void doubleClickByJS(WebElement element) {
        js.executeScript("var evt = new MouseEvent('dblclick', {bubbles: true, cancelable: true, view: window});" +
                "arguments[0].dispatchEvent(evt);", element);
    }

    public void sendKeysByJS(WebElement element, String value) {
        js.executeScript("arguments[0].setAttribute('value', arguments[1]);", element, value);
    }

    /**
     * Merged logic: Uses Tushar's centering logic for better visibility
     * but remains compatible with the standard scroll approach.
     */
    public void scrollIntoView(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", element);
    }

    public String getInternalHeaderText(WebElement card) {
        return (String) js.executeScript(
                "return arguments[0].querySelector('h3').textContent;", card);
    }

    public void scrollToBottom() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void scrollToTop() {
        js.executeScript("window.scrollTo(0, 0);");
    }

    public void highlightElement(WebElement element) {
        js.executeScript("arguments[0].style.border='2px solid red'", element);
    }

    public String getTitleByJS() {
        return (String) js.executeScript("return document.title;");
    }

    public void refreshByJS() {
        js.executeScript("history.go(0)");
    }
}