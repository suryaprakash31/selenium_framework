package com.qa.utils;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    public static Object[][] getSheetData(String filePath, String sheetName) throws IOException {
        // 1. Open the file and the sheet
        FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet(sheetName);

        // 2. Count the rows (without the heading) and columns
        int rowCount = sheet.getLastRowNum();
        int colCount = sheet.getRow(0).getLastCellNum();

        // 3. Prepare an empty table of the right size
        Object[][] data = new Object[rowCount][colCount];
        DataFormatter formatter = new DataFormatter();

        // 4. Copy every cell into the table (row 0 is the heading, so start at 1)
        for (int i = 1; i <= rowCount; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < colCount; j++) {
                data[i - 1][j] = formatter.formatCellValue(row.getCell(j));
            }
        }

        // 5. Close the file and hand the table back
        workbook.close();
        file.close();
        return data;
    }
}