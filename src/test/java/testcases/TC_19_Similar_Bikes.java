package testcases;

import basetest.BaseTest;
import org.automation.log.Log;
import org.automation.pages.SimilarBikes;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_19_Similar_Bikes extends BaseTest {

    @Test
    public void getSimilarBikes() throws InterruptedException{
        Log.info("Fetching Similar Bikes");
        SimilarBikes simb = new SimilarBikes(driver,wait);
        simb.similarSearch();
        Log.info("Similar Bikes Fetched Successfully");
        boolean actual = driver.getTitle().contains("Kawasaki");
        Assert.assertTrue(actual,"Similar Bikes are not loaded");
    }
}
