package testcases;

import basetest.BaseTest;
import org.automation.pages.BuyUsedBikes;
import org.automation.utility.TakeScreenShot;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TC_21_BuyUsedBikes extends BaseTest {

    @Test
    public void usedBikeCity() throws Exception{

        BuyUsedBikes buyBikes=new BuyUsedBikes(driver,wait);

        buyBikes.clickSideBar();

        List<BuyUsedBikes> results = buyBikes.getBikeResults();
        boolean isListEmpty= results.isEmpty();
        Assert.assertTrue(results.size() > 0, "There is no bike for the brand Gizzer");
        TakeScreenShot.takeScreenshot(driver, "TC_21_BuyUsedBikes");

    }

}
