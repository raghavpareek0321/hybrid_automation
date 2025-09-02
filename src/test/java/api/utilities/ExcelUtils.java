package api.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {

    // Path to your Excel file
    private static final String FILE_PATH = "testData/UserData.xlsx";

    /** Returns total number of rows in the sheet */
    public static int getRowCount(String sheetName) {
        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) return 0;
            return sheet.getPhysicalNumberOfRows();

        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /** Returns total number of columns in the sheet (based on first row) */
    public static int getColumnCount(String sheetName) {
        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) return 0;

            Row row = sheet.getRow(0);
            if (row == null) return 0;

            return row.getLastCellNum();

        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /** Reads a specific cell value by row and column index */
    public static String getCellData(String sheetName, int rowIndex, int colIndex) {
        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) return "";

            Row row = sheet.getRow(rowIndex);
            if (row == null) return "";

            Cell cell = row.getCell(colIndex);
            if (cell == null) return "";

            cell.setCellType(CellType.STRING);
            return cell.getStringCellValue().trim();

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /** Returns all sheet data as Object[][] for TestNG DataProvider */
    public static Object[][] getTestData(String sheetName) {
        Object[][] data = null;

        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null)
                throw new RuntimeException("Sheet not found: " + sheetName);

            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getLastCellNum();

            data = new Object[rowCount - 1][colCount]; // skip header row

            for (int i = 1; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < colCount; j++) {
                    Cell cell = row.getCell(j);
                    if (cell == null) data[i - 1][j] = "";
                    else {
                        cell.setCellType(CellType.STRING);
                        data[i - 1][j] = cell.getStringCellValue().trim();
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading Excel file: " + FILE_PATH);
        }

        return data;
    }
}
