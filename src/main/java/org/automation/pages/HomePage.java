package org.automation.pages;
import org.automation.utility.CommonCode;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.automation.utility.JavaScriptUtil;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class HomePage {
    WebDriver driver;
    CommonCode commonCode;
    JavaScriptUtil js;
    WebDriverWait wait;

    public HomePage(WebDriver driver,WebDriverWait wait){
        this.driver=driver;
        this.wait = wait;
        this.commonCode = new CommonCode(driver, Duration.ofSeconds(20));
        this.js=new JavaScriptUtil(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath ="//span[@class='header__menu-icon']")
    WebElement menuIcon;
    @FindBy(xpath = "//span[text()='New Bikes' and @class='o-jr o-jJ o-j3 o-cE']")
    WebElement newBikesLnk;
    @FindBy(xpath = "//span[text()='Upcoming Bikes']")
    WebElement upcomingBikeLnk;
    @FindBy(xpath = "//input[@placeholder='All Brands']")
    WebElement AllBrandOpt;
    @FindBy(xpath = "//ul[@data-screen='make-model']/li//span//span")
    List<WebElement> brands;

    public int findUpcomingBikes(){

        int bikeCount = 0;

        commonCode.clickWhenClickable(menuIcon);
        commonCode.clickWhenClickable(newBikesLnk);
        commonCode.safeClickToWebElement(upcomingBikeLnk);
        commonCode.clickWhenClickable(AllBrandOpt);

        for(WebElement e:brands){
            bikeCount++;
            if(e.getText().equalsIgnoreCase("Royal Enfield")){
                commonCode.safeClickToWebElement(e);
            }
        }

        return bikeCount;
    }
}
