package org.automation.pages;

import org.automation.utility.JavaScriptUtil;
import org.automation.utility.TakeScreenShot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UpcomingBikes {
    WebDriver driver;
    WebDriverWait wait;
    JavaScriptUtil js;

    public UpcomingBikes(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.js = new JavaScriptUtil(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[@class='header__menu-icon']")
    WebElement menuIcon;
    @FindBy(xpath = "//span[text()='New Bikes']")
    WebElement newBikesMenu;
    @FindBy(xpath = "//span[text()='Upcoming Bikes']")
    WebElement upcomingBikesOption;
    @FindBy(xpath = "//p[text()='Notify Me on Launch']")
    WebElement notifyMe;
    public void verifyNotifyMeForm() {
        menuIcon.click();
        js.clickByJS(newBikesMenu);
        js.clickByJS(upcomingBikesOption);
        wait.until(ExpectedConditions.elementToBeClickable(notifyMe));
        js.scrollIntoView(notifyMe);
        js.clickByJS(notifyMe);
        TakeScreenShot ts = new TakeScreenShot(driver, "screenshots");
        ts.take("Notify_Me_Form");
    }
}