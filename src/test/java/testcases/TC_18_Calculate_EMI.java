package testcases;

import basetest.BaseTest;
import org.automation.log.Log;
import org.automation.pages.EMICalculate;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_18_Calculate_EMI extends BaseTest {

    @Test
    public void EMICalculator() throws InterruptedException {
        Log.info("EMI Calculation Started");
        EMICalculate ec = new EMICalculate(driver,wait);
        ec.calculateEMI();
        Log.info("EMI Calculation Done Successfully");

        boolean emi = driver.getTitle().contains("EMI");
        Assert.assertTrue(emi,"EMI Page not loaded successfully");

    }

}
