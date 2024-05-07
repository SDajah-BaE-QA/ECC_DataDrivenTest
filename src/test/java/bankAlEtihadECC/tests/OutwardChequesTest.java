package bankAlEtihadECC.tests;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.DataProvider;

import bankAlEtihadECC.data.DataDriven;
import bankAlEtihadECC.pageObjects.OutwardPage;
import bankAlEtihadECC.testComponents.BaseTest;
import io.restassured.path.json.JsonPath;



public class OutwardChequesTest extends BaseTest{

	@Test(dataProvider= "getData")
	public void outwardChequesTest(HashMap<String,Object> input) throws IOException, InterruptedException
	{
		landingPage.loginApplication(input.get("UserName"), input.get("Password"));
		OutwardPage outwardPage=new OutwardPage(driver);
		outwardPage.createBatch();
		String sequence= outwardPage.chequeInfo();
		outwardPage.qualityAssuranceAccept(sequence);      
		//test
	}
	
	@Test(dataProvider= "getData")
	public void repairCheques(HashMap<String,String> input) throws InterruptedException
	{
		landingPage.loginApplication(input.get("userLoginName"), input.get("userPass"));
		OutwardPage outwardPage=new OutwardPage(driver);
		outwardPage.createBatch();
		String sequence= outwardPage.chequeInfo();
		outwardPage.qualityAssuranceReject(sequence);
		outwardPage.repairTab(sequence);
		outwardPage.qualityAssuranceAccept(sequence); 
		System.out.println("Test is Passed");
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		DataDriven users = new DataDriven();
		ArrayList userData = users.getUserData();
		
		
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\bankAlEtihadECC\\data\\TestData.json"), StandardCharsets.UTF_8);
		
		JsonPath js = new JsonPath(jsonContent);
		
		DataDriven data = new DataDriven();
		ArrayList testInfo = data.getData(js.getString("testCaseName[0]"), js.getString("SheetName[0]"));
			
    	HashMap<String, Object> testData = new HashMap<String, Object>();
    	testData.put("UserName", userData.get(0));
    	testData.put("Password", userData.get(1));
		testData.put("Account", testInfo.get(1));
		testData.put("Cheque1", testInfo.get(2));
		testData.put("Cheque2", testInfo.get(3));
		return new Object[][] {{testData}};

	}
	

}
