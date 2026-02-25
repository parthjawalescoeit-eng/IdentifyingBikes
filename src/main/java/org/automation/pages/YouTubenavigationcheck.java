package org.automation.pages;

import org.automation.utility.TakeScreenShot;
import org.automation.utility.WaitUtils;
import org.automation.utility.JavaScriptUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.Set;

public class YouTubenavigationcheck {

    private WebDriver driver;
    private WaitUtils wait;
    private JavaScriptUtil j;
    private TakeScreenShot screenshot;

    public YouTubenavigationcheck(WebDriver driver,WaitUtils wait) {
        this.driver = driver;
        this.wait = wait;
        this.j = new JavaScriptUtil(driver);
        this.screenshot = new TakeScreenShot(driver, "screenshots");
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[contains(@class,'o-C') and contains(@class,'ccQ1nm')]//img[contains(@title,'Royal Enfield Hunter 350')]")
    private WebElement bikeModel;

    @FindBy(css = "a[href*='youtube.com'], a[href*='youtu.be']")
    private WebElement youtubeAnchor;

    @FindBy(css = "img[title='2025 Royal Enfield Hunter 350 Review | Finally Fixed? | BikeWale']")
    private WebElement youtubeThumbnailImg;

    public boolean verifyVideoPlayback() {
        try {
            wait.visible(bikeModel);
            j.scrollIntoView(bikeModel);
            bikeModel.click();

            String originalWindow = driver.getWindowHandle();
            Set<String> oldWindows = driver.getWindowHandles();

            try {
                youtubeAnchor.click();
            } catch (Exception e) {
                j.clickByJS(youtubeAnchor);
            }

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
            screenshot.take("Opened_YouTube_On_New_Window");


            String url = driver.getCurrentUrl();
            System.out.println(url);
            if (!(url.contains("youtube.com"))) {
                return false;
            }

            driver.close();
            driver.switchTo().window(originalWindow);

            return true;

        } catch (Exception e) {
            return false;
        }
    }


}