
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

public class BreadcrumbWorkingCheck {
    private WebDriver driver;
    private WaitUtils wait;
    private JavaScriptUtil j;
    private TakeScreenShot screenshot;

    public BreadcrumbWorkingCheck(WebDriver driver,WaitUtils wait) {
        this.driver = driver;
        this.wait = wait;
        this.j = new JavaScriptUtil(driver);
        this.screenshot = new TakeScreenShot(driver, "screenshots");
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[placeholder='Search your bike here, e.g. Royal Enfield Hunter 350']")
    private WebElement SearchBar;

    @FindBy(css = "a[title='Home'] span[itemprop='name']")
    private WebElement Home;

    public boolean BreadcrumbWorking() throws InterruptedException {

            wait.visible(SearchBar);
            SearchBar.sendKeys("Hero Xtreme 125R");
            Thread.sleep(1000);
            SearchBar.sendKeys(Keys.ENTER);

            j.scrollIntoView(Home);
            wait.visible(Home);
            wait.clickable(Home);
            System.out.println("Title"+driver.getTitle());
            wait.clickable(Home);
            Home.click();
            screenshot.take("Successfully_Navigated_To_HomePage");
            System.out.println("Title"+driver.getTitle());

            String url = driver.getCurrentUrl();
            if (!(url.contains("bikewale.com/"))) {
                return false;
            }
        return true;
    }

}
