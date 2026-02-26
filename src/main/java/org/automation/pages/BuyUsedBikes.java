package org.automation.pages;

import org.automation.logs.Log;
import org.automation.utility.JavaScriptUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class BuyUsedBikes {
    WebDriver driver;
    JavaScriptUtil js;
    WebDriverWait wait;

    private String name;
    private String price;

    public BuyUsedBikes(WebDriver driver, WebDriverWait wait){
        this.driver=driver;
        this.js=new JavaScriptUtil(driver);
        this.wait=wait;
        PageFactory.initElements(driver, this);
    }
    public BuyUsedBikes(String name, String price){
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public String getPrice() { return price; }

    @FindBy(xpath="//span[@class='header__menu-icon']")
    WebElement sideBar;

    @FindBy(xpath = "//div[@title='Used Bikes']")
    WebElement usedBikeBtn;

    @FindBy(xpath = "//a[@title='Buy Bikes']")
    WebElement buyBikesBtn;

    @FindBy(xpath="//div[@class='chosen-container chosen-container-single']")
    WebElement selectCity;

    @FindBy(xpath="//li[text()='Mumbai']")
    WebElement city;

    @FindBy(xpath="//*[@id='searchCityBudget']")
    WebElement searchBtn;

    @FindBy(xpath="//*[text()='Suzuki']")
    WebElement yammhaChk;

    @FindBy(xpath="//*[text()='Gixxer']")
    WebElement gizzerChk;

    public void clickSideBar() throws InterruptedException{
        wait.until(ExpectedConditions.elementToBeClickable(sideBar));
        js.clickByJS(sideBar);

        wait.until(ExpectedConditions.elementToBeClickable(usedBikeBtn));
        js.clickByJS(usedBikeBtn);

        wait.until(ExpectedConditions.elementToBeClickable(buyBikesBtn));
        js.clickByJS(buyBikesBtn);

        wait.until(ExpectedConditions.elementToBeClickable(selectCity));
        selectCity.click();

        wait.until(ExpectedConditions.elementToBeClickable(city));
        js.clickByJS(city);

        wait.until(ExpectedConditions.elementToBeClickable(searchBtn));
        js.clickByJS(searchBtn);

        wait.until(ExpectedConditions.elementToBeClickable(yammhaChk));
        js.clickByJS(yammhaChk);

        wait.until(ExpectedConditions.elementToBeClickable(gizzerChk));
        js.clickByJS(gizzerChk);

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
        wait.until(ExpectedConditions.visibilityOf(bikeNames.get(0)));

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
