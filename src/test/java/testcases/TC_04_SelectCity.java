package testcases;

import basetest.BaseTest;
import org.automation.pages.LocationPage;
import org.testng.annotations.Test;

public class TC_04_SelectCity extends BaseTest {

    @Test
    public void selectCityToPurchasedVehicle(){

        LocationPage pageObject = new LocationPage(driver, wait);
        pageObject.mapClick();

    }

}
