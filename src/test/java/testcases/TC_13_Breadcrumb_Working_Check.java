package testcases;

import basetest.BaseTest;
import org.automation.log.Log;
import org.automation.pages.BreadCrumbWorkingCheck;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_13_Breadcrumb_Working_Check extends BaseTest {

    @Test
    public void BreadcrumbWorkingCheck() throws InterruptedException {

        BreadCrumbWorkingCheck page = new BreadCrumbWorkingCheck(driver,wait);
        boolean ok = page.breadCrumbWorking();
        Assert.assertTrue(ok, "Breadcrumb Working Failed.");
        Log.info("TC_13_Breadcrumb_Working_Check Passed: Breadcrumb Working Successfully");
    }
}
