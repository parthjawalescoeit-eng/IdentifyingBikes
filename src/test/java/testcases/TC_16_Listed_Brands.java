package testcases;

import basetest.BaseTest;

import org.automation.log.Log;
import org.automation.pages.ListedBrands;
import org.testng.annotations.Test;

public class TC_16_Listed_Brands extends BaseTest {

    @Test
    public void getBrandNames() throws InterruptedException {
        ListedBrands lb = new ListedBrands(driver,wait);
        lb.getAllBrands();
        Log.info("Brands fetched successfully");
    }

}
