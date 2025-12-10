package ExtentFactory;

import com.aventstack.extentreports.ExtentTest;

public class ExtentReportFactory {

	private final static ExtentReportFactory instance = new ExtentReportFactory();
	
	private ExtentReportFactory() {
		
	}
		
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	public static ExtentReportFactory getInstance() {
		return instance;
	}
	
	public void setExtentTest(ExtentTest testReport) {
		this.extentTest.set(testReport);
	}
	
	public ExtentTest getExtentTest() {
		return this.extentTest.get();
	}
	
	
}
