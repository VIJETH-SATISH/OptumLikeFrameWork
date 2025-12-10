package BaseTest;

import java.io.IOException;
import java.time.Duration;
import java.util.Locale;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import ExtentFactory.ExtentDriverFactory;
import Utils.ConfigUtil;

public class BasePage {
	public static final int smallWaitTime = 10;
	public static final int mediumWaitTime = 20;
	public static final int longWaitTime = 30;
	public static final ThreadLocal<WebDriverWait> smallWait = new ThreadLocal();
	public static final ThreadLocal<WebDriverWait> mediumWait = new ThreadLocal();
	public static final ThreadLocal<WebDriverWait> longWait = new ThreadLocal();
	public WebDriver driver;
	
	public BasePage() {
		try {
//			this.driver = ExtentDriverFactory.getInstance().getDriver();	
			this.driver = ExtentDriverFactory.createAndGetDeviceDriver();
			PageFactory.initElements(this.driver, this);
			this.driver.manage().window().maximize();
			this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10L));
		}catch(Exception var2) {
			Exception e = var2;
			Assert.fail("Execption occured from selenium Utility Driver initialization [" + e.getMessage() + "]");
			
		}
		
	}
	
	public void launchBrowserWithURL() {
		String env;
		try {
			env = ConfigUtil.getEnvironment();
//			this.driver.get(ConfigUtil.getConfigProperty(env.toUpperCase(Locale.ROOT)+"_URL"));
			this.driver.get("https://google.co.in");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
}
