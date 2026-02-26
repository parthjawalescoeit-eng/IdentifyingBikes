package testcases;

import basetest.BaseTest;
import org.automation.pages.BikeProsAndCons;
import org.automation.utility.TakeScreenShot;
import org.testng.annotations.Test;
import java.io.IOException;
import org.testng.Assert;

public class TC_25_BikeProsAndCons extends BaseTest {

    @Test
    public void bikeColorVariantImageUpdate() throws InterruptedException, IOException {
        BikeProsAndCons page = new BikeProsAndCons(driver, wait);
        page.bikeColoursOption();
        TakeScreenShot.takeScreenshot(driver, "TC_25_BikeProsAndCons");
        // final assertion: after navigating to expert review, at least one pros/cons card should exist
        // inspect underlying page object list via reflection since page doesn't expose it
        Assert.assertTrue(!page.pros.isEmpty(), "Expected at least one pros/cons card to be present");
    }
}
