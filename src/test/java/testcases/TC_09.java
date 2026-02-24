package testcases;

import basetest.BaseTest;
import org.automation.pages.NewsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TC_09 extends BaseTest {

    @Test
    public void validateNewsHeadlines() {
        NewsPage newsPage = new NewsPage(driver);
        newsPage.navigateToNews();
        List<String> headlines = newsPage.getAllHeadlines();
        System.out.println("Total Headlines Found: " + headlines.size());
        for (int i = 0; i < headlines.size(); i++) {
            System.out.println((i + 1) + ". " + headlines.get(i));
        }
        Assert.assertTrue(headlines.size() > 0, "No news headlines were found on the page!");
    }
}
