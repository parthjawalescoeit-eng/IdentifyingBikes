package testcases;

import basetest.BaseTest;
import org.automation.log.Log;
import org.automation.pages.CompareBikes;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_17_Compare_Bikes extends BaseTest {

    @Test
    public void compareBikes() throws InterruptedException {
        Log.info(" Bikes Comparison Started ....");
        CompareBikes cb = new CompareBikes(driver,wait);
        cb.compare();
        Log.info(" Bikes Comparison Done Successfully ...");

        boolean compareTitle = driver.getTitle().contains("Know Which Is Better");
        Assert.assertTrue(compareTitle,"Chosen Bikes Not Loaded Successfully");
    }

}
