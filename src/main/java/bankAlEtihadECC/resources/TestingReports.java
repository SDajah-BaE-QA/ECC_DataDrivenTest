package bankAlEtihadECC.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TestingReports {
	
	public static ExtentReports getReportObject()
	{
		String path = "C:\\Users\\Administrator\\TestData\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Outward Cheques Test Reults");
		reporter.config().setDocumentTitle("Test Reults");
		
		ExtentReports report = new ExtentReports();
		report.attachReporter(reporter);
		report.setSystemInfo("Created By", "Shadi Al Daajah");
		return report;
	}
	

}
