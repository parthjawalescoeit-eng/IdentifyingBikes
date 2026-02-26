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

public class SortByMileagePage {

    private final WebDriver driver;
    private final CommonCode commonCode;
    private final JavaScriptUtil js;
    private final WebDriverWait wait;

    public SortByMileagePage(WebDriver driver , WebDriverWait wait){
        this.driver = driver;
        this.commonCode = new CommonCode(driver, Duration.ofSeconds(20));
        this.wait = wait;
        this.js = new JavaScriptUtil(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[@data-testing-id='mileage-tab' and contains(text(),'Best Mileage Bikes')]")
    WebElement bestMileageBike;

    @FindBy(xpath = "//div[@data-skin='text' and contains(text(), 'All Mileage Bikes')]")
    WebElement allMileageBikes;

    @FindBy(xpath = "(//div[contains(@class,'o-az o-hl o-f')])//div[10]")
    WebElement bikeWithHighestMilage;

    public boolean sortBikesByMileage(){

        try{

        js.scrollIntoView(bestMileageBike);
        // wait until the element is clickable and click (replaces Thread.sleep)

        Thread.sleep(500);
        commonCode.safeClickToWebElement(bestMileageBike);

        // wait and click the 'All Mileage Bikes' tab
        Thread.sleep(500);
        commonCode.safeClickToWebElement(allMileageBikes);

        Thread.sleep(500);
        commonCode.safeClickToWebElement(bikeWithHighestMilage);

        TakeScreenShot ts = new TakeScreenShot(driver, "screenshots");
        ts.take("TC_03");


            Log.info("\n=========== Bike With Highest Mileage ===========\n");

                String text = bikeWithHighestMilage.getText().trim().replaceAll("\\n{2,}", "\n"); // collapse extra blanks
                System.out.println("The Beast is here");
                System.out.println(text);
                System.out.println("----------------------------------------------");

        return true;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
