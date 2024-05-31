package bankAlEtihadECC.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDriven {

	public ArrayList<HashMap<String, String>> getData(String runStat, String sheetName) throws IOException {
		ArrayList<HashMap<String, String>> data = new ArrayList<>();
		FileInputStream fis = new FileInputStream("C:\\Users\\Administrator\\TestData\\demodata1.xlsx");
		try (XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
			XSSFSheet sheet = workbook.getSheet(sheetName);
			Iterator<Row> rows = sheet.iterator();
			Row headerRow = rows.next();

			while (rows.hasNext()) {
				Row row = rows.next();
				HashMap<String, String> rowData = new HashMap<>();
				
				for (int i = 0; i < headerRow.getLastCellNum(); i++) {
					Cell cell = row.getCell(i);
					Cell headerCell = headerRow.getCell(i);

					String header = headerCell.getStringCellValue();
					String value = "";
					if(row.getCell(0).getStringCellValue().equals("Y"))//run stat
					{
					if (cell != null) {
						switch (cell.getCellType()) {
						case STRING:
							value = cell.getStringCellValue();
							break;
						case NUMERIC:
							value = String.valueOf(cell.getNumericCellValue());
							break;
						case BOOLEAN:
							value = String.valueOf(cell.getBooleanCellValue());
							break;
						default:
							value = null;
						}
						
					}
				}
					else 
						continue;
					rowData.put(header, value);
				}
				data.add(rowData);
			}
		}
		return data;
	}

	public ArrayList<HashMap<String, String>> getTestInfo() throws IOException {

		ArrayList<HashMap<String, String>> userData = new ArrayList<>();
		FileInputStream fis = new FileInputStream("C:\\Users\\Administrator\\TestData\\demodata1.xlsx");
		try (XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
			XSSFSheet sheet = workbook.getSheet("TestInfo");
			Iterator<Row> rows = sheet.iterator();
			Row headerRow = rows.next();

			while (rows.hasNext()) {
				Row row = rows.next();
				HashMap<String, String> rowData = new HashMap<>();

				for (int i = 0; i < headerRow.getLastCellNum(); i++) {
					Cell cell = row.getCell(i);
					Cell headerCell = headerRow.getCell(i);

					String header = headerCell.getStringCellValue();
					String value = "";

					if (cell != null) {
						switch (cell.getCellType()) {
						case STRING:
							value = cell.getStringCellValue();
							break;
						case NUMERIC:
							value = String.valueOf(cell.getNumericCellValue());
							break;
						case BOOLEAN:
							value = String.valueOf(cell.getBooleanCellValue());
							break;
						default:
							value = "";
						}
					}
					rowData.put(header, value);
				}
				userData.add(rowData);
			}
		}
		return userData;

	}
	
	public String getModule() throws IOException {

		
		FileInputStream fis = new FileInputStream("C:\\Users\\Administrator\\TestData\\demodata1.xlsx");
		try (XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
			XSSFSheet sheet = workbook.getSheet("TestInfo");
			Iterator<Row> rows = sheet.iterator();
			rows.next();
			String module = sheet.getRow(1).getCell(3).getStringCellValue();
			return module;
		}
	}
}


