package org.automation.pages;

import org.automation.logs.Log;
import org.automation.utility.JavaScriptUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.automation.utility.WaitUtil;
import java.util.ArrayList;
import java.util.List;

import static org.automation.utility.WaitUtil.waitForElementClickable;

public class BuyUsedBikes {
    WebDriver driver;
    JavaScriptUtil js;
    WebDriverWait wait;

    String name;
    String price;

    public BuyUsedBikes(WebDriver driver,WebDriverWait wait){
        this.driver=driver;
        this.js=new JavaScriptUtil(driver);
        this.wait=wait;
        PageFactory.initElements(driver, this);
    }
    public BuyUsedBikes(String name, String price){
        this.name = name;
        this.price = price;
    }

    @FindBy(xpath="//span[@class='header__menu-icon']")
    WebElement sideBar;

    @FindBy(xpath = "//div[@title='Used Bikes']")
    WebElement usedBikes;

    @FindBy(xpath = "//a[@title='Buy Bikes']")
    WebElement buyBikes;

    @FindBy(xpath="//div[@class='chosen-container chosen-container-single']")
    WebElement selectCity;

    @FindBy(xpath="//li[text()='Mumbai']")
    WebElement city;

    @FindBy(xpath="//*[@id='searchCityBudget']")
    WebElement searchBtn;

    @FindBy(xpath="//*[text()='Suzuki']")
    WebElement brand;

    @FindBy(xpath="//*[text()='Gixxer']")
    WebElement gizzer;

    public void clickSideBar() throws InterruptedException{
        WebElement sideBars=wait.until(ExpectedConditions.elementToBeClickable(sideBar));
        Assert.assertNotNull(sideBars);
        sideBars.click();

        WebElement used_Bikes=wait.until(ExpectedConditions.elementToBeClickable(usedBikes));
        Assert.assertNotNull(used_Bikes);
        used_Bikes.click();

        WebElement buy_Bikes=wait.until(ExpectedConditions.elementToBeClickable(buyBikes));
        Assert.assertNotNull(buy_Bikes);
        buy_Bikes.click();

        WebElement dropdown=wait.until(ExpectedConditions.elementToBeClickable(selectCity));
        Assert.assertNotNull(dropdown);
        dropdown.click();

        WebElement selectCity=wait.until(ExpectedConditions.elementToBeClickable(city));
        Assert.assertNotNull(selectCity);
        selectCity.click();

        WebElement button=wait.until(ExpectedConditions.elementToBeClickable(searchBtn));
        Assert.assertNotNull(button);
        button.click();

        WebElement brandSuzuki=wait.until(ExpectedConditions.elementToBeClickable(brand));
        Assert.assertNotNull(brandSuzuki);
        brandSuzuki.click();

        WebElement brandGizzer=wait.until(ExpectedConditions.elementToBeClickable(gizzer));
        Assert.assertNotNull(brandGizzer);
        brandGizzer.click();

    }
    @FindBy(css=".o-o.FTJfQA")
    private List<WebElement> bikeNames;

    // List of Bike Prices
    @FindBy(css= "div.o-f.o-ek")
    private List<WebElement> bikePrices;

    @Override
    public String toString() {
        return "Bike: " + name + " | Price: " + price;
    }

    public List<BuyUsedBikes> getBikeResults() {
        List<BuyUsedBikes> allBikes = new ArrayList<>();

        // Wait for the first element to be visible to ensure list is loaded
        WaitUtil.waitForElementVisible(driver, bikeNames.get(0));

        // Loop through the names and pair them with prices
        for (int i = 0; i < bikeNames.size(); i++) {
            String name = bikeNames.get(i).getText().trim();
            String price = bikePrices.get(i).getText().trim();

            // Store in our list
            allBikes.add(new BuyUsedBikes(name, price));

            // Print to console
            Log.info("Found Bike " + (i + 1) + ": " + name + " -> " + price);
        }

        return allBikes;
    }


}
