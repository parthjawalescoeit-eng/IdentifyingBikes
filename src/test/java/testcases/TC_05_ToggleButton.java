package testcases;

import basetest.BaseTest;

import org.automation.log.Log;
import org.automation.pages.TogglePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_05_ToggleButton extends BaseTest {

    @Test
    public void checkToggleButton(){

        TogglePage toggleObject = new TogglePage(driver,wait);
        boolean result = toggleObject.openSideBar();
        Assert.assertTrue(result, "Failed to open sidebar and toggle button");
        Log.info("Toggle button is working fine! ");
    }
}
