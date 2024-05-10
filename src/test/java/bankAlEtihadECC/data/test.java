package bankAlEtihadECC.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class test {

	public static void main(String[] args) throws IOException {
		
		
		ArrayList<String> d = new ArrayList<String>();

		FileInputStream fis = new FileInputStream(
				"C:\\Users\\Administrator\\eclipse-workspace\\ExcelDriven\\src\\test\\resources\\demodata1.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		int sheets = workbook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase("Outword Cheques")) {
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
				// purchase test case row
				while (rows.hasNext()) {
					Row r = rows.next();
					if (r.getCell(column).getStringCellValue().equalsIgnoreCase("Y")) {
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
		System.out.println(d);

	}

}
