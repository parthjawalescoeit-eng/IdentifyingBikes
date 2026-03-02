package org.automation.pages;

import org.automation.log.Log;
import org.automation.utility.JavaScriptUtil;
import org.automation.utility.TakeScreenShot;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SimilarBikes {
    WebDriver driver;
    WebDriverWait wait;
    JavaScriptUtil js;

    public SimilarBikes(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait=wait;
        this.js = new JavaScriptUtil(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@type='text' and @placeholder='Search']")
    WebElement search;

    @FindBy(xpath = "//div//li[@class='o-C o-cC o-c6  o-kY o-os FO7dpx HP_G3h  o-bS o-co o-mo o-m1 o-f o-aM o-aD o-mO']")
    WebElement clikk;

    @FindBy(xpath = "//div[text()='Similar Street Bikes']")
    WebElement similar;

    public void similarSearch() throws InterruptedException {
        search.sendKeys("Kawasaki Z650");
        wait.until(ExpectedConditions.visibilityOf(clikk));
        js.clickByJS(clikk);
        Log.info("Searched Bike");

        wait.until(ExpectedConditions.visibilityOf(similar));
        js.clickByJS(similar);
        Log.info("Similar Bikes Retrieved");

        Thread.sleep(2500);
        TakeScreenShot ts = new TakeScreenShot(driver, "screenshots");
        ts.take("TC_19");
        Log.info("Screenshot Taken Successfully");
    }



}

