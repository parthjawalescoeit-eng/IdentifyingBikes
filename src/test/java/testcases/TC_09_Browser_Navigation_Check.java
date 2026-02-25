package testcases;

import basetest.BaseTest;
import org.automation.pages.BrowserNavigationCheck;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_09_Browser_Navigation_Check extends BaseTest {

    @Test
    public void BrowserNavigationCheck() throws InterruptedException {


        BrowserNavigationCheck page = new BrowserNavigationCheck(driver,wait);
        boolean ok = page.verifyBrowserBackAndForwardNavigation();
        Assert.assertTrue(ok, "Browser Navigation Check Failed.");
        System.out.println("TC_09_Browser_Navigation_Check: Browser Navigation Check Successfull");

        driver.quit();
    }
}
