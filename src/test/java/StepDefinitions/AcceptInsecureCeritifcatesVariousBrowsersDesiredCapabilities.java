package StepDefinitions;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.cucumber.java.en.Given;

public class AcceptInsecureCeritifcatesVariousBrowsersDesiredCapabilities {

	@Given("I write snippet for Accepting Insecure Certificates for various browser via option and desired Capabilities")
	public void AcceptInsecureCerts() {
		/****VIA DESIRED CAPABILITES ***/
//			DesiredCapabilities cap = new DesiredCapabilities();
//			cap.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
//			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		/****"ACCEPT_SSL_CERTS" VIA DESIRED CAPABILITES IS OBSELETE NOW WE NEED TO USE CHORMEOPTIONS 
		 * chromeOptions.setAcceptInsecureCerts(true/false); NOW ***/
//			WebDriver driver = new ChromeDriver(cap);
		
		/****VIA CHROMEOPTIONS	*******/
//			ChromeOptions options = new ChromeOptions();
//			options.setAcceptInsecureCerts(true);
//			WebDriver driver = new ChromeOptions(options);
		/********/
		
		/********/
//			FirefoxOptions options = new FirefoxOptions();
//			options.setAcceptInsecureCerts(true);
//			WebDriver driver = new FirefoxDriver(options);
		/********/
		
		/********/
//			EdgeOptions options = new EdgeOptions();
//			options.setAcceptInsecureCerts(true);
//			WebDriver driver = new EdgeDriver(options);
		/********/
		
		/********/
//			InternetExplorerOptions options = new InternetExplorerOptions();
//			options.setAcceptInsecureCerts(true);
//			WebDriver driver = new InternetExplorerDriver(options);
		/********/
		
		/********/
//			SafariOptions options = new SafariOptions();
//			options.setAcceptInsecureCerts(true);
//			WebDriver driver = new SafariDriver(options);
		/********/
		
		/********/
//			OperaOptions options = new OperaOptions();
//			options.setAcceptInsecureCerts(true);
//			WebDriver driver = new OperaDriver(options);
		/********/
		
		/********/
//			DesiredCapabilities cap = new DesiredCapabilities();
//			cap.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
//			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//			WebDriver driver = new RemoteWebDriver(cap);
		/********/
		
		/********/
//			DesiredCapabilities cap = new DesiredCapabilities();
//			cap.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
//			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//			WebDriver driver = new RemoteWebDriver(cap);
		/********/
		
	
	}
	
}
