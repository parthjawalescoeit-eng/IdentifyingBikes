package org.automation.pages;

import org.automation.utility.JavaScriptUtil;
import org.automation.utility.TakeScreenShot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

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
    WebElement compare;
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
    WebElement specs;

    public void compare() throws InterruptedException {
        js.clickByJS(compare);
        wait.until(
                ExpectedConditions.visibilityOf(addBike1)
        );
        js.clickByJS(addBike1);

        wait.until(
                ExpectedConditions.visibilityOf(brand)
        );
        js.clickByJS(brand);

        js.clickByJS(bike1);
        wait.until(
                ExpectedConditions.visibilityOf(bike1Variant)
        );
        js.clickByJS(bike1Variant);
        wait.until(
                ExpectedConditions.visibilityOf(addBike2)
        );
        addBike2.click();
        wait.until(
                ExpectedConditions.visibilityOf(brand)
        );
        brand.click();
        Thread.sleep(500);
        js.clickByJS(bike2);
        wait.until(
                ExpectedConditions.visibilityOf(bike2Variant)
        );
        Thread.sleep(500);
        bike2Variant.click();
        wait.until(
                ExpectedConditions.visibilityOf(specs)
        );

        js.executeScript(
                "arguments[0].scrollIntoView();",specs
        );
        js.clickByJS(specs);
        Thread.sleep(500);
        boolean compare = driver.getTitle().contains("Know Which Is Better");
        Assert.assertTrue(compare,"Chosen Bikes Not Loaded Successfully");
        TakeScreenShot ts=new TakeScreenShot(driver,"screenshots");
        ts.take("Compared Stats");

    }

}
