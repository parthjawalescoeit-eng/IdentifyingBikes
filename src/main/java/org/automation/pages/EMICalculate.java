package org.automation.pages;

import org.automation.utility.JavaScriptUtil;
import org.automation.utility.TakeScreenShot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class EMICalculate {
    WebDriver driver;
    WebDriverWait wait;
    JavaScriptUtil js;
    public EMICalculate(WebDriver driver,WebDriverWait wait){
        this.wait=wait;
        this.driver=driver;
        this.js=new JavaScriptUtil(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//span[@class='header__menu-icon']")
    WebElement icon;
    @FindBy(xpath = "//span[text()='EMI Calculator']")
    WebElement emiOpt;
    @FindBy(id = "txtloanamount")
    WebElement loanAmt;
    @FindBy(id = "interestRate")
    WebElement roi;
    @FindBy(xpath = "//input[@type='radio' and @id='R1']")
    WebElement advOpt;
    @FindBy(id = "btnCalcEmi")
    WebElement calBtn;

    public void calculateEMI() throws InterruptedException {
        js.clickByJS(icon);
        js.clickByJS(emiOpt);
        wait.until(
                ExpectedConditions.visibilityOf(loanAmt)
        );

        loanAmt.clear();
        loanAmt.sendKeys("400000");

        roi.clear();
        roi.sendKeys("8");

        js.clickByJS(advOpt);
        js.clickByJS(calBtn);
        Thread.sleep(500);

        TakeScreenShot ts = new TakeScreenShot(driver,"screenshots");
        ts.take("Calculated EMI ");

        boolean emi = driver.getTitle().contains("EMI");
        Assert.assertTrue(emi,"EMI Page not loaded successfully");

    }

}
