package testcases;


import basetest.BaseTest;
import org.automation.pages.OnRoadPrice;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_07_Verify_Invalid_City_Error extends BaseTest {

    @Test
    public void verifyInvalidCityError() {
        OnRoadPrice on = new OnRoadPrice(driver, wait);
        String actualError = on.checkInvalidCity("InvalidCityName123");
        Assert.assertTrue(actualError.contains("No results match"), "Main error phrase not found!");
    }
}