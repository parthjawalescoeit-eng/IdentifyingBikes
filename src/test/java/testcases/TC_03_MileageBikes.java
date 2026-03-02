package testcases;

import basetest.BaseTest;
import org.automation.log.Log;
import org.automation.pages.SortByMileagePage;
import org.testng.annotations.Test;
import org.testng.Assert;

public class TC_03_MileageBikes extends BaseTest {


    @Test
    public void getBikesHavingBestMileage(){
        SortByMileagePage obj = new SortByMileagePage(driver,wait);

        boolean testExecuted = obj.sortBikesByMileage();
        
        // Assert that the test executed successfully
        Assert.assertTrue(testExecuted, "Failed to navigate to Best Mileage Bikes section");
        Log.info("Test passed: Successfully navigated to Best Mileage Bikes");
    }

}
