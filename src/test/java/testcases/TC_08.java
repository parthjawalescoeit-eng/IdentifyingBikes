package testcases;

import basetest.BaseTest;
import org.automation.pages.UpcomingBikes;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_08 extends BaseTest {
    @Test
    public void validateUpcomingBikeNotifyMe() {
        UpcomingBikes upcoming = new UpcomingBikes(driver, wait);
        upcoming.verifyNotifyMeForm();

    }
}