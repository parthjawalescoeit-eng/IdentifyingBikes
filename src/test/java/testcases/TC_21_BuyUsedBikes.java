package testcases;

import basetest.BaseTest;
import org.automation.pages.BuyUsedBikes;
import org.automation.utility.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

public class TC_21_BuyUsedBikes extends BaseTest {

    @Test
    public void usedBikeCity() throws Exception{

        BuyUsedBikes buyBikes=new BuyUsedBikes(driver,wait);
        buyBikes.clickSideBar();
        List<BuyUsedBikes> results = buyBikes.getBikeResults();
        Assert.assertTrue(results.size() > 0, "There is no bike for the brand Gizzer");
        String[] headers = {"Sr. No.", "Bike Name", "Price"};
        List<String[]> dataToExport = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            dataToExport.add(new String[] {
                    String.valueOf(i + 1),
                    results.get(i).getName(),
                    results.get(i).getPrice()
            });
        }

        String filePath = System.getProperty("user.dir") + "/ExcelData/BikeReport.xlsx";
        ExcelUtil.writeDynamicDataToExcel(filePath, "Sheet_5", headers, dataToExport);
    }
}

