
package org.automation.pages;

import org.automation.log.Log;
import org.automation.utility.CommonCode;
import org.automation.utility.JavaScriptUtil;
import org.automation.utility.TakeScreenShot;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BrowserNavigationCheck {
    private WebDriver driver;
    private  WebDriverWait wait;
    private JavaScriptUtil j;
    private CommonCode code;
    private TakeScreenShot screenshot;

    public BrowserNavigationCheck(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        code= new CommonCode(driver, Duration.ofSeconds(20));
        this.j = new JavaScriptUtil(driver);
        this.wait=wait;
        this.screenshot = new TakeScreenShot(driver, "screenshots");
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[placeholder='Search your bike here, e.g. Bajaj Pulsar N160']")
    private WebElement SearchBar;

    @FindBy(xpath = "(//a[contains(@href,'/honda-bikes/cb350rs/')])[1]")
    private WebElement coloursBtn;

    public boolean verifyBrowserBackAndForwardNavigation() throws InterruptedException {

        SearchBar.clear();
        SearchBar.sendKeys("Honda CB350RS");
        Thread.sleep(1000);
        SearchBar.sendKeys(Keys.ENTER);
        code.urlContains("cb350rs/");
        String modelUrl = driver.getCurrentUrl();
        Log.info("URL" + modelUrl);
        if (!modelUrl.toLowerCase().contains("cb350rs")) {
            return false;
        }

        code.clickable(coloursBtn);
        j.clickByJS(coloursBtn);
        Thread.sleep(1000);
        j.scrollIntoView(coloursBtn);
        code.clickable(coloursBtn);
        j.clickByJS(coloursBtn);
        driver.navigate().back();
        code.urlContains("cb350rs/");
        String backUrl1 = driver.getCurrentUrl().toLowerCase();
        Log.info("URL"+backUrl1);
        if (!backUrl1.contains("cb350rs/")) {
            return false;
        }

        driver.navigate().back();
        code.urlContains("honda-bikes/");
        screenshot.take("Successfully_Working_Of_Back_Navigation");
        String backUrl2 = driver.getCurrentUrl().toLowerCase();
        Log.info("URL"+backUrl2);
        if (!(backUrl2.contains("honda-bikes/"))) {
            return false;
        }

        driver.navigate().back();
        code.urlContains("bikewale.com");
        String backUrl3 = driver.getCurrentUrl().toLowerCase();
        Log.info("URL"+backUrl3);
        if (!(backUrl3.contains("bikewale.com"))) {
            return false;
        }

        driver.navigate().forward();
        code.urlContains("cb350rs");
        String forwardUrl1 = driver.getCurrentUrl().toLowerCase();
        Log.info("URL"+forwardUrl1);
        if (!(forwardUrl1.contains("cb350rs"))) {
            return false;
        }

        driver.navigate().forward();
        code.urlContains("colours/");
        String forwardUrl2 = driver.getCurrentUrl().toLowerCase();
        Log.info("URL"+forwardUrl2);
        if (!(forwardUrl2.contains("colours/"))) {
            return false;
        }
        Log.info("Navigation working perfectly");
        return true;
    }
}
