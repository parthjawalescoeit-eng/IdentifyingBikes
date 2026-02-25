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
        String filePath = System.getProperty("user.dir") + "/ExcelData/report.xlsx";

        String sheetName = "Sheet_1";
        ExcelUtil.writeHeadlinesToExcel(headlines, filePath, sheetName);

        Assert.assertTrue(headlines.size() > 0, "No headlines found!");
    }
}