package testcases;

import basetest.BaseTest;

import org.automation.pages.MultiFilterBikes;

import org.automation.utility.TakeScreenShot;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_24_MultiFilterCombination extends BaseTest {

   @Test
   public void multiFilterCombinationTest() throws InterruptedException, IOException {
   MultiFilterBikes mfb=new MultiFilterBikes(driver,wait);
   mfb.multiFilterBikes();
   TakeScreenShot.takeScreenshot(driver, "TC_24_MultiFilterCombination");
   }
}
