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

public class test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		

			
			FileInputStream fis = new FileInputStream("C:\\Users\\Administrator\\demodata1.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("LoginInfo");
			Iterator<Row> rows = sheet.iterator();
			Row headerRow = rows.next();
			String module = sheet.getRow(1).getCell(3).getStringCellValue();
			System.out.println(module);
	
		
//ArrayList<HashMap<String, String>> userData = new ArrayList<>();
//		
//		FileInputStream fis = new FileInputStream("C:\\Users\\Administrator\\demodata1.xlsx");
//		XSSFWorkbook workbook = new XSSFWorkbook(fis);
//		
//		//////////////////////////////////////////////////////////////////////////////////
//		 XSSFSheet sheet = workbook.getSheet("LoginInfo");
//	        Iterator<Row> rows = sheet.iterator();
//	        Row headerRow = rows.next(); // Assuming first row is header
//
//	        while (rows.hasNext()) {
//	            Row row = rows.next();
//	            HashMap<String, String> rowData = new HashMap<>();
//
//	            for (int i = 0; i < headerRow.getLastCellNum(); i++) {
//	                Cell cell = row.getCell(i);
//	                Cell headerCell = headerRow.getCell(i);
//	                
//	                String header = headerCell.getStringCellValue();
//	                String value = "";
//
//	                if (cell != null) {
//	                    switch (cell.getCellType()) {
//	                        case STRING:
//	                            value = cell.getStringCellValue();
//	                            break;
//	                        case NUMERIC:
//	                            value = String.valueOf(cell.getNumericCellValue());
//	                            break;
//	                        case BOOLEAN:
//	                            value = String.valueOf(cell.getBooleanCellValue());
//	                            break;
//	                        default:
//	                            value = "";
//	                    }
//	                }
//	                rowData.put(header, value);
//	            }
//	            userData.add(rowData);
//	        }
//
//	        System.out.println(userData);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//			ArrayList<String> a = new ArrayList<String>();
//
//			FileInputStream fis = new FileInputStream("C:\\Users\\Administrator\\demodata1.xlsx");
//			XSSFWorkbook workbook = new XSSFWorkbook(fis);
//			int sheets = workbook.getNumberOfSheets();
//			for (int i = 0; i < sheets; i++) {
//				if (workbook.getSheetName(i).equalsIgnoreCase("LoginInfo")) {
//					XSSFSheet sheet = workbook.getSheetAt(i);
//					int rowNumber =sheet.getLastRowNum()-sheet.getFirstRowNum();
//							for(i=0; i<=rowNumber;i++)
//							{
//								a.add(sheet.getRow(i).getCell(1).toString());
//							}
//				}
//					
//			
//		}
//			System.out.println(a);
//			
//
//		
//
//	}

}}
