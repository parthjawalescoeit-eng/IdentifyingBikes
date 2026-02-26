package testcases;

import basetest.BaseTest;
import org.automation.pages.ComparingBikes;
import org.automation.utility.TakeScreenShot;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_23_ComparingBikes extends BaseTest {

    @Test
    public void bikeCompare() throws InterruptedException, IOException {
        ComparingBikes cb=new ComparingBikes(driver,wait);
        cb.compareBike();
        TakeScreenShot.takeScreenshot(driver, "TC_23_ComparingBikes");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("compare"), "Expected URL to contain 'compare' after comparing bikes, but got: " + currentUrl);

    }
}
