package bankAlEtihadECC.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;

import io.restassured.path.json.JsonPath;

public class test1 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		

			DataDriven users = new DataDriven();
			ArrayList userData = users.getUserData();
			String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\bankAlEtihadECC\\data\\TestData.json"), StandardCharsets.UTF_8);
			JsonPath js = new JsonPath(jsonContent);
			DataDriven data = new DataDriven();
			ArrayList testInfo = data.getData(js.getString("RunStat[0]"), js.getString("SheetName[0]"));
				
	    	HashMap<String, Object> testData = new HashMap<String, Object>();
	    	testData.put("URL", userData.get(0));
	    	testData.put("UserName", userData.get(1));
	    	testData.put("Password", userData.get(2));
	    	testData.put("TestCase", testInfo.get(1));
			testData.put("Account", testInfo.get(2));
			testData.put("Cheque1", testInfo.get(3));
			testData.put("Amount1", testInfo.get(4));
			testData.put("Cheque2", testInfo.get(5));
			testData.put("Amount2", testInfo.get(6));
			
			System.out.println(testData);
			
		

	}

}
