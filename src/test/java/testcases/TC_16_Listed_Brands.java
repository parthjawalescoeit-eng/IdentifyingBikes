package testcases;

import basetest.BaseTest;
import org.automation.log.Log;
import org.automation.pages.ListedBrands;
import org.automation.utility.ExcelUtil;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

public class TC_16_Listed_Brands extends BaseTest {

    @Test
    public void getBrandNames() throws InterruptedException {
        ListedBrands lb = new ListedBrands(driver,wait);
        List<String> brands=new ArrayList<>();
        List<String[]> dataToWrite=new ArrayList<>();
        brands=lb.getAllBrands();
        String [] headers={"Sr.No","Brand Name"};
        for(int i=0;i<brands.size();i++){
            dataToWrite.add(
                    new String[]{
                            String.valueOf(i+1),
                            brands.get(i)
                    }
            );
        }

        String filePath=System.getProperty("user.dir")+"/ExcelData/BikeReport.xlsx";
        String sheetName="Sheet_4";
        ExcelUtil.writeDynamicDataToExcel(filePath,sheetName,headers,dataToWrite);
        Log.info("Data Written To Excel Successfully");
    }
}
