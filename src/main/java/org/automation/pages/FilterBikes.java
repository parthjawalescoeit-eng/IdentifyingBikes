package org.automation.pages;

import org.automation.log.Log;
import org.automation.utility.ExcelUtil;
import org.automation.utility.JavaScriptUtil;
import org.automation.utility.CommonCode;
import org.automation.utility.TakeScreenShot;
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
        PageFactory.initElements(driver, this);
    }

    public int filterBikesUnderTwoLacks() {
        try {
            commonCode.safeClickToWebElement(budgetLnk);
            js.clickByJS(underTwoLack);
            commonCode.safeClickToWebElement(brandBtn);
            Thread.sleep(1000);
            bajajChk.click();
            js.scrollIntoView(applyFilterBtn);
            commonCode.safeClickToWebElement(applyFilterBtn);
            Thread.sleep(2000);

            new TakeScreenShot(driver, "screenshots").take("TC_02");
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

        // 1. Prepare data structures to match ExcelUtil signature
        // headers -> String[]
        // dataRows -> List<String[]>
        String[] headers = {"Bike Name", "Rating/Reviews", "Specs", "Price"};
        List<String[]> dataRows = new ArrayList<>();

        Log.info("\n--- Processing " + bikeCount.size() + " Bikes ---");

        for (WebElement bike : bikeCount) {
            String rawText = bike.getText().trim();
            String[] lines = rawText.split("\n");

            // Extracting specific details
            String name = (lines.length > 0) ? lines[0] : "N/A";
            String rating = (lines.length > 2) ? lines[1] + " (" + lines[2] + ")" : "N/A";
            String specs = (lines.length > 3) ? lines[3] : "N/A";
            String price = (lines.length > 4) ? lines[4] : "N/A";

            // 2. Create a String array for this row and add to the list
            String[] rowContent = {name, rating, specs, price};
            dataRows.add(rowContent);

            Log.info("Processed: " + name);
        }

        // 3. Handle File Path and Folder creation
        String folderPath = "C:\\Users\\2464440\\IdentifyingBikes\\ExcelData";
        String filePath = folderPath + "\\BikeReport.xlsx";

        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // 4. Call your ExcelUtil method exactly as it is defined:
        // writeDynamicDataToExcel(String filePath, String sheetName, String[] headers, List<String[]> dataRows)
        ExcelUtil.writeDynamicDataToExcel(filePath, "Sheet_1", headers, dataRows);

        return bikeCount.size();
    }
}