package testcases;

import basetest.BaseTest;
import org.automation.log.Log;
import org.automation.pages.CompareBikes;
import org.testng.annotations.Test;

public class TC_17_Compare_Bikes extends BaseTest {

    @Test
    public void compareBikes() throws InterruptedException {
        Log.info(" Bikes Comparison done successfully ");
        CompareBikes cb = new CompareBikes(driver,wait);
        cb.compare();
    }

}
