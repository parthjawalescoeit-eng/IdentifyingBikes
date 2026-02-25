package testcases;
import basetest.BaseTest;

import basetest.automation.pages.UpcomingBikes;
import org.testng.annotations.Test;

public class TC_08_Check_Notify_Functionality extends BaseTest {
    @Test
    public void validateUpcomingBikeNotifyMe() {
        UpcomingBikes upcoming = new UpcomingBikes(driver, wait);
        upcoming.verifyNotifyMeForm();

    }
}