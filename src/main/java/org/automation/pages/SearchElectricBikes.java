
package org.automation.pages;

import org.automation.log.Log;
import org.automation.utility.CommonCode;
import org.automation.utility.JavaScriptUtil;
import org.automation.utility.TakeScreenShot;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SearchElectricBikes {
    private WebDriver driver;
    private WebDriverWait wait;
    private CommonCode code;
    private JavaScriptUtil js;
    private TakeScreenShot screenshot;

    public SearchElectricBikes(WebDriver driver,WebDriverWait wait) {
        this.driver = driver;
        code=new CommonCode(driver,Duration.ofSeconds(20));
        this.screenshot = new TakeScreenShot(driver, "screenshots");
        this.wait=wait;
        js=new JavaScriptUtil(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = ".header__menu-icon")
    private WebElement menuIcon;

    @FindBy(css = "a[title='Electric Bikes'] span[class='o-jr o-jJ o-j3 o-cE']")
    private WebElement electricBikesLnk;

    @FindBy(css = "div[data-display-name='Brand'] span[class='o-jK']")
    private WebElement brandChk;

    @FindBy(xpath = "//label[.//span[normalize-space(.)='Bajaj']]//input[@type='checkbox']")
    private WebElement bajajInputChk;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[11]/div[2]/div[2]/div/ul/li[1]/div/div[2]/div/ul[2]/li[3]/div/label/span[1]/input")
    private WebElement atherInputChk;

    @FindBy(xpath = "//p[normalize-space()='Start Type']")
    private WebElement startTypeBtn;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[11]/div[2]/div[2]/div/ul/li[2]/div/div[2]/div/ul/li[1]/div/label/span[1]/input")
    private WebElement electricStartChk;

    @FindBy(xpath = "//p[normalize-space()='Body Style']")
    private WebElement bodyStyleBtn;

    @FindBy(xpath = "(//div[contains(@class,'o-aw o-aE o-kJ rt2FdX o-f')])[2]")
    private WebElement scooterChk;

    @FindBy(xpath = "//p[normalize-space()='Brake Type']")
    private WebElement brakeTypeBtn;

    @FindBy(css = "//*[@id=\"root\"]/div/div[2]/div/div[11]/div[2]/div[2]/div/ul/li[5]/div/div[2]/div/ul/li[1]/div/label/span[1]/input")
    private WebElement discChk;

    @FindBy(xpath = "//p[normalize-space()='Wheels']")
    private WebElement wheelsBtn;

    @FindBy(css = "//*[@id=\"root\"]/div/div[2]/div/div[11]/div[2]/div[2]/div/ul/li[7]/div/div[2]/div/ul/li[1]/div/label/span[1]/input")
    private WebElement alloyChk;

    @FindBy(xpath = "//p[normalize-space()='Top Speed']")
    private WebElement topSpeedBtn;

    @FindBy(xpath = "(//input[contains(@value,'2')])[23]")
    private WebElement above25Chk;

    @FindBy(css = "button[data-testid='filter-popup-apply-button-inner']")
    private WebElement applyFiltersBtn;

    @FindBy(xpath = "//div[contains(@class,'o-f o-hl o-aE')]//h3[@class='o-c6']/span")
    private List<WebElement> bikeResults;

    public List<String> SearchElectricBikes() throws InterruptedException {
        code.clickable(menuIcon).click();
        code.clickable(electricBikesLnk).click();
        code.clickable(brandChk).click();

        Thread.sleep(500);
        js.clickByJS(bajajInputChk);

        js.clickByJS(atherInputChk);

        js.scrollIntoView(startTypeBtn);
        js.clickByJS(startTypeBtn);

        js.clickByJS(electricStartChk);

        js.scrollIntoView(bodyStyleBtn);
        js.clickByJS(bodyStyleBtn);

        js.clickByJS(scooterChk);

        js.clickByJS(applyFiltersBtn);

        List<String> Bikes = new ArrayList<>();

        // Final Wait for results to update
        Thread.sleep(5000);
        screenshot.take("TC_15_Final_Bike_Results");

        Log.info("Bikes Found: " + bikeResults.size());
        for (int i = 0; i < Math.min(6, bikeResults.size()); i++) {
            String ele=bikeResults.get(i).getText().trim();
            Log.info((i + 1) + ". " + ele);
            Bikes.add(ele);
        }

        return Bikes;
    }
}

