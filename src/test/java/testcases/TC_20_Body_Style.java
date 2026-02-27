package testcases;

import basetest.BaseTest;
import org.automation.pages.BodyStyle;
import org.testng.annotations.Test;

public class TC_20_Bike_Style extends BaseTest {

    @Test
    public void getBikes() throws InterruptedException {
        BodyStyle bd = new BodyStyle(driver,wait);
        bd.getAllBikes();
    }

}
