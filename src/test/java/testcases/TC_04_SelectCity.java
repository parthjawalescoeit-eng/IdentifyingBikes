package testcases;

import basetest.BaseTest;

import org.automation.log.Log;
import org.automation.pages.LocationPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_04_SelectCity extends BaseTest {

    @Test
    public void selectCityToPurchasedVehicle(){

        LocationPage pageObject = new LocationPage(driver,wait);
        boolean result = pageObject.mapClick();
        Assert.assertTrue(result, "Failed to select city and display trending bike info");
        Log.info("Bike get selected from particular city");
    }
}
