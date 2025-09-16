package api.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class ExcelUtils {

    private Workbook workbook;
    private Sheet sheet;

    // ✅ New constructor to accept file path + sheet name
    public ExcelUtils(String filePath, String sheetName) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
        } catch (IOException e) {
            throw new RuntimeException("Error initializing ExcelUtils with file: " + filePath, e);
        }
    }

    // Get test data as Object[][]
    public Object[][] getTestData(String sheetName) {
        Sheet dataSheet = workbook.getSheet(sheetName);
        int rowCount = dataSheet.getPhysicalNumberOfRows();
        int colCount = dataSheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] data = new Object[rowCount - 1][colCount];

        Iterator<Row> rowIterator = dataSheet.iterator();
        rowIterator.next(); // skip header row

        int i = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            for (int j = 0; j < colCount; j++) {
                Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                data[i][j] = getCellValue(cell);
            }
            i++;
        }
        return data;
    }

    private Object getCellValue(Cell cell) {
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> (int) cell.getNumericCellValue();
            case BOOLEAN -> cell.getBooleanCellValue();
            case BLANK -> "";
            default -> "";
        };
    }
}
