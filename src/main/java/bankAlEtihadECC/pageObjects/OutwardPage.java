package bankAlEtihadECC.pageObjects;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import bankAlEtihadECC.abstractComponents.AbstractComponents;
import bankAlEtihadECC.resources.TestingReports;

public class OutwardPage extends AbstractComponents {

	WebDriver driver;

	public OutwardPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "btnCollaps")
	private WebElement collaps;
	@FindBy(id = "txt_id_1010000_10")
	private WebElement outwardTab;
	@FindBy(xpath = "//span[@id='1010010']")
	private WebElement imgCapture;
	@FindBy(id = "batchListCreateBatch")
	private WebElement createBatchBtn;
	@FindBy(id = "accountNo1")
	private WebElement accountNo1;
	@FindBy(id = "create-totalAmount")
	private WebElement createTotalAmount;
	@FindBy(id = "create-totalCount")
	private WebElement createTotalCount;
	@FindBy(id = "create-batch-scan")
	private WebElement createBatchScan;
	By batches = By.xpath("//*[@id='Batches']/tbody/tr[2]/td/table/tbody/tr[1]/td[contains(@onmouseover,'hand')]");
	@FindBy(name = "sequence")
	private WebElement batchSequence;
	@FindBy(xpath = "//*[@id='Batches']/tbody/tr[2]/td/table/tbody/tr[1]/td[contains(@onmouseover,'hand')]")
	private WebElement batches1;
	@FindBy(xpath = "//*[@id='ChequeInfoTagEditButton']")
	private WebElement chequeInfoTagEditButton;
	@FindBy(id = "serial")
	private WebElement chqSerial;
	@FindBy(id = "payBankCodeInputFieldId")
	private WebElement payBankCode;
	@FindBy(xpath = "//*[@id='payBranchCodeTextField']")
	private WebElement payBranchCode;
	@FindBy(id = "payAccountNumber")
	private WebElement payAccountNumber;
	@FindBy(id = "transactionCode")
	private WebElement transactionCode;
	@FindBy(id = "amount")
	private WebElement chequeAmount;
	@FindBy(xpath = "//*[@id='buttons'] //div[@class='toolbar'] //a[@id='ChequeInfoTagUpdateCell']")
	private WebElement chequeInfoTagUpdate;
	@FindBy(xpath = "//*[@id='buttons'] //div[@class='toolbar'] //a[@id='ChequeInfoTagNextCell']")
	private WebElement chequeInfoTagNext;
	By editChequeInfo = By.xpath("//*[@id='ChequeInfoTagEditButton']");
	@FindBy(xpath = "//*[@id='Batches']/tbody/tr[1]/td[1]/table/tbody/tr/td[2]")
	private WebElement batchUpdate;
	By batchApprove = By.xpath("//*[@id='BatchInfoApproveCell']");
	@FindBy(xpath = "//span[@id='1010040']")
	private WebElement qualityAssurnace;
	By qualityBatchInforApprove = By.xpath("//*[@id='BatchInfoApproveCell']");
	By qualityBatchInforReject = By.xpath("//*[@id='BatchInfoRejectCell']");
	@FindBy(xpath = "//span[@id='1010030']")
	private WebElement repair;
	@FindBy(name = "chequeIDArr")
	private WebElement chequeSelect;
	@FindBy(css = "input[class='Chkbox']:first-of-type")
	private WebElement firstCheque;
	@FindBy(css = "a[title*='Repair']")
	private WebElement markForRepair;
	@FindBy(id = "ChequesListDeleteCell")
	private WebElement deleteCheq;
	@FindBy(id = "note")
	private WebElement noteTextBox;
	@FindBy(css = "input#totalAmount")
	private WebElement repairTotalAmount;
	@FindBy(css = "input#totalCount")
	private WebElement repairTotalCount;
	@FindBy(xpath = "//*[@id='BatchInfoUpdateCell']")
	private WebElement repairBatchUpdate;
	@FindBy(id = "deleteButton")
	private WebElement deteleCheque;
	@FindBy(id = "txt_id_1040000_10")
	private WebElement reports;

	ExtentTest test;
	ExtentReports report = TestingReports.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public void createBatch(int totalCheques, int totalAmount, String testCaseName, String account, String amount1,
			String amount2) {
		menuFrameToBeAvailable();
		collaps.click();
		outwardTab.click();
		driver.switchTo().frame("mainTree");
		imgCapture.click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("MainFrame");
		createBatchBtn.click();
		accountNo1.sendKeys(account);
		WebElement inputFieldAmount = driver.findElement(By.id("create-totalAmount"));
		((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", inputFieldAmount);
		createTotalAmount.sendKeys(String.valueOf(totalAmount));
		WebElement inputFieldCount = driver.findElement(By.id("create-totalCount"));
		((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", inputFieldCount);
		createTotalCount.sendKeys(String.valueOf(totalCheques));
		createBatchScan.click();

	}

	public String batchEnter() {
		String sequence = batchSequence.getAttribute("value");

		elementToBeClickable(batches);
		batches1.click();
		alertWaitAndAccept();
		chequeInfoTagEditButton.click();
		return sequence;
	}

	public void nextCheque() {
		chequeInfoTagNext.click();
		alertWaitAndAccept();
		elementToBeClickable(editChequeInfo);
	}

	public void chequeInfo(String payAccount, String Cheque, String amount) throws InterruptedException {

		chequeInfoTagEditButton.click();
		WebElement chqAmount = chequeAmount;
		((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", chqAmount);
		chequeAmount.clear();
		chequeAmount.sendKeys(amount);

		if (Cheque == null) {
			Random random = new Random();
			int randomserial1 = 100000 + random.nextInt(900000);
			String chequeSerial = Integer.toString(randomserial1);
			chqSerial.sendKeys(chequeSerial);
		} else
			chqSerial.sendKeys(Cheque);
		WebElement payBankCd = payBankCode;
		((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", payBankCd);
		payBankCode.clear();
		payBankCode.sendKeys("09");
		Thread.sleep(1000);
		WebElement payBranchCd = payBranchCode;
		((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", payBranchCd);
		payBranchCode.clear();
		payBranchCode.sendKeys("1010");
		if (payAccount == null) {
			Random random = new Random();
			int randomserial1 = 1000000000 + random.nextInt(900000000);
			String serial = Integer.toString(randomserial1);
			payAccountNumber.sendKeys(serial);
		} else
			payAccountNumber.sendKeys(payAccount);
		transactionCode.sendKeys("001");
		driver.switchTo().defaultContent();
		driver.switchTo().frame("MainFrame");
		try {
			chequeInfoTagUpdate.click();
			Thread.sleep(1000);
			if (payBranchCode.getText().equalsIgnoreCase("1010"))
				;
			Thread.sleep(1000);
		} catch (Exception e) {

			WebElement payBankCd1 = payBankCode;
			((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", payBankCd1);
			payBankCode.clear();
			payBankCode.sendKeys("09");
			Thread.sleep(1000);
			WebElement payBranchCd1 = payBranchCode;
			((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", payBranchCd1);
			payBranchCode.clear();
			payBranchCode.sendKeys("1010");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("MainFrame");
		}
		try {
			chequeInfoTagUpdate.click();
			Thread.sleep(1000);
			if (payBranchCode.getText().equalsIgnoreCase("1010"));
			Thread.sleep(1000);
		} catch (Exception e) {

			WebElement payBankCd1 = payBankCode;
			((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", payBankCd1);
			payBankCode.clear();
			payBankCode.sendKeys("09");
			Thread.sleep(1000);
			WebElement payBranchCd1 = payBranchCode;
			((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", payBranchCd1);
			payBranchCode.clear();
			payBranchCode.sendKeys("1010");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("MainFrame");
			elementToBeClickableW(chequeInfoTagUpdate);
		}

	}

	public void batchApprove() throws InterruptedException {
		driver.switchTo().defaultContent();
		driver.switchTo().frame("MainFrame");
		elementToBeClickableW(chequeInfoTagUpdate);
		chequeInfoTagUpdate.click();
		batchUpdate.click();
		// String sequence = batchSequence.getAttribute("value");
		elementToBeClickable(batchApprove);
		Thread.sleep(1000);
		// return sequence;
	}

	public String repairChoices(String repair) throws InterruptedException {
		String repairStatus = null;
		if (repair.equalsIgnoreCase("Y"))
			repairStatus = "Send to Repair";
		else
			repairStatus = "Accept";
		return repairStatus;

	}

	public void qualityAssuranceAccept(String sequence) {
		driver.switchTo().defaultContent();
		driver.switchTo().frame("menu");
		collaps.click();
		driver.switchTo().frame("mainTree");
		qualityAssurnace.click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("MainFrame");
		String batchNumber = String.format("//a[contains(@href,'%s')]", sequence);
		WebElement element = driver.findElement(By.xpath(batchNumber));
		element.click();
		try {
			elementToBeClickable(qualityBatchInforApprove);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void qualityAssuranceReject(String sequence) throws InterruptedException {
		driver.switchTo().defaultContent();
		driver.switchTo().frame("menu");
		collaps.click();
		driver.switchTo().frame("mainTree");
		qualityAssurnace.click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("MainFrame");
		String batchNumber = String.format("//a[contains(@href,'%s')]", sequence);
		WebElement element = driver.findElement(By.xpath(batchNumber));
		element.click();
		firstCheque.click();
		markForRepair.click();
		Thread.sleep(1000);
		elementToBeClickable(qualityBatchInforReject);
		repairTab(sequence);
		qualityAssuranceAccept(sequence);

	}

	public void repairTab(String sequence) {
		driver.switchTo().defaultContent();
		driver.switchTo().frame("menu");
		collaps.click();
		driver.switchTo().frame("mainTree");
		repair.click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("MainFrame");
		String batchNumber = String.format("//a[contains(@href,'%s')]", sequence);
		WebElement element = driver.findElement(By.xpath(batchNumber));
		element.click();
		firstCheque.click();
		deleteCheq.click();
		Set<String> windows = driver.getWindowHandles(); // [parentid,childid,subchildId]
		Iterator<String> it = windows.iterator();
		String parentId = it.next();
		String childId = it.next();
		driver.switchTo().window(childId);
		noteTextBox.sendKeys("Delete The Cheque");
		deteleCheque.click();
		driver.switchTo().window(parentId);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("MainFrame");

		WebElement inputFieldAmount = driver.findElement(By.cssSelector("input#totalAmount"));
		((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", inputFieldAmount);
		String newTotalAmount = driver.findElement(By.xpath("//*[@id='$:0']")).getAttribute("value"); // totalAmount
		System.out.println(newTotalAmount);

		repairTotalAmount.sendKeys(newTotalAmount);
		WebElement inputFieldCount = driver.findElement(By.cssSelector("input#totalCount"));
		((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", inputFieldCount);
		String newTotalCount = driver.findElement(By.xpath("//*[@id='actualCount']")).getAttribute("value");
		repairTotalCount.sendKeys(newTotalCount);
		elementToBeClickableW(repairBatchUpdate);
		elementToBeClickable(batchApprove);

	}

	public void masterQuery() {
		driver.switchTo().defaultContent();
		driver.switchTo().frame("menu");
		collaps.click();
		driver.switchTo().frame("mainTree");
		reports.click();

	}

}
