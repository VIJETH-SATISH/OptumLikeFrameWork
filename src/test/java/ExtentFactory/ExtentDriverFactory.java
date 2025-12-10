package ExtentFactory;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import BaseTest.BasePage;
import Utils.ConfigUtil;
import Utils.GlobalParameters;
import cucumber.deps.com.thoughtworks.xstream.InitializationException;
import driver.LocalDeviceDriver;

public class ExtentDriverFactory {
	
	public static String browserType;
	private static final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	
	private ExtentDriverFactory() {
		
	}
	
	static ExtentDriverFactory instance = new ExtentDriverFactory();
//	ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	
	public static ExtentDriverFactory getInstance() {
		return instance;
	}
		
	public static void setDriver(WebDriver InputDriver) {
		driver.set(InputDriver);
	}
	
//	public WebDriver getDriver() {
//		return this.driver.get();
//	}
	
	public static WebDriver getDriver() {
		if(driver.get() != null) {		
			return driver.get();
		} else {
			throw new InitializationException("Driver for Browser Not Initialized!!");
		}
	}
	
	public static WebDriver createAndGetDeviceDriver() throws IOException {
		if(driver.get()!= null) {
			return (WebDriver) driver.get();
		} else {
			System.out.println("Entered Create and Get Driver!!");
			browserType = ConfigUtil.getBrowserType();
			System.out.println("Browser type :"+ browserType);
			GlobalParameters.setBrowserType(browserType);
			String driverInstance = ConfigUtil.getDriverInstance();
			System.out.println("driverInstance :"+ driverInstance);
			if(driverInstance ==  null) {
				driverInstance ="CHROME";
			}
			
			if(driverInstance.equalsIgnoreCase("Local")) {
				LocalDeviceDriver.getLocalDeviceDriver();
			}else {
				System.out.println("Could not read driver instance properly, please verify and re-run!");
			}
			
			BasePage.smallWait.set(new WebDriverWait(getDriver(), Duration.ofSeconds(BasePage.smallWaitTime)));
			BasePage.mediumWait.set(new WebDriverWait(getDriver(), Duration.ofSeconds(BasePage.mediumWaitTime)));
			BasePage.longWait.set(new WebDriverWait(getDriver(), Duration.ofSeconds(BasePage.longWaitTime)));
			return getDriver();
		}
			
	}
	
	public static boolean closeDeviceDriver() {
		try {
			WebDriver currentDriver = getDriver();
			if(currentDriver != null) {
				setDriver((WebDriver) null);
				currentDriver.quit();
			}
			
			return getDriver() == null;
		} catch (InitializationException e) {
			return true;
		}
	}
	
	
	
}
