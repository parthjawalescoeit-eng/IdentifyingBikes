package testcases;

import basetest.BaseTest;
import org.automation.log.Log;
import org.automation.pages.YouTubenavigationcheck;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_11_YouTube_navigation_check extends BaseTest {

    @Test
    public void verifyVideoPopupPlayback_TC06() throws InterruptedException {

            YouTubenavigationcheck page = new YouTubenavigationcheck(driver,wait);
            boolean ok = page.verifyVideoPlayback();
            Assert.assertTrue(ok, "Naviagtion Failed.");
            Log.info("TC_11_YouTube_navigation_check Passed.");
    }
}
