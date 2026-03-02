package org.automation.pages;

import org.automation.log.Log;
import org.automation.utility.JavaScriptUtil;
import org.automation.utility.TakeScreenShot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BodyStyle {

    WebDriver driver;
    WebDriverWait wait;
    JavaScriptUtil js;

    public BodyStyle(WebDriver driver, WebDriverWait wait){
        this.driver=driver;
        this.wait=wait;
        this.js=new JavaScriptUtil(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div[@class='o-C o-o o-oz o-os YF8OI6   o-hl']//span[text()='Body Style']")
    WebElement bodyStyle;

    @FindBy(xpath = "//div[text()='Cafe Racer']")
    WebElement type;

    @FindBy(xpath = "//h2[@class='o-kD o-js o-j7 o-jK']")
    WebElement helper;

    @FindBy(xpath = "//span[@class='o-jJ o-j3 o-js o-ez']")
    List<WebElement> bikeLst;

    public void getAllBikes() throws InterruptedException {

        js.executeScript("arguments[0].scrollIntoView(false);",bodyStyle);
        wait.until(ExpectedConditions.elementToBeClickable(bodyStyle));
        Thread.sleep(800);
        js.clickByJS(bodyStyle);


        Thread.sleep(500);
        wait.until(ExpectedConditions.visibilityOf(type));
        js.clickByJS(type);
        Log.info("Scrolling for View");

        Thread.sleep(500);
        js.scrollIntoView(helper);
        Log.info("Scrolled to Bikes");

        Thread.sleep(500);
        TakeScreenShot ts=new TakeScreenShot(driver,"screenshots");
        ts.take("TC_20");
        Log.info("Screenshot taken successfully");

        List<WebElement> lst = wait.until(ExpectedConditions.visibilityOfAllElements(bikeLst));
        for(WebElement w : lst){
            System.out.println(w.getText());
            Log.info("Bike Name - "+w.getText().toLowerCase());
        }
    }


}
