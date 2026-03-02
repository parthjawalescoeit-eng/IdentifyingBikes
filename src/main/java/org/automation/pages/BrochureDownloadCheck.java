package org.automation.pages;

import org.automation.utility.CommonCode;
import org.automation.utility.JavaScriptUtil;
import org.automation.utility.TakeScreenShot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class BrochureDownloadCheck {
    private WebDriver driver;
    private JavaScriptUtil j;
    private CommonCode code;
    private WebDriverWait wait;
    private TakeScreenShot screenshot;

    public BrochureDownloadCheck(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.code=new CommonCode(driver, Duration.ofSeconds(20));
        this.j = new JavaScriptUtil(driver);
        this.wait=wait;
        this.screenshot = new TakeScreenShot(driver, "screenshots");
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[contains(@class,'o-C') and contains(@class,'ccQ1nm')]//img[contains(@title,'Royal Enfield Hunter 350')]")
    private WebElement bikeModelImg;

    @FindBy(css = "span[title='Royal Enfield Hunter 350 Mileage'] div")
    private WebElement mileageBtn;

    @FindBy(css = "section[data-section-id='model-brochure-section'] div div button[type='button']")
    private WebElement brochureDownloadBtn;

    public boolean downloadBrochure() throws InterruptedException {

        code.visible(bikeModelImg);
        j.scrollIntoView(bikeModelImg);
        code.clickable(bikeModelImg);
        bikeModelImg.click();

        String originalWindow = driver.getWindowHandle();
        Set<String> oldWindows = driver.getWindowHandles();
        code.clickable(mileageBtn);
        mileageBtn.click();
        try {
            code.visible(brochureDownloadBtn);
            code.clickable(brochureDownloadBtn);
            brochureDownloadBtn.click();
        } catch (Exception e) {
            code.visible(brochureDownloadBtn);
            code.clickable(brochureDownloadBtn);
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
