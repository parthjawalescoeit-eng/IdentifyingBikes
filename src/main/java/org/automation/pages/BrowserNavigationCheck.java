
package org.automation.pages;

import org.automation.utility.JavaScriptUtil;
import org.automation.utility.TakeScreenShot;
import org.automation.utility.WaitUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class BrowserNavigationCheck {
    private WebDriver driver;
    private  WaitUtils wait;
    private JavaScriptUtil j;
    private TakeScreenShot screenshot;

    public BrowserNavigationCheck(WebDriver driver,WaitUtils wait) {
        this.driver = driver;
        this.wait = wait;
        this.j = new JavaScriptUtil(driver);
        this.screenshot = new TakeScreenShot(driver, "screenshots");
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[placeholder='Search your bike here, e.g. Royal Enfield Hunter 350']")
    private WebElement SearchBar;

    @FindBy(xpath = "(//a[contains(@href,'/honda-bikes/cb350rs/')])[1]")
    private WebElement colours;

    public boolean verifyBrowserBackAndForwardNavigation() throws InterruptedException {
        wait.visible(SearchBar);
        SearchBar.clear();
        SearchBar.sendKeys("Honda CB350RS");
        Thread.sleep(1000);
        SearchBar.sendKeys(Keys.ENTER);
        wait.urlContains("cb350rs/");
        String modelUrl = driver.getCurrentUrl();
        System.out.println("URL"+modelUrl);
        if (!modelUrl.toLowerCase().contains("cb350rs")) {
            return false;
        }

        j.scrollIntoView(colours);
        wait.clickable(colours);
        j.clickByJS(colours);
        Thread.sleep(1000);
        j.scrollIntoView(colours);
        wait.clickable(colours);
        j.clickByJS(colours);

        driver.navigate().back();
        wait.urlContains("cb350rs/");
        String backUrl1 = driver.getCurrentUrl().toLowerCase();
        System.out.println("URL"+backUrl1);
        if (!backUrl1.contains("cb350rs/")) {
            return false;
        }

        driver.navigate().back();
        wait.urlContains("honda-bikes/");
        screenshot.take("Successfully_Working_Of_Back_Navigation");
        String backUrl2 = driver.getCurrentUrl().toLowerCase();
        System.out.println("URL"+backUrl2);
        if (!(backUrl2.contains("honda-bikes/"))) {
            return false;
        }

        driver.navigate().back();
        wait.urlContains("bikewale.com");
        String backUrl3 = driver.getCurrentUrl().toLowerCase();
        System.out.println("URL"+backUrl3);
        if (!(backUrl3.contains("bikewale.com"))) {
            return false;
        }

        driver.navigate().forward();
        wait.urlContains("cb350rs");
        String forwardUrl1 = driver.getCurrentUrl().toLowerCase();
        System.out.println("URL"+forwardUrl1);
        if (!(forwardUrl1.contains("cb350rs"))) {
            return false;
        }

        driver.navigate().forward();
        wait.urlContains("colours/");
        String forwardUrl2 = driver.getCurrentUrl().toLowerCase();
        System.out.println("URL"+forwardUrl2);
        if (!(forwardUrl2.contains("colours/"))) {
            return false;
        }
        System.out.println("Navigation working perfectly");
        return true;
    }
}
