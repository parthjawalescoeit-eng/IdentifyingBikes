package org.automation.pages;

import org.automation.logs.Log;
import org.automation.utility.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BikeProsAndCons {

    WebDriver driver;
    WebDriverWait wait;
    JavaScriptUtil js;

    public BikeProsAndCons(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.js = new JavaScriptUtil(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="(//div[@class='o-jq o-j4 o-jJ'])[1]")
    WebElement bike;

    @FindBy(xpath="//div[text()='Expert Opinion']")
    WebElement expertBtn;

    @FindBy(xpath="//li[@data-testid='pros-card' or @data-testid='cons-card']")
    public List<WebElement> pros;

    public void bikeColoursOption() throws InterruptedException {

        js.scrollIntoView(bike);
        String bikeModel=bike.getText();
        WebElement bikeClick=wait.until(ExpectedConditions.elementToBeClickable(bike));
        js.clickByJS(bikeClick);

        WebElement expert_btn=wait.until(ExpectedConditions.elementToBeClickable(expertBtn));
        js.clickByJS(expert_btn);

        Log.info("\n--- Bike Expert Review: Pros & Cons of "+bikeModel+"---");
        printProsAndCons();
    }

    public void printProsAndCons() {

        for (WebElement card : pros) {

            String header = card.findElement(By.tagName("h3")).getText();
            Log.info("\n" + header.toUpperCase() + ":");

            List<WebElement> points = card.findElements(By.tagName("li"));

            for (WebElement point : points) {
                Log.info(" - " + point.getText());
            }
        }
        Log.info("\n---------------------------------------");
    }
}