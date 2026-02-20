package org.automation.pages;

import org.automation.utility.JavaScriptUtil;
import org.automation.utility.TakeScreenShot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class OnRoadPrice  {

    WebDriver driver;
    WebDriverWait wait;
    JavaScriptUtil js;

    public OnRoadPrice(WebDriver driver, WebDriverWait wait){
        this.wait=wait;
        this.driver=driver;
        this.js=new JavaScriptUtil(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath ="//span[@class='header__menu-icon']")
    WebElement options;
    @FindBy(xpath = "//span[text()='New Bikes' and @class='o-jr o-jJ o-j3 o-cE']")
    WebElement new_Bikes;
    @FindBy(xpath = "//span[text()='Check On-Road Price']")
    WebElement check_On_Road_Price;
    @FindBy(id = "ddlMake")
    WebElement brandSelect;
    @FindBy(id="ddlModel")
    WebElement modelSelect;
    @FindBy(xpath = "//span[text()='--Select City--']")
    WebElement citySelect;
    @FindBy(xpath = "//li[@class='no-results']")
    WebElement no_Result;
    @FindBy(xpath = "//li[@class='active-result']")
    List<WebElement> cities;
    @FindBy(xpath = "//input[@value='Check On-Road Price']")
    WebElement chequeButton;


    public String chequeAvailability(String cityName) {
        options.click();
        js.clickByJS(new_Bikes);
        js.clickByJS(check_On_Road_Price);
        new Select(brandSelect).selectByVisibleText("Honda");
        wait.until(ExpectedConditions.visibilityOfAllElements(modelSelect));
        new Select(modelSelect).selectByVisibleText("Shine");
        citySelect.click();
        String cityToSelect = cityName;
        wait.until(ExpectedConditions.visibilityOfAllElements(cities));
        for (WebElement city : cities) {
            try {
                if (city.getText().equals(cityToSelect)) {
                    city.click();
                    break;
                }
            } catch (Exception e) {
                continue;
            }
        }
        js.clickByJS(chequeButton);
        String title=driver.getTitle();
        TakeScreenShot ts = new TakeScreenShot(driver, "screenshots");
        ts.take("Availability of the bike");
        return title;
    }
}
