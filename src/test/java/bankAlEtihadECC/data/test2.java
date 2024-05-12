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

public class test2 {

	public static void main(String[] args) throws IOException {

		ArrayList<String> a = new ArrayList<String>();

		FileInputStream fis = new FileInputStream(
				"C:\\Users\\Administrator\\eclipse-workspace1\\ECC_DataDrivenTest\\src\\test\\resources\\demodata1.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		int sheets = workbook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase("LoginInfo")) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				// indentify testcases column by scanning the entire list row
				a.add(sheet.getRow(0).getCell(1).toString());
				a.add(sheet.getRow(1).getCell(1).toString());
				a.add(sheet.getRow(2).getCell(1).toString());
				
				Iterator<Row> rows = sheet.iterator();
//				Row firstRow = rows.next();
//				Iterator<Cell> cell = firstRow.cellIterator();
//				System.out.println(cell.next());
//				int k = 0;
//				int column = 0;
//				
//				while (cell.hasNext()) {
//					Cell value = cell.next();
//					if (value.getStringCellValue().equalsIgnoreCase("URL")) {
//						System.out.println(value.getStringCellValue());
//						column = k;
//					
//					}
//					k++;
//				}
//
//				// once column is identified then scan entire Testcase column to identify
//				// purchase test case row
				while (rows.hasNext()) {
					Row r = rows.next();
					
						Iterator<Cell> cv = r.cellIterator();
						while (cv.hasNext()) {

							Cell c = cv.next();
							if (c.getCellType() == CellType.STRING) {
								a.add(c.getStringCellValue());
								

							} else {
								a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
								

							}
						}

					}

				}

			

			
		}

		System.out.println(a);
		}
	
}
