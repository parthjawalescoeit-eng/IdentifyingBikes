package testcases;



import basetest.BaseTest;
import basetest.automation.pages.NewsPage;
import basetest.automation.utility.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


public class TC_09_Validate_News_Headlines extends BaseTest {

    @Test
    public void validateNewsHeadlines() {
        NewsPage newsPage = new NewsPage(driver);
        newsPage.navigateToNews();
        List<String> headlines = newsPage.getAllHeadlines();
        String[] headers = {"Sr. No.", "Headline Title"};
        List<String[]> dataToWrite = new java.util.ArrayList<>();
        for (int i = 0; i < headlines.size(); i++) {
            dataToWrite.add(new String[] {
                    String.valueOf(i + 1),
                    headlines.get(i),
            });
        }

        String filePath = System.getProperty("user.dir") + "/ExcelData/BikeReport.xlsx";
        String sheetName = "Sheet_2";
        ExcelUtil.writeDynamicDataToExcel(filePath, sheetName, headers, dataToWrite);
        Assert.assertTrue(headlines.size() > 0, "No headlines found!");
    }
}