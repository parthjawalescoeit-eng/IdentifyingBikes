package org.automation.pages;

import org.automation.logs.Log;
import org.automation.utility.JavaScriptUtil;
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
      WebElement brandInput;

     @FindBy(xpath="(//div[@class='o-jr o-j1 o-jK o-ei'])[5]")
     WebElement yamahaOption;

     @FindBy(xpath="(//p[@class='o-jJ o-j2 o-ji'])[4]")
     WebElement displacement;

     @FindBy(xpath="(//label[@class='o-os o-oz o-j1 o-g  o-bv'])[11]")
     WebElement checkbox;

     @FindBy(xpath="(//div[@data-display-name='Budget'])")
     WebElement budgetClk;

     @FindBy(xpath="(//div[@class='o-gW o-if o-nh o-lc '])[2]")
     WebElement role;

     @FindBy(xpath="(//p[@class='o-jJ o-j2 o-ji'])[1]")
     WebElement budgetInput;

     @FindBy(xpath="(//input[@placeholder='Max'])")
     WebElement budgetOption;

     @FindBy(xpath="//button[@data-testid='filter-popup-apply-button-inner']")
     WebElement btn;

     @FindBy(xpath="//a[@href and contains(@href,'-bikes/')]")
     List<WebElement> bikeListings;

      public void multiFilterBikes() throws InterruptedException{

          js.scrollIntoView(brandInput);
       WebElement brandInputElement=wait.until(ExpectedConditions.elementToBeClickable(brandInput));
       js.clickByJS(brandInputElement);

        WebElement yamaha=wait.until(ExpectedConditions.elementToBeClickable(yamahaOption));
        Assert.assertNotNull(yamaha);
        yamaha.click();

        WebElement budget_clk=wait.until(ExpectedConditions.elementToBeClickable(budgetClk));
        js.clickByJS(budget_clk);

          WebElement role_=wait.until(ExpectedConditions.elementToBeClickable(role));
          assert role_ != null;
          role_.click();

         WebElement budget=wait.until(ExpectedConditions.elementToBeClickable(budgetOption));
          js.clickByJS(budget);
          budget.sendKeys(Keys.CONTROL + "a");
          budget.sendKeys(Keys.BACK_SPACE);
          budget.sendKeys("200000");

          WebElement displacementInputElement=wait.until(ExpectedConditions.visibilityOf(displacement));
          js.scrollIntoView(displacementInputElement);
          js.clickByJS(displacementInputElement);

          js.scrollIntoView(checkbox);
          WebElement checkbox150=wait.until(ExpectedConditions.visibilityOf(checkbox));
          if(!checkbox150.isSelected()) {
              checkbox150.click();
          }

          WebElement button=wait.until(ExpectedConditions.elementToBeClickable(btn));
          assert button != null;
          button.click();

          verifyFilteredResults();
         }



    public void verifyFilteredResults() throws InterruptedException{

    List<WebElement> bikes=driver.findElements(org.openqa.selenium.By.xpath("//a[@href and contains(@href,'-bikes/')]"));
    Assert.assertTrue(bikes.size()>0,"No bikes found matching the filters");
    Log.info("Total bikes found: "+bikes.size());
    }
}
