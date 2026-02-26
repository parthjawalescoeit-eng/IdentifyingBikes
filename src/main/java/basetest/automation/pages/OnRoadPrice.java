package basetest.automation.pages;

import basetest.automation.logs.Log;
import basetest.automation.utility.JavaScriptUtil;
import basetest.automation.utility.TakeScreenShot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    @FindBy(xpath = "//select[@id='ddlModel']/option[2]")
    WebElement firstActualModel;

    @FindBy(id = "ddlCity_chosen")
    WebElement cityDropdownContainer;
    @FindBy(xpath = "//li[@class='no-results']")
    WebElement no_Result;
    @FindBy(xpath = "//div[@id='ddlCity_chosen']//div[@class='chosen-search']//input")
    WebElement citySearchInput;
    @FindBy(xpath = "//li[contains(@class,'active-result')]")
    WebElement firstCitySuggestion;
    @FindBy(xpath = "//input[@id='btnCheckOnRoadPrice' or @value='Check On-Road Price']")
    WebElement chequeButton;

    public String checkInvalidCity(String invalidCity) {
        navigateToOnRoadPriceSection();
        new Select(brandSelect).selectByVisibleText("Honda");
        wait.until(ExpectedConditions.visibilityOf(firstActualModel));
        selectModelByPartialText("Shine");
        wait.until(ExpectedConditions.elementToBeClickable(cityDropdownContainer)).click();
        wait.until(ExpectedConditions.visibilityOf(citySearchInput));
        citySearchInput.clear();
        citySearchInput.sendKeys(invalidCity);
        wait.until(ExpectedConditions.visibilityOf(no_Result));
        String errorMsg = no_Result.getText();
        new TakeScreenShot(driver, "screenshots").take("InvalidCityError");
        return errorMsg;
    }

    public String chequeAvailability(String cityName) {
        navigateToOnRoadPriceSection();
        new Select(brandSelect).selectByVisibleText("Honda");
        wait.until(ExpectedConditions.visibilityOf(firstActualModel));
        selectModelByPartialText("Shine");
        selectCity(cityName);
        js.clickByJS(chequeButton);
        wait.until(ExpectedConditions.not(ExpectedConditions.titleContains("On Road Price")));
        new TakeScreenShot(driver, "screenshots").take("Availability_Success");
        return driver.getTitle();
    }

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
    }

    private void selectCity(String cityName) {
        wait.until(ExpectedConditions.elementToBeClickable(cityDropdownContainer)).click();
        wait.until(ExpectedConditions.visibilityOf(citySearchInput));
        citySearchInput.sendKeys(cityName);
        wait.until(ExpectedConditions.elementToBeClickable(firstCitySuggestion));
        firstCitySuggestion.click();
    }
}