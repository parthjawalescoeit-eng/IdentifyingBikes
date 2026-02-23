package testcases;

import basetest.BaseTest;
import org.automation.pages.TogglePage;
import org.testng.annotations.Test;

public class TC_05_ToggleButton extends BaseTest {

    @Test
    public void checkToggleButton(){

        TogglePage toggleObject = new TogglePage(driver,wait);
        toggleObject.openSideBar();
    }

}
