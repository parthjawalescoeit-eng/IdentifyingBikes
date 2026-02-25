package testcases;

import basetest.BaseTest;
import org.automation.pages.SimilarBikes;
import org.testng.annotations.Test;

public class TC_19_Similar_Bikes extends BaseTest {

    @Test
    public void getSimilarBikes() throws InterruptedException{
        SimilarBikes simb = new SimilarBikes(driver,wait);
        simb.similarSearch();
    }

}
