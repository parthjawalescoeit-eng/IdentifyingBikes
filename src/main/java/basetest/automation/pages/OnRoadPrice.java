package basetest.automation.pages;

import basetest.automation.logs.Log;
import basetest.automation.utility.CommonCode; // Import CommonCode
import basetest.automation.utility.TakeScreenShot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OnRoadPrice {

    WebDriver driver;
    WebDriverWait wait;
    CommonCode common; // Declare CommonCode

    public OnRoadPrice(WebDriver driver, WebDriverWait wait) {
        this.wait = wait;
        this.driver = driver;
        this.common = new CommonCode(driver, Duration.ofSeconds(10)); // Initialize
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
        common.selectByText(brandSelect, "Honda");
        wait.until(ExpectedConditions.visibilityOf(firstActualModel));
        common.selectByPartialText(modelSelect, "Shine");
        common.clickWhenClickable(cityDropdownContainer);
        common.enterText(citySearchInput, invalidCity);
        wait.until(ExpectedConditions.visibilityOf(no_Result));
        String errorMsg = no_Result.getText();
        new TakeScreenShot(driver, "screenshots").take("InvalidCityError");
        Log.info("Msg: " +errorMsg);
        return errorMsg;
    }

    public String chequeAvailability(String cityName) {
        navigateToOnRoadPriceSection();
        common.selectByText(brandSelect, "Honda");
        wait.until(ExpectedConditions.visibilityOf(firstActualModel));
        common.selectByPartialText(modelSelect, "Shine");
        selectCity(cityName);
        common.safeClickToWebElement(chequeButton);
        wait.until(ExpectedConditions.not(ExpectedConditions.titleContains("On Road Price")));
        String screenshotName = "Availability_Success_" + cityName;
        new TakeScreenShot(driver, "screenshots").take(screenshotName);
        Log.info("Availability Status: " + screenshotName);
        return driver.getTitle();
    }

    private void navigateToOnRoadPriceSection() {
        common.clickWhenClickable(options);
        common.safeClickToWebElement(new_Bikes);
        common.safeClickToWebElement(check_On_Road_Price);
    }

    private void selectCity(String cityName) {
        common.clickWhenClickable(cityDropdownContainer);
        common.enterText(citySearchInput, cityName);
        common.clickWhenClickable(firstCitySuggestion);
    }
}