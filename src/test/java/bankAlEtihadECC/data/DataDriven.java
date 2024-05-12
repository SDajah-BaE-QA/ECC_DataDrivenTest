package bankAlEtihadECC.data;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import io.restassured.path.json.JsonPath;

public class DataDriven {

	// Identify TestCaes column by scanning the entire list row
	// once column is identified then scan entire Testcase column to identify
	// purchase test case row
	// after you grab the purchase row = pull the data of that row and feed into
	// test


	public ArrayList getData(String RunStat, String sheetName) throws IOException {
		ArrayList<String> d = new ArrayList<String>();

		FileInputStream fis = new FileInputStream("C:\\Users\\Administrator\\demodata1.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		int sheets = workbook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				// indentify testcases column by scanning the entire list row

				Iterator<Row> rows = sheet.iterator();
				Row firstRow = rows.next();
				Iterator<Cell> cell = firstRow.cellIterator();
				int k = 0;
				int column = 0;
				while (cell.hasNext()) {
					Cell value = cell.next();
					if (value.getStringCellValue().equalsIgnoreCase("RunStat")) {
						column = k;
					}
					k++;
				}
				
				// once column is identified then scan entire Testcase column to identify
				while (rows.hasNext()) {
					Row r = rows.next();
					if (r.getCell(column).getStringCellValue().equalsIgnoreCase(RunStat)) {
						// after you grab the purchase row = pull the data of that row and feed into
						// test
						Iterator<Cell> cv = r.cellIterator();
						while (cv.hasNext()) {

							Cell c = cv.next();
							if (c.getCellType() == CellType.STRING) {
								d.add(c.getStringCellValue());

							} else {
								d.add(NumberToTextConverter.toText(c.getNumericCellValue()));

							}
						}

					}

				}

			}
		}
		return d;
	}
	
	
	
	public ArrayList getUserData() throws IOException {
		ArrayList<String> a = new ArrayList<String>();

		FileInputStream fis = new FileInputStream("C:\\Users\\Administrator\\demodata1.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		int sheets = workbook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase("LoginInfo")) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				int rowNumber =sheet.getLastRowNum()-sheet.getFirstRowNum();
						for(i=0; i<=rowNumber;i++)
						{
							a.add(sheet.getRow(i).getCell(1).toString());
						}
			}
		
	}
		return a;

	}
}
