package basetest.automation.utility;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {

    private WebDriver driver;

    private JavascriptExecutor js ;




    public JavaScriptUtil(WebDriver driver) {
        this.driver = driver;
        this.js=(JavascriptExecutor)driver;
    }

//    public Object executeScript(String script, Object... args) {
//        js= (JavascriptExecutor) driver;
//        return js.executeScript(script, args);
//    }

    public void clickByJS(WebElement element) {

        js.executeScript(
                "arguments[0].click();",element
        );
    }


    public void scrollIntoView(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
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


