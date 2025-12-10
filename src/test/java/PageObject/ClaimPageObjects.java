package PageObject;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ClaimPageObjects {

	@FindBy(how = How.XPATH, using = "")
	private List<WebElement> claimsIdList;
	
	public void selectClaimsCreatedByClaimId() {
		//lambda expression
		claimsIdList.stream().filter(webElement -> webElement.getText().contains(System.getProperty("claimId"))).findFirst().ifPresent(WebElement::click);
	}
	

}
