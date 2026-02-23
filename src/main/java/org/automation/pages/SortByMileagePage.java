package org.automation.pages;

import org.automation.utility.JavaScriptUtil;
import org.automation.utility.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SortByMileagePage {

    private final WebDriver driver;
    private final WaitUtils wait;
    private final JavaScriptUtil js;

    public SortByMileagePage(WebDriver driver , WaitUtils wait){
        this.driver = driver;
        this.wait = wait;
        this.js = new JavaScriptUtil(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[@data-testing-id='mileage-tab' and contains(text(),'Best Mileage Bikes')]")
    WebElement bestMileageBike;

    @FindBy(xpath = "//div[@data-skin='text' and contains(text(), 'All Mileage Bikes')]")
    WebElement allMileageBikes;

    public void sortBikesByMileage(){

        try{
        wait.pageReady();

        js.scrollIntoView(bestMileageBike);
        Thread.sleep(2000);
        wait.clickWhenClickable(bestMileageBike);

        Thread.sleep(2000);
        wait.clickWhenClickable(allMileageBikes);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




}
