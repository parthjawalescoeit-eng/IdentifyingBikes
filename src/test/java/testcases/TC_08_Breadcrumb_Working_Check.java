package testcases;

import basetest.BaseTest;
import org.automation.pages.BreadcrumbWorkingCheck;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_08_Breadcrumb_Working_Check extends BaseTest {

    @Test
    public void BreadcrumbWorkingCheck() throws InterruptedException {


        BreadcrumbWorkingCheck page = new BreadcrumbWorkingCheck(driver,wait);
        boolean ok = page.BreadcrumbWorking();
        Assert.assertTrue(ok, "Breadcrumb Working Failed.");
        System.out.println("TC_08_Breadcrumb_Working_Check Passed: Breadcrumb Working Successfully");

        driver.quit();
    }
}
