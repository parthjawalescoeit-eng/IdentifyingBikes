package testcases;


import basetest.BaseTest;
import org.automation.log.Log;
import org.automation.pages.ReviewsPage;
import org.automation.utility.TakeScreenShot;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_10_Validate_Bike_Reviews extends BaseTest {

    @Test
    public void validateReviewSearch() {
        ReviewsPage reviews = new ReviewsPage(driver, wait);
        TakeScreenShot ts = new TakeScreenShot(driver, "screenshots");
        reviews.navigateToReviews();
        reviews.searchForBike("Honda Shine");
        ts.take("TC_10");
        String title = reviews.getPageTitle();
        Log.info("Current Page Title: " + title);
        Assert.assertTrue(title.toLowerCase().contains("honda shine"),
                "Assertion Failed: Title does not contain 'Honda Shine'. Actual title: " + title);
    }
}