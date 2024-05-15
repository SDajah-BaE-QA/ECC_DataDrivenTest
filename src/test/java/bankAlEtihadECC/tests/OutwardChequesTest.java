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

	@Test(dataProvider = "getOutwordData")
	public void outwardChequesTest(HashMap<String, String> input) throws IOException, InterruptedException {
		landingPage.loginApplication(input.get("URL"), input.get("UserName"), input.get("Password"));
		OutwardPage outwardPage = new OutwardPage(driver);
		outwardPage.createBatch("outwardChequesTest", input.get("Account Number"), input.get("Amount1"),
				input.get("Amount2"));
		String sequence = outwardPage.chequeInfo(input.get("Cheque Number1"), input.get("Amount1"),
				input.get("Cheque Number2"), input.get("Amount2"));
		outwardPage.qualityAssuranceAccept(sequence);
		// test
	}

	@Test(dataProvider = "getPDCData", enabled = false)
	public void repairCheques(HashMap<String, String> input) throws InterruptedException, IOException {
		landingPage.loginApplication(input.get("URL"), input.get("UserName"), input.get("Password"));
		OutwardPage outwardPage = new OutwardPage(driver);
		outwardPage.createBatch("outwardChequesTest", input.get("Account").toString(), input.get("Amount1").toString(),
				input.get("Amount2").toString());
		String sequence = outwardPage.chequeInfo(input.get("Cheque1").toString(), input.get("Amount1").toString(),
				input.get("Cheque2").toString(), input.get("Amount2").toString());
	}

	@DataProvider
	public Object[][] getOutwordData() throws IOException {
		String jsonContent = FileUtils.readFileToString(
				new File(System.getProperty("user.dir") + "\\src\\test\\java\\bankAlEtihadECC\\data\\TestData.json"),
				StandardCharsets.UTF_8);
		JsonPath js = new JsonPath(jsonContent);
		DataDriven data = new DataDriven();
		ArrayList<HashMap<String, String>> userInfo = data.getUserData();
		ArrayList<HashMap<String, String>> testInfo = data.getData(js.getString("RunStat[0]"),
				js.getString("SheetName[0]"));
		Object[][] testDataArray = new Object[testInfo.size()][1];
		for (int i = 0; i < testInfo.size(); i++) {
			HashMap<String, String> rowDataMap = testInfo.get(i);
			HashMap<String, String> additionalDataMap = userInfo.get(0);
			rowDataMap.putAll(additionalDataMap);
			testDataArray[i][0] = rowDataMap;
			System.out.println(testDataArray[i][0]);
		}
		return testDataArray;
	}

	@DataProvider
	public Object[][] getPDCData() throws IOException {
		String jsonContent = FileUtils.readFileToString(
				new File(System.getProperty("user.dir") + "\\src\\test\\java\\bankAlEtihadECC\\data\\TestData.json"),
				StandardCharsets.UTF_8);
		JsonPath js = new JsonPath(jsonContent);
		DataDriven data = new DataDriven();
		ArrayList<HashMap<String, String>> userInfo = data.getUserData();
		ArrayList<HashMap<String, String>> testInfo = data.getData(js.getString("RunStat[0]"),
				js.getString("SheetName[0]"));
		Object[][] testDataArray = new Object[testInfo.size()][1];
		for (int i = 0; i < testInfo.size(); i++) {
			HashMap<String, String> rowDataMap = testInfo.get(i);
			HashMap<String, String> additionalDataMap = userInfo.get(0);
			rowDataMap.putAll(additionalDataMap);
			testDataArray[i][0] = rowDataMap;
			System.out.println(testDataArray[i][0]);
		}
		return testDataArray;
	}
}
