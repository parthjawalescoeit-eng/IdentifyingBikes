package testcases;

import basetest.BaseTest;
import org.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TC_01 extends BaseTest {

    @Test
    public void getFilterBikes() throws InterruptedException {
        HomePage homePage=new HomePage(driver,wait);
        homePage.test();
        //Assert.assertTrue(homePage.test1());
    }
}
