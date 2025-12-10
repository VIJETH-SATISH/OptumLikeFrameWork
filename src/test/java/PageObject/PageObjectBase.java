package PageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WindowType;

import BaseTest.BasePage;
import ExtentFactory.ExtentDriverFactory;
import Utils.WaitUtil;

public class PageObjectBase extends BasePage{
	
	public JavascriptExecutor jse;
	
	public PageObjectBase() {
		super();// constructr is executed and ""
		jse = (JavascriptExecutor) driver;
	}
	
	public static void PageLoadWait() {
		WaitUtil.waitForPageLoad(ExtentDriverFactory.getDriver());
		checkPageError();
	}
	
	public static void checkPageError() {
		for(int i=0; i < 5; i++) {
			if(ExtentDriverFactory.getDriver().getTitle().equalsIgnoreCase("Page Error")) {
				ExtentDriverFactory.getDriver().navigate().refresh();
				WaitUtil.waitForPageLoad(ExtentDriverFactory.getDriver());
			} else {
				break;
			}
		}
	}
	
	synchronized public void openNewTab() {
		ExtentDriverFactory.getDriver().switchTo().newWindow(WindowType.TAB);
	}
	
	synchronized public void switchBackToPreviousTab() {
		ExtentDriverFactory.getDriver().switchTo().window(System.getProperty("originalWindow"));
	}
}
