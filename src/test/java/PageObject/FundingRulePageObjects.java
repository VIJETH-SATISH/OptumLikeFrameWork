package PageObject;

import org.openqa.selenium.support.FindBy;

import BaseTest.BasePage;

import org.openqa.selenium.WebElement;

import Utils.NavigationUtil;

public class FundingRulePageObjects extends PageObjectBase {

	@FindBy(xpath="//select[@id='Enroll']")
	public WebElement Funding_Rule_SavingsAccount_DRPDWN;
	
	public void enrollAccountForEmployee(String accountName, String contributionAmount, String effectiveDate) throws InterruptedException {
		
		PageObjectBase.PageLoadWait();
		System.out.println(accountName);
		NavigationUtil.selectValueFromDropDown(Funding_Rule_SavingsAccount_DRPDWN, accountName);
		Thread.sleep(4000);
	}
}
