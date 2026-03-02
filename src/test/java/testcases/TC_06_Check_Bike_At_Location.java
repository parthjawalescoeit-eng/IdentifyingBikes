package testcases;

import basetest.BaseTest;
import org.automation.log.Log;
import org.automation.pages.OnRoadPrice;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_06_Check_Bike_At_Location extends BaseTest {

    @Test
    public  void getBikeAtLocation(){
        OnRoadPrice on=new OnRoadPrice(driver,wait);
        String title=on.chequeAvailability("Pune");
        Log.info(title);
        Assert.assertTrue(title.contains("Honda Shine price in Pune"),"Shine is not present in Pune");
    }
}
