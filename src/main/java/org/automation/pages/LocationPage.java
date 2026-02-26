package org.automation.pages;


import org.automation.log.Log;
import org.automation.utility.JavaScriptUtil;
import org.automation.utility.TakeScreenShot;
import org.automation.utility.CommonCode;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LocationPage {

    WebDriver driver;
    CommonCode commonCode;
    JavaScriptUtil js;
    WebDriverWait wait;

    public LocationPage(WebDriver driver, WebDriverWait wait){
        this.driver=driver;
        this.wait = wait;
        this.commonCode = new CommonCode(driver, Duration.ofSeconds(20));
        this.js=new JavaScriptUtil(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div[@title='Location']")
    WebElement selectMap;

    @FindBy(xpath = "//div[@role='presentation']//div[2]//div[1]//div[1]//div[2]//div[1]//div[1]//div[1]//div[4]")
    WebElement city;

    @FindBy(xpath = "//div[contains(@class,'LLaAxM o-iD o-c6 o-em o-fs o-cE')]//div[1]//ul[1]//li[5]")
    WebElement pcmc;

    @FindBy(xpath = "//body[1]/div[2]/div[1]/div[2]/div[2]/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]")
    WebElement mostTrendingBike;

    public boolean mapClick(){

        commonCode.safeClickToWebElement(selectMap);
        commonCode.clickWhenClickable(city);
        commonCode.clickWhenClickable(pcmc);
        commonCode.safeClickToWebElement(mostTrendingBike);

        try{
            Thread.sleep(500);
            TakeScreenShot ts = new TakeScreenShot(driver,"screenshots");
            ts.take("TC_04");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Log.info("\n=========== Pune's Most Favourite Bike ===========\n");

        String text = mostTrendingBike.getText().trim().replaceAll("\n{2,}", "\n"); // collapse extra blanks
        System.out.println(text);
        System.out.println("----------------------------------------------");

        // Return true if text is not empty (success)
        return !text.isEmpty();
    }
}
