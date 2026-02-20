package testcases;

import basetest.BaseTest;
import org.automation.pages.FilterBikes;
import org.testng.annotations.Test;

public class TC_02_FilterBikes extends BaseTest {
    git add -A
    @Test
    public void BikeFilterTestScenario() throws InterruptedException {
        FilterBikes filterPage = new FilterBikes(driver,wait);
        filterPage.filterBikesUnderTwoLacks();
        System.out.println(filterPage.getCount());
    }

}
