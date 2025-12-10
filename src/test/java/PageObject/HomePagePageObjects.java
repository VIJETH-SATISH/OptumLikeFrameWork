package PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;

import BaseTest.BasePage;
import Utils.GenericUtil;

public class HomePagePageObjects extends BasePage{

	@FindBy(xpath = "//input[@type='email']")
	private WebElement emailGmailId_ele;
	
	@FindBy(xpath = "//textarea[@title='Search' and @id='APjFqb']")
	private WebElement searchGoogle_ele;
	
	public void OpenNewTab() {
		
	}
	
	public void EnterTheCreds() throws Exception {
		Thread.sleep(2500);
		GenericUtil.isElementDisplayed(emailGmailId_ele, 20);
		emailGmailId_ele.sendKeys("vijeth2011");
		Thread.sleep(2500);
	}
	
	public void EnterTheSearchContent() throws Exception {
		Thread.sleep(2500);
		searchGoogle_ele.sendKeys("Selenium");
		Thread.sleep(2500);
	}
	
}
