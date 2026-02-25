package org.automation.pages;

import org.automation.utility.JavaScriptUtil;
import org.automation.utility.TakeScreenShot;
import org.automation.utility.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.Set;

public class BrochureDownloadCheck {
    private WebDriver driver;
    private WaitUtils wait;
    private JavaScriptUtil j;
    private TakeScreenShot screenshot;

    public BrochureDownloadCheck(WebDriver driver,WaitUtils wait) {
        this.driver = driver;
        this.wait = wait;
        this.j = new JavaScriptUtil(driver);
        this.screenshot = new TakeScreenShot(driver, "screenshots");
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[contains(@class,'o-C') and contains(@class,'ccQ1nm')]//img[contains(@title,'Royal Enfield Hunter 350')]")
    private WebElement bikeModel;

    @FindBy(css = "span[title='Royal Enfield Hunter 350 Mileage'] div")
    private WebElement mileage;

    @FindBy(css = "section[data-section-id='model-brochure-section'] div div button[type='button']")
    private WebElement brochureDownloadBtn;

    public boolean downloadBrochure() throws InterruptedException {

        wait.visible(bikeModel);
        j.scrollIntoView(bikeModel);
        wait.clickable(bikeModel);
        bikeModel.click();

        String originalWindow = driver.getWindowHandle();
        Set<String> oldWindows = driver.getWindowHandles();
        wait.clickable(mileage);
        mileage.click();
        try {
            wait.visible(brochureDownloadBtn);
            wait.clickable(brochureDownloadBtn);
            brochureDownloadBtn.click();
        } catch (Exception e) {
            wait.visible(brochureDownloadBtn);
            wait.clickable(brochureDownloadBtn);
            j.clickByJS(brochureDownloadBtn);
        }

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

        String url = driver.getCurrentUrl();
        if (!(url.contains("stc.aeplcdn.com/f/brochure/11/3295/18dkcdl.pdf"))) {
            return false;
        }
        Thread.sleep(2000);
        screenshot.take("Brochure_Downloaded_Successfully");

        driver.close();
        driver.switchTo().window(originalWindow);

        return true;

    }
}
