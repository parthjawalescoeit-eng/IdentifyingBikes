package testcases;

import basetest.BaseTest;
import org.automation.pages.BrochureDownloadCheck;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_07_Brochure_Download_Check extends BaseTest {

    @Test
    public void downloadBrochureOfModel() throws InterruptedException {


        BrochureDownloadCheck page = new BrochureDownloadCheck(driver,wait);
        boolean ok = page.downloadBrochure();
        Assert.assertTrue(ok, "Download Failed.");
        System.out.println("TC_07_Brochure_Download_Check Passed: Brochure Download Successfully");

        driver.quit();
    }
}
