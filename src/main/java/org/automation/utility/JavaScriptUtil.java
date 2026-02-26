package org.automation.utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {

    private WebDriver driver;

    private JavascriptExecutor js;

    public JavaScriptUtil(WebDriver driver) {
        this.driver = driver;
    }

    public Object executeScript(String script, Object... args) {
        js = (JavascriptExecutor) driver;
        return js.executeScript(script, args);
    }

    public void clickByJS(WebElement element) {
        executeScript("arguments[0].click();", element);
    }

    public void scrollIntoView(WebElement element) {
        executeScript("arguments[0].scrollIntoView(true);", element);
    }


    public void scrollToTop() {
        executeScript("window.scrollTo(0, 0);");
    }
}



