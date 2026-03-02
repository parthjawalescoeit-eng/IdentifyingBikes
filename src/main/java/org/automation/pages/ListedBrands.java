package org.automation.pages;

import org.automation.log.Log;
import org.automation.utility.JavaScriptUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.List;

public class ListedBrands {
    WebDriver driver;
    WebDriverWait wait;
    JavaScriptUtil js;

    public ListedBrands(WebDriver driver, WebDriverWait wait){
        this.driver=driver;
        this.wait=wait;
        this.js=new JavaScriptUtil(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//span[@data-testing-id='brand-tab']")
    WebElement helper;

    @FindBy(xpath = "//div[@role='button']")
    WebElement viewBrands;

    @FindBy(xpath = "//div[@class='o-jr o-j1 o-jK o-ei']")
    List<WebElement> brandLst;

    public List<String> getAllBrands() throws InterruptedException {
          js.scrollIntoView(helper);
          Log.info(" Scrolling TO Brands Page");
          WebElement view_Brands_Btn=wait.until(ExpectedConditions.elementToBeClickable(viewBrands));
          Thread.sleep(800);
          js.clickByJS(view_Brands_Btn);
          Log.info("Brands Button Selected");
          List<WebElement> Brandlst =wait.until(ExpectedConditions.visibilityOfAllElements(brandLst));
          List<String> brandName=new ArrayList<>();
          for (WebElement e:Brandlst){
                brandName.add(e.getText());
                Log.info("Brand Name Retrieved");
          }
          return  brandName;
    }
}
