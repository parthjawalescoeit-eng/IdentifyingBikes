package basetest.automation.utility;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelUtil {


    public static void writeDynamicDataToExcel(String filePath, String sheetName, String[] headers, List<String[]> dataRows) {
        Workbook workbook;
        File file = new File(filePath);

        try {
            if (file.exists()) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    workbook = WorkbookFactory.create(fis);
                }
            } else {
                workbook = new XSSFWorkbook();
            }

            int sheetIndex = workbook.getSheetIndex(sheetName);
            if (sheetIndex != -1) workbook.removeSheetAt(sheetIndex);
            Sheet sheet = workbook.createSheet(sheetName);
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                headerRow.createCell(i).setCellValue(headers[i]);
            }
            for (int i = 0; i < dataRows.size(); i++) {
                Row row = sheet.createRow(i + 1);
                String[] rowData = dataRows.get(i);

                for (int j = 0; j < rowData.length; j++) {
                    row.createCell(j).setCellValue(rowData[j]);
                }
            }

            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
            workbook.close();
            System.out.println("Excel updated successfully with " + headers.length + " columns.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}