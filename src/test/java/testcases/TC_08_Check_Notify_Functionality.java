package testcases;
import basetest.BaseTest;
import org.automation.pages.UpcomingBikes;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_08_Check_Notify_Functionality extends BaseTest {
    @Test
    public void validateUpcomingBikeNotifyMe() {
        UpcomingBikes upcoming = new UpcomingBikes(driver, wait);
        boolean isFormVisible = upcoming.verifyNotifyMeForm();
        Assert.assertTrue(isFormVisible, "The 'Notify Me' registration form did not open after clicking the button!");
    }
}