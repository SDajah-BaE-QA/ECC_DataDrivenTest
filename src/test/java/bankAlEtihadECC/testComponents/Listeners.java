package bankAlEtihadECC.testComponents;

import java.io.IOException;
import java.util.Arrays;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import bankAlEtihadECC.resources.TestingReports;

public class Listeners extends BaseTest implements ITestListener {

	static ExtentTest test;
	ExtentReports report = TestingReports.getReportObject();
	static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	String testCase;

	@Override
	public void onTestStart(ITestResult result) {

		Object[] parameters = result.getParameters();
		String testCase = extractTestNameFromParameters(parameters);
		test = report.createTest(testCase);
		extentTest.set(test);

	}

	private String extractTestNameFromParameters(Object[] parameters) {
		String myString = Arrays.toString(parameters);
		String[] splittedString = myString.split(",");

		System.out.println(myString);
		System.out.println(parameters);
		for (int i = 0; i < splittedString.length; i++) {
			for (int j = 0; j <= 1; j++) {
				String[] ts = splittedString[i].split("=");
				if (ts[0].contains("Test Case")) {
					testCase = ts[1];
					System.out.println(testCase);
					break;
				}
			}
		}
		return testCase;
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.set(test);
		extentTest.get().log(Status.PASS, "Test is Passed");
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String filePath = null;
		try {
			filePath = getScreenshot(testCase, driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, testCase);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.set(test);
		test.log(Status.FAIL, "Test is Failed");
		extentTest.get().fail(result.getThrowable());
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String filePath = null;
		try {
			filePath = getScreenshot(testCase, driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, testCase);

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.set(test);
		test.log(Status.FAIL, "Test is Skipped");
		extentTest.get().fail(result.getThrowable());
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String filePath = null;
		try {
			filePath = getScreenshot(testCase, driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, testCase);

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		extentTest.set(test);
		test.log(Status.FAIL, "Test is Failed with UnExpected Error");
		extentTest.get().fail(result.getThrowable());
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String filePath = null;
		try {
			filePath = getScreenshot(testCase, driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, testCase);

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		extentTest.set(test);
		test.log(Status.FAIL, "Test is Timeout");
		extentTest.get().fail(result.getThrowable());
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String filePath = null;
		try {
			filePath = getScreenshot(testCase, driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, testCase);

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		report.flush();

	}

	public static void instanceScreenShot(WebDriver driver, String testName) {
		extentTest.set(test);
		test.log(Status.INFO, "Step is Passed");

		String filePath = null;
		try {
			filePath = getScreenshot(testName, driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, testName);
	}

}
