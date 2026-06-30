package PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;

import BaseTest.BasePage;
import ExtentFactory.ExtentDriverFactory;
import Utils.GenericUtil;
import Utils.WaitUtil;

public class HomePagePageObjects extends BasePage{

	@FindBy(xpath = "//input[@type='email']")
	private WebElement emailGmailId_ele;
	
	@FindBy(xpath = "//textarea[@title='Search' and @id='APjFqb']")
	private WebElement searchGoogle_ele;
	
	public void OpenNewTab() {
		
	}
	
	public void EnterTheCreds() throws Exception {
		WaitUtil.waitForPageLoad(ExtentDriverFactory.getDriver());
		GenericUtil.isElementDisplayed(emailGmailId_ele, 20);
		emailGmailId_ele.sendKeys("vijeth2011");
		WaitUtil.waitForPageLoad(ExtentDriverFactory.getDriver());
	}
	
	public void EnterTheSearchContent() throws Exception {
		WaitUtil.waitForPageLoad(ExtentDriverFactory.getDriver());
		mediumWait.get().until(ExpectedConditions.visibilityOf(emailGmailId_ele)).sendKeys("Selenium");
		Thread.sleep(2500);//look out it in the OPTUM Frame work 
	}
	
}
