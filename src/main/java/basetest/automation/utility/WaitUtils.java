package basetest.automation.utility;
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

    public boolean titleContains(String text) {
        return getWait().until(ExpectedConditions.titleContains(text));
    }
}
