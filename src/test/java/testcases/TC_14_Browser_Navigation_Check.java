package testcases;

import basetest.BaseTest;
import org.automation.log.Log;
import org.automation.pages.BrowserNavigationCheck;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_14_Browser_Navigation_Check extends BaseTest {

    @Test
    public void BrowserNavigationCheck() throws InterruptedException {

        BrowserNavigationCheck page = new BrowserNavigationCheck(driver,wait);
        boolean ok = page.verifyBrowserBackAndForwardNavigation();
        Assert.assertTrue(ok, "Browser Navigation Check Failed.");
        Log.info("TC_14_Browser_Navigation_Check: Browser Navigation Check Successfull");
    }
}
