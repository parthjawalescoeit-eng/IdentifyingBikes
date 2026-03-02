package testcases;

import basetest.BaseTest;
import org.automation.log.Log;
import org.automation.pages.BodyStyle;
import org.testng.annotations.Test;

public class TC_20_Body_Style extends BaseTest {

    @Test
    public void getBikes() throws InterruptedException {
        BodyStyle bd = new BodyStyle(driver,wait);
        bd.getAllBikes();
        Log.info("Bikes Fetched Successfully");
    }
}
