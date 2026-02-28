
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

public class BreadcrumbWorkingCheck {
    private WebDriver driver;
    private CommonCode code;
    private JavaScriptUtil j;
    private WebDriverWait wait;
    private TakeScreenShot screenshot;

    public BreadcrumbWorkingCheck(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.code = new CommonCode(driver, Duration.ofSeconds(20));
        this.j = new JavaScriptUtil(driver);
        this.wait=wait;
        this.screenshot = new TakeScreenShot(driver, "screenshots");
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[placeholder='Search your bike here, e.g. Royal Enfield Hunter 350']")
    private WebElement SearchBar;

    @FindBy(css = "a[title='Home'] span[itemprop='name']")
    private WebElement HomeLnk;

    public boolean BreadcrumbWorking() throws InterruptedException {

        code.visible(SearchBar);
        SearchBar.sendKeys("Hero Xtreme 125R");
        Thread.sleep(1000);
        SearchBar.sendKeys(Keys.ENTER);

//            j.scrollIntoView(HomeLnk);
//            code.visible(HomeLnk);
        code.clickable(HomeLnk);
        Log.info("Title"+driver.getTitle());
        code.clickable(HomeLnk);
        j.clickByJS(HomeLnk);
        screenshot.take("Successfully_Navigated_To_HomePage");
        Log.info("Title"+driver.getTitle());

        String url = driver.getCurrentUrl();
        if (!(url.contains("bikewale.com/"))) {
            return false;
        }
        return true;
    }

}