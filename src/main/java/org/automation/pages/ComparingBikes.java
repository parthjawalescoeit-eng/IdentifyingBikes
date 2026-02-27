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

public class ComparingBikes {

    WebDriver driver;
    WebDriverWait wait;
    JavaScriptUtil js;

    public ComparingBikes(WebDriver driver,WebDriverWait wait){
        this.driver=driver;
        this.wait=wait;
        this.js=new JavaScriptUtil((driver));
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//a[@title='Hero' and @class='o-d o-os o-kY ']")
    WebElement brandLnk;

    @FindBy(xpath="(//div[text()='Top Comparisons'])[2]")
    WebElement compareBtn;

    @FindBy(xpath="(//div[@class='o-f o-av o-j1 o-js o-co o-c6 o-cC'])[1]")
    WebElement bikeLnk;

    @FindBy(xpath="(//div[@class='o-kg o-j3 o-ei'])[1]")
    WebElement plusBtn;

    @FindBy(xpath="(//div[@class='o-ez wZQV4N o-aS'])[2]")
    WebElement bike3Lnk;

    @FindBy(xpath="(//li[contains(@class,'BFykSq')])[2]")
    WebElement variant;

    public void compareBike() throws InterruptedException{

        wait.until(ExpectedConditions.elementToBeClickable(brandLnk));
        js.clickByJS(brandLnk);
        Thread.sleep(2000);

        wait.until(ExpectedConditions.elementToBeClickable(compareBtn));
        js.scrollIntoView(compareBtn);
        js.clickByJS(compareBtn);
        Thread.sleep(2000);

        wait.until(ExpectedConditions.elementToBeClickable(bikeLnk));
        bikeLnk.click();

        wait.until(ExpectedConditions.elementToBeClickable(plusBtn));
        js.clickByJS(plusBtn);

        wait.until(ExpectedConditions.elementToBeClickable(bike3Lnk));
        js.clickByJS(bike3Lnk);

        wait.until(ExpectedConditions.elementToBeClickable(variant));
        variant.click();

        String screenshotName = "TC_23_ComparingBikes";
        new TakeScreenShot(driver, "screenshots").take(screenshotName);
    }
}
