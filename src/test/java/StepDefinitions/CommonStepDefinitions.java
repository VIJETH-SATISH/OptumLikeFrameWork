package StepDefinitions;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import ExtentFactory.ExtentDriverFactory;
import ExtentFactory.ExtentReportFactory;
import Utils.ReusableMethodsUtil;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;

public class CommonStepDefinitions {
	public static ExtentReports extent;// MAIN ENGINE= Controls everything, holds all tests
	public static ExtentSparkReporter spark;// WRITER= Writes HTML report to disk
	public static ExtentTest report; // A SINGLE TEST NODE
	LoginPageObjects_CYC LoginPageObjectsobj_CYC;
	
	public CommonStepDefinitions() {
		LoginPageObjectsobj_CYC = new LoginPageObjects_CYC();
	}
	
	@Given("I am at Google Site")
	public void i_am_at_google_site() {
	    // Write code here that turns the phrase above into concrete actions
		LoginPageObjectsobj_CYC.launchBrowserWithURL();
//		this.driver.get("https://google.co.in");
		
	}

	@BeforeAll
	public static void initExtentTestReport() {
		String reportPath = reportTimeStamp();
		Date date = new Date();
		extent = new ExtentReports();//start the main engine first
		spark = new ExtentSparkReporter(reportPath+new SimpleDateFormat("HH_mm_ss").format(date)+".html");//writeS and stores HTML report at mentioned path
		extent.attachReporter(spark);//ATTACH THE html WRITER TO ENGINE
		//return extent which is be assigend => report
	}
	
	@Before
	public void LaunchBrowser(Scenario scenario) {
//		WebDriver driver = new ChromeDriver();	
//		System.setProperty("webdriver.edge.driver", "C:\\Users\\V\\Downloads\\edgedriver_win64 (2)\\msedgedriver.exe");
//		WebDriver driver= new EdgeDriver();
//		LoginPageObjectsobj_CYC.launchBrowserWithURL();
//		ExtentDriverFactory.getInstance().setDriver(driver);
		report = extent.createTest(scenario.getName());//GET SCENARIO NAME AND CREATE NEW TEST FOR EACH AND EVERY SCENARIO, attaching new test to main engine(with sparking capability) 
		ExtentReportFactory.getInstance().setExtentTest(report);
	
	}
	
	@AfterStep
	public void addScreenshotOnFailure(Scenario scenario) {
		  // Capture Base64 screenshot
		String base64Screenshot = ReusableMethodsUtil.captureScreenshot(ExtentDriverFactory.getInstance().getDriver());
        if (scenario.isFailed()) {
            try {
                // Attach to EXTENT REPORT NODE
                ExtentReportFactory.getInstance().getExtentTest().fail(
                        "Step Failed",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build()
                );

                // Attach to CUCUMBER REPORT
                scenario.attach(
                        Base64.getDecoder().decode(base64Screenshot),
                        "image/png",
                        "Failure Screenshot"
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
        	ExtentReportFactory.getInstance().getExtentTest().pass(
                    "Step Passed",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build()
            );
        }
    }
	
	
	@After
	public static void QuitBrowser() {
		ExtentDriverFactory.getDriver().quit();
	}
	
	@AfterAll
	public static void tearReport() {
		extent.flush();
	}
	public static String reportTimeStamp() {
		String relativePath = "";
		/*absolute file-system path of your project’s working directory.= C:\Users\John\workspace\MyProject */
		relativePath = new File(System.getProperty("user.dir")).getAbsolutePath();/* we warp up in file object as we need to convert to  Converts it to a full, absolute filesystem path using  getAbsolutePath method*/
		if (relativePath.endsWith("bin")) /* check = In case the navigation path ends up in a bin folder */
		{
			relativePath = new File(System.getProperty("user.dir")).getParent(); /*If so = In case the navigation path ends up in a bin folder traverse to the parent folder */
		}
		
		/*  THIS is CHAT GPT suggestions 
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MMM_dd_HH_mm_ss");
			String timestamp = now.format(formatter);
			System.out.println(timestamp);
			String year  = now.format(DateTimeFormatter.ofPattern("yyyy"));
			String month = now.format(DateTimeFormatter.ofPattern("MMM"));
			String day   = now.format(DateTimeFormatter.ofPattern("dd"));
		 */

		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MMM_dd_HH_mm_ss");
		System.out.println(dateFormat.format(date));
		String[] date_arr=  dateFormat.format(date).split("_");
		String year = date_arr[0];
		String month = date_arr[1];
		String day = date_arr[2];
		
		String reportPath = relativePath +"/Reports/"+ year +"/"+ month +"/"+ day;
		boolean success = (new File(reportPath)).mkdirs();
		System.out.println(reportPath+"/"+new SimpleDateFormat("HH_mm_ss").format(date));
		return reportPath+"/";
	}
	
	
	

}
