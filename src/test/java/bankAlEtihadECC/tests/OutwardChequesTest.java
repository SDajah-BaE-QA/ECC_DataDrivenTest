package bankAlEtihadECC.tests;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.testng.annotations.DataProvider;

import bankAlEtihadECC.data.DataDriven;
import bankAlEtihadECC.pageObjects.OutwardPage;
import bankAlEtihadECC.testComponents.BaseTest;
import bankAlEtihadECC.testComponents.Listeners;
import bankAlEtihadECC.testComponents.Retry;

public class OutwardChequesTest extends BaseTest {

	@Test(dataProvider = "getOutwordData", retryAnalyzer = Retry.class)
	public void outwardChequesTest(HashMap<String, String> input) throws IOException, InterruptedException {
		String sequence = null;
		int size = input.size();
		int totalCheques = 0;
		int totalAmount = 0;
		for (int k = 0; k <= size; k++) {
			String checkAmountKey = "Amount" + k;
			if (input.containsKey(checkAmountKey)) {
				int total = Integer.parseInt(input.get(checkAmountKey).trim());
				totalCheques = totalCheques + 1;
				totalAmount = totalAmount + total;
				System.out.println("total = " + total);
				System.out.println("totalCheques = " + totalCheques);
				System.out.println("totalAmount = " + totalAmount);
			}

		}

		landingPage.loginApplication(input.get("URL"), input.get("UserName"), input.get("Password"));
		Listeners.instanceScreenShot(driver, "Login Page");
		landingPage.login();
		OutwardPage outwardPage = new OutwardPage(driver);
		outwardPage.batchData(totalCheques, totalAmount, input.get("TestCases"), input.get("Account Number"),
				input.get("Amount1"), input.get("Amount2"));
		Listeners.instanceScreenShot(driver, "New Batch");
		outwardPage.createBatch();
		sequence = outwardPage.batchEnter();
		for (int i = 1; i <= totalCheques; i++) {
			String payBankKey = "Pay Bank" + i;
			String payBranchKey = "Pay Branch" + i;
			String payAccountKey = "Pay Account" + i;
			String chequeNumberKey = "Cheque Number" + i;
			String amountKey = "Amount" + i;
			// Debugging statements to verify keys and values
			System.out.println("Pay Bank Key: " + payBankKey + ", Value: " + input.get(payBankKey));
			System.out.println("Pay Branch Key: " + payBranchKey + ", Value: " + input.get(payBranchKey));
			System.out.println("Pay Account Key: " + payAccountKey + ", Value: " + input.get(payAccountKey));
			System.out.println("Cheque Number Key: " + chequeNumberKey + ", Value: " + input.get(chequeNumberKey));
			System.out.println("Amount Key: " + amountKey + ", Value: " + input.get(amountKey));
			outwardPage.chequeInfo(input.get(payBankKey), input.get(payBranchKey), input.get(payAccountKey), input.get(chequeNumberKey), input.get(amountKey));
			Listeners.instanceScreenShot(driver, "Cheque Info");
			outwardPage.checkUpdate();
			if (i < totalCheques)
				outwardPage.nextCheque();

		}
		outwardPage.batchApprove();
		Listeners.instanceScreenShot(driver, "Batch Upload");
		String repairTest = outwardPage.repairChoices(input.get("Repair"));
		outwardPage.qualityAssurance(sequence);
		Listeners.instanceScreenShot(driver, "Quality Assurnamce");
		if (repairTest == "Accept")
			outwardPage.qualityAssuranceAccept(sequence);
		else {
			outwardPage.qualityAssuranceReject(sequence);
			outwardPage.repairTab(sequence);
			Listeners.instanceScreenShot(driver, "Repair Batch");
			outwardPage.approveRepair();
		}
	}

	@DataProvider
	public Object[][] getOutwordData() throws IOException {

		DataDriven data = new DataDriven();
		String module = data.getModule();
		ArrayList<HashMap<String, String>> userInfo = data.getTestInfo();
		ArrayList<HashMap<String, String>> testInfo = data.getData("Y", module);
		testInfo.removeIf(HashMap::isEmpty);
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
