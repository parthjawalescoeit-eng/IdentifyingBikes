package org.automation.pages;

import org.automation.log.Log;
import org.automation.utility.JavaScriptUtil;
import org.automation.utility.TakeScreenShot;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.List;

public class MultiFilterBikes {

      WebDriver driver;
      WebDriverWait wait;
      JavaScriptUtil js;

      public MultiFilterBikes(WebDriver driver,WebDriverWait wait){
       this.driver=driver;
       this.wait=wait;
       this.js=new JavaScriptUtil((driver));
       PageFactory.initElements(driver,this);
      }

      @FindBy(xpath="//span[@data-testing-id='brand-tab']")
      WebElement brandInputBtn;

     @FindBy(xpath="(//div[@class='o-jr o-j1 o-jK o-ei'])[5]")
     WebElement yamahaLnk;

     @FindBy(xpath="(//p[@class='o-jJ o-j2 o-ji'])[4]")
     WebElement displaceDrop;

     @FindBy(xpath="(//label[@class='o-os o-oz o-j1 o-g  o-bv'])[11]")
     WebElement chk150_120;

     @FindBy(xpath="(//div[@data-display-name='Budget'])")
     WebElement budgetBtn;

     @FindBy(xpath="(//div[@class='o-gW o-if o-nh o-lc '])[2]")
     WebElement role;

     @FindBy(xpath="(//p[@class='o-jJ o-j2 o-ji'])[1]")
     WebElement budgetIpt;

     @FindBy(xpath="(//input[@placeholder='Max'])")
     WebElement budgetOptionBtn;

     @FindBy(xpath="//button[@data-testid='filter-popup-apply-button-inner']")
     WebElement applyBtn;

     @FindBy(xpath="//a[@href and contains(@href,'-bikes/')]")
     List<WebElement> bikeListings;

      public void multiFilterBikes() throws InterruptedException{
          js.scrollIntoView(brandInputBtn);
       wait.until(ExpectedConditions.elementToBeClickable(brandInputBtn));
       js.clickByJS(brandInputBtn);

        wait.until(ExpectedConditions.elementToBeClickable(yamahaLnk));
        yamahaLnk.click();

        wait.until(ExpectedConditions.elementToBeClickable(budgetBtn));
        js.clickByJS(budgetBtn);

        wait.until(ExpectedConditions.visibilityOf(role));
        wait.until(ExpectedConditions.elementToBeClickable(role));
        role.click();

         wait.until(ExpectedConditions.elementToBeClickable(budgetOptionBtn));
         js.clickByJS(budgetOptionBtn);
         budgetOptionBtn.sendKeys(Keys.CONTROL + "a");
         budgetOptionBtn.sendKeys(Keys.BACK_SPACE);
         budgetOptionBtn.sendKeys("200000");

          wait.until(ExpectedConditions.visibilityOf(displaceDrop));
          js.scrollIntoView(displaceDrop);
          js.clickByJS(displaceDrop);

          js.scrollIntoView(chk150_120);
          wait.until(ExpectedConditions.visibilityOf(chk150_120));
          if(!chk150_120.isSelected()) {
              chk150_120.click();
          }

          wait.until(ExpectedConditions.elementToBeClickable(applyBtn));
          applyBtn.click();

          verifyFilteredResults();
         }

    public void verifyFilteredResults() throws InterruptedException{
        List<WebElement> bikes=driver.findElements(org.openqa.selenium.By.xpath("//a[@href and contains(@href,'-bikes/')]"));
        Assert.assertTrue(bikes.size()>0,"No bikes found matching the filters");
        Log.info("Total bikes found: "+bikes.size());
        Thread.sleep(300);
        String screenshotName = "TC_24_MultiFilterCombination";
        new TakeScreenShot(driver, "screenshots").take(screenshotName);
    }
}
