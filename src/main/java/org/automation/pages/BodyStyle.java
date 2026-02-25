package org.automation.pages;

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

    @FindBy(xpath = "(//div[@class='o-C o-o o-oz o-os YF8OI6   o-hl'])[8]")
    WebElement bodyStyle;
    @FindBy(xpath = "//div[text()='Cafe Racer']")
    WebElement type;
    @FindBy(xpath = "//h2[@class='o-kD o-js o-j7 o-jK']")
    WebElement scroll;

    public void getAllBikes() throws InterruptedException {

        WebElement body_style=wait.until(ExpectedConditions.elementToBeClickable(bodyStyle));
        js.clickByJS(body_style);
        js.clickByJS(type);
        Thread.sleep(500);
        js.scrollIntoView(scroll);
        Thread.sleep(500);
        TakeScreenShot ts=new TakeScreenShot(driver,"screenshots");
        ts.take("Same Body Style");

        List<WebElement> lst = driver.findElements(By.xpath("//span[@class='o-jJ o-j3 o-js o-ez']"));

        for(WebElement w : lst){
            System.out.println(w.getText());
        }
    }


}
