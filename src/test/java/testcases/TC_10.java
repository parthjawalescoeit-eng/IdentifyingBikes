package testcases;

import basetest.BaseTest;
import org.automation.pages.ReviewsPage;
import org.automation.utility.TakeScreenShot;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_10 extends BaseTest {

    @Test
    public void validateHondaShineReviewSearch() {
        ReviewsPage reviews = new ReviewsPage(driver, wait);
        TakeScreenShot ts = new TakeScreenShot(driver, "screenshots");
        reviews.navigateToReviews();
        reviews.searchForBike("Honda Shine");
        ts.take("Honda_Shine_Review_Page");
        String title = reviews.getPageTitle();
        System.out.println("Current Page Title: " + title);
        Assert.assertTrue(title.toLowerCase().contains("honda shine"),
                "Assertion Failed: Title does not contain 'Honda Shine'. Actual title: " + title);
    }
}