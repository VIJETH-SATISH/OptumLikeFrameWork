package StepDefinitions;

import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import BaseTest.BasePage;
import PageObject.HomePagePageObjects;
import io.cucumber.java.en.Given;
import ExtentFactory.ExtentDriverFactory;

public class HomePageStepDefinitions {
	
	HomePagePageObjects homePagePageObjects;
	WebDriver driver = ExtentDriverFactory.getInstance().getDriver();
	ThreadLocal<String> parentWindow = new ThreadLocal<String>();
	
	
	public HomePageStepDefinitions() {
		 homePagePageObjects = new HomePagePageObjects();
	}
	
	@Given("I am on the cutomized Page")
	public void visitCustomHTMLPage() {
		this.driver.get("E:\\HTML Custom Pages Used For Selenium\\RemoveElementAndClick.html");
	}
	
	@Given("I am on the cutomized Page with DisabledElement")
	public void visitCustomHTMLPageWithDisableElement() {
		this.driver.get("E:\\HTML Custom Pages Used For Selenium\\DisabledElementAttributeRemoved.html");
	}
	
	@Given("I am on the spiceJet site")
	public void VisitspiceJetsite() {
		this.driver.get("https://www.spicejet.com/");
	}
	
	@Given("I am on the cutomized Page with ReadOnly Element")
	public void VisitReadonlyPage() {
		this.driver.get("E:\\HTML Custom Pages Used For Selenium\\ReadOnlyElement.html");
	}
	
	@Given("I am the amazon site")
	public void i_am_the_amazon_site() {
	    // Write code here that turns the phrase above into concrete actions			
		this.driver.get("https://tus.io/demo");
	}
	
	@Given("I enter the searhbox with required product")
	public void i_enter_the_searhbox_with_required_product() {
	    // Write code here that turns the phrase above into concrete actions
	    
	}
	
	
	
	@Given("I open new Tab and open a new url")
	public void i_open_new_tab_and_open_a_new_url() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		parentWindow.set(driver.getWindowHandle());
		Thread.sleep(3000);
		OpenNewTab(driver, parentWindow.get());		
		launchTheSitewithURL(driver,"https://accounts.google.com/v3/signin/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&ifkv=AdF4I76cEaDd83Gn16sHvMRNznbelmZbHnPBTic8onc8OOfz_7ZpJyaGPkXp7Jt98g4umMH195vDCw&rip=1&sacu=1&service=mail&flowName=GlifWebSignIn&flowEntry=ServiceLogin&dsh=S272343717%3A1721574602270447&ddm=0");
		homePagePageObjects.EnterTheCreds();
		SwitchToMainWindow(parentWindow.get(), driver);
		homePagePageObjects.EnterTheSearchContent();
		
		
	}
	
	public void OpenNewTab(WebDriver driver, String sessionId) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.open()");
		Thread.sleep(5000);
		SwitchWindows(driver, sessionId);
		
	}
	
	public void SwitchWindows(WebDriver driver, String parentId) {		
		Set<String> child_windows = driver.getWindowHandles();
		
		for(String child_window  : child_windows) {
			if(!child_window.equalsIgnoreCase(parentId)) {
				driver.switchTo().window(child_window);
			}
		}
		
	}
	
	
	public void launchTheSitewithURL(WebDriver driver, String url) {
		driver.get(url);
		
	}
	
	public void SwitchToMainWindow(String paretnId, WebDriver driver) throws Exception {
		driver.close();
		Thread.sleep(2000);
		driver.switchTo().window(paretnId);
	}
	



}
