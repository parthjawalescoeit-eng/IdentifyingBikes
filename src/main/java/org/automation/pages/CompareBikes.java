package org.automation.pages;

import org.automation.log.Log;
import org.automation.utility.JavaScriptUtil;
import org.automation.utility.TakeScreenShot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CompareBikes {

    WebDriver driver;
    WebDriverWait wait;
    JavaScriptUtil js;

    public CompareBikes(WebDriver driver,WebDriverWait wait){
        this.wait=wait;
        this.driver=driver;
        this.js=new JavaScriptUtil(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//div[text()='Compare Bikes of Your Choice']")
    WebElement compareBtn;

    @FindBy(xpath = "(//div[@class='o-aR o-aT o-aU o-aw o-aL o-aE o-os o-bA  o-f'])[1]")
    WebElement addBike1;

    @FindBy(xpath = "(//div[@class='o-aR o-aT o-aU o-aw o-aL o-aE o-os o-bA  o-f'])[2]")
    WebElement addBike2;

    @FindBy(xpath = "//span[text()='Royal Enfield']")
    WebElement brand;

    @FindBy(xpath = "//li[@id='1086' and @name='Continental GT 650']")
    WebElement bike1;

    @FindBy(xpath = "//div[@class='o-c8 OXSm67']//p[text()='Alloy Wheel']")
    WebElement bike1Variant;

    @FindBy(xpath = "//li[@id='2209' and @name='Guerrilla 450']")
    WebElement bike2;

    @FindBy(xpath = "//div[@class='o-c8 OXSm67']//p[text()='Flash']")
    WebElement bike2Variant;

    @FindBy(xpath = "//h4[@class='o-j5 o-jq ' and text()='Power & Performance']")
    WebElement specsBtn;

    public void compare() throws InterruptedException {
        js.clickByJS(compareBtn);

        wait.until(ExpectedConditions.visibilityOf(addBike1));
        Thread.sleep(500);
        js.clickByJS(addBike1);
        Log.info("First Bike Added");

        wait.until(ExpectedConditions.visibilityOf(brand));
        Log.info("Selected Brand For First Bike");
        js.clickByJS(brand);

        js.clickByJS(bike1);
        Log.info("Choosed First Bike");

        wait.until(ExpectedConditions.visibilityOf(bike1Variant));
        js.clickByJS(bike1Variant);
        Log.info("Choosed Variant For First Bike");

        wait.until(ExpectedConditions.visibilityOf(addBike2));
        Thread.sleep(500);
        addBike2.click();
        Log.info("Second Bike Added");

        wait.until(ExpectedConditions.visibilityOf(brand));
        brand.click();
        Log.info("Selected Brand For Second Bike");
        Thread.sleep(500);
        js.clickByJS(bike2);
        Log.info("Choosed Second Bike");

        wait.until(ExpectedConditions.visibilityOf(bike2Variant));
        Thread.sleep(500);
        bike2Variant.click();
        Log.info("Choosed variant For Second Bike");

        wait.until(ExpectedConditions.visibilityOf(specsBtn));
        js.executeScript("arguments[0].scrollIntoView();", specsBtn);
        js.clickByJS(specsBtn);
        Log.info("Showing Specs Of Bikes");

        Thread.sleep(500);
        TakeScreenShot ts=new TakeScreenShot(driver,"screenshots");
        ts.take("TC_17");
        Log.info("Screenshot Taken Successfully");
    }
}
