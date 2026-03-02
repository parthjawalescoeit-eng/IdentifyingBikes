package testcases;

import basetest.BaseTest;
import org.automation.log.Log;
import org.automation.pages.FilterBikes;
import org.testng.annotations.Test;
import org.testng.Assert;

public class TC_02_FilterBikes extends BaseTest {

    @Test
    public void BikeFilterTestScenario() {
        FilterBikes filterPage = new FilterBikes(driver,wait);
        Log.info("Filtered Bikes");
        int filteredBikeCount = filterPage.filterBikesUnderTwoLacks();
        Assert.assertTrue(filteredBikeCount > 0, "No bikes found after applying filter for Bajaj under 2 lakh");
        Log.info("Test passed: " + filteredBikeCount + " filtered bikes displayed");
    }
}
