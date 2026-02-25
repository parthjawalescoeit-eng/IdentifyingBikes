package org.automation.pages;

import org.automation.logs.Log;
import org.automation.utility.JavaScriptUtil;
import org.automation.utility.WaitUtil;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class BikeReview {

    WebDriver driver;
    JavaScriptUtil js;
    WebDriverWait wait;

    public BikeReview(WebDriver driver,WebDriverWait wait){
        this.driver=driver;
        this.js=new JavaScriptUtil(driver);
        this.wait=wait;
        PageFactory.initElements(driver, this);
    }

    String reviews;
    String stars;

    public BikeReview(String reviews,String stars){
        this.reviews=reviews;
        this.stars=stars;
    }

    @FindBy(xpath="(//div[@class='o-C']//input)[2]")
    WebElement search;

    @FindBy(xpath="(//div[@class='o-jA o-jJ o-j3  ']//li)[2]")
    WebElement bike;

    @FindBy(xpath="(//h3[@class='o-j4 o-jq o-hM o-c4 o-jK'])[4]")
    WebElement gt;

    @FindBy(xpath="(//div[@class='o-C o-o o-oz o-os YF8OI6   o-hl'])[8]")
    WebElement reviewStar;

    public void selectBike() throws InterruptedException {

        WebElement searchClick=wait.until(ExpectedConditions.visibilityOf(search));
        Assert.assertNotNull(searchClick);
        searchClick.sendKeys("Royal Enfield");

        WebElement bikeSelect=wait.until(ExpectedConditions.elementToBeClickable(bike));
        Assert.assertNotNull(bike);
        js.clickByJS(bikeSelect);

        WebElement gtBike=wait.until(ExpectedConditions.elementToBeClickable(gt));
        Assert.assertNotNull(gt);
        js.clickByJS(gtBike);

        WebElement userReview=wait.until(ExpectedConditions.elementToBeClickable(reviewStar));
        Assert.assertNotNull(userReview);
        userReview.click();
        Thread.sleep(1000);

    }

    @FindBy(xpath="//p[@class='o-jH o-j1 o-js o-bO']")
    List<WebElement> review;

    @FindBy(xpath="//div[@class='o-gZ o-ih o-C o-f o-aL o-aE o-nh o-aS']")
    List<WebElement> star;

    public String toString() {
        return "Bike: " + reviews + " | Price: " + stars;
    }

    public List<BikeReview> getList(){
        List<BikeReview> bikeReviewStar=new ArrayList<>();

        //WaitUtil.waitForElementVisible(driver, review.get(0));
        //int limit=Math.min(review.size(),9);

        for(int i=0;i<9;i++){
            String reviews=review.get(i).getText().trim();
            String stars=star.get(i).getText().trim();

            bikeReviewStar.add(new BikeReview(reviews,stars));

            Log.info((i + 1) + ": " + reviews + " -> " + stars);
        }


        return bikeReviewStar;
    }




}
