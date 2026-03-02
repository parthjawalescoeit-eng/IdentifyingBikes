package org.automation.pages;

import org.automation.log.Log;
import org.automation.utility.CommonCode;
import org.automation.utility.TakeScreenShot;
import org.automation.utility.JavaScriptUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class YouTubenavigationcheck {

    private WebDriver driver;
    private JavaScriptUtil j;
    private TakeScreenShot screenshot;
    private CommonCode code;

    public YouTubenavigationcheck(WebDriver driver,WebDriverWait wait) {
        this.driver = driver;
        this.j = new JavaScriptUtil(driver);
        this.code=new CommonCode(driver,Duration.ofSeconds(20));
        this.screenshot = new TakeScreenShot(driver, "screenshots");
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[contains(@class,'o-C') and contains(@class,'ccQ1nm')]//img[contains(@title,'Royal Enfield Hunter 350')]")
    private WebElement bikeModel;

    @FindBy(css = "a[href*='youtube.com'], a[href*='youtu.be']")
    private WebElement youtubeAnchor;

    @FindBy(css = "img[title='2025 Royal Enfield Hunter 350 Review | Finally Fixed? | BikeWale']")
    private WebElement youtubeThumbnailImg;

    public boolean verifyVideoPlayback() throws InterruptedException {
            //wait.visible(bikeModel);
            j.scrollIntoView(bikeModel);
            //bikeModel.click();
           code.clickWhenClickable(bikeModel);
           code.visible(youtubeAnchor);

            String originalWindow = driver.getWindowHandle();
            Set<String> oldWindows = driver.getWindowHandles();
            code.clickable(youtubeAnchor);
             j.clickByJS(youtubeAnchor);

            Thread.sleep(3000);
            Set<String> newWindows = driver.getWindowHandles();

            if (newWindows.size() == oldWindows.size()) {
                return false;
            }

            for (String window : newWindows) {
                if (!oldWindows.contains(window)) {
                    driver.switchTo().window(window);
                    break;
                }
            }
            screenshot.take("TC_11_Opened_YouTube_On_New_Window");

            String url = driver.getCurrentUrl();
            Log.info(url);
            if (!(url.contains("youtube.com"))) {
                return false;
            }
            driver.close();
            driver.switchTo().window(originalWindow);

            return true;
    }
}