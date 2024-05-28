package org.example.Data_Driven_Testing;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    public static Object[][] getExcelData(String filePath, String sheetName) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

        List<Object[]> data = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();

        for (int i = 1; i < rowCount; i++) { // Start from 1 to skip the header row
            Row row = sheet.getRow(i);
            String[] rowData = new String[colCount];
            for (int j = 0; j < colCount; j++) {
                rowData[j] = formatter.formatCellValue(row.getCell(j));
            }
            data.add(rowData);
        }

        workbook.close();
        fileInputStream.close();
        return data.toArray(new Object[0][0]);
    }
}

