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

import java.util.ArrayList;
import java.util.List;

public class OnRoadPrice {

    WebDriver driver;
    WebDriverWait wait;
    JavaScriptUtil js;

    public OnRoadPrice(WebDriver driver, WebDriverWait wait) {
        this.wait = wait;
        this.driver = driver;
        this.js = new JavaScriptUtil(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[@class='header__menu-icon']")
    WebElement options;

    @FindBy(xpath = "//span[text()='New Bikes']")
    WebElement new_Bikes;

    @FindBy(xpath = "//span[text()='Check On-Road Price']")
    WebElement check_On_Road_Price;

    @FindBy(id = "ddlMake")
    WebElement brandSelect;

    @FindBy(id = "ddlModel")
    WebElement modelSelect;
    @FindBy(id = "ddlCity_chosen")
    WebElement cityDropdownContainer;
    @FindBy(xpath = "//li[@class='no-results']")
    WebElement no_Result;
    @FindBy(xpath = "//div[@id='ddlCity_chosen']//div[@class='chosen-search']//input")
    WebElement citySearchInput;
    @FindBy(xpath = "//input[@id='btnCheckOnRoadPrice' or @value='Check On-Road Price']")
    WebElement chequeButton;

    public String chequeAvailability(String cityName) {
        navigateToOnRoadPriceSection();
        new Select(brandSelect).selectByVisibleText("Honda");
        wait.until(d -> new Select(modelSelect).getOptions().size() > 1);
        selectModelByPartialText("Shine");
        selectCity(cityName);

        js.clickByJS(chequeButton);
        wait.until(ExpectedConditions.not(ExpectedConditions.titleContains("On Road Price")));
        new TakeScreenShot(driver, "screenshots").take("Availability_Success");
        return driver.getTitle();
    }

    /**
     * Navigates and retrieves the error message for an invalid city.
     * Used in TC_07.
     */
    public String checkInvalidCity(String invalidCity) {
        navigateToOnRoadPriceSection();

        new Select(brandSelect).selectByVisibleText("Honda");
        wait.until(d -> new Select(modelSelect).getOptions().size() > 1);
        selectModelByPartialText("Shine");

        // Click to open city search
        wait.until(ExpectedConditions.elementToBeClickable(cityDropdownContainer)).click();

        // Enter invalid city name
        wait.until(ExpectedConditions.visibilityOf(citySearchInput));
        citySearchInput.clear();
        citySearchInput.sendKeys(invalidCity);

        // Wait for the specific "No results match" message
        wait.until(ExpectedConditions.visibilityOf(no_Result));
        String errorMsg = no_Result.getText();

        new TakeScreenShot(driver, "screenshots").take("InvalidCityError");
        return errorMsg;
    }

    // --- Private Helper Methods ---

    private void navigateToOnRoadPriceSection() {
        wait.until(ExpectedConditions.elementToBeClickable(options)).click();
        js.clickByJS(new_Bikes);
        js.clickByJS(check_On_Road_Price);
    }

    private void selectModelByPartialText(String partialText) {
        Select model = new Select(modelSelect);
        List<WebElement> optionsList = model.getOptions();
        for (WebElement option : optionsList) {
            if (option.getText().toLowerCase().contains(partialText.toLowerCase())) {
                model.selectByVisibleText(option.getText());
                return;
            }
        }

        // If not found, log available options for debugging
        List<String> available = new ArrayList<>();
        for(WebElement opt : optionsList) { available.add(opt.getText()); }
        System.out.println("Available models: " + available);
        throw new org.openqa.selenium.NoSuchElementException("Could not find model matching: " + partialText);
    }

    private void selectCity(String cityName) {
        // 1. Click to trigger search box
        wait.until(ExpectedConditions.elementToBeClickable(cityDropdownContainer)).click();

        // 2. Type city name
        wait.until(ExpectedConditions.visibilityOf(citySearchInput)).sendKeys(cityName);

        // 3. Click the result (Handling the <em> tag structure)
        By cityXpath = By.xpath("//li[contains(@class,'active-result')]//em[text()='" + cityName + "']");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(cityXpath)).click();
        } catch (Exception e) {
            // Fallback: Click the <li> using normalize-space if the <em> tag is not present
            By fallbackXpath = By.xpath("//li[contains(@class,'active-result')][normalize-space()='" + cityName + "']");
            js.clickByJS(driver.findElement(fallbackXpath));
        }
    }
}