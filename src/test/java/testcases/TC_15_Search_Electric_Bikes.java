package testcases;

import basetest.BaseTest;
import org.automation.pages.SearchElectricBikes;
import org.automation.utility.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class TC_15_Search_Electric_Bikes extends BaseTest {

    @Test
    public void SearchForElectricBikes() throws InterruptedException {


        SearchElectricBikes page = new SearchElectricBikes(driver,wait);
        String filePath = System.getProperty("user.dir") + "/ExcelData/BikeReport.xlsx";
        String sheet = "Sheet_3";
        String header[]={"Sr. No","Bikes"};
        List<String> Bikes;
        Bikes  = page.SearchElectricBikes();
        List<String[]> dataToWrite=new ArrayList<>();

        for(int i=0;i<Bikes.size();i++){
            dataToWrite.add(
                    new String[]{
                            String.valueOf(i+1),
                            Bikes.get(i)
                    }
            );
        }
        ExcelUtil.writeDynamicDataToExcel( filePath,sheet,header,dataToWrite);
        Assert.assertTrue(dataToWrite.size()>0, "Searching Electric Bikes Failed.");
        System.out.println("TC_15_Search_Electric_Bikes Passed Successfully");

        driver.quit();
    }
}
