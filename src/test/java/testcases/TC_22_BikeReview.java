package testcases;

import basetest.BaseTest;
import org.automation.pages.BikeReview;
import org.automation.pages.BuyUsedBikes;
import org.automation.utility.TakeScreenShot;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class TC_22_BikeReview extends BaseTest {

    @Test
    public void bikeReviewsPrint() throws IOException, InterruptedException {

        BikeReview bike=new BikeReview(driver,wait);
        bike.selectBike();
        List<BikeReview> results = bike.getList();

        TakeScreenShot.takeScreenshot(driver, "TC_22_BikeReview");
    }
}
