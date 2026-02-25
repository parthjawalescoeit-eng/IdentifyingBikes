package org.automation.utility;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelUtil {

    public static void writeHeadlinesToExcel(List<String> headlines, String filePath) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("News Headlines");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Sr. No.");
        headerRow.createCell(1).setCellValue("Headline Text");

        for (int i = 0; i < headlines.size(); i++) {
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(i + 1);
            row.createCell(1).setCellValue(headlines.get(i));
        }

        sheet.autoSizeColumn(1);
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            System.out.println("Excel file created successfully at: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
