package testcases;

import basetest.BaseTest;
import org.automation.pages.BuyUsedBikes;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TC_21_BuyUsedBikes extends BaseTest {

    @Test
    public void usedBikeCity() throws Exception{

        BuyUsedBikes buyBikes=new BuyUsedBikes(driver,wait);

        buyBikes.clickSideBar();

        List<BuyUsedBikes> results = buyBikes.getBikeResults();


    }

}
