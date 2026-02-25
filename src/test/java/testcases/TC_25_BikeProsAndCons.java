package testcases;

import basetest.BaseTest;
import org.automation.pages.BikeProsAndCons;
import org.automation.utility.TakeScreenShot;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_25_BikeProsAndCons extends BaseTest {

    @Test
    public void bikeColorVariantImageUpdate() throws InterruptedException, IOException {
        BikeProsAndCons page = new BikeProsAndCons(driver, wait);
        page.bikeColoursOption();
        TakeScreenShot.takeScreenshot(driver, "TC_25_BikeProsAndCons");

    }
}
