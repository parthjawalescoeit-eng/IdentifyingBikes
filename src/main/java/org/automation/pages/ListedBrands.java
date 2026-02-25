package org.automation.pages;

import org.automation.utility.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public void getAllBrands() throws InterruptedException {
          js.scrollIntoView(helper);
          WebElement view_Brands_Btn=wait.until(ExpectedConditions.elementToBeClickable(viewBrands));
          Thread.sleep(500);
          js.clickByJS(view_Brands_Btn);

          List<WebElement> lst = driver.findElements(By.xpath("//div[@class='o-jr o-j1 o-jK o-ei']"));

          for (WebElement wb : lst){
              System.out.println(wb.getText());
          }
    }

}
