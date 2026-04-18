package utils;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	 public static String getData(String sheet, int row, int col) {
	        try {
	            FileInputStream fis = new FileInputStream("src/test/resources/testdata/testcase.xlsx");
	            Workbook wb = WorkbookFactory.create(fis);

	            Cell cell = wb.getSheet(sheet).getRow(row).getCell(col);
	            String data = (cell == null) ? "" : cell.toString();

	            wb.close();
	            return data;

	        } catch (Exception e) {
	            e.printStackTrace();
	            return "";
	        }
	    }
}
