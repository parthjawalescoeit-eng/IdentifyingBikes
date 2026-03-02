package org.automation.pages;

import org.automation.utility.CommonCode;
import org.automation.utility.JavaScriptUtil;
import org.automation.utility.TakeScreenShot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.automation.log.Log;

import java.time.Duration;

public class UpcomingBikes {
    WebDriver driver;
    WebDriverWait wait;
    JavaScriptUtil js;
    CommonCode common;

    public UpcomingBikes(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.js = new JavaScriptUtil(driver);
        this.common=new CommonCode(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[@class='header__menu-icon']")
    WebElement menuIcon;

    @FindBy(xpath = "//span[text()='New Bikes']")
    WebElement newBikesMenu;

    @FindBy(xpath = "//span[text()='Upcoming Bikes']")
    WebElement upcomingBikesOptn;

    @FindBy(xpath = "//p[text()='Notify Me on Launch']")
    WebElement notifyMe;

    @FindBy(xpath = "//p[text()='Notify me']")
    WebElement notifyPopUpHeader;

    public boolean verifyNotifyMeForm() {
        common.clickWhenClickable(menuIcon);
        common.safeClickToWebElement(newBikesMenu);
        common.safeClickToWebElement(upcomingBikesOptn);
        common.safeClickToWebElement(notifyMe);

        try {
            wait.until(ExpectedConditions.visibilityOf(notifyPopUpHeader));
            boolean isDisplayed = notifyPopUpHeader.isDisplayed();
            if (isDisplayed) {
                new TakeScreenShot(driver, "screenshots").take("TC_08_Notify_Me");
                Log.info("Notify_Me_ScreenShot Captured");
            }
            return isDisplayed;
        } catch (Exception e) {
            Log.info("Failure screenshot captured: Notify_Me");
            return false;
        }
    }
}