package api.utilities;

import org.apache.poi.ss.usermodel.*;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Reads Excel test cases (ISA) into a list of object arrays.
 */
public class ExcelISAReader {

    private final String excelPath;

    public ExcelISAReader(String excelPath) {
        this.excelPath = excelPath;
    }

    public List<Object[]> getSheetData(String sheetName) {
        List<Object[]> data = new ArrayList<>();

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(excelPath)) {
            if (input == null) {
                throw new RuntimeException("Excel file not found: " + excelPath);
            }

            Workbook workbook = WorkbookFactory.create(input);
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Sheet not found: " + sheetName);
            }

            Iterator<Row> rowIterator = sheet.iterator();
            Row headerRow = rowIterator.next(); // skip header

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                List<String> rowData = new ArrayList<>();

                for (Cell cell : row) {
                    cell.setCellType(CellType.STRING);
                    rowData.add(cell.getStringCellValue().trim());
                }
                data.add(new Object[]{rowData.toArray(new String[0])});
            }

            workbook.close();
        } catch (Exception e) {
            throw new RuntimeException("Failed to read Excel: " + excelPath, e);
        }

        return data;
    }
}
