package testcases;

import basetest.BaseTest;
import org.automation.pages.SearchElectricBikes;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_10_Search_Electric_Bikes extends BaseTest {

    @Test
    public void SearchForElectricBikes() throws InterruptedException {


        SearchElectricBikes page = new SearchElectricBikes(driver,wait);
        boolean ok = page.SearchElectricBikes();
        Assert.assertTrue(ok, "Searching Electric Bikes Failed.");
        System.out.println("TC_10_Search_Electric_Bikes Passed Successfully");

        driver.quit();
    }
}
