package testcases;

import basetest.BaseTest;
import org.automation.pages.SortByMileagePage;
import org.testng.annotations.Test;

public class TC_03_MileageBikes extends BaseTest {


    @Test
    public void getBikesHavingBestMileage(){
        SortByMileagePage obj = new SortByMileagePage(driver, wait);

        obj.sortBikesByMileage();
    }

}
