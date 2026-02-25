package org.automation.pages;

import org.automation.utility.JavaScriptUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.PageFactoryFinder;
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
    WebElement brand;

    @FindBy(xpath="(//div[text()='Top Comparisons'])[2]")
    WebElement compare;

    @FindBy(xpath="(//div[@class='o-f o-av o-j1 o-js o-co o-c6 o-cC'])[1]")
    WebElement bike;

    @FindBy(xpath="(//div[@class='o-kg o-j3 o-ei'])[1]")
    WebElement plus;

    @FindBy(css="div[id='1280']")
    WebElement bike3;

    @FindBy(xpath="(//li[contains(@class,'BFykSq')])[2]")
    WebElement variant;

    public void compareBike() throws InterruptedException{

        WebElement brandBike=wait.until(ExpectedConditions.elementToBeClickable(brand));
        Assert.assertNotNull(brandBike);
        js.clickByJS(brandBike);
        Thread.sleep(2000);


        WebElement compareBike=wait.until(ExpectedConditions.elementToBeClickable(compare));
        js.scrollIntoView(compareBike);
        js.clickByJS(compareBike);
        Thread.sleep(2000);

        WebElement bikes=wait.until(ExpectedConditions.elementToBeClickable(bike));
        assert bikes != null;
        Assert.assertFalse(bikes.isSelected());
        bikes.click();

        WebElement addBike=wait.until(ExpectedConditions.elementToBeClickable(plus));
        Assert.assertNotNull(addBike);
        js.clickByJS(addBike);

        WebElement bike_3=wait.until(ExpectedConditions.elementToBeClickable(bike3));
        js.clickByJS(bike_3);

        WebElement variants=wait.until(ExpectedConditions.elementToBeClickable(variant));
        Assert.assertNotNull(variants);
        variants.click();









    }
}
