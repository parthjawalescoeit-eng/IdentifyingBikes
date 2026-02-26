package org.automation.pages;
import org.automation.log.Log;
import org.automation.utility.ExcelUtils;
import org.automation.utility.JavaScriptUtil;
import org.automation.utility.TakeScreenShot;
import org.automation.utility.CommonCode;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class FilterBikes {

    private final WebDriver driver;
    private final JavaScriptUtil js;
    private final WebDriverWait wait;
    private final CommonCode commonCode;


    @FindBy(xpath = "//span[@data-testing-id='budget-tab']")
    WebElement budgetLnk;

    @FindBy(xpath = "//a[@data-testid='budget-under-₹2-lakh' or contains(text(),'Under ₹2 lakh')]")
    WebElement underTwoLack;

    @FindBy(xpath = "//div[@data-display-name='Brand' or contains(text(), 'Brand')]//span")
    WebElement brandBtn;

    @FindBy(xpath = "//label[.//span[normalize-space(.)='Bajaj']]//input[@type='checkbox']")
    WebElement bajajChk;

    @FindBy(xpath = "//button[@type='button' and contains(text(), 'Apply Filters')]")
    WebElement applyFilterBtn;

    @FindBy(xpath = "//div[contains(@class,'o-cw o-c0 o-bK')]//ul[@data-skin='bg-transparent']//li")
    List<WebElement> bikeCount;

    public FilterBikes(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.js = new JavaScriptUtil(driver);
        commonCode = new CommonCode(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver,this);
    }


    public int filterBikesUnderTwoLacks() {



        try{
        commonCode.safeClickToWebElement(budgetLnk);
        js.clickByJS(underTwoLack);
        commonCode.safeClickToWebElement(brandBtn);
        Thread.sleep(500);
        bajajChk.click();

        js.scrollIntoView(applyFilterBtn);

        commonCode.safeClickToWebElement(applyFilterBtn);

        Thread.sleep(1000); // Wait for filtering to complete

            new TakeScreenShot(driver, "screenshots").take("TC_02");
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

        List<String> excelLines = new ArrayList<>();

        Log.info("\n--- Processing " + bikeCount.size() + " Bikes ---");

        for (int i = 0; i < bikeCount.size(); i++) {
            String rawText = bikeCount.get(i).getText().trim();
            String[] lines = rawText.split("\n");

            // 1. Parsing logic to separate specific details
            String name = (lines.length > 0) ? lines[0] : "N/A";
            String rating = (lines.length > 2) ? lines[1] + " (" + lines[2] + ")" : "N/A";
            String specs = (lines.length > 3) ? lines[3] : "N/A";
            String price = (lines.length > 4) ? lines[4] : "N/A";

            // 2. Format as a single clear string because ExcelUtil only has two columns (Sr No and Content)
            // We use a separator like " | " to make it look organized in the 'Content' column
            String formattedData = String.format("NAME: %s | RATING: %s | SPECS: %s | PRICE: %s",
                    name, rating, specs, price);

            excelLines.add(formattedData);
            Log.info("Processed: " + name);
        }

        // 3. Handle File Path and Folder creation
        String folderPath = "C:\\Users\\2464440\\IdentifyingBikes\\ExcelData";
        String filePath = folderPath + "\\report.xlsx";

        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // 4. Call your specific ExcelUtil method
        // memberName = "Sheet2" will ensure only "Sheet2" is overwritten
        ExcelUtils.writeHeadlinesToExcel(excelLines, filePath, "Sheet1");

        return bikeCount.size();
    }
}