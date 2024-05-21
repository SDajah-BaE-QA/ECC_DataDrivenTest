package bankAlEtihadECC.tests;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.testng.annotations.DataProvider;

import bankAlEtihadECC.data.DataDriven;
import bankAlEtihadECC.pageObjects.OutwardPage;
import bankAlEtihadECC.testComponents.BaseTest;

public class OutwardChequesTest extends BaseTest {

	@Test(dataProvider = "getOutwordData")
	public void outwardChequesTest(HashMap<String, String> input) throws IOException, InterruptedException {
		String sequence = null;
		int size = input.size();
		int totalCheques = 0;
		int totalAmount = 0;
		for (int k = 0; k <= size; k++) {
			String checkAmountKey = "Amount" + k;
			if (input.containsKey(checkAmountKey)) {
				int total = Integer.parseInt(input.get(checkAmountKey));
				totalCheques = totalCheques + 1;
				totalAmount = totalAmount + total;
				System.out.println("total = " + total);
				System.out.println("totalCheques = " + totalCheques);
				System.out.println("totalAmount = " + totalAmount);
			}

		}

		landingPage.loginApplication(input.get("URL"), input.get("UserName"), input.get("Password"));
		OutwardPage outwardPage = new OutwardPage(driver);
		outwardPage.createBatch(totalCheques, totalAmount, "outwardChequesTest", input.get("Account Number"),
				input.get("Amount1"), input.get("Amount2"));
		sequence = outwardPage.batchEnter();
		for (int i = 1; i <= totalCheques; i++) {
			String payAccountKey = "Pay Account" + i;
			String chequeNumberKey = "Cheque Number" + i;
			String amountKey = "Amount" + i;
			// Debugging statements to verify keys and values
			System.out.println("Pay Account Key: " + payAccountKey + ", Value: " + input.get(payAccountKey));
			System.out.println("Cheque Number Key: " + chequeNumberKey + ", Value: " + input.get(chequeNumberKey));
			System.out.println("Amount Key: " + amountKey + ", Value: " + input.get(amountKey));
			outwardPage.chequeInfo(input.get(payAccountKey), input.get(chequeNumberKey), input.get(amountKey));
			if (i < totalCheques)
				outwardPage.nextCheque();

		}
		outwardPage.batchApprove();

		String repairTest = outwardPage.repairChoices(input.get("Repair"));

		if (repairTest == "Accept")
			outwardPage.qualityAssuranceAccept(sequence);
		else
			outwardPage.qualityAssuranceReject(sequence);
	}

	@DataProvider
	public Object[][] getOutwordData() throws IOException {

		DataDriven data = new DataDriven();
		String module = data.getModule();
		ArrayList<HashMap<String, String>> userInfo = data.getUserData();
		ArrayList<HashMap<String, String>> testInfo = data.getData("Y", module);
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
