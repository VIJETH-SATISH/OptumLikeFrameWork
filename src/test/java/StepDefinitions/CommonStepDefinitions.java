package StepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import BaseTest.BasePage;
import ExtentFactory.ExtentDriverFactory;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.After;
import org.openqa.selenium.chrome.ChromeDriver;

public class CommonStepDefinitions {
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
	
	@Before
	public void LaunchBrowser() {
//		WebDriver driver = new ChromeDriver();
		
//		System.setProperty("webdriver.edge.driver", "C:\\Users\\V\\Downloads\\edgedriver_win64 (2)\\msedgedriver.exe");
//		WebDriver driver= new EdgeDriver();
//		LoginPageObjectsobj_CYC.launchBrowserWithURL();
//		ExtentDriverFactory.getInstance().setDriver(driver);
	}
	
	@After
	public static void QuitBrowser() {
		ExtentDriverFactory.getDriver().quit();
	}
	
	

}
