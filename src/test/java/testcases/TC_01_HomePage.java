package testcases;

import basetest.BaseTest;

import org.automation.log.Log;
import org.automation.pages.HomePage;
import org.testng.annotations.Test;
import org.testng.Assert;


public class TC_01_HomePage extends BaseTest {

    @Test
    public void getBikes(){
        HomePage homePage=new HomePage(driver,wait);
        
        // Execute the test and capture result
        int bikesDisplayed = homePage.findUpcomingBikes();
        
        // Assert that bikes are displayed
        Assert.assertTrue(bikesDisplayed > 0, "No bikes were displayed for Royal Enfield");
        Log.info("Test passed: " + bikesDisplayed + " bikes brands displayed");
    }
}
