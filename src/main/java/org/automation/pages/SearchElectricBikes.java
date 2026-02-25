
package org.automation.pages;

import org.automation.utility.ExcelUtil;
import org.automation.utility.JavaScriptUtil;
import org.automation.utility.TakeScreenShot;
import org.automation.utility.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SearchElectricBikes {
    private WebDriver driver;
    private WaitUtils wait;
    private JavaScriptUtil j;
    private TakeScreenShot screenshot;

    public SearchElectricBikes(WebDriver driver,WaitUtils wait) {
        this.driver = driver;
        this.wait = wait;
        this.j = new JavaScriptUtil(driver);
        this.screenshot = new TakeScreenShot(driver, "screenshots");
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = ".header__menu-icon")
    private WebElement menuIcon;

    @FindBy(css = "a[title='Electric Bikes'] span[class='o-jr o-jJ o-j3 o-cE']")
    private WebElement electricBikes;

    @FindBy(css = "div[data-display-name='Brand'] span[class='o-jK']")
    private WebElement brand;

    @FindBy(css = "div[aria-hidden='false'] div ul li div input[value='1']")
    private WebElement bajajInputBtn;

    @FindBy(css = "input[value='79']")
    private WebElement atherInputBtn;

    @FindBy(xpath = "//p[normalize-space()='Start Type']")
    private WebElement startType;

    @FindBy(css = "input[value='853']")
    private WebElement electricStart;

    @FindBy(xpath = "//p[normalize-space()='Body Style']")
    private WebElement bodyStyle;

    @FindBy(xpath = "(//div[contains(@class,'o-aw o-aE o-kJ rt2FdX o-f')])[2]")
    private WebElement scooter;

    @FindBy(xpath = "//p[normalize-space()='Brake Type']")
    private WebElement brakeType;

    @FindBy(css = "input[value='911']")
    private WebElement disc;

    @FindBy(xpath = "//p[normalize-space()='Wheels']")
    private WebElement wheels;

    @FindBy(css = "input[value='846']")
    private WebElement alloy;

    @FindBy(xpath = "//p[normalize-space()='Top Speed']")
    private WebElement topSpeed;

    @FindBy(xpath = "(//input[contains(@value,'2')])[23]")
    private WebElement above25;

    @FindBy(css = "button[data-testid='filter-popup-apply-button-inner']")
    private WebElement applyFilters;

    public boolean SearchElectricBikes() throws InterruptedException {
        wait.clickable(menuIcon).click();
        wait.clickable(electricBikes).click();

        wait.clickable(brand).click();

        wait.presence(By.cssSelector("input[value='1']"));
        j.clickByJS(bajajInputBtn);

        wait.presence(By.cssSelector("input[value='79']"));
        j.clickByJS(atherInputBtn);

        wait.presence(By.xpath("//p[normalize-space()='Start Type']"));
        j.clickByJS(startType);

        wait.presence(By.cssSelector("input[value='853']"));
        j.clickByJS(electricStart);

        j.scrollIntoView(bodyStyle);
        wait.presence(By.xpath("//p[normalize-space()='Body Style']"));
        j.clickByJS(bodyStyle);

        wait.presence(By.xpath("(//div[contains(@class,'o-aw o-aE o-kJ rt2FdX o-f')])[2]"));
        j.clickByJS(scooter);

        wait.presence(By.cssSelector("button[data-testid='filter-popup-apply-button-inner']"));
        j.clickByJS(applyFilters);

        Thread.sleep(2000);
        screenshot.take("Successfully_Displayed_Bikes");
        List<String> Bikes = new ArrayList<>();
        String filePath = System.getProperty("user.dir") + "/Shared_News_Headlines.xlsx";
        String teamMember = "Sheet_2";

        By CARD = By.cssSelector("li.o-em.o-hk");
        By NAME = By.cssSelector("h3.o-c6");

        wait.visible(CARD);

        List<WebElement> bikeCards = driver.findElements(CARD);
        int count = Math.min(6, bikeCards.size());

        for (int i = 0; i < count; i++) {
            WebElement card = bikeCards.get(i);

//            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", card);
            j.scrollIntoView(card);

            String text = card.findElement(NAME).getText().trim();

            if (text.isEmpty()) {
                text = j.getInternalHeaderText(card);
            }
            if (text != null && !text.isEmpty()) {
                String cleanedName = text.replaceAll("\\s+", " ");
                System.out.println(cleanedName);

                Bikes.add(cleanedName);
            }

        }
        ExcelUtil.writeHeadlinesToExcel(Bikes, filePath, teamMember);
        Assert.assertTrue(Bikes.size() > 0, "No bikes found!");
        return true;
    }
}
