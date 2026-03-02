package org.automation.pages;

import org.automation.utility.JavaScriptUtil;
import org.automation.utility.CommonCode;
import org.automation.utility.TakeScreenShot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TogglePage {

    private final WebDriver driver;
    private final CommonCode commonCode;
    private final JavaScriptUtil js;
    private final WebDriverWait wait;

    public TogglePage(WebDriver driver , WebDriverWait wait){
        this.driver = driver;
        this.commonCode = new CommonCode(driver, Duration.ofSeconds(20));
        this.wait = wait;
        this.js = new JavaScriptUtil(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[@class='header__menu-icon']")
    WebElement sidebar;

    @FindBy(xpath = "//div[@role='switch']//span[2][@theme='light']")
    WebElement toggleBtn;

    @FindBy(xpath = "//a[@title='Home']")
    WebElement homeBtn;


    public boolean openSideBar(){

        commonCode.safeClickToWebElement(sidebar);

        js.scrollIntoView(toggleBtn);
        commonCode.safeClickToWebElement(toggleBtn);

        wait.until(ExpectedConditions.visibilityOf(homeBtn));
        commonCode.clickWhenClickable(homeBtn);

        TakeScreenShot ts = new TakeScreenShot(driver, "screenshots");
        ts.take("TC_05");
        return true;
    }
}
