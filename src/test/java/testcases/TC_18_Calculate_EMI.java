package testcases;

import basetest.BaseTest;
import org.automation.pages.EMICalculate;
import org.testng.annotations.Test;

public class TC_18_Calculate_EMI extends BaseTest {

    @Test
    public void EMICalculator() throws InterruptedException {
        EMICalculate ec = new EMICalculate(driver,wait);
        ec.calculateEMI();

    }

}
