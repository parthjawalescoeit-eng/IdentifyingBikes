package org.automation.pages;

import org.automation.utility.JavaScriptUtil;
import org.automation.utility.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LocationPage {

    WebDriver driver;
    WaitUtils wait;
    JavaScriptUtil js;

    public LocationPage(WebDriver driver, WaitUtils wait){
        this.wait=wait;
        this.driver=driver;
        this.js=new JavaScriptUtil(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div[@title='Location']")
    WebElement selectMap;

    @FindBy(xpath = "//div[@role='presentation']//div[2]//div[1]//div[1]//div[2]//div[1]//div[1]//div[1]//div[4]")
    WebElement city;

    @FindBy(xpath = "//div[contains(@class,'LLaAxM o-iD o-c6 o-em o-fs o-cE')]//div[1]//ul[1]//li[5]")
    WebElement pcmc;

    @FindBy(xpath = "//body[1]/div[2]/div[1]/div[2]/div[2]/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]")
    WebElement mostTrendingBike;

    public void  mapClick(){

        wait.safeClickToWebElement(selectMap);

        wait.clickWhenClickable(city);

        wait.clickWhenClickable(pcmc);

        wait.safeClickToWebElement(mostTrendingBike);



    }


}
