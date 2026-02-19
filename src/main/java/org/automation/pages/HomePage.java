package org.automation.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.automation.utility.JavaScriptUtil;

import java.util.List;


public class HomePage {
    WebDriver driver;
    WebDriverWait wait;
    JavaScriptUtil js;
    public HomePage(WebDriver driver, WebDriverWait wait){
        this.wait=wait;
        this.driver=driver;
        this.js=new JavaScriptUtil(driver);
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath ="//span[@class='header__menu-icon']")
    WebElement options;

    @FindBy(xpath = "//span[text()='New Bikes' and @class='o-jr o-jJ o-j3 o-cE']")
    WebElement New_Bikes;

    @FindBy(xpath = "//span[text()='Upcoming Bikes']")
    WebElement upcoming_bikes;

    @FindBy(xpath = "//div[text()='All Upcoming Bikes']")
    WebElement allBikes;

    @FindBy(xpath = "//input[@placeholder='All Brands']")
    WebElement selectBrand;

    @FindBy(xpath = "//ul[@data-screen='make-model']/li//span")
    List<WebElement> brandList;

    @FindBy(xpath = "//h3[@class='o-j4 o-jJ']")
    List<WebElement> bikeList;

    @FindBy(xpath = "//div[@class='o-jr o-j1 o-jK o-ei' and text()='Royal Enfield']")
    List<WebElement> bullet;

    public void test() throws InterruptedException{
        options.click();
        Thread.sleep(2000);
        js.clickByJS(New_Bikes);
        Thread.sleep(2000);
        js.clickByJS(upcoming_bikes);
        selectBrand.click();

        for(WebElement e:brandList){
            if(e.getText().equals("Royal Enfield")){
                js.clickByJS(e);
            }
        }

        for(WebElement e:bikeList){
            System.out.println(e.getText());
        }

    }

    void test1(){

    }
}
