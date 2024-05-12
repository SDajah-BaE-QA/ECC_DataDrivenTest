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

public class OutwardChequesTest extends BaseTest {

<<<<<<< HEAD
	@Test(dataProvider = "getOutwordData")
	public void outwardChequesTest(HashMap<String, Object> input) throws IOException, InterruptedException {
		landingPage.loginApplication(input.get("URL"), input.get("UserName"), input.get("Password"));
		OutwardPage outwardPage = new OutwardPage(driver);
		outwardPage.createBatch("outwardChequesTest", input.get("Account").toString(), input.get("Amount1").toString(),
				input.get("Amount2").toString());
		String sequence = outwardPage.chequeInfo(input.get("Cheque1").toString(), input.get("Amount1").toString(),
				input.get("Cheque2").toString(), input.get("Amount2").toString());
		outwardPage.qualityAssuranceAccept(sequence);
		// test
	}

	@Test(dataProvider = "getPDCData")
	public void repairCheques(HashMap<String, String> input) throws InterruptedException {
		landingPage.loginApplication(input.get("URL"), input.get("UserName"), input.get("Password"));
		OutwardPage outwardPage = new OutwardPage(driver);
		outwardPage.createBatch("outwardChequesTest", input.get("Account").toString(), input.get("Amount1").toString(),
				input.get("Amount2").toString());
		String sequence = outwardPage.chequeInfo(input.get("Cheque1").toString(), input.get("Amount1").toString(),
				input.get("Cheque2").toString(), input.get("Amount2").toString());
=======

public class OutwardChequesTest extends BaseTest{
	
	public OutwardChequesTest() throws IOException, InterruptedException {
	String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\bankAlEtihadECC\\data\\TestData.json"), StandardCharsets.UTF_8);
	JsonPath js = new JsonPath(jsonContent);
		

	if(js.getString("SheetName[0]")=="Outword Cheques")
			{
		      System.out.println("this is outword test");
		outwardChequesTest(null);
		      
		      
			}
	
	}

	@Test(dataProvider= "getData")
	public void outwardChequesTest(HashMap<String,Object> input) throws IOException, InterruptedException
	{
		landingPage.loginApplication(input.get("UserName"), input.get("Password"));
		OutwardPage outwardPage=new OutwardPage(driver);
		outwardPage.createBatch(input.get("Account").toString(),input.get("Amount1").toString(),input.get("Amount2").toString());
		String sequence= outwardPage.chequeInfo(input.get("Cheque1").toString(),input.get("Amount1").toString(), input.get("Cheque2").toString(),input.get("Amount2").toString());
		outwardPage.qualityAssuranceAccept(sequence);      
		//test
	}
	
	@Test(dataProvider= "getData", enabled=false)
	public void repairCheques(HashMap<String,String> input) throws InterruptedException
	{
		landingPage.loginApplication(input.get("UserName"), input.get("Password"));
		OutwardPage outwardPage=new OutwardPage(driver);
		outwardPage.createBatch(input.get("Account").toString(),input.get("Amount1").toString(),input.get("Amount2").toString());
		String sequence= outwardPage.chequeInfo(input.get("Cheque1").toString(),input.get("Amount1").toString(), input.get("Cheque2").toString(),input.get("Amount2").toString());
>>>>>>> 95845b6 (save)
		outwardPage.qualityAssuranceReject(sequence);
		outwardPage.repairTab(sequence);
		outwardPage.qualityAssuranceAccept(sequence);
		System.out.println("Test is Passed");
	}

	@DataProvider
	public Object[][] getOutwordData() throws IOException {

		DataDriven users = new DataDriven();
		ArrayList userData = users.getUserData();
		String jsonContent = FileUtils.readFileToString(
				new File(System.getProperty("user.dir") + "\\src\\test\\java\\bankAlEtihadECC\\data\\TestData.json"),
				StandardCharsets.UTF_8);
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

		HashMap<String, Object> testData1 = new HashMap<String, Object>();
		testData1.put("URL", userData.get(0));
		testData1.put("UserName", userData.get(1));
		testData1.put("Password", userData.get(2));
		testData1.put("TestCase", testInfo.get(8));
		testData1.put("Account", testInfo.get(9));
		testData1.put("Cheque1", testInfo.get(10));
		testData1.put("Amount1", testInfo.get(11));
		testData1.put("Cheque2", testInfo.get(12));
		testData1.put("Amount2", testInfo.get(13));

		return new Object[][] { { testData }, { testData1 } };
	}

	@DataProvider
	public Object[][] getPDCData() throws IOException {

		DataDriven users = new DataDriven();
		ArrayList userData = users.getUserData();
		String jsonContent = FileUtils.readFileToString(
				new File(System.getProperty("user.dir") + "\\src\\test\\java\\bankAlEtihadECC\\data\\TestData.json"),
				StandardCharsets.UTF_8);
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

		HashMap<String, Object> testData1 = new HashMap<String, Object>();
		testData1.put("URL", userData.get(0));
		testData1.put("UserName", userData.get(1));
		testData1.put("Password", userData.get(2));
		testData1.put("TestCase", testInfo.get(8));
		testData1.put("Account", testInfo.get(9));
		testData1.put("Cheque1", testInfo.get(10));
		testData1.put("Amount1", testInfo.get(11));
		testData1.put("Cheque2", testInfo.get(12));
		testData1.put("Amount2", testInfo.get(13));

		return new Object[][] { { testData }, { testData1 } };

	}

}
