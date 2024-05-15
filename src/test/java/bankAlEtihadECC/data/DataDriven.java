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

	public ArrayList<HashMap<String, String>> getData(String runStat, String sheetName) throws IOException {
		ArrayList<HashMap<String, String>> data = new ArrayList<>();
		FileInputStream fis = new FileInputStream("C:\\Users\\Administrator\\demodata1.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
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
			data.add(rowData);
		}

		return data;
	}

	public ArrayList getUserData() throws IOException {

		ArrayList<HashMap<String, String>> userData = new ArrayList<>();
		FileInputStream fis = new FileInputStream("C:\\Users\\Administrator\\demodata1.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("LoginInfo");
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

		return userData;

	}
}
