package org.automation.utility;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelUtil {


    public static void writeHeadlinesToExcel(List<String> headlines, String filePath, String memberName) {
        Workbook workbook;
        File file = new File(filePath);

        try {
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                workbook = WorkbookFactory.create(fis);
                fis.close();
            } else {
                workbook = new XSSFWorkbook();
            }

            int sheetIndex = workbook.getSheetIndex(memberName);
            if (sheetIndex != -1) {
                workbook.removeSheetAt(sheetIndex);
            }

            Sheet sheet = workbook.createSheet(memberName);
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Sr. No.");
            headerRow.createCell(1).setCellValue("Content");
            for (int i = 0; i < headlines.size(); i++) {
                Row row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(headlines.get(i));
            }

            sheet.autoSizeColumn(1);
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
                System.out.println("Sheet '" + memberName + "' added/updated in: " + filePath);
            }
            workbook.close();

        } catch (IOException e) {
            System.err.println("Error accessing the Excel file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}